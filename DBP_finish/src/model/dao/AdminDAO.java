package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*import model.Car;
import model.License;
import model.Model;
import model.Overdue;
import model.Penalty;*/
import model.Manager;
import model.Authority;
import model.Company;


public class AdminDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AdminDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}
		
	public int create(Manager manager) throws SQLException {
		String sql = "INSERT INTO MANAGER (id, name, phone, password, mno, task) "
					+ "VALUES (?, ?, ?, ?, MNGQUENCE.nextval, ?)";		
		Object[] param = new Object[] {manager.getId(), manager.getName(), 
				manager.getPhone(), manager.getPassword(), manager.getAuthority().getAuthorityName()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
		System.out.println("-------------" + manager.getName());
		System.out.println("222222222" + manager.getManagerNo());
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;			
	}
	
	
	
	public int findMNo(String id) {
		String sql = "select mNo from manager where id = ?";
		Object[] param = new Object[] {id};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
					
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						
				return rs.getInt("mNo");
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;	
	}
	
	public String findName(int findMNo) {
		String sql = "select name from manager where mNo = ?";
		Object[] param = new Object[] {findMNo};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
					
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						
				return rs.getString("name");
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return "";	
	}
	
	public String findTask(int mNo) {
		String sql = "select task from manager where mNo = ?";			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mNo});	// JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
					
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						
				return rs.getString("task");
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return "";	
	}
	
	public int update(Manager manager) throws SQLException {
		String sql = "UPDATE MANAGER "
					+ "SET password=?, name=?, phone=?, task=? "
					+ "WHERE id=?";
				
		jdbcUtil.setSqlAndParameters(sql, new Object[] {manager.getPassword(), manager.getName(), manager.getPhone(), manager.getTask(), manager.getId()});
		try {				
			int result = jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}

	public Manager findManager(String id) throws SQLException {
        String sql = "SELECT password, name, phone, mno, task "
        			+ "FROM MANAGER "
        			+ "WHERE id=?";         
        
        System.out.println(sql);
        System.out.println(id);
        
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		Manager manager= null;
		int mno = 0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {
				manager = new Manager(		// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占싻삼옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						id,
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("phone"),
						rs.getString("task"));	
				mno = rs.getInt("mno");
				/*
				 * System.out.println(cno); System.out.println(user.getPassword());
				 * System.out.println(user.getName()); System.out.println(user.getPhone());
				 * System.out.println(user.getAge()); System.out.println(user.getSex());
				 */
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
			return manager;
		}
	}
	
	public boolean existingManager(String id) throws SQLException {
		String sql = "SELECT count(*) FROM MANAGER WHERE id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return false;
	}
	
	public boolean existingNumber(String phone) throws SQLException {
		String sql = "SELECT count(*) FROM MANAGER WHERE phone=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {phone});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}

}