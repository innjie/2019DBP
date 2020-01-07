package controller.Car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.service.CarManager;
import model.service.QnANotFoundException;
import model.service.RentManager;

public class ViewModelController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarManager manager = CarManager.getInstance();
		List<String> reviews = null;
		int modelNo = Integer.parseInt(request.getParameter("modelNo"));
		Model model = null;
		List<Car> carList = null;
		String review = "";
		
		RentManager rentManager = RentManager.getInstance();
		
		try {
			model = manager.getModelByModelNo(modelNo);		
			carList = manager.getSameCarByModel(modelNo);
			reviews = rentManager.getReviewList(modelNo);
			System.out.println(modelNo);
			
			if(reviews == null) {
				review = "no review";
			}
			else {
				for(int i = 0; i < reviews.size(); i++) {
					review += reviews.get(i) + "\n";
					System.out.println(review);
					System.out.println(reviews.get(i));
				}
			}
		} catch (Exception e) {				
	        return "redirect:/car/main";
		}	
		
		
		request.setAttribute("model", model);
		request.setAttribute("CarList", carList);
		request.setAttribute("review",  review);
		return "/car/modelView.jsp";
	}

}