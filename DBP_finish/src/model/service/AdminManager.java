package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Manager;
import model.dao.AdminDAO;


public class AdminManager {
	private static AdminManager adminMan = new AdminManager();
	private AdminDAO adminDAO;

	private AdminManager() {
		try {
			adminDAO = new AdminDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static AdminManager getInstance() {
		return adminMan;
	}
	
	public int findMNo(String id) {
		return adminDAO.findMNo(id);
	}
	
	public String findName(int mNo) {
		return adminDAO.findName(mNo);
	}
	
	public String findTask(int mNo) {
		return adminDAO.findTask(mNo);
	}
	
	public int create(Manager manager) throws SQLException, ExistingUserException, ExistingPhoneException {
		if (adminDAO.existingManager(manager.getId()) == true) {
			throw new ExistingUserException(manager.getId() + "는 이미 존재하는 아이디 입니다.");
		}
		if (adminDAO.existingNumber(manager.getPhone()) == true) {
			throw new ExistingPhoneException(manager.getPhone() + "이미 존재하는 전화번호입니다.");
		}             
		return adminDAO.create(manager);
	}

	public int update(Manager manager) throws SQLException {
		return adminDAO.update(manager);
	}	
	
	public Manager findManager(String id)
		throws SQLException, UserNotFoundException {
		Manager manager = adminDAO.findManager(id);
		
		if (manager == null) {
			throw new UserNotFoundException(id + "존재하지 않는 아이디입니다.");
		}		
		return manager;
	}


	public boolean login(String id, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		Manager manager = findManager(id);

		if (!manager.matchPassword(password)) {
			throw new PasswordMismatchException("올바르지 않은 비밀번호입니다.");
		}
		return true;
	}

	public AdminDAO getAdminDAO() {
		return this.adminDAO;
	}

}