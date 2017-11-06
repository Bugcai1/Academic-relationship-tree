<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML> 
<html> 
<head> 
<title>登录</title> 
<script type="text/javascript" src="jquery.js"></script>
<script src="json2.js"></script>
<style type="text/css"> 
body {/*设置背景*/
    margin-left:50px;
    background-image:url('imag.jpg');
    background-size: 100%,100%;
    overflow: scroll;
}
.wrap { 
    margin:300px auto; 
    width:380px; 
    overflow:scroll; 
    float:none;
    background-color:#000000;
} 
.loginForm{ 
    position:absolute; 
    z-index:0; 
    border-radius:20px; 
    height:200px; 
    width:380px;
    background: -webkit-gradient(linear, left top, left 24, from(#EEE), color-stop(4%, #FFF), to(#EEE)); 
    background: -moz-linear-gradient(top, #EEE, #FFF 1px, #EEE 24px); 
    background: -o-linear-gradient(top, #EEEEEE, #FFFFFF 1px, #EEEEEE 24px); 
} 
.loginForm:before { 
    content:''; 
    position:absolute; 
    z-index:-1; 
    border:1px dashed #CCC; 

    top:5px; 

    bottom:5px; 

    left:5px; 

    right:5px; 

    box-shadow: 0 0 0 1px #FFF; 

} 

.loginForm h1 { 

    text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5); 

    text-transform:uppercase; 

    text-align:center; 

    color:#666; 

    line-height:3em; 

    margin:16px 0 20px 0; 

    letter-spacing: 4px; 

    font:normal 26px/1 Microsoft YaHei, sans-serif; 

} 

fieldset { 

    border:none; 

    padding:10px 10px 0; 

} 

fieldset input[type=text] { 

    background:url(style/default/images/user.png) 4px 5px no-repeat; 

} 

fieldset input[type=password] { 

    background:url(style/default/images/password.png) 4px 5px no-repeat; 

} 

fieldset input[type=text], fieldset input[type=password] { 

    width:100%; 

    line-height:2em; 

    font-size:12px; 

    height:30px; 

    border:none; 

    padding:3px 4px 3px 2.2em; 

    width:300px; 

} 

fieldset input[type=submit] { 

    text-align:center; 

    padding:2px 20px; 

    line-height:2em; 

    border:1px solid #113DEE; 

    border-radius:3px; 

    background: -webkit-gradient(linear, left top, left 24, from(#113DEE), color-stop(0%, #0938F7), to(#113DEE)); 

    background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px); 

    background:-o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px); 

filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900'); 

    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')"; 

    height:30px; 

    cursor:pointer; 

    letter-spacing: 4px; 

    margin-left:10px; 

    color:#FFF; 

    font-weight:bold; 

} 

fieldset input[type=submit]:hover { 

    background: -webkit-gradient(linear, left top, left 24, from(#FF9800), color-stop(0%, #FF6900), to(#FF9800)); 

    background: -moz-linear-gradient(top, #FF9800, #FF6900 0, #FF9800 24px); 

    background:-o-linear-gradient(top, #FF6900, #FF6900 0, #FF9800 24px); 

filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800'); 

    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800')"; 

}

.inputWrap { 

    background: -webkit-gradient(linear, left top, left 24, from(#FFFFFF), color-stop(4%, #EEEEEE), to(#FFFFFF)); 

    background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px); 

    background: -o-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px); 

    border-radius:3px; 

    border:1px solid #CCC; 

    margin:10px 10px 0; 

filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF'); 

    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF')"; 

} 

fieldset input[type=checkbox] { 

    margin-left:10px; 

    vertical-align:middle; 

} 

fieldset a { 

    color:blue; 

    font-size:12px; 

    margin:6px 0 0 10px; 

    text-decoration:none; 

} 

fieldset a:hover { 

    text-decoration:underline; 

} 

fieldset span { 

    font-size:12px; 

} 

</style> 
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

          <a href="#">忘记密码</a> <a href="register">注册</a> <input type="button" value="登录" onclick="post()"> 

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
		alert(data);
		alert(status);
		if(parseInt(data)==1)
		{
			window.location.href="search.jsp?cc="+getval;
		}	
		else
			alert("您还没有注册，请您先注册");
	});	
}
</script>
</html>