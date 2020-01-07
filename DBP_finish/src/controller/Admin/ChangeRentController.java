package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Car;
import model.Manager;
import model.Reserve;
import model.service.AdminManager;
import model.service.CarManager;
import model.service.RentManager;
import model.service.ReserveManager;

public class ChangeRentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
		System.out.println("555:" + rsvNo);
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userId");
		AdminManager am = AdminManager.getInstance();
		
		int mno = am.findMNo(id);
	
		
		ReserveManager manager = ReserveManager.getInstance();
		RentManager manager2 = RentManager.getInstance();
		CarManager manager3 = CarManager.getInstance();

		Reserve reserve = manager.getOneReserve(rsvNo);
		
		manager.rentCar(rsvNo, mno); //reserve state
		manager2.insertRent(reserve);	//insert into
		
		Car car = reserve.getCar();
		manager3.getCar(car.getCarNo());
		System.out.println("aaaa:" + car.getRentCount());
		System.out.println("aaaa:" + car.getMonthRent());
		System.out.println("aaaa:" + car.getCarNo());
		manager3.rentCar(car);

		
		
		
		request.setAttribute("reserve", reserve);
		
		return "/admin/rentList";
	}

}