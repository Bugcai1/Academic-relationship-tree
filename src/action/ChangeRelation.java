package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import SqlCon.DbUtil;

public class ChangeRelation {
	private int user_id;
	private int ID1;
	private int ID2;
	private String par_name;
	private String chi_name;
	private int relation;
	private String start;
	private String end;
	
    public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getID1() {
		return ID1;
	}
	public void setID1(int iD1) {
		ID1 = iD1;
	}
	public int getID2() {
		return ID2;
	}
	public void setID2(int iD2) {
		ID2 = iD2;
	}
	public String getPar_name() {
		return par_name;
	}
	public void setPar_name(String par_name) {
		this.par_name = par_name;
	}
	public String getChi_name() {
		return chi_name;
	}
	public void setChi_name(String chi_name) {
		this.chi_name = chi_name;
	}
	public int getRelation() {
		return relation;
	}
	public void setRelation(int relation) {
		this.relation = relation;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String deleteAction() throws SQLException, IOException
    {
    	DbUtil connects = new DbUtil();
    	ResultSet phoneset = null;
        String phone = "";
        String search_phone="select phone from register_person where id="+getUser_id()+";";
        phoneset = connects.executeQuery(search_phone);
        while(phoneset.next())
        {
            phone = phoneset.getString(1);
        }
        String table_name="a"+getUser_id()+phone;
        
        String sql="delete from "+table_name+" where user_id="+getID1()+" and relation_id="+getID2()+";";
        
        System.out.println("ɾ����䣺"+sql);
        connects.executeUpdate(sql);
        
        HttpServletResponse response=ServletActionContext.getResponse(); 
        response.setContentType("text/html;charset=utf-8");  
        PrintWriter out = response.getWriter();
    	 out.println("ok");
        out.flush();  
        out.close();
    	return "SUCCESS";
    }
    public String changeAction() throws SQLException, IOException
    {
    	System.out.println("-----------------------˨������---------------------------");
    	DbUtil connects = new DbUtil();
    	int pre;
        int now;
        now=getRelation();
        int result;
        ResultSet phoneset = null;
        String phone = "";
        String search_phone="select phone from register_person where id="+getUser_id()+";";
        phoneset = connects.executeQuery(search_phone);
        while(phoneset.next())
        {
            phone = phoneset.getString(1);
        }
        String table_name="a"+getUser_id()+phone;
        
        String sql="update "+table_name+" set relation="+now+",start_time='"+getStart()+"',end_time='"+getEnd()+"' where user_id="+getID1()+" and relation_id="+getID2()+";";
        System.out.println("���µ���䣺   "+sql+" "+getPar_name()+"  "+getChi_name());
        
        connects.executeUpdate(sql);
        String change="update register_person set name='"+getPar_name()+"' where id="+getID1()+";";
        String change_n="update no_register_person set name='"+getPar_name()+"' where id="+getID1()+";";
        
        System.out.println(change);
        System.out.println(change_n);
        
        connects.executeUpdate(change_n);
        connects.executeUpdate(change);
        
        change="update register_person set name='"+getChi_name()+"' where id="+getID2()+";";
        change_n="update no_register_person set name='"+getChi_name()+"' where id="+getID2()+";";
        
        connects.executeUpdate(change_n);
        connects.executeUpdate(change);
        System.out.println("ok!");
        HttpServletResponse response=ServletActionContext.getResponse(); 
        response.setContentType("text/html;charset=utf-8");  
        PrintWriter out = response.getWriter();
    	 out.println("ok");
        out.flush();  
        out.close();
        return "SUCCESS";
    }
}
