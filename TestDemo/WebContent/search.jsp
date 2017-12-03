<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询界面</title>
	<script type="text/javascript" src="search/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="search/json2.js"></script>
	<script src="search/jtopo-0.4.8-min.js"></script>
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
      <li><a href="search.jsp">修改</a></li>
      <li><a href="">合并</a></li>
      <li><a href="addrelation.jsp">添加</a></li>
    </ul>
    </section>
</div>
<body onload="commit()">
<div id="left1"><canvas height="600" id="relation"></canvas></div>
<div id="right1">
	<div id="main">
		<input type="hidden" id="uesr_iid"/>
		<form action="#">
			身份账号：<input type="text" name="id" id="user_id"/></br>
			查询类型:</br>
				<input type="checkbox"  name = "searchType" value = "1">全部关系</br>
				<input type="checkbox"  name = "searchType" value= "2" >老师</br>
				<input type="checkbox"  name = "searchType" value= "3" >师兄弟</br>
				<input type="checkbox"  name = "searchTypeNotUse" value = "4" >学生</br>
		</br>
			<input type="button" value="查询" onclick="search()" />
		</form>
	</div>
		
	<div id="showa">
	<table border="1" id = "table1">
	    <tr>
	        <td>1号</td>
	        <td><input id="par" style="border:none"></td>
	    </tr>
	    <tr>
	        <td>2号</td>
	        <td><input id="chi" style="border:none"></td>
	    </tr>
	    <tr>
	        <td>关系</td>
	        <td><input id="rela" style="border:none"></td>
	    </tr>
	    <tr>
	        <td>操作</td>
	        <td>
	     
		      <input type="button" value="修改此关系" id="add-key2" >
		        <input type="button" value="删除此关系" onclick="deleteRelation()">
	        </td>
	    </tr>
	</table>
	<input type="hidden" id="par_id">
	<input type="hidden" id="chi_id">
	</div>
</div>
</body>
<script type="text/javascript">
function deleteRelation()
{
	var url = "deleteRelation";
	var ID1=$('#par_id').val();
	var ID2=$('#chi_id').val();
	//alert(ID1+"  "+ID2+" "+url);
	$.post(url,{"TableID":$('#user_id').val(),"ID1":ID1,"ID2":ID2},function(data,status){
		alert("Delete success");
		search();
	});
}




/*
function search()
{
	var names = document.getElementsByName("searchType");
	var type = 0;
	for(var i=0;i<names.length;i++){  
        if(names[i].checked==true){
        	type = type + 1*Math.pow(10,2-i);  //1的位置对应选择查询的关系
        }
 	}
	if(type == 0){
		alert("您的查询条件有误，请确认！");
		return;
	}
	var url="search";
	$.post(url,{"id":$('#user_id').val(),"search_type":type},function(data,status){

		
		var c=document.getElementById("relation");    
	    c.width=screen.width*0.6975;
	 	create(data);
	});
}
*/



</script>

<script type="text/javascript">
    $("#add-key2").click(function(){
       //alert("ttt");
    	$("#add-key2").createModal({
            background: "white",//设定弹窗之后的覆盖层的颜色
            width: "200px",//设定弹窗的宽度
            height: "200px",//设定弹窗的高度
            resizable: true,//设定弹窗是否可以拖动改变大小
			bgClose: true,
            html:"<form id=\"addnew\">"+
	            "新的关系:<select id=\"new_type_zz\">"+
	            "<option value=\"1\">teacher</option>"+
	            "<option value=\"2\">brother</option>"+
	            "<option value=\"3\">student</option>"+
	         	"</select>"+"</br>"+
	            "开始时间:<input type=\"text\" id=\"new_startz\"></br>"+
	            "结束时间:<input type=\"text\" id=\"new_endz\"></br>"+
	            "<input type=\"button\" value=\"确认修改\" onclick=\"changeRelation()\"></form>"
        });
    });
    function changeRelation()
    {
    	var ID1=$('#par_id').val();
    	var ID2=$('#chi_id').val();
    	
    	var nrela=$('#new_type_zz').val();
    	var start=$('#new_startz').val();
    	var end=$('#new_endz').val();
    	
    	var url="changeRelation";
    	$.post(url,{"TableID":$('#user_id').val(),"ID1":ID1,"ID2":ID2,"nowRelation":nrela,"startTime":start,"endTime":end},function(data,status){
    		document.getElementById('close').click();
    		alert("Change success");
		search();
    	});
    	
    };
</script>


<script type="text/javascript">
var canvas = document.getElementById('relation');
var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var ctx=canvas.getContext("2d");
function search()
{
        var names = document.getElementsByName("searchType");
        var type = 0;
        for(var i=0;i<names.length;i++){  
        if(names[i].checked==true){
                type = type + 1*Math.pow(10,2-i);  //1的位置对应选择查询的关系 
        }
        }
        if(type == 0){
                alert("您的查询条件有误，请确认！");
                return; 
        }
        var url="search";
        $.post(url,{"id":$('#user_id').val(),"search_type":type},function(data,status){
            	canvas.width=screen.width*0.6975;
                create(data);
        });
}

function create(data1){
	var data=eval('('+data1+')');
    var i=0;
    var k=0;
    var parent=data[0].parent_id;
    stage.clear();
    var scene = new JTopo.Scene(stage); // 创建一个场景对象
    //scene.backgroundColor="#f1f1f1";
    scene.background="secuse.jpeg";
    var userNode = new JTopo.Node(); // 创建一个节点
    userNode.setBound(250, 200, 66, 66); // 同时设置大小及位置
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
            otherNode.setBound(250+data[i].x, 200+data[i].y,50, 50); // 同时设置大小及位置
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
            	link = new JTopo.Link(userNode, otherNode); // 增加连线
            	link.strokeColor ='235, 175, 66';
        	}
            else if(parseInt(data[i].relation)==2){
            	link = new JTopo.Link(userNode, otherNode); // 增加连线
            	link.strokeColor ='242, 75, 74';
        	}
            else if(parseInt(data[i].relation)==3){
            	link = new JTopo.Link(userNode, otherNode); // 增加连线
            	link.strokeColor ="61, 124, 201";
        	}
            i++;
            link.par=userNode.id;
            link.child=otherNode.id;
            link.ps=i-1;
            link.click(function(){
            	$('#par').val(data[this.ps].parent_name);
            	$('#par_id').val(this.par);
            	$('#chi_id').val(this.child);
            	$('#chi').val(data[this.ps].child_name);
		var relationType;
		if(data[this.ps].relation == 1){
			relationType = "老师";
		}else if(data[this.ps].relation==2){
			relationType = "师兄弟";
		}else{
			relationType = "学生";
		}
		$('#rela').val(relationType);
            });
            scene.add(link);
            temp=data[i].parent_id;
        }
        
        var j=0;
        while(j<i)
        {
            if(parseInt(data[j].child_id)==parseInt(data[i].parent_id))
            {
                break;
            }
            j=j+1;
        }
        userNode=otherNode_z[j];
        parent=data[i].parent_id;
    }
}
</script>
</html>
