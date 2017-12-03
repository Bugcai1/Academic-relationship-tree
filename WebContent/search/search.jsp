<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询界面</title>
	<script src="jquery.min.js"></script>
	<script type="text/javascript" src="search/jquery-2.1.4.js"></script>
	
	<script src="search/jtopo-0.4.8-min.js"></script>
	<link rel="stylesheet" href="bootstart/css/search.css" />
	
    <link rel="stylesheet" type="text/css" href="bootstart/jquery-ui.css">
   

    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link href="bootsart/js/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <script type="text/javascript" src="bootstart/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="bootstart/js/locales/bootstrap-datetimepicker.fr.js"></script>
    
	<script type="text/javascript" src="home.min.js"></script>
	<link href="kowa.css" rel="stylesheet" type="text/css" />
	

</head>
<div>
	<section class="demo" style="background:green">
	<ol class="nav1">
      <li><a href="login.jsp">登陆</a></li>
      <li><a href="#">About Me</a></li>
      <li><a href="search.jsp">查询</a></li>
      <li><a href="search.jsp">修改</a></li>
      <li><a href="dialog/newDialog.html">合并</a></li>
      <li><a href="addrelation.jsp">添加</a></li>
    </ol>
    </section>
</div>
<body onload="commit()">
<div id="left1"><canvas height="600" id="relation"></canvas></div>
<div id="right1">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                 查询
            </a>
        </li>
    <li><a href="#change" data-toggle="tab">修改</a></li>
    <li><a href="#merge" data-toggle="tab">添加</a></li>
    <li><input type="button" onclick="load()">添加</a></li>
    </ul>

    <div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="home">
        <input type="hidden" id="uesr_iid"/>
        <form action="#">
            身份账号：<input type="text" name="id" id="user_id"/></br>
            查询类型:</br>
                <input type="checkbox"  name = "searchType" value = "1">老师
                <input type="checkbox"  name = "searchType" value= "2" >师兄弟
                <input type="checkbox"  name = "searchType" value= "3" >学生<br>
            时间段查询：</br><input type="text"  placeholder="start time" id="start_time"><input placeholder="请输入结束时间" type="text" id="end_time">
        </br>
            <input style="margin-top:5px" type="button" value="查询" onclick="search()" />
        </form>
        
    </div>

    <div class="tab-pane fade" id="change">
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
            <td><select id="relation_1" style="border-style: none"><option value="1">老师</option><option value="2">师兄弟</option><option value="3">学生</option></select></td>
        </tr>
        <tr>
            <td>起始时间</td>
            <td><input id="start_time1" style="border:none"></td>
        </tr>
        <tr>
            <td>结束时间</td>
            <td><input id="end_time1" style="border-style: none"></td>
        </tr>
        <tr>
            <td>操作</td>
            <td>
         
              <input type="button" value="修改此关系" onclick="changeRelation()" >
                <input type="button" value="删除此关系" onclick="deleteRelation()">
            </td>
        </tr>
    </table>
    </div>

    <div class="tab-pane fade" id="merge">
    <div id="top">
        <h2>供您选择的方案:</h2>
        <ol>
        <li><a href="#" id="add-key2" data-title="填写新成员信息" onclick="add_new()">创建新人物</a></li>
            <li>
                <p>拖动下方人物进行添加</p>
            </li>
      </ol>
      <div id="div_brother" ondrop="drop_brother()" ondragover="allowDrop(event)">
      <table border="1" id = "table2">
        <tr>
            <td>姓名</td>
            <td><input id="add_name" style="border:none"></td>
        </tr>
        <tr>
            <td>关系</td>
            <td><select id="add_relation" style="border-style: none"><option value="0">--请选择与此人的关系--</option><option value="1">老师</option><option value="2">师兄弟</option><option value="3">学生</option></select></td>
        </tr>
        <tr>
            <td>起始时间</td>
            <td><input id="add_start_time" style="border:none"></td>
        </tr>
        <tr>
            <td>结束时间</td>
            <td><input id="add_end_time" style="border-style: none"></td>
        </tr>
    </table>
    </div>
         <input type="button" value="chaxun" onclick="sh()">
        </div>
        <div id="bottom" class="content">
    		<ul id="da-thumbs" class="da-thumbs">
    		 <li><a href="#" target="_blank"> <img src="photo/bg.jpg"><div class="hot_info"><h2 title="牙周病困扰">牙周病困扰！</h2><em></em><p title="牙周病困扰">这些痛苦，你是否也感同身受？忍无可忍，无需再忍！科瓦齿科帮你彻底摆脱牙周病困扰</p></div></a></li>
    		</ul>
    	</div>
    </div>
    </div>
    

    <input type="hidden" id="par_id">
    <input type="hidden" id="chi_id">
