package controller.Admin;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Model;
import model.service.CarManager;

public class resetMonthlyCarController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarManager manager = CarManager.getInstance();
		
		
		if (request.getMethod().equals("POST")) {
			Calendar cal = Calendar.getInstance();
			int date = cal.get(Calendar.DATE);
			
			if(date == 1) {
				System.out.println("date:"+date);

				manager.firstMonth();
			}
			
		}
		
		List<Model> modelList = manager.getMonthlyModel();
		request.setAttribute("modelList", modelList);
		return "/admin/monthlyCar.jsp";
	}

}
