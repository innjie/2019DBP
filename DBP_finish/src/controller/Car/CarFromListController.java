package controller.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Car;
import model.service.CarManager;

public class CarFromListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CarManager manager = CarManager.getInstance();
		int carNo = Integer.parseInt(request.getParameter("carNo"));
		Car car = null;
		try {
			car = manager.getCar(carNo);
		} catch (Exception e) {				
	        return "redirect:/car/ViewAllCar";
		}	
		
		request.setAttribute("car", car);
		return "/admin/viewCar.jsp";
	}

}
