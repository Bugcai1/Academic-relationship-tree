/*
 * 对于删除操作的感想
 * 
 * 应该在拉动任务进表的时刻就将任务添加到自己的关系中，点击删除然后从关系中删除
 * 
 * 1.方便 ：后台不用一起解析json；
 * 2.对于指定多层关系具有好处 ，可以随时变更
 */



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

public class addrelation {
	private int user_id;
	private String name;
	private String sex;
	private String work;
	private String phone;
	private int id;
	private int type;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String lookall() throws SQLException, IOException
	{
		String sql="select * from register_person;";
		DbUtil con=new DbUtil();
		ResultSet rs=null;
		rs=con.executeQuery(sql);
		JSONArray json=new JSONArray();
		JSONObject list=new JSONObject();
		while(rs.next())
		{
			list.put("id",rs.getString(1));
			list.put("name", rs.getObject(2));
			list.put("sex", rs.getString(3));
			list.put("work", rs.getString(4));
			list.put("phone", rs.getString(5));
			json.add(list);
		}
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter(); 
	    System.out.println(json);
	    out.println(json);
	   
	    out.flush();  
	    out.close();
		return "SUCCESS";
	}
	public void add_Relation() throws SQLException
	{
		System.out.println("----------------\n"+getWork());
		System.out.println(getType()+"     "+getUser_id());
		
		String search_p="select phone from register_person where id="+getUser_id()+";"; 
		
		DbUtil con=new DbUtil();
		ResultSet rs=con.executeQuery(search_p);
		
		String phone="";
		while(rs.next())
		{
			phone=rs.getString(1);
		}
		
		String table_name="a"+getUser_id()+phone;
		
		String sql="insert into "+table_name+" values("+getUser_id()+","+getId()+","+getType()+",0,0);";
		System.out.println(sql);
		con.executeUpdate(sql);
		
	}
}