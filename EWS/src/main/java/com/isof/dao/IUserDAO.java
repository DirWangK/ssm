package com.isof.dao;

import java.util.List;
import java.util.Map;

public interface IUserDAO {
    String login(String uname,String upwd) throws Exception;
   /* boolean register(String uname,String upwd,String email);*/
   boolean register(String uname,String upwd,String email,String role);
    List<Map<String,Object>> findAllUser(int page, int pageSize);
    List<Map<String,Object>> userTotal();
    Map<String,Object> findUserCount();
    int deleteUserInfoById(int id);
    boolean insertImage(Map<String,Integer> map);
    List<Map<String,Object>> getImage(Integer id);
    String returnRole(String uname);
    Map<String,Object> returnAUser(String uname);
    String editPwd(String uname, String pwd);
    int updateUserInfoById(int id, String uname, String email);

}
