<%@ page language="java" contentType="text/html; "
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="css/style.css">
  <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <SCRIPT src="js/jquery-1.12.4.min.js" type="text/javascript"></SCRIPT>
  <SCRIPT src="js/jQuery.md5.js" type="text/javascript"></SCRIPT>
  
  
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>用户注册</h1>
      <form method="post" action="register">
        <p><input type="text" name="username" value="" placeholder="用户名"></p>
        <p><input type="password" name="password1" id="password1"  placeholder="密码"></p>
        <p><input type="hidden" name="password" id="password"></p>
        <p><input type="text" name="showname" value="" placeholder=呢称></p>
        <p><input type="text" name="description" value="" placeholder=简介></p>
        <p><input type="text" name="question" value="" placeholder="提示问题"></p>
        <p><input type="text" name="answer" value="" placeholder="问题答案"></p>
        <p class="submit"><input type="submit" name="commit" value="确认"></p>
      </form>
      
      <script>
$(function(){
  $("form").submit(function() {
	alert("submitted");
    var v=$.md5($("#password1").val());
    $("#password").val(v);
    $("#password1").attr("disabled", "true");
    return true;
  });
});
</script>

    </div>
  </section>
</html>