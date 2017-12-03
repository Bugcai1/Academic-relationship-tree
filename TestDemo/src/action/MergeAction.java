package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import SqlCon.DbUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MergeAction {
	private int ID1;               //ǰ�˵�������ID1
	private int ID2;               //ǰ�˵�������ID2
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////�û�����ʾ�����޸ĺ�Ĺ�ϵ���л�ȡ���޸ĵĹ�ϵ���ǰ���ԭ�ȵ�1�ź�2�ŵ�˳�򣬷�����¹�ϵ��������������
	private ArrayList<Relation> amendQue;                  //ǰ�˵�������amendQue
	
	public ArrayList<Relation> getAmendQue() {
		return amendQue;
	}
	public void setAmendQue(ArrayList<Relation> amendQue) {
		this.amendQue = amendQue;
	}
	////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	public int getID1() {
		return ID1;
	}
	public void setID1(int ID1) {
		this.ID1 = ID1;
	}
	
	public int getID2() {
		return ID2;
	}
	public void setID2(int ID2) {
		this.ID2 = ID2;
	}
	
	public void tryMerge() throws SQLException, IOException{
		
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter();
	    
		DbUtil con1=new DbUtil();
		DbUtil con2=new DbUtil();
		ResultSet rst1=null;
		ResultSet rst2=null;
		
		String ID1InfoSQL="select * from register_person where id="+ID1+";";
		rst1=con1.executeQuery(ID1InfoSQL);      //��ѯ�û�����Ϣ������ֻ���
		String ID1Phone = null;
		if(rst1.next()){
			ID1Phone=rst1.getString(5);
		}else {
			;
		}
		
		String ID2InfoSQL="select * from register_person where id="+ID2+";";
		rst2=con2.executeQuery(ID2InfoSQL);          //��ѯ�û�����Ϣ������ֻ���
		String ID2Phone = null;
		if(rst2.next()){
			ID2Phone=rst2.getString(5);
		}else {
			System.out.println("1��ʾID2�ı�����");
			out.println(1);                            //����������������������������������������������������������������������   1��ʾID2�ı�����
			out.flush();  
		    out.close();
		    return;
		}
		
		String ID1Table="a"+ID1+ID1Phone;
		String ID2Table="a"+ID2+ID2Phone;
		String ID1TableSQL="select * from "+ID1Table+";";
		String ID2TableSQL="select * from "+ID2Table+";";
		rst1 = con1.executeQuery(ID1TableSQL); 
		rst2 = con2.executeQuery(ID2TableSQL);
		RelationQue ID1Que = new RelationQue(rst1);
		RelationQue ID2Que = new RelationQue(rst2);
		
		if(Other.MixRelation(ID1Que, ID2Que)) {
			Other.cmpRltQue(ID1Que, ID2Que);
			if(ID1Que.rltMllQue.isEmpty()) {
				//��rltAddQue�����ݼ������ݿ���
				Other.addRltAddQue(ID1Que.rltAddQue, ID1Table);
				System.out.println("0��ʾ�ϲ��ɹ�");
				out.println(0);
				out.flush();  
			    out.close();
			    return;
			    //����Ϊ����search�������в�ѯ�ϲ���Ľ��������ˢ��ǰ�˵�Data
			    //����ǰ��,��̬ˢ�µķ�ʽ                              //��������������������������������������������������   0��ʾ�ϲ��ɹ�
			}else {
				//������Ķ��з��͵�ǰ�ˣ���JSONArray��ʽ
				out.println(Other.sendError(ID1Que.rltMllQue));
				out.flush();  
			    out.close();
			    return;
			}
		}else {
			System.out.println("2��ʾû�н���");
			out.println(2);                           //����������������������������������������������������������   2��ʾû�н���
			out.flush();  
		    out.close();
		    return;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////�û�����ʾ����޸ĳ�ͻ��ĺϲ������û����޸ı�������Ϊ׼
	public void amendMerge() throws SQLException, IOException{
		
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    
		DbUtil con1=new DbUtil();
		DbUtil con2=new DbUtil();
		ResultSet rst1=null;
		ResultSet rst2=null;
		
		String ID1InfoSQL="select * from register_person where id="+ID1+";";
		rst1=con1.executeQuery(ID1InfoSQL);      //��ѯ�û�����Ϣ������ֻ���
		String ID1Phone = null;
		if(rst1.next()){
			ID1Phone=rst1.getString(5);
		}else {
			;
		}
		
		String ID2InfoSQL="select * from register_person where id="+ID2+";";
		rst2=con2.executeQuery(ID2InfoSQL);          //��ѯ�û�����Ϣ������ֻ���
		String ID2Phone = null;
		if(rst2.next()){
			ID2Phone=rst2.getString(5);
		}else {
			;
		}
		
		String ID1Table="a"+ID1+ID1Phone;
		String ID2Table="a"+ID2+ID2Phone;
		String ID1TableSQL="select * from "+ID1Table+";";
		String ID2TableSQL="select * from "+ID2Table+";";
		rst1 = con1.executeQuery(ID1TableSQL); 
		rst2 = con2.executeQuery(ID2TableSQL);
		RelationQue ID1Que = new RelationQue(rst1);
		RelationQue ID2Que = new RelationQue(rst2);
		
		if(Other.MixRelation(ID1Que, ID2Que)) {
			Other.cmpRltQue(ID1Que, ID2Que);
			
			//��rltAddQue�����ݼ������ݿ���
			Other.addRltAddQue(ID1Que.rltAddQue, ID1Table);
			
			//���û��ύ���޸ĺ�Ĺ�ϵ���ID1�����ǿ�Ƹ���	
			for(Relation rltTmp:this.amendQue) {
				String updateSQL = "update "+ID1Table+" set relation="+rltTmp.relationType+",start_time='"+rltTmp.startTime+"',end_time='"+rltTmp.endTime
						+"' where user_id="+rltTmp.no1+" and relation_id="+rltTmp.no2+";";
				con1.executeUpdate(updateSQL);
			}
				
			out.println(0);
			out.flush();  
			out.close();
			    
			//����Ϊ����search�������в�ѯ�ϲ���Ľ��������ˢ��ǰ�˵�Data
			//����ǰ��,����search()�ķ�ʽ                              //��������������������������������������������������   0��ʾ�ϲ��ɹ�
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}



//��ϵ���еĹ�ϵԪ��
class Relation{
	public int no1;
	public int no2;
	public int relationType;
	public int startTime;
	public int endTime;
	
	public Relation(int no1, int no2, int relationType, int startTime, int endTime) {
		this.no1=no1;
		this.no2=no2;
		this.relationType=relationType;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	//�ж��Ƿ��ǽ�����ϵ
	public boolean mixRelation(Relation obj) {
		if(no1 == obj.no1 || no1 == obj.no2 || no2 == obj.no1 || no2 == obj.no2) {
			return true;
		}
		return false;
	}
	
	//�����������ϵ�ĳ�ͻ��-1.��������ϵָ�Ĳ���������ͬ����  0.�޳�ͻ   1.�����ͻ         2:��ϵ���ͳ�ͻ,��ϵʱ���ͻ���У�3��ֻ�й�ϵ���ͳ�ͻ����,4��ֻ�й�ϵʱ���ͻ         5.��������ϵ����û�й�ϵ
	public int getRelationClash(Relation obj) {
		int clash = -1;
		//�޷����ͻ
		if(no1 == obj.no1) {
			if(no2 == obj.no2) {
				if(relationType == obj.relationType) {
					if(startTime == obj.startTime && endTime==obj.endTime) {
						clash =  0;
					}else {
						clash = 4;
					}
				}else {
						if(startTime == obj.startTime && endTime==obj.endTime) {
						clash = 3;
					}else {
						clash = 2;
					}
				}
			}else {
				clash = -1;
			}
			return clash;
		}
		
		//�����Ƿ����ͻ
		if(no1 == obj.no2) {
			if(no2 == obj.no1) {
				if(relationType + obj.relationType == 4) {
					if(startTime == obj.startTime && endTime==obj.endTime) {
						clash =  1;
					}else {
						clash = 4;
					}
				}else {
						if(startTime == obj.startTime && endTime==obj.endTime) {
						clash = 3;
					}else {
						clash = 2;
					}
				}
			}else {
				clash = -1;
			}
			return clash;
		}
		
		return clash;
	}
}

//�����ϵ��ͻ���࣬���ڽ�����ͻ�Ĺ�ϵ����
class ClashRelation{
	public int no1;
	public int no2;
	public int RltTypeClash;          //��ϵ���ͳ�ͻ��1Ϊ���ڣ�0Ϊ������
	public int RltTimeClash;		//ʱ���ͻ��ͬ��
	
	public ClashRelation(int a,int b, int c , int d) {
		no1=a;
		no2=b;
		RltTypeClash=c;
		RltTimeClash=d;
	}
}

//��ϵ���У���Ӧ��һ����ϵ��
class RelationQue{
	//����ԭ���Ĺ�ϵ����
	public Queue<Relation> rltQue= new LinkedList<Relation>();
	//���������Ĺ�ϵ����
	public Queue<Relation> rltAddQue = new LinkedList<Relation>();
	//�����ͻ�Ĺ�ϵ����
	public Queue<ClashRelation> rltMllQue = new LinkedList<ClashRelation>();
	
	public RelationQue(ResultSet rst) throws SQLException {
		while(rst.next()) {
			Relation tmp = new Relation(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getInt(4), rst.getInt(5));
			rltQue.add(tmp);
		}
	}
}

//������������渨������
class Other {
	//�ж�������ϵ�����Ƿ��н���
	public static boolean MixRelation(RelationQue obj1, RelationQue obj2) {
		for(Relation rltTmp1 : obj1.rltQue) {
			for(Relation rltTmp2 : obj2.rltQue) {
				if(rltTmp1.mixRelation(rltTmp2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//�Ƚ���������ϵ���У���ô���ӹ�ϵ����rltQue����ì�ܹ�ϵ����rltMllQue
	//��ͻ��-1.��������ϵָ�Ĳ���������ͬ����  0.�޳�ͻ   1.�����ͻ         2:��ϵ���ͳ�ͻ,��ϵʱ���ͻ���У�3��ֻ�й�ϵ���ͳ�ͻ����,4��ֻ�й�ϵʱ���ͻ
	public static void cmpRltQue(RelationQue obj1, RelationQue obj2) {
		for(Relation rltTmp2 : obj2.rltQue) {
			int clash = -1;
			for(Relation rltTmp1 : obj1.rltQue) {
				clash = rltTmp2.getRelationClash(rltTmp1);
				if(clash == -1) {
					continue;
				}else if(clash == 2) {
					obj1.rltMllQue.add(new ClashRelation(rltTmp1.no1,rltTmp1.no2,1,1));
					break;
				}else if(clash == 3) {
					obj1.rltMllQue.add(new ClashRelation(rltTmp1.no1,rltTmp1.no2,1,0));
					break;
				}else if(clash == 4) {
					obj1.rltMllQue.add(new ClashRelation(rltTmp1.no1,rltTmp1.no2,0,1));
					break;
				}else {
					break;
				}
			}
			if(clash == -1) {
				obj1.rltAddQue.add(rltTmp2);
			}
		}
	}
	
	//����ϵ�����еĴ�������м������ݿ���
	public static void addRltAddQue(Queue<Relation> rltAddQue, String Table) {
		String AddSQL= null;
		DbUtil con=new DbUtil();
		for(Relation rltTmp : rltAddQue) {
			AddSQL="insert into "+Table+" values("+rltTmp.no1+","+rltTmp.no2+","+rltTmp.relationType+","+rltTmp.startTime+","+rltTmp.endTime+");";
			con.executeUpdate(AddSQL);
		}
	}
	
	//�����������JsonArray�ĸ�ʽ���͸�ǰ��
	public static JSONArray sendError(Queue<ClashRelation> rltMllQue) throws IOException {
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		
		for(ClashRelation rltTmp : rltMllQue) {
			jsonObject = new JSONObject();
			jsonObject.put("no1",rltTmp.no1);
			jsonObject.put("no2", rltTmp.no2);
			jsonObject.put("rltTypeClash",rltTmp.RltTypeClash);
			jsonObject.put("rltTimeClash",rltTmp.RltTimeClash);
			jsonArray.add(jsonObject);
		}
		System.out.println("----------------�������-------------------");
		System.out.println(jsonArray);
		System.out.println("------------------------------------------");
		
		return jsonArray;
	}
}


