package controller.reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Reserve;
import model.service.ReserveManager;
import controller.Controller;
import controller.user.UserSessionUtils;

public class CancleReserveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("in");
		int rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
		Reserve rsv = new Reserve(rsvNo);
	
		
		try {
			ReserveManager mgr = ReserveManager.getInstance();
			int check = mgr.deleteReserve(rsv);
			System.out.println(rsvNo);
			System.out.println(check);
			if(check == 0) {
				
				request.setAttribute("errorMsg", "취소 불가 : 이미 대여한 항목입니다.");
				return "/reserve/list";
			}
			request.setAttribute("rsvNo",  rsvNo);
			return "/reserve/cancel/page";
		} catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception",  e);
			request.setAttribute("rsv",  rsv);
			return "/reserve/reserveList.jsp";
		}
	}

}