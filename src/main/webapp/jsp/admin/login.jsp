<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
    <style type="text/css">
    	table{
    		margin-top:12%;
    	}
    </style>
  </head>
  <body>
  	<form action="${pageContext.request.contextPath }/a/loginvalidate" method="post">
    <table align="center">
    	<tr>
    		<td></td>
    		<td>${msg }</td>
    	</tr>
    	<tr>
    		<td>用户名</td>
    		<td><input type="text" name="username"/></td>
    	</tr>
    	<tr>
    		<td>密&nbsp;码</td>
    		<td><input type="password" name="password"/></td>
    	</tr>
    	<tr>
    		<td></td>
    		<td><button type="submit">登录</button></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
