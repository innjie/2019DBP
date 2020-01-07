package controller.rent;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Rent;
import model.service.RentManager;

public class ListRentController implements Controller {
	private static final int countPerPage = 10;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		HttpSession session = request.getSession();
		int cNo = (int) session.getAttribute("cNo");

		
		RentManager manager = RentManager.getInstance();
		List<Rent> rentList = manager.getRentListByNo(cNo, currentPage, countPerPage);

		int size = manager.getRentListByNo(cNo).size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rentList", rentList);
		
		return "/rent/list.jsp";
	}
	
}
