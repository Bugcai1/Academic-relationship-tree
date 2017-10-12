package Mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class con_sql {
	Connection con =null;
    Statement stat=null;
    ResultSet rs=null;
    public con_sql()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb","root","123456");
            stat=(Statement) con.createStatement(); 
        }catch(Exception e)
        {
            con=null;
        }
    }
    public ResultSet executeQuery(String sql)
    {
        try
        { 
            rs=stat.executeQuery(sql);
        }
        catch(Exception e)
        {
            rs=null;
        }
        return rs;
    }
    public int count_all() throws SQLException
    {
    	ResultSet result;
    	int r=0;
    	result=stat.executeQuery("select * from book");
    	while(result.next())
    	{
    		r++;
    	}

    	return r;
    }
    public int executeUpdate(String sql)
    {
        try
        {
        	stat.executeUpdate(sql);
            return 0;
        }
        catch(Exception e)
        {
            return -1;
        }
    }
}
