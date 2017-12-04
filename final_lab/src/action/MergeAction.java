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
	private int ID1;               //前端的命名：ID1
	private int ID2;               //前端的命名：ID2
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////用户被提示错误修改后的关系队列获取，修改的关系还是按照原先的1号和2号的顺序，方便更新关系表。。。。。。。
	private ArrayList<Relation> amendQueFinal = new ArrayList<Relation>();                  
	private String amendQue;
	
	public String getAmendQue() {
		return amendQue;
	}
	public void setAmendQue(String amendQue) {
		this.amendQue=amendQue;
	}
	
	public void editAmendQueFinal(JSONArray jsonArray) {
		Relation rltTmp = null;
		for(int i = 0 ;i<jsonArray.size();i++) {
		    JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(i));
		    rltTmp = new Relation(jsonObject.getInt("no1"),jsonObject.getInt("no2"),jsonObject.getInt("type"),jsonObject.getString("start_time"),jsonObject.getString("end_time"));
		    amendQueFinal.add(rltTmp);
		}
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
		rst1=con1.executeQuery(ID1InfoSQL);      //查询用户的信息，获得手机号
		String ID1Phone = null;
		if(rst1.next()){
			ID1Phone=rst1.getString(5);
		}else {
			;
		}
		
		String ID2InfoSQL="select * from register_person where id="+ID2+";";
		rst2=con2.executeQuery(ID2InfoSQL);          //查询用户的信息，获得手机号
		String ID2Phone = null;
		if(rst2.next()){
			ID2Phone=rst2.getString(5);
		}else {
			System.out.println("1表示ID2的表不存在");
			out.println(1);                            //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！   1表示ID2的表不存在
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
				//将rltAddQue的数据加入数据库中
				Other.addRltAddQue(ID1Que.rltAddQue, ID1Table);
				System.out.println("0表示合并成功");
				out.println(0);
				out.flush();  
			    out.close();
			    return;
			    //以下为调用search函数进行查询合并后的结果，并且刷新前端的Data
			    //交给前端,动态刷新的方式                              //！！！！！！！！！！！！！！！！！！！！！！！！！   0表示合并成功
			}else {
				//将错误的队列发送到前端，以JSONArray格式

				out.println(Other.sendError(ID1Que.rltMllQue,ID1,ID2));
				out.flush();  
			    out.close();
			    return;
			}
		}else {
			System.out.println("2表示没有交集");
			out.println(2);                           //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！   2表示没有交集
			out.flush();  
		    out.close();
		    return;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////用户被提示后的修改冲突后的合并，以用户的修改表和自身表为准
	public void amendMerge() throws SQLException, IOException{
		//对于用户提交修改后的合并强制进行
		
		HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    
	    //Just print the amendQue .........................................................................
	    System.out.println("========================================");
	    System.out.println(amendQue);
	    
	    JSONObject jsonObject=JSONObject.fromObject(amendQue);
	    JSONArray jsonArray = (JSONArray) jsonObject.get("rows");
	    editAmendQueFinal(jsonArray);
	    
	    for(Relation rltTmp : amendQueFinal) {
	    	System.out.println(rltTmp.no1);
	    	System.out.println(rltTmp.no2);
	    	System.out.println(rltTmp.relationType);
	    	System.out.println(rltTmp.startTime);
	    	System.out.println(rltTmp.endTime);
	    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	    }
	    //................................................................
	    
		DbUtil con1=new DbUtil();
		DbUtil con2=new DbUtil();
		ResultSet rst1=null;
		ResultSet rst2=null;
		
		String ID1InfoSQL="select * from register_person where id="+ID1+";";
		rst1=con1.executeQuery(ID1InfoSQL);      //查询用户的信息，获得手机号
		String ID1Phone = null;
		if(rst1.next()){
			ID1Phone=rst1.getString(5);
		}else {
			;
		}
		
		String ID2InfoSQL="select * from register_person where id="+ID2+";";
		rst2=con2.executeQuery(ID2InfoSQL);          //查询用户的信息，获得手机号
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
			
			//删除冲突队列读应的关系
			Other.deleteMllQue(ID1Que.rltMllQue, ID1Table);
			
			//将rltAddQue的数据加入数据库中
			Other.addRltAddQue(ID1Que.rltAddQue, ID1Table);
			
			//将用户提交的修改后的关系表对ID1表进行加入	
			for(Relation rltTmp:this.amendQueFinal) {
				String addSQL = "insert into "+ID1Table+" values("+rltTmp.no1+","+rltTmp.no2+","+rltTmp.relationType+",'"+rltTmp.startTime+"','"+rltTmp.endTime+"');";
				con1.executeUpdate(addSQL);
			}
				
			out.println(0);
			out.flush();  
			out.close();
			    
			//以下为调用search函数进行查询合并后的结果，并且刷新前端的Data
			//交给前端,调用search()的方式                              //！！！！！！！！！！！！！！！！！！！！！！！！！   0表示合并成功
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}



//关系表中的关系元素
class Relation{
	public int no1;
	public int no2;
	public int relationType;
	public String startTime;
	public String endTime;
	
	public Relation(int no1, int no2, int relationType, String startTime, String endTime) {
		this.no1=no1;
		this.no2=no2;
		this.relationType=relationType;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	//判断是否是交集关系
	public boolean mixRelation(Relation obj) {
		if(no1 == obj.no1 || no1 == obj.no2 || no2 == obj.no1 || no2 == obj.no2) {
			return true;
		}
		return false;
	}
	
	//获得这两个关系的冲突：-1.这两个关系指的不是两个相同的人  0.无冲突   1.方向冲突         2:关系类型冲突,关系时间冲突都有，3：只有关系类型冲突存在,4：只有关系时间冲突         5.这两个关系对象没有关系
	public int getRelationClash(Relation obj) {
		int clash = -1;
		//无方向冲突
		if(no1 == obj.no1) {
			if(no2 == obj.no2) {
				if(relationType == obj.relationType) {
					if(startTime.equals(obj.startTime) && endTime.equals(obj.endTime)) {
						clash =  0;
					}else {
						clash = 4;
					}
				}else {
					if(startTime.equals(obj.startTime) && endTime.equals(obj.endTime)) {
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
		
		//可能是方向冲突
		if(no1 == obj.no2) {
			if(no2 == obj.no1) {
				if(relationType + obj.relationType == 4) {
					if(startTime.equals(obj.startTime) && endTime.equals(obj.endTime)) {
						clash =  1;
					}else {
						clash = 4;
					}
				}else {
					if(startTime.equals(obj.startTime) && endTime.equals(obj.endTime)) {
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

//保存关系冲突的类，用于建立冲突的关系队列
class ClashRelation{
	public int no1;
	public int no2;
	public int RltTypeClash;          //关系类型冲突，1为存在，0为不存在
	public int RltTimeClash;		//时间冲突，同上
	
	public ClashRelation(int a,int b, int c , int d) {
		no1=a;
		no2=b;
		RltTypeClash=c;
		RltTimeClash=d;
	}
}

//关系队列，对应于一个关系表
class RelationQue{
	//保存原本的关系队列
	public Queue<Relation> rltQue= new LinkedList<Relation>();
	//保存待加入的关系队列
	public Queue<Relation> rltAddQue = new LinkedList<Relation>();
	//保存冲突的关系队列
	public Queue<ClashRelation> rltMllQue = new LinkedList<ClashRelation>();
	
	public RelationQue(ResultSet rst) throws SQLException {
		while(rst.next()) {
			Relation tmp = new Relation(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getString(4), rst.getString(5));
			rltQue.add(tmp);
		}
	}
}

//这个类用来保存辅助操作
class Other {
	//判断两个关系队列是否有交集
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
	
	//比较这两个关系队列，获得待添加关系队列rltQue，和矛盾关系队列rltMllQue
	//冲突：-1.这两个关系指的不是两个相同的人  0.无冲突   1.方向冲突         2:关系类型冲突,关系时间冲突都有，3：只有关系类型冲突存在,4：只有关系时间冲突
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
	
	//将关系队列中的待加入队列加入数据库中
	public static void addRltAddQue(Queue<Relation> rltAddQue, String tableName) {
		String AddSQL= null;
		DbUtil con=new DbUtil();
		for(Relation rltTmp : rltAddQue) {
			AddSQL="insert into "+tableName+" values("+rltTmp.no1+","+rltTmp.no2+","+rltTmp.relationType+",'"+rltTmp.startTime+"','"+rltTmp.endTime+"');";
			System.out.println(AddSQL+"+++++++++++++++++++++++++++++++++++++++++++++");
			con.executeUpdate(AddSQL);
		}
	}
	
	//将错误队列以JsonArray的格式发送给前端
	public static JSONArray sendError(Queue<ClashRelation> rltMllQue,int ID1,int ID2) throws IOException, SQLException {
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		
		DbUtil con=new DbUtil();
		ResultSet rst=null;

		for(ClashRelation rltTmp : rltMllQue) {
			jsonObject = new JSONObject();
			/*查两人的相关信息*/
			//姓名：
			
			String sql="select * from register_person where id="+rltTmp.no1+";";
			rst=con.executeQuery(sql);
			if(rst.next()){
				jsonObject.put("user_1",rst.getString("name"));
			}else{
				sql="select * from no_register_person where id="+rltTmp.no1+";";
				rst=con.executeQuery(sql);
				if(rst.next()) {
					jsonObject.put("user_1",rst.getString("name"));
				}
			}
			
			sql="select * from register_person where id="+rltTmp.no2+";";
			rst=con.executeQuery(sql);
			if(rst.next()){
				jsonObject.put("user_2",rst.getString("name"));
			}else {
				sql="select * from no_register_person where id="+rltTmp.no2+";";
				rst=con.executeQuery(sql);
				if(rst.next()) {
					jsonObject.put("user_2",rst.getString("name"));
				}
			}
			
			jsonObject.put("no1",rltTmp.no1);
			jsonObject.put("no2", rltTmp.no2);

			sql="select * from register_person where id="+ID1+";";
			rst=con.executeQuery(sql);
			String phone="";
			if(rst.next()) {
			  phone=rst.getString(5);
			}
			String ID1Tablename="a"+ID1+phone;
			sql="select * from "+ID1Tablename+" where user_id="+rltTmp.no1+" and relation_id="+rltTmp.no2+";";
			rst=con.executeQuery(sql);
			while(rst.next()) {
				jsonObject.put("type",rst.getString(3));
				jsonObject.put("start_time",rst.getString(4));
				jsonObject.put("end_time",rst.getString(5));
			}

			// jsonObject.put("rltTypeClash",rltTmp.RltTypeClash);
			// jsonObject.put("rltTimeClash",rltTmp.RltTimeClash);//1表示冲突
			jsonArray.add(jsonObject);
		}
		System.out.println("----------------错误队列-------------------");
		System.out.println(jsonArray);
		System.out.println("------------------------------------------");
		
		return jsonArray;
	}
	
	//用户提交修改后的关系表后，进行原数据表中冲突的关系进行删除
	public static void deleteMllQue(Queue<ClashRelation> MllQue,String tableName) throws SQLException{
		DbUtil con=new DbUtil();
		String deleteSQL = null;
		for(ClashRelation cRltTmp : MllQue) {
			deleteSQL="delete from "+tableName+" where user_id="+cRltTmp.no1+" and relation_id="+cRltTmp.no2+";";
			con.executeUpdate(deleteSQL);
		}
	}
	
}