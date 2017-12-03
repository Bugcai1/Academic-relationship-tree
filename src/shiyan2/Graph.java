package shiyan2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Graph {
	int G[][];
	int num;
	int visited[][];
	Map<String,Integer>s_to_int=new HashMap<String,Integer>();
	Map<String,String>int_to_s =new HashMap<String,String>();
	public Graph()
	{
		
	}
	public Graph(String Filename) throws IOException
	{
		File inputFile=new File(Filename);
		FileReader in=new FileReader(inputFile);
		int c,i=0,j=0;
		String []ch =new String[1000];
		while((c=in.read())!=-1)
		{
			if((c>64&&c<91)||(c>96&&c<123))
			{
				if(c>64&&c<91)
					c=c+32;
				if(ch[j]==null)
					ch[j]=""+(char)c;
				else
					ch[j]+=((char)c);
				i++;
			}
			else 
			{
				if(i!=0)
				{
					i=0;
					j++;
				}
				else
					continue;
			}
		}
		if(ch[j]==null)
			j--;
		int numl=j+1;
		in.close();
		j=0;
		for(i=0;i<numl;i++)
		{
			if(!s_to_int.containsKey(ch[i]))	//大小写功能的设定：
			{
				s_to_int.put(ch[i],j);
				int_to_s.put(""+j, ch[i]);
				j++;
			}
		}
		num=j;
		G=new int[num][num];
		visited=new int[num][num];
		for(i=0;i<num;i++)
		{
			for(j=0;j<num;j++)
			{
				visited[i][j]=0;
				G[i][j]=0;
			}
		}
		for(i=0;i<numl-1;i++)
		{
			G[s_to_int.get(ch[i])][s_to_int.get(ch[i+1])]++;
		}
	}
}
