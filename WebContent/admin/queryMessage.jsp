<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>留言查询</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.1.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/messages_zh.min.js"></script>
	<link rel="stylesheet" href="css/querymessage.css">
    <script>
        $().ready(function(){
            $("#queryForm").validate();
            $("#queryForm select").change(function(){
            	var type = $(this).val();
            	if(type=="username"){
            		$("#queryForm").attr("action","queryMsgByUserName.do");
            		$("#queryInput").attr("name","username");
            	}else if(type=="email"){
            		$("#queryForm").attr("action","queryMsgByMail.do");
            		$("#queryInput").attr("name","email");
            	}else if(type=="content"){
            		$("#queryForm").attr("action","queryMsgByContent.do");
            		$("#queryInput").attr("name","content");
            	}
            });
        });
    </script>
</head>

<body>
    <div id="container">
        <div id="header">
            <img src="../photo/query.png" alt="商品查询">
            <form class="form-inline" id="queryForm" role="form" action="queryMsgByUserName.do">
                <div class="form-group">
                    <select class="form-control" id="queryType">
                    <option value="username">按用户名查询</option>
                    <option value="email">按邮箱查询</option>
                    <option value="content">按正文内容查询</option>
                    </select>
                </div>
                &nbsp;-->>&nbsp;
                <div class="form-group">
                    <input type="text" name="username" required class="form-control" id="queryInput">
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
                        <th>管理员?</th>
                        <th>正文内容</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<s:iterator value="#request.list" var="msg">
                    <tr>
                        <td><s:property value="#msg.id" /></td>
                        <td><s:property value="#msg.username" /></td>
                        <td><s:property value="#msg.email" /></td>
                        <td><s:if test="#msg.isAdmin==true">是</s:if><s:else>否</s:else></td>
                        <td><s:property value="#msg.content" />...</td>
                        <td><a href="modifyMessages.html">修改</a>|<a href="">删除</a></td>
                    </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <!-- 保存变量，方便存取 -->
        <s:set value="#request.page" var="page"></s:set>
        <!-- 根据不同的类型显示不同的翻页区域 -->
        <s:if test="#page!=null">
	        <s:if test="#page.type=='queryAllMsg'">
	        <div id="footer">
	            <a href='queryAllMsg.do?currentPage=1'>首页</a>
	            <s:if test="#page.isPrevious">
	            <a href="queryAllMsg.do?currentPage=<s:property value="#page.currentPage-1" />">上一页</a>
	            </s:if>
	            <s:if test="#page.isNext">
	            <a href="queryAllMsg.do?currentPage=<s:property value="#page.currentPage+1" />">下一页</a>
	            </s:if>
	            <a href="queryAllMsg.do?currentPage=<s:property value="#page.totalPageNum" />">尾页</a>
	            <br> 当前第&nbsp;<s:property value="#page.currentPage"/>&nbsp;页, 总共&nbsp;<s:property value="#page.totalPageNum"/>&nbsp;页
	        </div>
        	</s:if>
        	<s:elseif test="#page.type=='queryMsgByUserName'">
        	<div id="footer">
	            <a href='queryMsgByUserName.do?currentPage=1'>首页</a>
	            <s:if test="#page.isPrevious">
	            <a href="queryMsgByUserName.do?currentPage=<s:property value="#page.currentPage-1" />">上一页</a>
	            </s:if>
	            <s:if test="#page.isNext">
	            <a href="queryMsgByUserName.do?currentPage=<s:property value="#page.currentPage+1" />">下一页</a>
	            </s:if>
	            <a href="queryMsgByUserName.do?currentPage=<s:property value="#page.totalPageNum" />">尾页</a>
	            <br> 当前第&nbsp;<s:property value="#page.currentPage"/>&nbsp;页, 总共&nbsp;<s:property value="#page.totalPageNum"/>&nbsp;页
	        </div>
        	</s:elseif>
        	<s:elseif test="#page.type=='queryMsgByMail'">
        	<div id="footer">
	            <a href='queryMsgByMail.do?currentPage=1'>首页</a>
	            <s:if test="#page.isPrevious">
	            <a href="queryMsgByMail.do?currentPage=<s:property value="#page.currentPage-1" />">上一页</a>
	            </s:if>
	            <s:if test="#page.isNext">
	            <a href="queryMsgByMail.do?currentPage=<s:property value="#page.currentPage+1" />">下一页</a>
	            </s:if>
	            <a href="queryMsgByMail.do?currentPage=<s:property value="#page.totalPageNum" />">尾页</a>
	            <br> 当前第&nbsp;<s:property value="#page.currentPage"/>&nbsp;页, 总共&nbsp;<s:property value="#page.totalPageNum"/>&nbsp;页
	        </div>
        	</s:elseif>
        	<s:elseif test="#page.type=='queryMsgByContent'">
        	<div id="footer">
	            <a href='queryMsgByContent.do?currentPage=1'>首页</a>
	            <s:if test="#page.isPrevious">
	            <a href="queryMsgByContent.do?currentPage=<s:property value="#page.currentPage-1" />">上一页</a>
	            </s:if>
	            <s:if test="#page.isNext">
	            <a href="queryMsgByContent.do?currentPage=<s:property value="#page.currentPage+1" />">下一页</a>
	            </s:if>
	            <a href="queryMsgByContent.do?currentPage=<s:property value="#page.totalPageNum" />">尾页</a>
	            <br> 当前第&nbsp;<s:property value="#page.currentPage"/>&nbsp;页, 总共&nbsp;<s:property value="#page.totalPageNum"/>&nbsp;页
	        </div>
        	</s:elseif>
        </s:if>
    </div>
</body>

</html>