package action;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import SqlCon.DbUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class queryAllUser {
	public String execute() throws Exception {
		System.out.println("----------------------------queryAllUser---------------------------------");
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		
		DbUtil con=new DbUtil();
		ResultSet rs=null;
		
		String sql="select * from register_person;";
		rs=con.executeQuery(sql);
		while(rs.next()) {
			jsonObject.put("ID",rs.getString(1));
			jsonObject.put("name", rs.getObject(2));
			jsonObject.put("sex", rs.getString(3));
			jsonObject.put("work", rs.getString(4));
			jsonObject.put("phone", rs.getString(5));
			jsonArray.add(jsonObject);
		}
		
		sql="select * from no_register_person;";
		rs=con.executeQuery(sql);
		while(rs.next()) {
			jsonObject.put("ID",rs.getString(1));
			jsonObject.put("name", rs.getObject(2));
			jsonObject.put("sex", rs.getString(3));
			jsonObject.put("work", rs.getString(4));
			jsonObject.put("phone", rs.getString(5));
			jsonArray.add(jsonObject);
		}
		System.out.println("!!!!!!!!!!"+jsonArray);
		
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter();
	    out.println(jsonArray);                            
		out.flush();  
	    out.close();
        return "SUCCESS";
    }
}
