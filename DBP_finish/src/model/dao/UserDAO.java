package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.User;
import model.Car;
import model.License;
import model.Model;
import model.Overdue;
import model.Penalty;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	
	}

	public int create(User user) throws SQLException {

		String sql = "INSERT INTO CUSTOMER (cid, name, phone, age, sex, password, cno) "
					+ "VALUES (?, ?, ?, ?, ?, ?, CTMQUENCE.nextval)";		
		Object[] param = new Object[] {user.getId(), user.getName(), 
				user.getPhone(), user.getAge(), user.getSex(), user.getPassword()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;			
	}
	
	
	
	//id�쓽 cNO李얠쓬
	public int findCNo(String id) {
		String sql = "select cNo from customer where cId = ?";
		Object[] param = new Object[] {id};				
		jdbcUtil.setSqlAndParameters(sql, param);	
					
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {						
				return rs.getInt("cNo");
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;	
	}
	

	public int update(User user) throws SQLException {
		String sql = "UPDATE CUSTOMER "
					+ "SET password=?, name=?, age=?, phone=?, sex=?, licenseno=? "
					+ "WHERE cid=?";
		//Object[] param = ;				
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user.getPassword(), user.getName(), 
				user.getAge(), user.getPhone(), user.getSex(), user.getLicense().getLicenseNo(), user.getId()});	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			System.out.println("555555" +user.getLicense().getLicenseNo());
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}

	/**
	 * �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄瑜� �궘�젣.
	 */
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM CUSTOMER WHERE cid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}

	/**
	 * 二쇱뼱吏� �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄 �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 User �룄硫붿씤 �겢�옒�뒪�뿉 
	 * ���옣�븯�뿬 諛섑솚.
	 */
	public User findUser(String userId) throws SQLException {
        String sql = "SELECT password, name, phone, age, sex, cno, licenseno "
        			+ "FROM CUSTOMER "
        			+ "WHERE cid=? ";         
        
        System.out.println(sql);
        System.out.println(userId);
        
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		User user = null;
		int cno = 0;
		String licenseno = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				//License license = new License(rs.getString("licenseno"));
				user = new User(		// User ��ü�� �����Ͽ� �л� ������ ����
						userId,
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("phone"),
						rs.getString("age"),
						rs.getString("sex"));	
				cno = rs.getInt("cno");
				licenseno = rs.getString("licenseno");
				System.out.println(cno);
				System.out.println(user.getPassword());
				System.out.println(user.getName());
				System.out.println(user.getPhone());
				System.out.println(user.getAge());
				System.out.println(user.getSex());
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		sql = "SELECT overdue, ocount, OVERDUE.plevel, restriction "
				+ "FROM OVERDUE, PENALTY "
				+ "where cno=? AND PENALTY.PLEVEL = OVERDUE.PLEVEL";
		
		System.out.println(sql);
		  Object[] p0 = {cno};
		  
		  System.out.println(cno);
 
		 jdbcUtil.setSqlAndParameters(sql, p0);
		 
		//jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Overdue o = new Overdue();
			Penalty p = new Penalty();
			if(rs.next()) {
				o.setOverdue(rs.getString("overdue"));
				o.setCount(rs.getInt("ocount"));
				o.setLevel(rs.getString("plevel"));
				p.setRestriction(rs.getString("restriction"));
				
				user.setOverdue(o);
				user.setPenalty(p);
				
				System.out.println(user.getOverdue().getOverdue());
				System.out.println(user.getOverdue().getLevel());
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		
		  sql = "SELECT c.licenseno, vdate, type " 
				  +"FROM CUSTOMER c, LICENSE l "
				 + "where cid = ? AND c.licenseno = l.licenseno";
		  
		  
		  jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		  
		  try { 
			  ResultSet rs = jdbcUtil.executeQuery();
		  License li = new License();
		  if(rs.next()) {
		  li.setLicenseNo(rs.getString("licenseno"));
		  li.setVdate(rs.getDate("vdate")); 
		  li.setType(rs.getString("type"));
		  
		  user.setLicense(li);

		  
		  System.out.println(user.getLicense().getLicenseNo());
		  System.out.println(user.getLicense().getVdate());
		  System.out.println(user.getLicense().getType());}
		  }catch (Exception ex) { 
			  ex.printStackTrace(); 
		}
		 finally {
			jdbcUtil.close();		// resource ��ȯ
			return user;
		}
	}

	public List<User> findUserList() throws SQLException {
        String sql = "SELECT cid, password, name, phone, age, sex " 
        		   + "FROM CUSTOMER "
        		   + "ORDER BY cid";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뿉 query臾� �꽕�젙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
			List<User> userList = new ArrayList<User>();	// User�뱾�쓽 由ъ뒪�듃 �깮�꽦
			while (rs.next()) {
				User user = new User(			// User 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
					rs.getString("cid"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("phone"),
					rs.getString("age"),
					rs.getString("sex"));	
				userList.add(user);				// List�뿉 User 媛앹껜 ���옣
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	public List<User> findBadUserList() throws SQLException { //plevel이 balcklist인 사람만 
        String sql = "SELECT cid, password, name, phone, age, sex, o.plevel "
        			+ "FROM CUSTOMER c, OVERDUE o "
        			+ "WHERE plevel = 10 AND c.cno = o.cno "
        			+ "ORDER BY c.cno";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈뼄占쎈뻬			
			List<User> userList = new ArrayList<User>();	// User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				User user = new User(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
					rs.getString("cid"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("phone"),
					rs.getString("age"),
					rs.getString("sex"));	
				userList.add(user);				// List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	public List<User> findUserList(int currentPage, int countPerPage) throws SQLException {
		 String sql = "SELECT cid, password, name, phone, age, sex " 
      		   + "FROM CUSTOMER "
      		   + "ORDER BY cid";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�뿉 query臾� �꽕�젙
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 媛��뒫
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query �떎�뻾			
			int start = ((currentPage-1) * countPerPage) + 1;	// 異쒕젰�쓣 �떆�옉�븷 �뻾 踰덊샇 怨꾩궛
			if ((start >= 0) && rs.absolute(start)) {			// 而ㅼ꽌瑜� �떆�옉 �뻾�쑝濡� �씠�룞
				List<User> userList = new ArrayList<User>();	// User�뱾�쓽 由ъ뒪�듃 �깮�꽦
				do {
					User user = new User(		// User 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
							rs.getString("cid"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getString("phone"),
							rs.getString("age"),
							rs.getString("sex"));
					userList.add(user);							// 由ъ뒪�듃�뿉 User 媛앹껜 ���옣
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}

	/**
	 * 二쇱뼱吏� �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄媛� 議댁옱�븯�뒗吏� 寃��궗 
	 */
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM CUSTOMER WHERE cid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return false;
	}
	
	public boolean existingNumber(String phone) throws SQLException {
		String sql = "SELECT count(*) FROM CUSTOMER WHERE phone=?";      
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
	
	//면허----------------------------------------------
	//생성
	public int createLicense(License license) throws SQLException {
		String sql = "INSERT INTO LICENSE (vdate, type, licenseno) "
					+ "VALUES (?, ?, ?)";		
		Object[] param = new Object[] {license.getVdate(), license.getType(), license.getLicenseNo()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		System.out.println("#########3#" +license.getVdate());				
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;			
	}
	
	public boolean checkLisence(License license) throws SQLException {
		String sql = "select * from license where licenseNo=?";
		Object[] param = new Object[] {license.getLicenseNo()};		
		jdbcUtil.setSqlAndParameters(sql, param);	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				return true;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return false;			
	}
	
	public Date checkLicenseVDate(License license) throws SQLException {
		String sql = "select vDate from license where licenseNo=?";	
		Object[] param = new Object[] {license.getLicenseNo()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				return rs.getDate("vDate");
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return new Date();			
	}
	
	public String checkLicenseType(License license) throws SQLException {
		String sql = "select type from license where licenseNo=?";	
		Object[] param = new Object[] {license.getLicenseNo()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				return rs.getString("type");
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return "";			
	}
	
	//수정	
	public int updateLicense(License license, String licenseno) throws SQLException {
		String sql = "UPDATE LICENSE "
					+ "SET vdate=?, type=?, licenseno=? "
					+ "WHERE licenseno=?";
					
		jdbcUtil.setSqlAndParameters(sql, new Object[] {license.getVdate(), license.getType(), license.getLicenseNo(), licenseno});	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			//System.out.println("555555" +user.getLicense().getLicenseNo());
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
}