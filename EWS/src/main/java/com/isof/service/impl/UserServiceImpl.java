package com.isof.service.impl;

import com.isof.dao.IUserDAO;
import com.isof.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDAO userDAO;
    @Override
    public String login(String uname, String upwd) {
        try{
            String str=userDAO.login(uname,upwd);
            return str;
        }catch (Exception e){
e.printStackTrace();
        }
       return null;
    }
    @Override
    public String returnRole(String uname) {
        return userDAO.returnRole(uname);
    }

   /* @Override
    public boolean register(String uname, String upwd, String email) {

        boolean temp=userDAO.register(uname,upwd,email);
        return temp;
    }*/
   @Override
   public boolean register(String uname, String upwd, String email,String role) {
       boolean temp = userDAO.register(uname,upwd,email,role);
       return temp;
   }

    @Override
    public List<Map<String, Object>> findAllUser(int page, int pageSize) {
        return userDAO.findAllUser(page,pageSize);
    }

    @Override
    public Map<String, Object> findUserCount() {
        return userDAO.findUserCount();
    }

    @Override
    public int deleteUserInfoById(int id) {
        return userDAO.deleteUserInfoById(id);
    }

    @Override
    public List<Map<String, Object>> userTotal() {
        return userDAO.userTotal();
    }

    @Override
    public boolean insertImage(Map<String, Integer> map) {
        return false;
    }

    @Override
    public List<Map<String, Object>> getImage(Integer id) {
        return null;
    }
    @Override
    public Map returnAUser(String uname) {
        return userDAO.returnAUser(uname);
    }

    @Override
    public String editPwd(String uname, String pwd) {
        return userDAO.editPwd(uname, pwd);
    }
    @Override
    public int updateUserInfoById(int id, String uname, String email) {
        return userDAO.updateUserInfoById(id,uname,email);
    }
}
