<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>search</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="jquery.min.js"></script>
    <script src="search/jtopo-0.4.8-min.js"></script>
    <script type="text/javascript" src="home.min.js"></script>
    <link href="kowa.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="search.css" />
   
    
     <link rel="stylesheet" href="bootstrap.min.css">
     <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" type="text/css" href="dialog/common.css" />
    <link rel="stylesheet" type="text/css" href="dialog/modal.css" />
    
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <link rel="stylesheet" type="text/css" href="demo.css">
	<link rel="stylesheet" type="text/css" href="layer-animate.css">
 
    <link rel="stylesheet" href="demo/style.css">
    <script type="text/javascript" src="dialog/jquery.js"></script>
    <script type="text/javascript" src="dialog/moudel.js"></script> 
    <script src="demo/index.js"></script>
    <script type="text/javascript" src="jquery.easyui.min.js"></script>
    
    
<style type="text/css">
.div01{  position:absolute; overflow-y:scroll;}
.b,.c,.d{display:none;}
#body{background:url(bg1.jpg);;font-style:normal;font-size:20px;}
.buttons{border-bottom:solid #d1c8b8 4px;display:block;padding:8px;width:80px;-moz-border-radius: 1em 4em 1em 4em;border-radius: 1em 4em 1em 4em;text-align:center;margin:1px;background:#4b7975;text-decoration:none;color:#FFFFFF;float:left;font-family:Georgia, "Times New Roman", Times, serif;font-size:14px}
.buttonHover{background:#86b8b4;border-bottom:solid #FF0000 4px;}
a.buttons:hover{background:#86b8b4;border-bottom:solid #FF0000 4px;}
#body{font-size:13px;text-align:justify;overflow:hidden;color:#fff;padding:20px;-moz-border-radius: 1em 4em 1em 4em;border-radius: 1em 4em 1em 4em;	height:550px;width:100%;margin:10px 0 0 0;}


label {
	width:100%;
	height:100%;
	
}

.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
<script type="text/javascript">
</script>
<script type="text/javascript">
$(document).ready(function(){
	navigate_tabs('a','first-tab');
	$("#first-tab").addClass('buttonHover');
});

function navigate_tabs(container, tab){
	$(".b").css('display' , 'none');
	$(".c").css('display' , 'none');
	$(".e").css('display','none');
	$(".a").css('display' , 'none');
	
	$("#first-tab").removeClass('buttonHover');
	$("#second-tab").removeClass('buttonHover');
	$("#third-tab").removeClass('buttonHover');
	$("#four-tab").removeClass('buttonHover');
	
	
	$("#"+tab).addClass('buttonHover');
	$("."+container).show('slow');
}
</script>
</head>
<div>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="menu-nav">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            
            	<li>&nbsp;&nbsp;</li>
             	 <li>&nbsp;&nbsp;</li>
             	 <li>&nbsp;&nbsp;</li>
             	  <li>&nbsp;&nbsp;</li>
             	 <li></li>
             	 <li>&nbsp;&nbsp;</li>
                <li class=" one"><i class="ui-center"></i><a href="login.jsp">用户</a></li>
                <li class="two"><i class="ui-home"></i><a href="#" onclick="postlog()">我的主页</a></li>
                <li class="three"><i class="ui-friends"></i><a href="allUser.jsp">好友</a></li>
                <li class="five"><i class="ui-dress"></i><a href="#" >装扮</a></li>
                <li class="eight">&nbsp;&nbsp;用&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;<img id="user_img" style="border-radius:20px;" height="40px" width="40px" src="" alt=""></li>
             	<li class="four"><i class="ui-apply"></i><a href="#" onclick="right_show()">应用</a></li>
         </ul>             
        </div>
    </div>
</nav>
<div ><a href="#" onclick="right_show()">应用</a></div>
</div>
           	
<body>
<div id="left1"><canvas height="600" id="relation"></canvas>

                      
 <input type="hidden" id="par_id">
 <input type="hidden" id="chi_id">
 <input type="hidden" id="add_user_id"><!-- 被添加得人得id -->
 <input type="hidden" id="add_user"><!-- 主动填机啊得人 -->
 

</div>
<div id="right1" >
		<a href="javascript:navigate_tabs('a','first-tab');" class="buttons" id="first-tab">查询</a>  
		<a href="javascript:navigate_tabs('b','second-tab');" class="buttons" id="second-tab">修改</a>
		<a href="javascript:navigate_tabs('c','third-tab');" class="buttons" id="third-tab">添加</a>
		<a href="javascript:navigate_tabs('e','four-tab');" class="buttons" id="four-tab">合并</a>
		<br style="clear:both;" />
		<div id="body">
			<div class="a">
				
        			<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
            			<div style="margin-bottom:20px">
                			<input id="user_id" name="user_id" class="easyui-textbox" name="" style="width:80%" data-options="label:'账户:'">
            			</div>
            			<div style="margin-bottom:20px">
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 			<input type="checkbox"  name = "searchType" value = "1">teacher
                			<input type="checkbox"  name = "searchType" value= "2" >brother
              				 <input type="checkbox"  name = "searchType" value= "3" >student
            			</div>
            
            <div style="margin-bottom:20px">
                <input id="start_time" class="easyui-datebox" label="开始时间:" labelPosition="left" style="width:80%;">
            </div>
            <div style="margin-bottom:20px">
               <input id="end_time" class="easyui-datebox" label="截止时间:" labelPosition="left" style="width:80%;">
            </div>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">Submit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
        </div>
    
	</div>
	<div class="b">
		<table align="center" border="1" id = "table1">
        <tr>
            <td>No.1</td>
            <td><input id="par" style="border:none;background:#919b9d"></td>
        </tr>
        <tr>
            <td>No.2</td>
            <td><input id="chi" style="border:none;background:#919b9d"></td>
        </tr>
        <tr>
            <td>relation</td>
            <td><select id="relation_1" style="border-style: none;background:#919b9d"><option value="1">teacher</option><option value="2">brother</option><option value="3">student</option></select></td>
        </tr>
        <tr>
            <td>start time</td>
            <td><input id="start_time1" type="text" class="easyui-datebox" style="width:160px;border:0px;background-color:#919b9d"></td>
        </tr>
        <tr>
            <td>end time</td>
            <td><input id="end_time1" type="text" class="easyui-datebox" style="width:160px;border:0px;background-color:#919b9d"></td>
        </tr>
    </table>
    <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="changeRelation()" style="width:80px">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="deleteRelation()" style="width:80px">删除</a>
        </div>
	</div>
	
	
	<div class="c" style="height:350px;">
	 <div id="" style="width:300px">
	 	
	 	<div id="add_p">
	 	
          <div class="cont_ba_opcitiy">
            <button class="btn_login" onClick="cambiar_login()">LOGIN</button>
          </div>
      
      
      <div class="cont_forms" >
        <div  class="cont_form_login"> <a href="#" onClick="ocultar_login_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
          <h2>LOGIN</h2> 
          <div id="div2"></div>
      <form enctype="multipart/form-data" id="uploadForm">
       <a style="margin-top:10px;" href="javascript:;" class="file">选择文件<input class="" type="file" name="uploadFile" id="upload_file" onchange="c()" style="margin-bottom:10px;"></a>
      </form>
      <input id="friend_name" placeholder="姓名" required type="text">
      <select id="friend_sex"><option>--请选择性别--</option><option value="1">男</option><option value="2">女</option></select>
      <input id="friend_phone" class="item" placeholder="手机号" required type="text">
      <input id="friend_work" class="item" placeholder="工作" required type="text">
      <button class="btn_login" onClick="upload_friend_picture()">LOGIN</button>
      <button class="btn_login" onClick="ocultar_login_sign_up()()">EXIT</button>
       </div>
      </div>
	 </div>
        <table border="1" id = "table2" align="center">
        <tr>
            <td>name</td>
            <td><input id="add_name" style="border:none"></td>
        </tr>
        <tr>
            <td>relation</td>
            <td><select id="add_relation" style="border-style: none"><option value="0">--选择关系--</option><option value="1">teacher</option><option value="2">brother</option><option value="3">student</option></select></td>
        </tr>
        <tr>
            <td>start time</td>
            <td><input id="add_start_time" type="text" class="easyui-datebox" style="width:150px;"></td>
        </tr>
        <tr>
            <td>end time</td>
            <td><input id="add_end_time" type="text" class="easyui-datebox" style="width:150px;"></td>
        </tr>
        <tr>
        <td></td>
            <td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitAddForm()" style="width:80px">OK!</a>
            </td>
        </tr>
    </table>
    </div>
    
    
             <div id="bottom" class="content div01" style="margin-top:5px;width:30%;height:250px;">
                <ul id="da-thumbs" class="da-thumbs">
                
                </ul>
            </div>
	
	</div>
	<div class="e">
		  <input id="merge_ID1" name="" class="easyui-textbox" name="" style="width:50%" data-options="label:'ID1:'">
       
    <br>
        <input id="merge_ID2" name="" class="easyui-textbox" name="" style="width:50%" data-options="label:'ID2:'">
   
           <br> 
        <a align="center" href="javascript:void(0)" class="easyui-linkbutton" onclick="merge()" style="width:80px">Merge</a>
	</div>
	</div>
   
  
</div>
<input type="hidden" id="par_id">
<input type="hidden" id="chi_id">
<div  style="display:none" id="diag" class="key-word"><a href="javascript:;" id="add-key2" data-title="The error information"></a></div>
<!-- 对话框的隐藏界面，界面上不显示 -->
</body>
<script type="text/javascript">
	var log_people;
	function postlog(){	
		window.location.href="log.jsp?cc="+log_people;
	}
	
	function c () {
		$('#div2').find('img').remove();
		var r= new FileReader();
		f=document.getElementById('upload_file').files[0];
		r.readAsDataURL(f);
		r.onload=function  (e) {
        	var text="<img src="+this.result+" height=\"70px\" width=\"70px\" style=\"border-radius:25px;\">";
        	$('#div2').append(text);	
		};
	}
</script>
<script type="text/javascript">
	function submitAddForm()
	{
		alert("jjk");
		var user_id=$('#add_user').val();
		var user2_id=$('#add_user_id').val();
		var final_people=$('#user_id').val();
		
		var start=$('#add_start_time').textbox('getValue');
		var end=$('#add_end_time').textbox('getValue');
		var relation=$('#add_relation').val();
		if(start=="")
			start="00/01/0000";
		if(end=="")
			end="99/98/9999"
		
		if(user_id=="")
		{
			$.messager.confirm('Confirm',"请选择添加的节点",function(r){
				if (r){
				}
				return;
			});
			return;
		}
		if(parseInt(relation)==0)
		{
			$.messager.confirm('Confirm',"请您选择关系！",function(r){
				if (r){
				}
				return;
			});
			return;
		}
		
		$.messager.confirm('Confirm','您正在向账号为：'+user_id+"的用户添加节点！",function(r){
			if (r){
			    var fdd = new FormData();
			    fdd.append('user_id', user_id);
			    fdd.append('user2_id',user2_id);
			    fdd.append('final_people',final_people);
			    fdd.append('start',start);
			    fdd.append('end',end);
			    fdd.append('relation',relation);
			    $.ajax({
			        url:"insertRelation",
			        type:"post",
			        // Form数据
			        data: fdd,
			        cache: false,
			        contentType: false,
			        processData: false,
			        success:function(data){
			        	search();
			        	
			        }
			    });
			}
		});
	}
</script>
<script type="text/javascript">
function right_show()
{
	if(parseInt($('#left1').width())>screen.width*0.9)
		zhankai();
	else
		hides();
			
}
	function zhankai(){
		$('#right1').show(1000);
		$('#left1').width(screen.width*0.67);
	}
	function hides()
	{
		$('#right1').hide(1000);
		$('#left1').width(screen.width);
	}
</script>
<script type="text/javascript"> 

function merge(){ 
    //此过程完成与后台的交互
    var id1=$('#merge_ID1').val();
    var id2=$('#merge_ID2').val();
    
    var url='merge_one';
    $.post(url,{"ID1":id1,"ID2":id2},function(data,status){
       
        if(parseInt(data)==0)//代表成功，直接进行查询操作
        {
          
            //再次调用search函数，应该使当前的用户状态为id1,并且查询所有类型
            $('#ff').form('load',{
                user_id:id1
            });
            var names = document.getElementsByName("searchType");
            var type = 0;
            for(var i=0;i<names.length;i++){  
                names[i].checked=true;
            }
            search();
        }
        else if(parseInt(data)==1)
        {
            alert("用户不存在");   
        }
        else if(parseInt(data)==2)//应该提示用户不能合并
        {
            alert("两者没有交集，不能合并");
        }
        else{   //不成功时打印错误队列
        //中间进行村务信息的处理
        
        $('#add-key2').click();
        loadGrid();
        loaddata(data);
        }
    });//加载数据，str为表格中的数据，应该是json格式的字符
}
$("#add-key2").click(function(){
    $("#add-key2").createModal({
        background: "blue",//设定弹窗之后的覆盖层的颜色
        width: "1000px",//设定弹窗的宽度
        height: "500px",//设定弹窗的高度
        resizable: true,//设定弹窗是否可以拖动改变大小
        bgClose: true,
        html:"<div><table id=\"dg\" data-options=\"toolbar:toolbar\"></table></div>"});
});
var toolbar = [{
    text:'Save',
    iconCls:'icon-save',
    handler:function(){submitt();}
}];
</script>

<script>
	//这个是控制新加人物的弹出层的一个script区
	var friendID;
    function upload_friend_picture(){
    	var f_name=$("#friend_name").val();
        var fd = new FormData();
        fd.append('id', parseInt(friendID));
        fd.append('name',$("#friend_name").val());
	fd.append('sex',$("#friend_sex").val());
        fd.append('work',$("#friend_work").val());
	fd.append('phone',$("#friend_phone").val());
	
	var pic = $('#upload_file')[0].files[0];
    var fdd = new FormData();
    fdd.append('uploadFile', pic);
    fdd.append('ID',friendID); 
    $.ajax({
        url:"no_register_picture",
        type:"post",
        // Form数据
        data: fdd,
        cache: false,
        contentType: false,
        processData: false,
        success:function(data){
        	
        	
        	$.ajax({
                url:"insert_no",
                type:"post",
                // Form数据
                data: fd,
                cache: false,
                contentType: false,
                processData: false,
                success:function(data){
                    ocultar_login_sign_up();
                    reload_friend();
                    $("#add_name").val(f_name);
                    $("#add_user_id").val(friendID);
                }
            });   
        	
        	
        	
        }
    });              
    
}


    function reload_friend(){
	var url="lookup";
        $.post(url,{"name":"bao"},function(data){
            var num=data.length;
            var text="";
            for(var i=0;i<num;i++)
            {
             text=text+"<li style=\"margin-top:5px\">"+
                  "<a target=\"_blank\"> <img src="+"\"UserPicture/"+data[i].id+".jpeg\""+"height=\"70px\" width=\"70px\" draggable=\"true\">"+
                  "<div class=\"hot_info\">"+
                      "<p>姓名："+data[i].name+"</p>"+
              
                      "<input type=\"button\" value=\"添加\" onclick=\"drag(this)\">"+
                      
                      "<input type=\"hidden\" id=\"inp_name\" value="+data[i].name+">"+
                      "<input type=\"hidden\" id=\"inp_sex\" value="+data[i].sex+">"+
                      "<input type=\"hidden\" id=\"inp_work\" value="+data[i].work+">"+
                      "<input type=\"hidden\" id=\"inp_phone\" value="+data[i].phone+">"+
                      "<input type=\"hidden\" id=\"pid\" value="+data[i].id+">"+   
                    "</div>"+
                    "</a>"+
                  "</li>";
            }
            
		$("#da-thumbs").empty();
            $("#da-thumbs").append(text);
            
            var jsElem = document.createElement('script');
            jsElem.src='home.min.js';
            document.getElementsByTagName('head')[0].appendChild(jsElem);
            },'json');
	}
</script>

<script>
function submitt()
{
    var datas=$('#dg').datagrid('getData');
    var url="merge_two";
    
    datas=JSON.stringify(datas);
  
    $.post(url,{"ID1":$('#merge_ID1').val(),"ID2":$('#merge_ID2').val(),"amendQue":datas},function(){
        document.getElementById('close').click();//关闭窗口
        search();
    });
}
function loaddata(d){
   
    $('#dg').datagrid('loadData',JSON.parse(d));
}
        function loadGrid(){
       // $(function(){
    $('#dg').datagrid({
                title:'sure the relation',
                iconCls:'icon-edit',
                width:990,
                height:250,
               
                columns:[[
                    {field:'user_1',title:'name',width:180,editor:'text'},
                    {field:'user_2',title:'name',width:180,editor:'text'},
                    {field:'type',title:'type',width:180,editor:'text'},
                    {field:'start_time',title:'start_time',width:180,editor:'text'},
                    {field:'end_time',title:'end_time',width:180,editor:'text'},
                    {field:'status',title:'status',width:180,editor:{type:'checkbox',options:{on:'P',off:''}}}
                   
                ]],
               
                
                
            });
    $('#dg').datagrid().datagrid('enableCellEditing');
        }
        $.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
                    var ed = $(this).datagrid('getEditor', param);
                    if (ed){
                        if ($(ed.target).hasClass('textbox-f')){
                            $(ed.target).textbox('textbox').focus();
                        } else {
                            $(ed.target).focus();
                        }
                    }
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			},
            enableCellEditing: function(jq){
                return jq.each(function(){
                    var dg = $(this);
                    var opts = dg.datagrid('options');
                    opts.oldOnClickCell = opts.onClickCell;
                    opts.onClickCell = function(index, field){
                        if (opts.editIndex != undefined){
                            if (dg.datagrid('validateRow', opts.editIndex)){
                                dg.datagrid('endEdit', opts.editIndex);
                                opts.editIndex = undefined;
                            } else {
                                return;
                            }
                        }
                        dg.datagrid('selectRow', index).datagrid('editCell', {
                            index: index,
                            field: field
                        });
                        opts.editIndex = index;
                        opts.oldOnClickCell.call(this, index, field);
                    }
                });
            }
		});
    </script>


