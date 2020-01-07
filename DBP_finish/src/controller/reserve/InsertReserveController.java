package controller.reserve;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Reserve;
import model.service.ReserveManager;
import model.Car;
import model.Model;


public class InsertReserveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("in success");
		//CarDTO에 받아온 값 입력
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
	
		
		try {
			ReserveManager mgr = ReserveManager.getInstance();
			int rsvNo = mgr.insertReserve(rsv);

			if (rsvNo == 0) {
				request.setAttribute("errorMsg", "중복 예약 확인 : 잘못된 접근입니다.");
				
				return "/reserve/list";
			}
			request.setAttribute("rsvNo", rsvNo);
			System.out.println(rsvNo);
			return "/reserve/success";
		} catch (Exception e) {
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception",  e);
			request.setAttribute("rsv", rsv);
			return "/user/myPage";
			
		}
	}
	
}