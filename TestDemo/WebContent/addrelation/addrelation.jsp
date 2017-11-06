<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		 <link href="easyui.css" rel="stylesheet" />
    <link href="icon.css" rel="stylesheet" />
    <link href="demo.css" rel="stylesheet" />
    <link href="validatebox.css" rel="stylesheet" />
    <script src="jquery.min.js"></script>
    <script src="jquery.easyui.min.js"></script>
    <script src="easyui-lang-zh_CN.js"></script>
    <script src="datagrid-detailview.js"></script>
    <script src="jtopo-0.4.8-min.js"></script>
		<script type="text/javascript" src="jquery-2.1.4.js"></script>
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
			background:red;
		}
		#left2{
			height:500px;
			width:23%;
			margin-right:1%;
			float:left;
			background:red;
		}
		#left3{
			height:500px;
			width:25%;
			float:left;
			background:pink;
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
				$.post(urll,{"id":1,"search_type":1},function(data,status){
					var w=parseInt(screen.width);
					var c=document.getElementById("relation");    
				    c.width=w*0.35;
				    alert(c.width);
					create(data);
				});
			}
		</script>
		<script type="text/javascript">
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
    </script>
	</head>
	<body>	
	<div id="addo" class="easyui-window" style="width:400px;height:300px;padding:10px 20px" closed="false">
		<form id="fm" method="post">
			<div class="fitem a" align="right">
				<label >姓名:</label>
				<input id="name1" name="name" class="validatebox" required="true">
			</div>
			<div class="fitem a" align="right">
				<label>工作:</label>
				<input id="work1" name="work" class="validatebox" required="true">
			</div>
			<div class="fitem a" align="right">
				<label>手机号:</label>
				<input id="phone1" name="phone" class="validatebox" required="true">
			</div>
			<div class="fitem a" align="right">
				<label>性别:</label>
				<input id="sex1" name="sex" class="validatebox" required="true">
			</div>
			<div class="fitem a" align="right">
				<label>start:</label>
				<input id="start" name="start" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input>
				<!--<input id="pud" name="publishDate" class="easyui-validatebox" required="true">-->
			</div>
			<div class="fitem a" align="right">
				<label>start:</label>
				<input id="start" name="start" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input>
				<!--<input id="pud" name="publishDate" class="easyui-validatebox" required="true">-->
			</div>
			<div align="right">
				<a href="#"  class="easyui-linkbutton" iconCls="icon-ok" onclick="">保存</a>
				<a href="#"  class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addo').dialog('close')">Cancel</a>
			</div>
		</form>
	</div>
	
	
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
	<div id="div_brother" ondrop="drop_brother()" ondragover="allowDrop(event)">
				<div class="div_panel">
					<table border="0" cellspacing="0" cellpadding="0">
						<caption><h2>添加的师兄弟</h2></caption>
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
		<div >
		<input type="text" id="user_id" value=""/>
		<input type="button" value="show" onclick="shows()">
		</div>
		
		
	</body>
	
	<script type="text/javascript">
	function shows(){
		var s=$('#user_id').val();
		alert(s);
		$('').show();


		
	}
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
	            otherNode.showSelected = false; // 不显示选中矩形
	            scene.add(otherNode); // 放入到场景中
	            otherNode.url='#';
	            otherNode.id=data[i].child_id;
	           	otherNode.click(function(event){
	           		alert(this.id);
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
	<script>
		var name="";
		var work="";
		var sex="";
		var phone="";
		var id="";
		var data_brother=[];
		function allowDrop(e){
			e.preventDefault();
		}
		/*点击图片时就执行*/
		function drag(t){
			name=$(t).siblings("#inp_name").val();
			/*sex=$(t).siblings("#inp_sex").val();
			work=$(t).siblings("#inp_work").val();
			phone=$(t).siblings("#inp_phone").val();*/
			id=$(t).siblings("#pid").val();
		}
		function addJson(data,name,sex,work,phone){
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
			});
		}

		function showdata(){
			alert("data"+data_brother);
			for(var i=0;i<data_brother;i++)
			{	
			}
		}
		function addHtml_brother(){
			var text_html=''
				var totalMoney=0;
				$("#u2_panel").html("");
				for(var i=0;i<data_brother.length;i++){
					text_html+='<tr><td>'+data_brother[i].name+'</td><td>'+data_brother[i].start+'</td>'+'<td>'+data_brother[i].end+'</td>'+'<td><a onclick="reduce_brother(\'\'+'+i+',this)">删除</a></td></tr>';
				}
				$("#u2_panel").append(text_html);
		}	
		function drop_brother(){
			
			alert("hhh");
			addJson(data_brother,name,sex,work,phone);
			addHtml_brother();
		}
		function reduce_brother(a,t){
			data_brother.splice(a,1);
			addHtml_brother();
		}
		
	</script>
	
</html>