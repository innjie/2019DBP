package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Rent;
import model.Reserve;
import model.dao.*;

public class RentManager {
	private static RentManager rentMan = new RentManager();
	private RentDAO rentDAO;
	
	public RentManager() {
		rentDAO = new RentDAO();
	}
	
	public static RentManager getInstance() {
		return rentMan;
	}
	
	public List<Rent> getRentList(){
		return rentDAO.getRentList();
	}
	public List<Rent> getRentList(int currentPage, int countPerPage){
		return rentDAO.getRentList(currentPage, countPerPage);
	}
	
	public List<Rent> getRentListByNo(int cNo){
		return rentDAO.getRentListByNo(cNo);
	}
	public List<Rent> getRentListByNo(int cNo, int currentPage, int countPerPage){
		return rentDAO.getRentListByNo(cNo,currentPage, countPerPage);
	}
	
	public Rent getRentView(int rentNo) {
		return rentDAO.getRentView(rentNo);
	}
	
	public int insertRent(Reserve reserve) throws SQLException {
		return rentDAO.insertRent(reserve);
	}
	public int returnRent(Rent rent) throws Exception {
		return rentDAO.returnRent(rent);
	}
	
	public int insertReview(Rent rent) throws SQLException {
		return rentDAO.insertReview(rent);
		
	}

	public List<String> getReviewList(int modelNo) throws SQLException {
		return rentDAO.getReviewList(modelNo);
	}
	
}
