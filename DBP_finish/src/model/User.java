package model;

import java.sql.Date;

import model.License;


public class User {
	private int customerNo;						
	private String u_id;					
	private String password;				
	private String name;					
	private String phone;				
	private String age;							
	private String sex;					
				
	private License license;
	private Overdue overdue = new Overdue();
	private Penalty penalty = new Penalty();
	
	public User() {
		super();
	}
	
	public User(int customerNo, String u_id, String password, 
			String name, String phone, String age, String sex, License license) {
		this.customerNo = customerNo;
		this.u_id = u_id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.license = license;
	}

	public User(String u_id, String password, String name, String phone, String age, String sex) {
		this.u_id = u_id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
	}

	public User(String u_id, String password, String name, String phone, String age, String sex, int customerNo) {
		this.u_id = u_id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.customerNo = customerNo;
	}
	
	

	public User(String u_id, String password, String name, String phone, String age, String sex, String license) {
		this.u_id = u_id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.license.setLicenseNo(license);
	}
	
	public User(String u_id, String password, String name, String phone, String age, String sex, License license) {
		this.u_id = u_id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.license = license;
	}

	public User(License license) {
		this.license = license;
	}

	public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.age = updateUser.age;
        this.phone = updateUser.phone;
        this.sex = updateUser.sex;
        this.license = updateUser.license;
    }
	public int getCustomerNo() {
		return customerNo;    
	}	   
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;    
	}
	
	public String getId() {
		return u_id;    
	}	   
	public void setId(String u_id) {
		this.u_id = u_id;    
	}
	
	public String getPassword() {
		return password;    
	}	   
	public void setPassword(String password) {
		this.password = password;    
	}
	
	public String getName() {
		return name;    
	}	   
	public void setName(String name) {
		this.name = name;    
	}
	
	public String getPhone() {
		return phone;    
	}	   
	public void setPhone(String phone) {
		this.phone = phone;    
	}
	
	public License getLicense() {
		return license;  
	}	   
	public void setLicense(License license) {
		this.license = license;;    
	}
	
	public String getAge() {
		return age;    
	}	   
	public void setAge(String age) {
		this.age = age;    
	}
	
	public String getSex() {
		return sex;    
	}	   
	public void setSex(String sex) {
		this.sex = sex;    
	}
	

	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.u_id.equals(userid);
    }
	public Overdue getOverdue() {
		return overdue;
	}

	public void setOverdue(Overdue overdue) {
		this.overdue = overdue;
	}

	public Penalty getPenalty() {
		return penalty;
	}

	public void setPenalty(Penalty penalty) {
		this.penalty = penalty;
	}

	@Override
	public String toString() {
		return "User [customerNo=" + customerNo + ", cid=" + u_id + ", password=" + password + ", name=" + name 
				+ ", age=" + age + ", phone=" + phone + ", sex" + sex + ", license " + license + "]";
	}	
}
