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
	private int type;//传送的是 插入的类型
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


	
	

	/*
	 * 返回注册界面的id
	 * 应该是向界面返回，如何返回？？？？？？？
	 * 在这里只是返回当前的序号数，id的前缀在界面中添加即可
	 */
//	public static void main(String[] args) throws SQLException
//	{
//		System.out.println(get_person_id());
//	}
	public String getpersonid() throws SQLException, IOException
	{
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
	 * 未注册的人添加属于自己的关系表
	 */
	public void createPerson_Table() throws SQLException
	{
		String table_Name="a"+getId()+getPhone();
		DbUtil con=new DbUtil();
		con.createNewPersonTable(table_Name);
	}
	/*
	 * 插入注册的成员 向register_person表中进行添加
	 */
	public String insert_register_People() throws SQLException
	{
		String insert = "insert into register_person values("+getId()+",'"+getName()+"','"+getSex()+"','"+getWork()+"','"+getPhone()+"');";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		createPerson_Table();
		System.out.print(insert);
		return "SUCCESS";
	}
	/*
	 * 插入没有注册的人物//向  no_register_person表中添加
	 */
	public String insert_nregister() throws SQLException, IOException
	{
		System.out.println("没有注册的人物");
		DbUtil con=new DbUtil();
		int r=con.getID();
		String insert = "insert into no_register_person values("+r+",'"+getName()+"','"+getSex()+"','"+getWork()+"','"+getPhone()+"');";
		con.executeUpdate(insert);
		System.out.println("**************************\n"+insert);
		String search_p="select phone from register_person where id="+getFinal_people()+";";
		System.out.println("77777777777==="+search_p);
		ResultSet rs=con.executeQuery(search_p);
		
		String phone="";
		while(rs.next())
		{
			phone=rs.getString(1);
		}
		
		String table_name="a"+getFinal_people()+phone;
		
		String sql="insert into "+table_name+" values("+getUser_id()+","+r+","+getRelation()+",'"+getStart_time()+"','"+getEnd_time()+"');";
		System.out.println("插入函数      "+sql);
		con.executeUpdate(sql);
		
		return "SUCCESS";
	}
	
}
