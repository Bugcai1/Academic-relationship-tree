<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML> 
<html> 
<head> 
<title>登录</title> 
<script type="text/javascript" src="login/jquery.js"></script>
<script src="login/json2.js"></script>
<link rel="stylesheet" href="login/login.css">
</head> 


<body> 

<div class="wrap"> 

  <form action="login" method="post"> 

    <section class="loginForm"> 

      <header></header> 

      <div class="loginForm_content"> 

        <fieldset> 

          <div class="inputWrap"> 

            <input type="text" name="userName" placeholder="请输入手机号" autofocus required> 

          </div> 

          <div class="inputWrap"> 

            <input type="password" id="password" name="password" placeholder="请输入账号" required> 

          </div> 

        </fieldset> 

        <fieldset> 

         <input type="checkbox" checked="checked"> 

          <span>记住密码</span> 

          <a href="#">忘记密码</a> <a href="register.jsp">注册</a> <input type="button" value="登录" onclick="post()"> 

        </fieldset> 

      </div> 

    </section> 
  </form> 
</div> 
</body> 
<script>
<!--可以添加检查机制-->
function post()
{
	var  getval =$('#password').val();
	var url="login";
	$.post(url,{"password":getval},function(data,status){
		if(parseInt(data)==1)//成功跳转到查询页面
		{
			window.location.href="search.html?cc="+getval;
		}	
		else
			alert("您还没有注册，请您先注册");//提示用户注册
	});	
}
</script>
</html>