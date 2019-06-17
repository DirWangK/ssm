package com.isof.dao.impl;

import com.isof.dao.IUserDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {
    @Autowired
    SqlSessionFactory sessionFactoryBean;

    @Override
    public String login(String uname, String upwd) throws Exception {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);//true  开启事务
        String statment = "com.isof.mapping.userMapper.login";
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("upwd", upwd);
        System.out.println(map);
        List list = sqlSession.selectList(statment, map);
        if (!list.isEmpty()) {
            statment = "com.isof.mapping.userMapper.updateLoginTime";
            sqlSession.update(statment, uname);
            return "success";
        } else
            return "fault";
    }

    @Override
    public String returnRole(String uname) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.userMapper.returnRole";
//            Map map = new HashMap();
//            map.put("uname", uname);
            Map<String, Object> map = sqlSession.selectOne(statement, uname);
            if (map.isEmpty())
                return "error";
            return (String) map.get("role");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

   /* @Override
    public boolean register(String uname, String upwd, String email) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.userMapper.register";
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("upwd", upwd);
        map.put("email", email);
        System.out.println(map);
        int i = sqlSession.insert(statement, map);
        if (i > 0)
            return true;
        else
            return false;
    }*/
   /*zz*/
   @Override
   public boolean register(String uname, String upwd, String email,String role) {
       SqlSession sqlSession = sessionFactoryBean.openSession(true);
       String statment = "com.isof.mapping.userMapper.register";
       Map map=new HashMap();
       map.put("uname",uname);
       map.put("upwd",upwd);
       map.put("email",email);
       map.put("role",role);
       //  System.out.println(map);
       int i = sqlSession.insert(statment,map);
       if(i>0)
           return true;
       else
           return false;
   }

    @Override
    public List<Map<String, Object>> findAllUser(int page, int pageSize) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.userMapper.findAllUser";
        Map map = new HashMap();
        map.put("page", (page - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = sqlSession.selectList(statement, map);
        return list;
    }

    @Override
    public List<Map<String, Object>> userTotal() {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.userMapper.userTotal";
        List<Map<String, Object>> list = sqlSession.selectList(statement);
        return list;
    }

    @Override
    public Map<String, Object> findUserCount() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.userMapper.findUserCount";
            Map map = sqlSession.selectOne(statement);
            System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteUserInfoById(int id) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.userMapper.deleteUserInfoById";
            int i = sqlSession.delete(statement, id);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
    public Map<String, Object> returnAUser(String uname) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.userMapper.select";
            Map map = new HashMap();
            map.put("uname", uname);
            List<Map<String, Object>> list = sqlSession.selectList(statement, map);
            if (list.isEmpty())
                return null;
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String editPwd(String uname, String pwd) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.userMapper.editPwd";
            Map map = new HashMap();
            map.put("uname", uname);
            map.put("pwd", pwd);
            int i = sqlSession.update(statement, map);
            if (i == 0)
                return "fault";
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    @Override
    public int updateUserInfoById(int id, String uname, String email) {
        try{
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statment = "com.isof.mapping.userMapper.updateUserInfoById";
            Map map=new HashMap();
            map.put("id",id);
            map.put("uname",uname);
            map.put("email",email);
            int i =sqlSession.update(statment,map);
            return i;

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }
}
