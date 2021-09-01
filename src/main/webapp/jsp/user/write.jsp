<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>撰写文章</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/write/write.css"/>
    <script language="JavaScript" src="${pageContext.request.contextPath }/static/js/jquery-1.4.1.min.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath }/static/js/write.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
	
  </head>
  <script language="JavaScript">
  	$(function(){
  		ajaxCategory();
  		SyntaxHighlighter.all();
  	});
  	
  	//异步加载分类
  	function ajaxCategory(){
  		$.ajax({
			url:"${pageContext.request.contextPath}/ajaxCategory",
			type:"POST",
			dataType:"json",
			async:true,
			cache:false,
			success:function(arr){
				$("#select").empty();
				$("#select").append($("<option value='0'>选择分类</option>"));
				//循环遍历数组，把每个对象转换成<option>添加到select中
				for(var i = 0; i < arr.length; i++) {
					var option = $("<option>").val(arr[i].id).text(arr[i].name);
					$("#select").append(option);
				}
			}
		});
  	}
  	
  	//非空校验
	function submitData(){
		var title=$("#blogName").val();
		var cid=$('#select option:selected').val();
		var img=$("#img").val();
		var content=UE.getEditor('newsEditor').getContent();
		var summary=UE.getEditor('newsEditor').getContentTxt().substr(0,135)+'...';	
		if(title==null || title==''){
			alert("请输入标题！");
			return false;
		}else if(cid==0){
			alert("请选择文章类别！");
			return false;
		}else if(img==null || img==''){
			alert("请添加图片！");
			return false;
		}else if(content==null || content==''){
			alert("请输入内容！");
			return false;
		}else{
			$("#content").val(content);
			$("#summary").val(summary);
			var v = $(".tag-display").text();
			$("#blogTag").val(v);
			$("#form").submit();
			return true;
	    }
	 }
	
</script>
  <body>
    <%@include file="/page/menu.jsp" %>
    <!-- 写文章区域 -->
    <div class="body-all">
        <div class="body-left">
            <span>撰写新文章</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:18px;">${msg}</span>
            <form id="form" action="${pageContext.request.contextPath }/user/writeSave" method="post" enctype="multipart/form-data">
                <input id="blogName" type="text" name="title" placeholder="在此输入标题"/>
                <input id="blogTag" type="hidden" name="tag" value=""/>
                <input id="summary" type="hidden" name="summary" value=""/>
                <div class="div-category">
                    <select id="select" name="cid">
                        <option value="0">选择分类</option>
                        <option value="1">生活笔记</option>
                        <option value="2">技术分享</option>
                        <option value="3">福利专区</option>
                    </select>
                    <a href="" class="file"><input type="file" name="img" id="img"/>文章主页图片</a>
                </div>
                <div class="div-textarea">
                    <textarea id="newsEditor" name="newsEditor"></textarea>
                    <input id="content" type="hidden" name="content" value=""/>
                </div>
                <div class="div-button">
                    <button type="button" class="button" onclick="submitData();">发表</button>
                </div>
            </form>
        </div>

        <!-- 页面右部分 -->
        <div class="body-right">
            <div class="div-tag">
                <span id="span1">&nbsp;&nbsp;标签</span>
                <div class="tag-main">
                    <form action="${pageContext.request.contextPath}/user/addTag" method="post">
                        <input type="text" name="tagName" id="tagName" placeholder="添加标签后从常用标签中选择"/>
                        <button type="submit" id="tagButton">添加</button>
                    </form>
                </div>
                <span id="span2">&nbsp;&nbsp;多个标签请用英文逗号（,）分开</span>
                <div class="tag-display">

                </div>
                <a id="tag-a">从常用标签中选择</a>
                <div class="tag-list">
                	<c:forEach items="${tagList}" var="tag">
                    	<a name="${tag.id}">${tag.name}</a>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</body>
   <script type="text/javascript">  
           UE.getEditor('newsEditor');  
   </script>  
</html>
