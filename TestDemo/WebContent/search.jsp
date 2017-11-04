<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询界面</title>
	<script type="text/javascript" src="jquery-2.1.4.js"></script>
	 <script type="text/javascript" src="json.js"></script>
	 <script type="text/javascript" src="json_parse.js"></script>
    <script type="text/javascript" src="cycle.js"></script>
    <script type="text/javascript" src="json_parse_state.js"></script>
<script src="jtopo-0.4.8-min.js"></script>
	<style type="text/css">
		#main{
			width: 500px;
			height: 300px;
			
		}
	</style>
</head>
<body>
	<div id="main">
		<form action="#">
			查询者的id<input type="text" name="id" id="user_id"/></br>
			查询类型<input type="text" name="search_type" id="search_t"/>
		</br>
			<input type="button" value="提交了" onclick="search()" />
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	function search()
	{
		var url="search";
		alert("jjj");
		var data=new Object();
		data.id=$('#user_id').val();
		data.search_type=$('#search_t').val();
		alert(data.search_type);
		$.post(url,{"id":$('#user_id').val(),"search_type":$('#search_t').val()},function(data,status){
			alert("ooo");
			//create(data);
		});
	}
	
	function create(data){
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
        userNode.id=5;
       userNode.click(function(event){
                alert(this.id);
                });
        scene.add(userNode); // 放入到场景中

        var num=data.length,jr=true,py=true,ts=true;
        var otherNode_z=new Array(num);

        while(i<num)
        {
            var temp=data[i].parent_id;
            while(parseInt(parent)==parseInt(temp))
            {
                var otherNode = new JTopo.Node(); // 创建一个节点
                otherNode.setBound(100+data[i].x, 100+data[i].y,50, 50); // 同时设置大小及位置
                otherNode.borderRadius = 50; // 圆角
                otherNode.setImage('2.png', false); // 设置图片
                otherNode.showSelected = false; // 不显示选中矩形
                scene.add(otherNode); // 放入到场景中
                otherNode.url='#';
                otherNode.id=data[i].child_id;
                otherNode.click(function(event){
                window.location.href=event.target.url;
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