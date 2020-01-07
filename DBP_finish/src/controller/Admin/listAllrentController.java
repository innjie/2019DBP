package controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Rent;
import model.service.RentManager;

public class listAllrentController implements Controller {
	private static final int countPerPage = 10;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		RentManager manager = RentManager.getInstance();
		List<Rent> rentList = manager.getRentList(currentPage, countPerPage);

		int size = manager.getRentList().size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);

		request.setAttribute("rentList", rentList);
		
		return "/admin/rentList.jsp";

	}

}
