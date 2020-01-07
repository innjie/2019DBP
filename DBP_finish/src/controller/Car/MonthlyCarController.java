package controller.Car;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext ;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Model;
import model.service.CarManager;

public class MonthlyCarController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarManager manager = CarManager.getInstance();
		
/*		ServletContext application = request.getServletContext();
		Integer count = (Integer)application.getAttribute("count");
		if(count == null){
			count = new Integer(0);
		}
		count++;
		
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DATE);
		
		
		if(date == cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			
		}
		if(date == 1 && count == 1) {
			manager.firstMonth();
		}
		application.setAttribute("count", count);
	*/	
		
		
		
		
		List<Model> modelList = manager.getMonthlyModel();

		request.setAttribute("modelList", modelList);
		
		return "/admin/monthlyCar.jsp";
		
	}

}
