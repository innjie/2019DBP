package controller.user;

import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.License;
import model.User;
import model.dao.JDBCUtil;
import model.service.UserManager;

public class RegisterLicenseController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterLicenseController.class);
    private JDBCUtil jdbcUtil = null;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	 String referer = request.getHeader("REFERER");
		 		  
    	String vdate = request.getParameter("vdate");
    	java.sql.Date d = java.sql.Date.valueOf(vdate);
		
    	License license = new License(
    			d,
    			request.getParameter("type"),
			request.getParameter("licenseno"));
    	
	
        log.debug("Create License : {}", license);

		try {
			UserManager manager = UserManager.getInstance();
			manager.createLicense(license);
			System.out.println("#########3#");
			//return "redirect: /user/register/form";
			
			
			 System.out.println(referer);
			 return "/user/registerForm.jsp";
			
	       //return "redirect:/user/list";
			//return "/user/registerForm.jsp";
	        
		} catch (Exception e) {		
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("license", license);
			return "/user/registerForm.jsp";
		} 
    }
}