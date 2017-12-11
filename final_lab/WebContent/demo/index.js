
function cambiar_login() {
	$('#div2').find('img').remove();
	var url="getid";
	$.post(url,{"name":"bao"},function(data,status){
		$("add_user_id").val(data);
		friendID=data;
	});
	
  document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";  
  document.querySelector('.cont_form_login').style.display = "block";               

  setTimeout(function(){  document.querySelector('.cont_form_login').style.opacity = "1"; },400);  
  
setTimeout(function(){    
document.querySelector('.cont_form_sign_up').style.display = "none";
},200);  
  }



function ocultar_login_sign_up() {

document.querySelector('.cont_forms').className = "cont_forms";  
             
document.querySelector('.cont_form_login').style.opacity = "0"; 

setTimeout(function(){

document.querySelector('.cont_form_login').style.display = "none";
},500);  
  
  }