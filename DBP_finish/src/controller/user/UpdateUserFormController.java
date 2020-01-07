package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.User;

public class UpdateUserFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String updateId = (String) request.getAttribute("userId");
		
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
}