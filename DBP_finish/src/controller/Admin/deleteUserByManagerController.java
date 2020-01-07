package controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.UserManager;
import model.Manager;
import model.service.AdminManager;

public class deleteUserByManagerController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(deleteUserByManagerController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String deleteId = request.getParameter("userId");
		log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();

		manager.remove(deleteId); // 사용자 정보 삭제
		// 로그인한 사용자는 이미 삭제됨
		return "redirect:/admin/userList"; // logout 처리

		/* 삭제가 불가능한 경우 */
		/*
		 * User user = manager.findUser(deleteId); // 사용자 정보 검색
		 * request.setAttribute("user", user); request.setAttribute("deleteFailed",
		 * true); String msg = (UserSessionUtils.isLoginUser("admin", session)) ?
		 * "시스템 관리자 정보는 삭제할 수 없습니다." "타인의 정보는 삭제할 수 없습니다.";
		 * request.setAttribute("exception", new IllegalStateException(msg)); return
		 * "/user/view.jsp";
		 */ // 사용자 보기 화면으로 이동 (forwarding)
	}
}
