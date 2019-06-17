package com.isof.service;

import java.util.List;
import java.util.Map;
public interface IEquService {
    String login(String uname, String upwd);
    String charkEquInfoById(int e_id);
    boolean register(String uname, String upwd, String email);
    boolean change(String snumber, String iname, String itype,String dept);

    List<Map<String,Object>> findAllEqu(int page, int pageSize);
    Map<String,Object> findEquCount();
    int deleteEquInfoById(int s_id);

    List<Map<String,Object>> findAllEquRep(int page, int limit);
    Map<String,Object> findEquRepCount();
    List<Map<String,Object>> equRepTotal();
    Map<String, Object> findEquNeedRepCount();

    boolean equRepUpdate(String snumber);

    List<Map<String, Object>> findAllEquNeedRep(int page, int limit);
    boolean equInsRepLog(String snumber, String iname, String itype, String dept, String iproducetime, String latestrepairtime,String uname);

    List<Map<String, Object>> findAllEquRepLog(int page, int limit);
    Map<String, Object> findEquRepCountLog();
    int deleteRepLogInfoBySnumber(String snumber);
    List<Map<String,Object>> equTotal();

    List<Map<String,Object>> equTotal1();

    List<Map<String,Object>> equTotal2();

    List<Map<String,Object>> equTotal3();

    List<Map<String,Object>> equTotal4();

    boolean addEquInfoById(String snumber, String iname, String itype);
    boolean openEquInfoEdit(String snumber, String iname, String itype);
    List<Map<String,Object>> queryAllEqu(String itype);
}
