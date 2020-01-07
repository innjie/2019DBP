<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ page import="java.util.*, java.text.*" %> 
<%@page import="model.*" %>      
<% request.setCharacterEncoding("UTF-8"); %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function insertReserve() {
   var s = '';
   
   if(form.customer.value == "") {
      s += '예약자 ID';
   }
   if(form.model.value == "") {
      s += ', 모델';
   }
   if(form.brand.value == "") {
      s += ', 브랜드';
   }
   if(form.passenger.value == "") {
      s += ', 탑승 수';
   }
   if(form.rentDate.value == "") {
      s += ', 대여 예정일';
   }
   if(form.rtnDate.value == "") {
      s += ', 반납 예정일';
   }
   
   
   if(s != "") {
      var v = "빈 항목 :";
      v = v + s;
      alert(v);
      form.customer.focus();
      return false;
      
   }   
   
   var datatimeRegexp = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
   var rentyear = parseInt(form.rentDate.value.substring(0, 4));
   var rentmonth = parseInt(form.rentDate.value.substring(5, 7));
   var rentday = parseInt(form.rentDate.value.substring(8, 10));
   var rtnyear = parseInt(form.rtnDate.value.substring(0, 4));
   var rtnmonth = parseInt(form.rtnDate.value.substring(5, 7));
   var rtnday = parseInt(form.rtnDate.value.substring(8, 10));
   const tone = [1, 3, 5, 7, 8, 10, 12];
   const tzero = [4, 6, 9, 11];
   const te = [2]
   
 
   if ( datatimeRegexp.test(form.rentDate.value) == false) {
       alert("날짜는 yyyy-mm-dd 형식입니다.");
       return false;
   }
   if(datatimeRegexp.test(form.rtnDate.value) == false) {
	   alert("날짜는 yyyy-mm-dd 형식입니다.")
	   return false;
   }
   
   if ( rentmonth < 1 || rentmonth > 12 ||rtnmonth < 1 || rtnmonth > 12) {
       alert("1월부터 12월까지로 입력해주세요.");
       return false;
   }
   if(tone.includes(rtnmonth)){
	   if(rtnday > 31 || rtnday < 1) {
		   	alert("1일부터 31일 까지로 입력해주세요.")
		   	return false;
	   }
   }
   if(tone.includes(rentmonth)){
	   if(rentday > 31 || rentday < 1) {
		   	alert("1일부터 31일 까지로 입력해주세요.")
		   	return false;
	   }
   }
   if(tzero.includes(rentmonth)){
	   if(rentday > 30 || rentday < 1) {
		   	alert("1일부터 30일 까지로 입력해주세요.")
		   	return false;
	   }
   }
   if(tzero.includes(rtnmonth)){
	   if(rtnday > 30 || rtnday < 1) {
		   	alert("1일부터 30일 까지로 입력해주세요.")
		   	return false;
	   }
   }
   if(te.includes(rentmonth)){
	   var result = leap_year(rentyear);
	   if(result){
		   if(rentday > 29 || rentday < 1 ) {
			   	alert("1일부터 29일 까지로 입력해주세요.")
			   	return false;
		   }
	   }
	   else{
		   if(rentday > 28 || rentday < 1) {
			   	alert("1일부터 28일 까지로 입력해주세요.")
			   	return false;
		   }
	   }
	   
   }
   if(te.includes(rtnmonth)){
	   var result = leap_year(rtnyear);
	   
	   if(result){
		   if(rtnday > 29 || rtnday < 1) {
			   	alert("1일부터 29일 까지로 입력해주세요.")
			   	return false;
		   }
	   }
	   else{
		   if(rtnday > 28 || rtnday < 1) {
			   	alert("1일부터 28일 까지로 입력해주세요.")
			   	return false;
		   }
	   }	  
   }
   
   if(form.rentDate.value.length >10 ){   
	   alert('대여일을 다시입력해주세요.');
	   return false;	}
   if(form.rtnDate.value.length > 10){   
	   alert('반납일을 다시 입력해주세요.');
	   return false;	}
   
   
   if(form.rentDate.value < form.rsvDate.value) {
	   alert('대여일은 예약일보다 늦어야 합니다.');
	   return false;
   }
   if(form.rtnDate.value < form.rentDate.value) {
	   alert('반납일은 대여일보다 늦어야 합니다.');
	   return false;
   }
   
   
   form.submit();
}

function leap_year(year){
	var result;
	if(year % 400 == 0)
		result = true;	
	else 
		if(year % 100 == 0)
			result = false;
		else
			if(year % 4 == 0)
				result = true;
			
			else
				result = false;
	
	return result;
}

</script>
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


<title>예약 목록</title>
<%
   Date date = new Date();
   SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
   String strdate = simpleDate.format(date);
%>

</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
       <form name="form" method="post" action="<c:url value='/reserve/view'/>">
			
	
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
              <h1>예약목록</h1>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section bg-light" id="contact-section">
      <div class="container">
        <div class="row justify-content-center text-center">
      
      </div>
   		<table border=1>
   			<tr>
   				<td align="center"> 예약자  </td>
   				<td align="center"><input type="text" name="customer" width="200dp" value="${customer.name}"></td>
   			</tr>
   			<tr>
   				<td align="center"> 모델  </td>
   				<td align="center"><input type="text" name="model" width="200dp" value="${car.model.modelName}" readonly></td>
   			</tr>
   			<tr>
   				<td align="center"> 브랜드  </td>
   				<td align="center"><input type="text" name="brand" width="200dp" value="${car.model.brand}" readonly></td>
   			</tr>
   			<tr>
   				<td align="center"> 색  </td>
   				<td align="center"><input type="text" name="color" width="200dp" value="${car.color}" readonly></td>
   			</tr>
   			<tr>
   				<td align="center"> 가격  </td>
   				<td align="center"><input type="text" name="price" width="200dp" value="${car.price}" readonly></td>
   			</tr>
   			<tr>
   				<td align="center"> 탑승자 수  </td>
   				<td align="center"><input type="text" name="passenger" width="200dp" value="${car.model.passenger}" readonly></td>
   			</tr>
   			<tr>
   				<td align="center"> 예약일  </td>
   				<td align="center"><input type="text" name="rsvDate" width="200dp" value=<%=strdate %> readonly></td>
   			</tr>
   			<tr>
   				<td align="center"> 대여 예정일(yyyy-mm-dd)  </td>
   				<td align="center"><input type="text" name="rentDate" width="200dp" value=<%=strdate %>></td>
   			</tr>
   			<tr>
   				<td align="center"> 반납예정일(yyyy-mm-dd)  </td>
   				<td align="center"><input type="text" name="rtnDate" width="200dp" value=<%=strdate %>></td>
   			</tr>
   		</table>
<br>
   <input type="hidden" name="carNo" value='${car.carNo}'>
   <input type="hidden" name="modelNo" value='${car.model.modelNo}'>
   <div class="form-group row">
                <div class="col-md-6 mr-auto">
                <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="예약" onClick="insertReserve()">&nbsp;
                <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="취소" onClick="history.back()">
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