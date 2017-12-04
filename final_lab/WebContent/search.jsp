<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询界面</title>
	<script src="jquery.min.js"></script>
	<script src="search/jtopo-0.4.8-min.js"></script>
	<link rel="stylesheet" href="bootstart/css/search.css" />
	
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="demo.css">
	<script type="text/javascript" src="jquery.easyui.min.js"></script>
<style type="text/css">
</style>
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
<div id="right1" >
	<div class="easyui-tabs" data-options="tabWidth:112" style="width:700px;height:250px">
		<div title="Home" style="padding:10px">
			<p>Home Content.</p>
		</div>
		<div title="Maps" style="padding:10px">
			<p>Maps Content.</p>
		</div>
		<div title="Journal" style="padding:10px">
			<p>Journal Content.</p>
		</div>
		<div title="History" style="padding:10px">
			<p>History Content.</p>
		</div>
		<div title="References" style="padding:10px">
			<p>References Content.</p>
		</div>
		<div title="Contact" data-options="tabWidth:110" style="padding:10px">
			<p>Contact Content.</p>
		</div>
	</div>
</div>
</body>

<script type="text/javascript">//页面加载的时候加载候选人
    window.onload=function(){
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
	var url="search";
	$.post(url,{"flaag":"0","Id":"1","relationType":"111","beginTime":"0000-00-00","endTime":"9999-99-99"},function(data,status){
		/*
		*应该先清画布，不知是否正确
		*/
		 canvas.width=screen.width*0.68;
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