package model;

import java.util.Date;

import model.Reserve;

public class Rent {
	private	int rentNo;
	private Reserve reserve = null;		
	private String review;
	private Date rtnDate;
	private Date rentDate;
	private String returned;
	private int cno;
	
	
	public Rent(int rentNo, Reserve reserve, String review, String returned) {
		super();
		this.rentNo = rentNo;
		this.reserve = reserve;
		this.review = review;
		this.returned = returned;
	}

	public Rent(int rentNo, Reserve reserve, String review, Date rentDate, Date rtnDate, String returned) {
		super();
		this.rentNo = rentNo;
		this.reserve = reserve;
		this.review = review;
		this.rtnDate = rtnDate;
		this.rentDate = rentDate;
		this.returned = returned;
	}
	
	public Rent() {
		super();
	}

	public int getRentNo() 
	{
		return rentNo;    
	}
   
	public void setRentNo(int rentNo) 
	{
		this.rentNo = rentNo;    
	}
   
	public Reserve getReserve() 
	{
		return reserve;    
	}


	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getRtnDate() {
		return rtnDate;
	}

	public void setRtnDate(Date rtnDate) {
		this.rtnDate = rtnDate;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getCno() {
		return cno;
	}
	
}

