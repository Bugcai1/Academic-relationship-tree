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
    
    private String relationType;
    private String endTime;
    private String beginTime;
    private int Id;
    private int flaag;
    public int getFlaag()
    {
    	return flaag;
    }
    public void setFlaag(int flaag)
    {
    	System.out.println("��ʼ����");
    	this.flaag=flaag;
    }
//    DbUtil connect = new DbUtil();
    public int getId()
    {
        return Id;
    }
    public void setId(int Id)
    {
        this.Id = Id;
    }
    public String getRelationType() {
        return relationType;
    }
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
 /*
  * ���ò�ͬ�ķ�ʽ����淵��json���ֵ��Ҫ�����ն�Ӧ��js���շ�ʽ
  */
 
 /*
  * ����id����ĳ���˵Ĺ�ϵ��
  * �����߼������ȸ���id��register_person���в����ֻ��ţ�ȷ�����˵Ĺ�ϵ�����ڵĵط�
  * ��ѯ��ϵ���е�����id��Ȼ��register_person��no_register_person���н�����ϸ���ݵĲ�ѯ
  */
//    public static void main(String[] args) throws SQLException
//    {
//        search_id();
//    }
    
public int workoutString(String s)
    {
        int result ;
        String all = "";
        String temp[] = new String[3];
        temp = s.split("/");
        System.out.println("s="+s+"   "+temp[0]);
        all = temp[0] +temp[1] +temp[2];
        result =Integer.parseInt(all); 
        return result ;
    }

     public boolean isrelation(int data_start,int data_end)
    {
//    	 System.out.println("ispeople"+getEndTime());
    	 System.out.println(data_start+" time  "+data_end);
        if(workoutString(getEndTime())==99999999&&workoutString(getBeginTime())==0)
        {
        	System.out.println(workoutString(getEndTime())+"  1  "+workoutString(getBeginTime()));
            return true;
        }
        if(data_start>=workoutString(getBeginTime()) &&data_end<=workoutString(getEndTime()))
        {
        	System.out.println(workoutString(getEndTime())+"  2  "+workoutString(getBeginTime()));
            return true;
        }
        System.out.println(workoutString(getEndTime())+"  3  "+workoutString(getBeginTime()));
        return false;
    }
 public String sureSql()
 {
    String relationType=getRelationType();
     String sql = " and (relation=" ;
//     System.out.println("relation"+relationType);
        switch (relationType)
        {
            case "111":
                sql = sql + "1 or relation = 2 or relation = 3) ;";
                break;
            case "110":
                sql = sql + "1 or relation = 3);" ; 
                break;
            case "100":
                sql = sql + "1) ;";
                break;
            case "010":
                sql = sql + "2) ;";
                break;    
            case "001":
                sql = sql + "3) ;";
                break;
            case "101":
                sql = sql + "1 or relation= 3); ";
                break;
            case "011":
                sql = sql + "2 or relation = 3) ;";
            default:
                sql = null;
                break;
        }
    return sql;
 }
 public void search() throws SQLException, IOException
 {
	 System.out.println("okokokookokokokokokokookoko"+getFlaag());
     JSONArray json=new JSONArray();
     JSONObject list=new JSONObject();
     DbUtil con=new DbUtil();
     DbUtil conn=new DbUtil();
     ResultSet rs=null;
     ResultSet rpar=null;
     String search_p="select * from register_person where id="+getId()+";";
     rs=con.executeQuery(search_p);//��ѯ�ֻ���
     String phone="";
     if(getFlaag()==1)
     {
     	 System.out.println("------------------��������");
     	 while(rs.next())
         {
     		 list.put("parent_id",rs.getString(1));
             list.put("parent_name", rs.getString(2));
             list.put("parent_sex",rs.getString(3));
             list.put("parent_work",rs.getString(4));
             list.put("parent_phone",rs.getString(5));
             json.add(list);
         }
     	 HttpServletResponse response=ServletActionContext.getResponse(); 
         response.setContentType("text/html;charset=utf-8");  
         PrintWriter out = response.getWriter();
     	 out.println(json);
         out.flush();  
         out.close();
     	 return;
     }
     while(rs.next())
     {
         phone=rs.getString(5);
     }
//        json.add(list);
     /*
      * ���Ϸ��ص�ʱ��ѯ�ߵ������Ϣ
      */
//        System.out.println("haode "+list);
     
      //����ֻ��ţ���ѯ�����Ҫ��֤�ǲ�����ȷ��
     String table_name="a"+getId()+phone;//ȷ��������Ϣ������֣�
//        System.out.println("tablename  "+table_name);
     /*
      * �������Ĳ�α�����˼�룬�����ݱ��е���Ϣ��һ��һ���Ĳ������
      */
//     System.out.println("table"+table_name);
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
    	 System.out.println("YYYYYYY");
         int id=Q[front++];
         String search_relation="select * from "+table_name+" where user_id="+id+sureSql();
         System.out.println("search"+search_relation);
         rs=con.executeQuery(search_relation);//��ʱrs���������ݺ���Ҫ��һ������Ҫ���ص����ݣ�һ����Ҫȡ��Ѱ�ң�
         int count=conn.executecount(table_name, id,sureSql());         
         int length=120;
         int k=0;
         pk++;
         
         while(rs.next())
         {
        	 System.out.println("lllllllllll");
//        	 System.out.println("search");
             if(isrelation(workoutString(rs.getString(4)),workoutString(rs.getString(5)))!=true)
                continue;
//                System.out.println("id="+rs.getString(1) + " "+"kk=   "+kk);
             k++;
             Q[end++]=rs.getInt(2);
             System.out.println("end"+rs.getInt(2));
             int par=rs.getInt(1);
             int chi=rs.getInt(2);
             int relation=rs.getInt(3);
             String start=rs.getString(4);
             String end_t=rs.getString(5);
             

            System.out.println("��Ϣ��"+par+"  "+chi+"  "+relation+" "+start+" "+end_t);
             
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
             
             
//             System.out.println(k+"  ����  "+(Math.sin(k*2.0/count*Math.PI))+"   "+dex[pk]);
//             System.out.println((Math.cos(k*2.0/count*Math.PI))+"   "+dey[pk]);
             x=(int)(Math.sin(k*2.0/count*Math.PI)*length+dex[pk]);
             y=(int)(Math.cos(k*2.0/count*Math.PI)*length+dey[pk]);
             dex[++kk]=x;
             dey[kk]=y;
//             System.out.println("x="+x+"  y="+y);
             
             list.put("x",x);
             list.put("y", y);
             json.add(list);
         }   
     }
//        json.add(list);
     System.out.println("json"+json);
    
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
}
