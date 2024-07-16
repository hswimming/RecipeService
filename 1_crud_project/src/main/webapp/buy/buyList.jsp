<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<%@ include file="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매내역</title>
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/buyList.css">
<script>
	$(function () {
		$("#buy_btn").on("click", f_buy_btn);
	});
	
	function f_buy_btn(bNo) {
		$.ajax({
			url : "${path}/buy/buyDelete.do",
			data : {"bNo" : bNo},
			type : "post",
			success : function (responseData) {
				alert(responseData);
				location.reload();
			},
			error : function (data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<%-- 주문내역 --%>
	<div class="info_container">
		<div class="info_inner_container">
			<div class="info_title">
				<aside>
					<div class="profile_img">
						<img src="${path}/static/images/pompompurin.png">
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
					<%-- 주문 내역 없는 경우 --%>
					<c:if test="${empty buyList}">
						<section id="orders" class="empty_content">
							<h2>${message} (T⌓T)</h2>
							<div class="empty_order">
								<img src="${path}/static/images/pompompurin_profile.png">
							</div>
						</section>
					</c:if>

					<%-- 주문 내역 있는 경우 --%>
					<c:if test="${not empty buyList}">
						<c:forEach items="${buyList}" var="buy">
							<section id="orders" class="content">
								<h2>주문 내역 (${buy.r_name})</h2>
								<div class="order">
									<h3>주문 번호 : ${buy.b_no}</h3>
									<p>상품명 : ${buy.r_name}</p>
									<p>재료 : ${buy.r_food}</p>
									<p>내용 : ${buy.rc_content}</p>
									<p>가격 : ${buy.r_price}</p>
									<p style="color: #1a2867;">
										다른 메뉴가 땡기시나요?
										<button id="buy_btn" class="info_btn" onclick="f_buy_btn(${buy.b_no})">취소</button>
									</p>
								</div>
							</section>
						</c:forEach>
					</c:if>
				</main>
			</div>
		</div>
	</div>

	<%-- 
	<h1>마이페이지</h1>
	<a href="${path}/member/mypage.do">프로필</a>
	<a href="${path}/buy/buyRecipe.do">구매내역</a>
	<table>
		<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>재료</th>
					<th>내용</th>
					<th>가격</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty buyList}">
					<c:forEach items="${buyList}" var="buy">
						<tr>
							<td>${buy.b_no}</td>
							<td>${buy.r_name}</td>
							<td>${buy.r_food}</td>
							<td>${buy.rc_content}</td>
							<td>${buy.r_price}</td>
							<td>
							<button id="buy_btn" onclick="f_buy_btn(${buy.b_no})">취소</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty buyList}">
					<td>${message}</td>
				</c:if>
			</tbody>
	</table>
	 --%>

<%@ include file="../common/footer.jsp"%>