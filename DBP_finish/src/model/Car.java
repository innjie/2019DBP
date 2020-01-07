package model;


public class Car {
	private int carNo = 0;
	private int price = 0;
	private String occupied = "";
	private String color = "";
	private int rentCount = 0;
	private Model model = new Model();
	private int monthRent = 0;
	
	
	
	public Car() {
		super();
	}

	public Car(int carNo) {
		this.carNo = carNo;
	}
	
	public Car(int carNo, int modelNo) {
		this.carNo = carNo;
		this.model.setModelNo(modelNo);
	}
	
	public Car(int carNo, int price, String occupied, String color, String modelName) {
		this.carNo = carNo;
		this.price = price;
		this.occupied = occupied;
		this.color = color;
		this.model.setModelName(modelName);
	}
	
	public Car(int carNo, int price, String color, Model model) {
		this.carNo = carNo;
		this.price = price;
		this.color = color;
		this.model = model;
	}
	
	public Car(int price, String color, Model model) {
		this.price = price;
		this.color = color;
		this.model = model;
	}
	
	public Car(int carNo, int price, String occupied, String color, Model model) {
		this.carNo = carNo;
		this.price = price;
		this.occupied = occupied;
		this.color = color;
		this.model = model;
	}
	
	public Car(int carNo, int price, String occupied, String color, int rentcount, Model model, int monthRent) {
		this.carNo = carNo;
		this.price = price;
		this.occupied = occupied;
		this.color = color;
		this.rentCount = rentcount;
		this.model = model;
		this.monthRent = monthRent;
	}
	public int getRentCount() {
		return rentCount;
	}
	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}
	public int getMonthRent() {
		return monthRent;
	}
	public void setMonthRent(int monthRent) {
		this.monthRent = monthRent;
	}
	public int getCarNo() {
		return carNo;
	}
	public void setCarNo(int carNo) {
		this.carNo = carNo;    
	}
	public int getPrice() {
		return price;    
	}
	public void setPrice(int price) {
		this.price = price;    
	}
	public String getOccupied() {
		return occupied;    
	}
	public void setOccupied(String occupied) {
		this.occupied = occupied;    
	}
	public String getColor() {
		return color;    
	}
	public void setColor(String color) {
		this.color = color;    
	}
	public Model getModel() {
		return model;    
	}
	public void setModel(Model model) {
		this.model = model;    
	}
}
