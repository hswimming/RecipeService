<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<c:set var="path" value="${pageContext.request.servletContext.contextPath}" />
<script src="${path}/static/js/jquery-3.7.1.min.js"></script>
<%-- slick --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<%-- css --%>
<link rel="stylesheet" href="${path}/static/css/footer.css">
</head>
<body>
	<%-- footer --%>
	<footer>
		<div class="footer_container">
			<div class="footer_inner_container">
				<%-- sns 아이콘  --%>
				<div class="footer_sns">
					<div class="footer_sns_logo">
						<img src="${path}/static/images/logo_instagram.svg">
					</div>
					<div class="footer_sns_logo">
						<img src="${path}/static/images/logo_youtube.svg">
					</div>
					<div class="footer_sns_logo">
						<img src="${path}/static/images/logo_facebook.svg">
					</div>
				</div>
				<%-- 내용 --%>
				<div class="footer_text">
					<p>(주)나 혼자 먹는다</p>
					<p>서울 마포구 월드컵북로4길 77</p>
					<p>사업자번호 | 123-12-12345</p>
					<p>통신판매업 | 서울 마포-1234</p>
					<p>MON-FRI 09:00 - 17:50 | LUNCH 12:50 - 14:00 | SAT, SUN, HOLIDAY OFF</p>
					<p>Tel.1544-9970</p>
				</div>
				<%-- 하단 로고 --%>
				<div class="footer_img">
					<img src="${path}/static/images/footer_logo.png">
				</div>
				<div class="fotter_corp">
					<p>Copyright ⓒ 2024 나 혼자 먹는다 All rights reserved.</p>
				</div>
			</div>
		</div>
	</footer>
	</div>
</body>
</html>