<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加数据</title>
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
		<input type="hidden" name="flag" value="doAdd"/>
		<table>
			<tr>
				<td colspan="3">
					<h2>添加数据</h2>
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
				<td>名字:</td>
				<td>
					<input type="text" name="name"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>积分：</td>
				<td>
					<input type="text" name="score"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td>
					<input type="text" name="tel"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" value="男"/>男&nbsp;&nbsp;&nbsp;
					<input type="radio" name="sex" value="女"/>女
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="添加" />
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