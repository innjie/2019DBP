package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.sql.Date;
import model.Reserve;
import model.User;
import model.Car;
import model.Model;
import model.Rent;


public class ReserveDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReserveDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public int insertReserve(Reserve rsv) throws SQLException {
		//back -> retry case
		String sql = "SELECT OCCUPIED FROM CAR WHERE carno = ?";
		Object[] pa = {rsv.getCar().getCarNo()};
		System.out.println(rsv.getCar().getCarNo());
		jdbcUtil.setSqlAndParameters(sql,  pa);	
		ResultSet rs0 = jdbcUtil.executeQuery();
	
		try {
			rs0.next();
			String check = rs0.getString("OCCUPIED");
			System.out.println(check);
			if(check.equals("N"))
				return 0;
			
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} 
		
		
		sql = "select RSVQUENCE.nextval from dual";
		
		//get ReserveNo by sequence
		jdbcUtil.setSqlAndParameters(sql, null);
		ResultSet rs = jdbcUtil.executeQuery();
		rs.next();
		int no = rs.getInt(1);
		
		System.out.println(no);
		
		//get customerName by query
		sql = "select name from customer "
				+ "where customer.cno=? ";
		Object[] params = {rsv.getUser().getCustomerNo()};
		jdbcUtil.setSqlAndParameters(sql,  params);
		rs = jdbcUtil.executeQuery();
		rs.next();
		String name = rs.getString("name");
		System.out.println(name);
		
