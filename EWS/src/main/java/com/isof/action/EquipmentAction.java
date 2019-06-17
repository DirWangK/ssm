package com.isof.action;

import com.isof.service.IEquService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/equa")
public class EquipmentAction {
    @Autowired
    IEquService equService;
    @RequestMapping("/findAllEqu.do")
    @ResponseBody
    public Map<String, Object> findAllEqu(int page, int limit) {
        System.out.println(page + "," + limit);
        List<Map<String, Object>> list = equService.findAllEqu(page, limit);
        Map<String, Object> equCount = equService.findEquCount();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "用户信息");
        map.put("count", equCount.get("count"));//用户表中的总记录数
        map.put("data", list);
        return map;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }
    @RequestMapping("/equTotal.do")
    @ResponseBody
    public Map<String, Object> equTotal() {

        List<Map<String, Object>> list = equService.equTotal();
        Map map = new HashMap();
        List roleList=new ArrayList();
        List countlist=new ArrayList();
        for (Map mp:list) {
            roleList.add(mp.get("itype"));
            countlist.add(mp.get("count"));
        }
        map.put("roleList",roleList);
        map.put("countList",countlist);
        return map;
    }

    @RequestMapping("/addEquInfoById.do")
    @ResponseBody
    public String addEquInfoById(String snumber, String iname, String itype) {
        /*硬编码*/
        boolean temp = equService.addEquInfoById(snumber,iname,itype);
        if (temp)
            return "success";
        else
            return "fault";

    }

    @RequestMapping("/deleteEquInfoById.do")
    @ResponseBody
    public String deleteEquInfoById(String id) {
        System.out.println(id);
        String result = "success";
        int i=0;
        try{
            String  [] arr= id.split(",");
            for (String index:arr) {
                i= equService.deleteEquInfoById(Integer.parseInt(index));
                System.out.println("正在删除第"+index+"条");
            }
        }catch ( Exception e){
            i=0;
            e.printStackTrace();
        }
        if (i == 0)
            result = "fault";
        return result;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }
//    @RequestMapping("toopenEquInfoEdit")
//    public String toopenEquInfoEdit(Model model, Long id)
//    {
//        model.addAttribute("paper", paperService.queryById(id));
//        return "updatePaper"; }


    @RequestMapping("/openEquInfoEdit.do")
    @ResponseBody
    public String openEquInfoEdit(String snumber, String iname, String itype) {
        /*硬编码*/
        boolean temp = equService.openEquInfoEdit(snumber,iname,itype);
        if (temp)
            return "success";
        else
            return "fault";

    }
    @RequestMapping("/queryAllEqu.do")
    @ResponseBody
    public Map<String, Object> queryAllEqu(String itype) {//测试链接http://localhost:8080/EWS/equ/findAllEquNeedRep.do?page=1&limit=10
//        System.out.println(page+","+limit);//分页查询所有记录
        System.out.println("action -->findAllEquRepLog-->BEGIN");
        List<Map<String, Object>> list = equService.queryAllEqu(itype);
        Map<String, Object> equCount = equService.findEquCount();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "用户信息");
        map.put("count", equCount.get("count"));//用户表中的总记录数
        map.put("data", list);
        System.out.println("action -->findAllEquLog-->END");
        return map;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }
}
