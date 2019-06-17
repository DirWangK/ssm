package com.isof.dao;

import java.util.List;
import java.util.Map;

public interface IEquDAO {
    String login(String uname, String upwd) throws Exception;
    String charkEquInfoById(int e_id) throws Exception;
    boolean register(String uname, String upwd, String email);
    boolean change(String snumber, String iname, String itype,String dept);

    List<Map<String,Object>> findAllEqu(int page, int pageSize);
    Map<String,Object> findEquCount();
    int deleteEquInfoById(int e_id);

    List<Map<String,Object>> findAllEquRep(int page, int limit);
    Map<String,Object> findEquRepCount();
    List<Map<String,Object>> equRepTotal();
    int deleteRepLogInfoBySnumber(String snumber);
    Map<String, Object> findEquNeedRepCount();
    boolean equRepUpdate(String snumber);
    List<Map<String, Object>> findAllEquNeedRep(int page, int limit);
    boolean equInsRepLog(String snumber, String iname, String itype, String dept, String iproducetime, String latestrepairtime,String uname);
    List<Map<String, Object>> findAllEquRepLog(int page, int limit);
    Map<String, Object> findEquRepCountLog();
    List<Map<String,Object>> equTotal();
    List<Map<String,Object>> equTotal1();
    List<Map<String,Object>> equTotal2();
    List<Map<String,Object>> equTotal3();
    List<Map<String,Object>> equTotal4();

    boolean addEquInfoById(String snumber, String iname, String itype);
    boolean openEquInfoEdit(String snumber, String iname, String itype);
    List<Map<String,Object>> queryAllEqu(String itype);

}
