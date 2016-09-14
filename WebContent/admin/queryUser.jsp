<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>用户信息查询</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.1.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/messages_zh.min.js"></script>
	 <link rel="stylesheet" href="css/queryuser.css">
    <script>
        $().ready(function(){
            $("#queryForm").validate();
        });
    </script>
</head>

<body>
    <div id="container">
        <div id="header">
            <img src="../photo/query.png" alt="商品查询">
            <form class="form-inline" id="queryForm" role="form" action="">
                <div class="form-group">
                    <select class="form-control" id="sel1">
                    <option>按编号查询</option>
                    <option>按用户名查询</option>
                    <option>按邮箱查询</option>
                    </select>
                </div>
                &nbsp;-->>&nbsp;
                <div class="form-group">
                    <input type="text" required="true" class="form-control" id="inputstream">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
        <hr>
        <div id="content">
            <!-- 消息提示 -->
          	<s:if test="#request.msg!=null">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;
            </button> <s:property value="#request.msg"/>
            </div>
            </s:if>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>编号</th>
                        <th>用户名</th>
                        <th>联系邮箱</th>
                        <th>用户角色</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
               <s:iterator value="#request.list" var="user">
                    <tr>
                        <td><s:property value="#user.id"/></td>
                        <td><s:property value="#user.username"/></td>
                        <td><s:property value="#user.email"/></td>
                        <td><s:property value="#user.role"/></td>
                        <td><a href="modifyUser.html">修改</a>|<a href="">删除</a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <s:set name="page" value="#request.page" />
        <div id="footer">
        	<a href="queryUser.do?currentPage=1">首页</a>
        	<s:if test="#page.isPrevious">
            <a href="queryUser.do?currentPage=<s:property value="#page.currentPage-1"/>">上一页</a>
            </s:if>
            <s:if test="#page.isNext">
            <a href="queryUser.do?currentPage=<s:property value="#page.currentPage+1"/>">下一页</a>
         	</s:if>
            <a href="queryUser.do?currentPage=<s:property value="#page.totalPageNum"/>">尾页</a>
            <br> 当前第&nbsp;<s:property value="#page.currentPage"/>&nbsp;页, 总共&nbsp;<s:property value="#page.totalPageNum"/>&nbsp;页
        </div>
    </div>
</body>

</html>