</div>
</body>

<script type="text/javascript">//页面加载的时候加载候选人
    window.onload=function(){
	
		var thisURL = document.URL;  
    	var  getval =thisURL.split('?')[1];  
    	var showval= getval.split("=")[1];
    	$("#user_id").val(showval);    
	var url="lookup";
	$.post(url,{"name":"bao"},function(data){
		var num=data.length;
		var text="";
		for(var i=0;i<num;i++)
		{
		 text=text+"<li>"+
		      "<a href=\"#\" target=\"_blank\"> <img src=\"photo/1.png\">"+
		        "<div class=\"hot_info\">"+
		          "<h2 title=\"牙周病困扰\">牙周病困扰！</h2>"+
		          "<em></em>"+
		          "<p title=\"牙周病困扰\">这些痛苦，你是否也感同身受？忍无可忍，无需再忍！科瓦齿科帮你彻底摆脱牙周病困扰</p>"+
		        "</div>"+
		        "</a>"+
		      "</li>";
			
			
		}
		$("#da-thumbs").html(text).trigger('create');
	},'json');

	
	var names = document.getElementsByName("searchType");
	var type = 0;
	for(var i=0;i<names.length;i++){  
        names[i].checked=true;
 	}
	search();
	};
</script>

<script type="text/javascript">//日历的控件设置，加载的格式
function load()
{
	alert("name");
	var head = document.getElementsByTagName('da-thumbs')[0];
    var link = document.createElement('link');
    link.href = 'kowa.css';
    link.rel = 'stylesheet';
    link.type = 'text/css';
    head.appendChild(link);	
    alert("lll");
    var script = document.createElement('script');
    script.src = 'home.min.js';
    script.type = 'text/javascript';
    head.appendChild(script);
    alert("yyyy");
}
  $('#start_time').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒 
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1,
    });
  $('#end_time').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒 
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1,
    });
</script>

<script type="text/javascript">
function add_new()
{
    var  getval =$('#user_id').val();
	window.location.href="add_new.html?cc="+getval;
}
</script>
<script type="text/javascript">//删除操作
    function deleteRelation()
    {
	   var url = "deleteRelation";
	   var ID1=$('#par_id').val();
	   var ID2=$('#chi_id').val();
       alert(ID1+"  "+ID2+" "+url);
	   $.post(url,{"user_id":$('#user_id').val(),"ID1":ID1,"ID2":ID2},function(data,status){
		search();
	   });
    }
    function changeRelation()
    {
    	var url="changeRelation";
    	$.post(url,{"user_id":$("#user_id").val(),"ID1":$("#par_id").val(),"ID2":$("#chi_id").val(),"par_name":$("#par").val(),"chi_name":$("#chi").val(),"relation":$("#relation_1").val(),"start":$("#start_time1").val(),"end":$("#end_time1").val()},function(data,status){
    		alert("fanhui");
				  		
    	});
    	search(); 
    }
</script>

<script type="text/javascript">/*一次添加操作时的检错机制，以及添加的功能*/
    var name="";
    var work="";
    var sex="";
    var phone="";
    var id="";
    var start="";
    var end="";
    var data_brother=[];
    function allowDrop(e){
	e.preventDefault();
    }
