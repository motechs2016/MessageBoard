<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>用户信息修改</title>
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
            $("#modifyForm").validate();
        });
    </script>
</head>

<body>
    <div id="container">
        <h2>用户信息修改</h2>
        <hr>
        <!--提示警告-->
        <c:if test="${requestScope.msg!=null }">
        <div class="alert alert-info alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            修改成功了！你可以<a href="queryAllUser.do">查看你的修改</a>
        </div>
        </c:if>
        <!-- 保存集合变量方便取值 -->
        <c:set value="${requestScope.list[0] }" var="user"></c:set>
        <form class="form-horizontal" id="modifyForm" role="form" method="POST" action="modifyUserById.do">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">用户编号:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${user.id }</p>
                    <input type="hidden" value="${user.id }">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsName">用户名:</label>
                <div class="col-sm-10">
                    <input type="text" required name="username" value="${user.username }" class="form-control" placeholder="在此输入用户名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsPrice">联系邮箱:</label>
                <div class="col-sm-10">
                    <input type="email" required email name="email" value="${user.email }" class="form-control" placeholder="在此输入联系邮箱">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsDiscount">用户密码:</label>
                <div class="col-sm-10">
                    <input type="text" required name="password" class="form-control" placeholder="在此输入用户密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">修改</button>
                </div>
            </div>
        </form>
    </div>
</body>

</html>