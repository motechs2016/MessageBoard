<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>添加留言</title>
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
        <h2>添加留言</h2>
        <hr>
        <!--提示警告-->
        <c:choose>
        <c:when test="${requestScope.msg==null }">
        <div class="alert alert-info alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            你当前的身份是管理员，因此你添加的内容在首页中将以蓝色醒目的字体显示。
        </div>
        </c:when>
        <c:otherwise>
        <div class="alert alert-info alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            ${requestScope.msg }
        </div>
        </c:otherwise>
        </c:choose>
        <form class="form-horizontal" id="addForm" role="form" method="POST" action="addMsgForAdmin.do">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">留言编号:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">自动</p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsName">用户名:</label>
                <div class="col-sm-10">
                    <input type="text" required="true" name="username" class="form-control" placeholder="在此输入用户名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsPrice">联系邮箱:</label>
                <div class="col-sm-10">
                    <input type="email" required="true" email="true" name="email" class="form-control" placeholder="在此输入联系邮箱">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsDiscount">正文内容:</label>
                <div class="col-sm-10">
                    <textarea class="form-control" required="true" name="content" onmousewheel="" name="content" required rows="5" id="comment"></textarea>
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