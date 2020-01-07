package model.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.License;
import model.User;
import model.dao.UserDAO;


public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private UserAnalysis userAanlysis;

	private UserManager() {
		try {
			userDAO = new UserDAO();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int findCNo(String id) {
		return userDAO.findCNo(id);
	}
	
	public int create(User user) throws SQLException, ExistingUserException, ExistingPhoneException {
		if (userDAO.existingUser(user.getId()) == true) {
			throw new ExistingUserException(user.getId() + "는 이미 존재하는 아이디 입니다.");
		}
		if (userDAO.existingNumber(user.getPhone()) == true) {
			throw new ExistingPhoneException(user.getPhone() + "이미 존재하는 전화번호입니다.");
		}             
		return userDAO.create(user);
	}

	public int update(User user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException {
		return userDAO.remove(userId);
	}
	
	public List<User> findBadUserList() throws SQLException {
		return userDAO.findBadUserList();
	}

	public boolean checkLisence(License license) throws SQLException {
		return userDAO.checkLisence(license);
	}
	public String checkLicenseType(License license) throws SQLException {
		return userDAO.checkLicenseType(license);
	}
	public Date checkLicenseVDate(License license) throws SQLException {
		return userDAO.checkLicenseVDate(license);
	}
	
	public User findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "존재하지 않는 아이디입니다.");
		}		
		return user;
	}

	public List<User> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<User> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}
	
	

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public List<User> makeFriends(String userId) throws Exception {
		return userAanlysis.recommendFriends(userId);
	}
	
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
	
	public int createLicense(License license) throws SQLException {            
		return userDAO.createLicense(license);
	}

	public int updateLicense(License updateLicense, String licenseno) throws SQLException {
		return userDAO.updateLicense(updateLicense, licenseno);
	}
}