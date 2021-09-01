<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.4.1.min.js"></script>
    <script language="JavaScript">
        $(function(){
            $(".td3 img").hide();
            $("#username").focus();

            $("#username").blur(function(){
                validateUser();
            });
            $("#password").blur(function(){
                validatePass();
            });
            $("#codeInput").blur(function(){
                validateCode();
            });
        });
        
        
        
        //换一张验证码
        function _change() {
			$("#vCode").attr("src", "${pageContext.request.contextPath}/userVerifyCode?" + new Date().getTime());
		}
		//用户名是否为空
		function validateUser(){
			var value = $.trim($("#username").val());
			if(!value){
				$("#login-error span").text("用户名不能为空！");
				$("#usernameimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
				$("#usernameimg").show();
			}else{
				$("#login-error span").text("");
				$("#usernameimg").hide();
			}
		}
		//校验密码是否为空
		function validatePass(){
			var value = $.trim($("#password").val());
			if(!value){
				$("#login-error span").text("密码名不能为空！");
				$("#passwordimg").attr("src","${pageContext.request.contextPath}/static/images/cuohao.png");
				$("#passwordimg").show();
			}else{
				$("#login-error span").text("");
				$("#passwordimg").hide();
			}
		}
		//验证码是否为空
		function validateCode(){
			var value = $.trim($("#codeInput").val());
			if(!value){
				$("#login-error span").text("验证码不能为空！");
			}else{
				$("#login-error span").text("");
			}
		}
    </script>
  </head>
  
  <body>
    <div id="login-form">
        <form action="${pageContext.request.contextPath}/loginCheck" method="post">
            <div id="login-error">
				<span>${msg}</span>
            </div>
            <table>
                <tr>
                    <td class="td1">用户名</td>
                    <td class="td2"><input type="text" name="username" id="username"/></td>
                    <td class="td3"><img src="" id="usernameimg"/></td>
                </tr>
                <tr>
                    <td class="td1">密码</td>
                    <td class="td2"><input type="password" name="password" id="password"/></td>
                    <td class="td3"><img src="" id="passwordimg"/></td>
                </tr>
                <tr>
                    <td class="td1">验证码</td>
                    <td class="td2">
                    <input type="text" name="vCode" id="codeInput" style="width:80px;"/>
                    <img src="${pageContext.request.contextPath}/userVerifyCode" alt="验证码" id="vCode" onclick="_change();"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td class="td1"></td>
                    <td class="td2"><button type="submit" name="submit">登录</button></td>
                    <td class="td3"></td>
                </tr>
            </table>
        </form>
        <div class="login-footer">
            <a href="#">注册</a>&nbsp;|&nbsp;<a href="#">忘记密码?</a>
            <br/><br/>
            <a href="${pageContext.request.contextPath}/index">←回到主页</a>
        </div>
    </div>
  </body>
</html>
