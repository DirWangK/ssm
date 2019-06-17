package com.isof.service.impl;

import com.isof.dao.IEquDAO;
import com.isof.service.IEquService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service("equService")
public class EquServiceImpl implements IEquService {
    @Autowired
    IEquDAO equDAO;

    @Override
    public String login(String uname, String upwd) {
        try {
            String str = equDAO.login(uname, upwd);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String charkEquInfoById(int e_id) {
        try {
            String str = equDAO.charkEquInfoById(e_id);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean register(String uname, String upwd, String email) {

        boolean temp = equDAO.register(uname, upwd, email);
        return temp;
    }

    @Override
    public boolean change(String snumber, String iname, String itype,String dept) {
        boolean temp = equDAO.change(snumber, iname, itype,dept);
        return temp;
    }
    @Override
    public List<Map<String, Object>> findAllEqu(int page, int pageSize) {
        return equDAO.findAllEqu(page, pageSize);
    }

    @Override
    public Map<String, Object> findEquCount() {
        return equDAO.findEquCount();
    }

    @Override
    public int deleteEquInfoById(int e_id) {
        return equDAO.deleteEquInfoById(e_id);
    }

    @Override
    public List<Map<String, Object>> findAllEquRep(int page, int limit) {
        System.out.println(this.toString() + "<-findAllEquRep->");
        return equDAO.findAllEquRep(page, limit);
    }

    @Override
    public Map<String, Object> findEquRepCount() {
        System.out.println(this.toString() + "<-findEquRepCount->");
        return equDAO.findEquRepCount();
    }

    @Override
    public List<Map<String, Object>> equRepTotal() {
        System.out.println(this.toString() + "<-equRepTotal->");
        return equDAO.equRepTotal();
    }


    @Override
    public Map<String, Object> findEquNeedRepCount() {
        System.out.println(this.toString() + "<-findEquNeedRepCount->");

        return equDAO.findEquNeedRepCount();
    }

    @Override
    public boolean equRepUpdate(String snumber) {
        System.out.println(this.toString() + "<-equRepUpdate->");
        return equDAO.equRepUpdate(snumber);
    }

    @Override
    public List<Map<String, Object>> findAllEquNeedRep(int page, int limit) {
        System.out.println(this.toString() + "<-findAllEquNeedRep->");
        return equDAO.findAllEquNeedRep(page, limit);
    }

    @Override
    public boolean equInsRepLog(String snumber, String iname, String itype, String dept, String iproducetime, String latestrepairtime, String uname) {
        System.out.println(this.toString() + "<-equLogUpdate->");
        return equDAO.equInsRepLog(snumber, iname, itype, dept, iproducetime, latestrepairtime, uname);
    }

    @Override
    public List<Map<String, Object>> findAllEquRepLog(int page, int limit) {
        System.out.println(this.toString() + "<-findAllEquRepLog->");
        return equDAO.findAllEquRepLog(page, limit);
    }

    @Override
    public Map<String, Object> findEquRepCountLog() {
        System.out.println(this.toString() + "<-findEquRepCountLog->");
        return equDAO.findEquRepCountLog();
    }

    @Override
    public int deleteRepLogInfoBySnumber(String snumber) {
        System.out.println(this.toString() + "<-deleteRepLogInfoBySnumber->");
        return equDAO.deleteRepLogInfoBySnumber(snumber);
    }

    @Override
    public List<Map<String, Object>> equTotal() {
        return equDAO.equTotal();
    }

    @Override
    public List<Map<String, Object>> equTotal1() {
        return equDAO.equTotal1();
    }

    @Override
    public List<Map<String, Object>> equTotal2() {
        return equDAO.equTotal2();
    }

    @Override
    public List<Map<String, Object>> equTotal3() {
        return equDAO.equTotal3();
    }

    @Override
    public List<Map<String, Object>> equTotal4() {
        return equDAO.equTotal4();
    }

    @Override
    public boolean addEquInfoById(String snumber, String iname, String itype) {
        boolean temp=equDAO.addEquInfoById(snumber,iname,itype);
        return temp;
    }
    @Override
    public boolean openEquInfoEdit(String snumber,String iname,String itype) {
        return equDAO.openEquInfoEdit(snumber,iname,itype);
    }
    public List<Map<String, Object>> queryAllEqu(String itype) {
        return equDAO.queryAllEqu(itype);
    }
}
