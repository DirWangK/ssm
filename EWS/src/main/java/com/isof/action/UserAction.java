package com.isof.action;

import com.isof.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserAction {
    @Autowired
    IUserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public String login(String uname, String upwd) {
        /*硬编码*/
        String str = userService.login(uname, upwd);
        return str;

    }
    /*@RequestMapping("/register.do")
    @ResponseBody
    public String register(String r_uname, String r_upwd,String email) {
        //IUserService userService=new UserServiceImpl();
        boolean temp=userService.register(r_uname, r_upwd, email);
        *//*boolean temp=true;
        //uname.equals("admin") && upwd.equals("123456")
        System.out.println(r_uname+", "+r_upwd+", "+email);
        *//*
        if (temp)
            return "success";
//                    +"<br>"+r_uname+", "+r_upwd+", "+email;
            //System.out.println(uname + "," + upwd);
        else
            return "fault";
    }*/
    /*zz*/

    @RequestMapping("/register.do")
    @ResponseBody
    public String register(String uname, String upwd,String email,String role) {
//        硬编码
        boolean temp = userService.register(uname,upwd,email,role);
        if (temp)
            return "success";
        else
            return "fault";
    }
    @RequestMapping("/returnRole.do")
    @ResponseBody
    public String returnRole(String uname){
        String str=userService.returnRole(uname);
        System.out.println(str);
        return str;
    }

    @RequestMapping("/findAllUser.do")
    @ResponseBody
    public Map<String, Object> findAllUser(int page, int limit) {
        System.out.println(page + "," + limit);
        List<Map<String, Object>> list = userService.findAllUser(page, limit);
        Map<String, Object> userCount = userService.findUserCount();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "用户信息");
        map.put("count", userCount.get("count"));//用户表中的总记录数
        map.put("data", list);
        return map;//返回JSON格式数据，但是不能转换，因为找不到JSON消息转换器
    }
    @RequestMapping("/userTotal.do")
    @ResponseBody
    public Map<String, Object> userTotal() {

        List<Map<String, Object>> list = userService.userTotal();
        Map map = new HashMap();
        List roleList=new ArrayList();
        List countlist=new ArrayList();
        for (Map mp:list) {
            roleList.add(mp.get("role"));
            countlist.add(mp.get("count"));
        }
        map.put("roleList",roleList);
        map.put("countList",countlist);
        return map;
    }

    @RequestMapping("/deleteUserInfoById.do")
    @ResponseBody
    public String deleteUserInfoById(String id) {
        System.out.println(id);
        String result = "success";
        int i=0;
        try{
         String  [] arr= id.split(",");
            for (String index:arr) {
                i= userService.deleteUserInfoById(Integer.parseInt(index));
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


    @RequestMapping("/uploadphoto.do")//上传到数据库
    public void uploadphoto(@RequestParam("fileName")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        Integer id = (Integer) request.getSession().getAttribute("Id");
        System.out.println(id);
//      String fileType = "jpg,png,jpeg";//允许上传的文件类型
//      int maxSize = 1024*200;//允许上传的文件最大大小(单位为byte)
        response.setContentType("text/html; charset=UTF-8");
        Map map =new HashMap();
//      Part part = request.getPart("fileName");//请求参数
//      String name=file.getOriginalFilename();
        System.out.println(file.getBytes());
        map.put("image", file.getBytes());
        map.put("id", id);
        userService.insertImage(map);
        response.sendRedirect("list.do");
    }

    /*@RequestMapping("/getPhoto.do")//从数据库提取到页面
    @ResponseBody
    public void getPhotoById (int id,HttpServletRequest request, final HttpServletResponse response) throws IOException {
        Integer iid = (Integer) request.getSession().getAttribute("Id");
        System.out.println(iid);
        System.out.println(id);
        List<Manage> image = userService.getImage(id);
        byte[] data = image.get(0).getImage();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputSream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputSream.write(buf, 0, len);
        }
        outputSream.close();
    }*/
    @RequestMapping("/returnAUser.do")
    @ResponseBody
    public Map<String,Object> returnAUser(String uname){
        Map map=new HashMap();
        map=userService.returnAUser(uname);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/editPwd.do")
    @ResponseBody
    public String editPwd(String uname,String pwd,String newpwd){
        String s=userService.login(uname, pwd);
        if(s!="success")
            return "fault";
        System.out.println("原密码正确");
        String str=userService.editPwd(uname,newpwd);
        return str;
    }
    @RequestMapping("/updateUserInfoById.do")
    @ResponseBody
    public String updateUserInfoById(int id, String uname, String email) {
        String result="success";
        int i=0;
        try{
            i = userService.updateUserInfoById(id,uname,email);
        }catch (Exception e){
            i=0;
            e.printStackTrace();
        }
        if(i==0)
        {
            result="fault";
        }
        return result ;
    }
}
