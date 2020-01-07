package controller.rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class RentReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rentNo = Integer.parseInt(request.getParameter("rentNo"));
		
		request.setAttribute("rentNo",  rentNo);
		System.out.println(rentNo);
		return "/rent/review.jsp";
	}

}