

package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Car;
import model.Model;
import model.Rent;
import model.Reserve;

import java.sql.*;


public class RentDAO {

	private JDBCUtil jdbcUtil = null;		
		
	public RentDAO() {			
		jdbcUtil = new JDBCUtil();
	}
	
	public List<Rent> getRentList() {
		String allQuery = "SELECT rentNo, reserve.rsvNo, lender, rentDate, rtnDate, cNo, review, reserve.carNo, rrentDate, rrtnDate, return "
						+ "FROM RENT, reserve, car "
						+ "where rent.rsvNo = reserve.rsvNo and reserve.carNo = car.carNo "
						+ "order by rentNo";
		
		jdbcUtil.setSqlAndParameters(allQuery, null);
		List<Rent> list = new ArrayList<Rent>();		

		try { 
			ResultSet rs = jdbcUtil.executeQuery();		
			while (rs.next()) {	
				Car c = new Car(rs.getInt("carNo"));
				
				Reserve r = new Reserve(
						rs.getInt("rsvNo"),
						rs.getString("lender"),
						rs.getDate("rentDate"),
						rs.getDate("rtnDate"),
						c,
						rs.getInt("cNo"));
				

				
				Rent dto = new Rent(rs.getInt("rentNo"), r, rs.getString("review"), rs.getDate("rrentDate"), rs.getDate("rrtnDate"), rs.getString("return"));
				
				list.add(dto);		
			}
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}		
		return null;	
	}
	
