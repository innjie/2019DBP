package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class viewAllUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
/*    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		
        }
*///    	
		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();
		////String userId = (String) session.getAttribute("userId");
		String userId =  request.getParameter("userId");
		
/*		if(request.getParameter("userId") != null){
			userId = request.getParameter("userId");
		}
im */       
    	User user;
		try {
			user = manager.findUser(userId);	
		} catch (UserNotFoundException e) {	
	        return "redirect:/admin/userList";
		}	
		
		request.setAttribute("user", user);					
		return "/admin/userView.jsp";				
    }
}
