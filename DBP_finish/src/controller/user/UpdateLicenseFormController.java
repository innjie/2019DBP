package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.User;
import model.License;

public class UpdateLicenseFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String updateId = request.getParameter("userId");
		
		log.debug("UpdateForm Request : {}", updateId);

		UserManager manager = UserManager.getInstance();
		User user = manager.findUser(updateId);	
		request.setAttribute("user", user);						

		HttpSession session = request.getSession();
	
        
		if (UserSessionUtils.isLoginUser(updateId, session)) {
			return "/user/updateLicense.jsp";     
		}
		
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
			new IllegalStateException("���씤�쓽 �젙蹂대뒗 �닔�젙�븷 �닔 �뾾�뒿�땲�떎."));            
		return "/user/view.jsp";	
    }
}