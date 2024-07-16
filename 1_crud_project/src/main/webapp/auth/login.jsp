<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<%@ include file="../common/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/memberLogin.css">
<script>
 	$(function () {
		$("form").on("submit", login);
		
		$("#login_btn").on("click", f_login_btn);
	});
 	
 	function f_login_btn(id, pw) {
 		$.ajax({
			url : "login.do",
			data : {"id" : $("#id").val(), "pw" : $("#pw").val()},
			type : "post",
			success : function (responseData) {
				
				if (responseData == "-1") {
					alert("입력하신 계정이 존재하지 않습니다");
					return;
					
				} else if (responseData == "0") {
					alert("아이디와 비밀번호가 일치하지 않습니다");
					return;
					
				} else if (responseData == "1") {
					/* alert("로그인 성공"); */
					location.href="${path}";
					
				} else {
					alert("로그인 오류 !! 관리자에게 문의 하세요 (T⌓T)");
					return;
				}
			},
			error : function (data) {
				alert(data);
			}
		});
	}
 	
 	function login(event) {
		var userId = $("#id").val();
		var userPw = $("#pw").val();
		var message = "";
		
		if (userId == "") {
			message = "아이디를 입력 하세요";
			
			document.querySelector("#id").focus();
			event.preventDefault();
			
		} else if (userPw == "") {
			message = "비밀번호를 입력 하세요";
			
			document.querySelector("#pw").focus();
			event.preventDefault();
			
		} else  {
			message = "아이디와 비밀번호를 확인 하세요";
			
			document.querySelector("#id").focus();
			event.preventDefault();
		}
		
		$("#resultMessage").val(message);
	}
</script>
</head>
<body>
	<%-- 로그인 --%>
	<div class="insert_container">
		<div id="con">
			<div id="login">
				<div id="login_form">
					<form action="login.do" method="post">
						<h3 class="login" style="letter-spacing: -1px;">로그인</h3>
						<hr>
						<%-- 아이디 --%>
						<label for="id">
							<p class="form_info">* ID</p>
						</label>
						<%-- 아이디 입력 --%>
						<label>
							<p class="form_info"></p>
							<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요" class="size" required>
						</label>

						<%-- 비밀번호 --%>
						<label for="pw">
							<p class="form_info">* Password</p>
						</label>
						<%-- 비밀번호 입력 --%>
						<label>
							<p class="form_info"></p>
							<input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요" class="size" required>
						</label>
						<br>
						<p>
							<input type="text" id="resultMessage" value="" class="size" readonly>
							<input type="submit" id="login_btn" value="먹어볼까?" class="btn">
						</p>
					</form>
					<hr>
					<p class="find">
						<span>난 아직 먹을 준비가 되지 않았다 >
							<a href="${path}/member/memberInsert.do">회원가입</a>
						</span>
					</p>
				</div>
			</div>
		</div>

<%@ include file="../common/footer.jsp"%>