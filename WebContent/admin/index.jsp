<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>管理后台</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.1.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/index.css">
</head>

<body>
    <div id="container">
        <div id="header">
            <img src="../photo/logo.png" alt="欢迎使用留言板">
            <h2>留言板管理后台</h2>
            <div id="systemLoginInfo">
                欢迎您：${sessionScope.user.username }     &nbsp;&nbsp;
                <a href="logout.do">退出</a>&nbsp;&nbsp;
                <a href="about.html" target="iframeContent">关于</a>
            </div>
        </div>
        <div id="content_left">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="index.html" style="text-align:center">功能菜单</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">留言管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="addMessage.jsp" target="iframeContent">添加新留言</a></li>
                        <li><a href="queryAllMsg.do" target="iframeContent">修改已有留言</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">用户管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="addUser.jsp" target="iframeContent">添加新用户</a></li>
                        <li><a href="queryAllUser.do" target="iframeContent">修改已有用户</a></li>
                    </ul>
                </li>
                <li><a href="getSettings.do" target="iframeContent">系统维护</a></li>
                <li><a href="help.html" target="iframeContent">使用帮助</a></li>
            </ul>
        </div>
        <div id="content_right">
            <iframe src="about.html" frameborder="0" name="iframeContent"></iframe>
        </div>
        <div id="footer">
            <p> Power by：Mr.Lejie
                <a href="http://www.hack4b.com"><span class="glyphicon glyphicon-envelope"></span></a> 
            </p>
        </div>
    </div>
</body>
</html>