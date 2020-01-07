package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Reserve;
import model.dao.ReserveDAO;
public class ReserveManager {
	private static ReserveManager rsvMan = new ReserveManager();
	private ReserveDAO reserveDAO;
	
	public ReserveManager() {
		reserveDAO = new ReserveDAO();
	}
	public static ReserveManager getInstance() {
		return rsvMan;
	}
	public int insertReserve(Reserve rsv) throws SQLException {
		return reserveDAO.insertReserve(rsv);
	}
	public int deleteReserve(Reserve rsv) throws Exception {
		return reserveDAO.deleteReserve(rsv);
		
	}
	public Reserve getOneReserve(int rsvNo) throws SQLException {
		return reserveDAO.getOneReserve(rsvNo);
	}
	public List<Reserve> findReserve(int cNo, int currentPage, int countPerPage) throws SQLException {
		return reserveDAO.findReserve(cNo, currentPage, countPerPage);
	}
	public List<Reserve> findReserve(int cNo) throws SQLException {
		return reserveDAO.findReserve(cNo);
	}
	
	public List<Reserve> getReserveList() throws SQLException {
		return reserveDAO.getReserveList();
	}
	public List<Reserve> getReserveList(int currentPage, int countPerPage) throws SQLException {
		return reserveDAO.getReserveList(currentPage, countPerPage);
	}
	
	public int rentCar(int reserveNo, int mno){
		return reserveDAO.rentCar(reserveNo, mno);
	}
}