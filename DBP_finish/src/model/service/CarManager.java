package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.dao.*;

public class CarManager {
	
	private static CarManager carMan = new CarManager();
	private CarDAO carDAO;
	private ModelDAO modelDAO;
	
	public CarManager() {
		carDAO = new CarDAO();
		modelDAO = new ModelDAO();
	}
	
	public static CarManager getInstance() {
		return carMan;
	}
	
	public List<Model> findModel(String price, String color, String passenger) throws SQLException{
		return modelDAO.findModel(price, color, passenger);
	}
	public List<Model> findModel(String price, String color, String passenger, int currentPage, int countPerPage) throws SQLException{
		return modelDAO.findModel(price, color, passenger, currentPage, countPerPage);
	}
	
	public List<Model> getMonthlyModel() throws SQLException{
		return modelDAO.getMonthlyModel();
	}
	
	public int insertModel(Model model) throws SQLException{
		return modelDAO.insertModel(model);
	}
	
	public int updateModel(Model model) throws SQLException{
		return modelDAO.updateModel(model);
	}
	public int updateModelDeleteCar(int modelNo) throws SQLException{
		return modelDAO.updateModelDeleteCar(modelNo);
	}
	
	public int deleteModel(int modelNo) throws SQLException{
		return modelDAO.deleteModel(modelNo);
	}
	
	public List<Model> getModelList(int currentPage, int countPerPage) throws SQLException{
		return modelDAO.getModelList(currentPage, countPerPage);
	}
	public List<Model> getModelList() throws SQLException{
		return modelDAO.getModelList();
	}
	
	public Model getModelByModelNo(int modelNo) throws SQLException{
		return modelDAO.getModelByModelNo(modelNo);
	}

	public List<Model> getModelByName(String modelName) throws SQLException, ModelNotFoundException{
		List<Model> model = modelDAO.getModelByName(modelName);
		
		if (model == null) {
			throw new ModelNotFoundException(modelName + "��(��) �����ϴ�.");
		}		
		return model;		
	}
	
	public List<Model> getModelByName(String modelName, int currentPage, int countPerPage) throws SQLException, ModelNotFoundException{
		List<Model> model = modelDAO.getModelByName(modelName, currentPage, countPerPage);
		
		if (model == null) {
			throw new ModelNotFoundException(modelName + "��(��) �����ϴ�.");
		}		
		return model;		
	}
	
	public int insertCar(Car car) throws SQLException{
		return carDAO.insertCar(car);
	}
	
	public int rentCar(Car car) throws SQLException{
		return carDAO.rentCar(car);
	}
	
	public int returnCar(Car car) throws SQLException{
		return carDAO.returnCar(car);
	}
	
	public int firstMonth() throws SQLException{
		return carDAO.firstMonth();
	}
	
	public int deleteCar(int carNo) throws SQLException{
		return carDAO.deleteCar(carNo);
	}
	
	public List<Car> getCarList() throws SQLException{
		return carDAO.getCarList();
	}
	public List<Car> getCarListByModelNo(int modelNo) throws SQLException{
		return carDAO.getCarListByModelNo(modelNo);
	}
	public List<Car> getCarListByModelNo(int modelNo, int currentPage, int countPerPage) throws SQLException{
		return carDAO.getCarListByModelNo(modelNo, currentPage, countPerPage);
	}

	public List<Car> getCarByModel(int modelNo, int price, String color) throws SQLException{		
		return carDAO.getCarByModel(modelNo, price, color);
	}
	public List<Car> getCarByModel(int modelNo, int price, String color, int currentPage, int countPerPage) throws SQLException{		
		return carDAO.getCarByModel(modelNo, price, color, currentPage, countPerPage);
	}
	
	public List<Car> getSameCarByModel(int modelNo) throws SQLException{		
		return carDAO.getSameCarByModel(modelNo);
	}
	
	public Car getCarByNo(int carNo) throws SQLException{
		return carDAO.getCarByNo(carNo);	
	}
	
	public Car getCar(int carNo) throws SQLException{
		return carDAO.getCar(carNo);	
	}
	
	public Car reserveCar(int modelNo, int price, String color) throws SQLException{
		return carDAO.reserveCar(modelNo, price, color);	
	}
	
	public List<String> setModelColor(int modelNo) throws SQLException{
		return modelDAO.setModelColor(modelNo);
	}
	
	public int setRentCount(int modelNo) throws SQLException{
		return modelDAO.setRentCount(modelNo);
	}
	
	
	public String passenger(String passenger) {
		return modelDAO.passenger(passenger);
	}
	public String color(String color) {
		return modelDAO.color(color);
	}
	public String price(String price) {
		return modelDAO.price(price);
	}
	public List<Car> getAllList() throws SQLException {
		return carDAO.getAllList();
	}
	public List<Car> getAllList(int currentPage, int countPerPage) throws SQLException {
		return carDAO.getAllList(currentPage, countPerPage);
	}
	
}
