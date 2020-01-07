package model;
import java.util.Date;
import model.*;

public class Reserve {
	private int reserveNo = 0;
	private Date reserveDate = null;
	private Date rentDate = null;
	private Date returnDate = null;
	private String lender = null;
	private String cancel = null;
	private String state = null;
	private User user = new User();
	private Car car = new Car();
	private Model model = new Model();
	private Manager manager = new Manager();
	
	public Reserve() {
		
	}
	public Reserve(int rsvNo) {
		this.reserveNo = rsvNo; 
	}
	public Reserve(int cNo, Car car) {
		this.user.setCustomerNo(cNo);
		this.car = car;
	}
	public Reserve(int cNo, int reserveNo, Date reserveDate, Car carDTO) {
		this.user.setCustomerNo(cNo);
		this.setReserveNo(reserveNo);
		this.setReserveDate(reserveDate);
		this.setCar(carDTO);
	}
	public Reserve(int reserveNo, String lender, Date rentDate, Date returnDate, Car carDTO, int cNo) {
		this.user.setCustomerNo(cNo);
		this.returnDate = returnDate;
		this.rentDate = rentDate;
		this.car = carDTO;
		this.reserveNo = reserveNo;
		this.lender = lender;
	}
	
	public Reserve(int reserveNo, String lender, Date reserveDate, Date rentDate, Date returnDate, Car carDTO, int cNo, String cancel, String state) {
		this.user.setCustomerNo(cNo);
		this.reserveDate = reserveDate;
		this.returnDate = returnDate;
		this.rentDate = rentDate;
		this.car = carDTO;
		this.reserveNo = reserveNo;
		this.lender = lender;
		this.cancel = cancel;
		this.state = state;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
	
}