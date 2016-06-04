<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/base64.js" type="text/javascript"></script>
<script src="js/jquery-1.12.4.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎参与信息系统安全课程</title>
</head>
<body>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/infosec"
     user="root"  password="123456"/>
<span><sec:authentication property="name" scope="session" var="name" /></span> <!-- spring security 获取当前认证用户名 -->
<sql:query dataSource="${snapshot}" var="result">
	SELECT id from user where username="${name}";
</sql:query>
<c:forEach var="row" items="${result.rows}">

<c:set value="${row.id}" var="id"></c:set>
<span id='userId'>${id}</span>
</c:forEach>
<script type="text/javascript">  
	$(function(){
		 var b = new Base64();  
		 var userIdVal = $('#userId').text();
		 if(userIdVal){
			 $('#userId').remove();
		 }
        var str = b.encode(userIdVal);
        document.getElementById("checkId").href='user/'+str;
         //alert("base64 encode:" + str);  
         str = b.decode(str);  
         //alert("base64 decode:" + str); 
	});    
        </script>  
<h1>信息系统安全-课程设计要求</h1>
<br>

<a href="<c:url value="/login.jsp" />">登陆</a>&nbsp;&nbsp;&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="<c:url value="/users" />">用户列表</a>&nbsp;&nbsp;&nbsp;
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
<!--<a href="<c:url value="/user/${str}" />">用户信息</a>&nbsp;&nbsp;&nbsp;-->
<a id="checkId">用户信息</a>&nbsp;&nbsp;&nbsp;
</sec:authorize>
<a href="<c:url value="/j_spring_security_logout"/>">退出</a>

</body>
</html>