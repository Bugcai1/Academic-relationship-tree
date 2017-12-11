
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		System.out.println("lookall");
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
	public String add_Relation() throws SQLException, IOException
	{ 
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		
		System.out.println("添加关系");
		String search_p="select phone from register_person where id="+getFinal_people()+";"; 
		System.out.println("查询语句1："+search_p);
		DbUtil con=new DbUtil();
		ResultSet rs=con.executeQuery(search_p);
		int flag=0;
		String phone="";
		while(rs.next())
		{
			phone=rs.getString(1);
		}
		
		String table_name="a"+getFinal_people()+phone;
		
		//----------------------------------------------------------------------------------------------------------
		search_p = "select * from " + table_name + " where (user_id="+getUser_id()+" and relation_id="+getUser2_id() + ") or ("
				+ "user_id="+getUser2_id()+" and relation_id="+getUser_id()+");"; 
		rs = con.executeQuery(search_p);
		
		if(rs.next()) {
			flag=1;
			HttpServletResponse response=ServletActionContext.getResponse(); 
			response.setContentType("text/html;charset=utf-8");  
		    PrintWriter out = response.getWriter();
		   
		    
			   
		 
		    out.println(1);
		    out.flush();  
	        out.close();
		 //   System.out.println("回掉了"+flag);
		}
		
		System.out.println("回掉了"+flag);
		
		//----------------------------------------------------------------------------------------------------------

		/*
		*插入提醒语句，当作历史纪录
		*向被添加与添加得人都增添
		*数据表得形式：
		*表的命名方式：time+id;
		*id1 id2 relation time(添加的时间，年月日) type(标记是主动添加0还是被动添加1)
		*返回的形式：您的朋友id在什么时间将您添加为+某某+关系好友，请您注意查看。//您添加某某为好友
		*/
		//主动添加别人
		if(flag!=1)
		{
			String sql="select * from register_person where id="+getFinal_people()+";";
		ResultSet rst=con.executeQuery(sql);
		if(rst.next())
		{
			String time_table1="time"+getFinal_people();
			String time_sql="insert into "+time_table1+" values("+getFinal_people()+","+getUser2_id()+",'"+getTime()+"');";
			con.executeUpdate(time_sql);
			System.out.println(time_sql);
		}
		
		sql="select * from register_person where id="+getUser2_id()+";";
		rst=con.executeQuery(sql);
		if(rst.next())
		{
			String time_table2="time"+getUser2_id();
			String time_sql="insert into "+time_table2+" values("+getFinal_people()+","+getUser2_id()+",'"+getTime()+"');";
			con.executeUpdate(time_sql);
			System.out.println(time_sql);
		}
		
			sql="insert into "+table_name+" values("+getUser_id()+","+getUser2_id()+","+getRelation()+",'"+getStart()+"','"+getEnd()+"');";
			System.out.println("添加语句："+sql);
			con.executeUpdate(sql);
		}
		
	    
	   
	    
		System.out.println("flag="+jsonArray);
	    System.out.println("flag="+flag);
		return "SUCCESS";
	}
	public static String getTime()
	{
		String time="";
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println("tiem   "+df.format(day));   
		return df.format(day);
	}
}