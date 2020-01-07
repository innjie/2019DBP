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

		manager.remove(deleteId); // ����� ���� ����
		// �α����� ����ڴ� �̹� ������
		return "redirect:/admin/userList"; // logout ó��

		/* ������ �Ұ����� ��� */
		/*
		 * User user = manager.findUser(deleteId); // ����� ���� �˻�
		 * request.setAttribute("user", user); request.setAttribute("deleteFailed",
		 * true); String msg = (UserSessionUtils.isLoginUser("admin", session)) ?
		 * "�ý��� ������ ������ ������ �� �����ϴ�." "Ÿ���� ������ ������ �� �����ϴ�.";
		 * request.setAttribute("exception", new IllegalStateException(msg)); return
		 * "/user/view.jsp";
		 */ // ����� ���� ȭ������ �̵� (forwarding)
	}
}
