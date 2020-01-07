package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.QnA;
import model.service.AdminManager;
import model.service.QnAManager;
import model.service.QnANotFoundException;

public class viewAllQnAController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnAManager manager = QnAManager.getInstance();
		AdminManager manager2 = AdminManager.getInstance();
		
		int qnaNo = Integer.parseInt(request.getParameter("qNo"));
		QnA qna = null;
	
		try {
			qna = manager.findQnAByQNumM(qnaNo);
			//qna.getManager().setName(manager2.findName(qna.getManager().getManagerNo()));
			
		} catch (QnANotFoundException e) {				
	        return "redirect:/admin/qnaList";
		}	
		
		request.setAttribute("qna", qna);		// qna 정보 저장				
		return "/admin/qnaView.jsp";			// qna 보기 화면으로 이동
	}

}
