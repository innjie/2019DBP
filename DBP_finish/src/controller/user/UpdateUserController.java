package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager;
import model.License;
import model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

    	if(request.getMethod().equals("GET")) {
    		HttpSession session = request.getSession();

    		String updateId = (String) session.getAttribute("userId");
    		
    		log.debug("UpdateForm Request : {}", updateId);

    		UserManager manager = UserManager.getInstance();
    		User user = manager.findUser(updateId);	
    		request.setAttribute("user", user);						
    		request.setAttribute("errorMsg", request.getParameter("errorMsg"));
    		
    	
            
    		if (UserSessionUtils.isLoginUser(updateId, session)) {
    			return "/user/updateForm.jsp";     
    		}
    		
    		//  else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
    		request.setAttribute("updateFailed", true);
    		request.setAttribute("exception", 
    			new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
    		return "/user/view.jsp";
        }
        
    	
    String age = request.getParameter("birthyy") +
    			  request.getParameter("birthmm") + request.getParameter("birthdd");
  	String vdate = request.getParameter("vdate");
	java.sql.Date d = java.sql.Date.valueOf(vdate);
	
	License license = new License(
			d,
			request.getParameter("type"),
		request.getParameter("licenseno"));
	
    	User updateUser = new User(
    		request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("phone"),
			age,
			request.getParameter("sex"), 
			license);
    	
    	log.debug("Update User : {}", updateUser);

		/*
		 * if()//존재하면 잡아, set으로 같은지 비교해서 안같으면 에러메세지
		 */
    	UserManager manager = UserManager.getInstance();
    	if(manager.checkLisence(license)) {
    		
    		
    		
    		
    		if(!license.getType().equals(manager.checkLicenseType(license))){
    			System.out.println("1111"+license.getType());
    			System.out.println("1111"+manager.checkLicenseType(license));
    			request.setAttribute("updateFailed", true);
    			request.setAttribute("exception", 
    					new IllegalStateException("동일 면허는 수정할 수 없습니다."));  
    			request.setAttribute("exception1", true);
    			return "/user/view";
    		}
    		else if(!license.getVdate().equals(manager.checkLicenseVDate(license))) {
    			System.out.println("2222"+license.getVdate());
    			System.out.println("2222"+manager.checkLicenseVDate(license));
    			request.setAttribute("exception2", true);
    			request.setAttribute("updateFailed", true);
    			request.setAttribute("exception", 
    					new IllegalStateException("동일 면허는 수정할 수 없습니다."));  
    			return "/user/view";
    		}
    		
    		
    		
    		
    	}
    	else {
        	manager.createLicense(license);

    	}

    	UserManager manager2 = UserManager.getInstance();
    	manager2.update(updateUser);		
    		
    	return "/user/view";
   
        
    }
}