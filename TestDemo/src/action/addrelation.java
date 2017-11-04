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
	private int user_id;//添加的人的id
	private String user_phone;//添加的人的手机号
	//以上两项，作为判断出入表的寻找依据
	
	private String relation;
	//插入的关系类型
	
	private int user1_id;
	private int user2_id;
	
	private String start_time;
	private String end_time;
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public int getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(int user1_id) {
		this.user1_id = user1_id;
	}

	public int getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(int user2_id) {
		this.user2_id = user2_id;
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
	public void add_Relation()
	{
		/*
		 * 这里边的relation还没有改成数字的类型，需要我们重新定义
		 */
		String table_name="a"+getUser_id()+getUser_phone();
		String sql="insert into "+table_name+" values("+getUser1_id()+","+getUser2_id()+","+getRelation()+","+getStart_time()+","+getEnd_time()+");";
		DbUtil con=new DbUtil();
		con.executeUpdate(sql);//执行插入语句
		
	}
}