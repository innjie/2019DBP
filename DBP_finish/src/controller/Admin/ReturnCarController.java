package controller.Admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Rent;
import model.Reserve;
import model.service.RentManager;

public class ReturnCarController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rentNo = Integer.parseInt(request.getParameter("rentNo"));
		int cno = Integer.parseInt(request.getParameter("cNo"));
		int rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
		
		Rent rent = new Rent();
		Reserve rsv = new Reserve();
		
		rsv.setReserveNo(rsvNo);
		rent.setRentNo(rentNo);
		rent.setCno(cno);
		
		rent.setReserve(rsv);
		
		
		try {
			RentManager manager = RentManager.getInstance();
			rentNo = manager.returnRent(rent);
			if(rentNo == 0) {
				request.setAttribute("errorMsg", "이미 처리된 항목입니다.");
				return "/admin/rentList";
			}
			request.setAttribute("rentNo",  rentNo);
			return "/admin/return/page";
		} catch(Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception",  e);
			request.setAttribute("rentNo",  rentNo);
			return "/admin/modelList";
		}
	}

}
