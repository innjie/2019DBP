package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Manager;
import model.QnA;
import model.User;

public class QnADAO {
private JDBCUtil jdbcUtil = null;
	
	public QnADAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public int insertQna(QnA qna) throws SQLException {
		
		String sql = "select QNAQUENCE.nextval from dual"; //시퀀스로 qNo받아오기

		jdbcUtil.setSqlAndParameters(sql, null);
		ResultSet rs = jdbcUtil.executeQuery();
		rs.next();
		int num = rs.getInt(1);

		sql = "INSERT INTO QNA (qnaNo, question, cNo, secret) "
				+ "VALUES (?, ?, ?, ?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {num, qna.getQContent(), qna.getUser().getCustomerNo(), qna.getSecret()});
		
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
	
	
	
	public List<QnA> findQnAList() {
		String sql = "SELECT q.cNo, cId, qnaNo, question, qDate, finish, secret "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<QnA> list = new ArrayList<QnA>();
			User ctm = new User();
			
			while (rs.next()) {				
					ctm.setCustomerNo(rs.getInt("cNo"));
					ctm.setId(rs.getString("cId"));							
					
					QnA dto = new QnA(
							ctm.getId(),
							rs.getInt("qnaNo"),
							rs.getString("question"),
							rs.getDate("qDate"),
							rs.getString("finish"));
					dto.setSecret(rs.getString("secret"));
					
					list.add(dto);
					
				} 
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<QnA> findQnAList(int currentPage, int countPerPage) {
		String sql = "SELECT q.cNo, cId, qnaNo, question, qDate, finish, secret "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<QnA> list = new ArrayList<QnA>();
			User ctm = new User();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {	
				do {				
					ctm.setCustomerNo(rs.getInt("cNo"));
					ctm.setId(rs.getString("cId"));							
					
					QnA dto = new QnA(
							ctm.getId(),
							rs.getInt("qnaNo"),
							rs.getString("question"),
							rs.getDate("qDate"),
							rs.getString("finish"));
					dto.setSecret(rs.getString("secret"));
					
					list.add(dto);
					
				} while ((rs.next()) && (--countPerPage > 0));	
			
				return list;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<QnA> findQnAMList(int currentPage, int countPerPage) {
		String sql = "SELECT q.cNo, cId, qnaNo, question, qDate, finish, name "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, null, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<QnA> list = new ArrayList<QnA>();
			User ctm = new User();
			Manager m = new Manager();
			
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
				do {	
					m.setName(rs.getString("name"));
					ctm.setCustomerNo(rs.getInt("cNo"));
					ctm.setId(rs.getString("cId"));							
					
					QnA dto = new QnA(
							ctm.getId(),
							rs.getInt("qnaNo"),
							rs.getString("question"),
							rs.getDate("qDate"),
							rs.getString("finish"), m);
					
					list.add(dto);
					
				}while (rs.next() && (--countPerPage > 0));
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<QnA> findQnAMList() {
		String sql = "SELECT q.cNo, cId, qnaNo, question, qDate, finish, mNo "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<QnA> list = new ArrayList<QnA>();
			User ctm = new User();
			Manager m = new Manager();
			
			while(rs.next()) {	
				m.setManagerNo(rs.getInt("mNo"));
				ctm.setCustomerNo(rs.getInt("cNo"));
				ctm.setId(rs.getString("cId"));							
				
				QnA dto = new QnA(
						ctm.getId(),
						rs.getInt("qnaNo"),
						rs.getString("question"),
						rs.getDate("qDate"),
						rs.getString("finish"), m);
				
				list.add(dto);
				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public QnA findQnAByQNumM(int qnaNo) {
		QnA dto = null;
		String sql = "SELECT cid, qnaNo, question, finish, rating, qDate, answer, name "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo and qnaNo = ? "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { qnaNo });
		
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			User ctm = new User();
			Manager m = new Manager();
			
			if(rs.next()) {
				m.setName(rs.getString("name"));
				ctm.setId(rs.getString("cid"));				
				
				dto = new QnA(
						ctm.getId(),
						rs.getInt("qnaNo"),
						rs.getString("question"),
						rs.getString("finish"),
						rs.getInt("rating"),
						rs.getDate("qDate"),
						rs.getString("answer"), m);
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public QnA findQnAByQNum(int qnaNo) {
		QnA dto = null;
		String sql = "SELECT cid, qnaNo, question, finish, rating, qDate, answer "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo and qnaNo = ? "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { qnaNo });
		
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			User ctm = new User();
			
			if(rs.next()) {
				ctm.setId(rs.getString("cid"));				
				
				dto = new QnA(
						ctm.getId(),
						rs.getInt("qnaNo"),
						rs.getString("question"),
						rs.getString("finish"),
						rs.getInt("rating"),
						rs.getDate("qDate"),
						rs.getString("answer"));
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<QnA> findQnAByQuestion(String question) {
		String sql = "SELECT cid, qnaNo, question, finish, rating, qDate, answer "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo and "
				+ "question like '%' || ? || '%' "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { question });		
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<QnA> list = new ArrayList<QnA>();
			User ctm = new User();
			
			while(rs.next()) {				
				ctm.setId(rs.getString("cId"));							
				
				QnA dto = new QnA(
						ctm.getId(),
						rs.getInt("qnaNo"),
						rs.getString("question"),
						rs.getDate("qDate"),
						rs.getString("finish"));
				
				list.add(dto);
				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<QnA> findQnAByQuestion(String question, int currentPage, int countPerPage) {
		String sql = "SELECT cid, qnaNo, question, finish, rating, qDate, answer "
				+ "FROM QNA q, CUSTOMER c WHERE c.cNo = q.cNo and "
				+ "question like '%' || ? || '%' "
				+ "ORDER BY qDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { question }, ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll
				ResultSet.CONCUR_READ_ONLY);		
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<QnA> list = new ArrayList<QnA>();
			User ctm = new User();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			if ((start >= 0) && rs.absolute(start)) {
			
			do {				
				ctm.setId(rs.getString("cId"));							
				
				QnA dto = new QnA(
						ctm.getId(),
						rs.getInt("qnaNo"),
						rs.getString("question"),
						rs.getDate("qDate"),
						rs.getString("finish"));
				
				list.add(dto);
				
			}while(rs.next() && (--countPerPage > 0));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public int updateAnswer(QnA q, int mNo) {
		String sql = "UPDATE QNA "
				+ "SET answer=?, mNo=?, finish=? "
				+ "WHERE qnaNo=? ";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {q.getAContent(), mNo, "Y", q.getQNANo()});	
		
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
	public int updateRate(QnA q) {
		if(q.getAContent() == "")
			return -1;
		
		String sql = "UPDATE QNA "
				+ "SET rating=? "
				+ "WHERE qnaNo=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {q.getRating(), q.getQNANo()});	

		
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
}
