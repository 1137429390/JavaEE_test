<%@page import="com.lab.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Customer cs = (Customer)request.getAttribute("cs"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.content{
		border: 1px black solid; 
		width: 400px;
		height: 400px;
		margin: 0 auto;
	}
	table {
		margin: 80px auto;
	}
</style>
</head>
<body>
	<div class="content">
	<form action="CustomerServlet" method="post">
		<input type="hidden" name="flag" value="doUpdate"/>
		<table>
			<tr>
				<td colspan="3">
					<h2>修改数据</h2>
				</td>
			</tr>
			<tr>
				<td>编号：</td>
				<td>
					<input type="text" name="no" value="<%=cs.getNo() %>"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>名字:</td>
				<td>
					<input type="text" name="name" value="<%=cs.getName() %>"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>积分：</td>
				<td>
					<input type="text" name="score" value="<%=cs.getScore() %>"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td>
					<input type="text" name="tel" value="<%=cs.getTel() %>"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" value="男" 
						<%if("男".equals(cs.getSex())) { %>
							checked="checked"
						<% } %>
					/>男&nbsp;&nbsp;&nbsp;
					<input type="radio" name="sex" value="女" 
						<%if("女".equals(cs.getSex())) { %>
							checked="checked"
						<% } %>
					/>女
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="确定" />
				</td>
				<td>
					<a href="CustomerServlet?flag=goIndex">首页</a>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>