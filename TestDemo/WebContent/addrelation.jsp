<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    <script src="addrelation/jtopo-0.4.8-min.js"></script>
    <script type="text/javascript" src="addrelation/jquery.min.js"></script>
	<link rel="Stylesheet" type="text/css" href="addrelation/login.css" />
		<script type="text/javascript" src="addrelation/jquery-2.1.4.js"></script>
		<link rel="stylesheet" type="text/css" href="addrelation/common.css" />
<link rel="stylesheet" type="text/css" href="addrelation/modal.css" />
<link rel="stylesheet" type="text/css" href="css/search.css" />
<script type="text/javascript" src="addrelation/moudel.js"></script>
		<title>添加关系</title>
		

		<style>
		
		
		
		
			*{padding:0px;margin:0px}
			*{padding:0px;margin:0px}
			table,thead{width: 100%;}
			tr th{width:20%;height:30px;background-color: #23B7E5;font-size: 16px;padding: 2px 0px;color:#fff;}
			tr td{text-align: center;padding: 5px 0px;background-color: #fff;font-size: 14px;}
			tr td a{padding: 2px 5px;border:1px solid #ddd;cursor: pointer;}
			.body{
			width: 100%;
			height: 100%;
			background: white;
		}
		#top{
			height: 400px;
			width: 100%;
			margin-top:15px;
			margin-bottom:10px;  
			/*background: yellow;*/
		}
		#teacher{
			height: 200px;
			width: 30%;
			margin-left:2.5%;
			float: left;
			border-bottom: 1px dashed red;
		}
		#brother{
			height: 200px;
			width: 30%;
			margin-left: 2.5%;	
			float: left;
			border-bottom: 1px dashed blue;
			overflow-y: scroll;
		}
		#student{
			height: 200px;
			width: 30%;
			margin-left: 2.5%;
			float: left;
			border-bottom: 1px dashed blue;
			overflow-y: scroll;
		}
		.hide{
			width: 150px;
			height: 60px;
			display: none;
			/*z-index: 999;*/
		}
		.conter{

		}
		#tijiao{
			height: 50px;
			width: 100%;
			background: blue;
		}
		#left1{
			height:500px;
			width:40%;
			margin-right:1%;
			float:left;
			background:white;
		}
		#left2{
			height:500px;
			width:23%;
			margin-right:1%;
			float:left;
			background:white;
		}
		#left3{
			height:500px;
			width:35%;
			float:left;
			background:white;
		}
			
			#div_teacher ul li p{50%;text-align: right;flex: 1;}
			
			#div_student ul li p{50%;text-align: right;flex: 1;}
		
			#div_brother ul li p{50%;text-align: right;flex: 1;}
			#div2 ul{overflow:hidden;}
			#div2 ul li{float: left;width:70px;height:60px;border:1px solid #ddd;margin-right: 10px;}
			#div2 ul li img{width: 100%;height: 100%;position: relative;}
			.div_panel{min-height: 200px;}
		</style>
		<script type="text/javascript">
			window.onload=function(){
				var url="lookup";
				$.post(url,{"name":"bao"},function(data){
					var num=data.length;
					for(var i=0;i<num;i++)
					{
						
						var text="<li>"+
								"<img src=\"1.png\" draggable=\"true\" ondragstart=\"drag(this)\"/>"+
										"<input type=\"hidden\" id=\"inp_name\" value="+data[i].name+">"+
										"<input type=\"hidden\" id=\"inp_sex\" value="+data[i].sex+">"+
										"<input type=\"hidden\" id=\"inp_work\" value="+data[i].work+">"+
										"<input type=\"hidden\" id=\"inp_phone\" value="+data[i].phone+">"+
										"<input type=\"hidden\" id=\"pid\" value="+data[i].id+">"+
							"</li>";
						$('ul').append(text);
					}
				},'json');
				var urll="search";
				$.post(urll,{"id":1,"search_type":100},function(data,status){
					var w=parseInt(screen.width);
					var c=document.getElementById("relation");    
				    c.width=w*0.4;
					create(data);
				});
			}
		</script>
	</head>
	<body>	
		 <div>
	<section class="demo" style="background:green">
	<ol class="nav">
      <li><a href="login.jsp">登陆</a></li>
      <li><a href="#">About Me</a></li>
      <li><a href="search.jsp">查询</a></li>
      <li><a href="search.jsp">修改</a></li>
      <li><a href="">合并</a></li>
      <li><a href="addrelation.jsp">添加</a></li>
    </ol>
    </section>
