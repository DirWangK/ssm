package com.isof.dao.impl;

import com.isof.dao.IEquDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

@Repository("equDAO")
public class EquDAOImpl implements IEquDAO {
    @Autowired
    SqlSessionFactory sessionFactoryBean;

    @Override
    public String login(String uname, String upwd) throws Exception {

        SqlSession sqlSession = sessionFactoryBean.openSession(true);//true  开启事务
        String statment = "com.isof.mapping.equMapper.login";
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("upwd", upwd);
        System.out.println(map);
        List list = sqlSession.selectList(statment, map);
        System.out.println(list);
        if (list != null) {
            statment = "com.isof.mapping.equMapper.updateLoginTime";
            sqlSession.update(statment, uname);
            return "success";
        } else
            return "fault";
    }

    @Override
    public String charkEquInfoById(int e_id) throws Exception {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statment = "com.isof.mapping.equMapper.logina";
        List list = sqlSession.selectList(statment, e_id);
        System.out.println();
        if (list != null) {
            statment = "com.isof.mapping.equMapper.updateLoginTime";
            sqlSession.update(statment, e_id);
            return "success";
        } else
            return "fault";
    }


    @Override
    public boolean register(String uname, String upwd, String email) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.equMapper.register";
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("upwd", upwd);
        map.put("email", email);
        int i = sqlSession.insert(statement, map);
        if (i > 0)
            return true;
        else
            return false;
    }
    @Override
    public boolean change(String snumber, String iname, String itype,String dept) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.equMapper.change";
        Map map = new HashMap();
        map.put("snumber", snumber);
        map.put("iname", iname);
        map.put("itype", itype);
        map.put("dept", dept);
        System.out.println(map);
        int i = sqlSession.insert(statement, map);
        if (i > 0)
            return true;
        else
            return false;
    }
    @Override
    public List<Map<String, Object>> findAllEqu(int page, int pageSize) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.equMapper.findAllEqu";
        Map map = new HashMap();
        map.put("page", (page - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = sqlSession.selectList(statement, map);
        return list;
    }

    public Map<String, Object> findEquCount() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findEquCount";
            Map map = sqlSession.selectOne(statement);
            System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteEquInfoById(int e_id) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.deleteEquInfoById";
            int i = sqlSession.delete(statement, e_id);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> findAllEquRep(int page, int limit) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findAllEquRep";
            Map map = new HashMap();
            map.put("page", (page - 1) * limit);
            map.put("pageSize", limit);
            List<Map<String, Object>> list = sqlSession.selectList(statement, map);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
       /* List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        Map mm=new HashMap();
        mm.put("dao--findAllEqu>",1);
        list.add(mm);
        return list;*/
    }

    @Override
    public Map<String, Object> findEquRepCount() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findEquRepCount";
            Map map = sqlSession.selectOne(statement);
//            System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        /*Map<String, Object> map=new HashMap();
        map.put("dao-->findEquCount",2);
        return map;*/
    }

    @Override
    public Map<String, Object> findEquNeedRepCount() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findEquNeedRepCount";
            String statement1 = "com.isof.mapping.equMapper.creatNeedRepView";
            int i = sqlSession.update(statement1);
            Map map = sqlSession.selectOne(statement);
//            System.out.println(map);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        /*Map<String, Object> map=new HashMap();
        map.put("dao-->findEquCount",2);
        return map;*/
    }

    @Override
    public boolean equRepUpdate(String snumber) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.updateLatestrepairtime";
            int i = sqlSession.update(statement, snumber);
            System.out.println("update flag " + i + "--" + snumber);
            if (i == 0)
                return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> findAllEquNeedRep(int page, int limit) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findAllEquNeedRep";
            Map map = new HashMap();
            map.put("page", (page - 1) * limit);
            map.put("pageSize", limit);
            List<Map<String, Object>> list = sqlSession.selectList(statement, map);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equInsRepLog(String snumber, String iname, String itype, String dept, String iproducetime, String latestrepairtime, String uname) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.equInsRepLog";

            System.out.println(iproducetime + "<--->" + latestrepairtime);
            long it = new Long(iproducetime);
            Date time1 = new Date(it);

            long it2 = new Long(latestrepairtime);
            Date time2 = new Date(it2);

            Map map = new HashMap();
//            "snumber": snumber,"iname":iname,"itype":itype,"dept":dept,"iproducetime":iproducetime,"pastrepair":latestrepairtime
            map.put("snumber", snumber);
            map.put("iname", iname);
            map.put("itype", itype);
            map.put("dept", dept);
            map.put("iproducetime", time1);
            map.put("latestrepairtime", time2);
            map.put("repairpeople", uname);
//            List<Map<String, Object>> list = sqlSession.selectList(statement, map);
            System.out.println(snumber + iname + itype + dept + time1 + time2 + uname);
            int i = sqlSession.insert(statement, map);
            if (i == 0)
                return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Map<String, Object>> findAllEquRepLog(int page, int limit) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findAllEquRepLog";
            Map map = new HashMap();
            map.put("page", (page - 1) * limit);
            map.put("pageSize", limit);
            List<Map<String, Object>> list = sqlSession.selectList(statement, map);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> findEquRepCountLog() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.findEquRepCountLog";
            Map map = sqlSession.selectOne(statement);
//            System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> equRepTotal() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map map = new HashMap<String, Object>();
        map.put("dao-equTotal", 3);
        list.add(map);
        return list;
    }

    @Override
    public int deleteRepLogInfoBySnumber(String snumber) {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.deleteRepLogInfoBySnumber";
            int i = sqlSession.delete(statement, snumber);
//            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Override
    public List<Map<String, Object>> equTotal() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.equTotal";
            List<Map<String, Object>> list = sqlSession.selectList(statement);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> equTotal1() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.equTotal1";
            List<Map<String, Object>> list = sqlSession.selectList(statement);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> equTotal2() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.equTotal2";
            List<Map<String, Object>> list = sqlSession.selectList(statement);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> equTotal3() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.equTotal3";
            List<Map<String, Object>> list = sqlSession.selectList(statement);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> equTotal4() {
        try {
            SqlSession sqlSession = sessionFactoryBean.openSession(true);
            String statement = "com.isof.mapping.equMapper.equTotal4";
            List<Map<String, Object>> list = sqlSession.selectList(statement);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addEquInfoById(String snumber, String iname, String itype) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isof.mapping.equMapper.addEquInfoById";
        Map map = new HashMap();
        map.put("snumber", snumber);
        map.put("iname", iname);
        map.put("itype", itype);
        System.out.println(map);
        try {
            int i = sqlSession.insert(statement, map);
            if (i > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean openEquInfoEdit(String iname, String snumber, String itype) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isoft.mapping.equMapper.openEquInfoEdit";
        Map map = new HashMap();
        map.put("iname", iname);
        map.put("snumber", snumber);
        map.put("itype", itype);
        int i = sqlSession.insert(statement, map);
        if (i > 0)
            return true;
        else
            return false;
    }

    public List<Map<String, Object>> queryAllEqu(String itype) {
        SqlSession sqlSession = sessionFactoryBean.openSession(true);
        String statement = "com.isoft.mapping.equMapper.queryAllEqu";
        Map map = new HashMap();
        map.put("itype", itype);
        List<Map<String, Object>> list = sqlSession.selectList(statement, map);
        return list;
    }
}


