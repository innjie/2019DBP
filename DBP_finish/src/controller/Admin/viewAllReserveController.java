package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Reserve;
import model.service.ReserveManager;

public class viewAllReserveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
		
		ReserveManager manager = ReserveManager.getInstance();
		Reserve reserve = manager.getOneReserve(rsvNo);
		
		request.setAttribute("reserve", reserve);
		
		return "/admin/reserveView.jsp";
	}

}
