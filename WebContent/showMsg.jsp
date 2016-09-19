<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.min.js"></script>
<link rel="stylesheet" href="css/index.css">
<title>首页数据显示</title>
</head>
<body>
	<!-- 插入正文内容 -->
	<s:iterator value="#request.list" var="msg">
		<div class="row clearfix">
			<div class="col-md-4 column">
				<div
					style="height: 140px; width: 140px; margin-top: 20px; float: left">
					<img alt="140x140" src="photo/touxiang.jpg" class="img-thumbnail" />
				</div>
				<div style="float: left; margin: 20px">
					<h4>
						用户名：
						<s:property value="#msg.username" />
					</h4>
					<h5>
						联系邮箱：
						<s:property value="#msg.email" />
					</h5>
				</div>
			</div>
			<div class="col-md-8 column">
				<h2>正文内容：</h2>
				<p <s:if test="#msg.isAdmin==true">style="color:blue"</s:if>>
					<s:property value="#msg.content" />
				</p>
				<p>
					<a style="float: right" class="btn" href="#">查看详情 »</a>
				</p>
			</div>
		</div>
	</s:iterator>
	<!-- 分页 -->
	<s:set value="#request.page" var="page"></s:set>
	<div id="pager">
		<a href="queryMsgForIndex.do?currentPage=1">首页</a> 
		<s:if test="#page.isPrevious">
		<a href="queryMsgForIndex.do?currentPage=<s:property value="#page.currentPage-1" />">上一页</a>
		</s:if>
		<s:if test="#page.isNext">
		<a href="queryMsgForIndex.do?currentPage=<s:property value="#page.currentPage+1" />">下一页</a>
		</s:if>
		<a href="queryMsgForIndex.do?currentPage=<s:property value="#page.totalPageNum" />">尾页</a>
		<br> 当前第&nbsp;
		<s:property value="#page.currentPage" />
		&nbsp;页, 总共&nbsp;
		<s:property value="#page.totalPageNum" />
		&nbsp;页
	</div>
</body>
</html>