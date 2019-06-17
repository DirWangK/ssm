package com.isof.action;

import com.isof.service.IEquRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/equ/rep")
public class EquRepAction {
    @Autowired
    IEquRepService equService;
    @RequestMapping("/findAllEquNeedRep.do")
    @ResponseBody
    public Map<String, Object> findAllEquNeedRep(int page, int limit) {
        System.out.println(page+","+limit);//分页查询所有记录
        System.out.println("action -->findAllEquNeedRep-->BEGIN");
        List<Map<String, Object>> list = equService.findAllEquNeedRep(page, limit);
        Map<String, Object> equRepCount = equService.findEquNeedRepCount();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "用户信息");
        map.put("count", equRepCount.get("count"));//用户表中的总记录数
        map.put("data", list);
        System.out.println("action -->findAllEqu-->END");
        return map;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }

    @RequestMapping("/findAllEquRep.do")
    @ResponseBody
    public Map<String, Object> findAllEquRep(int page, int limit) {
        System.out.println(page+","+limit);//分页查询所有记录
        List<Map<String, Object>> list = equService.findAllEquRep(page, limit);
        Map<String, Object> equRepCount = equService.findEquRepCount();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "用户信息");
        map.put("count", equRepCount.get("count"));//用户表中的总记录数
        map.put("data", list);
        return map;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }

    @RequestMapping("/equRepTotal.do")
    @ResponseBody
    public Map<String, Object> equRepTotal(){
        System.out.println("action -->equTotal-->BEGIN");
        List<Map<String, Object>> list = equService.equRepTotal();
        Map map=new HashMap();
        System.out.println("action -->equRepTotal-->END");
        return list.get(0);
    }
    @RequestMapping("/deleteRepLogInfoBySnumber.do")
    @ResponseBody
    public String deleteRepLogInfoBySnumber(String snumber) {
        System.out.println("action -->deleteRepLogInfoBySnumber-->BEGIN");//通过id删除
        System.out.println(snumber);
        String result = "success";
        int i=0;
        try{
            String  [] arr= snumber.split(",");
            System.out.println(arr);
            for (String s:arr) {
                i= equService.deleteRepLogInfoBySnumber(s);
                System.out.println("正在删除snumber为:"+s+"的记录");
            }
        }catch ( Exception e){
            i=0;
            e.printStackTrace();
        }
        if (i == 0)
            result = "fault";
        System.out.println(this.toString()+"<-arrSnumber->"+snumber+"<-->"+result);
        System.out.println("action -->deleteInfoById-->END");

        return result;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }
    @RequestMapping("/equRepUpdate.do")
    @ResponseBody
    public String equRepUpdate(String snumber) {
        boolean temp=equService.equRepUpdate(snumber);

        if (temp)
            return "success";

        else
            return "fault";
    }

    @RequestMapping("/equRepAddLog.do")
    @ResponseBody
    public String equRepAddLog(String snumber, String iname,String itype,String dept,String iproducetime,String latestrepairtime,String uname) {
        //IUserService userService=new UserServiceImpl();//更新数据
        boolean temp=equService.equInsRepLog(snumber,iname,itype,dept,iproducetime,latestrepairtime,uname);

        if (temp)
            return "success";

        else
            return "fault";
    }

    @RequestMapping("/findAllEquRepLog.do")
    @ResponseBody
    public Map<String, Object> findAllEquRepLog(int page, int limit) {
        System.out.println("action -->findAllEquRepLog-->BEGIN");
        List<Map<String, Object>> list = equService.findAllEquRepLog(page, limit);
        Map<String, Object> equRepLogCount = equService.findEquRepCountLog();
        Map map = new HashMap();

        map.put("code", 0);
        map.put("msg", "用户信息");
        map.put("count", equRepLogCount.get("count"));//用户表中的总记录数
        map.put("data", list);
        System.out.println("action -->findAllEquLog-->END");
        return map;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }

    }
