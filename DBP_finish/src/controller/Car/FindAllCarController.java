package controller.Car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Car;
import model.service.CarManager;

public class FindAllCarController implements Controller {
	private static final int countPerPage = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	

		CarManager manager = CarManager.getInstance();
		
		List<Car> cars = manager.getAllList(currentPage, countPerPage);
		
		int size = manager.getAllList().size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);	
		request.setAttribute("cars",  cars);	
		
		return "/admin/ViewAllCar.jsp";
	}

}
