package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class viewBadUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
  	
		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();

		String userId =  request.getParameter("userId");
      
    	User user;
		try {
			user = manager.findUser(userId);	
		} catch (UserNotFoundException e) {	
	        return "redirect:/admin/badUserList";
		}	
		
		request.setAttribute("user", user);					
		return "/admin/badUserView.jsp";				
    }
}