package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import SqlCon.DbUtil;

public class person_login {
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String login() throws SQLException, IOException
	{
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter(); 
		
		String phone=getUserName();
		String id=getPassword();
		
		DbUtil con=new DbUtil();
		String sql="select * from register_person where id="+id+";";
		System.out.println(sql);
		ResultSet rs=null;
		rs=con.executeQuery(sql);
		System.out.println(rs);
		int flag=0;
		if(rs.first())
			flag=1;
	    out.println(flag);
	    out.flush();  
	    out.close();
		return "SUCCESS";
	}
}
