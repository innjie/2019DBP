package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Manager;
import model.User;
import model.service.AdminManager;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class ViewManagerController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	/*if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		
        }
    	*/
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userId");
		AdminManager manager = AdminManager.getInstance();

        
    	Manager mgr = new Manager();
		try {
			mgr = manager.findManager(id);
		} catch (UserNotFoundException e) {				
	        return "redirect:/admin/mgrPage";
		}	
		
		request.setAttribute("mgr", mgr);					
		
		return "/admin/managerView.jsp";
	}

}
