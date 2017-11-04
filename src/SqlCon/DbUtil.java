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
     * ����һ���˵Ĺ�ϵ����������ʽ a+id+�ֻ���
     * 
     */
    public void createNewPersonTable(String name) throws SQLException
    {
    	String sql="create table a"+name+"(user_id int(3),relation_id int(3),relation int(2),start_time int(20),end_time int(20);";
    	System.out.println(sql);
    	stat.execute(sql);
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
        }
        catch(Exception e)
        {
            rs=null;
        }
        return rs;
    }
    /*
     * ִ�и�����䣬ɾ��/�޸�/���ӵ�
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
     *����id�ĺ���������ѯע�����δע��������������ݸ���֮�ͣ�Ȼ���1��
     */
    public int getID()
    {
    	int count=0;
    	return count+1;
    }
}