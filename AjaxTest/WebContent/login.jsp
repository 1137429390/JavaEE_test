<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<body>
	<p>
		姓名<input type="text" id="name"/>
		<span id="showMsg1"></span>
	</p>
	<p>
		密码<input type="password" id="password"/>
	</p>
	<p>
		确认密码<input type="password" id="confirmPassword"/>
		<span id="showMsg2"></span>
	</p>
	<p><input type="button" onclick="setup()" value="注册"/></p>
</body>
<script type="text/javascript" src="jq/jquery-1.11.2.js"></script>
<script type="text/javascript">
	$("#name").blur(function () {
		$.post(
				"Ajaxservlet",
				{
					flag: "confirmName",
					name: $("#name").val(),
				},
				function (data) {
					if(data == 1){
						$("#showMsg1").attr("style","color:red");
						$("#showMsg1").html("该账号已存在！");
					}else{
						$("#showMsg1").attr("style","color:green");
						$("#showMsg1").html("该账号可用！");
					}
				},
				"text"
			);
	})
	
	$("#confirmPassword").blur(function () {
		var ps = $("#password").val();
		var cps = $("#confirmPassword").val();
		if(ps == cps){
			$("#showMsg2").attr("style","color:green");
			$("#showMsg2").html("密码可用！");
		}else{
			$("#showMsg2").attr("style","color:red");
			$("#showMsg2").html("两次密码不一致！");
		}
	})
</script>
</html>