<script type="text/javascript">
/*画人物的关系图*/
</script>
<script type="text/javascript">//页面加载的时候加载候选人
var canvas = document.getElementById('relation');
var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var nodes=[];
var links=[];
    window.onload=function(){
    	hides();
        var thisURL = document.URL;  
        var  getval =thisURL.split('?')[1];  
        var showval= getval.split("=")[1];
        showval=parseInt(showval);
        $('#ff').form('load',{
            user_id:showval
        });
        
        log_people=showval;
        $('#user_img').attr('src',"UserPicture/"+showval+".jpeg");
        
        
        
       
        var url="lookup";
        $.post(url,{"name":"bao"},function(data){
            var num=data.length;
            var text="";
            for(var i=0;i<num;i++)
            {
             text=text+"<li style=\"margin-top:5px\">"+
                  "<a target=\"_blank\"> <img src="+"\"UserPicture/"+data[i].id+".jpeg\""+"height=\"70px\" width=\"70px\" draggable=\"true\">"+
                  "<div class=\"hot_info\">"+
                      "<p>姓名："+data[i].name+"</p>"+
              
                      "<input type=\"button\" value=\"添加\" onclick=\"drag(this)\">"+
                      
                      "<input type=\"hidden\" id=\"inp_name\" value="+data[i].name+">"+
                      "<input type=\"hidden\" id=\"inp_sex\" value="+data[i].sex+">"+
                      "<input type=\"hidden\" id=\"inp_work\" value="+data[i].work+">"+
                      "<input type=\"hidden\" id=\"inp_phone\" value="+data[i].phone+">"+
                      "<input type=\"hidden\" id=\"pid\" value="+data[i].id+">"+   
                    "</div>"+
                    "</a>"+
                  "</li>";
            }
            
            $("#da-thumbs").append(text);
            
            var jsElem = document.createElement('script');
            jsElem.src='home.min.js';
            document.getElementsByTagName('head')[0].appendChild(jsElem);
            
            
            },'json');
        
        $("#start_time").textbox('setValue',"")
    	$("#end_time").textbox('setValue',"");
        var names = document.getElementsByName("searchType");
        var type = 0;
        for(var i=0;i<names.length;i++){  
            names[i].checked=true;
        }
        var url="search";
        
        $.post(url,{"flaag":1,"Id":$('#user_id').val(),"relationType":type,"beginTime":start,"endTime":end},function(data,status){
        	 var dataa=eval('('+data+')');
        	$('.eight').append('&nbsp;&nbsp;'+dataa[0].parent_name);
        });
        search();
    };
    
    function search()
    {
    	nodes=[];
    	links=[];
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
        if(type == "000"||$('#user_id').val()==''){
        	$.messager.confirm('Confirm','查询条件有误，请确认！',function(r){
    			if (r){
    				return;
    			}
    		});
        }
        if($('#start_time').datetimebox('getValue')==''){
            start="00/00/0000";
        }
        else
            start=$('#start_time').val();
        if($('#end_time').datetimebox('getValue')==''){
            end="99/99/9999";
        }
        else
            end=$("#end_time").val();
        var endt=end.split("/");
        var startt=start.split("/");
        if((parseInt(startt[2])+parseInt(startt[0])+parseInt(startt[1]))>=(parseInt(endt[2])+parseInt(endt[0])+parseInt(endt[1])))
        {
        	$.messager.confirm('Confirm','查寻时间有错，请更改！',function(r){
    			if (r){
    				return;
    			}
    		});
        	return;
        }
        var url="search";
        $.post(url,{"flaag":0,"Id":$('#user_id').val(),"relationType":type,"beginTime":start,"endTime":end},function(data,status){
            canvas.width=screen.width*0.69;
            create(data);
        });
    }
    function create(data1){
    	
    	
        stage.clear();
        var scene = new JTopo.Scene();
        stage.add(scene);
        var data=eval('('+data1+')');
        var num=data.length;
       
        if(num==0)
        {
        	var i=0;
        	var url="search";
        	$.post(url,{"flaag":1,"Id":$('#user_id').val()},function(data,status){
        		var dataa=eval('('+data+')');
                 var x = Math.ceil(Math.random() * 600);
                 var y = Math.ceil(Math.random() * 400);
                 var node = new JTopo.Node();
                 node.radius =40;
                 node.setSize(80,80);
                 node.id=dataa[i].parent_id;
                 node.name=dataa[i].parent_name;
                 node.fillColor = JTopo.util.randomColor();
                 node.setImage("UserPicture/"+dataa[0].parent_id+".jpeg");
                 node.setLocation(x, y);
                 node.click(function(){
                 	$('#add_user').val(this.id);
                     
                 });
                 node.mouseover(function(){
                     this.text = this.name;
                 });
                 node.mouseout(function(){
                     this.text = null;
                 });
                 scene.add(node);
                 nodes.push(node);
             });
        	
        }
         
        for (var i=0;i<num;i++)
        {
            if(isHave(parseInt(data[i].parent_id)))
            {
            	
                var x = Math.ceil(Math.random() * 600);
                var y = Math.ceil(Math.random() * 400);
                var node = new JTopo.Node();
                node.setSize(50,50);
                if(parseInt(data[i].parent_id)==parseInt($("#user_id").val()))
            	{
            		node.setSize(80,80);	
            	}
                node.radius = 40;
                node.id=data[i].parent_id;
                node.name=data[i].parent_name;
                node.fillColor = JTopo.util.randomColor();
                node.setImage("UserPicture/"+data[i].parent_id+".jpeg");
                node.setLocation(x, y);
                node.click(function(){
                	$('#add_user').val(this.id);
                    
                });
                node.mouseover(function(){
                    this.text = this.name;
                });
                node.mouseout(function(){
                    this.text = null;
                });
                scene.add(node);
                nodes.push(node);
            }
            if(isHave(data[i].child_id))
            {
                var x = Math.ceil(Math.random() * 600);
                var y = Math.ceil(Math.random() * 400);
                var node = new JTopo.Node();
               
                node.fillColor = JTopo.util.randomColor();
                node.setImage("UserPicture/"+data[i].child_id+".jpeg");
                node.radius = 25;
                node.setSize(50,50);
                if(parseInt(data[i].child_id)==parseInt($("#user_id").val()))
            	{
            		node.setSize(80,80);	
            	}
                node.setLocation(x, y);  
                node.id=data[i].child_id;
                node.name=data[i].child_name;
                node.click(function(){
                	$('#add_user').val(this.id);
                });
                node.mouseover(function(){
                    this.text = this.name;
                });
                node.mouseout(function(){
                    this.text = null;
                });
                scene.add(node);
                
                nodes.push(node);
            }
        }
        
        var effect = JTopo.Effect.spring({
            minLength: 200 // 节点之间最短距离
        });
        for(var i=0;i<num;i++)
        {
            var num1=Ldex(data[i].parent_id);
            var num2=Ldex(data[i].child_id);
            var link = new JTopo.Link(nodes[num1], nodes[num2],data[i].relation);
            
            link.strokeColor = JTopo.util.randomColor();
            link.lineWidth = Math.ceil(15 * Math.random()) + 1;
            link.ps=i;
            link.click(function(){
            	$("#par").val(data[this.ps].parent_name);
            	$("#chi").val(data[this.ps].child_name);
            	$("#relation_1").val(data[this.ps].relation);
            	$("#start_time1").textbox('setValue',data[this.ps].start_time)
            	$("#end_time1").textbox('setValue',data[this.ps].end_time);
            	$("#par_id").val(data[this.ps].parent_id);
            	$("#chi_id").val(data[this.ps].child_id);
            });
            scene.add(link);
            links.push(link);
            effect.addNode(nodes[num1], nodes[num2]);
            effect.addNode(nodes[num2], nodes[num1]);
        }    
        effect.play();
    }
    function isHave(id)
    {
        for(var j=0;j<nodes.length;j++)
        {
            if(parseInt(id)==parseInt(nodes[j].id))
            {
                
                return false;
            }
        }
        return true;
    }
    function Ldex(num)
    {
        var result=-1;
        for(var i=0;i<nodes.length;i++)
        {
            if(parseInt(nodes[i].id)==parseInt(num))
            {   
                return i;
            }
        }
        return result;
    }
    function drag(t){
        
           name=$(t).siblings("#inp_name").val();
           sex=$(t).siblings("#inp_sex").val();
           work=$(t).siblings("#inp_work").val();
            phone=$(t).siblings("#inp_phone").val();
           id=$(t).siblings("#pid").val();
           $("#add_user_id").val(id);
           addJson(data_brother,name,sex,work,phone,start,end,id);
           addHtml_brother();
        }
</script>
<script type="text/javascript">
    function submitForm()
    {
        search();
    }
    
    function clearForm(){
        $('#ff').form('clear');
    }
</script>

<script type="text/javascript">//删除操作
    function deleteRelation()
    {
       var url = "deleteRelation";
       var ID1=$('#par_id').val();
       var ID2=$('#chi_id').val();
       
       $.post(url,{"user_id":$('#user_id').val(),"ID1":ID1,"ID2":ID2},function(data,status){
            search();
       });
    }
    function changeRelation()
    {
        var url="changeRelation";
     
        var start1=$("#start_time1").textbox('getValue');
        var end1=$("#end_time1").textbox('getValue');
        
        $.post(url,{"user_id":$("#user_id").val(),"ID1":$("#par_id").val(),"ID2":$("#chi_id").val(),"par_name":$("#par").val(),"chi_name":$("#chi").val(),"relation":$("#relation_1").val(),"start":start1,"end":end1},function(data,status){
        	search(); 
                        
        });
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
   
/*将添加的人的信息加入数组中，检擦时使用*/
function addJson(data,name,sex,work,phone,start,end,id){
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


</html>