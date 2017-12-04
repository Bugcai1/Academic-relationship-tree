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
<title>头像上传</title>
</head>
<body>
    <section class="container">
	<form action="insert" method="post">
	<fieldset class="alpha">
		<legend><b>1. </b>上传头像</legend>
		<div>
			<img id="user_img" style="margin-left:40%;border-radius:25px" alt="" width="50px" height="50px">
		</div>
		<div style="margin-top:10px" class="frow">
			<a href="uploadPicture.jsp"><input id="" class="item" type="button" value="上传头像"></a>
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

	<fieldset class="charlie">
		<legend><b>3. </b>完成</legend>
		<div class="frow">
		
			<p>您的信息已经填写完整，谢谢！</p>
			<a id="aa" href="">前往自己的关系树</a>
		</div>
	</fieldset>
</form>
</section>

<script type="text/javascript">
var id="0";
	window.onload=function(){
		var url="getid";
		$.post(url,{"name":"bao"},function(data,status){
			$("#user_id").val("您的账号是："+data);
			id=data; 
			ad="search.html?cc="+id;
			$("#aa").attr('href',ad);
			$('#user_img').attr('src',"UserPicture/"+id+".jpeg");
		});
		
	};

</script>

<script type="text/javascript">//提交表单的操作

function submitl(){
	
	var sex=$("#sex").val();
	var wsex=""
	if(parseInt(sex)==1)
		wsex="man";
	else if(parseInt(sex)==2)
		wsex="woman";
	else{
		alert("请选择性别！！");
		return;
	}
	var url="insert";
	$.post(url,{"id":parseInt(id),"name":$("#uname").val(),"sex":wsex,"work":$("#work").val(),"phone":$("#phone").val()},function(data,status){
	});
}
</script>
</body>
</html>

