<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询界面</title>
	<script type="text/javascript" src="jquery-2.1.4.js"></script>
	 <script type="text/javascript" src="json2.js"></script>
<script src="jtopo-0.4.8-min.js"></script>
	<style type="text/css">
		#main{
			width: 500px;
			height: 300px;
			
		}
		#left1{
			height:700px;
			width:70%;
			margin-right:10px;
			background:blue;
			float:left;
		}
		#right1{
			height:700px;
			width:28%;
			background:red;
			float:left;
		}
		/*css代码*/
#menu{
width: 0; /*设置为0 隐藏自定义菜单*/
height: 125px;
overflow: hidden; /*隐藏溢出的元素*/
box-shadow: 0 1px 1px #888,1px 0 1px #ccc;
position: absolute; /*自定义菜单相对与body元素进行定位*/
}
.menu{
width: 130px;
height: 25px;
line-height: 25px;
padding: 0 10px;
background:red;

}
*{margin:0;padding:0;list-style-type:none;}
body{
  background: #ebebeb;
}
	.nav{
  width:560px;
  height: 50px;
  font:bold 0/50px Arial;
  text-align:center;
  margin:5px auto 0;
		border-radius: 8px;
	}
	.nav a{display: inline-block;
		-webkit-transition: all 0.2s ease-in;
		-moz-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
	}
	.nav a:hover{
		-webkit-transform:rotate(10deg);
		-moz-transform:rotate(10deg);
  -o-transform:rotate(10deg);
  -ms-transform:rotate(10deg);
  transform:rotate(10deg);
	}
	
	.nav li{
  position:relative;
  display:inline-block;
  padding:0 16px;
  font-size: 13px;
		text-shadow:1px 2px 4px rgba(0,0,0,.5);
  list-style: none outside none;
	}

	.nav li::before,
.nav li::after{
  content:"";
  position:absolute;
  top:14px;
  height: 25px;
  width: 1px;
 }
	.nav li::after{
  right: 0;
		background: -moz-linear-gradient(top, rgba(255,255,255,0), rgba(255,255,255,.2) 50%, rgba(255,255,255,0));
		background: -webkit-linear-gradient(top, rgba(255,255,255,0), rgba(255,255,255,.2) 50%, rgba(255,255,255,0));
		background: -o-linear-gradient(top, rgba(255,255,255,0), rgba(255,255,255,.2) 50%, rgba(255,255,255,0));
  background: -ms-linear-gradient(top, rgba(255,255,255,0), rgba(255,255,255,.2) 50%, rgba(255,255,255,0));
  background: linear-gradient(top, rgba(255,255,255,0), rgba(255,255,255,.2) 50%, rgba(255,255,255,0));
	}
	.nav li:first-child::before{
  background: none;
}
	.nav li:last-child::after{
  background: none;
}

	.nav a,
.nav a:hover{
  color:#fff;
  text-decoration: none;
 }
	</style>
</head>
<script type="text/javascript">
var thisURL = document.URL;  
var  getval =thisURL.split('?')[1];  
var showval= getval.split("=")[1]; 
function commit(){  
	     document.getElementById('uesr_iid').value=showval;    
}
</script>
<div>
	<section class="demo" style="background:green">
	<ul class="nav">
      <li><a href="login.jsp">登陆</a></li>
      <li><a href="#">About Me</a></li>
      <li><a href="search.jsp">查询</a></li>
      <li><a href="">修改</a></li>
      <li><a href="">合并</a></li>
      <li><a href="addrelation.jsp">添加</a></li>
    </ul>
    </section>
</div>
<body onload="commit()">
<!--自定义右键菜单html代码-->
<div id="menu">
<div class="menu" onclick="ss()">删除</div>
<div class="menu">修改</div>
<div class="menu">添加</div>
<div class="menu">查询</div>
</div>
<div id="left1"><canvas width="700" height="500" id="relation"></canvas></div>
<div id="right1">
	<div id="main">
		<input type="hidden" id="uesr_iid"/>
		<form action="#">
			身份账号：<input type="text" name="id" id="user_id"/></br>
			查询类型:<select name="type" id="search_type">
				<option values="1">老师</option>
				<option values="2">师兄弟</option>
				<option values="3">学生</option>
			</select>
		</br>
			<input type="button" value="查询" onclick="search()" />
		</form>
	</div>
	<div id="show">
		1号<input type="text" id="par"></br>
		2号<input type="text" id="chi"></br>
		关系<input type="text" id="rela"></br>
		<input type="button" value="删除此关系" onclick="#"></br>
		<input type="button" value="修改此关系" onclick="#"></br>
	</div>