</div>

	<div id="example">
	<div id="left1"><canvas height="500" id="relation"></canvas></div>
	<div id="left2">
		<div id="top">
			<div id="div2">
				<ul>
				</ul>
			</div>
		</div>
	</div>
	<div id="left3">
		<div align="center">这是<input type="text" id="final_people"/>的关系树</div>
		<div id="div_brother" ondrop="drop_brother()" ondragover="allowDrop(event)">
			<div class="div_panel">
				<table border="0" cellspacing="0" cellpadding="0">
					<caption><h2>添加的人物</h2></caption>
						<thead>
							<tr>
								<th>姓名</th>
								<th>开始时间</th>
								<th>终止时间</th>
								<th>关系</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="u2_panel"></tbody>
				</table>
			</div>
		</div>
	</div>
	</br>
		<div>
		<input type="hidden" id="user_id" value=""/></br>
		<input type="hidden" id="start_time"/></br>
		<input type="hidden" id="end_time"/></br>
		<input type="hidden" id="type">
		</div>
	</div>
	<div id="diag" class="key-word"><a href="javascript:;" id="add-key2" data-title="填写新成员信息">创建新人物</a></div>
	<div id="LoginBox">
       <form id="fmm" method="post">
            <div class="row1">
                完善信息<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
            </div>
            <div class="row" align="center">
               <font size="12px">关系:</font>
                 <select id="type_z">
                 	<option value="1">老师</option>
					<option value="2">师兄弟</option>
					<option value="3">学生</option>
                 </select>  
            <div class="row" align="center">
                <font size="12px">开始时间:</font>
                 <input type="text" id="start" placeholder="2017-01-30" />
                
            </div>
            <div class="row" align="center">
                <font size="12px">结束时间:</font>
                 <input type="text" id="end" placeholder="2017-01-30" />
                
            </div>
            <div class="row">
            <a href="#" id="btn" onclick="submit()">保存</a>
        </div>
        </form>
    </div>	
	</body>
	<script type="text/javascript">
			 $("#add-key2").click(function(){
			        $("#add-key2").createModal({
			            background: "red",//设定弹窗之后的覆盖层的颜色
			            width: "1000px",//设定弹窗的宽度
			            height: "500px",//设定弹窗的高度
			            resizable: true,//设定弹窗是否可以拖动改变大小
						bgClose: true,
						html:"<form id=\"addnew\">"+
						"<div class=\"row\" align=\"center\">name:<input type=\"text\" id=\"new_name\"></br></div>"+
			            "<div class=\"row\" align=\"center\">sex:<input type=\"text\" id=\"new_sex\"></br></div>"+
			            "<div class=\"row\" align=\"center\">phone:<input type=\"text\" id=\"new_phone\"></br></div>"+
			            "<div class=\"row\" align=\"center\">work:<input type=\"text\" id=\"new_work\"></br></div>"+
			            "<div class=\"row\" align=\"center\">start time<input type=\"text\" id=\"new_start\"></br></div>"+
			            "<div class=\"row\" align=\"center\">end time<input type=\"text\" id=\"new_end\"></br></div>"+
			            "<div class=\"row\" align=\"center\">relation:<select id=\"new_type_z\"></div>"+
			                    "<option value=\"1\">teacher</option>"+
			                    "<option value=\"2\">brother</option>"+
			                    "<option value=\"3\">student</option>"+
			                 "</select></div>"+
			            "<div class=\"row\" align=\"center\"><input type=\"button\" value=\"submit\" onclick=\"show()\"></div></form>" 
			            	});
			    });
			    function show()
			    {
			    	/*
			    	*首先进行未注册人的登记
			    	*/
			    	var url="insert_no_register_people";
			    	alert("final="+$('#final_people').val());
			    	$.post(url,{"final_people":$('#final_people').val(),"type":2,"name":$('#new_name').val(),"sex":$('#new_sex').val(),"phone":$('#new_phone').val(),"work":$('#new_work').val(),"user_id":$('#user_id').val(),"relation":$('#new_type_z').val(),"start_time":$('#new_start').val(),"end_time":$('#new_end').val()},function(data,status){
			    		document.getElementById('close').click();
			    	});
			        /*窗口关闭的函数*/
			    }
	</script>
	<script type="text/javascript">
	function shows(){
		 $("body").append("<div id='mask'></div>");
            $("#mask").addClass("mask").fadeIn("slow");
            $("#LoginBox").fadeIn("slow");

	}
	
	$("#loginbtn").hover(function () {
        $(this).stop().animate({
            opacity: '1'
        }, 600);
    }, function () {
        $(this).stop().animate({
            opacity: '0.8'
        }, 1000);
    });

