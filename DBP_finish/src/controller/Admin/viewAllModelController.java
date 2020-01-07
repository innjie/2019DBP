package controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.service.CarManager;
import model.service.QnANotFoundException;

public class viewAllModelController implements Controller{
	private static final int countPerPage = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		CarManager manager = CarManager.getInstance();
		
		int modelNo = Integer.parseInt(request.getParameter("modelNo"));
		Model model = null;
		List<Car> carList = null;
		
		try {
			model = manager.getModelByModelNo(modelNo);		
			carList = manager.getCarListByModelNo(modelNo, currentPage, countPerPage);
		} catch (Exception e) {				
	        return "redirect:/admin/mgrPage";
		}	
		
		int size = manager.getCarListByModelNo(modelNo).size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("model", model);
		request.setAttribute("CarList", carList);
		return "/admin/modelView.jsp";
	}

}
