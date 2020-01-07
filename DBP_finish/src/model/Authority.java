package model;

public class Authority {
	private String autName = null;			
	private String mRentRreserve = null;	
	private String mCustom = null;			
	private String mCar = null;				
	private String mQnA = null;				

	public Authority(String autName) {
		this.autName = autName;
	}
	public Authority() {
		super();
	}
	public String getAuthorityName() {
		return autName;    
	}	   
	public void setAuthorityName(String autName) {
		this.autName = autName;    
	}
	
	public String getMRentRreserve() {
		return mRentRreserve;    
	}	   
	public void setMRentRreserve(String mRentRreserve) {
		this.mRentRreserve = mRentRreserve;    
	}
	

	
	public String getMCustome() {
		return mCustom;    
	}   
	public void setMCustome(String mCustom) {
		this.mCustom = mCustom;    
	}
	
	public String getMCar() {
		return mCar;    
	}  
	public void setMCar(String mCar) {
		this.mCar = mCar;    
	}
	
	public String getMQnA() {
		return mQnA;    
	}	   
	public void setMQnA(String mQnA) {
		this.mQnA = mQnA;    
	}
}
