package action;

import java.sql.SQLException;

import SqlCon.DbUtil;

public class newregister {
	private int type;//���͵��� ���������
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
		if(getType()==1) //type==1����ʾ����ע�����
		{
			try {
				insert_register_People();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(getType()==2)  //type==2��ʱ�򣬱�ʾ����û��ע�����
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
	 * ����ע������id
	 * Ӧ��������淵�أ���η��أ�������������
	 * ������ֻ�Ƿ��ص�ǰ���������id��ǰ׺�ڽ�������Ӽ���
	 */
	public int get_person_id()
	{
		int r=0;
		DbUtil con=new DbUtil();
		r=con.getID();
		return r;
	}
	
	/*
	 * δע�������������Լ��Ĺ�ϵ��
	 */
	public void createPerson_Table() throws SQLException
	{
		String table_Name="a"+getId()+getPhone();
		DbUtil con=new DbUtil();
		con.createNewPersonTable(table_Name);
	}
	/*
	 * ����ע��ĳ�Ա ��register_person���н������
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
	 * ����û��ע�������//��  no_register_person�������
	 */
	public int insert_no_register_People() throws SQLException
	{
		String insert = "insert into no_register_person values("+getId()+",'"+getNickname()+"','"+getName()+"','"+getPosition()+"','"+getBirthday()+"','"+getSex()+"','"+getQq()+"','"+getPhone()+"');";
		DbUtil con=new DbUtil();
		con.executeUpdate(insert);
		//����һ����ϵ��
		return 1;
	}
	
}
