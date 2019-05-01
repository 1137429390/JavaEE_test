<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String no = (String)request.getAttribute("no"); %>
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
	<form action="CustomerServlet" method="post" onsubmit="return conf()">
		<input type="hidden" name="flag" value="doDel" />
		
		<table>
			<tr>
				<td colspan="3">
					<h2>删除数据</h2>
				</td>
			</tr>
			<tr>
				<td>编号：</td>
				<td>
					<input type="text" readonly="readonly" name="no" value="<%=no%>"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="删除" />
				</td>
				<td>
					<a href="CustomerServlet?flag=goIndex">首页</a>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
<script type="text/javascript">
	function conf() {
		var result = confirm("是否确认删除？");
		return result;
	}
</script>
</html>