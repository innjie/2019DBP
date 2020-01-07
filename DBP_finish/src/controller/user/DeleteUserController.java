package controller.user;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	HttpSession session = request.getSession();
		String deleteId = (String) request.getParameter("userId");
    	log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();		
		
		
	
		if (UserSessionUtils.isLoginUser(deleteId, session)) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
				
			manager.remove(deleteId);				// ����� ���� ����
											// �α����� ����ڴ� �̹� ������
			System.out.println(deleteId);
				return "redirect:/user/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		User user = manager.findUser(deleteId);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = /*(UserSessionUtils.isLoginUser("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."	*/	
				    "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/view.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}
