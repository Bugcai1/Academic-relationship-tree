<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>上传头像</title>
<link rel="stylesheet" href="style.css"/>
</head>


<body>

<script type="text/javascript" src="jquery.min.js"></script> 

<script type="text/javascript" src="cropbox.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var url="getid";
		$.post(url,{"name":"bao"},function(data,status){
			$("#user_id").val(data);
		});
	};
</script>

<div class="container">

  <div class="imageBox">

    <div class="thumbBox"></div>

    <div class="spinner" style="display: none"></div>

  </div>

  <div class="action"> 

    <div class="new-contentarea tc"> 

    <a href="javascript:void(0)" class="upload-img">

      <label for="upload-file">请先选择图片...</label>

      </a>
      <form action="nuploadPictrue" enctype="multipart/form-data" method="post">
      <input type="file" class="" name="uploadFile" id="upload-file" />
      <input type="hidden" name="ID" value="" id="user_id"/>
      <input type="submit" id="sub" style="display:none"></input>
      </form>
    
	</div>
    <input id="btn" type="button"   class="Btnsty_peyton" value="OK">

    <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >

    <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >

  </div>

  <div class="cropped"></div>

</div>

<script type="text/javascript">
$("#btn").click(function(){
	$('#sub').click();
	
	
});
 

$(window).load(function() {
	var options =

	{

		thumbBox: '.thumbBox',

		spinner: '.spinner',

		imgSrc: ''

	}

	var cropper = $('.imageBox').cropbox(options);

	var img="";

	$('#upload-file').on('change', function(){

		var reader = new FileReader();

		reader.onload = function(e) {

			options.imgSrc = e.target.result;

			cropper = $('.imageBox').cropbox(options);

			getImg();

		}

		reader.readAsDataURL(this.files[0]);

		this.files = [];

		//getImg();

	})

	$('#btnCrop').on('click', function(){

		alert("图片上传喽");

	})

	function getImg(){

		img = cropper.getDataURL();

		$('.cropped').html('');

		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');

		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');

		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');

		}

		

	$(".imageBox").on("mouseup",function(){

 		getImg();

  		});

		

		

	$('#btnZoomIn').on('click', function(){

		cropper.zoomIn();

	})

	$('#btnZoomOut').on('click', function(){

		cropper.zoomOut();

	})

});

</script>

</body>

</html>