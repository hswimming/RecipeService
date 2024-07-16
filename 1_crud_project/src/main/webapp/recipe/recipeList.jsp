<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<%@ include file="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 전체 리스트</title>
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/recipeList.css">
<script>
	$(function () {
		$("#recipe_btn").on("click", f_recipe_btn);
	});
	
	function f_recipe_btn(rNo) {
		// location.href='${path}/buy/buyRecipe.do?rNo=${recipe. r_no}'
		$.ajax({
			url : "${path}/buy/buyRecipe.do",
			data : {"rNo" : rNo},
			type : "post",
			success : function (responseData) {
				alert(responseData);
			},
			error : function (data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<%-- 판매 리스트 --%>
	<div class="recipe_container">
		<div class="recipe_inner_container">
			<c:forEach items="${recipeAll}" var="recipe">
				<div class="item">
					<img src="${path}/static/images/food/${recipe. r_no}.jpg">
					<h2>${recipe. r_no}. ${recipe.r_name}</h2>
					<p>${recipe.rc_content}</p>
					<span class="price">${recipe.r_price}원</span>
					<button id="recipe_btn" onclick="f_recipe_btn(${recipe. r_no})">바로 구매하기</button>
				</div>
			</c:forEach>
			
			<%-- 검색 결과 --%>
			<c:if test="${not empty searchRecipe}">
				<c:forEach items="${searchRecipe}" var="search">
					<div class="item">
						<img src="${path}/static/images/food/${search. r_no}.jpg">
						<h2>${search.r_no}.${search.r_name}</h2>
						<p>${search.rc_content}</p>
						<span class="price">${search.r_price}원</span>
						<button id="recipe_btn" onclick="f_recipe_btn(${search. r_no})">바로 구매하기</button>
					</div>
				</c:forEach>
			</c:if>
			
			<%-- 결과 없음 --%>
			<c:if test="${empty recipeAll && empty searchRecipe}">
			<div class="item">
                    <h2>${message}</h2>
                    <div class="empty_search">
                        <img src="${path}/static/images/pompompurin_profile.png">
                    </div>
                </div>
			</c:if>
		</div>
	</div>
	
	<%-- 
	<h1>검색 결과</h1>
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
			<c:forEach items="${recipeAll}" var="recipe">
				<tr>
					<td>${recipe. r_no}</td>
					<td>${recipe.r_name}</td>
					<td>${recipe.r_food}</td>
					<td>${recipe.rc_content}</td>
					<td>${recipe.r_price}</td>
					<td>
						<button id="recipe_btn" onclick="f_recipe_btn(${recipe. r_no})">구매</button>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${not empty searchRecipe}">
				<c:forEach items="${searchRecipe}" var="search">
					<tr>
						<td>${search.r_no}</td>
						<td>${search.r_name}</td>
						<td>${search.r_food}</td>
						<td>${search.rc_content}</td>
						<td>${search.r_price}</td>
						<td>
							<button id="recipe_btn" onclick="f_recipe_btn(${search. r_no})">구매</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty searchRecipe}">
				<td>${message}</td>
			</c:if>
		</tbody>
	</table>
	 --%>

<%@ include file="../common/footer.jsp"%>