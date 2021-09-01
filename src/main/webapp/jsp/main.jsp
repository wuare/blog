<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>xjie的个人博客</title>
	<link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/main.css"/>
	<script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".icon-like a").click(function(){
				var aid = $(this).attr("name");
				var that = $(this);
				$.ajax({
					url:"${pageContext.request.contextPath}/ajaxLikeNum",
					data:{id:aid},
					type:"POST",
					dataType:"json",
					async:true,
					cache:false,
					success:function(data){
						that.children(".span-a").text(data);
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
    <!-- logo -->
    <div class="main-top-logo">
        <span class="logo-first"><a href="${pageContext.request.contextPath}/index">XJIE</a></span><span class="logo-second"><a href="${pageContext.request.contextPath}/index">星空大海爱我所爱</a></span>
    </div>
    <!-- 导航栏 -->
    <div class="navbar">
        <div class="navbar-header">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/index?cid=1">生活笔记</a></li>
                <li><a href="${pageContext.request.contextPath}/index?cid=2">技术分享</a></li>
                <li><a href="${pageContext.request.contextPath}/index?cid=3">福利专区</a></li>
                <li><a href="${pageContext.request.contextPath}/about">关于自己</a></li>
                <li><a href="${pageContext.request.contextPath}/message">留言板</a></li>
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
        		<strong class="icon-login"><i class="icon icon-user"><a href="${pageContext.request.contextPath }/user/person">&nbsp;${sessionScope.user.username }</a></i></strong>&nbsp;&nbsp;&nbsp;
            	<strong class="icon-register"><i class="icon icon-mobile"><a href="${pageContext.request.contextPath }/logout">&nbsp;注销</a></i></strong>
        	</c:otherwise>
        </c:choose>
        </div>
    </div>
    <!-- 页面主体 -->
    <div class="main-body">
        <!-- 页面左栏，文章 -->
        <div class="body-left">
        <c:forEach items="${pb.beanList}" var="article">
            <div class="body-left-article">
                <div class="article-top">
                    <a href="${pageContext.request.contextPath }/index?cid=${article.cid}" class="article-tag">${article.cateName }</a>
                    <a href="${pageContext.request.contextPath }/content?id=${article.id}" class="article-title" style="text-overflow:ellipsis;">
                    	${article.title }
                    </a>
                </div>
                <div class="article-main">
                    <div class="article-div">
                        <a href="${pageContext.request.contextPath }/content?id=${article.id}"><img src="${pageContext.request.contextPath }${article.imgUrl}" alt="图片展示"></a>
                    </div>
                    <div class="article-summary" style="text-overflow:ellipsis;">${article.summary }</div>
                </div>
                <p class="article-footer">
                    <span class="icon-author"><i class="icon icon-user"></i><a href="#">&nbsp;&nbsp;${article.author }</a></span>
                    &nbsp;&nbsp;&nbsp;
                    <i class="icon icon-clock"></i>&nbsp;&nbsp;<span class="icon-pubtime"><fmt:formatDate value="${article.date }" pattern="yyyy-MM-dd"/></span>
                    &nbsp;&nbsp;&nbsp;
                    <i class="icon icon-eye"></i>&nbsp;&nbsp;<span class="icon-browse">${article.lookNum }浏览</span>
                    &nbsp;&nbsp;&nbsp;
                    <i class="icon icon-bubbles4"></i>&nbsp;&nbsp;<span class="icon-comment"><a href="#">${article.cNum }评论</a></span>
                    &nbsp;&nbsp;&nbsp;
                    <i class="icon icon-heart"></i>&nbsp;&nbsp;<span class="icon-like"><a name="${article.id}"><span class="span-a">${article.likeNum }</span>喜欢</a></span>
                </p>
            </div>

		</c:forEach>

            
        </div>
        <!-- 页面右栏，推荐部分相关 -->
        <div class="body-right">
                <!-- 文章归档 -->
                <div class="right-file">
                    <span>文章归档</span><hr/>
					<c:forEach items="${pb.cateDate}" var="itemDate">
                        <a href="#">${itemDate }</a>
					</c:forEach>
                </div>
                <!-- 猜你喜欢 -->
                <div class="right-like">
                    <span>猜你喜欢</span><hr/>
                    <c:forEach items="${pb.hotList}" var="like">
                    <div class="like-article">
                        <p><a href="${pageContext.request.contextPath }/content?id=${like.id}"><img src="${pageContext.request.contextPath }${like.imgUrl}" alt="猜你喜欢图片加载"/></a></p>
                        <div class="like-article-title" style="text-overflow: ellipsis;"><a href="${pageContext.request.contextPath }/content?id=${like.id}">${like.title }</a></div>
                        <div class="like-article-info"><fmt:formatDate value="${like.date }" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;384评论</div>
                    </div>
                    </c:forEach>
                </div>
            <!-- 标签云 -->
            <div class="right-tag">
                <span>标签云</span><hr/>
                <div class="right-tag-content">
                <c:forEach items="${pb.tagList}" var="tag">
                        <a href="${pageContext.request.contextPath}/index?tid=${tag.id}" class="li-${tag.num }">&nbsp;${tag.name }(${tag.anum})</a>
                </c:forEach>       
                </div>
            </div>
            <!-- 友情链接 -->
            <div class="right-link">
                <span>友情链接</span><hr/>
                <ul>
                <c:forEach items="${linkList}" var="link">
	                <a href="http://${link.href }" target="_blank"><li class="link-info">${link.name }</li></a>
	            </c:forEach>
                </ul>
            </div>
            </div>
        </div>
        <%@include file="/page/pager.jsp" %>
    <!-- 页面底部 -->
    <div class="main-footer">
        <div id="footer"></div>
        <div id="copyright">Copyright © 2013-2016 XJIE博客 保留所有权利</div>
    </div>
</body>
</html>
