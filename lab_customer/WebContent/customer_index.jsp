<%@page import="com.lab.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.lab.model.CustomerPage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% CustomerPage cp = (CustomerPage)request.getAttribute("cp"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<style type="text/css">
	.content{
		border: 1px black solid; 
		width: 450px;
		height:auto;
		margin: 0 auto;
	}
	table {
		margin: 20px auto;
	}
</style>
</head>
<body>
<div class="content">
	<form action="CustomerServlet" method="post">
		<input type="hidden" name="flag" value="doSearch" />
		<table>
			<tr>
				<td colspan="3">
					<h2>查询</h2>
				</td>
			</tr>
			<tr>
				<td>编号：</td>
				<td>
					<input type="text" name="no"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>手机号：</td>
				<td>
					<input type="text" name="tel"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="搜索" />
				</td>
				<td>
					<a href="CustomerServlet?flag=goAdd">添加</a>
				</td>
			</tr>
		</table>
	</form>
	<br/><br/>
	<table border="1">
		<tr>
			<td>#</td>
			<td>编号</td>
			<td>姓名</td>
			<td>积分</td>
			<td>手机号</td>
			<td>性别</td>
			<td>操作</td>
		</tr>
		<%	int count = 1;
			for (Customer cs : cp.getList()) {%>
			<tr>
				<td><%=count %></td>
				<td><%=cs.getNo() %></td>
				<td><%=cs.getName() %></td>
				<td><%=cs.getScore() %></td>
				<td><%=cs.getTel() %></td>
				<td><%=cs.getSex() %></td>
				<td>
					<a href="CustomerServlet?flag=goUpdate&no=<%=cs.getNo() %>">修改</a>&nbsp;&nbsp;
					<a href="CustomerServlet?flag=goDelete&no=<%=cs.getNo() %>">删除</a>
				</td>
			</tr>
		<%
				count++;
			}%>
			
	</table>
	<div style="margin-left: 40px;">
		<%if(cp.getCurrentPge() != 1){ %>
			<a href="CustomerServlet?flag=goIndex&currentPage=<%=cp.getCurrentPge() -1 %>">上一页</a>
		<%} %>
		<%for(int i = 1;i <= cp.getTotalPage();i++) {%>
			<a href="CustomerServlet?flag=goIndex&currentPage=<%=i %>"><%=i %></a>
		<%} %>
		<%if(cp.getCurrentPge() != cp.getTotalPage()){ %>
			<a href="CustomerServlet?flag=goIndex&currentPage=<%=cp.getCurrentPge() +1 %>">下一页</a>	
		<%} %>
		总共<%=cp.getTotalPage() %>页
	</div>
</div>
</body>
</html>