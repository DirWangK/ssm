package com.isof.service;

import java.util.List;
import java.util.Map;

public interface IUserService {
    String login(String uname,String upwd);
    String returnRole(String uname);
   /* boolean register(String uname,String upwd,String email);*/
   boolean register(String uname,String upwd,String email,String role);
    List<Map<String,Object>> findAllUser(int page,int pageSize);
    Map<String,Object> findUserCount();
    int deleteUserInfoById(int id);
    List<Map<String,Object>> userTotal();
    boolean insertImage(Map<String,Integer> map);
    List<Map<String,Object>> getImage(Integer id);
    Map<String,Object> returnAUser(String uname);
    String editPwd(String uname, String pwd);

    int updateUserInfoById(int id, String uname, String email);
}
