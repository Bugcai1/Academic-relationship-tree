package SqlCon;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.DatabaseMetaData;

public class DbUtil {
    Connection con =null;
    Statement stat=null;
    ResultSet rs=null;
    public DbUtil()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//连接驱动
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab","root","123456");//连接数据库
            stat=con.createStatement(); 
        }catch(Exception e)
        {
            con=null;
        }
    }
    /*
     * 创建一个人的关系表；命名方式 a+id+手机号
     * 
     */
    public void createNewPersonTable(String name) throws SQLException
    {
    	String sql="create table "+name+"(user_id int(3),relation_id int(3),relation int(2),start_time varchar(20),end_time varchar(20));";
    	System.out.println(sql);
    	stat.execute(sql);
    }
    /*
     * 查数的功能
     */
    public int executecount(String table_name,int id,String sql) throws SQLException
    {
    	int count=0;
    	String str="select * from "+table_name+" where user_id="+id+sql+";";
//    	System.out.println(str);
    	rs=stat.executeQuery(str);
    	while(rs.next())
    	{
    		count++;
    	}
    	return count;
    }
    /*
     * 创建注册函数的数据表
     */
    /*
     * 返回查询结果集的语句
     */
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
    /*
     * 执行更新语句，删除/修改/添加等
     */
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
    /*
     *返回id的函数（即查询注册表和未注册表表的所有数据个数之和，然后加1）
     */
    public int getID() throws SQLException
    {
    	int count=0;
    	String one="select count(*) from register_person;";
    	rs=stat.executeQuery(one);
    	while(rs.next())
    		count=rs.getInt(1);
    	String two="select count(*) from no_register_person;";
    	rs=stat.executeQuery(two);
    	while(rs.next())
    		count=count+rs.getInt(1);
    	return count+1;
    }
}
