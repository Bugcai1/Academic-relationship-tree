package shiyan2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JLabel;



public class main_1 {
	public static void main(String[] args)
	{
		menu1 m=new menu1();
	}
	static int arr[][];
	public static Graph createDirectedGraph(String filename) throws IOException
	{
		Graph T=null;
		T=new Graph(filename);
		return T;
	}
	public static String queryBridgeWords(Graph T, String word1, String word2)
	{
		String result="";
		if(T.s_to_int.containsKey(word1)&&T.s_to_int.containsKey(word2))
		{
			for(int i=0;i<T.num;i++)
			{
				if(T.G[T.s_to_int.get(word1)][i]!=0&&T.G[i][T.s_to_int.get(word2)]!=0)
				{
					result=result+" "+T.int_to_s.get(""+i);
				}
			}
			if(result=="")
				result="No bridge words from word1 to word2";
			return result;
		}
		result="No word1 or word2 in the graph!";
		return result;
	}
	
	public static String generateNewText(Graph T, String inputText) {
		String[] in1=inputText.split(" ");//当输入很规范时使用此方法
		String result=in1[0];
		String[]bridge;
		String B="No bridge words from word1 to word2";
		String A="No word1 or word2 in the graph!";
		for(int i=0;i<in1.length-1;i++)
		{
			String C=queryBridgeWords(T,in1[i],in1[i+1]);
			if(C.equals(B)||C.equals(A))
			{
				
			}
			else
			{
				bridge=queryBridgeWords(T,in1[i],in1[i+1]).split(" ");
				result=result+" "+bridge[((int)(Math.random()*10))%(bridge.length-1)+1];
			}
			result=result+" "+in1[i+1];
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static String randgo(Graph T,int n,int from)
	{
		int i,k=0;
		java.util.Random r=new java.util.Random();
		int []path=new int[n];
		String result=new String();
		for(i=0;i<T.num;i++)
		{
			 if(T.G[from][i]!=0)//&&T.visited[from][i]==0
			 {
				 path[k++]=i;
			 }
		}
		if(k==0)
		{
			return null;
		}
		int to=(r.nextInt(k));
		if(T.visited[from][path[to]]==1)
		{
			for(int j=0;j<n;j++)
				for(int x=0;x<n;x++)
					T.visited[j][x]=1;
			return T.int_to_s.get(""+path[to])+" no more";
		}
		T.visited[from][path[to]]=1;
		result=T.int_to_s.get(""+path[to]);
		return result;//+randgo(T,n,path[to]);	
	}
	public static String randomWalk(Graph T,String start) {
		int from;
		String result=new String();
		from=T.s_to_int.get(start);
		result=randgo(T,T.num,from);
		return T.int_to_s.get(""+from)+" "+result;
	}
	
	public static void showDirectedGraph(Graph T)
	{
		String type = "gif";
		GraphViz gv = new GraphViz();
	    gv.addln(gv.start_graph());
	    for(int i=0;i<T.num;i++)
	    {
	    	for(int j=0;j<T.num;j++)
	    	{
	    		if(T.G[i][j]!=0)
	    		{
	    			gv.addlnlabel(T.int_to_s.get(""+i)+"->"+T.int_to_s.get(""+j),""+T.G[i][j]);
	    		}
	    	}
	    }
	    gv.addln(gv.end_graph());
	    File out = new File("E:\\javaapp." + type);    // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
	
	
	public static String calcShortestPath(Graph T, String word1, String word2)
	{
		int max=9999;
		int start=T.s_to_int.get(word1);
		int end=T.s_to_int.get(word2);
		int n=T.num;
		int [][]G=new int[n][n];
		for(int i=0;i<n;i++)		//图的初始化
			for(int j=0;j<n;j++)
			{
				G[i][j]=T.G[i][j];
				if(G[i][j]==0)
					G[i][j]=max;
				
			}
		Stack p[]=find_short_paths(T,G,n,start,end);
		int [][]path=short_path(T,p,start,end);
		arr=showpath(p,path,n,start,end);
		
		String result="";
		Stack stack=new Stack();
		for(int i=0;i<arr[0].length;i++)
		{
			if(arr[0][i]==-1)
				break;
			stack.push(T.int_to_s.get(""+arr[0][i]));
		}
		while(!stack.isEmpty())
		{
			result=result+" "+stack.pop();
		}
		return result;
	}
	public static int[] show(Stack stack,int n)
	{
		int arr[]=new int[n];
		for(int x=0;x<n;x++)
			arr[x]=-1;
		int i=0;
		//x++;
		while(!stack.isEmpty())
		{
			//arr[x][i]=(int) stack.pop();
			arr[i]=(int) stack.pop();
			i++;
		}
		//System.out.println();
		for(int j=i-1;j>=0;j--)
		{
			stack.push(arr[j]);
		}
		return arr;
	}
	public static int[][] showpath(Stack p[],int G[][],int n,int start,int end)
	{
		int path[][]=new int[n][n];//这个需要再次考率
		int d=0;
		int v[]=new int[n];
		int A[][]=new int[n][n];
		for(int i=0;i<n;i++)
		{
			v[i]=0;
			for(int j=0;j<n;j++)
			{
				A[i][j]=0;
				path[i][j]=-1;
			}
		}
		Stack mystack=new Stack();
		mystack.push(start);
		v[0]=1;
		int ele;
		while(!mystack.isEmpty())
		{
			ele=(int) mystack.peek();
			if(ele==end)
			{
//				int arr[]=show(mystack,n);
				path[d++]=show(mystack,n);
				
				v[ele]=0;
				updataA(G,A,mystack,n);
				mystack.pop();
			}
			else
			{
				int i;
				for(i=0;i<n;i++)
				{
					if(v[i]==0&&A[ele][i]==0&&G[ele][i]==1)
					{
						v[i]=1;
						A[ele][i]=1;
						mystack.push(i);
						break;
					}
				}
				if(i==n)
				{
					v[ele]=0;
					updataA(G,A,mystack,n);
					mystack.pop();
				}
			}
		}
		return path;
	}
	public static void updataA(int G[][],int A[][],Stack stack,int n)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				//if(G[i][j]!=0)
				{
					//A[i][j]=1;
					if((stack.search(i)==-1&&stack.search(j)==-1))
					{
						A[i][j]=0;
					}
				}
			}
		}
	}
	public static int[][] short_path(Graph T,Stack p[],int start,int end)
	{
		int n=p.length;
		int q[]=new int [1000];
		int G[][]=new int[n][n];
		for(int i=0;i<n;i++)		//图的初始化
			for(int j=0;j<n;j++)
			{
				G[i][j]=0;
			}
		int front=0,rear=0;
		q[rear++]=end;
		while(rear!=front)
		{
			int s=q[front++];
			while(!p[s].isEmpty())
			{
				int x=(int) p[s].pop();
				G[x][s]=1;
				if(x!=start)
					q[rear++]=x;
			}
		}
		String type = "gif";
		GraphViz gv = new GraphViz();
	    gv.addln(gv.start_graph());
		for(int i=0;i<n;i++)
		{
			//System.out.print(T.int_to_s.get(""+i)+" ");
			for(int j=0;j<n;j++)
			{
				//System.out.print(G[i][j]+" ");
				if(G[i][j]!=0)
				{
					gv.addlnlabel(T.int_to_s.get(""+i)+"->"+T.int_to_s.get(""+j),""+T.G[i][j]);
				}
			}
			//System.out.println();
		}
		gv.addln(gv.end_graph());
	    File out = new File("E:\\javaapp1." + type);    // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	    return G;
	}
	public static Stack[] find_short_paths(Graph T,int G[][],int n,int from,int end)
	{
		Stack stack=new Stack();
		Stack dex=new Stack();
		Stack[][]p=new Stack[n][n];
		int i,j,k;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				p[i][j]=new Stack();
				p[i][j].push(i);
			}
		}
		for(k=0;k<n;k++)
		{
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
				{
					if(G[i][j]>G[i][k]+G[k][j])
					{
						G[i][j]=G[i][k]+G[k][j];
						while(!p[i][j].isEmpty())
						{
							p[i][j].pop();
						}
						if(T.G[k][j]!=0)
							p[i][j].push(k);
				
					}
					else if(G[i][j]==G[i][k]+G[k][j])
					{
						if(T.G[k][j]!=0)
							p[i][j].push(k);
					}
				}
			}