</div>
</body>
<script type="text/javascript">
function search()
{
	var url="search";
	var type=0;
	if($('#search_type').val()=='老师')
		type=1;
	else if($('#search_type').val()=='师兄弟')
		type=2;
	else if($('#search_type').val()=='学生')
		type=3;
	else
	{
		alert("您的查询条件有误，请确认！");
		return;
	}
	$.post(url,{"id":$('#user_id').val(),"search_type":type},function(data,status){
		/*
		*应该先清画布，不知是否正确
		*/
		alert("nihao");
		var c=document.getElementById("relation");    
	    c.height=c.height;  
	 	create(data);
	});
}
</script>
<script type="text/javascript">
function create(data1){
	var data=eval('('+data1+')');
    var i=0;
    var k=0;
    var parent=data[0].parent_id;
    var canvas = document.getElementById('relation');
    var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
    var scene = new JTopo.Scene(stage); // 创建一个场景对象
    scene.backgroundColor="#AE0000";
    var userNode = new JTopo.Node(); // 创建一个节点
    userNode.setBound(137, 117, 66, 66); // 同时设置大小及位置
    userNode.showSelected = true; // 不显示选中矩形
    userNode.setImage('1.png'); // 设置图片
    userNode.id=parent;
    scene.add(userNode); // 放入到场景中
    var num=data.length,jr=true,py=true,ts=true;
    var link_all=new Array();
    var otherNode_z=new Array(num);
    while(i<num)
    {
        var temp=data[i].parent_id;
        while(parseInt(parent)==parseInt(temp))
        {
            var otherNode = new JTopo.Node(); // 创建一个节点
            otherNode.setBound(300+data[i].x, 200+data[i].y,50, 50); // 同时设置大小及位置
            otherNode.borderRadius = 50; // 圆角
            otherNode.setImage('2.png', false); // 设置图片
            otherNode.showSelected = true; // 不显示选中矩形
            scene.add(otherNode); // 放入到场景中
            otherNode.url='#';
            otherNode.id=data[i].child_id;
           	otherNode.click(function(event){
           		
            });
           	
            otherNode_z[i]=otherNode;
            var link;
            if(parseInt(data[i].relation)==1){
                if(jr==true){
                    jr=false;
                    link = new JTopo.Link(userNode, otherNode,data[i].relation); // 增加连线
                }else{
                    link = new JTopo.Link(userNode, otherNode); // 增加连线
            }
            link.strokeColor ='235, 175, 66';
        }else if(parseInt(data[i].relation)==4){
            // alert("44");
            if(ts==true){
                ts=false;
                link = new JTopo.Link(userNode, otherNode,data[i].relation); // 增加连线
            }else{
                link = new JTopo.Link(userNode, otherNode); // 增加连线
            }
            link.strokeColor ='242, 75, 74';
        }else if(parseInt(data[i].relation)==3){
            // alert("33");
            if(py==true){
                py=false;
                link = new JTopo.Link(userNode, otherNode,data[i].relation); // 增加连线
            }else{
                link = new JTopo.Link(userNode, otherNode); // 增加连线
            }
            link.strokeColor ="61, 124, 201";
        }
            i++;
            link.par=userNode.id;
            link.child=otherNode.id;
            link.ps=i-1;
            link.click(function(){
            	$('#par').val(data[this.ps].parent_name);
            	$('#chi').val(data[this.ps].child_name);
            	$('#rela').val(data[this.ps].relation);
            });
            scene.add(link);
            temp=data[i].parent_id;
        }
        
        var j=0;
        // alert("uu");
        while(j<i)
        {
            if(parseInt(data[j].child_id)==parseInt(data[i].parent_id))
            {
                break;
            }
        }
        // alert("j="+j);
        userNode=otherNode_z[j];
        //alert(userNode.getImage());
        parent=data[i].parent_id;
    }
}
</script>
<script type="text/javascript">	
	
</script>
</html>