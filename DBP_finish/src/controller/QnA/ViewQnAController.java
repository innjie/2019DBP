package controller.QnA;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.QnA;
import model.service.QnAManager;
import model.service.QnANotFoundException;

public class ViewQnAController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnAManager manager = QnAManager.getInstance();
		
		int qnaNo = Integer.parseInt(request.getParameter("qNo"));
		QnA qna = null;
	
		try {
			qna = manager.findQnAByQNum(qnaNo);		
		} catch (QnANotFoundException e) {				
	        return "redirect:/qna/list";
		}	
		
		request.setAttribute("qna", qna);		// qna 정보 저장				
		return "/qna/view.jsp";				// qna 보기 화면으로 이동
		
		
		
	}

}
