<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理后台</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.1.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/messages_zh.min.js"></script>
    <style type="text/css">
        #loginDiv {
            height: 400px;
            width: 40%;
            text-align: center;
            margin-left: auto;
            margin-right: auto;
            margin-top: 100px;
        }
        
        #authCode {
            width: 200px;
            float: left;
        }
        
        #authPic {
        	margin-left:20px;
           	height:32px;
   		    width:100px;
            float: left;
        }
    </style>
    <script>
        $().ready(function(){
            $("#loginForm").validate();
            $("#authPic").click(function(){
            	$(this).attr("src","../authCode.do?timestamp="+new Date().getTime());
            });
        });
    </script>
</head>

<body>
    <div id="loginDiv">
        <h3>留言板管理后台</h3><br>
        <img src="../photo/key.png" alt="用户登录" width="160px">
        <!-- 消息提示 -->
        <c:if test="${requestScope.msg!=null }">
         <div class="alert alert-success alert-danger">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;
            </button> ${requestScope.msg }
        </div>
        </c:if>
        <form class="form-horizontal" id="loginForm" role="form" method="POST" action="login.do">
            <div class="form-group">
                <label class="control-label col-sm-2" for="user">用户名:</label>
                <div class="col-sm-10">
                    <input type="text" name="username" value="${requestScope.user.username }" required class="form-control" id="email" placeholder="在此输入你的用户名">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">密码:</label>
                <div class="col-sm-10">
                    <input type="password" name="password" required value="${requestScope.user.password }" class="form-control" id="pwd" placeholder="在此输入你的密码">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">验证码:</label>
                <div class="col-sm-10">
                    <input type="text" name="authcode" required class="form-control" id="authCode" placeholder="在此输入验证码！">
                    <img src="../authCode.do" id="authPic" alt="单击刷新">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label><input type="checkbox">要我记住你吗？</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">登录</button>
                </div>
            </div>
            <p>
                <a style="float:left" class="btn" href="../index.jsp">返回首页</a>
            </p>
        </form>
    </div>
</body>
</html>