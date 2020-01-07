package controller.Car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Model;
import model.service.CarManager;

public class FindModelByNameController implements Controller{
	private static final int countPerPage = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
		
		CarManager manager = CarManager.getInstance();
		String modelName = request.getParameter("findName");
		List<Model> modelList = manager.getModelByName(modelName, currentPage, countPerPage);
		
		int size = manager.getModelByName(modelName).size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
				
		request.setAttribute("modelName", modelName);
		request.setAttribute("modelList", modelList);
		
		return "/car/findByName.jsp";
	}

}
