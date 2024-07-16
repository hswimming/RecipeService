<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>잔액 충전 팝업창</title>
<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap" rel="stylesheet">
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/memberMoney.css">
<script>
	$(function () {
		$("#plusMoney_btn").on("click", f_plusMoney_btn);
		$("#close_btn").on("click", f_close_btn);
	});
	
	function f_close_btn() {
		self.close();
	}

	function f_plusMoney_btn() {
		var plusMoney = $("#plusMoney").val();

		if (plusMoney == "" || plusMoney == 0) {
			alert("0원 이상의 금액을 입력하세요");

			document.querySelector("#plusMoney").focus();
			event.preventDefault();
			return;
		}
		
		$.ajax({
			url : "plusMoney.do",
			data : {"plusMoney" : $("#plusMoney").val()},
			type : "post",
			success : function(responseData) {
				console.log(responseData);
				console.log("충전 완료");
				alert("충전 완료");
				
				opener.parent.location.reload();
				self.close();
			},
			error : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
<div class="overlay">
        <div class="popup">
			<label for="money">충전 할 금액을 입력하세요</label>
			<input id="plusMoney" name="plusMoney" type="number">
			<input type="button" id="plusMoney_btn" value="충전">
			<input type="button" id="close_btn" value="닫기">
		</div>
    </div>
</body>
</html>