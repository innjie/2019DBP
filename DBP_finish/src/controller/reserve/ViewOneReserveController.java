package controller.reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Reserve;
import model.service.ReserveManager;

public class ViewOneReserveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String rsvNo = (String) request.getParameter("rsvNo");
		Reserve rsv = new Reserve();

		try {
			ReserveManager mgr = ReserveManager.getInstance();
			Reserve newRsv = mgr.getOneReserve(Integer.parseInt(rsvNo));
			request.setAttribute("rsvNo", newRsv.getReserveNo());
			request.setAttribute("name", newRsv.getLender());
			
			request.setAttribute("model", newRsv.getModel().getModelName());
		
			request.setAttribute("brand",  newRsv.getModel().getBrand());
			request.setAttribute("passenger",   newRsv.getModel().getPassenger());
			request.setAttribute("date",  newRsv.getReserveDate());
			request.setAttribute("rentDate",  newRsv.getRentDate());
			request.setAttribute("returnDate",  newRsv.getReturnDate());
			request.setAttribute("cancel", newRsv.getCancel());
			
			
			

			return "/reserve/reserveView.jsp";
		} catch (Exception e) {
			request.setAttribute("selectFailed", true);
			request.setAttribute("exception",  e);
			request.setAttribute("rsv", rsv);
			return "/user/myPage";
		}
		
	}

}