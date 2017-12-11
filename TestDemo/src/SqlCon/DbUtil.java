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
            Class.forName("com.mysql.jdbc.Driver");//锟斤拷锟斤拷锟斤拷锟斤拷
            //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab","root","123456");//锟斤拷锟斤拷锟斤拷锟捷匡拷
            con=DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn/app_finlab?useUnicode=true&characterEncoding=UTF-8","l245yolykj","10i1h2yx440ym1zyih510i1z5z440zwyi1iillk0");
            stat=con.createStatement(); 
        }catch(Exception e)
        {
            con=null;
        }
    }
    /*
     * 锟斤拷锟斤拷一锟斤拷锟剿的癸拷系锟斤拷锟斤拷锟斤拷锟斤拷式 a+id+锟街伙拷锟斤拷
     * 
     */
    public void createNewPersonTable(String name) throws SQLException
    {
    	String sql="create table "+name+"(user_id int(3),relation_id int(3),relation int(2),start_time varchar(20),end_time varchar(20));";
    	System.out.println(sql);
    	stat.execute(sql);
    }
    public void createNewTimeTable(String name) throws SQLException
    {
    	String sql="create table "+name+"(id1 int(3),id2 int(3),time varchar(20));";
    	System.out.println(sql);
    	stat.execute(sql);
    }
    /*
     * 锟斤拷锟斤拷锟侥癸拷锟斤拷
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
     * 锟斤拷锟斤拷注锟结函锟斤拷锟斤拷锟斤拷锟捷憋拷
     */
    /*
     * 锟斤拷锟截诧拷询锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
     */
    public ResultSet executeQuery(String sql)
    {
        try
        { 
        	
            rs=stat.executeQuery(sql);
            System.out.println("姝ｅ父鏌ヨ");
        }
        catch(Exception e)
        {
        	System.out.println("鍑洪棶棰樹簡");
            //rs=null;
        }
        return rs;
    }
    /*
     * 执锟叫革拷锟斤拷锟斤拷洌撅拷锟�/锟睫革拷/锟斤拷拥锟�
     */
    public int executeUpdate(String sql)
    {
        try
        {
        	System.out.println("sql+"+sql);
        	stat.executeUpdate(sql);
            return 0;
        }
        catch(Exception e)
        {
            return -1;
        }
    }
    /*
     *锟斤拷锟斤拷id锟侥猴拷锟斤拷锟斤拷锟斤拷锟斤拷询注锟斤拷锟斤拷未注锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷莞锟斤拷锟街拷停锟饺伙拷锟斤拷1锟斤拷
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
