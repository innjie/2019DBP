package controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.QnA;
import model.service.QnAManager;

public class listAllQnAController implements Controller{
	private static final int countPerPage = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}	

		QnAManager manager = QnAManager.getInstance();
		List<QnA> QnAList = manager.findQnAMList(currentPage, countPerPage);
		int size = manager.findQnAMList().size();
		int page;
		
		if(size % countPerPage == 0)
			page = size / countPerPage;
		else
			page = size / countPerPage + 1;

		request.setAttribute("pageSize", page);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("QnAList", QnAList);				
		
		return "/admin/qnaList.jsp";
		
		
	}
	

}
