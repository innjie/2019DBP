package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.CarManager;

public class deleteCarController  implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int carNo = Integer.parseInt(request.getParameter("carNo"));
		int modelNo = Integer.parseInt(request.getParameter("modelNo"));
		
		try {
			CarManager manager = CarManager.getInstance();
			manager.deleteCar(carNo);
			manager.updateModelDeleteCar(modelNo);
			request.setAttribute("carNo", carNo);
			request.setAttribute("modelNo", modelNo);
			return "/admin/deleteCar.jsp";
		}catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception",  e);
			request.setAttribute("carNo",  carNo);
			return "/admin/modelList";
		}
	}

}
