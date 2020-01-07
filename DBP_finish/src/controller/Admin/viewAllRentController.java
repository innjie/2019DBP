package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Rent;
import model.service.RentManager;

public class viewAllRentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RentManager manager = RentManager.getInstance();
		
		int rentNo = Integer.parseInt(request.getParameter("rentNo"));
		Rent rent = null;
		
		
		try {
			rent = manager.getRentView(rentNo);
		} catch (Exception e) {
			return "redirect:/admin/rentList";
		}

		request.setAttribute("rent", rent);
		return "/admin/rentView.jsp";
	}

}
