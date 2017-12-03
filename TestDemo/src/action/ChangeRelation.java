package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import SqlCon.DbUtil;

public class ChangeRelation {
    private int TableID ;
    private String ID1;
    private String ID2;
//    private String preRelation;
    private int nowRelation;
    private String startTime;
    private String endTime;
    
    public int getTableID() {
        return TableID;
    }
    public void setTableID(int tableID) {
        TableID = tableID;
    }
    public String getID1() {
        return ID1;
    }
    public void setID1(String ID1) {
        this.ID1 = ID1;
    }
    public String getID2() {
        return ID2;
    }
    public void setID2(String ID2) {
        this.ID2 = ID2;
    }
//    public String getPreRelation() {
//        return preRelation;
//    }
//    public void setPreRelation(int preRelation) {
//        this.preRelation = preRelation;
//    }
    public int getNowRelation() {
        return nowRelation;
    }
    public void setNowRelation(int nowRelation) {
        this.nowRelation = nowRelation;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    
    //将输入的关系转化成对应的数字
    
    public String deleteAction() throws SQLException
    {
    	DbUtil connects = new DbUtil();
    	ResultSet phoneset = null;
        String phone = "";
        String search_phone="select phone from register_person where id="+TableID+";";
        phoneset = connects.executeQuery(search_phone);
        while(phoneset.next())
        {
            phone = phoneset.getString(1);
        }
        String table_name="a"+getTableID()+phone;
        
        String sql="delete from "+table_name+" where user_id="+getID1()+" and relation_id="+getID2()+";";
        
        System.out.println("删除的语句："+sql);
        connects.executeUpdate(sql);
        
        
        
    	return "SUCCESS";
    }
    public String changeAction() throws SQLException
    {
    	System.out.println("--------------------------------------------------");
    	DbUtil connects = new DbUtil();
    	int pre;
        int now;
//        pre = setRelationNumber(preRelation);
//        now = setRelationNumber(nowRelation);
        now=getNowRelation();
        int result;
        ResultSet phoneset = null;
        String phone = "";
        String search_phone="select phone from register_person where id="+TableID+";";
        phoneset = connects.executeQuery(search_phone);
        while(phoneset.next())
        {
            phone = phoneset.getString(1);
        }
        String table_name="a"+getTableID()+phone;
        
        String sql="update "+table_name+" set relation="+now+",start_time='"+getStartTime()+"',end_time='"+getEndTime()+"' where user_id="+getID1()+" and relation_id="+getID2()+";";
        System.out.println("更新的语句：   "+sql);
        connects.executeUpdate(sql);
        return "SUCCESS";
    }
}
