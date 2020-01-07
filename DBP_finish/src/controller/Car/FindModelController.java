package controller.Car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Model;
import model.service.CarManager;

public class FindModelController implements Controller{
	private static final int countPerPage = 10;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		CarManager manager = CarManager.getInstance();
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		String passenger = request.getParameter("passenger");

		
		List<Model> modelList = manager.findModel(price, color, passenger, currentPage, countPerPage);
		int size = manager.findModel(price, color, passenger).size();
		
		price = manager.price(price);
		color = manager.color(color);
		passenger = manager.passenger(passenger);

		
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("price", price);
		request.setAttribute("color", color);
		request.setAttribute("passenger", passenger);

		request.setAttribute("modelList", modelList);
		
		return "/car/find.jsp";
	}

}
