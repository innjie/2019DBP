<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*" %>      
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


<title>대여목록</title>
<script type = "text/javascript">
function review(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
       <form name="form" method="get" action="<c:url value='/rent/list' />">
			
	
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
              <h1>대여 상세정보</h1>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section bg-light" id="contact-section">
      <div class="container">
        <div class="row justify-content-center text-center">
      
      </div>
	<table border="1">
		<tr>
			<td align="center">대여 번호</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.rentNo}       
			</td>
		</tr>
		<tr>
			<td align="center">예약번호</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.reserveNo}       
			</td>
		</tr>
		<tr>
			<td align="center">대여자</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.lender}     
			</td>
		</tr>
		<tr>
			<td align="center">대여날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.rentDate}        
			</td>
		</tr>
		<tr>
			<td align="center">반납날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.returnDate}      
			</td>
		</tr>
		<tr>
			<td align="center">실제대여날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.rentDate}        
			</td>
		</tr>
		<tr>
			<td align="center">실제반납날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.rtnDate}      
			</td>
		</tr>
		<tr>
			<td align="center">반납여부</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.returned}      
			</td>
		</tr>
		<tr>
			<td align="center">차종</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.car.model.modelName}     
			</td>
		</tr>
		<tr>
			<td align="center">가격</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.car.price}     
			</td>
		</tr>
		<tr>
			<td align="center">색깔</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.car.color}     
			</td>
		</tr>
	</table>
	<br><br>
	<input type="hidden" name="rentNo" value='${rent.rentNo}'>
	<input type="hidden" name="cNo" value='${ rent.reserve.user.customerNo }'>
	<div class="form-group row">
                <div class="col-md-6 mr-auto">
                <input type="submit" class="btn btn-block btn-primary text-white py-3 px-5" value="목록">
                <c:if test="${rent.returned == 'Y'}">
		<input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="리뷰" onClick="review('<c:url value='/rent/review'/>')">
	</c:if>
                </div>
              </div>
	</td>
  </tr>
      </div>
    </div>
</form>
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
	</form>
</body>
</html>











<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@page import="model.*" %>
<% request.setCharacterEncoding("UTF-8"); %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대여 목록</title>
<script type = "text/javascript">
function review(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
<form name="form" method="get" action="<c:url value='/rent/list' />">
	<table>
		<tr>
			<td bgcolor="f4f4f4" height="22"><b>대여 상세정보 보기</b>&nbsp;&nbsp;</td>
		</tr>
	</table> <br>
	<table border="1">
		<tr>
			<td align="center">대여 번호</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.rentNo}       
			</td>
		</tr>
		<tr>
			<td align="center">예약번호</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.reserveNo}       
			</td>
		</tr>
		<tr>
			<td align="center">대여자</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.lender}     
			</td>
		</tr>
		<tr>
			<td align="center">대여날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.rentDate}        
			</td>
		</tr>
		<tr>
			<td align="center">반납날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.returnDate}      
			</td>
		</tr>
		<tr>
			<td align="center">실제대여날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.rentDate}        
			</td>
		</tr>
		<tr>
			<td align="center">실제반납날짜</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.rtnDate}      
			</td>
		</tr>
		<tr>
			<td align="center">반납여부</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.returned}      
			</td>
		</tr>
		<tr>
			<td align="center">차종</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.car.model.modelName}     
			</td>
		</tr>
		<tr>
			<td align="center">가격</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.car.price}     
			</td>
		</tr>
		<tr>
			<td align="center">색깔</td>
			<td align="center" bgcolor="ffffff" height="20">
	  			${rent.reserve.car.color}     
			</td>
		</tr>
	</table>
	<br><br>
	<input type="hidden" name="rentNo" value='${rent.rentNo}'>
	<input type="hidden" name="cNo" value='${ rent.reserve.user.customerNo }'>
	<input type="submit" value="목록">
	<c:if test="${rent.returned == 'Y'}">
		<input type="button" value="리뷰" onClick="review('<c:url value='/rent/review'/>')">
	</c:if>
	<br>
</form>

</body>
</html> --%>