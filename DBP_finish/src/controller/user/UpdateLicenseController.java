package controller.user;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager;
import model.Company;
import model.License;
import model.User;

public class UpdateLicenseController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateLicenseController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
    	String vdate = request.getParameter("vdate");
    	java.sql.Date d = java.sql.Date.valueOf(vdate);
    	String no = request.getParameter("licenseno");
		
    	License updateLicense = new License(
    			d,
    			request.getParameter("type"),
    			request.getParameter("no"));
    	
    	log.debug("Update license : {}", updateLicense);
    	System.out.println(no);
    	System.out.println(request.getParameter("no"));

		UserManager manager = UserManager.getInstance();
		manager.createLicense(updateLicense);
		//manager.updateLicense(updateLicense, no);			
        return "redirect:/user/updateLicense/form";			
    }
}