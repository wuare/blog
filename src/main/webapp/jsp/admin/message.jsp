<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>留言</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/admin/comment/comment.css"/>
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
            <span>评论</span>
        </div>
        <div class="list-info">
            <span><a>全部</a>(${pb.tr })&nbsp;|&nbsp;<a>已批准</a>(${pb.tr })&nbsp;|&nbsp;<a>待审</a>(8)</span>
        </div>
        <div class="list-search">
            <form action="" method="post">
                <button type="button" id="search-button">搜索评论</button>
                <input type="text" name="content" id="search-name"/>

            </form>
        </div>
        <!-- 分页按钮 -->
        <div class="list-page">
            <div class="page">
                <a class="page-foward3" href="#"><i class="icon icon-forward3"></i></a>
                <c:if test="${pb.pc<pb.tp}">
                <a class="page-last" href="${pageContext.request.contextPath }/admin/messagelist?pc=${pb.pc+1}"><i class="icon icon-last"></i></a>
                </c:if>
                <c:if test="${pb.pc==pb.tp}">
                <a class="page-last"><i class="icon icon-last"></i></a>
                </c:if>
                <form action="${pageContext.request.contextPath }/admin/messagelist" method="post">
                    <span class="currentPage">&nbsp;第<input type="text" name="pc" value="${pb.pc }"/>页，共${pb.tp }页</span>
                    <input type="submit" value="submit" style="display: none;"/>
                </form>
                <c:if test="${pb.pc>1}">
                <a class="page-first" href="${pageContext.request.contextPath }/admin/messagelist?pc=${pb.pc-1}"><i class="icon icon-first"></i></a>
                </c:if>
                <c:if test="${pb.pc==1}">
                <a class="page-first"><i class="icon icon-first"></i></a>
                </c:if>
                <a class="page-backward2" href="#"><i class="icon icon-backward2"></i></a>
            </div>
        </div>
        <!--表格数据-->
        <table id="table" cellspacing="0">
            <tr id="tr-head">
            	<td class="td-0"></td>
                <td class="td-1">作者</td>
                <td class="td-2">留言内容</td>
                <td class="td-3">提交于</td>
                <td class="td-4">操作</td>

            </tr>
            <c:forEach items="${pb.messageList}" var="message">
            <tr class="tr-main">
            	<td class="td-0"><a class="td-0-a">留言</a></td>
                <td>${message.username }</td>
                <td>${message.content }</td>
                <td><fmt:formatDate value="${message.date }" pattern="yyyy年M月d日 HH:mm:SS"/></td>
                <td><a onclick="return confirm('确定要删除该留言？')" href="${pageContext.request.contextPath}/admin/deletemessage?id=${message.id}">删除</a></td>
            </tr>
            <c:forEach items="${message.replyList}" var="reply">
            <tr class="children">
            	<td class="td-0"></td>
                <td>${reply.username }</td>
                <td><span style="color:#0073aa;">回复：
                	<c:if test="${empty reply.pname}">
	                ${message.username}
	                </c:if>
                	<c:if test="${not empty reply.pname}">
	                ${reply.pname}
	                </c:if>
	                &nbsp;</span>${reply.content }</td>
                <td><fmt:formatDate value="${reply.date }" pattern="yyyy年M月d日 HH:mm:SS"/></td>
                <td><a onclick="return confirm('确定要删除该回复？')" href="${pageContext.request.contextPath}/admin/deletemreply?id=${reply.id}">删除</a></td>
            </tr>
            </c:forEach>
            </c:forEach>
            <tr>
            	<td class="td-0"></td>
                <td class="td-1">作者</td>
                <td class="td-2">留言内容</td>
                <td class="td-3">提交于</td>
                <td class="td-4">操作</td>
            </tr>
        </table>
        <!-- 分页按钮 -->
        <div class="list-page">
            <div class="page">
                <a class="page-foward3" href="#"><i class="icon icon-forward3"></i></a>
                <c:if test="${pb.pc<pb.tp}">
                <a class="page-last" href="${pageContext.request.contextPath }/admin/messagelist?pc=${pb.pc+1}"><i class="icon icon-last"></i></a>
                </c:if>
                <c:if test="${pb.pc==pb.tp}">
                <a class="page-last"><i class="icon icon-last"></i></a>
                </c:if>
                <form action="${pageContext.request.contextPath }/admin/messagelist" method="post">
                    <span class="currentPage">&nbsp;第<input type="text" name="pc" value="${pb.pc }"/>页，共${pb.tp }页</span>
                    <input type="submit" value="submit" style="display: none;"/>
                </form>
                <c:if test="${pb.pc>1}">
                <a class="page-first" href="${pageContext.request.contextPath }/admin/messagelist?pc=${pb.pc-1}"><i class="icon icon-first"></i></a>
                </c:if>
                <c:if test="${pb.pc==1}">
                <a class="page-first"><i class="icon icon-first"></i></a>
                </c:if>
                <a class="page-backward2" href="#"><i class="icon icon-backward2"></i></a>
            </div>
        </div>
        <div id="footer">

        </div>
    </div>
  </body>
</html>
