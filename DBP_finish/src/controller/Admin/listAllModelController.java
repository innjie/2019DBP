package controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Model;
import model.service.CarManager;

public class listAllModelController implements Controller{
	private static final int countPerPage = 10;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		CarManager manager = CarManager.getInstance();
		List<Model> modelList = manager.getModelList(currentPage, countPerPage);
		HttpSession session = request.getSession();
		
		int size = manager.getModelList().size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("modelList", modelList);
		return "/admin/modelList.jsp";
	}

}
