<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>注册</title>
	<link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/regist.css"/>
	    <script src="${pageContext.request.contextPath}/static/js/jquery-1.4.1.min.js"></script>
	    
	    <script language="JavaScript">
	        $(function(){
	            $("img").hide();
	            $("#vCode").show();
	            $("#username").focus()
	            
	            $("#username").blur(function(){
	            	validateUsername();
	            });
	            $("#password").blur(function(){
	            	validatePass();
	            });
	            $("#repassword").blur(function(){
	            	validateRePass();
	            });
	            $("#email").blur(function(){
	            	validateEmail();
	            });
	        });
	        
	        function _change() {
				$("#vCode").attr("src", "${pageContext.request.contextPath}/userVerifyCode?" + new Date().getTime());
			}
			
			function validateUsername(){
				var value = $.trim($("#username").val());//获取输入框中的值
				//非空校验
				if(!value || value==''){
					$("#message").text("用户名不能为空！");
					$("#usernameimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#usernameimg").show();
					return false;
				}else{
					$("#usernameimg").attr("src","${pageContext.request.contextPath}/static/images/duihao.jpg");
					$("#usernameimg").show();
				}
				//长度校验
				if(value.length < 3 || value.length > 12){
					$("#message").text("用户名长度必须在3 ~ 12之间！");
					$("#usernameimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					return false;
				}
				//是否注册校验
				$.ajax({
					url:"${pageContext.request.contextPath}/ajaxUsername",
					data:{username:value},
					type:"POST",
					dataType:"json",
					async:false,
					cache:false,
					success:function(result){
						if(result){
							$("#message").text("用户名可以使用！");
							$("#usernameimg").attr("src","${pageContext.request.contextPath}/static/images/duihao.jpg");
							$(".td1 img").show();
						}else{
							$("#message").text("用户名已被注册！");
							$("#usernameimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
							$(".td1 img").show();
							return fasle;
						}
					}
				});
				
				return true;
			}
			
			//密码校验
			function validatePass(){
				var value = $.trim($("#password").val());
				if(!value || value==''){
					$("#message").text("密码不能为空！");
					$("#passwordimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#passwordimg").show();
					return false;
				}else{
					$("#message").text("");
					$("#passwordimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#passwordimg").hide();
					return true;
				}
			}
			//确认密码校验
			function validateRePass(){
				var value = $.trim($("#password").val());
				var reValue = $.trim($("#repassword").val());
				
				if(!reValue || reValue==''){
					$("#message").text("确认密码不能为空！");
					$("#repasswordimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#repasswordimg").show();
					return false;
				}
				if(value==reValue){
					$("#message").text("");
					$("#repasswordimg").attr("src","${pageContext.request.contextPath}/static/images/duihao.jpg");
					$("#repasswordimg").show();
				}else{
					$("#message").text("两次输入密码不一致！");
					$("#repasswordimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#repasswordimg").show();
					return false;
				}
				return true;
			}
			//邮箱校验
			function validateEmail(){
				var value = $.trim($("#email").val());
				if(!value || value==''){
					$("#message").text("邮箱不能为空！");
					$("#emailimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#emailimg").show();
					return false;
				}
				
				if ((email.length > 128) || (email.length < 6)) {
        			return false;
    			}
			    var format = /^[A-Za-z0-9+]+[A-Za-z0-9\.\_\-+]*@([A-Za-z0-9\-]+\.)+[A-Za-z0-9]+$/;
			    if (!value.match(format)) {
			    	$("#message").text("邮箱格式不正确！");
					$("#emailimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
					$("#emailimg").show();
			        return false;
			    }
			    $("#message").text("");
				$("#emailimg").attr("src","${pageContext.request.contextPath}/static/images/duihao.jpg");
				$("#emailimg").show();
			    return true;
			}
	    </script>
  </head>
  
  <body>
  	<div id="register-form">
    <form id="form" action="${pageContext.request.contextPath}/registCheck" method="post">
		<input type="hidden" name="token" value="${token}" />
        <div id="register-error">
			<span id="message">${msg}</span>
        </div>
        <table>
            <tr>
                <td class="td1">用户名</td>
                <td class="td2"><input type="text" name="username" id="username" value="${user.username}"/></td>
                <td class="td3"><img id="usernameimg" src=""/></td>
            </tr>
            <tr>
                <td class="td1">密码</td>
                <td class="td2"><input type="password" name="password" id="password"/></td>
                <td class="td3"><img id="passwordimg" src=""/></td>
            </tr>
            <tr>
                <td class="td1">确认密码</td>
                <td class="td2"><input type="password" name="repassword" id="repassword"/></td>
                <td class="td3"><img id="repasswordimg" src=""/></td>
            </tr>
            <tr>
                <td class="td1">邮箱</td>
                <td class="td2"><input type="text" name="email" id="email" style="font-size:16px;" value="${user.email}"/></td>
                <td class="td3"><img src="" id="emailimg"/></td>
            </tr>
            <tr>
                <td class="td1">验证码</td>
                <td class="td2">
                	<input type="text" name="vCode" id="codeInput" style="width:80px;"/>
                    <img src="${pageContext.request.contextPath}/userVerifyCode" alt="验证码" id="vCode" onclick="_change();"/>
                    </td>
                <td class="td3"><img src=""/></td>
            </tr>
            <tr>
                <td class="td1"></td>
                <td class="td2"><button id="register" type="submit" name="submit">注册</button></td>
                <td class="td3"></td>
            </tr>
        </table>
    </form>
    <div class="register-footer">
        已有账号&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/login">登录</a>
        <br/><br/>
        <a href="${pageContext.request.contextPath}/index">←回到主页</a>
    </div>
</div>
  </body>
</html>
