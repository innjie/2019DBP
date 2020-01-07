package controller.rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Rent;
import model.service.RentManager;
import model.service.ReserveManager;

public class InsertReviewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String review = request.getParameter("text");
		int rentNo = Integer.parseInt(request.getParameter("rentNo"));
		
		RentManager rentManager = new RentManager();
		Rent rent = new Rent();
		System.out.println(rentNo + " " + review + "\n");
		rent.setRentNo(rentNo);
		rent.setReview(review);
		
		rentManager.insertReview(rent);
		
		return "/rent/list";
	}
	
}