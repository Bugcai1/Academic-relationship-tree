
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
	private int user2_id;
	private int relation;
	private String start;
	private String end;
	private int final_people;
	
	public int getFinal_people()
	{
		return final_people;
	}
	public void setFinal_people(int final_people)
	{
		this.final_people=final_people;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(int user2_id) {
		this.user2_id = user2_id;
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
		sql="select * from no_register_person;";
		rs=con.executeQuery(sql);
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
	public String add_Relation() throws SQLException
	{
		System.out.println("插入的函数“");
		String search_p="select phone from register_person where id="+getFinal_people()+";"; 
//		System.out.println(search_p);
		DbUtil con=new DbUtil();
		ResultSet rs=con.executeQuery(search_p);
		
		String phone="";
		while(rs.next())
		{
			phone=rs.getString(1);
		}
		
		String table_name="a"+getFinal_people()+phone;
		String sql="insert into "+table_name+" values("+getUser_id()+","+getUser2_id()+","+getRelation()+","+getStart()+","+getEnd()+");";
//		System.out.println(sql);
		con.executeUpdate(sql);
		return "SUCCESS";
	}
}