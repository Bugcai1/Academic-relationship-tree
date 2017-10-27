package action;

import java.sql.SQLException;

import Mysql.con_sql;

public class editPeople {
	private int id;
	private String name;
	private String sex;
	private int age;
	private String identity;
	
	public void setId(int id)
	{
		this.id=id;
	}
	public int getId()
	{
		return this.id;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	
	public void setAge(int age)
	{
		this.age=age;
	}
	public int getAge()
	{
		return this.age;
	}
	
	public void setIdentity(String identity)
	{
		this.identity=identity;
	}
	public String getIdentity()
	{
		return this.identity;
	}
	
	
	/**
	 * 添加人员
	 */
	public static void add_People()		
	{
		con_sql con=new con_sql();
		String insert="insert into People values()";//待续
		int result=con.executeUpdate(insert);
		if (result==0)
			System.out.println("insert Sucessful");
		else
			System.out.println("insert fail");
	}
	
	/**
	 * 进行当前人数的查询
	 * 优化方法：可以采用触发器来记录总人数，而不需要每次遍历数据库
	 * @return
	 * @throws SQLException
	 */
	public static int count_People() throws SQLException
	{
		con_sql con=new con_sql();
		int result=con.count_all();
		System.out.println(result);
		return result;
	}
	
	/**
	 * 删除人员：删除依据时这个人的id
	 */
	public static void deletePeople()
	{
		con_sql con=new con_sql();
		String del="delete from People where id=*******";
		int result=con.executeUpdate(del);
		if (result==0)
			System.out.println("insert Sucessful");
		else
			System.out.println("insert fail");
	}
	
	/**
	 * 修改任务信息的功能
	 * 不允许修改id
	 * 
	 * 界面应该传来改动的项：而不是所有
	 */
	
	public static void change()
	{
		
	}
	
	/**
	 * 查询的功能
	 * 查询个人的隐私信息（应该可以根据id：姓名）
	 * 查询关系的功能
	 */
	public static void search_people()
	{
		
	}
	public static void search_relative()
	{
		
	}
}
