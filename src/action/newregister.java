package action;

import java.sql.SQLException;

import SqlCon.DbUtil;

public class newregister {
	private int type;//传送的是 插入的类型
	private String name;
	private String nickname;
	private String position;
	private String birthday;
	private String qq;
	private String sex;
	private String introduce;
	private String phone;
	
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type=type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId()
	{
		int id=1;
		return id;
	}
	
	public String insert_people()
	{
		if(getType()==1) //type==1，表示插入注册的人
		{
			try {
				insert_register_People();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(getType()==2)  //type==2的时候，表示插入没有注册的人
		{
			try {
				insert_no_register_People();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "SUCCESS";
	}
	

	/*
	 * 返回注册界面的id
	 * 应该是向界面返回，如何返回？？？？？？？
	 * 在这里只是返回当前的序号数，id的前缀在界面中添加即可
	 */
	public int get_person_id()
	{
		int r=0;
		DbUtil con=new DbUtil();
		r=con.getID();
		return r;
	}
	
	/*
	 * 未注册的人添加属于自己的关系表
	 */
	public void createPerson_Table() throws SQLException
	{
		String table_Name="a"+getId()+getPhone();
		DbUtil con=new DbUtil();
		con.createNewPersonTable(table_Name);
	}
	/*
	 * 插入注册的成员 向register_person表中进行添加
	 */
	public int insert_register_People() throws SQLException
	{
		String insert = "insert into register_person values("+getId()+",'"+getNickname()+"','"+getName()+"','"+getPosition()+"','"+getBirthday()+"','"+getSex()+"','"+getQq()+"','"+getPhone()+"');";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		createPerson_Table();
		return 1;
	}
	/*
	 * 插入没有注册的人物//向  no_register_person表中添加
	 */
	public int insert_no_register_People() throws SQLException
	{
		String insert = "insert into no_register_person values("+getId()+",'"+getNickname()+"','"+getName()+"','"+getPosition()+"','"+getBirthday()+"','"+getSex()+"','"+getQq()+"','"+getPhone()+"');";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		//创建一个关系表
		return 1;
	}
	
}
