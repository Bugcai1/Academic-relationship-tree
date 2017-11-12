<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询界面</title>
	<script type="text/javascript" src="jquery-2.1.4.js"></script>
	<script type="text/javascript" src="json2.js"></script>
	<script src="jtopo-0.4.8-min.js"></script>
	<link rel="stylesheet" href="css/search.css" />
	<link rel="stylesheet" type="text/css" href="dialog/common.css"/>
	<link rel="stylesheet" type="text/css" href="dialog/modal.css"/>
	<script type="text/javascript" src="dialog/jquery.js"></script>
	<script type="text/javascript" src="dialog/moudel.js"></script>
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
			查询类型:</br>
				<input type="checkbox"  name = "searchType" value = "1">老师</br>
				<input type="checkbox"  name = "searchType" value= "2" >师兄弟</br>
				<input type="checkbox"  name = "searchType" value= "3" >学生</br>
		</br>
			<input type="button" value="查询" onclick="search()" />
		</form>
	</div>
	<div id="show">
		
	
	<table border="1" id = "table1">
	    <tr>
	        <td>1号</td>
	        <td id="par">Name</td>
	    </tr>
	    <tr>
	        <td>2号</td>
	        <td id="chi">Name</td>
	    </tr>
	    <tr>
	        <td>关系</td>
	        <td id="rela">Relation</td>
	    </tr>
	    <tr>
	        <td>操作</td>
	        <td>
		        <input type="button" value="修改此关系" id="add-key2">
		        <input type="button" value="删除此关系" onclick="deleteRelation()">
	        </td>
	    </tr>
	</table>
	</div>
</div>
</body>
<script type="text/javascript">
function deleteRelation()
{
	var url = "deleteRelation";
	var ID1=$('#par').val();
	var ID2=$('#chi').val();
	var Relation=$('#rela').val();
	$.post(url,{"TableID":$('#user_id').val(),"ID1":ID1, "ID2":ID2, "Relation":Relation},function(data,status){
		/*
		*应该先清画布，不知是否正确
		*/
		alert("nihao");
		var c=document.getElementById("relation");    
	    c.height=c.height;
	 	create(data);
	});
}

function search()
{
	var names = document.getElementsByName("searchType");
	var type = 0;
	for(var i=0;i<names.length;i++){  
        if(names[i].checked==true){
        	type = type + 1*Math.pow(10,2-i);  //1的位置对应选择查询的关系
        }
 	}
	if(type = 0){
		alert("您的查询条件有误，请确认！");
		return;
	}
	var url="search";
	$.post(url,{"id":$('#user_id').val(),"search_type":type},function(data,status){
		/*
		*应该先清画布，不知是否正确
		*/
		//alert("nihao");
		var c=document.getElementById("relation");    
	    c.height=c.height;
	 	create(data);
	});
}
</script>

<script>
    $("#add-key2").click(function(){
        $("#add-key2").createModal({
            background: "white",//设定弹窗之后的覆盖层的颜色
            width: "400px",//设定弹窗的宽度
            height: "200px",//设定弹窗的高度
            resizable: true,//设定弹窗是否可以拖动改变大小
			bgClose: true,
            html:
            	"<form id=\"addnew\">"+
	            "新的关系:<select id=\"new_type_z\">"+
	            "<option value=\"1\">teacher</option>"+
	            "<option value=\"2\">brother</option>"+
	            "<option value=\"3\">student</option>"+
	         	"</select>"+"</br>"+
	            "开始时间:<input type=\"text\" id=\"new_start\"></br>"+
	            "结束时间:<input type=\"text\" id=\"new_end\"></br>"+
	            "<input type=\"button\" value=\"确认修改\" onclick=\"changeRelation()\"></form>"
        });
    });
    function changeRelation()
    {
    	var ID1=$('#par').val();
    	var ID2=$('#chi').val();
    	var Relation=$('#rela').val();
    	var nowRelation=$('#new_type_z').val();
        var startTime=$('#new_start').val();
        var endTime=$('#new_end').val();
        
        var url = "changeRelation";
        //alert("nihao");
    	$.post(url,{"TableID":$('#user_id').val(),"ID1":ID1, "ID2":ID2, "preRelation":Relation, "nowRelation":nowRelation, "startTime":startTime,"endTime":endTIme},function(data,status){
    		/*
    		*应该先清画布，不知是否正确
    		*/
    		document.getElementById('close').click();
            //回掉函数将窗口关闭
    		//alert("nihao");
    		document.getElementById('close').click();
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
</html>