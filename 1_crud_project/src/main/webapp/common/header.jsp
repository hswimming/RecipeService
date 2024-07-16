<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나 혼자 먹는다</title>
<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- slick --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<%-- 폰트 --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap" rel="stylesheet">
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/header.css">
<script>
	$(function () {
		$("#search_btn").on("click", search);
		
	});

	function search() {
		location.href = "${path}/recipe/recipeSearch.do";
	}
</script>
</head>
<body>
	<div class="container">
		<%-- header --%>
		<div class="header_container">
			<header>
				<div class="header_inner_container">
					<%-- 로그인 전 --%>
					<c:if test="${empty loginMember}">
						<div id="header_login">
							<a id="signUp" href="${path}/member/memberInsert.do">회원가입</a>
							<div id="loginLine"></div>
							<a href="${path}/auth/login.do">로그인</a>
						</div>
					</c:if>
					<%-- 로그인 후 --%>
					<c:if test="${not empty loginMember}">
						<div id="header_login">
							<a id="myPage" href="${path}/member/mypage.do">마이페이지</a>
							<div id="loginLine"></div>
							<a href="${path}/auth/logout.do">로그아웃</a>
						</div>
					</c:if>
					<%-- 로고 --%>
					<div id="logo">
						<a href="${path}">나 혼자 먹는다 🍜</a>
					</div>
					<%-- 검색 --%>
					<div id="search">
						<form action="${path}/recipe/recipeSearch.do" method="get">
							<div class="search_form">
								<input type="text" name="recipe_search" class="text_box" placeholder="배고프니까 일단 검색!"> 
								<span class="focus_border"></span>
							</div>
							<button id="search_btn" type="submit">
								<img src="${path}/static/images/search.svg">
							</button>
						</form>
					</div>
					<%-- 로그인 유저 아이디 --%>
					<c:if test="${not empty loginMember}">
						<div class="header_login_id">
							<p>${loginMember.m_id}님안녕하세요( ˶'ᵕ'🫶)💕</p>
						</div>
					</c:if>
				</div>
			</header>
		</div>
</body>
</html>