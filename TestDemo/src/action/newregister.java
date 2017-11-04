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
	private int type;//���͵��� ���������
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
		if(getType()==1) //type==1����ʾ����ע�����
		{
			try {
				insert_register_People();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(getType()==2)  //type==2��ʱ�򣬱�ʾ����û��ע�����
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
	 * ����ע������id
	 * Ӧ��������淵�أ���η��أ�������������
	 * ������ֻ�Ƿ��ص�ǰ���������id��ǰ׺�ڽ�������Ӽ���
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
	 * δע�������������Լ��Ĺ�ϵ��
	 */
	public void createPerson_Table() throws SQLException
	{
		String table_Name="a"+getId()+getPhone();
		DbUtil con=new DbUtil();
		con.createNewPersonTable(table_Name);
	}
	/*
	 * ����ע��ĳ�Ա ��register_person���н������
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
	 * ����û��ע�������//��  no_register_person�������
	 */
	public int insert_no_register_People() throws SQLException
	{
		String insert = "insert into register_person values("+getId()+",'"+getName()+"','"+getName()+"','"+getSex()+"','"+getWork()+"','"+getPhone()+"');";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		//����һ����ϵ��
		return 1;
	}
	
}
