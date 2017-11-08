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

public class ChangeRelation {
    private int TableID ;
    private String ID1;
    private String ID2;
    private String PreRelation;
    private String NowRelation;
    int pre;
    int now;
    SqlCon connects = new SqlCon();
    
    public int getTableID() {
        return TableID;
    }
    public void setTableID(int tableID) {
        TableID = tableID;
    }
    public String getID1() {
        return ID1;
    }
    public void setID1(String iD1) {
        ID1 = iD1;
    }
    public String getID2() {
        return ID2;
    }
    public void setID2(String iD2) {
        ID2 = iD2;
    }
    public String getPreRelation() {
        return PreRelation;
    }
    public void setPreRelation(String preRelation) {
        PreRelation = preRelation;
    }
    public String getNowRelation() {
        return NowRelation;
    }
    public void setNowRelation(String nowRelation) {
        NowRelation = nowRelation;
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
    
    public void ChangeRelation()
    {
        pre = setRelationNumber(PreRelation);
        now = setRelationNumber(NowRelation);
        int result;
        ResultSet phoneset = null;
        String phone = "";
        String search_phone="select phone from register_person where id="+TableID+";";
        setRelationNumber();
        phoneset = connects.executeQuery(search_phone);
        while(phoneset.next())
        {
            phone = phoneset.getString(1);
        }
        String table_name="a"+getTableID()+phone;
        
        String deletesql = "Delete from "+ table_name + "where user_id =" + ID1 + ",relation_id = "+ ID2 + ",Relation = " + PreRelation()+";";
        String addsql = "insert into author (user_id,relation_id,Relation) values('"+ID1+"','"+ID2+"','"+NowRelation+");";
        connects.executeUpdata(deletesql);
        connects.executeUpdata(addsql);
        return "SUCCESS";
    }
}
