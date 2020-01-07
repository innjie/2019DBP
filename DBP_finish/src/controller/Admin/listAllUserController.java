package controller.Admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.UserManager;

public class listAllUserController implements Controller {
	 private static final int countPerPage = 10;	

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

 /*   	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		
        }
*///    	
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
    		
    
		UserManager manager = UserManager.getInstance();
		List<User> userList = manager.findUserList(currentPage, countPerPage);
	
		int size = manager.findUserList().size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("userList", userList);				
/*		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		

*/		
		return "/admin/userList.jsp";        
    }
}
