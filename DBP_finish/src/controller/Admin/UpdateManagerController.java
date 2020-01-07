package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Manager;
import model.service.AdminManager;

public class UpdateManagerController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userId");
		
		AdminManager manager = AdminManager.getInstance();

        
		Manager mgr = new Manager();
    	
    		
		if (request.getMethod().equals("GET")) {
			
			mgr = manager.findManager(id);
			request.setAttribute("mgr", mgr);
			return "/admin/updateManager.jsp";
		}
		
		mgr.setId(request.getParameter("userId"));
		mgr.setPassword(request.getParameter("password"));
		mgr.setName(request.getParameter("name"));
		mgr.setPhone(request.getParameter("phone"));
		mgr.setTask(request.getParameter("task"));
		
		
		manager.update(mgr);
		request.setAttribute("mgr", mgr);
		return "/admin/managerView.jsp";
	}

}
