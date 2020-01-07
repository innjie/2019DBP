package controller.Admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.UserManager;

public class listBadUserController implements Controller {
	// private static final int countPerPage = 100;	

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		UserManager manager = UserManager.getInstance();
		List<User> userList = manager.findBadUserList();
	
		request.setAttribute("userList", userList);				

		return "/admin/badUserList.jsp";        
    }
}