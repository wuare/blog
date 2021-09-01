<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文章</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/user/all.css"/>
    	<script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
    <script type="text/javascript">
    	 $(function(){
	        	$(".hot").click(function(){
					var aid = $(this).attr("name");
					var that = $(this);
					$.ajax({
						url:"${pageContext.request.contextPath}/admin/ajaxhot",
						data:{id:aid},
						type:"POST",
						dataType:"json",
						async:true,
						cache:false,
						success:function(b){
							if(b){
								that.hide();
							}
						}
					});
				});
	        	$(".cancel").click(function(){
					var aid = $(this).attr("name");
					var that = $(this);
					$.ajax({
						url:"${pageContext.request.contextPath}/admin/ajaxcancelhot",
						data:{id:aid},
						type:"POST",
						dataType:"json",
						async:true,
						cache:false,
						success:function(b){
							if(b){
								that.hide();
							}
						}
					});
				});
	        });
    </script>
    <style type="text/css">
    	a{
    		cursor: pointer;
    	}
    </style>
  </head>
  
  <body>
    <%@include file="/page/admin/menu.jsp" %>
    <!-- 所有文章列表 -->
    <div class="all-list">
        <div class="list-top">
            <span>文章</span>
            <a href="#"><button type="button">写文章</button></a>
        </div>
        <div class="list-info">
            <span><a>全部</a>(${pb.tr })&nbsp;|&nbsp;<a>已发布</a>(${pb.tr })&nbsp;|&nbsp;<a>置顶</a>(${hotNum})</span>
        </div>
        <div class="list-search">
            <form action="${pageContext.request.contextPath }/admin/index" method="post">
                <button type="submit" id="search-button">搜索文章</button>
                <input type="text" name="title" id="search-name"/>

            </form>
        </div>
        <!-- 分页按钮 -->
        <div class="list-page">
            <div class="page">
                <a class="page-foward3" href="#"><i class="icon icon-forward3"></i></a>
                <c:if test="${pb.pc<pb.tp}">
                <a class="page-last" href="${pageContext.request.contextPath }/admin/index?pc=${pb.pc+1}"><i class="icon icon-last"></i></a>
                </c:if>
                <c:if test="${pb.pc==pb.tp}">
                <a class="page-last"><i class="icon icon-last"></i></a>
                </c:if>
                <form action="${pageContext.request.contextPath }/admin/index" method="post">
                    <span class="currentPage">&nbsp;第<input type="text" name="pc" value="${pb.pc }"/>页，共${pb.tp }页</span>
                    <input type="submit" value="submit" style="display: none;"/>
                </form>
                <c:if test="${pb.pc>1}">
                <a class="page-first" href="${pageContext.request.contextPath }/admin/index?pc=${pb.pc-1}"><i class="icon icon-first"></i></a>
                </c:if>
                <c:if test="${pb.pc==1}">
                <a class="page-first"><i class="icon icon-first"></i></a>
                </c:if>
                <a class="page-backward2" href="#"><i class="icon icon-backward2"></i></a>
            </div>
        </div>
        <!--表格数据-->
        <table id="table">
            <tr id="tr-head">
                <td class="td-0"></td>
                <td class="td-1">标题</td>
                <td class="td-2">作者</td>
                <td class="td-3">分类目录</td>
                <td class="td-4">标签</td>
                <td class="td-5">日期</td>
                <td class="td-6">浏览</td>
                <td class="td-7">操作</td>
            </tr>
            <c:forEach items="${pb.beanList}" var="article">
            <tr>
                <td><input type="checkbox" name="checkbox" value="${article.id }"/></td>
                <td>${article.title }<br/><a href="${pageContext.request.contextPath }/content?id=${article.id }">查看</a></td>
                <td>${article.author }</td>
                <td>${article.cateName }</td>
                <td>
					<c:forEach items="${article.tagList}" var="tag">
						${tag.name }&nbsp;
					</c:forEach>
				</td>
                <td>已发布<br/><fmt:formatDate value="${article.date }" pattern="yyyy-MM-dd"/></td>
                <td>${article.lookNum }次</td>
                <td><a href="${pageContext.request.contextPath }/admin/modifyPre?id=${article.id}">修改</a><br/>
                	<a onclick="return confirm('您是否真要删除该文章？')" href="${pageContext.request.contextPath }/admin/delete?id=${article.id}">删除</a><br/>
                	<c:if test="${article.hotState == 0}">
                	<a class="hot" name="${article.id}">推荐</a>
                	</c:if>
                	<c:if test="${article.hotState == 1}">
                	<a class="cancel" name="${article.id}">取消推荐</a>
                	</c:if>
                	</td>
            </tr>
            </c:forEach>
            
            <tr>
                <td></td>
                <td class="td-1">标题</td>
                <td class="td-2">作者</td>
                <td class="td-3">分类目录</td>
                <td class="td-4">标签</td>
                <td class="td-5">日期</td>
                <td class="td-6">浏览</td>
                <td class="td-7">描述</td>
            </tr>
        </table>
        <!-- 分页按钮 -->
        <div class="list-page">
            <div class="page">
                <a class="page-foward3" href="#"><i class="icon icon-forward3"></i></a>
                <c:if test="${pb.pc<pb.tp}">
                <a class="page-last" href="${pageContext.request.contextPath }/admin/index?pc=${pb.pc+1}"><i class="icon icon-last"></i></a>
                </c:if>
                <c:if test="${pb.pc==pb.tp}">
                <a class="page-last"><i class="icon icon-last"></i></a>
                </c:if>
                <form action="${pageContext.request.contextPath }/admin/index" method="post">
                    <span class="currentPage">&nbsp;第<input type="text" name="pc" value="${pb.pc }"/>页，共${pb.tp }页</span>
                    <input type="submit" value="submit" style="display: none;"/>
                </form>
                <c:if test="${pb.pc>1}">
                <a class="page-first" href="${pageContext.request.contextPath }/admin/index?pc=${pb.pc-1}"><i class="icon icon-first"></i></a>
                </c:if>
                <c:if test="${pb.pc==1}">
                <a class="page-first"><i class="icon icon-first"></i></a>
                </c:if>
                <a class="page-backward2" href="#"><i class="icon icon-backward2"></i></a>
            </div>
        </div>

    </div>
</body>
</html>
