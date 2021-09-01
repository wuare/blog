<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户头像</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/admin/userimg.css"/>
	<script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
	    		
	    	});
	</script>
	<style type="text/css">
		button{
			padding: 1px 20px;
			cursor: pointer;
		}
	</style>
  </head>
  <body>
  	<%@include file="/page/admin/menu.jsp" %>
    <div class="all-list">
        <div class="list-top">
            <span>用户头像资料</span>
        </div>
        <div class="list-info">
            <span><a>全部</a>(${num})</span>
            <br/>
            <br/>
            <form action="${pageContext.request.contextPath}/admin/adduserimg" method="post" enctype="multipart/form-data">
            	<span><a>添加图片</a></span>&nbsp;&nbsp;&nbsp;<input type="file" name="img"/>
            	<button type="submit">上传</button>
            </form>
            <br/>
        </div>
        <div class="list-img">
        <c:forEach items="${imgList}" var="img">
            <img src="${pageContext.request.contextPath}${img.url}"/>
        </c:forEach>
        </div>
        
        <div id="footer">

        </div>
    </div>
  </body>
</html>
