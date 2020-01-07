<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*" %>      
<%@ page import="java.util.*, java.text.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	User user = (User) request.getAttribute("user");
	License license = (License) request.getAttribute("license");
%>   
<%
	Date date = new Date();
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	String today = simpleDate.format(date);
%>
<!DOCTYPE html>
<html>
<head>
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


<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
function userModify() {
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	var s = '';

    if(form.licenseno.value == "") {
    	s += ' 면허번호';
    }
    if(form.vdate.value == "") {
		s += ' 면허날짜';
	}
    if(form.type.value == "") {
    	s += ' 면허종류';
    }
    if(s != "") {
    	var v = "빈 항목 :";
    	v = v + s;
    	alert(v);
    	form.customer.focus();
    	return false;
    } 
    var datatimeRegexp = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
    var year = parseInt(form.vdate.value.substring(0, 4));
    var month = parseInt(form.vdate.value.substring(5, 7));
    var day = parseInt(form.vdate.value.substring(8, 10));
    const tone = [1, 3, 5, 7, 8, 10, 12];
    const tzero = [4, 6, 9, 11];
    const te = [2];
    
   
    if(form.strdate.value > form.vdate.value) {
    	alert("만료된 면허날짜입니다.");
    	return false;
    }
    
    if ( datatimeRegexp.test(form.vdate.value) == false) {
    	alert("날짜는 yyyy-mm-dd 형식입니다.");
    	return false;
    }
    if (month < 1 || month > 12) {
    	alert("1월부터 12월까지로 입력해주세요.");
    	return false;
    }
    
    if(tone.includes(month)){
    	if(day > 31 || day < 1) {
    		alert("1일부터 31일 까지로 입력해주세요.");
    		return false;
    	}
    }
    if(tzero.includes(month)){
    	if(day > 30 || day < 1) {
    		alert("1일부터 30일 까지로 입력해주세요.");
    		return false;
    	}
    }
    if(te.includes(month)){
    	var result = leap_year(year);
    	if(result){
    		if(day > 29 || day < 1 ) {
    			alert("1일부터 29일 까지로 입력해주세요.");
    			return false;
    		}
    	}
    	else{
    		if(day > 28 || day < 1) {
    			alert("1일부터 28일 까지로 입력해주세요.");		 
    			return false;
    		}
		}
    }
    
    form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
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
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
       <form name="form" method="POST" action="<c:url value='/user/update' />">
			
	
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
              <h1>회원 정보 수정</h1>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section bg-light" id="contact-section">
      <div class="container">
        <div class="row justify-content-center text-center">
      
      </div>
		<input type="hidden" name="userId" value="<%=user.getId()%>" />
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 -
									사용자 수정</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen">
						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">사용자 ID</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="userId"
								value="<%=user.getId()%>" readonly></td>
						</tr>

						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="password" style="width: 240" name="password"
								<c:if test="${registerFailed}">value="${user.password}"</c:if>>
							</td>
						</tr>

						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="password" style="width: 240" name="password2"
								<c:if test="${registerFailed}">value="${user.password}"</c:if>>
							</td>
						</tr>

						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">이름</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="name"
								value="<%=user.getName()%>"></td>
						</tr>
						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">전화번호</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="phone"
								value="${user.phone}"></td>
						</tr>
						<tr>
							<td width="150" align="center" bgcolor="E6ECDE">나이</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10">
							<script language="javascript">
		
			var toyear = parseInt(new Date().getFullYear()); 
			var start = toyear - 20
			var end = toyear - 60;

			document.write("<font size=2><select name=birthyy>");
			document.write("<option value='' selected>");
			for (i=start;i>=end;i--) document.write("<option>"+i);
			document.write("</select>  ");
		</script> 
		<select name="birthmm">
									<option value=" ">월</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
							</select> <select name="birthdd">
									<option value="">일</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
							</select></td>
						</tr>
						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">성별</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="radio" name="sex" value="남"
								<%if ("남".equals(user.getSex())) {%> checked <%}%>>남자</input> <input
								type="radio" name="sex" value="여"
								<%if ("여".equals(user.getSex())) {%> checked <%}%>>여자</input></td>
						</tr>
						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">면허번호</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240;" name="licenseno"></td>
						</tr>

						<tr height="40">
							<td width="150" align="center" bgcolor="E6ECDE">면허종류</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="radio" name="type" value="1종보통"> 1종보통 <input
								type="radio" name="type" value="2종보통" checked> 2종보통</td>
						</tr>

						<tr>
							<td align="center" bgcolor="E6ECDE">면허날짜(YYYY-MM-DD)</td>
							<td bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="vdate"></td>
						</tr>
						
					</table> <br>
					<table style="width: 100%">
						<tr>
							<td align="left">
							
							<div class="form-group row">
                <div class="col-md-6 mr-auto">
                <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="수정" onClick="userModify()"> &nbsp;
                <input type="button" class="btn btn-block btn-primary text-white py-3 px-5" value="취소" onClick="userList('<c:url value='/user/view' />')">
                </div>
              </div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type = "hidden" name = "strdate" value=<%=today %>>
		
	    <c:if test="${exception1 == true}">
	    	<script type="text/javascript">alert("면허 유형 오류");</script>
	    </c:if>
	    <c:if test="${exception2 == true}">
	    	<script type="text/javascript">alert("면허 일자 오류");</script>
	    </c:if>
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

