<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>计算器</title>
</head>
<body>
	<input type="text" id="num1"/>
	<select id="pt">
		<option>+</option>
		<option>-</option>
		<option>*</option>
		<option>/</option>
	</select>
	<input type="text" id="num2"/>
	<br/>
	<input type="button" value="计算"/ onclick="count()">
	<span id="rs"></span>
</body>
<script type="text/javascript" src="jq/jquery-1.11.2.js"></script>
<script type="text/javascript">
function count() {
	$.post(
			"Ajaxservlet",
			{
				flag: "calculator",
				num1: $("#num1").val(),
				num2: $("#num2").val(),
				pt: $("#pt").val(),
			},
			function (data) {
				$("#rs").html(data);
			},
			"text"
		);
}
	
</script>
</html>