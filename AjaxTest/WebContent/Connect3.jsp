<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="findBig()">刷新</button>
	<select id="big">
		<option value="0">-请选择大区-</option>
	</select>
	<select id="small">
		<option value="0">-请选择小区-</option>
	</select>
	<select id="user">
		<option value="0">-请选择小区-</option>
	</select>
</body>
<script type="text/javascript" src="jq/jquery-1.11.2.js"></script>
<script type="text/javascript">
	function findBig() {
		$.post(
			"ConnetServlet"	,
			{
				flag: "findBig",
			},
			function (data) {
				$("#big option:gt(0)").remove();
				$.each(data, function (index, json) {
					//JSON无法读取Java对象，需要调用toString()方法
					var option = "<option value='"+ (json.bigId).toString() +"'>"+ (json.bigName).toString() +"</option>";
					$("#big").append(option);
				});
			},
			"json"
		);
	}
	$("#big").change(function () {
		$.ajax({
			url: "ConnetServlet",
			type: "post",
			data: {
				flag: "findSmall",
				bigId: $(this).val()
			},
			dataType: "json",
			success: function (data) {
				$("#small option:gt(0)").remove();
				$("#user option:gt(0)").remove();
				$.each(data, function (index, json) {
					var option = "<option value='"+ (json.smallId).toString() +"'>"+ (json.smallName).toString() +"</option>"
					$("#small").append(option);
				});
			},
			error: function (data) {
				alert("后台错误");
			}
		}); 
	});
	$("#small").change(function () {
		$.ajax({
			url: "ConnetServlet",
			type: "post",
			data: {
				flag: "findUser",
				smallId: $(this).val()
			},
			dataType: "json",
			success: function (data) {
				$("#user option:gt(0)").remove();
				$.each(data, function (index, json) {
					var option = "<option value='"+ (json.userId).toString() +"'>"+ (json.userName).toString() +"</option>"
					$("#user").append(option);
				});
			},
			error: function (data) {
				alert("后台错误");
			}
		});
	})
	
</script>
</html>