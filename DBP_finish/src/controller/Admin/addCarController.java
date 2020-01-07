package controller.Admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.*;
import model.service.CarManager;

public class addCarController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int modelNo = Integer.parseInt(request.getParameter("modelNo"));
		String modelName = request.getParameter("modelName");
		int price;
		String color;
		
		request.setAttribute("modelNo", modelNo);
		request.setAttribute("modelName", modelName);
		if (request.getMethod().equals("GET")) {
			return "/admin/addCar.jsp";
		}
		
		price = Integer.parseInt(request.getParameter("price"));
		color = request.getParameter("color");
		
		Model model = new Model(modelNo, modelName);
		Car car = new Car(price, color, model);
		
		request.setAttribute("modelNo", modelNo);
		try {
			CarManager manager = CarManager.getInstance();
			manager.insertCar(car);
			manager.updateModel(model);
			return "/admin/modelView";
		} catch(Exception e){
			 request.setAttribute("creationFailed", true);
				request.setAttribute("exception", e);
				request.setAttribute("car", car);
				return "/admin/addCar.jsp";
		}
	}

}
