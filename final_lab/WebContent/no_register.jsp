<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="register/style.css" media="screen">
<script type="text/javascript" src="register/jquery.js"></script>
<script type="text/javascript" src="register/index.js"></script>
<title>用户注册</title>
</head>
<body>
    <section class="container">
	<form action="insert" method="post">
	<fieldset class="alpha">
		<legend><b>1. </b>上传头像</legend>
		<div>
			<img id="user_img" style="margin-left:40%;border-radius:25px"  alt="" width="50px" height="50px">
		</div>
		<div style="margin-top:10px" class="frow">
			<a href="nopicture.jsp"><input id="" class="item" type="button" value="上传头像"></a>
		</div>
		
		<div style="margin-top:10px" class="frow">
			<input id="user_id" class="item" type="text" disabled=true>
		</div>
		
		<div class="frow">
			<a class="next-step" href="#">下一步</a>
		</div>
		
	</fieldset>
	<fieldset class="beta">
		<legend><b>2. </b>个人信息</legend>
		<div class="frow">
			<input id="uname" class="item" placeholder="用户名" required type="text">
		</div>
		<div class="frow">
			<select id="sex"><option>--请选择性别--</option><option value="1">男</option><option value="2">女</option></select>
		</div>
		<div class="frow">
			<input id="phone" class="item" placeholder="手机号" required type="text">
		</div>
		<div class="frow">
			<input id="work" class="item" placeholder="工作" required type="text">
		</div>
		<div class="frow">
			<a class="prev-step" href="#">上一步</a>
		</div>
		<div class="frow">
			<input class="submit" type="submit" value="完成" onclick="submitl()">
		</div>
	</fieldset>
</form>
</section>

<script type="text/javascript">
var id="0";
var user_id="0";

	window.onload=function(){
		
		var thisURL = document.URL;  
	    var  getval =thisURL.split('?')[1];  
	    var showval= getval.split("=")[1];
	    user_id=parseInt(showval);
	    
	    
		var form=document.forms[0];
		form.onsubmit=function(){
			return false;
		};
		var url="getid";
		$.post(url,{"name":"bao"},function(data,status){
			id=data;
			$("#user_id").val("您朋友的账号是："+data);
		});
		
		
	};

</script>

<script type="text/javascript">//提交表单的操作

function submitl(){
	var sex=$("#sex").val();
	var wsex=""
	if(parseInt(sex)==1)
		wsex="男";
	else if(parseInt(sex)==2)
		wsex="女";
	else{
		alert("请选择性别！！");
		return;
	}
	var url="insert_no_register_people";
	$.post(url,{"id":parseInt(id),"name":$("#uname").val(),"sex":wsex,"work":$("#work").val(),"phone":$("#phone").val()},function(data,status){
		window.location.href="search.jsp?cc="+user_id+"?flag=0"+"?name="+$("#uname").val()+"?id="+parseInt(id);
	});
}
</script>
</body>
</html>

