package controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Reserve;
import model.service.ReserveManager;

public class listAllReserveController implements Controller {
	private static final int countPerPage = 10;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		ReserveManager manager = ReserveManager.getInstance();
		List<Reserve> reserveList = manager.getReserveList(currentPage, countPerPage);
		
		int size = manager.getReserveList().size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("reserveList", reserveList);
		
		return "/admin/reserveList.jsp";
	}

}
