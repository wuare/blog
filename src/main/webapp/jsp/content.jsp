<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${pb.article.title}</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/content.css"/>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css"/>
	<script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
	    <script>
	    	$(function(){
	    		$(".content-text p").attr("style","");
	    		$(".comment-div").children(".write-word").hide();

	    		$(".reply-a").toggle(function(){
	    				$(this).closest("div.comment").next(".write-word").slideDown();
		    		},
		    		function(){
	    				$(this).closest("div.comment").next(".write-word").slideUp();
	    		});
	    		
	    		 
	    	});
	    	
	        $(function() {
	            $(".comment").hover(function(){
	                $(this).add(".warn").css("opacity","1");
	            },function(){
	                $(".warn").css("opacity","0");
	            })
	            
	           
	        });
	        
	        //点赞喜欢功能
	        $(function(){
	        	$("#like").click(function(){
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
	        //评论顶功能
	        $(function(){
	        	$(".comment-a").click(function(){
					var commentid = $(this).attr("name");
					var that = $(this);
					$.ajax({
						url:"${pageContext.request.contextPath}/ajaxCommentLike",
						data:{id:commentid},
						type:"POST",
						dataType:"json",
						async:true,
						cache:false,
						success:function(data){
							that.children(".comment-ding").text(data);
						}
					});
				});
	        });
	       
	       $(function(){
	       		$(".button").click(function(){
	    			var value = $.trim($(this).closest("form").children(".textarea").val());
	    			if(!value){
		        		alert('评论内容不能为空！');
		        		return false;
	        		}else{
	        			return true;
	        		}
	    		});
	    		
	       });
	       
	        //修复代码高亮换行行号问题
	        $(function(){
		        SyntaxHighlighter.highlight();
		        $("table.syntaxhighlighter").each(function () {
		            if (!$(this).hasClass("nogutter")) {
		                var $gutter = $($(this).find(".gutter")[0]);
		                var $codeLines = $($(this).find(".code .line"));
		                $gutter.find(".line").each(function (i) {
		                    $(this).height($($codeLines[i]).height());
		                    $($codeLines[i]).height($($codeLines[i]).height());
		                });
		            }
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
        		<strong class="icon-login"><i class="icon icon-user"><a href="">&nbsp;${sessionScope.user.username }</a></i></strong>&nbsp;&nbsp;&nbsp;
            	<strong class="icon-register"><i class="icon icon-mobile"><a href="${pageContext.request.contextPath }/logout">&nbsp;注销</a></i></strong>
        	</c:otherwise>
        </c:choose>
    </div>
</div>
<!-- 页面主体 -->
<div class="main-body">
    <!-- 页面左栏，文章内容 -->
    <div class="body-left">
        <!-- 显示文章分类 -->
       <div id="content-top">
           <span class="icon-author"><a href="#"><i class="icon icon-home3"></i></a>&nbsp;&gt;&nbsp;生活笔记
            &nbsp;&gt;&nbsp;python
           </span>
       </div>
        <!-- 显示文章标题 -->
        <div class="content-title">
            <span id="content-span">${pb.article.title}</span>
            <p class="content-info">
                <span class="icon-author"><i class="icon icon-user"></i><a href="#">&nbsp;&nbsp;${pb.article.author}</a></span>
                &nbsp;&nbsp;&nbsp;
                <i class="icon icon-clock"></i>&nbsp;&nbsp;<span class="icon-pubtime"><fmt:formatDate value="${pb.article.date }" pattern="yyyy-MM-dd"/></span>
                &nbsp;&nbsp;&nbsp;
                <i class="icon icon-eye"></i>&nbsp;&nbsp;<span class="icon-browse">${pb.article.lookNum }浏览</span>
                &nbsp;&nbsp;&nbsp;
                <i class="icon icon-bubbles4"></i>&nbsp;&nbsp;<span class="icon-comment"><a href="#">${commentNum }评论</a></span>
                &nbsp;&nbsp;&nbsp;
                <i class="icon icon-heart"></i>&nbsp;&nbsp;<span class="icon-like"><a>${pb.article.likeNum }喜欢</a></span>
            </p>
        </div>
        <!-- 文章内容-->
        <div class="content-text">
            ${pb.article.content}
        </div>
        <!-- 赞，标签和显示上一篇下一篇 -->
        <div class="content-footer">
            <a name="${pb.article.id}" id="like"><i class="icon icon-heart"></i>&nbsp;喜欢(<span class="span-a">${pb.article.likeNum}</span>)</a>
            <a href="" id="share"><i class="icon icon-share2"></i>&nbsp;分享(0)</a>
        </div>
        <!-- 标签 -->
        <div class="content-tag">
            <i class="icon icon-price-tags"></i>&nbsp;
            <c:forEach items="${pb.article.tagList}" var="atag">
            <a href="${pageContext.request.contextPath}/index?tid=${atag.id}">${atag.name}</a>
            </c:forEach>
        </div>
        <!-- 上一篇和下一篇 -->
        <div class="content-about">
        	<c:if test="${not empty last.title}">
            	<a id="last" href="${pageContext.request.contextPath}/content?id=${last.id}">上一篇&lt;&lt;${last.title}</a>
            </c:if>
        	<c:if test="${empty last.title}">
            	<a id="last">上一篇&lt;&lt;没有了</a>
            </c:if>
            <c:if test="${not empty next.title}">
            	<a id="next" href="${pageContext.request.contextPath}/content?id=${next.id}">${next.title}&gt;&gt;下一篇</a>
            </c:if>
            <c:if test="${empty next.title}">
            	<a id="next">没有了&gt;&gt;下一篇</a>
            </c:if>
        </div>
        <!-- 空div，只做效果用，没实际作用 -->
        <div id="blank">

        </div>
        <!-- 评论 -->
        
        <div class="content-comment">
        <div class="comment-div">
            <div class="comment-top"><span>${commentNum }条评论</span></div>
            <!-- 每一条评论，评论条目 -->
            <c:forEach items="${cList}" var="comment">
            
	            <c:forEach items="${comment.replyList}" var="reply">
		            <div class="comment">
		                <img src="${pageContext.request.contextPath }${reply.userUrl}" alt="用户头像" style="border:1px solid #DCDCDC;"/>
		                <div class="comment-content">
		                    <div class="comment-username">${reply.username}</div>
		                    <div class="comment-text">
		                    <c:if test="${empty reply.pid}">
		                    <span style="color:#d32;">回复：${comment.username}</span>
		                    </c:if>
		                    <c:if test="${not empty reply.pid}">
		                    <span style="color:#d32;">回复：${reply.pname}</span>
		                    </c:if>
		                    &nbsp;${reply.content}
		                    </div>
		                    <p><fmt:formatDate value="${reply.date }" pattern="M月d日"/>&nbsp;&nbsp;&nbsp;<a class="reply-a"><i class="icon icon-undo2"></i>&nbsp;回复</a>&nbsp;&nbsp;&nbsp;
		                        <a name="${reply.id}" class="ding-a"><i class="icon icon-heart"></i>&nbsp;顶(<span class="ding">${reply.likeNum}</span>)</a>&nbsp;&nbsp;&nbsp;
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
					        <form action="${pageContext.request.contextPath}/user/retore?aid=${pb.article.id}&cid=${comment.id}&pid=${reply.id}" method="post">
					            <textarea placeholder="说点什么吧..." title="写评论" class="textarea" name="content" ></textarea>
					            <div class="submit-word">
					                <button type="submit" class="button">发布</button>
					            </div>
					        </form>
				
				    </div>
	            </c:forEach>
            
            <!-- 评论 -->
            <div class="comment">
                <img src="${pageContext.request.contextPath }${comment.userUrl}" alt="用户头像" style="border:1px solid #DCDCDC;"/>
                <div class="comment-content">
                    <div class="comment-username">${comment.username}</div>
                    <div class="comment-text">${comment.content}
                    </div>
                    <p><fmt:formatDate value="${comment.date }" pattern="M月d日"/>&nbsp;&nbsp;&nbsp;<a class="reply-a"><i class="icon icon-undo2"></i>&nbsp;回复</a>&nbsp;&nbsp;&nbsp;
                        <a name="${comment.id}" class="comment-a"><i class="icon icon-heart"></i>&nbsp;顶(<span class="comment-ding">${comment.likeNum}</span>)</a>&nbsp;&nbsp;&nbsp;
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
				        <form action="${pageContext.request.contextPath}/user/retocm?aid=${pb.article.id}&cid=${comment.id}" method="post">
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
			        <form action="${pageContext.request.contextPath}/user/comment?aid=${pb.article.id}" method="post">
			            <textarea placeholder="说点什么吧..." title="写评论" class="textarea" name="content" ></textarea>
			            <div class="submit-word">
			                <button type="submit" class="button">发布</button>
			            </div>
			        </form>
			
			    </div>

        </div>
    </div>
    <!-- 页面右栏，推荐部分相关 -->
    <div class="body-right">
        <!-- 文章归档 -->
        <div class="right-file">
            <span>文章归档</span><hr/>
            <c:forEach items="${pb.cateDate}" var="itemDate">
             <a href="#">${itemDate}</a>
            </c:forEach>
        </div>
        <!-- 猜你喜欢 -->
        <div class="right-like">
            <span>猜你喜欢</span><hr/>
            <c:forEach items="${pb.hotList}" var="like">
            <div class="like-article">
                <p><a href="${pageContext.request.contextPath}/content?id=${like.id}"><img src="${pageContext.request.contextPath}${like.imgUrl}" alt="猜你喜欢图片加载"/></a></p>
                <div class="like-article-title" style="text-overflow: ellipsis;"><a href="${pageContext.request.contextPath}/content?id=${like.id}">${like.title}</a></div>
                <div class="like-article-info"><fmt:formatDate value="${like.date }" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;384评论</div>
            </div>
            </c:forEach>
        </div>
        <!-- 标签云 -->
        <div class="right-tag">
            <span>标签云</span><hr/>
            <div class="right-tag-content">
                <c:forEach items="${tagList}" var="tag">
                        <a href="#" class="li-${tag.num }">&nbsp;${tag.name }(${tag.anum})</a>
                </c:forEach>
            </div>
        </div>
        <!-- 友情链接 -->
        <div class="right-link">
            <span>友情链接</span><hr/>
            <ul>
				<c:forEach items="${linkList}" var="link">
                <a href="${link.href}"><li class="link-info">${link.name}</li></a>
                </c:forEach>

            </ul>
        </div>
    </div>
</div>
<!-- 页面底部 -->
<div class="main-footer">
    <div id="footer"></div>
    <div id="copyright">Copyright © 2013-2016 XJIE博客 保留所有权利</div>
</div>

</body>
<script> SyntaxHighlighter.all(); //执行代码高亮 </script>
</html>
