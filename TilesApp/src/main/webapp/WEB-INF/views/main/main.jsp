<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- bxslider 라이브러리를 통한 이미지 슬라이드  -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<div class="slider">
	<div>
		<img width="850px" src="images/우유니.gif">
	</div>
	<div>
		<img width="850px" src="images/우유니.jpg">
	</div>
	<div>
		<img width="850px" src="images/우유니2.jpg">
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('.slider').bxSlider({
			mode : 'fade',
			captions : true,
			slideWidth : 600,
			randomStart :'false'
		});
	});
</script>