/*点击图片时就执行*/
    function drag(t){
	   name=$(t).siblings("#inp_name").val();
	   sex=$(t).siblings("#inp_sex").val();
	   work=$(t).siblings("#inp_work").val();
    	phone=$(t).siblings("#inp_phone").val();
	   id=$(t).siblings("#pid").val();
    }
/*将添加的人的信息加入数组中，检擦时使用*/
    function addJson(data,name,sex,work,phone,start,end,id){
	alert("nnn");
	   for(var j=0;j<data.length;j++){
		  if(data[j].id==id){
			return;
		  }
    	}
	   alert("name"+name);
	   data.push({
          id:id,
		    name:name,
    	   sex:sex,
	       work:work,
	 	phone:phone,
	 	start:start,
	 	end:end,
	   });
    }
/**/
    function drop_brother(){
	   addJson(data_brother,name,sex,work,phone,start,end,id);
	   addHtml_brother();
    }
    /*在页面显示添加人的名字信息*/
    function addHtml_brother(){
	  $('#add_name').val(data_brother[data_brother.length-1].name);
    }
</script>
<script type="text/javascript">
var canvas = document.getElementById('relation');
var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var ctx=canvas.getContext("2d");
function search()
{
	alert("search");
	var names = document.getElementsByName("searchType");
	var type = "";
	var start="";
	var end="";
	for(var i=0;i<names.length;i++){  
        if(names[i].checked==true){
        	type=type+"1";
        }
        else
        	type=type+"0";
 	}
	if(type == "000"){
		alert("您的查询条件有误，请确认！");
		return;
	}
	if($("#start_time").val()==''){
		start="0000-00-00";
	}
	else
		start=$('#start_time').val();
	if($("#end_time").val()==''){
		end="9999-99-99";
	}
	else
		end=$("#end_time").val();
	
	var url="search";
	$.post(url,{"flaag":0,"Id":$('#user_id').val(),"relationType":type,"beginTime":start,"endTime":end},function(data,status){
		/*
		*应该先清画布，不知是否正确
		*/
	    canvas.width=screen.width*0.69;
	 	create(data);
	});
}

/*画人物的关系图*/
function create(data1){
	stage.clear();
	var data_tmp=eval('('+data1+')');
	
    var i=0;
    var k=0;
    var num=data_tmp.length;
    if(num==0)
    {
    	var url="search";
    	$.post(url,{"flaag":1,"Id":$('#user_id').val()},function(data,status){
    		
    		data_tmp=eval('('+data+')');
    		var parent=data_tmp[0].parent_id;
    		var scene = new JTopo.Scene(stage); // 创建一个场景对象
    	    scene.backgroundColor="#f1f1f1";
    	    var userNode = new JTopo.Node(); // 创建一个节点
    	    userNode.setBound(250, 200, 66, 66); // 同时设置大小及位置
    	    userNode.showSelected = true; // 不显示选中矩形
    	    userNode.setImage('photo/1.png'); // 设置图片
    	    userNode.id=parent;
    	    userNode.mouseover(function(){
    	    	
    	    });
    	    scene.add(userNode); // 放入到场景中
    	});
    	return;
    }
    
    var data=data_tmp;
    var parent=data[0].parent_id;
    var scene = new JTopo.Scene(stage); // 创建一个场景对象
    scene.backgroundColor="#f1f1f1";
    var userNode = new JTopo.Node(); // 创建一个节点
    userNode.setBound(250, 200, 66, 66); // 同时设置大小及位置
    userNode.showSelected = true; // 不显示选中矩形
    userNode.setImage('photo/1.png'); // 设置图片
    userNode.id=parent;
    
    userNode.mouseover(function(){
    	
    });
    scene.add(userNode); // 放入到场景中
    var jr=true,py=true,ts=true;
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
            otherNode.setImage('photo/1.png', false); // 设置图片
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
            	
                $("#relation_1").val(data[this.ps].relation);
                
                $("#start_time1").val(data[this.ps].start_time);
                $("#end_time1").val(data[this.ps].end_time);
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