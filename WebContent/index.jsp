<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title id="webtitle">留言板</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/messages_zh.min.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <script>
        $().ready(function(){
            $("#addMsgForm").validate();
            $("#authCodeImg").click(function(){  
          		$(this).attr("src","authCode.do?timestamp="+new Date().getTime());  
         	 }); 
            $("#aboutme").click(function(){alert("这只是一个DEMO～如果你有意见，直接留言哦～～");});
        });
    </script>
</head>

<body>
    <div class="container">
        <!--header-->
        <div class="row clearfix">
            <div class="col-md-12 column">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="index.jsp">首页</a>
                    </li>
                    <li>
                        <a href="admin/login.jsp">后台</a>
                    </li>
                    <li>
                        <a href="#" id="aboutme">关于</a>
                    </li>
                </ul>
                <div class="jumbotron">
                    <h1 id="title1">
                       首页一级标题
                    </h1>
                    <p id="title2">
                       首页二级标题
                    </p>
                    <p>
                        <a class="btn btn-primary btn-large" href="#addMsgForm" id="webbutton">首页按钮</a>
                    </p>
                </div>
            </div>
        </div>
        <!-- 消息提示 -->
        <s:if test="#request.msg!=null">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;
            </button> <s:property value="#request.msg"/>
        </div>
        </s:if>
        <!-- 正文显示 -->
        <div class="row clearfix">
            <div class="col-md-12 column">
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
							<h2>第<s:property value="#msg.id"/>楼：</h2>
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
					<a href="index.do?currentPage=1">首页</a> 
					<s:if test="#page.isPrevious">
					<a href="index.do?currentPage=<s:property value="#page.currentPage-1" />">上一页</a>
					</s:if>
					<s:if test="#page.isNext">
					<a href="index.do?currentPage=<s:property value="#page.currentPage+1" />">下一页</a>
					</s:if>
					<a href="index.do?currentPage=<s:property value="#page.totalPageNum" />">尾页</a>
					<br> 当前第&nbsp;
					<s:property value="#page.currentPage" />
					&nbsp;页, 总共&nbsp;
					<s:property value="#page.totalPageNum" />
					&nbsp;页
				</div>
			</div>
		</div>
        <!-- 留言 -->
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" id="addMsgForm" action="addMsgForIndex.do">
                    <div class="form-group">
                        <label for="name">用户：</label>
                        <input type="text" name="username" required maxlength="8" class="form-control" placeholder="请输入你的名称">
                    </div>
                    <div class="form-group">
                        <label for="name">联系邮箱：</label>
                        <input type="email" name="email" required email="true" class="form-control" placeholder="请输入你的联系邮箱">
                    </div>
                    <div class="form-group">
                        <label for="message">留言内容:</label>
                        <textarea class="form-control" name="content" required rows="5" id="comment"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="name" id="authCodeLabel">验证码：</label>
                        <input type="text" id="authCode" name="authCode" required class="form-control" placeholder="请输入验证码">
                        <img src="authCode.do" id="authCodeImg" alt="单击图片刷新验证码">
                    </div><br>
                    <div class="form-group">
                        <button id="sbtn" type="submit" class="btn btn-default">提交留言！</button>
                    </div>
                </form>
            </div>
        </div>
        <br>
        <!-- footer -->
        <div class="row clearfix">
            <div class="col-md-12 column">
                <blockquote>
                    <p id="copyright1">
                        首页一级版权
                    </p> <small id="copyright2">首页二级版权</cite></small>
                </blockquote>
            </div>
        </div>
    </div>
</body>
<script>
	var xmlhttp;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("GET","<%=request.getContextPath() %>/indexSettings.do",true);
	xmlhttp.send();
	xmlhttp.onreadystatechange=function(){
	    if (xmlhttp.readyState==4 && xmlhttp.status==200){
	         var settings = JSON.parse(xmlhttp.responseText);
	         document.getElementById("webtitle").innerText = settings.WebTitle;
	         document.getElementById("title1").innerText = settings.Title1;
	         document.getElementById("title2").innerText = settings.Title2;
	         document.getElementById("webbutton").innerText = settings.WebButton;
	         document.getElementById("copyright1").innerText = settings.Copyright1;
	         document.getElementById("copyright2").innerText = settings.Copyright2;
	    }
	}
</script>
</html>