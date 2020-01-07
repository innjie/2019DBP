package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Model;
import model.service.CarManager;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		int cNo;
		
		try {
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
			cNo = manager.findCNo(userId);
	
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            session.setAttribute("cNo", cNo);
          
            return "/car/main";			
		} catch (Exception e) {
			/* UserNotFoundException
			 *
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	

			
    }
}
