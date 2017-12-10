package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import SqlCon.DbUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class newregister {
	private int type;//浼犻�佺殑鏄� 鎻掑叆鐨勭被鍨�
	private String name;
	private String id;
	private String sex;
	private String work;
	private String phone;
	
	private int user_id;
	private String start_time;
	private String end_time;
	private String relation;
	private int final_people;
	
	
	
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


	public String getEnd_time() {
		return end_time;
	}


	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}


	public String getRelation() {
		return relation;
	}


	public void setRelation(String relation) {
		this.relation = relation;
	}


	public int getFinal_people() {
		return final_people;
	}


	public void setFinal_people(int final_people) {
		this.final_people = final_people;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getWork() {
		return work;
	}


	public void setWork(String work) {
		this.work = work;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getpersonid() throws SQLException, IOException
	{
		System.out.println("-----------1111111111");
		int r=0;
		DbUtil con=new DbUtil();
		r=con.getID();
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter(); 

	    out.println(r);
	   
	    out.flush();  
	    out.close();
		return "SUCCESS";
	}
	
	/*
	 * 鏈敞鍐岀殑浜烘坊鍔犲睘浜庤嚜宸辩殑鍏崇郴琛�
	 */
	public void createPerson_Table() throws SQLException
	{
		String table_Name="a"+getId()+getPhone();
		DbUtil con=new DbUtil();
		con.createNewPersonTable(table_Name);
		table_Name="time"+getId();
		con.createNewTimeTable(table_Name);
	}
	/*
	 * 鎻掑叆娉ㄥ唽鐨勬垚鍛� 鍚憆egister_person琛ㄤ腑杩涜娣诲姞
	 */
	public String insert_register_People() throws SQLException
	{
		
		String insert = "insert into register_person values("+getId()+",'"+getName()+"','"+getSex()+"','"+getWork()+"','"+getPhone()+"');";
		System.out.println("man    "+insert);
//		return "success";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		createPerson_Table();
//		System.out.print(insert);
		return "SUCCESS";
	}
	/*
	 * 鎻掑叆娌℃湁娉ㄥ唽鐨勪汉鐗�//鍚�  no_register_person琛ㄤ腑娣诲姞
	 */
	public String insert_nregister() throws SQLException, IOException
	{
		String insert = "insert into no_register_person values("+getId()+",'"+getName()+"','"+getSex()+"','"+getWork()+"','"+getPhone()+"');";
		System.out.println("man    "+insert);
//		return "success";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		createPerson_Table();
//		System.out.print(insert);
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter(); 

	    out.println("r");
	   
	    out.flush();  
	    out.close();
		
		return "SUCCESS";
	}
	
}
