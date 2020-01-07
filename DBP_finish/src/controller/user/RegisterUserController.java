package controller.user;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.License;
import model.User;
import model.dao.JDBCUtil;
import model.service.ExistingUserException;
import model.service.ExistingPhoneException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
    private JDBCUtil jdbcUtil = null;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String age = request.getParameter("birthyy") +
		  request.getParameter("birthmm") + request.getParameter("birthdd");
		 
    	User user = new User(
			request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("phone"),
			age,
			request.getParameter("sex"));
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			System.out.println("#########3#");
	        return "redirect:/user/loginForm.jsp";		
	        
		} catch (ExistingUserException e) {		
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		} catch (ExistingPhoneException e) {		
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}