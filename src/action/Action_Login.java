package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import SqlCon.DbUtil;
import com.opensymphony.xwork2.ActionContext;

public class Action_Login {

	 private String username;
	    private String password;

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

//	    public String execute() throws Exception {
//	    	System.out.println("sdff");
//	       return "SUCCESS";
//	    }
	    public int getNumberofID() throws SQLException
	    {
	        DbUtil connect =new DbUtil();
	        ResultSet reg =null;
	        ResultSet nreg =null;
	        int x=0;
	        int y=0;
	        int number = 0 ;
	        String findreg = "Select count(*) from register_person;";
	        String findnoreg = "Select count(*) from no_register_person values;";
	        reg = connect.executeQuery(findreg);
	        while(reg.next())
	        {
	            x = reg.getInt(1);
	        }
	        nreg = connect.executeQuery(findnoreg);
	        while(nreg.next())
	        {
	            y = nreg.getInt(1);
	        }
	        number = x+y+1;
	        return number;
	    }

}