//			System.out.println(k);
//			for(i=0;i<n;i++)
//			{
//				for(j=0;j<n;j++)
//				{
//					System.out.print(G[i][j]+" ");
//				}
//				System.out.println();
//			}
		}
		return p[from];
	}
	
	
	public static Stack[] all_calcShortestPath(Graph T,String word)
	{
		int maxint=1000;
		int A[][]=new int[T.num][T.num];
		for(int i=0;i<T.num;i++)
		{
			for(int j=0;j<T.num;j++)
			{
				if(T.G[i][j]==0)
					A[i][j]=maxint;
				else
					A[i][j]=T.G[i][j];
			}
		}
		int v0=T.s_to_int.get(word);
		int dist[]=new int[T.num];
		int prev[]=new int[T.num];
		boolean s[]=new boolean[T.num];
		for(int i=0;i<T.num;i++)
		{
			dist[i]=A[v0][i];
			s[i]=false;
			if(dist[i]==maxint)
				prev[i]=-1;
			else
				prev[i]=v0;
		}
		dist[v0]=0;
		s[v0]=true;
		for(int i=1;i<T.num;i++)
		{
			int min=maxint;
			int u=v0;
			for(int j=0;j<T.num;j++)
			{
				if(!s[j]&&dist[j]<min)
				{
					u=j;
					min=dist[j];
				}
			}
			s[u]=true;
			for(int j=0;j<T.num;j++)
			{
				if(!s[j]&&dist[u]+A[u][j]<dist[j])
				{
					{
						dist[j]=dist[u]+A[u][j];
						prev[j]=u;
					}
				}
			}
		}
		//输出部分
		Stack stack=new Stack();
		Stack pp[]=new Stack[T.num];
		int i=0;
		for(int j=0;j<T.num;j++)
		{
			pp[i]=new Stack();
			if(j!=v0)
			{
				int k=j;
				while(k!=v0)
				{
					pp[i].push(T.int_to_s.get(""+k));
					k=prev[k];
				}
			}
			i++;
		}
		return pp;
	}
}