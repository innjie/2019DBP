package controller.Admin;



import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.*;
import model.service.*;

public class addModelController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String modelName = request.getParameter("modelName");
		String brand = request.getParameter("brand");
		int passenger = Integer.parseInt(request.getParameter("passenger"));
		String year = request.getParameter("year");
		java.sql.Date d = java.sql.Date.valueOf(year);
		int mNo = 9000;
		
		Model model = new Model(modelName, brand, passenger, d, mNo);
		
		try {
			CarManager manager = CarManager.getInstance();
			manager.insertModel(model);
			return "redirect:/admin/modelList";
		} catch(Exception e){
			 request.setAttribute("creationFailed", true);
				request.setAttribute("exception", e);
				request.setAttribute("model", model);
				return "/admin/addModel.jsp";
		}
	}

	

}
