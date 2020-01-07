package controller.reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Car;
import model.Model;
import model.Reserve;
import model.service.KakaoPayService;

public class KakaoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Car car = new Car();
		Model model = new Model();
		
		HttpSession session = request.getSession();
		int cNo = (int) session.getAttribute("cNo");
		
		model.setBrand(request.getParameter("brand"));
		model.setModelName(request.getParameter("model"));
		model.setPassenger(Integer.parseInt(request.getParameter("passenger")));
		
		car.setModel(model);
		car.setColor(request.getParameter("color"));
		car.setPrice(Integer.parseInt(request.getParameter("price")));
		car.setCarNo(Integer.parseInt(request.getParameter("carNo")));
		//cNo넣기 위한 session, reserveNo car 객체 
		
		//이전 페이지에서 cno 받는다 가정
		Reserve rsv = new Reserve(cNo, car);
		
		String rsvDate = request.getParameter("rsvDate");
		java.sql.Date d =java.sql.Date.valueOf(rsvDate);
		rsv.setReserveDate(d);
		
		//rentDate, returnDate not null,,,
		String rentDate = request.getParameter("rentDate");
		d =java.sql.Date.valueOf(rentDate);
		rsv.setRentDate(d);
		
		String rtnDate = request.getParameter("rtnDate");
		d =java.sql.Date.valueOf(rtnDate);
		rsv.setReturnDate(d);
		
		KakaoPayService kakaoPayService = new KakaoPayService();
		kakaoPayService.kakaoPayReady(rsv);
		
		return "/car/main.jsp";
	}

}