		sql = "INSERT INTO RESERVE (rsvNo, rsvDate, rentDate, rtnDate, cno, carNo, mNo, lender) "
				+ "VALUES (?, ?, ?, ?, ?, ?, 9000, ?)";
		Object[] params4 = {no, rsv.getReserveDate(), rsv.getRentDate(), rsv.getReturnDate(), rsv.getUser().getCustomerNo(), rsv.getCar().getCarNo(), name};
		jdbcUtil.setSqlAndParameters(sql, params4);
		
	
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("insert ok");
		} catch (Exception e) {
			jdbcUtil.rollback();
			//오류 (차 없음);
			e.printStackTrace();
		} 
		
		sql = "UPDATE CAR SET OCCUPIED='N' WHERE CARNO=?";
		Object[] params5 = {rsv.getCar().getCarNo()};
		jdbcUtil.setSqlAndParameters(sql,  params5);
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("insert ok");
			return no;
		} catch (Exception e) {
			jdbcUtil.rollback();
			//오류 (차 없음);
			e.printStackTrace();
		}  finally {
			System.out.println("finally");
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	public int deleteReserve(Reserve rsv) throws Exception {
		//if in rent
		String sql = "SELECT * FROM RENT WHERE RSVNO = ?";
		Object[] p0 = {rsv.getReserveNo()};
		jdbcUtil.setSqlAndParameters(sql,  p0);
		
		ResultSet rs0 = jdbcUtil.executeQuery();
		
		if(rs0.next()) {
			if(rs0 != null)
				return 0;
		}
		
		//cancel reserve
		sql = "UPDATE RESERVE SET CANCEL='Y', STATE='CANCEL' WHERE RSVNO=?";
		
		Object[] params = {rsv.getReserveNo()};
		jdbcUtil.setSqlAndParameters(sql,  params);		
	
		
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("cancel reserve");
			
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} 
		
		//set car occupied
		sql = "UPDATE CAR SET OCCUPIED ='Y' WHERE CARNO = (SELECT CARNO FROM RESERVE WHERE RSVNO = ?)";
		System.out.print(sql);
		Object[] params2 = {rsv.getReserveNo()};
		jdbcUtil.setSqlAndParameters(sql,  params2);
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("set car occupied");
			
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} 
		//get cno
		sql = "SELECT CNO FROM RESERVE WHERE RSVNO = ?";
		Object[] cparam = {rsv.getReserveNo()};
		jdbcUtil.setSqlAndParameters(sql,  cparam);
		int cno = 0;
		try {
			ResultSet cRs = jdbcUtil.executeQuery();
			cRs.next();
			cno = cRs.getInt("CNO");
			System.out.println(cno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(cno);
		//get delay
		sql = "SELECT trunc(sysdate) - trunc(reserve.rtndate) \"delay\" "
				+ "from reserve "
				+ "where rsvno = ?";
		Object[] dP = {rsv.getReserveNo()};
		jdbcUtil.setSqlAndParameters(sql,  dP);
		ResultSet rs = jdbcUtil.executeQuery();
		int delay = 0;
		
		try {
			rs.next();
			delay = rs.getInt("delay");
			System.out.println("get delay");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(delay > 0) {
			//Exist in overdue
			sql = "SELECT CNO FROM OVERDUE WHERE CNO = ?";
			Object[] dP2 = {cno};
			
			jdbcUtil.setSqlAndParameters(sql,  dP2);
			
			rs = jdbcUtil.executeQuery();
			ResultSet temp = rs;
			int overdue = 0;
			int ocount = 0;
			
			System.out.println("exist in overdue");
			//set OverdueDay
			if(temp.next()) {//if exist
				//get overdue
				
				sql = "SELECT overdue, ocount from overdue where cno = ?";
				Object[] dp3 = {cno};
				jdbcUtil.setSqlAndParameters(sql,  dp3);
				rs = jdbcUtil.executeQuery();
				
				try {
					rs.next();
					overdue = rs.getInt("overdue");
					ocount = rs.getInt("ocount");
					System.out.println("exist in overdue");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				overdue += delay;
				ocount += 1;
				
				//set overdue
				
				sql = "UPDATE OVERDUE SET overdue = ?, ocount = ? where cno = ?";
				Object[] dp4 = {overdue, ocount, cno};
				jdbcUtil.setSqlAndParameters(sql,  dp4);
				try {
					int result = jdbcUtil.executeUpdate();
					System.out.println("set overdue");
				} catch (SQLException e) {
					jdbcUtil.rollback();
					e.printStackTrace();
				}
			} else { //not exist in overdue table
				sql = "INSERT INTO OVERDUE (OVERDUE, OCOUNT, CNO) VALUES (?, 1, ?) ";
				Object[] dp5 = {delay, cno};
				jdbcUtil.setSqlAndParameters(sql,  dp5);
				System.out.println(delay);
				
				try {
					int result = jdbcUtil.executeUpdate();
					System.out.println("not exist in ovredue");
				}catch (SQLException e) {
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
			Object[] param6 = {plevel, cno};
			jdbcUtil.setSqlAndParameters(sql,  param6);
			
			try {
				int result = jdbcUtil.executeUpdate();
				System.out.println("plevel : " + plevel);
			} catch (SQLException e) {
				jdbcUtil.rollback();
				e.printStackTrace();
			}
			
		}
		jdbcUtil.commit();
		jdbcUtil.close();
		return rsv.getReserveNo();
	}
	public Reserve getOneReserve(int rsvNo) throws SQLException {
		String sql = "select rsvDate, lender, cancel, car.carNo, rentDate, rtnDate, state, MONTHRENT, RENTCOUNT, model, brand, passenger "
					+ "from reserve, car, model where model.modelNo =  car.modelNo and reserve.carNo = car.carNo and rsvno = ? order by rsvNo";
		Object[] params = {rsvNo};
		jdbcUtil.setSqlAndParameters(sql,  params);
		
		ResultSet rs = jdbcUtil.executeQuery();
		int carNo = 0;
		Reserve rsv = new Reserve();
		rsv.setReserveNo(rsvNo);
		System.out.println(rsvNo);
		
		if(rs.next()) {
			Car car = new Car();
			car.setCarNo(rs.getInt("carNo"));
			car.setRentCount(rs.getInt("RENTCOUNT"));
			car.setMonthRent(rs.getInt("MONTHRENT"));
			
			Model model = new Model();
			model.setModelName(rs.getString("model"));
			model.setBrand(rs.getString("brand"));
			model.setPassenger(rs.getInt("passenger"));
			rsv.setModel(model);
			
			rsv.setCar(car);
			rsv.setReserveDate(rs.getDate("rsvDate"));
			rsv.setRentDate(rs.getDate("rentDate"));
			rsv.setReturnDate(rs.getDate("rtnDate"));
			rsv.setLender(rs.getString("lender"));
			rsv.setCancel(rs.getString("cancel"));
			rsv.setState(rs.getString("state"));
		
		}
		

		
		return rsv;
	}
	
	public List<Reserve> findReserve(int cNo, int currentPage, int countPerPage) throws SQLException {
		String sql = "select rsvNo, rsvDate, rentDate, rtnDate, cNo, carNo, lender, cancel "
				+ "from reserve "
				+ "where cNo=? "
				+ "order by rsvNo";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {cNo}, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = jdbcUtil.executeQuery();
		
		
		List<Reserve> list = new ArrayList<Reserve>();
		int start = ((currentPage-1) * countPerPage) + 1;
		int countPerPage2 = countPerPage;
		
		if ((start >= 0) && rs.absolute(start)) {
			do {
				Reserve rsv = new Reserve();
				
				rsv.setCancel(rs.getString("cancel"));
				rsv.setReserveNo(rs.getInt("rsvNo"));
				rsv.setReserveDate(rs.getDate("rsvDate"));
				rsv.getUser().setCustomerNo(rs.getInt("cNo"));
				rsv.getCar().setCarNo(rs.getInt("carNo"));
				
				list.add(rsv);
			}while (rs.next() && (--countPerPage > 0));
		}
		
		
		sql = "select rsvNo, model, brand, passenger, reserve.lender "
				+"from model, car, reserve "
				+"where model.modelno = car.modelno "
				+"and car.carNo = reserve.carNo "
				+"and reserve.cno=? "
				+"order by rsvNo";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {cNo}, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs2 = jdbcUtil.executeQuery();
		
		int index = 0;
		if ((start >= 0) && rs2.absolute(start)) {
			do {
				
				Model model = new Model();
				model.setModelName(rs2.getString("model"));
				model.setBrand(rs2.getString("brand"));
				model.setPassenger(rs2.getInt("passenger"));
				
				list.get(index).setModel(model);
				//rsv.setModelDTO(model);
				list.get(index).setLender(rs2.getString("lender"));
				index++;
			}while (rs2.next() && (--countPerPage2 > 0));
		}
		
		
		
		return list;
	}
	
	public List<Reserve> findReserve(int cNo) throws SQLException {
		String sql = "select rsvNo, rsvDate, rentDate, rtnDate, cNo, carNo, lender, cancel "
				+ "from reserve "
				+ "where cNo=? "
				+ "order by rsvNo";
		
		Object[] params = {cNo};
		jdbcUtil.setSqlAndParameters(sql, params);
		
		ResultSet rs = jdbcUtil.executeQuery();
		
		
		List<Reserve> list = new ArrayList<Reserve>();
		
		
		while(rs.next()) {
			Reserve rsv = new Reserve();
			
			rsv.setCancel(rs.getString("cancel"));
			rsv.setReserveNo(rs.getInt("rsvNo"));
			rsv.setReserveDate(rs.getDate("rsvDate"));
			rsv.getUser().setCustomerNo(rs.getInt("cNo"));
			rsv.getCar().setCarNo(rs.getInt("carNo"));
			
			list.add(rsv);
		}
		
		sql = "select rsvNo, model, brand, passenger, reserve.lender "
				+"from model, car, reserve "
				+"where model.modelno = car.modelno "
				+"and car.carNo = reserve.carNo "
				+"and reserve.cno=? "
				+"order by rsvNo";
		Object[] params2 = {cNo};
		jdbcUtil.setSqlAndParameters(sql,  params2);
		ResultSet rs2 = jdbcUtil.executeQuery();
		
		int index = 0;
		while(rs2.next()) {
			
			Model model = new Model();
			model.setModelName(rs2.getString("model"));
			model.setBrand(rs2.getString("brand"));
			model.setPassenger(rs2.getInt("passenger"));
			
			list.get(index).setModel(model);
			//rsv.setModelDTO(model);
			list.get(index).setLender(rs2.getString("lender"));
			index++;
		}
		
		
		
		return list;
	}

	public List<Reserve> getReserveList() {
		String allQuery = "SELECT rsvNo, lender, modelNo, car.carNo, rsvDate, rentDate, rtnDate, cancel, cNo, state "
						+ "FROM reserve, car "
						+ "where reserve.carNo = car.carNo "
						+ "order by rsvNo";
		
		jdbcUtil.setSqlAndParameters(allQuery, null);
		List<Reserve> list = new ArrayList<Reserve>();		

		try { 
			ResultSet rs = jdbcUtil.executeQuery();		
			while (rs.next()) {	
				Car c = new Car(rs.getInt("carNo"), rs.getInt("modelNo"));
				
				Reserve dto = new Reserve(
						rs.getInt("rsvNo"),
						rs.getString("lender"),
						rs.getDate("rsvDate"),
						rs.getDate("rentDate"),
						rs.getDate("rtnDate"),
						c,
						rs.getInt("cNo"),
						rs.getString("cancel"),
						rs.getString("state"));
							
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
	
	public List<Reserve> getReserveList(int currentPage, int countPerPage) {
		String allQuery = "SELECT rsvNo, lender, modelNo, car.carNo, rsvDate, rentDate, rtnDate, cancel, cNo, state "
						+ "FROM reserve, car "
						+ "where reserve.carNo = car.carNo "
						+ "order by rsvNo";
		
		jdbcUtil.setSqlAndParameters(allQuery, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		List<Reserve> list = new ArrayList<Reserve>();		

		try { 
			ResultSet rs = jdbcUtil.executeQuery();		
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					Car c = new Car(rs.getInt("carNo"), rs.getInt("modelNo"));
					
					Reserve dto = new Reserve(
							rs.getInt("rsvNo"),
							rs.getString("lender"),
							rs.getDate("rsvDate"),
							rs.getDate("rentDate"),
							rs.getDate("rtnDate"),
							c,
							rs.getInt("cNo"),
							rs.getString("cancel"),
							rs.getString("state"));
								
					list.add(dto);		
				} while (rs.next() && (--countPerPage > 0));
			}
			
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}		
		return null;	
	}
	
	
	public int rentCar(int reserveNo, int mno) {
		String sql = "UPDATE reserve "
				+ "SET State = ?, mno = ? "
				+ "WHERE rsvNo = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[]{ "ING", mno, reserveNo });
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;

	}
	
	
	/**
	 * //getCarNumber
		sql = "select carNo, occupied from car "
				+ "where modelNo=(select modelNo from model where model=?)";
		
		Object[] params2 = {rsv.getCarDTO().getModelDTO().getModelName()};
		jdbcUtil.setSqlAndParameters(sql,  params2);
		rs = jdbcUtil.executeQuery();
		
		ArrayList carNo = new ArrayList();
		
		while(rs.next()) {
			carNo.add(rs.getString("carNo"));
		}
		
		String cancel;
		String carNum = null;
		int i = 0;
		while(carNo.get(i) != null) {
			sqlForCarNo = "select occupied from car "
					+ "where carno=?";
			carNum = (String) carNo.get(i);
			Object[] params3 = {carNum};
			jdbcUtil.setSqlAndParameters(sqlForCarNo, params3);
			rs = jdbcUtil.executeQuery();
			
			rs.next();
			cancel = rs.getString("occupied");
			
			if(cancel.equals("Y"))
				break;
		}
		
	 */
	
}