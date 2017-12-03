package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import SqlCon.DbUtil;
import net.sf.json.JSONArray;

public class search_people {
	private int search_type;
	private int id;
	private int relation_type;
	private int start_time;
	private int end_time;
	public int getSearch_type() {
		return search_type;
	}
	public void setSearch_type(int search_type) {
		this.search_type = search_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(int relation_type) {
		this.relation_type = relation_type;
	}
	public int getStart_time() {
		return start_time;
	}
	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}
	public int getEnd_time() {
		return end_time;
	}
	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}
	
	/*
	 * �ܵĴ�����
	 */
	public String all_search() throws SQLException
	{
		int type=getSearch_type();//����ѯ�����ͷ�Ϊ���࣬1���������ȫ���Ĳ�ѯ����     2�������չ�ϵ���ͽ��в�ѯ����       3��������ʱ��ν��в�ѯ����
		
		if(type==1)
			search_id();
		else if(type==2)
			look_type();
		else if(type==3)
			search_time();
		return "SUCCESS";
	}
	/*
	 * ���ò�ͬ�ķ�ʽ����淵��json���ֵ��Ҫ�����ն�Ӧ��js���շ�ʽ
	 */
	
	/*
	 * ����id����ĳ���˵Ĺ�ϵ��
	 * �����߼������ȸ���id��register_person���в����ֻ��ţ�ȷ�����˵Ĺ�ϵ�����ڵĵط�
	 * ��ѯ��ϵ���е�����id��Ȼ��register_person��no_register_person���н�����ϸ���ݵĲ�ѯ
	 */
	public void search_id() throws SQLException
	{
		
		DbUtil con=new DbUtil();
		ResultSet rs=null;
		String search_p="select phone from register_person where id="+getId()+";";
		rs=con.executeQuery(search_p);//��ѯ�ֻ���
		
		String phone=rs.getString(1);//����ֻ��ţ���ѯ�����Ҫ��֤�ǲ�����ȷ��
		
		String table_name="a"+getId()+phone;//ȷ��������Ϣ������֣�
		
		String search_relation="select * from "+table_name+";";
		
		rs=con.executeQuery(search_relation);//��ʱrs���������ݺ���Ҫ��һ������Ҫ���ص����ݣ�һ����Ҫȡ��Ѱ�ң�
		
		
		
		
		/*
		 * Ѱ��ÿ���˵������Ϣ��Ȼ����json�ĸ�ʽ���з���
		 */
	}
	public void look_type() throws SQLException
	{
		DbUtil con=new DbUtil();
		ResultSet rs=null;
		String search_p="select phone from register_person where id="+getId()+";";
		rs=con.executeQuery(search_p);//��ѯ�ֻ���
		
		String phone=rs.getString(1);//����ֻ��ţ���ѯ�����Ҫ��֤�ǲ�����ȷ��
		
		String table_name="a"+getId()+phone;//ȷ��������Ϣ������֣�
		
		String search_relation="select * from "+table_name+" where relation="+getSearch_type()+";";
		
		rs=con.executeQuery(search_relation);
		
		/*
		 * ��������һ��������
		 */
	}
	
	public void search_time() throws SQLException
	{
		DbUtil con=new DbUtil();
		ResultSet rs=null;
		String search_p="select phone from register_person where id="+getId()+";";
		rs=con.executeQuery(search_p);//��ѯ�ֻ���
		
		String phone=rs.getString(1);//����ֻ��ţ���ѯ�����Ҫ��֤�ǲ�����ȷ��
		
		String table_name="a"+getId()+phone;//ȷ��������Ϣ������֣�
		
		int start=getStart_time();
		int end=getEnd_time();
		
		/*
		 * ��Ե����⣺��ν����ݿ��е�ʱ��������е�ʱ��ν��бȽϣ�ѡ����������ʵ��Ǹ�
		 */
		//String search_relation="select * from "+table_name+";";//������仹����������Ҫ���
		
		
	}
}
