package model.dao;
import java.sql.*;

public class PStmtTest {
	public static void main(String args[]) 		// 일반적으로 method로 구현하고 호출됨
	{
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pStmt = null;			// PreparedStatment 참조 변수 생성
		ResultSet rs = null;
		String url = // "jdbc:oracle:thin:@localhost:1521:xe";
					"jdbc:oracle:thin:@202.20.119.117:1521:orcl";			
		String user = "dbp0207";
		String passwd = "dw0207";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// JDBC 드라이버 로딩 및 등록
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
		
		String pattern = "%AR%";				// 일반적으로 method의 parameter로 전달됨
/*
	
*/
		try {
		/*	String query2 = "SELECT u_id, QnAnum, QContent, QnAdate "
					+ "FROM QNA q, CUSTOMER c WHERE c.c_num = q.c_num "*/
			
			System.out.println("p");
			
			

			String query3 = "SELECT name FROM CUSTOMER";
			System.out.println(query3);

			pStmt = conn.prepareStatement(query3);	// Connection에서 PreparedStatement 객체 생성
			//pStmt.setString(1, pattern);				// PreparedStatement에 매개변수 값 설정
			rs = pStmt.executeQuery();				// 질의 실행 (주의: parameter 없음!!)			
			System.out.println();
			while (rs.next()) {						// 커서를 통해 한 행씩 fetch
				String ename = rs.getString("u_id");
				//int job = rs.getInt("QnAnum");
				//String dname = rs.getString("QContent");
				//Date dname2 = rs.getDate("QnAdate");
				//System.out.println(ename + " " + job + " " + dname + " " + dname2);
				System.out.println(ename);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// 자원 반납
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (stmt != null) 
				try { 
					stmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}	
	}	
}