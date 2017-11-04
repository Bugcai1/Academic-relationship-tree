package action;

import java.io.IOException;
import java.io.PrintWriter;
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


	public String insert_people()
	{
		if(getType()==1) //type==1，表示插入注册的人
		{
			try {
				insert_register_People();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(getType()==2)  //type==2的时候，表示插入没有注册的人
		{
			try {
				insert_no_register_People();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "SUCCESS";
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
	public int insert_no_register_People() throws SQLException
	{
		String insert = "insert into register_person values("+getId()+",'"+getName()+"','"+getName()+"','"+getSex()+"','"+getWork()+"','"+getPhone()+"');";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		//创建一个关系表
		return 1;
	}
	
}
