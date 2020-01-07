package controller.Admin;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.License;
import model.Manager;
import model.Authority;
import model.Company;
import model.dao.JDBCUtil;
import model.service.ExistingUserException;
import model.service.ExistingPhoneException;
import model.service.AdminManager;

public class RegisterManagerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterManagerController.class);
    private JDBCUtil jdbcUtil = null;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * String com = request.getParameter("comno"); int comno =
		 * Integer.parseInt(com); Company company = new Company(comno);
		 */
    	Authority authority = new Authority(request.getParameter("task"));
    	
    	Manager admin = new Manager(
			request.getParameter("id"),
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("phone"),
			authority);
    
        log.debug("Create Manager : {}", admin);

		try {
			AdminManager manager = AdminManager.getInstance();
			manager.create(admin);
	        return "redirect:/admin/loginMan.jsp";		
	        
		} catch (ExistingUserException e) {		
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("manager", admin);
			return "/admin/registerMan.jsp";
		} catch (ExistingPhoneException e) {		
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("manager", admin);
			return "/admin/registerMan.jsp";
		}
    }
}