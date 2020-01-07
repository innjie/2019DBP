package controller.QnA;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.QnA;
import model.service.QnAManager;

public class UpdateQnAController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String id = request.getParameter("id");
		
		
		
		// GET request: qna 수정 form 요청
		if (request.getMethod().equals("GET")) {	
			
			QnAManager manager = QnAManager.getInstance();
			QnA qna = manager.findQnAByQNum(qNo);
			request.setAttribute("qna", qna);
			
			if (UserSessionUtils.isLoginUser(id, session)) {
			// 현재 로그인한 사용자가 수정 대상 사용자인 경우 -> 수정 가능
								
				
				return "/qna/updateUserForm.jsp";  
			} 
			if (UserSessionUtils.isLoginUser("admin", session)) {
				// 현재 로그인한 사용자가 관리자인 경우 -> 수정 가능
					return "/qna/updateManagerForm.jsp";     
			} 
			
				 else// (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));   
			return "/qna/view.jsp";	
		}
		
		// POST request (qna 정보가 parameter로 전송됨)
		QnA qna = new QnA(
				userId,
				qNo,
				request.getParameter("question"),
				request.getParameter("answer"),
				Integer.parseInt(request.getParameter("rating"))
				);
		
		System.out.println(request.getParameter("question") + request.getParameter("answer") + Integer.parseInt(request.getParameter("rating")));
		
		QnAManager manager = QnAManager.getInstance();
		
//		HttpSession session = request.getSession();
//		if (UserSessionUtils.isLoginUser(updateId, session)) {
		// 현재 로그인한 사용자가 수정 대상 사용자인 경우 -> 수정 가능  
								
			int i = manager.updateRate(qna);   

			if(i == -1) {
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", 
				new IllegalStateException("답변등록 후 평점을 달아주세요"));   
			}
//		} 
//		if (UserSessionUtils.isLoginUser("admin", session)) {
			// 현재 로그인한 사용자가 관리자인 경우 -> 수정 가능
			
//			manager.updateAnswer(qna, 9001);	//mNo
			
//		} 
	
		request.setAttribute("qNo", qNo);		
				
		return "/qna/view";
	}

}