	public List<Rent> getRentList(int currentPage, int countPerPage) {
		String allQuery = "SELECT rentNo, reserve.rsvNo, lender, rentDate, rtnDate, cNo, review, reserve.carNo, rrentDate, rrtnDate, return "
						+ "FROM RENT, reserve, car "
						+ "where rent.rsvNo = reserve.rsvNo and reserve.carNo = car.carNo "
						+ "order by rentNo";
		
		jdbcUtil.setSqlAndParameters(allQuery, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		List<Rent> list = new ArrayList<Rent>();		

		try { 
			ResultSet rs = jdbcUtil.executeQuery();	
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					Car c = new Car(rs.getInt("carNo"));
					
					Reserve r = new Reserve(
							rs.getInt("rsvNo"),
							rs.getString("lender"),
							rs.getDate("rentDate"),
							rs.getDate("rtnDate"),
							c,
							rs.getInt("cNo"));
					

					
					Rent dto = new Rent(rs.getInt("rentNo"), r, rs.getString("review"), rs.getDate("rrentDate"), rs.getDate("rrtnDate"), rs.getString("return"));
					
					list.add(dto);		
				}while (rs.next() && (--countPerPage > 0));
			}
			
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}		
		return null;	
	}
	
	public List<Rent> getRentListByNo(int cNo) {
		String allQuery = "SELECT rentNo, reserve.rsvNo, lender, rentDate, rtnDate, cNo, review, reserve.carNo, return "
						+ "FROM RENT, reserve, car "
						+ "where rent.rsvNo = reserve.rsvNo and reserve.carNo = car.carNo and cNo = ? "
						+ "order by rentNo";
		
		jdbcUtil.setSqlAndParameters(allQuery, new Object[]{ cNo });
		List<Rent> list = new ArrayList<Rent>();		

		try { 
			ResultSet rs = jdbcUtil.executeQuery();		
			while (rs.next()) {	
				Car c = new Car(rs.getInt("carNo"));
				
				Reserve r = new Reserve(
						rs.getInt("rsvNo"),
						rs.getString("lender"),
						rs.getDate("rentDate"),
						rs.getDate("rtnDate"),
						c,
						rs.getInt("cNo"));
				
				Rent dto = new Rent(rs.getInt("rentNo"), r, rs.getString("review"), rs.getString("return"));
				
				list.add(dto);		
			}
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}		
		return null;	
	}
	
	public List<Rent> getRentListByNo(int cNo, int currentPage, int countPerPage) {
		String allQuery = "SELECT rentNo, reserve.rsvNo, lender, rentDate, rtnDate, cNo, review, reserve.carNo, return "
						+ "FROM RENT, reserve, car "
						+ "where rent.rsvNo = reserve.rsvNo and reserve.carNo = car.carNo and cNo = ? "
						+ "order by rentNo";
		
		jdbcUtil.setSqlAndParameters(allQuery, new Object[]{ cNo }, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		List<Rent> list = new ArrayList<Rent>();		

		try { 
			ResultSet rs = jdbcUtil.executeQuery();		
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					Car c = new Car(rs.getInt("carNo"));
					
					Reserve r = new Reserve(
							rs.getInt("rsvNo"),
							rs.getString("lender"),
							rs.getDate("rentDate"),
							rs.getDate("rtnDate"),
							c,
							rs.getInt("cNo"));
					
					Rent dto = new Rent(rs.getInt("rentNo"), r, rs.getString("review"), rs.getString("return"));
					
					list.add(dto);		
				}while (rs.next() && (--countPerPage > 0));
			}
			
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}		
		return null;	
	}
	
	public Rent getRentView(int rentNo) {
		String allQuery = "SELECT rentNo, rent.rsvNo, lender, rentDate, rtnDate, cNo, review, car.carNo, price, color, model, rrentDate, rrtnDate, return "
						+ "FROM rent, reserve, car, model "
						+ "where rent.rsvNo = reserve.rsvNo and reserve.carNo = car.carNo and car.modelNo = model.modelNo and "
						+ "rentNo = ?";
		
		jdbcUtil.setSqlAndParameters(allQuery, new Object[] { rentNo });		
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();	
			Rent dto = null;
			if (rs.next()) {	
				Model m = new Model(rs.getString("model"));
				Car c = new Car(rs.getInt("carNo"), rs.getInt("price"), rs.getString("color"), m);
				
				Reserve r = new Reserve(
						rs.getInt("rsvNo"),
						rs.getString("lender"),
						rs.getDate("rentDate"),
						rs.getDate("rtnDate"),
						c,
						rs.getInt("cNo"));
				
				dto = new Rent(rs.getInt("rentNo"), r, rs.getString("review"), rs.getDate("rrentDate"), rs.getDate("rrtnDate"), rs.getString("return"));	
			}
			return dto;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}		
		return null;	
	}

	public int insertRent(Reserve reserve) throws SQLException {
		String sql = "select RENTQUENCE.nextval from dual"; //시퀀스로 qNo받아오기

		jdbcUtil.setSqlAndParameters(sql, null);
		ResultSet rs = jdbcUtil.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		
		int result = 0;
		String insertQuery = "INSERT INTO RENT (rentNo, rsvNo) " +
							 "VALUES (?, ?) ";
		
		
		Object[] param = new Object[] {num, reserve.getReserveNo()};		
		jdbcUtil.setSql(insertQuery);			
		jdbcUtil.setParameters(param);			
				
		try {				
			result = jdbcUtil.executeUpdate();		
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1)
				;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		
		}		
		return result;			
	}
	
	
	public int returnRent(Rent rent) throws Exception {
		//if already return
		String sql = "SELECT return FROM rent WHERE rentno = ?";
		Object[] p = new Object[] {rent.getRentNo()};
		jdbcUtil.setSqlAndParameters(sql,  p);
		ResultSet r = jdbcUtil.executeQuery();
		r.next();
		String rtn = r.getString("return");
		
		
		if(rtn.equals("Y")) {
			System.out.println(rtn);
			return 0;
		}
		sql = "UPDATE CAR SET OCCUPIED ='Y' WHERE CARNO = (SELECT CARNO FROM RESERVE WHERE RSVNO = ?)";
		System.out.print(sql);
		Object[] params2 = {rent.getReserve().getReserveNo()};
		jdbcUtil.setSqlAndParameters(sql,  params2);
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("set car occupied");
			
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} 
		//return rent
		sql = "UPDATE RENT "
				+ "SET return = 'Y', rrtndate = sysdate "
				+ "WHERE rentNo = ?";
		Object[] param = new Object[] {rent.getRentNo()};
		jdbcUtil.setSqlAndParameters(sql,  param);
		int result = 0;
		try {
			result = jdbcUtil.executeUpdate();
		} catch (SQLException ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		
		//return rent for reserve
				sql = "UPDATE reserve "
						+ "SET state = 'ED' "
						+ "WHERE rsvNo = ?";
				Object[] param2 = new Object[] {rent.getReserve().getReserveNo()};
				jdbcUtil.setSqlAndParameters(sql,  param2);
				int result2 = 0;
				try {
					result2 = jdbcUtil.executeUpdate();
				} catch (SQLException ex) {
					jdbcUtil.rollback();
					ex.printStackTrace();
				}
		
		//get delay
		sql = "SELECT trunc(rent.rrtndate) - trunc(reserve.rtndate) \"delay\" "
				+ "from reserve, rent "
				+ "where rentno = ? and reserve.rsvno = rent.rsvno";
		jdbcUtil.setSqlAndParameters(sql,  param);
		
		ResultSet rs = jdbcUtil.executeQuery();
		int delay = 0;
		try {
			rs.next();
			delay = rs.getInt("delay");
		} catch(SQLException ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		
		if (delay > 0) {
			//EXIST IN OVERDUE
			sql = "SELECT CNO FROM OVERDUE WHERE CNO = ?";
			Object[] param3 = {rent.getCno()};
			
			System.out.println(rent.getCno());
			
			jdbcUtil.setSqlAndParameters(sql,  param3);
			
			rs = jdbcUtil.executeQuery();
			ResultSet temp = rs;
			int overdue = 0;
			int ocount = 0;
			//set overdueDay
			if(temp.next()) { //if exist

				//get overdue
				sql = "SELECT overdue, ocount FROM overdue where cno = ?";
				Object[] param4 = new Object[] {rent.getCno()};
				
				jdbcUtil.setSqlAndParameters(sql,  param4);
				rs = jdbcUtil.executeQuery();
				
				try {
					rs.next();
					overdue = rs.getInt("overdue");
					ocount = rs.getInt("ocount");
					System.out.println(overdue);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				overdue += delay;
				ocount += 1;
				//set overdue
				sql = "UPDATE OVERDUE SET overdue = ?, ocount = ? where cno = ?";
				Object[] param5 = {overdue, ocount, rent.getCno()};
				jdbcUtil.setSqlAndParameters(sql,  param5);
				
				try {
					result = jdbcUtil.executeUpdate();
				} catch (SQLException e){
					jdbcUtil.rollback();
					e.printStackTrace();
				}
			}else {//if not exist
				sql = "INSERT INTO OVERDUE (OVERDUE, OCOUNT, CNO) VALUES(?, 1, ?) ";
				Object[] param5 = {delay, rent.getCno()};
				jdbcUtil.setSqlAndParameters(sql,  param5);
				
				System.out.println(delay);
				
				try {
					result = jdbcUtil.executeUpdate();
					System.out.println(result);
				} catch (SQLException e) {
					jdbcUtil.rollback();
					e.printStackTrace();
				}
			}
			
			int plevel = 0;
			if(overdue <= 3 && ocount < 2) {
				plevel = 1;
			} else if(overdue <= 7 && ocount <= 3) {
				plevel = 2;
			} else if(overdue <= 15 && ocount <= 5) {
				plevel = 3;
			} else if(overdue <= 20 && ocount <= 7) {
				plevel = 4;
			} else if(overdue <= 30 && ocount <= 10) {
				plevel = 5;
			} else {
				plevel = 10;
			}
			sql = "UPDATE OVERDUE SET PLEVEL = ? WHERE CNO = ?";
			Object[] param6 = {plevel, rent.getCno()};
			jdbcUtil.setSqlAndParameters(sql,  param6);
			
			try {
				result = jdbcUtil.executeUpdate();
			} catch (SQLException e) {
				jdbcUtil.rollback();
				e.printStackTrace();
			}
			
		}
		jdbcUtil.commit();
		jdbcUtil.close();
		
		return rent.getRentNo();
	}
	
	public int insertReview(Rent rent) throws SQLException {
		//put review
		String inReview = "UPDATE RENT SET review = ? WHERE rentNo = ?";
		Object[] param = {rent.getReview(), rent.getRentNo()};
		jdbcUtil.setSqlAndParameters(inReview, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("set review success");
		} catch (Exception e){
			jdbcUtil.rollback();
			e.printStackTrace();
		}
		jdbcUtil.commit();
		jdbcUtil.close();
		return 0;
	}

	public List<String> getReviewList(int modelNo) throws SQLException {
		//get review
		String getReview = "SELECT distinct car.carno, review " + 
				"from rent, car, reserve " + 
				"where car.modelno = ? and reserve.carno = car.carno "
				+ "and rent.rsvno = reserve.rsvno and review is not null";
		System.out.println(getReview);
		System.out.println(modelNo);
		Object[] p0 = {modelNo};
		jdbcUtil.setSqlAndParameters(getReview, p0);
		ResultSet rs0 = jdbcUtil.executeQuery();
		
		List<String> reviews = new ArrayList();
		
		while(rs0.next()) {
			reviews.add(rs0.getString("review"));
		}
		return reviews;
	}


}


