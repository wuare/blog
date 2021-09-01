<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/page/menu/menu.css"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/page/menu/superfish.css" media="screen">
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/page/menu/js/jquery-1.2.6.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/page/menu/js/hoverIntent.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/page/menu/js/superfish.js"></script>
    <script type="text/javascript">

        // initialise plugins
        jQuery(function(){
            jQuery('ul.sf-menu').superfish({
                delay:600
            });
        });

    </script>
<!--导航栏，高度为40px-->
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/user/person"><img src="${pageContext.request.contextPath}/static/images/about.jpg" alt="头像图片"/></a>
        <span><a href="${pageContext.request.contextPath}/user/person">您好，${sessionScope.admin.username}</a></span>
        <a href="${pageContext.request.contextPath}/index" id="index"><i class="icon icon-home3">主页</i></a>
    </div>
    <!-- 左侧菜单，宽度为180px-->
    <div class="menu">
        <ul class="sf-menu">
            <li class="current">
                <a href="${pageContext.request.contextPath}/admin/index">
                    <i class="icon icon-file-text2"></i>
                    文章
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/commentlist">
                    <i class="icon icon-chat"></i>
                    评论</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/messagelist">
                    <i class="icon icon-chat"></i>
                    留言</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/linklist">
                    <i class="icon icon-user"></i>
                    友链
                    </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/tag">
                    <i class="icon icon-user"></i>
                    标签
                    </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/userimg">
                    <i class="icon icon-user"></i>
                    用户头像图片
                    </a>
            </li>
            </ul>
        </div>