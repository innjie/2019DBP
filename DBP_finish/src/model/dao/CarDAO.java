package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Car;
import model.Model;
import model.Manager;

public class CarDAO {
private JDBCUtil jdbcUtil = null;
		
	public CarDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public Car reserveCar(int modelNo, int price, String color) {
		String searchQuery = "select distinct carNo, PRICE, COLOR, MODEL, brand, passenger "
				+ "from CAR, Model " + "where car.modelNo = model.modelNo and "
				+ "car.modelNo = ? and price = ? and color = ? and occupied = ?";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { modelNo, price, color, "Y" });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			Car dto = null;
			if (rs.next()) { 
				Model m = new Model(rs.getString("MODEL"), rs.getString("brand"), rs.getInt("passenger"));

				dto = new Car(
						rs.getInt("carNo"),
						rs.getInt("PRICE"),
						rs.getString("COLOR"),
						m); 
			}
			return dto;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		} return null;
	}
	
	public List<Car> getCarList() {
		String allQuery = "select CARNO, PRICE, OCCUPIED, COLOR, MODELNO, RENTCOUNT, MONTHRENT "
						+ "from CAR order by CARNO";
		jdbcUtil.setSqlAndParameters(allQuery, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<Car> list = new ArrayList<Car>(); 
	
			
			while (rs.next()) {	
				Model m = new Model(rs.getInt("MODELNO"));
				Car dto = new Car(
						rs.getInt("CARNO"),
						rs.getInt("PRICE"),
						rs.getString("OCCUPIED"),
						rs.getString("COLOR"),
						rs.getInt("RENTCOUNT"),
						m,
						rs.getInt("MONTHRENT"));

				list.add(dto); 
			}
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	public List<Car> getCarListByModelNo(int modelNo) {
		String allQuery = "select CARNO, PRICE, OCCUPIED, COLOR, car.MODELNO, RENTCOUNT, MONTHRENT, Model "
						+ "from CAR, model where car.modelNo = model.modelNo "
						+ "and car.modelNo = ? order by CARNO";
		jdbcUtil.setSqlAndParameters(allQuery, new Object[]{ modelNo });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<Car> list = new ArrayList<Car>();
	
			
			while (rs.next()) {	
				Model m = new Model(rs.getInt("MODELNO"), rs.getString("model"));
				Car dto = new Car(
						rs.getInt("CARNO"),
						rs.getInt("PRICE"),
						rs.getString("OCCUPIED"),
						rs.getString("COLOR"),
						rs.getInt("RENTCOUNT"),
						m,
						rs.getInt("MONTHRENT"));

				list.add(dto); 
			}
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	public List<Car> getCarListByModelNo(int modelNo, int currentPage, int countPerPage) {
		String allQuery = "select CARNO, PRICE, OCCUPIED, COLOR, car.MODELNO, RENTCOUNT, MONTHRENT, Model "
						+ "from CAR, model where car.modelNo = model.modelNo "
						+ "and car.modelNo = ? order by CARNO";
		jdbcUtil.setSqlAndParameters(allQuery, new Object[]{ modelNo }, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<Car> list = new ArrayList<Car>();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					Model m = new Model(rs.getInt("MODELNO"), rs.getString("model"));
					Car dto = new Car(
							rs.getInt("CARNO"),
							rs.getInt("PRICE"),
							rs.getString("OCCUPIED"),
							rs.getString("COLOR"),
							rs.getInt("RENTCOUNT"),
							m,
							rs.getInt("MONTHRENT"));

					list.add(dto); 
				}while (rs.next() && (--countPerPage > 0));
			}
			
			
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}


	public Car getCarByNo(int carNo) {
		String searchQuery = "select CARNO, PRICE, OCCUPIED, COLOR, MODEL "
							+ "from CAR, Model " + "where car.modelNo = model.modelNo and CARNO = ? ";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { carNo });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			Car dto = null;
			if (rs.next()) { 
				Model m = new Model(rs.getString("MODEL"));

				dto = new Car(
						rs.getInt("CARNO"),
						rs.getInt("PRICE"),
						rs.getString("OCCUPIED"),
						rs.getString("COLOR"),
						m); 
			}
			return dto;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		} return null;
	}
	
	public Car getCar(int carNo) {
		String searchQuery = "select CARNO, PRICE, OCCUPIED, COLOR, car.modelNo, model, year, brand, passenger, rentCount, monthRent "
							+ "from CAR, Model " + "where car.modelNo = model.modelNo and CARNO = ? ";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { carNo });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			Car dto = null;
			if (rs.next()) { 
				Model m = new Model(rs.getInt("modelNo"), rs.getString("model"), rs.getInt("passenger"), rs.getDate("year"), rs.getString("brand"));

				dto = new Car(
						rs.getInt("CARNO"),
						rs.getInt("PRICE"),
						rs.getString("OCCUPIED"),
						rs.getString("COLOR"),
						rs.getInt("rentCount"),
						m,
						rs.getInt("monthRent")); 
			}
			return dto;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		} return null;
	}
	
	public List<Car> getCarByModel(int modelNo, int price, String color) {
		String searchQuery = "select carNo, OCCUPIED, PRICE, COLOR, MODEL "
						+ "from CAR, Model " + "where car.modelNo = model.modelNo and "
						+ "car.modelNo = ? and price = ? and color = ?";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { modelNo, price, color });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Car> list = new ArrayList<Car>();
			
			while (rs.next()) {	
				Model m = new Model(modelNo, rs.getString("MODEL"));
				Car dto = new Car(
						rs.getInt("carNo"),
						rs.getInt("PRICE"),
						rs.getString("OCCUPIED"),
						rs.getString("COLOR"),
						m); 

				list.add(dto); 
			}
			
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		} return null;
	}
	
	public List<Car> getCarByModel(int modelNo, int price, String color, int currentPage, int countPerPage) {
		String searchQuery = "select carNo, OCCUPIED, PRICE, COLOR, MODEL "
						+ "from CAR, Model " + "where car.modelNo = model.modelNo and "
						+ "car.modelNo = ? and price = ? and color = ?";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { modelNo, price, color }, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Car> list = new ArrayList<Car>();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					Model m = new Model(modelNo, rs.getString("MODEL"));
					Car dto = new Car(
							rs.getInt("carNo"),
							rs.getInt("PRICE"),
							rs.getString("OCCUPIED"),
							rs.getString("COLOR"),
							m); 

					list.add(dto); 
				}while (rs.next() && (--countPerPage > 0));
			}
			
			
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		} return null;
	}
	
	
	public List<Car> getSameCarByModel(int modelNo) {
		String searchQuery = "select distinct PRICE, COLOR, MODEL "
						+ "from CAR, Model " 
						+ "where car.modelNo = model.modelNo and car.modelNo = ? and occupied = ?";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { modelNo, "Y" });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Car> list = new ArrayList<Car>();
			
			while (rs.next()) {	
				Model m = new Model(modelNo, rs.getString("MODEL"));
				Car dto = new Car(
						rs.getInt("PRICE"),
						rs.getString("COLOR"),
						m); 

				list.add(dto); 
			}
			
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	


	public int insertCar(Car car) throws SQLException {
		String s = "select carquence.nextval from dual";

		jdbcUtil.setSqlAndParameters(s, null);
		ResultSet rs = jdbcUtil.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		
		String sql = "INSERT INTO CAR (CARNO, PRICE, OCCUPIED, COLOR, RENTCOUNT, MODELNO, MONTHRENT) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {num, car.getPrice(), "Y", car.getColor(), 0, car.getModel().getModelNo(), 0});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}


	public int rentCar(Car car) {
		String sql = "UPDATE CAR "
				+ "SET rentCount = ?, monthRent = ? "
				+ "WHERE CARNO = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {car.getRentCount() + 1, car.getMonthRent() + 1, car.getCarNo()});
		
		try {
			System.out.println("bbbb:" + car.getRentCount());
			System.out.println("bbbb:" + car.getMonthRent());
			System.out.println("bbbb:" + car.getCarNo());
			int result = jdbcUtil.executeUpdate();
			System.out.println("cccc:" + car.getRentCount());
			System.out.println("cccc:" + car.getMonthRent());
			System.out.println("cccc:" + car.getCarNo());
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
	
	public int firstMonth() {
		String sql = "UPDATE CAR SET monthRent = 0";
		
		jdbcUtil.setSqlAndParameters(sql, null);
		
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
	

	public int returnCar(Car car) {
		String sql = "UPDATE CAR "
				+ "SET OCCUPIED = ? "
				+ "WHERE CARNO=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {"Y", car.getCarNo()});
		
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


	public int deleteCar(int carNo) {
		String sql = "DELETE FROM CAR WHERE CARNO=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { carNo });
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	public List<Car> getAllList() throws SQLException {
		String sql = "SELECT carNo, model.modelNo, model, brand, price, color, passenger, year, occupied "
				+ "from car, model "
				+ "where car.modelNo = model.modelNo "
				+ "order by car.carno";
		
		jdbcUtil.setSql(sql);
		ResultSet rs = jdbcUtil.executeQuery();
		
		List<Car> list = new ArrayList<Car>();
		while(rs.next()) {
			Car car = new Car();
			Model model = new Model();
			
			System.out.println(rs.getInt("modelNo"));
			
			car.setCarNo(rs.getInt("carNo"));
			model.setModelName(rs.getString("model"));
			model.setBrand(rs.getString("model"));
			model.setModelNo(rs.getInt("modelNo"));
			car.setPrice(rs.getInt("price"));
			car.setColor(rs.getString("color"));
			model.setPassenger(rs.getInt("passenger"));
			model.setYear(rs.getDate("year"));
			car.setOccupied(rs.getString("occupied"));
			
			car.setModel(model);
			
			list.add(car);
			
		}
		return list;
	}
	public List<Car> getAllList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT carNo, model.modelNo, model, brand, price, color, passenger, year, occupied "
				+ "from car, model "
				+ "where car.modelNo = model.modelNo "
				+ "order by car.carno";
		
		jdbcUtil.setSqlAndParameters(sql, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = jdbcUtil.executeQuery();
		
		List<Car> list = new ArrayList<Car>();
		int start = ((currentPage-1) * countPerPage) + 1;
		
		if ((start >= 0) && rs.absolute(start)) {
			do{
			Car car = new Car();
			Model model = new Model();
			
			System.out.println(rs.getInt("modelNo"));
			
			car.setCarNo(rs.getInt("carNo"));
			model.setModelName(rs.getString("model"));
			model.setBrand(rs.getString("model"));
			model.setModelNo(rs.getInt("modelNo"));
			car.setPrice(rs.getInt("price"));
			car.setColor(rs.getString("color"));
			model.setPassenger(rs.getInt("passenger"));
			model.setYear(rs.getDate("year"));
			car.setOccupied(rs.getString("occupied"));
			
			car.setModel(model);
			
			list.add(car);
			
			}while (rs.next() && (--countPerPage > 0));
			
		}
		return list;
	}
}
