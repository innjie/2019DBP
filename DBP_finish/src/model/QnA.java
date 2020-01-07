package model;

import java.util.Date;

import model.User;
import model.Manager;

public class QnA {
	private User user = new User(); 
	private Manager manager = new Manager();
	private int QNANo; 
	private String QContent;
	private String finish; 
	private int rating; 
	private Date QNADate; 
	private String AContent; 
	private String secret;
	
	public QnA(int cNo, Manager manager, int qNANo, String qContent, String finish, int rating,
			Date qNADate, String aContent) {
		this.user.setCustomerNo(cNo);
		this.manager = manager;
		QNANo = qNANo;
		QContent = qContent;
		this.finish = finish;
		this.rating = rating;
		QNADate = qNADate;
		AContent = aContent;
	}
	
	public QnA(int cNo, String qContent, int QNANo) {
		user.setCustomerNo(cNo);
		QContent = qContent;
		this.QNANo = QNANo;
	}

	public QnA(String cId, int QNANo, String QContent, String aContent, int rating) {
		this.user.setId(cId);
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.AContent = aContent;
		this.rating = rating;
	}
	public QnA(String cId, int QNANo, String QContent, String aContent) {
		this.user.setId(cId);
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.AContent = aContent;
	}
	public QnA(int cNo, int QNANo, String QContent, Date QNADate) {
		this.user.setCustomerNo(cNo);
		
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.QNADate = QNADate;
	}
	public QnA(int cNo, int QNANo, String QContent) {
		this.user.setCustomerNo(cNo);
		
		this.QNANo = QNANo;
		this.QContent = QContent;
		
	}

	public QnA(int cNo, String QContent, String secret) {
		this.user.setCustomerNo(cNo);
		
		this.QContent = QContent;
		this.secret = secret;
	}
	
	public QnA(String cId, int QNANo, String QContent, Date QNADate, String finish) {
		this.user.setId(cId);
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.QNADate = QNADate;
		this.finish = finish;
	}
	
	public QnA(String cId, int QNANo, String QContent, Date QNADate, String finish, Manager manager) {
		this.user.setId(cId);
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.QNADate = QNADate;
		this.finish = finish;
		this.manager = manager;
	}
	
	public QnA(String cId, int QNANo, String QContent, String finish, int rating,
			Date qNADate, String aContent) {
		this.user.setId(cId);
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.finish = finish;
		this.rating = rating;
		this.QNADate = qNADate;
		this.AContent = aContent;
	}
	
	public QnA(String cId, int QNANo, String QContent, String finish, int rating,
			Date qNADate, String aContent, Manager manager) {
		this.user.setId(cId);
		this.QNANo = QNANo;
		this.QContent = QContent;
		this.finish = finish;
		this.rating = rating;
		this.QNADate = qNADate;
		this.AContent = aContent;
		this.manager = manager;
	}
	
	
	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public int getQNANo() {
		return QNANo;
	}
	public void setQNANo(int QNANo) {
		this.QNANo = QNANo;
	}
	public String getQContent() {
		return QContent;
	}
	public void setQContetn(String QContent) {
		this.QContent = QContent;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getQNADate() {
		return QNADate;
	}
	public void setQNADate(Date QNADate) {
		this.QNADate = QNADate;
	}
	public String getAContent() {
		return AContent;
	}
	public void setAContent(String AContent) {
		this.AContent = AContent;
	}

}
