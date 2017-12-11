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

public class search_log{
	private int user_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String search_logg() throws SQLException, IOException
	{
		System.out.println("searchlog");
		JSONArray json=new JSONArray();
	     JSONObject list=new JSONObject();
		int id=getUser_id();

		
		String sqls="select * from register_person where id="+id+";";
		DbUtil conn=new DbUtil();
		ResultSet rs=conn.executeQuery(sqls);
		while(rs.next())
		{
			list.put("flag", rs.getString(6));
			json.add(list);
		}

		String table="time"+id;
		String sql="select * from "+table;
		DbUtil con=new DbUtil();
		ResultSet rst=con.executeQuery(sql);
		while(rst.next())
		{
			String result="";
			if(rst.getInt(1)==id)
			{
				result="您添加账户为"+rst.getInt(2)+"的用户";
			}
			else {
				result="您被账户为"+rst.getInt(2)+"的用户添加";
			}
			list.put("result",result);
			list.put("time", rst.getString(3));
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
}