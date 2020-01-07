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


<title>모델정보</title>
<script type="text/javascript">
function car(targetUri) {
	form.action = targetUri;
	form.submit();
}
function add(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
	<form name="form" method="get" action="<c:url value='/reserve/main' />">

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
                <a href="<c:url value='/admin/mgrPage' />">붕붕이</a>
              </div>
            </div>

            <div class="col-9  text-right">

              <span class="d-inline-block d-lg-none"><a href="#" class="text-white site-menu-toggle js-menu-toggle py-5 text-white"><span class="icon-menu h3 text-white"></span></a></span>
              <nav class="site-navigation text-right ml-auto d-none d-lg-block" role="navigation">
                <ul class="site-menu main-menu js-clone-nav ml-auto ">
                  <li class="active"><a href="<c:url value='/admin/mgrPage' />" class="nav-link">Home</a></li>
                  <li><a href="<c:url value='/admin/managerView' />" class="nav-link">마이페이지</a></li> <!-- 카 -->
                 <c:if test="${(sessionScope.task == '총관리') || sessionScope.task == '대여예약관리'}">	
					<li><a href="<c:url value='/admin/reserveList' />" class="nav-link" onClick="reseave('<c:url value='/admin/reserveList' />')">예약 관리</a></li>
			</c:if> 
			<c:if test="${(sessionScope.task == '총관리') || sessionScope.task == '대여예약관리'}">	
					<li><a href="<c:url value='/admin/rentList' />" class="nav-link" onClick="rent('<c:url value='/admin/rentList' />')">대여 관리</a></li>
			</c:if> 
				<c:if test="${(sessionScope.task == '총관리') || sessionScope.task == '회원관리'}">
                 <li><a href="<c:url value='/admin/userList' />" class="nav-link" >회원 관리</a></li>
                 </c:if>
                 <c:if test="${(sessionScope.task == '총관리') || sessionScope.task == '상담관리'}">
                  <li><a href="<c:url value='/admin/qnaList' />" class="nav-link">Q&A 관리</a></li> <!-- 어바웃 -->
					</c:if> 
					<c:if test="${(sessionScope.task == '총관리') || sessionScope.task == '자동차관리'}">
                  <li><a href="<c:url value='/admin/modelList' />" class="nav-link">자동차 관리</a></li> <!-- 블로그 -->
                  	</c:if>	
                  <li><a href="../car/main.jsp" class="nav-link" onclick="logout('<c:url value='/user/logout' />')">로그아웃</a></li> <!-- 컨택트 -->
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
              <h1>모델정보</h1>
            </div>
          </div>
        </div>
      </div>
          <div class="site-section bg-light" id="contact-section">
      <div class="container">
        <div class="row justify-content-center text-center">
      
      </div>
        <tr>
	<td width="20"></td>
	<table>
		<tr>
			<td bgcolor="f4f4f4" height="22"><b>모델 상세정보 보기</b>&nbsp;&nbsp;</td>
		</tr>
	</table> <br>
	<table border="1" width="300">
		<tr>
			<td width="50" align="center">모델번호</td>
			<td width="250">
				${model.modelNo}
			</td>
		</tr>
		<tr>
			<td width="50" align="center">모델이름</td>
			<td width="250">
				${model.modelName}
			</td>
		</tr>
		<tr>
			<td width="50" align="center">브랜드</td>
			<td width="250">
				${model.brand}
			</td>
		</tr>
		<tr>
			<td width="50" align="center">탑승인원</td>
			<td width="250">
				${model.passenger}
			</td>
		</tr>
		<tr>
			<td width="50" align="center">연식</td>
			<td width="250">
				${model.year}
			</td>
		</tr>
		<tr>
			<td width="50" align="center">모델 수</td> 
			<td width="250">
				${model.modelCount}
			</td>
		</tr>
		<tr>
			<td width="50" align="center">담당자번호</td>
			<td width="250">
				${model.manager.managerNo}
			</td>
		</tr>
		
	</table>
	<br><br>
	
		

	<br>
	
	<table width="600dp" border="1">
		<tr border="1">
			<td width="190" align="center">자동차번호</td>
			<td width="190" align="center">모델이름</td>
			<td width="190" align="center">가격</td>
		 	<td width="190" align="center">색</td>   
			<td width="190" align="center">이용가능</td>
			<td width="190" align="center">대여횟수</td>   
			<td width="190" align="center">월 대여횟수</td>
		</tr>
			
		<c:forEach var="car" items="${CarList}">
			<tr>
				<td width="190" align="center" bgcolor="ffffff" height="20">
					<a href="<c:url value='/admin/carView'>
							<c:param name='modelNo' value='${model.modelNo}'/>
							<c:param name='carNo' value='${car.carNo}'/>
							</c:url>">
	  				${car.carNo}</a>	       
		  		</td>
		  		<td width="200" align="center" bgcolor="ffffff" style="padding-left: 10">
			  		${car.model.modelName}
	  			</td>
				<td width="200" align="center" bgcolor="ffffff" height="20">
	   				${car.price}        
	  			</td>
	 			<td width="200" align="center" bgcolor="ffffff" height="20">	
					${car.color}
	 			</td>
	 			<td width="200" align="center" bgcolor="ffffff" height="20">	
					${car.occupied}
	 			</td> 
	 			<td width="200" align="center" bgcolor="ffffff" height="20">	
					${car.rentCount}
	 			</td>
	 			<td width="200" align="center" bgcolor="ffffff" height="20">	
					${car.monthRent}
	 			</td> 
	 				
			</tr>
		</c:forEach>
	</table>
	<br><br>
		<c:forEach var="page" begin="1" end="${pageSize}" step="1" varStatus="varStatus">
			<c:if test="${1 != page}">
				&nbsp;&nbsp;
			</c:if>
			<c:if test="${currentPage != page}">
			<a href="<c:url value='/admin/modelView'>
				<c:param name='currentPage' value="${page}"/>
				</c:url>">
			${page}</a>
			</c:if>
			<c:if test="${currentPage == page}">
				${page}
			</c:if>
			<c:if test="${varStatus.end != page}">
				&nbsp;&nbsp;|
			</c:if>
		</c:forEach>
	<br><br>
	<input type="hidden" name="modelNo" value="${model.modelNo}">
	<input type="hidden" name="modelName" value="${model.modelName}">
	<div class="form-group row">
                <div class="col-md-6 mr-auto">
                <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="추가" onClick="add('<c:url value='/admin/addCar' />')">
                <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="목록" onClick="car('<c:url value='/admin/modelList' />')">
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





