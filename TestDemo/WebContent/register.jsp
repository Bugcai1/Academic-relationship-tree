<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>注册界面</title>
        <script src="register/jquery.js"></script>
        <script src="register/jquery.cityselect.js"></script>
        <link href="register/main.css" type="text/css" rel="stylesheet">
        <link href="register/easyui.css" type="text/css" rel="stylesheet" />
        <link href="register/icon.css" type="text/css" rel="stylesheet" />
        <link href="register/demo.css" type="text/css" rel="stylesheet" />
        <link href="register/validatebox.css"  type="text/css" rel="stylesheet" />
        <script src="register/easyui-lang-zh_CN.js"></script>
        <script src="register/datagrid-detailview.js"></script>
         <script src="register/jquery.min.js"></script>
        <script src="register/jquery.easyui.min.js"></script>
       <script type="text/javascript" src="register/json2.js"></script> 
        <style type="text/css">
            body{
                background: white;
            }
            .jieshao{
                width: 300px;
                border-bottom: 2px dashed;
            }
            .div0{
                width: 400px;
                border: 1px solid cornflowerblue;
                line-height: 30px;
                padding-left: 50px;
                margin: auto;
                margin-top: 50px;
            }
            .zi{
                font-size: 13px;
            }
        </style>
        <script>
            $(function(){
                init_city_select($("#sel1, #sel2"));
            });
            function getValue() {
                alert($('#sel1').val());
            }
        </script>
        <script>
        	window.onload=function(){
        		var url="getid";
        		$.post(url,function(data){
        			alert(data);
        			$('#user_id').val(data);
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
    <div align="center" margin-top="50px">
        <h2>个人信息资料</h2>
    </div>
     <div class="div0" align="center">
        <form action="insert" method="post">
            <span style="padding-left: 33px;">帐号：<input type="text" name="id" id="user_id" value="" /></span></br>
            真实姓名：<input type="text" name="name" id="ps"  /><br/>
            <span style="padding-left: 33px;">所在地 : <input name="" id="sel1"  type="text"  class="city_input" readonly="readonly"></span>
            <br/>
            <span style="padding-left: 33px;">生日：<input id="" name="" class="easyui-datebox" height="10px" data-options="formatter:myformatter,parser:myparser"></input></span><br/>
            工作：<input type="text" name="work"><br />

            <span style="padding-left: 36px;">QQ：<input type="text" name="wd" id="wd" value="" /></span><br />

            <span style="padding-left: 28px;">EMS：<input type="text" name="wd" id="wd" value="" /></span><br />
            <span style="padding-left: 30px;">介绍：</span>
            <div class="jieshao" >
            <textarea  name="" rows="10" cols="20" style="resize: none;  width: 300px;height: 100px; "> 
            </textarea>
            </div>
            <p class="zi">以下信息可作为通过客服取回账号的依据</p>
            性别  : <select name="sex">
                <option>man</option>
                <option>woman</option>
            </select><br />

           手机号 : <input type="text" name="phone" id="wd" value="" /><br />

            <input type="submit" name="" id="" value="保存" />
        </form>
        </div>
    </body>
</html>