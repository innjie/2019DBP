package controller.Car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Car;
import model.Model;
import model.service.CarManager;

public class ReserveCarController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarManager manager = CarManager.getInstance();
		
		String select = request.getParameter("select");
		String[] array = select.split("/");
		
		int modelNo = Integer.parseInt(array[0]);
		int price = Integer.parseInt(array[1]);
		String color = array[2];
		
		Car car = manager.reserveCar(modelNo,  price, color);
	
		request.setAttribute("car", car);
		return "/reserve/reserveMain.jsp";
	}

}
