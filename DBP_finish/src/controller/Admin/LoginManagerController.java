package controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.service.*;

public class LoginManagerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		int mNo;
		String task;
		
		try {
			AdminManager manager = AdminManager.getInstance();
			manager.login(id, password);
			mNo = manager.findMNo(id);
			task = manager.findTask(mNo);
	
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, id);
            session.setAttribute("mNo", mNo);
            session.setAttribute("task", task);
            session.setAttribute("mgr", "true");
            
            System.out.println("aa: "+ task);
          
            return "/admin/mgrPage";			
		} catch (Exception e) {
			/* UserNotFoundException
			 *
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/admin/loginMan.jsp";			
		}	

			
    }
}
