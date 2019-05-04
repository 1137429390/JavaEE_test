<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String)request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<style type="text/css">
	.content{
		border: 1px black solid; 
		width: 400px;
		height: 400px;
		margin: 0 auto;
	}
	.content_tbl{
		margin: 80px auto;
	}
}
</style>

</head>
<body>
	<div class="content">
		<form action="Admin/login" method="post">
			<table class="content_tbl">
				<tr>
					<td colspan="2"><h2>管理员登录</h2></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td>
						<input type="text" name="name"/>
					</td>
				</tr>
				<tr>
					<td>密码:</td>
					<td>
						<input type="password" name="password"/>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="submit" value="登录">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
		<%if(msg!=null){ %>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;<%=msg %></p>
		<%} %>
		
	</div>
</body>
</script>
</html>