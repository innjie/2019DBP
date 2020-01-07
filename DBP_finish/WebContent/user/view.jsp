<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	User user = (User)request.getAttribute("user");
%>
<html>
<head>
<title>회원정보</title>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
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

<script>
function userRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
function user(){
	form.submit();
}
function del(targetUri) {
	form.action = targetUri;
	form.submit();
}
function reseave(targetUri) {
	form.action = targetUri;
	form.submit();
}
function rent(targetUri){
	form.action = targetUri;
	form.submit();
}
function main(targetUri){
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
            	
            
            
              <h1>마이페이지</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
            <div class="container">
          <div class="row align-items-center">
            <div class="col-lg-5">
              <div class="feature-car-rent-box-1">
                <h3>나의 정보</h3>
                <ul class="list-unstyled">
				 <li>
                    <span>사용자ID</span>
                    <span class="spec"><%=user.getId()%></span>
                  </li>
                  <li>
                    <span>이름</span>
                    <span class="spec"><%=user.getName()%></span>
                  </li>
                  <li>
                    <span>전화번호</span>
                    <span class="spec">${user.phone}</span>
                  </li>
                  <li>
                    <span>나이</span>
                    <span class="spec">${user.age}</span>
                  </li>
                  <li>
                    <span>성별</span>
                    <span class="spec">${user.sex}</span>
                  </li>
                  <li>
                    <span>면허번호</span>
                    <span class="spec">${user.license.licenseNo}</span>
                  </li>
                  <li>
                    <span>면허날짜</span>
                    <span class="spec">${user.license.vdate}</span>
                  </li>
                  <li>
                    <span>면허종류</span>
                    <span class="spec">${user.license.type}</span>
                  </li>
                  <li>
                    <span>연체</span>
                    <span class="spec">${user.overdue.overdue}</span>
                  </li>
                  <li>
                    <span>연체 횟수</span>
                    <span class="spec">${user.overdue.count}</span>
                  </li>
                       <li>
                    <span>연체 레벨</span>
                    <span class="spec">${user.overdue.level}</span>
                  </li>
                       <li>
                    <span>이용제한</span>
                    <span class="spec">${user.penalty.restriction}</span>
                  </li>
                </ul>
              </div>
            </div>
          
        
        
        <div class="form-group row">
                <div class="col-md-6 mr-auto">
        <form name="form" method="get" action="<c:url value='/user/update' />">
		<input type="hidden" name="userId" value="<%=user.getId()%>" />
				<input type="button" value="회원정보수정" onClick="user()" class="btn btn-primary">
			<br><br>
			<input type="button" value="탈퇴" onClick="del('<c:url value='/user/delete' />')" class="btn btn-primary">
			<br><br>
				<input type="button" value="예약목록" onClick="reseave('<c:url value='/reserve/list' />')" class="btn btn-primary">
				<br><br>
				<input type="button" value="대여목록" onClick="rent('<c:url value='/rent/list' />')" class="btn btn-primary">
			<br><br>
			<c:if test="${updateFailed || deleteFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
			
	</form>
	</div>
	<div>
        <img src="../images/꼬끼오.png" alt="Image"
                 class="img-fluid" width="150" height="100">
        </div>
	
	
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
  <br>
  
	
</form>
 
</body>
</html>