package shiyan2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class menu1 implements ActionListener {

	JButton jb1=new JButton("上传文件");
	JButton jb2=new JButton("显示图片");
	
	JButton sure=new JButton("sure");
	JButton exit=new JButton("exit");
	JButton next=new JButton("next");
	
	JButton sure1=new JButton("sure");
	JButton sure2=new JButton("sure");
	JButton sure3=new JButton("sure");
	JButton sure4=new JButton("sure");
	
	JButton gn1=new JButton("桥接词功能");
	JButton gn2=new JButton("生成文本功能");
	JButton gn3=new JButton("两点最短距离");
	JButton gn4=new JButton("点到所有点距离");
	JButton gn5=new JButton("随机游走功能");

	JTextField from=new JTextField();
	JTextField to =new JTextField();
	JTextArea bridge_word=new JTextArea();
	
	JTextArea User_input=new JTextArea();
	JTextArea word_output=new JTextArea();
	
	JTextArea ran_go=new JTextArea();
	JTextField start=new JTextField();
	
	JTextField start1=new JTextField();//点到其他顶点
	JTextArea short2=new JTextArea();
	
	JFrame myframe=new JFrame();
	JPanel mypanel=new JPanel();
	
	JTextField start2=new JTextField();
	JTextField end=new JTextField();
	JTextArea short1=new JTextArea();
	JTextArea short3=new JTextArea();
	
	JPanel pn1=new JPanel();
	Graph T=new Graph();
	
	int height=768-80;
	int width=1366;
	int x;
	public menu1()
	{
		
		myframe.setSize(1366,768);
		//myframe.add(pn1);
		pn1.setVisible(false);
		myframe.setVisible(true);
		myframe.add(mypanel);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mypanel.setLayout(null);
		
		mypanel.add(jb1);
		jb1.addActionListener(this);
		jb1.setBounds(1366/2-100,height/8,200,50);
		
		jb2.addActionListener(this);
		mypanel.add(jb2);
		jb2.setBounds(1366/2-100,height*2/8,200,50);
		
		gn1.addActionListener(this);
		mypanel.add(gn1);
		gn1.setBounds(1366/2-100,height*3/8,200,50);
		
		gn2.addActionListener(this);
		mypanel.add(gn2);
		gn2.setBounds(1366/2-100,height*4/8,200,50);
		
		gn3.addActionListener(this);
		mypanel.add(gn3);
		gn3.setBounds(1366/2-100,height*5/8,200,50);
		
		gn4.addActionListener(this);
		mypanel.add(gn4);
		gn4.setBounds(1366/2-100,height*6/8,200,50);
		
		gn5.addActionListener(this);
		mypanel.add(gn5);
		gn5.setBounds(1366/2-100,height*7/8,200,50);
		
		next.addActionListener(this);
		exit.addActionListener(this);
		sure.addActionListener(this);
		sure1.addActionListener(this);
		sure2.addActionListener(this);
		sure3.addActionListener(this);
		sure4.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
//			JFileChooser jfc=new JFileChooser();
//    		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
//    		jfc.showDialog(new JLabel(), "选择");
//    		File file=jfc.getSelectedFile();
//    		if(file.isFile())
//    		{
    			try {
    				T = main_1.createDirectedGraph("E:\\javaapp\\Test.java\\text1.txt"); //file.getAbsolutePath()
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
   				e1.printStackTrace();
   			}
//    		}
		}
		else if(e.getSource()==jb2)
		{
			main_1.showDirectedGraph(T);
        	JFrame frm=new JFrame();
        	JPanel pnl=new JPanel(new BorderLayout());
        	frm.add(pnl);
        	frm.setSize(1000,1000);
        	JScrollPane sp = new JScrollPane(pnl);
        	frm.getContentPane().add(sp);
        	pnl.setBounds(0, 0, 500, 500);
        	frm.setVisible(true);
        	JLabel imag=new JLabel(new ImageIcon("E:\\javaapp.gif"));
        	imag.setBounds(0,0,pnl.getWidth(),pnl.getHeight());
        	pnl.add(imag);
		}
		else if(e.getSource()==exit)
		{
			pn1.removeAll();
			pn1.setVisible(false);
			mypanel.setVisible(true);
		}
		else if(e.getSource()==gn1)
		{
			Label l1=new Label("查询桥接词功能");
			l1.setFont(new Font("Dialog",1,25));
			l1.setBounds(width/2-100,50,300,60);
			pn1.add(l1);
			pn1.setLayout(null);
			pn1.add(from);
			pn1.add(to);
			pn1.add(bridge_word);
			bridge_word.setLineWrap(true);
			pn1.add(sure);
			pn1.add(exit);
			from.setBounds(width/2-100,130,200,30);
			to.setBounds(width/2-100,180,200,30);
			bridge_word.setBounds(width/2-100,230,200,60);
			sure.setBounds(width/2-100,310,200,30);
			exit.setBounds(width/2-100,360,200,30);
			from.setText(null);
			to.setText(null);
			bridge_word.setText(null);
			mypanel.setVisible(false);
			pn1.setVisible(true);
			myframe.add(pn1);
		}
		else if(e.getSource()==sure)
		{
			String result=main_1.queryBridgeWords(T,from.getText().toLowerCase(),to.getText().toLowerCase());
			bridge_word.setText(result);
		}
		else if(e.getSource()==gn2)
		{
			Label l2=new Label("生成文本功能");
			l2.setFont(new Font("Dialog",1,25));
			l2.setBounds(width/2-100,50,300,60);
			User_input.setBounds(width/2-100,130,200,60);
			word_output.setBounds(width/2-100,210,200,60);
			User_input.setLineWrap(true);
			word_output.setLineWrap(true);
			word_output.setEditable(false);
			pn1.add(l2);
			pn1.add(User_input);
			pn1.add(word_output);
			pn1.add(sure1);
			pn1.setLayout(null);
			pn1.add(exit);
			sure1.setBounds(width/2-100,310,200,30);
			exit.setBounds(width/2-100,360,200,30);
			User_input.setText(null);
			word_output.setText(null);
			mypanel.setVisible(false);
			pn1.setVisible(true);
			myframe.add(pn1);
		}
		else if(e.getSource()==sure1)
		{
			String result=main_1.generateNewText(T,User_input.getText().toLowerCase());
			word_output.setText(result);
		}
		else if(e.getSource()==gn5)
		{
			Label l3=new Label("随机游走");
			l3.setFont(new Font("Dialog",1,25));
			l3.setBounds(width/2-100,50,300,60);
			start.setBounds(width/2-100,130,200,30);
			ran_go.setBounds(width/2-100,210,200,60);
			ran_go.setLineWrap(true);
			ran_go.setEditable(false);
			pn1.add(l3);
			pn1.add(ran_go);
			pn1.add(start);
			pn1.add(next);
			pn1.add(sure2);
			pn1.setLayout(null);
			pn1.add(exit);
			sure2.setBounds(width/2-100,310,200,30);
			exit.setBounds(width/2-100,360,200,30);
			next.setBounds(width/2-100,400,200,30);
			next.setVisible(false);
			start.setText(null);
			ran_go.setText(null);
			mypanel.setVisible(false);
			pn1.setVisible(true);
			myframe.add(pn1);
		}
		else if(e.getSource()==sure2)
		{
			for(int i=0;i<T.num;i++)
				for(int j=0;j<T.num;j++)
					T.visited[i][j]=0;
			if(!T.s_to_int.containsKey(start.getText().toLowerCase()))
			{
				ran_go.setText("no such the word!");
				return;
			}
			x=T.s_to_int.get((start.getText().toLowerCase()));
			ran_go.setText(null);
			next.setVisible(true);
			//String result=main_1.randomWalk(T,start.getText().toLowerCase());
			//ran_go.setText(result);
		}
		else if(e.getSource()==next)
		{
			String s=main_1.randgo(T,T.num,x);
			if(s==null)
			{
				ran_go.setText(ran_go.getText()+" No words");
				next.setVisible(false);
				return;
			}
			ran_go.setText(ran_go.getText()+" "+s);
			if(!T.s_to_int.containsKey(s))
			{
				next.setVisible(false);
				return;
			}
			x=T.s_to_int.get(s);
		}
		else if(e.getSource()==gn4)
		{
			Label l3=new Label("点到其他个点");
			l3.setFont(new Font("Dialog",1,25));
			l3.setBounds(width/2-100,50,300,60);
			start1.setBounds(width/2-100,130,200,30);
			short2.setBounds(width/2-100,210,200,200);
			short2.setLineWrap(true);
			short2.setEditable(false);
			pn1.add(l3);
			pn1.add(start1);
			pn1.add(short2);
			pn1.add(sure3);
			pn1.setLayout(null);
			pn1.add(exit);
			sure3.setBounds(width/2-100,440,200,30);
			exit.setBounds(width/2-100,500,200,30);
			start1.setText(null);
			short2.setText(null);
			mypanel.setVisible(false);
			pn1.setVisible(true);
			myframe.add(pn1);
		}
		else if(e.getSource()==sure3)
		{
			short2.setText("");
			if(!T.s_to_int.containsKey(start1.getText().toLowerCase()))
			{
				short2.setText("there is no such a word!");
				return;
			}
			Stack []result=main_1.all_calcShortestPath(T,start1.getText().toLowerCase());
			int i=0;
			while(i<T.num)
			{
				if(!result[i].isEmpty())
				{
					short2.setText(short2.getText()+" "+start1.getText().toLowerCase()+" ");
					while(!result[i].isEmpty())
					{
						short2.setText(short2.getText()+(result[i].pop()+" "));
					}
					short2.setText(short2.getText()+("\n"));
				}
				i++;
			}
		}
		else if(e.getSource()==gn3)
		{
			Label l3=new Label("点到点");
			l3.setFont(new Font("Dialog",1,25));
			l3.setBounds(width/2-100,50,300,60);
			start2.setBounds(width/2-100,130,200,30);
			end.setBounds(width/2-100,180,200,30);
			short1.setBounds(width/2-200,220,200,200);
			short1.setLineWrap(true);
			short1.setEditable(false);
			short3.setBounds(width/2+10,220,200,200);
			short3.setLineWrap(true);
			short3.setEditable(false);
			pn1.add(l3);
			pn1.add(start2);
			pn1.add(end);
			pn1.add(short1);
			pn1.add(sure4);
			pn1.setLayout(null);
			pn1.add(exit);
			pn1.add(short3);
			sure4.setBounds(width/2-100,440,200,30);
			exit.setBounds(width/2-100,500,200,30);
			start2.setText(null);
			short3.setText(null);
			end.setText(null);
			short1.setText(null);
			mypanel.setVisible(false);
			pn1.setVisible(true);
			myframe.add(pn1);
		}
		else if(e.getSource()==sure4)
		{
			Stack stack=new Stack();
			String result="there is no such word!";
			if(T.s_to_int.containsKey(start2.getText().toLowerCase())&&T.s_to_int.containsKey(end.getText().toLowerCase()))
			{
				result=main_1.calcShortestPath(T,start2.getText().toLowerCase(),end.getText().toLowerCase());
			}
			short1.setText(result);
			short3.setText(null);
			for(int j=0;j<main_1.arr.length;j++)
			{
				for(int i=0;i<main_1.arr[j].length;i++)
				{
					if(main_1.arr[j][i]==-1)
						break;
					stack.push(T.int_to_s.get(""+main_1.arr[j][i]));
				}
				while(!stack.isEmpty())
				{
					short3.setText(short3.getText()+" "+stack.pop());
				}
				short3.setText(short3.getText()+"\n");
			}
		}
	}
}
