package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Car;
import model.Model;
import model.service.CarManager;

public class viewAllCarController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarManager manager = CarManager.getInstance();
		
	
	//	int carNo=1000;
		int carNo = Integer.parseInt(request.getParameter("carNo"));
		int modelNo = Integer.parseInt(request.getParameter("modelNo"));
		Car car = null;
		
		try {
			car = manager.getCar(carNo);
		} catch (Exception e) {				
	        return "redirect:/admin/carList";
		}	
		
		
		request.setAttribute("modelNo", modelNo);
		request.setAttribute("car", car);
		return "/admin/carView.jsp";
	
	
	}

}
