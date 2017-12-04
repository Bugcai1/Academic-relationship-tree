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
    	System.out.println("初始化！");
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
  * 采用不同的方式向界面返回json结果值，要求掌握对应的js接收方式
  */
 
 /*
  * 按照id查找某个人的关系树
  * 是新逻辑，首先根据id在register_person表中查找手机号，确定个人的关系表所在的地方
  * 查询关系表中的数据id，然后到register_person，no_register_person表中进行详细数据的查询
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
     rs=con.executeQuery(search_p);//查询手机号
     String phone="";
     if(getFlaag()==1)
     {
     	 System.out.println("------------------！！！！");
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
      * 以上返回的时查询者的相关信息
      */
//        System.out.println("haode "+list);
     
      //获得手机号（查询结果需要验证是不是正确）
     String table_name="a"+getId()+phone;//确定个人信息表的名字；
//        System.out.println("tablename  "+table_name);
     /*
      * 按照树的层次遍历的思想，将数据表中的信息，一个一个的查出来。
      */
//     System.out.println("table"+table_name);
     int Q[]=new int[1000];
     int front=0;
     int end=0;
     int kk=0;
     int pk=-1;
     int dex[]=new int[100];
     int dey[]=new int[100];
     Q[end++]=getId();/*首先将自己压入*/
     while(front!=end)
     {
    	 System.out.println("YYYYYYY");
         int id=Q[front++];
         String search_relation="select * from "+table_name+" where user_id="+id+sureSql();
         System.out.println("search"+search_relation);
         rs=con.executeQuery(search_relation);//此时rs包含的内容很重要：一部分是要返回的数据，一部分要取在寻找；
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
             

            System.out.println("信息："+par+"  "+chi+"  "+relation+" "+start+" "+end_t);
             
             String search_information="select * from register_person where id="+par+";";
             rpar=conn.executeQuery(search_information);
             int flag=0;
             if(!rpar.next())//假设不存在的时候是null
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
             if(!rpar.next())//假设不存在的时候是null
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
             
             
//             System.out.println(k+"  坐标  "+(Math.sin(k*2.0/count*Math.PI))+"   "+dex[pk]);
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
      * 寻找每个人的相关信息，然后以json的格式进行返回
      */
 }
}
