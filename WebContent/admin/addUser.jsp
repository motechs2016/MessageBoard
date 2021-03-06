<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>添加用户</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.1.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/messages_zh.min.js"></script>
    <style type="text/css">
        #container {
            height: 100%;
            width: 800px;
            margin-left: 10px;
        }
        
        #container h2 {
            text-align: center;
        }
    </style>
    <script>
        $().ready(function(){
            $("#addForm").validate();
        });
    </script>
</head>

<body>
    <div id="container">
        <h2>添加用户</h2>
        <hr>
        <c:if test="${requestScope.msg!=null }">
         <!--提示警告-->
        <div class="alert alert-info alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            ${requestScope.msg }
        </div>
        </c:if>
        <form class="form-horizontal" id="addForm" role="form" method="POST" action="addUser.do">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">用户编号:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">自动</p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsName">用户名:</label>
                <div class="col-sm-10">
                    <input type="text" name="username" required class="form-control" placeholder="在此输入用户名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsPrice">联系邮箱:</label>
                <div class="col-sm-10">
                    <input type="email" name="email" required email class="form-control" placeholder="在此输入联系邮箱">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsPrice">用户角色:</label>
                <div class="col-sm-10">
                    <select class="form-control" name="role">
                    <option value="普通管理员">普通管理员</option>
                    <option value="超级管理员">超级管理员</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsDiscount">用户密码:</label>
                <div class="col-sm-10">
                    <input type="password" name="password" required class="form-control" placeholder="在此输入用户密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">添加</button>
                </div>
            </div>
        </form>
    </div>
</body>

</html>