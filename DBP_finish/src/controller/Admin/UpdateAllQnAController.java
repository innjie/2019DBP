package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.QnA;
import model.service.QnAManager;

public class UpdateAllQnAController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userId");
		int mNo = (int) session.getAttribute("mNo");
		
		
		
		// GET request: qna 수정 form 요청
		if (request.getMethod().equals("GET")) {	
			
			QnAManager manager = QnAManager.getInstance();
			QnA qna = manager.findQnAByQNum(qNo);
			request.setAttribute("qna", qna);
			
			
			return "/admin/updateQnAForm.jsp";     
		}
		
		// POST request (qna 정보가 parameter로 전송됨)
		QnA qna = new QnA(
				request.getParameter("id"),
				qNo,
				request.getParameter("question"),
				request.getParameter("answer"),
				Integer.parseInt(request.getParameter("rating")));
		
		QnAManager manager = QnAManager.getInstance();
			
		manager.updateAnswer(qna, mNo);	//mNo

	
		request.setAttribute("qNo", qNo);		
				
		return "/admin/qnaView";
	}

}
