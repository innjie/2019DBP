package controller.Car;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Model;
import model.service.CarManager;

public class MainCarController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarManager manager = CarManager.getInstance();
		List<Model> modelList = manager.getModelList();
		HttpSession session = request.getSession();
		
		System.out.println("1111");
		request.setAttribute("modelList", modelList);
		return "/car/main2.jsp";
	}
}
