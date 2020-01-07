package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Manager;

public class ModelDAO {
private JDBCUtil jdbcUtil = null;
	
			
	
	public ModelDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<Model> getModelList() {
		String allQuery = "select * " + "from model order by modelNo";
		jdbcUtil.setSqlAndParameters(allQuery, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Model> list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
			
			while(rs.next()) {	
				Model dto = new Model(
						rs.getInt("modelNo"),
						rs.getString("model"),
						rs.getInt("modelCount"),
						rs.getString("brand"),
						rs.getInt("passenger"),
						rs.getDate("year"),
						rs.getInt("mNo"));
						//setModelColor(rs.getInt("modelNo")));,
						//setRentCount(rs.getInt("modelNo")));
				
			//	dto.setColor(setModelColor(dto.getModelNo()));
			//	dto.setRentCount(dto.getModelNo());

				list.add(dto); // 리스트에 DTO 객체 저장
			}
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} return null;
	}
	
	public List<Model> getModelList(int currentPage, int countPerPage) {
		String allQuery = "select * " + "from model order by modelNo";
		jdbcUtil.setSqlAndParameters(allQuery, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Model> list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					Model dto = new Model(
							rs.getInt("modelNo"),
							rs.getString("model"),
							rs.getInt("modelCount"),
							rs.getString("brand"),
							rs.getInt("passenger"),
							rs.getDate("year"),
							rs.getInt("mNo"));
							//setModelColor(rs.getInt("modelNo")));,
							//setRentCount(rs.getInt("modelNo")));
					
				//	dto.setColor(setModelColor(dto.getModelNo()));
				//	dto.setRentCount(dto.getModelNo());

					list.add(dto); // 리스트에 DTO 객체 저장
				}while (rs.next() && (--countPerPage > 0));
			}
			
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} return null;
	}
	
	//이달의 차
	public List<Model> getMonthlyModel() {
		String allQuery = "select RowNum, model.modelNo, model, brand, passenger, s " + 
						"from model, ( select modelNo, sum(monthRent) s " +
										"from car " +
										"group by modelNo " +
										"order by s desc ) m " +
						"where model.modelNo = m.modelNo and RowNum <= 5";
		jdbcUtil.setSqlAndParameters(allQuery, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Model> list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
			
			while(rs.next()) {	
				Model dto = new Model(
						rs.getInt("modelNo"),
						rs.getString("model"),
						rs.getString("brand"),
						rs.getInt("passenger"),
						rs.getInt("s"),
						rs.getInt("RowNum"));
						
				

				list.add(dto); // 리스트에 DTO 객체 저장
			}

			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} return null;
	}

	//색 가격 탑승인원으로 찾기
	public List<Model> findModel(String price, String color, String passenger, int currentPage, int countPerPage) {
		String searchQuery = "select distinct model.modelNo, model, modelCount, brand, passenger " 
						+ "from model, car where model.modelNo = car.ModelNo ";
		Object[] object = new Object[4];
		Object[] newObject;
		int oSize = 0;
		
		
		//조건 처리
		if( price != null) {
			if (price.equals("200000")){
				searchQuery = searchQuery + "and price >= ? ";
				object[oSize++] = price;
				
			} else {
				searchQuery = searchQuery + "and price between ? and ? ";
				object[oSize++] = Integer.parseInt(price)-49999;
				object[oSize++] = price;				
			}
						
		}
		
		
		if(color != null) {
			switch(color) {
			case "1":
				color = "white";
				break;
			case "2":
				color = "black";
				break;
			case "3":
				color = "gray";
				break;
			case "4":
				color = "red";
				break;
			case "5":
				color = "blue";
				break;
			}
			searchQuery = searchQuery + "and color = ? ";
			object[oSize++] = color;
		}
		
		if(passenger != null) {
			if (passenger.equals("13")){
				searchQuery = searchQuery + "and passenger >= ? ";
			} else {
				searchQuery = searchQuery + "and passenger <= ? ";
			}
			object[oSize++] = passenger;
		}
		
		if(oSize == 0) {
			newObject =null;
		}
		else {
			newObject = new Object[oSize];
			for(int i = 0; i < oSize; i++) {
				newObject[i] = object[i];
			}
		}
		
		searchQuery = searchQuery + "and occupied = \'Y\' order by model.modelNo";
		jdbcUtil.setSqlAndParameters(searchQuery, newObject, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		
		List<Model> list = null; // DTO 객체들을 담기 위한 리스트
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do{
					Model dto = new Model(
							rs.getInt("modelNo"),
							rs.getString("model"),
							rs.getInt("modelCount"),
							rs.getString("brand"),
							rs.getInt("passenger"));
						//	setModelColor(rs.getInt("modelNo")),
						//	setRentCount(rs.getInt("modelNo")));

					list.add(dto); // 리스트에 DTO 객체 저장
				}while (rs.next() && (--countPerPage > 0));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} return list;
	}
	
	//색 가격 탑승인원으로 찾기
		public List<Model> findModel(String price, String color, String passenger) {
			String searchQuery = "select distinct model.modelNo, model, modelCount, brand, passenger " 
							+ "from model, car where model.modelNo = car.ModelNo ";
			Object[] object = new Object[4];
			Object[] newObject;
			int oSize = 0;
			
			
			//조건 처리
			if( price != null) {
				if (price.equals("200000")){
					searchQuery = searchQuery + "and price >= ? ";
					object[oSize++] = price;
					
				} else {
					searchQuery = searchQuery + "and price between ? and ? ";
					object[oSize++] = Integer.parseInt(price)-49999;
					object[oSize++] = price;				
				}
							
			}
			
			
			if(color != null) {
				switch(color) {
				case "1":
					color = "white";
					break;
				case "2":
					color = "black";
					break;
				case "3":
					color = "gray";
					break;
				case "4":
					color = "red";
					break;
				case "5":
					color = "blue";
					break;
				}
				searchQuery = searchQuery + "and color = ? ";
				object[oSize++] = color;
			}
			
			if(passenger != null) {
				if (passenger.equals("13")){
					searchQuery = searchQuery + "and passenger >= ? ";
				} else {
					searchQuery = searchQuery + "and passenger <= ? ";
				}
				object[oSize++] = passenger;
			}
			
			if(oSize == 0) {
				newObject =null;
			}
			else {
				newObject = new Object[oSize];
				for(int i = 0; i < oSize; i++) {
					newObject[i] = object[i];
				}
			}
			
			searchQuery = searchQuery + "and occupied = \'Y\' order by model.modelNo";
			jdbcUtil.setSqlAndParameters(searchQuery, newObject);
			
			
			List<Model> list = null; // DTO 객체들을 담기 위한 리스트
			try {
				ResultSet rs = jdbcUtil.executeQuery(); // query 실행
				list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
				
				while (rs.next()) {
					Model dto = new Model(
							rs.getInt("modelNo"),
							rs.getString("model"),
							rs.getInt("modelCount"),
							rs.getString("brand"),
							rs.getInt("passenger"));
						//	setModelColor(rs.getInt("modelNo")),
						//	setRentCount(rs.getInt("modelNo")));

					list.add(dto); // 리스트에 DTO 객체 저장
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
			} return list;
		}
	
	//모델이름으로 찾기
	public List<Model> getModelByName(String modelName) {
		String searchQuery = "select modelNo, model, modelCount, brand, passenger " 
						+ "from model "
						+ "where model like '%' || ? || '%' "
						+ "order by modelNo";
		jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { modelName });
		
		List<Model> list = null; // DTO 객체들을 담기 위한 리스트
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
			
			while (rs.next()) {
				Model dto = new Model(
						rs.getInt("modelNo"),
						rs.getString("model"),
						rs.getInt("modelCount"),
						rs.getString("brand"),
						rs.getInt("passenger"));
					//	setModelColor(rs.getInt("modelNo")),
					//	setRentCount(rs.getInt("modelNo")));

				list.add(dto); // 리스트에 DTO 객체 저장
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} return list;
	}
	
	//모델이름으로 찾기
		public List<Model> getModelByName(String modelName, int currentPage, int countPerPage) {
			String searchQuery = "select modelNo, model, modelCount, brand, passenger " 
							+ "from model "
							+ "where model like '%' || ? || '%' "
							+ "order by modelNo";
			jdbcUtil.setSqlAndParameters(searchQuery, new Object[] { modelName }, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
					ResultSet.CONCUR_READ_ONLY);
			
			
			List<Model> list = null; // DTO 객체들을 담기 위한 리스트
			try {
				ResultSet rs = jdbcUtil.executeQuery(); // query 실행
				list = new ArrayList<Model>(); // DTO 객체들을 담기 위한 리스트
				int start = ((currentPage-1) * countPerPage) + 1;
				
				if ((start >= 0) && rs.absolute(start)) {
					do {
						Model dto = new Model(
								rs.getInt("modelNo"),
								rs.getString("model"),
								rs.getInt("modelCount"),
								rs.getString("brand"),
								rs.getInt("passenger"));
							//	setModelColor(rs.getInt("modelNo")),
							//	setRentCount(rs.getInt("modelNo")));

						list.add(dto); // 리스트에 DTO 객체 저장
					}while (rs.next() && (--countPerPage > 0));
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
			} return list;
		}
		
	
	//모델번호로 찾기
	public Model getModelByModelNo(int modelNo) {
		String allQuery = "select * " + "from model where modelNo = ?";
		jdbcUtil.setSqlAndParameters(allQuery, new Object[] { modelNo });
		Model dto = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {	
				dto = new Model(
						rs.getInt("modelNo"),
						rs.getString("model"),
						rs.getInt("modelCount"),
						rs.getString("brand"),
						rs.getInt("passenger"),
						rs.getDate("year"),
						rs.getInt("mNo"));
						//setModelColor(rs.getInt("modelNo")),
						//setRentCount(rs.getInt("modelNo")));
				
			//	dto.setColor(setModelColor(dto.getModelNo()));
			//	dto.setRentCount(dto.getModelNo());

			}
			return dto; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} return null;
	}
	
	//모델이름별 색 설정
	public List<String> setModelColor(int modelNo) {
		String sql = "select distinct color " + "from model, car " + 
								"where model.modelNo = car.modelno and car.modelNo = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { modelNo });
		List<String> list = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			list = new ArrayList<String>(); // DTO 객체들을 담기 위한 리스트
			while (rs.next()) {
				list.add(rs.getString("color")); 
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		//	jdbcUtil.closeP(); // ResultSet, PreparedStatement 반환
		} 
		return list;
	}
	
	//모델별 대여 횟수 설정
	public int setRentCount(int modelNo) {
		String sql = "select modelNo, sum(monthRent) rentCnt " + "from car " + 
								"where modelNo = ? " + "group by modelNo";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { modelNo });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if(rs.next())
				return rs.getInt("rentCnt");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		//	jdbcUtil.closeP(); // ResultSet, PreparedStatement 반환
		} 
		return 0;
	}

	public int insertModel(Model model) throws SQLException {
		String sql = "select modquence.nextval from dual"; //시퀀스로 modelNo받아오기

		jdbcUtil.setSqlAndParameters(sql, null);
		ResultSet rs = jdbcUtil.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		
		int result = 0;
		String insertQuery = "insert into model(modelNo, model, modelCount, brand, passenger, year, mno) "
							+ "values(?, ?, ?, ?, ?, ?, ?)";
		
		jdbcUtil.setSqlAndParameters(insertQuery, new Object[] {num, model.getModelName(), 0, model.getBrand(), model.getPassenger(), model.getYear(), model.getManager().getManagerNo()});
		
		try {
			result = jdbcUtil.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
			{
				// 오류 메시지
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		} return result;

	}

	public int updateModel(Model model) {
		String updateQuery = "UPDATE model SET modelCount = modelCount+1 where modelNo = ?";
		
		jdbcUtil.setSqlAndParameters(updateQuery, new Object[] { model.getModelNo() });
		
		try {
			int result = jdbcUtil.executeUpdate();		// update 문 실행
			return result;			// update 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return 0;

	}
	
	public int updateModelDeleteCar(int modelNo) {
		String updateQuery = "UPDATE model SET modelCount = modelCount-1 where modelNo = ?";
		
		jdbcUtil.setSqlAndParameters(updateQuery, new Object[] { modelNo });
		
		try {
			int result = jdbcUtil.executeUpdate();		// update 문 실행
			return result;			// update 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return 0;

	}

	
	public int deleteModel(int modelNo) {
		String sql = "DELETE FROM Model WHERE modelNo = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { modelNo });
		
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
	
	public String price(String price) {
		if(price != null) {
			switch(price) {
			case "49999":
				price = "0-49999원";
				break;
			case "99999":
				price = "50000-99999원";
				break;
			case "149999":
				price = "100000-149999원";
				break;
			case "199999":
				price = "150000-199999원";
				break;
			case "200000":
				price = "200000원 이상";
				break;
			}
			
			return price;
		}
		return "all";
	}
	public String color(String color) {
		if(color != null) {
			switch(color) {
			case "1":
				color = "white";
				break;
			case "2":
				color = "black";
				break;
			case "3":
				color = "gray";
				break;
			case "4":
				color = "red";
				break;
			case "5":
				color = "blue";
				break;
			}
			
			return color;		
		}
		return "all";
	}
	public String passenger(String passenger) {
		if(passenger != null) {
			switch(passenger) {
			case "1":
				passenger = "2명 이하";
				break;
			case "5":
				passenger = "5명 이하";
				break;
			case "9":
				passenger = "9명 이하";
				break;
			case "12":
				passenger = "12명 이하";
				break;
			case "13":
				passenger = "blue";
				break;
			}
			
			return passenger;
		}
		return "all";
	}
	
	
	/*
//모델별 대여 횟수 설정
	public int setRentCount(int modelNo) {
		String sql = "select modelNo, sum(monthRent) rentCnt " + "from car " + 
								"where modelNo = ? " + "group by modelNo";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { modelNo });
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if(rs.next())
				return rs.getInt("rentCnt");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
	//		jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		} 
		return 0;
	}

	public int insertModel(Model model) {
		int result = 0;
		String insertQuery = "insert into model " + "values(?, ?, ?, ?, ?, ?, ?)";
		
		jdbcUtil.setSqlAndParameters(insertQuery, new Object[] {model.getModelNo(), model.getModelName(), model.getModelCount(), model.getBrand(), model.getPassenger(), model.getYear(), model.getManagerDTO().getManagerNo()});
		
		try {
			result = jdbcUtil.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
			{
				// 오류 메시지
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		} return result;

	}

	 * 
	 * 
	 */
	
	
}
