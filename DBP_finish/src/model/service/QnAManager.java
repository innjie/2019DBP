package model.service;

import java.sql.SQLException;
import java.util.List;

import model.QnA;
import model.dao.QnADAO;

public class QnAManager {
	private static QnAManager qnaMan = new QnAManager();
	private QnADAO qnaDAO;
	
	public QnAManager() {
		qnaDAO = new QnADAO();
	}
	
	public static QnAManager getInstance() {
		return qnaMan;
	}
	
	public int add(QnA qna) throws SQLException {
		return qnaDAO.insertQna(qna);
	}

	
	public int updateAnswer(QnA qna, int mnum) throws SQLException {
		return qnaDAO.updateAnswer(qna, mnum);
	}
	
	public int updateRate(QnA qna) throws SQLException {
		return qnaDAO.updateRate(qna);
	}
	
	public QnA findQnAByQNum(int qNo) throws SQLException, QnANotFoundException {
		QnA qnas = qnaDAO.findQnAByQNum(qNo); //
			
		if (qnas == null) {
			throw new QnANotFoundException(qNo + "는 존재하지 않는 상담번호입니다.");
		}		
		return qnas;
	}
	public QnA findQnAByQNumM(int qNo) throws SQLException, QnANotFoundException {
		QnA qnas = qnaDAO.findQnAByQNumM(qNo); //
			
		if (qnas == null) {
			throw new QnANotFoundException(qNo + "는 존재하지 않는 상담번호입니다.");
		}		
		return qnas;
	}
	
	public List<QnA> findQnAByQuestion(String question) throws SQLException, QnANotFoundException {
		List<QnA> qnas = qnaDAO.findQnAByQuestion(question); //
		
		if (qnas == null) {
			throw new QnANotFoundException(question + "이(가) 들어간 내용은 없습니다.");
		}		
		return qnas;
	}
	public List<QnA> findQnAByQuestion(String question, int currentPage, int countPerPage) throws SQLException, QnANotFoundException {
		List<QnA> qnas = qnaDAO.findQnAByQuestion(question, currentPage, countPerPage); //
		
		if (qnas == null) {
			throw new QnANotFoundException(question + "이(가) 들어간 내용은 없습니다.");
		}		
		return qnas;
	}
	
	public List<QnA> findQnAList(int currentPage, int countPerPage) throws SQLException {
		return qnaDAO.findQnAList(currentPage, countPerPage);
	}
	public List<QnA> findQnAList() throws SQLException {
		return qnaDAO.findQnAList();
	}
	public List<QnA> findQnAMList() throws SQLException {
		return qnaDAO.findQnAMList();
	}
	public List<QnA> findQnAMList(int currentPage, int countPerPage) throws SQLException {
		return qnaDAO.findQnAMList(currentPage, countPerPage);
	}
	
	public QnADAO getQnADAO() {
		return this.qnaDAO;
	}
	
	
	
	
	
	
	
	
	

}
