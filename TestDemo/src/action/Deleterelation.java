package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import SqlCon.DbUtil;

public class Deleterelation {
    private String TableID;
    private String ID1;
    private String ID2;
    private String Relation;
    
    int relationNumber;
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
    public void setID1(String ID1) {
        this.ID1 = ID1;
    }
    public String getID2() {
        return ID2;
    }
    public void setID2(String ID2) {
        this.ID2 = ID2;
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
            relationNumber = 3;
        }
    }
    
    public String deleteAction() throws SQLException
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
        String deletesql = "Delete from "+ table_name + "where user_id =" + ID1 + ",relation_id = "+ ID2 + ",Relation = " + relationNumber+";";
        result = connects.executeUpdate(deletesql);
        if(result == -1)
            return "Failed";
        return "SUCCESS";
    }
}
