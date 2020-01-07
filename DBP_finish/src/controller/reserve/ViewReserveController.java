package controller.reserve;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Reserve;
import model.service.ReserveManager;

public class ViewReserveController implements Controller {
	private static final int countPerPage = 5;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	
		HttpSession session = request.getSession();
		int cNo = (int) session.getAttribute("cNo");
		try {
			ReserveManager mgr = ReserveManager.getInstance();
			List<Reserve> result = mgr.findReserve(cNo, currentPage, countPerPage);
			
			int size = mgr.findReserve(cNo).size();
			int page;
			
			if(size % countPerPage == 0)
				page = size / countPerPage;
			else
				page = size / countPerPage + 1;

			request.setAttribute("pageSize", page);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("rsv", result);
			
			return "/reserve/reserveList.jsp";
		} catch (Exception e){
			return "/user/myPage";
		}
	}

}