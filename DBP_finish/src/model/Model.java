package model;

import java.util.*;

import model.Manager;

public class Model {
	private int modelNo = 0;
	private String modelName = "";
	private int modelCount = 0;
	private String brand = "";
	private int passenger = 0;
	private Date year = new Date();
	private Manager manager = new Manager();
	private int rentCount = 0;
	private int rowNum = 0;
	
	
	public Model() {
		super();
	}

	public Model(int modelNo) {
		this.modelNo = modelNo;
	}
	
	public Model(int modelNo, String modelName) {
		this.modelNo = modelNo;
		this.modelName = modelName;
	}
	
	public Model(int modelNo, String modelName, int modelCount) {
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.modelCount = modelCount;
	}
	
	public Model(String modelName) {
		this.modelName = modelName;
	}
	
	public Model(String modelName, String brand, int passenger) {
		this.modelName = modelName;
		this.brand = brand;
		this.passenger = passenger;
	}
	
	public Model(String modelName, String brand, int passenger, Date year, int mNo) {
		super();
		this.modelName = modelName;
		this.brand = brand;
		this.passenger = passenger;
		this.year = year;
		this.manager.setManagerNo(mNo);
	}
	
	public Model(int modelNo, String modelName, int modelCount, String brand, int passenger, int rentCount) {
		super();
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.modelCount = modelCount;
		this.brand = brand;
		this.passenger = passenger;
		this.rentCount = rentCount;
	}
	
	public Model(int modelNo, String modelName, int modelCount, String brand, int passenger) {
		super();
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.modelCount = modelCount;
		this.brand = brand;
		this.passenger = passenger;
	}

	public Model(int modelNo, String modelName, int passenger, Date year, String brand) {
		super();
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.passenger = passenger;
		this.year = year;
		this.brand = brand;

	}
	
	public Model(int modelNo, String modelName, String brand, int passenger, int rentCount, int rowNum) {
		super();
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.brand = brand;
		this.passenger = passenger;
		this.rentCount = rentCount;
		this.rowNum = rowNum;
	}
	
	public Model(int modelNo, String modelName, int modelCount, String brand, int passenger, Date year, int managerNo) {
		super();
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.modelCount = modelCount;
		this.brand = brand;
		this.passenger = passenger;
		this.year = year;
		this.manager.setManagerNo(managerNo);
	}
	
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getRentCount() {
		return rentCount;
	}
	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}

	public int getModelNo() {
		return modelNo;
	}
	public void setModelNo(int modelNo) {
		this.modelNo = modelNo;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getModelCount() {
		return modelCount;
	}
	public void setModelCount(int modelCount) {
		this.modelCount = modelCount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPassenger() {
		return passenger;
	}
	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
	
}
