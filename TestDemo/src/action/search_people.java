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
import freemarker.ext.util.IdentityHashMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	public String all_search() throws SQLException, IOException
	{
		int type=getSearch_type();//����ѯ�����ͷ�Ϊ���࣬1���������ȫ���Ĳ�ѯ����     2�������չ�ϵ���ͽ��в�ѯ����       3��������ʱ��ν��в�ѯ����
		System.out.println("jjjjj");
		System.out.println(getId()+"  "+getSearch_type());
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
//	public static void main(String[] args) throws SQLException
//	{
//		search_id();
//	}
	public void search_id() throws SQLException, IOException
	{
		JSONArray json=new JSONArray();
		JSONObject list=new JSONObject();
		DbUtil con=new DbUtil();
		DbUtil conn=new DbUtil();
		ResultSet rs=null;
		ResultSet rpar=null;
		String search_p="select * from register_person where id="+getId()+";";
		rs=con.executeQuery(search_p);//��ѯ�ֻ���
		String phone="";
		while(rs.next())
		{
			phone=rs.getString(5);
		}
//		json.add(list);
		/*
		 * ���Ϸ��ص�ʱ��ѯ�ߵ������Ϣ
		 */
//		System.out.println("haode "+list);
		
		 //����ֻ��ţ���ѯ�����Ҫ��֤�ǲ�����ȷ��
		String table_name="a"+getId()+phone;//ȷ��������Ϣ������֣�
//		System.out.println("tablename  "+table_name);
		/*
		 * �������Ĳ�α�����˼�룬�����ݱ��е���Ϣ��һ��һ���Ĳ������
		 */
		int Q[]=new int[1000];
		int front=0;
		int end=0;
		int kk=0;
		int pk=-1;
		int dex[]=new int[100];
		int dey[]=new int[100];
		Q[end++]=getId();/*���Ƚ��Լ�ѹ��*/
		while(front!=end)
		{
			int id=Q[front++];
			pk++;
			String search_relation="select * from "+table_name+" where user_id="+id+";";
			rs=con.executeQuery(search_relation);//��ʱrs���������ݺ���Ҫ��һ������Ҫ���ص����ݣ�һ����Ҫȡ��Ѱ�ң�
			int count=conn.executecount(table_name, id);
			int length=60;
			int k=0;
			while(rs.next())
			{
				k++;
				Q[end++]=rs.getInt(2);
				int par=rs.getInt(1);
				int chi=rs.getInt(2);
				int relation=rs.getInt(3);
				int start=rs.getInt(4);
				int end_t=rs.getInt(5);
//				System.out.println(start+" "+end_t);
				
				String search_information="select * from register_person where id="+par+";";
				rpar=conn.executeQuery(search_information);
				int flag=0;
				if(!rpar.next())//���費���ڵ�ʱ����null
				{
					flag=1;
					search_information="select * from no_register_person where id="+par+";";
					rpar=conn.executeQuery(search_information);
				}
				if(flag==0)
					rpar.previous();
				while(rpar.next())
				{
				list.put("parent_id",rpar.getString(1));
				list.put("parent_name", rpar.getString(2));
				list.put("parent_sex",rpar.getString(3));
				list.put("parent_work",rpar.getString(4));
				list.put("parent_phone",rpar.getString(5));
				}
				search_information="select * from register_person where id="+chi+";";
				rpar=conn.executeQuery(search_information);
				flag=0;
				if(!rpar.next())//���費���ڵ�ʱ����null
				{
					flag=1;
					search_information="select * from no_register_person where id="+chi+";";
					rpar=conn.executeQuery(search_information);
				}
				if(flag==0)
					rpar.previous();
				while(rpar.next())
				{
				list.put("child_id",rpar.getString(1));
				list.put("child_name", rpar.getString(2));
				list.put("child_sex",rpar.getString(3));
				list.put("child_work",rpar.getString(4));
				list.put("child_phone",rpar.getString(5));
				}
				
				list.put("relation",relation);
				list.put("start_time", start);
				list.put("end_time",end_t);
				
				int x=0;
				int y=0;
				
				x=(int)Math.sin(k/count*Math.PI)*length+dex[pk];
				y=(int)Math.cos(k/count*Math.PI)*length+dey[pk];
				dex[++kk]=x;
				dey[kk]=y;
				
				list.put("x",x);
				list.put("y", y);
				json.add(list);
			}	
		}
//		json.add(list);
		System.out.println(json);
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter(); 

	    out.println(json);
	    out.flush();  
	    out.close();
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
