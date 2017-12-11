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
            Class.forName("com.mysql.jdbc.Driver");//��������
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab","root","123456");//�������ݿ�
            stat=con.createStatement(); 
        }catch(Exception e)
        {
            con=null;
        }
    }
    /*
     * ����һ���˵Ĺ�ϵ��������ʽ a+id+�ֻ���
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
     * �����Ĺ���
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
     * ����ע�ắ�������ݱ�
     */
    /*
     * ���ز�ѯ����������
     */
    public ResultSet executeQuery(String sql)
    {
        try
        { 
        	
            rs=stat.executeQuery(sql);
            System.out.println("正常查询");
        }
        catch(Exception e)
        {
        	System.out.println("出问题了");
            //rs=null;
        }
        return rs;
    }
    /*
     * ִ�и�����䣬ɾ��/�޸�/��ӵ�
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
     *����id�ĺ���������ѯע����δע������������ݸ���֮�ͣ�Ȼ���1��
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
