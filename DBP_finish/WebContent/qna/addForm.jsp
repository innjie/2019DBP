<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=DM+Sans:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="../fonts/icomoon/style.css">

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="../css/jquery.fancybox.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <link rel="stylesheet" href="../css/owl.theme.default.min.css">
    <link rel="stylesheet" href="../fonts/flaticon/font/flaticon.css">
    <link rel="stylesheet" href="../css/aos.css">

    <!-- MAIN CSS -->
    <link rel="stylesheet" href="../css/style.css">

<title>상담 검색 내용</title>
<script type="text/javascript">
function addQnA(){
	if(form.question.value == "") {
		alert("문의내용을 입력하십시오.");
		form.question.focus();
		return false;
	}
	
	form.submit();
}
function qnaList(targetUri) {
	form.action = targetUri;
	form.submit();
}

</script>


</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
<div class="site-wrap" id="home-section">

      <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
          <div class="site-mobile-menu-close mt-3">
            <span class="icon-close2 js-menu-toggle"></span>
          </div>
        </div>
        <div class="site-mobile-menu-body"></div>
      </div>



      <header class="site-navbar site-navbar-target" role="banner">

        <div class="container">
          <div class="row align-items-center position-relative">

            <div class="col-3 ">
              <div class="site-logo">
                <a href="<c:url value='/car/main' />">붕붕이</a>
              </div>
            </div>

            <div class="col-9  text-right">
              

              <span class="d-inline-block d-lg-none"><a href="#" class="text-white site-menu-toggle js-menu-toggle py-5 text-white"><span class="icon-menu h3 text-white"></span></a></span>

              

              <nav class="site-navigation text-right ml-auto d-none d-lg-block" role="navigation">
                <ul class="site-menu main-menu js-clone-nav ml-auto ">
                  <li class="active"><a href="<c:url value='/car/main' />" class="nav-link">Home</a></li>
                  <li><a href="<c:url value='/user/view' />" class="nav-link">마이페이지</a></li> <!-- 카 -->
                  <li><a href="<c:url value='/qna/list' />" class="nav-link">Q&A</a></li> <!-- 어바웃 -->
                  <li><a href="<c:url value='/car/MonthlyCar' />" class="nav-link">이달의 차</a></li> <!-- 블로그 -->
                  <li><a href="<c:url value='/user/logout' />" class="nav-link" onclick="logout()">로그아웃</a></li> <!-- 컨택트 -->
                </ul>
              </nav>
            </div>

            
          </div>
        </div>

      </header>

    <div class="ftco-blocks-cover-1">
      <div class="ftco-cover-1 overlay innerpage" style="background-image: url('../images/hero_2.jpg')">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-lg-6 text-center">
              <h1>상담하기</h1>
              <p>상담 내용을 입력해주세요.</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section bg-light" id="contact-section">
      <div class="container">
        <div class="row justify-content-center text-center">
        <div class="col-7 text-center mb-5">
          <h2>관리자가 빠르고 친절하게 답변해 드립니다.</h2>
          <p>붕붕이 이용에 불편하신 점이나, 궁금하신 점이 있으신가요?
          <br>
불편하신점이나 궁금하신 점이 있으시면 이곳에 글을 남겨주세요. 
       <br>
