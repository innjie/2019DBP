package model.dao;
import java.sql.*;

public class PStmtTest {
	public static void main(String args[]) 		// �Ϲ������� method�� �����ϰ� ȣ���
	{
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pStmt = null;			// PreparedStatment ���� ���� ����
		ResultSet rs = null;
		String url = // "jdbc:oracle:thin:@localhost:1521:xe";
					"jdbc:oracle:thin:@202.20.119.117:1521:orcl";			
		String user = "dbp0207";
		String passwd = "dw0207";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// JDBC ����̹� �ε� �� ���
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
		
		String pattern = "%AR%";				// �Ϲ������� method�� parameter�� ���޵�
/*
	
*/
		try {
		/*	String query2 = "SELECT u_id, QnAnum, QContent, QnAdate "
					+ "FROM QNA q, CUSTOMER c WHERE c.c_num = q.c_num "*/
			
			System.out.println("p");
			
			

			String query3 = "SELECT name FROM CUSTOMER";
			System.out.println(query3);

			pStmt = conn.prepareStatement(query3);	// Connection���� PreparedStatement ��ü ����
			//pStmt.setString(1, pattern);				// PreparedStatement�� �Ű����� �� ����
			rs = pStmt.executeQuery();				// ���� ���� (����: parameter ����!!)			
			System.out.println();
			while (rs.next()) {						// Ŀ���� ���� �� �྿ fetch
				String ename = rs.getString("u_id");
				//int job = rs.getInt("QnAnum");
				//String dname = rs.getString("QContent");
				//Date dname2 = rs.getDate("QnAdate");
				//System.out.println(ename + " " + job + " " + dname + " " + dname2);
				System.out.println(ename);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// �ڿ� �ݳ�
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