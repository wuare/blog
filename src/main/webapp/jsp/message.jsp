<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery-1.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/message.css"/>
    <title>留言</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
    <script>
        $(function() {
            $(".stay").hover(function(){
                $(this).add(".warn").css("opacity","1");
            },function(){
                $(".warn").css("opacity","0");
            })
            $(".stay-div").children(".write-word").hide();
            
            $(".reply-a").toggle(function(){
	    				$(this).closest("div.stay").next(".write-word").slideDown();
		    		},
		    		function(){
	    				$(this).closest("div.stay").next(".write-word").slideUp();
	    		});
        });
        //回复顶功能
	        $(function(){
	        	$(".ding-a").click(function(){
					var replyid = $(this).attr("name");
					var that = $(this);
					$.ajax({
						url:"${pageContext.request.contextPath}/ajaxReplyLike",
						data:{id:replyid},
						type:"POST",
						dataType:"json",
						async:true,
						cache:false,
						success:function(data){
							that.children(".ding").text(data);
						}
					});
				});
	        });
        //留言顶功能
	        $(function(){
	        	$(".message-a").click(function(){
					var messageid = $(this).attr("name");
					var that = $(this);
					$.ajax({
						url:"${pageContext.request.contextPath}/ajaxMessageLike",
						data:{id:messageid},
						type:"POST",
						dataType:"json",
						async:true,
						cache:false,
						success:function(data){
							that.children(".message-ding").text(data);
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
                <li><a href="${pageContext.request.contextPath}/about"">关于自己</a></li>
                <li><a href="${pageContext.request.contextPath}/message"">留言板</a></li>
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
    <div class="stay-main">
        <div class="stay-header">
            <span>给我留言</span>
        </div>
        <div class="stay-top"><span>3条留言</span></div>
        <div class="stay-div">
        <!-- 每一条留言，留言条目 -->
        <c:forEach items="${listMessage}" var="message">
        <!-- 留言的回复 -->
        <c:forEach items="${message.replyList}" var="reply">
        	<div class="stay">
            <img src="${pageContext.request.contextPath }${reply.userUrl}" alt="用户头像" />
            <div class="stay-content">
                <div class="stay-username">${reply.username}</div>
                <div class="stay-text">
              		<c:if test="${empty reply.pid}">
                    <span style="color:#d32;">回复：${message.username}</span>
                    </c:if>
                    <c:if test="${not empty reply.pid}">
                    <span style="color:#d32;">回复：${reply.pname}</span>
                    </c:if>
                &nbsp;${reply.content}
                </div>
                <p><fmt:formatDate value="${reply.date}" pattern="M月d日"/>&nbsp;<a class="reply-a" href=""><i class="icon icon-undo2"></i>&nbsp;回复</a>&nbsp;&nbsp;
                    <a name="${reply.id}" class="ding-a"><i class="icon icon-heart"></i>&nbsp;顶(<span class="ding">${reply.likeNum}</span>)</a>&nbsp;&nbsp;
                    <a href="#" class="warn"><i class="icon icon-file-text"></i>&nbsp;举报</a>
                </p>
            </div>
        </div>
        <div class="write-word">
					    <c:if test="${empty sessionScope.user.userUrl}">
					        <img src="${pageContext.request.contextPath }/static/images/20150525112112.jpg"/>
					    </c:if>
					    <c:if test="${not empty sessionScope.user.userUrl}">
					        <img src="${pageContext.request.contextPath }${sessionScope.user.userUrl}"/>
					    </c:if>
					        <form action="${pageContext.request.contextPath}/user/msgretore?pid=${reply.id}&mid=${message.id}" method="post">
					            <textarea placeholder="说点什么吧..." title="写评论" class="textarea" name="content" ></textarea>
					            <div class="submit-word">
					                <button type="submit" class="button">发布</button>
					            </div>
					        </form>
				
				    </div>
        </c:forEach>
        
        <!-- end -->
        <div class="stay">
            <img src="${pageContext.request.contextPath }${message.userUrl}" alt="用户头像" />
            <div class="stay-content">
                <div class="stay-username">${message.username}</div>
                <div class="stay-text"><pre>${message.content}</pre>
                </div>
                <p><fmt:formatDate value="${message.date}" pattern="M月d日"/>&nbsp;<a class="reply-a" href=""><i class="icon icon-undo2"></i>&nbsp;回复</a>&nbsp;&nbsp;
                    <a name="${message.id}" class="message-a"><i class="icon icon-heart"></i>&nbsp;顶(<span class="message-ding">${message.likeNum}</span>)</a>&nbsp;&nbsp;
                    <a href="#" class="warn"><i class="icon icon-file-text"></i>&nbsp;举报</a>
                </p>
            </div>
        </div>
        <div class="write-word">
					    <c:if test="${empty sessionScope.user.userUrl}">
					        <img src="${pageContext.request.contextPath }/static/images/20150525112112.jpg"/>
					    </c:if>
					    <c:if test="${not empty sessionScope.user.userUrl}">
					        <img src="${pageContext.request.contextPath }${sessionScope.user.userUrl}"/>
					    </c:if>
					        <form action="${pageContext.request.contextPath}/user/msgretome?mid=${message.id}" method="post">
					            <textarea placeholder="说点什么吧..." title="写评论" class="textarea" name="content" ></textarea>
					            <div class="submit-word">
					                <button type="submit" class="button">发布</button>
					            </div>
					        </form>
				
				    </div>
		</c:forEach>
	</div>




    <!-- 写留言的文本框 -->
    <div class="write-word">
        <c:if test="${empty sessionScope.user.userUrl}">
	        <img src="${pageContext.request.contextPath }/static/images/20150525112112.jpg"/>
	    </c:if>
	    <c:if test="${not empty sessionScope.user.userUrl}">
	        <img src="${pageContext.request.contextPath }${sessionScope.user.userUrl}"/>
	    </c:if>
        <form action="${pageContext.request.contextPath}/user/writeMessage" method="post">
            <textarea placeholder="说点什么吧..." title="留言板" class="textarea" name="content"></textarea>
            <div class="submit-word">
                <button type="submit" class="button">发布</button>
            </div>
        </form>

    </div>


</div>

    <!-- 页面底部 -->
    <div class="main-footer">
        <div id="footer"></div>
        <div id="copyright">Copyright © 2013-2016 XJIE博客 保留所有权利</div>
    </div>
  </body>
</html>
