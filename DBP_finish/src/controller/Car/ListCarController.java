package controller.Car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Car;
import model.service.CarManager;

public class ListCarController  implements Controller{
	private static final int countPerPage = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		CarManager manager = CarManager.getInstance();
		
		String select = request.getParameter("select");
		String[] array = select.split("/");
		
		int modelNo = Integer.parseInt(array[0]);
		int price = Integer.parseInt(array[1]);
		String color = array[2];
		List<Car> carList = null;
		
		
		try {
			carList = manager.getCarByModel(modelNo, price, color);
		} catch (Exception e) {				
	        return "redirect:/car/main";
		}	

		int size = manager.getCarByModel(modelNo, price, color, currentPage, countPerPage).size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("modelNo", modelNo);
		request.setAttribute("carList", carList);
		request.setAttribute("select", select);
		return "/car/carList.jsp";
	}

}
