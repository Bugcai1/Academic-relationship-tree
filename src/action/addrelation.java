package action;

import SqlCon.DbUtil;

public class addrelation {
	private int user_id;//��ӵ��˵�id
	private String user_phone;//��ӵ��˵��ֻ���
	//���������Ϊ�жϳ�����Ѱ������
	
	private String relation;
	//����Ĺ�ϵ����
	
	private int user1_id;
	private int user2_id;
	
	private String start_time;
	private String end_time;
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public int getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(int user1_id) {
		this.user1_id = user1_id;
	}

	public int getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(int user2_id) {
		this.user2_id = user2_id;
	}
	
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void add_Relation()
	{
		/*
		 * ����ߵ�relation��û�иĳ����ֵ����ͣ���Ҫ�������¶���
		 */
		String table_name="a"+getUser_id()+getUser_phone();
		String sql="insert into "+table_name+" values("+getUser1_id()+","+getUser2_id()+","+getRelation()+","+getStart_time()+","+getEnd_time()+");";
		DbUtil con=new DbUtil();
		con.executeUpdate(sql);//ִ�в������
		
	}
}