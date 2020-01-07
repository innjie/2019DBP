package controller.QnA;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.QnA;
import model.service.QnAManager;

public class FindQnAController implements Controller {
	private static final int countPerPage = 9;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	
		
		QnAManager manager = QnAManager.getInstance();
		String question = request.getParameter("findContent");
		List<QnA> QnAList = manager.findQnAByQuestion(question, currentPage, countPerPage);
		
		
		int size = manager.findQnAByQuestion(question).size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("question", question);
		request.setAttribute("QnAList", QnAList);				
	/*	request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
	*/	
		return "/qna/find.jsp";
	}

}
