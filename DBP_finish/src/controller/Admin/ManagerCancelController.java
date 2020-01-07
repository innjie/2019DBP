package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Reserve;
import model.service.ReserveManager;
import model.service.UserManager;

public class ManagerCancelController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("in");
		int rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
		Reserve rsv = new Reserve(rsvNo);
	
		
		try {
			UserManager user = UserManager.getInstance();
		
			ReserveManager mgr = ReserveManager.getInstance();
			int check = mgr.deleteReserve(rsv);
			System.out.println(rsvNo);
			System.out.println(check);
			if(check == 0) {
				
				request.setAttribute("errorMsg", "취소 불가");
				return "/admin/reserveList";
			}
			request.setAttribute("rsvNo",  rsvNo);
			return "/admin/cancel/page";
		} catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception",  e);
			request.setAttribute("rsv",  rsv);
			return "/admin/reserveList.jsp";
		}
	}

}
