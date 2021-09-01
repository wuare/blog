<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'test.jsp' starting page</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript">
		function test(){
			$.ajax({
					url:"${pageContext.request.contextPath}/test1",
					type:"POST",
					dataType:"json",
					async:false,
					cache:false,
					success:function(result){
						alert('success');
					}
				});
		}
	</script>
  </head>
  
  <body>
    ${i}<br/><br/>
    <button onclick="test();">点击</button>
  </body>
</html>