고객님의 작은소리에도 언제나 귀 기울이는 붕붕이가 되겠습니다.
<br></p>
        </div>
      </div>
        <div class="row">
          <div class="col-lg-8 mb-5" >
            <form action="<c:url value='/qna/addForm' />" method="post" name="form">

			<div class="form-group row">
                <div class="col-md-12">
              		  공개여부
					<select name="secret">
						<option value="N">공개</option>
						<option value="Y">비밀</option>
					</select>                
				</div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <textarea name="question" id="" class="form-control" placeholder="상담내용을 입력하세요." cols="30" rows="10"></textarea>
                </div>
              </div>
              
              <div class="form-group row">
                <div class="col-md-6 mr-auto">
                  <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="상담 추가" onClick="addQnA()">
				<input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="상담 목록" onClick="qnaList('<c:url value='/qna/list' />')">
                </div>
              </div>
            </form>
            
          </div>
          <div class="col-lg-4 ml-auto">
            <div class="bg-white p-3 p-md-5">
              <h5 class="text-black mb-4">보다 빠른 연락을 원하시면 아래 연락처를 활용하세요</h5>
              <ul class="list-unstyled footer-link">
                <li class="d-block mb-3">
                  <span class="d-block text-black">주소 : </span>
                  <span>서울특별시 성북구 월곡2동 화랑로13길 60</span></li>
                <li class="d-block mb-3"><span class="d-block text-black">전화번호 : </span><span>010-****-****</span></li>
                <li class="d-block mb-3"><span class="d-block text-black">이메일 : </span><span>********@dongduk.ac.kr</span></li>
              </ul>
            </div>
          </div>
          
          
              <div id="map" style="width:700px;height:300px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3b8032c1de1aee79deee694c5b470e41"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
    		center: new kakao.maps.LatLng(37.6063202, 127.041808), // 지도의 중심좌표
    		level: 3 // 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		var markerPosition  = new kakao.maps.LatLng(37.6063202, 127.041808); 

		var marker = new kakao.maps.Marker({
		position: markerPosition
		});

		marker.setMap(map);
		var iwContent = '<div style="padding:5px;"> 붕붕이 <br><a href="https://map.kakao.com/link/map/Hello World!,37.6063202,127.041808" style="color:blue" target="_blank">큰지도</a> <a href="https://map.kakao.com/link/to/Hello World!,37.6063202,127.041808" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker); 
</script>
              
       
        </div>
      </div>
    </div>

   

    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <h2 class="footer-heading mb-4">About Us</h2>
                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
          </div>
          <div class="col-lg-8 ml-auto">
            <div class="row">
              <div class="col-lg-3">
                <h2 class="footer-heading mb-4">Quick Links</h2>
                <ul class="list-unstyled">
                  <li><a href="#">About Us</a></li>
                  <li><a href="#">Testimonials</a></li>
                  <li><a href="#">Terms of Service</a></li>
                  <li><a href="#">Privacy</a></li>
                  <li><a href="#">Contact Us</a></li>
                </ul>
              </div>
              <div class="col-lg-3">
                <h2 class="footer-heading mb-4">Quick Links</h2>
                <ul class="list-unstyled">
                  <li><a href="#">About Us</a></li>
                  <li><a href="#">Testimonials</a></li>
                  <li><a href="#">Terms of Service</a></li>
                  <li><a href="#">Privacy</a></li>
                  <li><a href="#">Contact Us</a></li>
                </ul>
              </div>
              <div class="col-lg-3">
                <h2 class="footer-heading mb-4">Quick Links</h2>
                <ul class="list-unstyled">
                  <li><a href="#">About Us</a></li>
                  <li><a href="#">Testimonials</a></li>
                  <li><a href="#">Terms of Service</a></li>
                  <li><a href="#">Privacy</a></li>
                  <li><a href="#">Contact Us</a></li>
                </ul>
              </div>
              <div class="col-lg-3">
                <h2 class="footer-heading mb-4">Quick Links</h2>
                <ul class="list-unstyled">
                  <li><a href="#">About Us</a></li>
                  <li><a href="#">Testimonials</a></li>
                  <li><a href="#">Terms of Service</a></li>
                  <li><a href="#">Privacy</a></li>
                  <li><a href="#">Contact Us</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div class="row pt-5 mt-5 text-center">
          <div class="col-md-12">
            <div class="border-top pt-5">
              <p>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart text-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" >Colorlib</a>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </p>
            </div>
          </div>

        </div>
      </div>
    </footer>

    </div>

    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.sticky.js"></script>
    <script src="../js/jquery.waypoints.min.js"></script>
    <script src="../js/jquery.animateNumber.min.js"></script>
    <script src="../js/jquery.fancybox.min.js"></script>
    <script src="../js/jquery.easing.1.3.js"></script>
    <script src="../js/bootstrap-datepicker.min.js"></script>
    <script src="../js/aos.js"></script>

    <script src="../js/main.js"></script>
</body>
</html>

