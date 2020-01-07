package controller.QnA;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Manager;
import model.QnA;
import model.service.QnAManager;
import model.User;

public class AddQnAController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date d = new Date();
		Manager managerDTO = new Manager();
		HttpSession session = request.getSession();
		int cNo = (int) session.getAttribute("cNo");
		String secret = request.getParameter("secret");
	//	String userId = request.getParameter("userId");
		

		QnA qna = new QnA(cNo, request.getParameter("question"), secret);
		qna.setSecret(secret);
		
		
		
	//	session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
    //    request.setAttribute("cNo", cNo);
		try {
			QnAManager manager = QnAManager.getInstance();
			manager.add(qna);
			return "redirect:/qna/list";
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("QnA", qna);
			return "/qna/addForm.jsp";
		}
		
	}

}
