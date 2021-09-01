<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>关于自己</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/about.css"/>
    
  </head>
  
  <body>
    <!-- logo -->
    <div class="main-top-logo">
        <span class="logo-first"><a href="${pageContext.request.contextPath }/index">XJIE</a></span><span class="logo-second"><a href="${pageContext.request.contextPath }/index">星空大海爱我所爱</a></span>
    </div>
    <!-- 导航栏 -->
    <div class="navbar">
        <div class="navbar-header">
            <ul>
                <li><a href="${pageContext.request.contextPath }/index">首页</a></li>
                <li><a href="${pageContext.request.contextPath }/index?cid=1">生活笔记</a></li>
                <li><a href="${pageContext.request.contextPath }/index?cid=2">技术分享</a></li>
                <li><a href="${pageContext.request.contextPath }/index?cid=3">福利专区</a></li>
                <li><a href="${pageContext.request.contextPath }/about">关于自己</a></li>
                <li><a href="${pageContext.request.contextPath }/message">留言板</a></li>
            </ul>
        </div>
    </div>
    <!-- 公告内容，登录注册按钮 -->
    <div class="navbar-footer">
        <strong class="icon-high"><i class="icon icon-volume-high"></i></strong>
        &nbsp;您好，欢迎光临xjie博客，欢迎大家交流学习
        <div class="navbar-footer-right">
           <c:choose>
        	<c:when test="${empty sessionScope.user}">
            	<strong class="icon-login"><i class="icon icon-user"><a href="${pageContext.request.contextPath }/login">&nbsp;登录</a></i></strong>&nbsp;&nbsp;&nbsp;
            	<strong class="icon-register"><i class="icon icon-mobile"><a href="${pageContext.request.contextPath }/regist">&nbsp;注册</a></i></strong>
        	</c:when>
        	<c:otherwise>
        		<strong class="icon-login"><i class="icon icon-user"><a href="">&nbsp;${sessionScope.user.username }</a></i></strong>&nbsp;&nbsp;&nbsp;
            	<strong class="icon-register"><i class="icon icon-mobile"><a href="${pageContext.request.contextPath }/logout">&nbsp;注销</a></i></strong>
        	</c:otherwise>
        </c:choose>
        </div>
    </div>


<!-- 留言 -->
    <div class="about-main">
        <div class="about-header">
            <span>关于自己</span>
        </div>
        <div class="about">
            <span class="span-1">个人简介</span>
            <div class="div-img"><img src="${pageContext.request.contextPath}/static/images/about.jpg"/></div>
            <span class="span-2">吴鑫杰</span>
            <p>爱生活，爱学习，爱编程。</p>
            <p>如果你曾歌颂黎明，那么也请你拥抱黑夜。</p>
            <div class="div-a">
                <a href="${pageContext.request.contextPath }/index">XJIE博客</a>
            </div>
        </div>

</div>

    <!-- 页面底部 -->
    <div class="main-footer">
        <div id="footer"></div>
        <div id="copyright">Copyright © 2013-2016 XJIE博客 保留所有权利</div>
    </div>
  </body>
</html>
