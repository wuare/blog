<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>友链</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/admin/link/comment.css"/>
	<script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
	    		
	    	});
	</script>
  </head>
  
  <body>
  	<%@include file="/page/admin/menu.jsp" %>
    <div class="all-list">
        <div class="list-top">
            <span>友链</span>
        </div>
        <div class="list-info">
            <span><a>全部</a>(${linkNum })&nbsp;|&nbsp;<a>已添加</a>(${linkNum })</span>
        </div>
         <div class="list-search">
            <form action="${pageContext.request.contextPath}/admin/addlink" method="post">
                <button type="submit" id="search-button">添加友链</button>
		        <input type="text" name="href" class="search-name" placeholder="站点"/>
		        <input type="text" name="name" class="search-name" placeholder="名称"/>

            </form>
        </div>
        <!-- 分页按钮 -->
        <div class="list-page">
            <div class="page">
                <a class="page-foward3" href="#"><i class="icon icon-forward3"></i></a>
                <a class="page-last"><i class="icon icon-last"></i></a>
                <form action="" method="post">
                    <span class="currentPage">&nbsp;第<input type="text" name="pc" value="1"/>页，共1页</span>
                    <input type="submit" value="submit" style="display: none;"/>
                </form>
                <a class="page-first"><i class="icon icon-first"></i></a>
                <a class="page-backward2" href="#"><i class="icon icon-backward2"></i></a>
            </div>
        </div>
        <!--表格数据-->
        <table id="table" cellspacing="0">
            <tr id="tr-head">
            	<td class="td-0"></td>
            	<td class="td-1">友链</td>
                <td class="td-2">站点</td>
                <td class="td-3">操作</td>

            </tr>
            <c:forEach items="${linkList}" var="link">
            <tr class="tr-main">
            	<td class="td-0"><input name="checkbox" type="checkbox" value=""/></td>
                <td>${link.name }</td>
                <td>${link.href }</td>
                <td><a onclick="return confirm('确定要删除该友链？')" href="${pageContext.request.contextPath}/admin/deletelink?id=${link.id}">删除</a></td>
            </tr>
            </c:forEach>
            <tr>
            	<td class="td-0"></td>
            	<td class="td-1">友链</td>
                <td class="td-2">站点</td>
                <td class="td-3">操作</td>
            </tr>
        </table>
         <!-- 分页按钮 -->
        <div class="list-page">
            <div class="page">
                <a class="page-foward3" href="#"><i class="icon icon-forward3"></i></a>
                <a class="page-last"><i class="icon icon-last"></i></a>
                <form action="" method="post">
                    <span class="currentPage">&nbsp;第<input type="text" name="pc" value="1"/>页，共1页</span>
                    <input type="submit" value="submit" style="display: none;"/>
                </form>
                <a class="page-first"><i class="icon icon-first"></i></a>
                <a class="page-backward2" href="#"><i class="icon icon-backward2"></i></a>
            </div>
        </div>
        <div id="footer">

        </div>
    </div>
  </body>
</html>
