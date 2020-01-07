package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Admin.*;
import controller.Car.*;
import controller.Forward.*;
import controller.QnA.*;
import controller.rent.*;
import controller.user.*;
import controller.reserve.*;


public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        //mappings.put("/", new ForwardController("index.jsp"));
    	mappings.put("/", new IndexController());        
        
        //qna 관련 request URI 추가
        mappings.put("/qna/list", new ListQnAController());
        mappings.put("/qna/view", new ViewQnAController());
        mappings.put("/qna/find", new FindQnAController());
        mappings.put("/qna/addForm/form", new addController()); //qna리스트에서 추가버튼 클릭시 추가페이지로 이동
        mappings.put("/qna/addForm", new AddQnAController()); //추가할 문의 내용 입력 후 등록할때 추가버튼클릭
        mappings.put("/qna/updateForm", new UpdateQnAController());
        
        //car 관련 request URI 추가
        mappings.put("/car/main", new MainCarController());
        mappings.put("/car/modelView", new ViewModelController());
        mappings.put("/car/carList", new ListCarController());
        mappings.put("/car/carView", new ViewCarController());
        mappings.put("/car/findModelByName", new FindModelByNameController());
        mappings.put("/car/findModel", new FindModelController());
        mappings.put("/car/MonthlyCar", new MonthlyCarController());  
        mappings.put("/car/findAll", new FindAllCarController());
        mappings.put("/car/findFromList", new CarFromListController());
        
        //예약 관련 request URI 추가
        mappings.put("/reserve/main", new ReserveCarController());
        mappings.put("/reserve/view", new InsertReserveController());
        mappings.put("/reserve/success", new ForwardController("/reserve/reserveSuccess.jsp"));
        mappings.put("/reserve/list", new ViewReserveController());
        mappings.put("/reserve/cancel", new CancleReserveController());
        mappings.put("/reserve/cancel/page", new ForwardController("/reserve/reserveCancel.jsp"));
        mappings.put("/reserve/per", new ViewOneReserveController());
        
        //kakao API
        mappings.put("/kakaoPay", new KakaoController());
        
      //대여 관련 request URI 추가
        mappings.put("/rent/list", new ListRentController());
        mappings.put("/rent/view", new ViewRentController());
        mappings.put("/rent/review", new RentReviewController());
        mappings.put("/rent/inReview", new InsertReviewController());
        
        //사용자관련 request URI 추가
        mappings.put("/user/login/form", new loginUController());
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/user/register/form", new registerController());
        mappings.put("/user/registerLicense/form", new ForwardController("/user/registerLicense.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/registerLicense", new RegisterLicenseController());
        mappings.put("/user/updateLicense", new UpdateLicenseController());
        mappings.put("/user/updateLicense/form", new UpdateLicenseFormController());
        mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/myPage", new UserPageController());
        mappings.put("/user/registerupdate", new UpdateLicenseController());
        
      //관리자관련 request URI 추가
        mappings.put("/admin/mgrPage", new MgrPageController());
        mappings.put("/admin/userList", new listAllUserController());
        mappings.put("/admin/userView", new viewAllUserController());
        mappings.put("/admin/badUserList", new listBadUserController());
        mappings.put("/admin/badUserView", new viewBadUserController());
        mappings.put("/admin/deleteCar", new deleteCarController());
        mappings.put("/admin/addCar", new addCarController());
        mappings.put("/admin/addModel", new addModelController());
        mappings.put("/admin/addModel/form", new aModelController());
        mappings.put("/admin/rentList", new listAllrentController());
        mappings.put("/admin/rentView", new viewAllRentController());
        mappings.put("/admin/reserveList", new listAllReserveController());
        mappings.put("/admin/reserveView", new viewAllReserveController());
        mappings.put("/admin/modelList", new listAllModelController());
        mappings.put("/admin/modelView", new viewAllModelController());
        mappings.put("/admin/carView", new viewAllCarController());
        mappings.put("/admin/qnaList", new listAllQnAController());
        mappings.put("/admin/qnaView", new viewAllQnAController());
        mappings.put("/admin/updateForm", new UpdateAllQnAController());
        mappings.put("/admin/changeRent", new ChangeRentController());
        mappings.put("/admin/resetMCar", new resetMonthlyCarController());
        mappings.put("/admin/return", new ReturnCarController());
        mappings.put("/admin/return/page", new ForwardController("/admin/returnPage.jsp"));
        mappings.put("/admin/cancel", new ManagerCancelController());
        mappings.put("/admin/cancel/page", new ForwardController("/admin/reserveCancel.jsp"));
        mappings.put("/admin/managerView", new ViewManagerController());
        mappings.put("/admin/update", new UpdateManagerController());
        mappings.put("/admin/register/form", new registerMController());
        mappings.put("/admin/register", new RegisterManagerController());
        mappings.put("/admin/login/form", new loginMController());
        mappings.put("/admin/login", new LoginManagerController());
        mappings.put("/admin/deleteuser", new deleteUserByManagerController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
