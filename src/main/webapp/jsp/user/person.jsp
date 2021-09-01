<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>个人资料</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath }/static/images/main.jpg" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/person/person.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/person/superfish.css" media="screen">
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/css/person/js/jquery-1.2.6.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/css/person/js/hoverIntent.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/css/person/js/superfish.js"></script>
    <script type="text/javascript">

        // initialise plugins
        jQuery(function(){
            jQuery('ul.sf-menu').superfish({
                delay:600
            });
        });

    </script>
  </head>
  
  <body>
    <!--导航栏，高度为40px-->
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/user/person"><img src="${pageContext.request.contextPath}${sessionScope.user.userUrl}" alt="头像图片"/></a>
        <span><a href="${pageContext.request.contextPath}/user/person">您好，${sessionScope.user.username}</a></span>
        <a href="${pageContext.request.contextPath}/index" id="index"><i class="icon icon-home3">主页</i></a>
    </div>
    <!-- 左侧菜单，宽度为180px-->
    <div class="menu">
        <ul class="sf-menu">
            <li class="current">
                <a href="${pageContext.request.contextPath}/user/index">
                    <i class="icon icon-file-text2"></i>
                    文章
                </a>
                <ul>
                    <li class="current"><a href="${pageContext.request.contextPath}/user/index">所有文章</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/write">写文章</a></li>
                </ul>
            <li>
                <a href="${pageContext.request.contextPath}/user/commentlist">
                    <i class="icon icon-chat"></i>
                    评论</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/person">
                    <i class="icon icon-user"></i>
                    个人资料
                    </a>
            </li>
            </ul>
        </div>
    <div class="body">
        <div class="body-top">
            <span>个人资料</span>
        </div>
        <form action="${pageContext.request.contextPath}/user/savePerson?id=${sessionScope.user.id}" method="POST" enctype="multipart/form-data">
        <table id="table">
            <tr>
                <td class="td-1">
                    <div class="body-top">
                        <span>姓名</span>
                    </div>
                </td>
                <td class="td-2"></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">用户名</td>
                <td class="td-2"><input type="text" name="username" disabled="disabled" value="${user.username}"/></td>
                <td class="td-3">用户名不能更改</td>
            </tr>
            <tr>
                <td class="td-1">名字</td>
                <td class="td-2"><input type="text" name="firstname" value="${user.firstname}"/></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">姓氏</td>
                <td class="td-2"><input type="text" name="lastname" value="${user.lastname}"/></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">昵称(必填)</td>
                <td class="td-2"><input type="text" name="nickname" value="${user.nickname}"/></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">公开显示为</td>
                <td class="td-2"><input type="text" name="pubname" value="${user.pubname}"/></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">
                    <div class="body-top">
                        <span>联系信息</span>
                    </div>
                </td>
                <td class="td-2"></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">电子邮件(必填)</td>
                <td class="td-2"><input type="email" name="email"  value="${user.email}" disabled="disabled"/></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">站点</td>
                <td class="td-2"><input type="text" name="site" value="${user.site}"/></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">
                    <div class="body-top">
                        <span>关于您自己</span>
                    </div>
                </td>
                <td class="td-2"></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">个人说明</td>
                <td class="td-2"><textarea rows="6" cols="39" name="descrip">${user.descrip}</textarea></td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1"></td>
                <td class="td-2">分享关于您的一些信息。可能会被公开。</td>
                <td class="td-3"></td>
            </tr>
            <tr>
                <td class="td-1">资料图片</td>
                <td class="td-2"><img src="${pageContext.request.contextPath}${user.userUrl}" alt="资料图片" width="96px" height="96px"/></td>
                <td class="td-3"><a href="" class="file"><input type="file" name="img" id="img"/>更换头像</a></td>
            </tr>
            <tr>
                <td class="td-1"><button type="submit" id="button">更新个人资料</button></td>
                <td class="td-2"></td>
                <td class="td-3"></td>
            </tr>
        </table>
      </form>
    </div>
  </body>
</html>
