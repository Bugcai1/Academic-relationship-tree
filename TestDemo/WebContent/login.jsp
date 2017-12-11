<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>sign in and sign up</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Internship Sign In & Sign Up Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
<link href="snow.css" rel="stylesheet" type="text/css" media="all" />
<link href="login_style.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- web font -->
<!--<link href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">-->

<!-- //web font -->
</head>
<body>
<div class="snow-container">
			  <div class="snow foreground"></div>
			  <div class="snow foreground layered"></div>
			  <div class="snow middleground"></div>
			  <div class="snow middleground layered"></div>
			  <div class="snow background"></div>
			  <div class="snow background layered"></div>
			</div>

<div class="top-buttons-agileinfo">
<h1>系统登陆注册入口
</h1>
<div class="main-agileits">
<!--form-stars-here-->
		<div class="form-w3-agile">
			<h2 class="sub-agileits-w3layouts">登录</h2>
			<form>
					<input type="text" name="Eamil" placeholder="手机号" />
					<input type="text" id="password" name="password" placeholder="账号"/>
					
				<div class="submit-w3l">
					  <input id="button1" type="button"  value="登录" onclick="post()">
				</div>
				<p class="p-bottom-w3ls"><a href="register.jsp">点击注册</a>如果你没有一个帐户。
.</p>
			</form>
		</div>
		</div>
<!--//form-ends-here-->
<!-- copyright -->
	<div class="copyright w3-agile">
		<p> © 2017</a></p>
	</div>
	<!-- //copyright --> 
	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript">
		function post()
		{
			var  getval =$('#password').val();
			var url="login";
			$.post(url,{"password":getval},function(data,status){
				if(parseInt(data)==1)//成功跳转到查询页面
				{
					window.location.href="search.jsp?cc="+getval;
				}	
				else
					alert("您还没有注册，请您先注册");//提示用户注册
				});	
		}
	</script>

</body>
</html>