<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<%@ include file="../common/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/memberInsert.css">
<script>
	$(function () {
		$("#idCheck_btn").on("click", f_idCheck_btn); // 아이디 중복 확인
	});
	
	function f_idCheck_btn() {
		var id = $("#id").val();
		
		if (id == "") {
			alert("아이디를 입력하세요");
			
			document.querySelector("#id").focus();
			return;
		}
		
		$.ajax({
			url : "memberIdCheck.do",
			data : {"id" : $("#id").val()},
			type : "get",
			success : function (responseData) {
				var message = "";
				
				if (responseData == "0") {
					message = "사용 가능한 아이디 입니다";
					
				} else if (responseData == "1") {
					message = "이미 사용중인 아이디 입니다";

					event.preventDefault();
					$("#id").val("");
					document.querySelector("#id").focus();

				} else {
					message = "올바른 아이디 양식이 아닙니다";
					
					event.preventDefault();
					$("#id").val("");
					document.querySelector("#id").focus();
				}
				
				$("#idtMessage").val(message);
				
			},
			error : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<%-- 회원가입 --%>
	<div class="insert_container">
		<div id="con">
			<div id="login">
				<div id="login_form">
					<form action="memberInsert.do" method="post">
						<h3 class="login" style="letter-spacing: -1px;">회원 정보 입력</h3>
						<hr>
						<%-- 아이디 --%>
						<label>
							<p class="form_info">
								* ID
								<input type="button" id="idCheck_btn" value="중복확인">
							</p>
						</label>
						<%-- 아이디 입력 --%>
						<label>
							<p class="form_info"></p>
							<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요" class="size" required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$">
							<input type="text" id="idtMessage" value="영문/숫자/특수문자 _ 가능, 5~12자" class="size" readonly>
						</label>

						<%-- 비밀번호 --%>
						<label>
							<p class="form_info">* Password</p>
						</label>
						<%-- 비밀번호 입력 --%>
						<label>
							<p class="form_info"></p>
							<input type="password" name="pw" placeholder="비밀번호를 입력해주세요" class="size" required pattern="^[A-Za-z0-9]{6,12}$">
							<input type="text" id="idtMessage" value="영문,숫자 6~12자" class="size" readonly>
						</label>

						<%-- 전화번호 --%>
						<label>
							<p class="form_info">* phone</p>
						</label>
						<%-- 전화번호 입력 --%>
						<label>
							<p class="form_info"></p> <input type="text" name="phone" placeholder="전화번호를 입력해주세요" class="size" required pattern="^[0-9]{3}-[0-9]{4}-[0-9]{4}$">
							<input type="text" id="idtMessage" value="010-123-4567 또는 010-1234-5678" class="size" readonly>
						</label>

						<%-- 주소 --%>
						<label>
							<p class="form_info">* Address</p>
							<input type="text" name="address" placeholder="주소를 입력해주세요" class="size" required>
							<input type="text" id="idtMessage" value="" class="size" readonly>
						</label>
						<br>
						<p>
							<input type="submit" value="회원가입" class="btn">
						</p>
					</form>
					<hr>
					<p class="find">
						<span>난 이미 먹을 준비가 됐다 >
							<a href="${path}/auth/login.do">로그인</a>
						</span>
					</p>
				</div>
			</div>
		</div>
	</div>

<%@ include file="../common/footer.jsp"%>