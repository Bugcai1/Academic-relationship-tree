package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import SqlCon.DbUtil;

class ClearUpTable {
	public static void clear(String tableName, int id) throws SQLException {
		DbUtil con=new DbUtil();
		String querySQL="select * from " + tableName + ";";
		ResultSet rst=null;
		rst = con.executeQuery(querySQL);
		
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		Edge edgeTmp = null;
		HashMap<String, Integer> IDToIndex = new HashMap<String, Integer>();
		String no1Tmp;
		String no2Tmp;
		int tail = 0;
		String ID = new String(id+"");
		while(rst.next()) {
			no1Tmp=rst.getString(1);
			no2Tmp=rst.getString(2);
			if(!IDToIndex.containsKey(no1Tmp)) {
				IDToIndex.put(no1Tmp, new Integer(tail));
				tail++;
			}
			if(!IDToIndex.containsKey(no2Tmp)) {
				IDToIndex.put(no2Tmp, new Integer(tail));
				tail++;
			}
			edgeTmp = new Edge(IDToIndex.get(no1Tmp),IDToIndex.get(no2Tmp));
			edgeList.add(edgeTmp);
		}
		if(!IDToIndex.containsKey(ID)) {
			IDToIndex.put(ID, new Integer(tail));
			tail++;
		}
		int IDindex = IDToIndex.get(ID);
		int[] parArray = new int[tail];
		for(int i = 0; i<tail; i++) {
			parArray[i] = i;
		}
		
		for(Edge edge : edgeList) {
			union(parArray, edge.index1, edge.index2, IDindex);
		}
		Set<String> IDSet = IDToIndex.keySet();
		Set<String> IDDelSet = new HashSet<String>();
		for(String IDStr : IDSet) {
			if(!(find(parArray,IDToIndex.get(IDStr).intValue())==IDindex)) {
				IDDelSet.add(IDStr);
			}
		}
		DelRelation(tableName, IDDelSet);
	}
	
	public static int find(int[] arrayName, int index) {
		int curIndex=index;
		while(curIndex != arrayName[curIndex]) {
			curIndex = arrayName[curIndex];
		}
		return curIndex;
	}
    
    public static void union(int[] arrayName, int index1, int index2,  int IDindex) {
    	int parIndex1 = find(arrayName, index1);
    	int parIndex2 = find(arrayName, index2);
    	if(parIndex1 != parIndex2) {
    		if(parIndex1 == IDindex) {
    			arrayName[parIndex2] = parIndex1; 
    		}else {
    			arrayName[parIndex1] = parIndex2; 
    		}
    	}
    }
    
    public static void DelRelation(String tableName, Set<String> IDDelSet) {
    	DbUtil con=new DbUtil();
    	for(String ID : IDDelSet) {
			String deleteSQL="delete from "+tableName+" where user_id="+ID+" or relation_id="+ID+";";
			con.executeUpdate(deleteSQL);
    	}
    }
	
}

class Edge {
	public int index1;
	public int index2;
	
	public Edge(int index1,int index2) {
		this.index1=index1;
		this.index2=index2;
	}
}