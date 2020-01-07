package model;

import java.util.*;


public class License {
	private String licenseNo = null;			
	private String type = null;			
	private Date vdate = null;	
	
	public License(String licenseNo) {
		super();
		this.licenseNo = licenseNo;
	}
	public License(Date vdate, String type, String licenseno) {
		this.vdate = vdate;
		this.type = type;
		this.licenseNo = licenseno;
	}
	public License(String type, String licenseno) {
		this.type = type;
		this.licenseNo = licenseno;
	}
	public License() {
		super();
	}
	 
	public String getLicenseNo() {
		return licenseNo;    
	}	   
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;    
	}
	
	public String getType() {
		return type;    
	}	   
	public void setType(String type) {
		this.type = type;    
	}
	
	public Date getVdate() {
		return vdate;    
	}	   
	public void setVdate(Date vdate) {
		this.vdate = vdate;    
	}
	 
}
