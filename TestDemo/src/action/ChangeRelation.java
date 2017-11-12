package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import SqlCon.DbUtil;

public class ChangeRelation {
    private int TableID ;
    private String ID1;
    private String ID2;
    private String preRelation;
    private String nowRelation;
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
    public String getPreRelation() {
        return preRelation;
    }
    public void setPreRelation(String preRelation) {
        this.preRelation = preRelation;
    }
    public String getNowRelation() {
        return nowRelation;
    }
    public void setNowRelation(String nowRelation) {
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
    public int setRelationNumber(String Relation)
    {
        int relationNumber;
        if(Relation.equals("老师"))
        {
            relationNumber = 1;
        }
        else if (Relation.equals("师兄弟"))
        {
            relationNumber = 2;
        }
        else
        {
            relationNumber =3;
        }
        return relationNumber;
    }
    
    public String changeAction() throws SQLException
    {
    	DbUtil connects = new DbUtil();
    	int pre;
        int now;
        pre = setRelationNumber(preRelation);
        now = setRelationNumber(nowRelation);
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
        
        String deletesql = "Delete from "+ table_name + "where user_id =" + ID1 + ",relation_id = "+ ID2 + ",Relation = " + pre+";";
        String addsql = "insert into author (user_id, relation_id,Relation, start_time, end_time) values("+ 
        				ID1 +
        				", "+ ID2 +
        				", "+ now +
        				", \""+startTime+
        				"\",\""+endTime+
        				"\";)";
        result = connects.executeUpdate(deletesql);
        if (result ==-1) return "Failed";
        result = connects.executeUpdate(addsql);
        if (result ==-1 ) return "Failed";
        return "SUCCESS";
    }
}
