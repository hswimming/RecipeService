<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<%@ include file="./common/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나 혼자 먹는다</title>
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- slick --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/home.css">
</head>
<body>
	<%-- section --%>
	<section>
		<div class="section_container">
			<%-- slick slider --%>
			<div class="banner">
				<div>
					<img src="${path}/static/images/banner01.jpg">
				</div>
				<div>
					<img src="${path}/static/images/banner02.jpg">
				</div>
				<div>
					<img src="${path}/static/images/banner03.jpg">
				</div>
			</div>
			<%-- 베스트 --%>
			<div class="best_ranking_text">
				<p>⭐ 실시간 BEST ⭐</p>
			</div>
			<div class="best_food_list">
				<div id="food">
					<figure class="best_1">
						<img id="best_img_1" src="${path}/static/images/best01.jpg">
						<figcaption>
							<p>
								<a id="best_content1" href="">
									레시피 내용
								</a>
							</p>
							<div>
								<h2>
									<span id="best_name1">레시피 이름</span>
								</h2>
							</div>
						</figcaption>
					</figure>
				</div>
				<div id="food">
					<figure class="best_1">
						<img id="best_img_2" src="${path}/static/images/best02.jpg">
						<figcaption>
							<p>
								<a id="best_content2" href="">
									레시피 내용
								</a>
							</p>
							<div>
								<h2>
									<span id="best_name2">레시피 이름</span>
								</h2>
							</div>
						</figcaption>
					</figure>
				</div>
				<div id="food">
					<figure class="best_1">
						<img id="best_img_3" src="${path}/static/images/best03.jpg">
						<%-- <img src="${path}/static/images/food/${recipe. r_no}.jpg"> --%>
						<figcaption>
							<p>
								<a id="best_content3" href="">
									레시피 내용
								</a>
							</p>
							<div>
								<h2>
									<span id="best_name3">레시피 이름</span>
								</h2>
							</div>
						</figcaption>
					</figure>
				</div>
			</div>
		</div>
	</section>
	<script>
		$.noConflict(); // slick js 라이브러리 충돌 방지

		$(document).ready(function() {
			$(".banner").slick({
				slidesToShow : 2,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 2000,
			});

			// 서버 실행 시 많이 판매된 레시피 3개 메인화면에 보여주기
			$.ajax({
				url : "${path}/recipe/BestRecipe",
				type : "get",
				success : function(responseData) {
					console.log(responseData);

					var obj = JSON.parse(responseData);
					var bestList = obj["bestList"];
					// var output = "";

					$("#best_name1").text(bestList[0].r_name);
					$("#best_name2").text(bestList[1].r_name);
					$("#best_name3").text(bestList[2].r_name);

					$("#best_content1").text(bestList[0].rc_content);
					$("#best_content2").text(bestList[1].rc_content);
					$("#best_content3").text(bestList[2].rc_content);
					
					$("#best_img_1").attr("src", "${path}/static/images/food/" + bestList[0].r_no + ".jpg");
					$("#best_img_2").attr("src", "${path}/static/images/food/" + bestList[1].r_no + ".jpg");
					$("#best_img_3").attr("src", "${path}/static/images/food/" + bestList[2].r_no + ".jpg");
					console.log(bestList[0].r_no);

					/*
					$.each(bestList, function(index, item) {
						console.log(item);

						output += "<span>" + item.r_name + "</span>"
						
						$("#here").html(output);
					});
					 */
				},
				error : function(data) {
					alert(data);
				}
			});
		});

		// 마우스 효과
		$(".hover").mouseleave(function() {
			$(this).removeClass("hover");
		});
	</script>
	
<%@ include file="./common/footer.jsp"%>