$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#LoginBox").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });
	
	var canvas = document.getElementById('relation');
            var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
            //var scene = new JTopo.Scene(stage); // 创建一个场景对象

	function create(data1){
		var data=eval('('+data1+')');
	    var i=0;
	    var k=0;
	    var parent=data[0].parent_id;
	    stage.clear();
	    //var canvas = document.getElementById('relation');
	    //var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
	    var scene = new JTopo.Scene(stage); // 创建一个场景对象
	    scene.backgroundColor="#f1f1f1";
	    var userNode = new JTopo.Node(); // 创建一个节点
	    userNode.setBound(250, 200, 66, 66); // 同时设置大小及位置
	    userNode.showSelected = true; // 不显示选中矩形
	    userNode.setImage('1.png'); // 设置图片
	    userNode.id=parent;
	    userNode.click(function(event){
	    	$('#user_id').val(this.id);
	    });
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
	           		$('#user_id').val(this.id);
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
	            	$('#rela').val(data[this.ps].relation);
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
	<script>
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
		function addJson(data,name,sex,work,phone,start,end){
			for(var j=0;j<data.length;j++){
				if(data[j].name==name){
					return;
				}
			}
			data.push({
				name:name,
			 	sex:sex,
				work:work,
			 	phone:phone,
			 	start:start,
			 	end:end,
			});
		}
		//添加已有成员进行的提交，即填写后的日期节点的属性的提交
		function submit()
		{
			var s=$('#type_z').val();
	
			start=$('#start').val();
			end=$('#end').val();

			$('#start_time').val(start);
			$('#end_time').val(end);
			$('#type').val(s);
			
			 $("#LoginBox").fadeOut("fast");
	         $("#mask").css({ display: 'none' });
	         
	        addJson(data_brother,name,sex,work,phone,start,end);
			addHtml_brother();
			
			var url="insertRelation";

			$.post(url,{"final_people":$('#final_people').val(),"user_id":$('#user_id').val(),"user2_id":id,"relation":s,"start":start,"end":end},function(data,status){
				alert(status);
				showNew();		
			});
			
		}
		function showNew(){
			var urll="search";
                                $.post(urll,{"id":1,"search_type":100},function(data,status){
                                        var w=parseInt(screen.width);
                                        var c=document.getElementById("relation");    
                                    c.width=w*0.4;
                                        create(data);
			})
		}
		function drop_brother(){
			shows();
		}
		function addHtml_brother(){
			var text_html=''
				$("#u2_panel").html("");
				for(var i=0;i<data_brother.length;i++){
					//text_html+='<tr><td>'+data_brother[i].name+'</td><td>'+start+'</td>'+'<td>'+end+'</td>'+'<td>'+$('#type_z').val()+'</td>'+'<td><a onclick="reduce_brother(\'\'+'+i+',this)">删除</a></td></tr>';
				text_html+='<tr><td>'+data_brother[i].name+'</td><td>'+data_brother[i].start+'</td>'+'<td>'+data_brother[i].end+'</td>'+'<td>'+$('#type_z').val()+'</td>'+'<td><a onclick="reduce_brother(\'\'+'+i+',this)">删除</a></td></tr>';
				}
				$("#u2_panel").append(text_html);
		}	
		function reduce_brother(a,t){
			data_brother.splice(a,1);
			addHtml_brother();
		}		
	</script>
	
</html>
