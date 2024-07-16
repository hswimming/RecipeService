<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<%@ include file="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/memberInfo.css">
<link rel="stylesheet" href="${path}/static/css/memberMoney.jsp">
<script>
	$(function () {
		$("#address_btn").on("click", f_address_btn);
		$("#money_btn").on("click", f_moneyPopup);
	});
	
	function f_moneyPopup() {
		popup = window.open("memberMoney.jsp", "잔액 충전 팝업창", "width=500, height=250, left=600, top=200");
	}
	
	function f_address_btn() {
		var address = $("#address").val();
		
		if (address == "") {
			alert("주소를 입력하세요");
			
			event.preventDefault();
			document.querySelector("#address").focus();
		}
		
		$.ajax({
			url : "mypage.do",
			data : {"address" : $("#address").val()},
			type : "post",
			success : function (responseData) {
				alert("주소가 변경되었습니다");
				$("#address").val(responseData);

				//location.href="${path}/member/mypage.do";
			},
			error : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<%-- 프로필 --%>
	<div class="info_container">
		<div class="info_inner_container">
			<div class="info_title">
				<aside>
					<div class="profile_img">
						<img src="${path}/static/images/pompompurin.png" alt="프로필 사진">
					</div>
					<h2>사용자 이름</h2>
					<ul>
						<li>
							<a href="${path}/member/mypage.do">내프로필</a>
						</li>
						<li>
							<a href="${path}/buy/buyRecipe.do">주문내역</a>
						</li>
						<li>
							<a href="${path}/auth/logout.do">로그아웃</a>
						</li>
					</ul>
				</aside>
			</div>
			<div class="info_content">
				<main>
					<section id="profile" class="content">
						<h2>프로필 정보</h2>
						<p>
							ID : 
							<input id="id" value="${loginMember.m_id}" readonly="readonly">
						</p>
						<p>
							전화번호 : 
							<input id="phone" value="${loginMember.m_phone}" readonly="readonly">
						</p>
						<p>
							주소 : 
							<input id="address" name="address" value="${loginMember.m_address}">
							<button type="submit" id="address_btn" class="info_btn">수정</button>
						</p>
						<p>
							잔액 : 
							<input id="money" name="money" value="${loginMember.m_money}" readonly="readonly">
							<button id="money_btn" class="info_btn">충전</button>
						</p>
					</section>
				</main>
			</div>
		</div>
	</div>

	<%-- 
	<h1>마이페이지</h1>
	<a href="${path}/member/mypage.do">프로필</a>
	<a href="${path}/buy/buyRecipe.do">구매내역</a>
	<ul>
		<li>
			<label for="id">ID : </label>
			<input id="id" value="${loginMember.m_id}" readonly="readonly">
		</li>
		<li>
			<label for="phone">전화번호 : </label>
			<input id="phone" value="${loginMember.m_phone}" readonly="readonly">
		</li>
		<li>
			<label for="address">주소 : </label>
			<input id="address" name="address" value="${loginMember.m_address}">
			<button type="submit" id="address_btn">수정</button>
		</li>
		<li>
			<label for="money">잔액 : </label>
			<input id="money" name="money" value="${loginMember.m_money}" readonly="readonly">
			<button id="money_btn">충전</button>
		</li>
	</ul>
	 --%>

<%@ include file="../common/footer.jsp"%>