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

public class Deleterelation {
    
    private String TableID;
    private String ID1;
    private String ID2;
    private String Relation;
    int RelationNumber;
    DbUtil connects = new DbUtil();
    
    public String getTableID() {
        return TableID;
    }
    public void setTableID(String tableID) {
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
    public String getRelation() {
        return Relation;
    }
    public void setRelation(String relation) {
        Relation = relation;
    }
    
    public void setRelationNumber()
    {
        if(Relation.equals("¿œ ¶"))
        {
            relationNumber = 1;
        }
        else if (Relation.equals(" ¶–÷µ‹"))
        {
            relationNumber = 2;
        }
        else
        {
            relationNumber =3;
        }
    }
    
    public String SetTableID()
    {
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
        String deletesql = "Delete from "+ table_name + "where user_id =" + ID1 + ",relation_id = "+ ID2 + ",Relation = " + Relation()+";";
        result = connects.executeUpdata(deletesql);
        if(result = -1)
            return "Failed";
        return "SUCCESS";
    }
    
}
