<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>系统信息维护</title>
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
        <h2>系统信息维护</h2>
        <hr>
        <!--提示警告-->
        <c:if test="${msg!=null }">
        <div class="alert alert-info alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            ${msg }
        </div>
        </c:if>
        <form class="form-horizontal" id="modifyForm" role="form" method="POST" action="midifySettings.do">
            <div class="form-group">
                <label class="control-label col-sm-2">网站标题：</label>
                <div class="col-sm-10">
                    <input type="text" required name="webtitle" value="${map.WebTitle }" class="form-control" placeholder="在此输入标题名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">首页一级标题：</label>
                <div class="col-sm-10">
                    <input type="text" name="title1" required value="${map.Title1 }" class="form-control" placeholder="在此输入标题名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsName">首页二级标题：</label>
                <div class="col-sm-10">
                    <input type="text" name="title2" required value="${map.Title2 }" class="form-control" placeholder="在此输入标题名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="goodsPrice">首页按钮名:</label>
                <div class="col-sm-10">
                    <input type="text" name="webbutton" required value="${map.WebButton }" class="form-control" placeholder="在此输入按钮名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">首页留言数量：</label>
                <div class="col-sm-10">
                    <input type="text" name="msgnumber" required digits="true" value="${map.MsgNumber }" class="form-control" placeholder="在此输入每一页你想显示的留言数量">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">首页一级版权：</label>
                <div class="col-sm-10">
                    <input type="text" name="copyright1" required value="${map.Copyright1 }" class="form-control" placeholder="在此输入版权名称">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">首页二级版权：</label>
                <div class="col-sm-10">
                    <input type="text" name="copyright2" required="true" value="${map.Copyright2 }" class="form-control" placeholder="在此输入版权名称">
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