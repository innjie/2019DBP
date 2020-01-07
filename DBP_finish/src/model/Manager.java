package model;

public class Manager {
	private int managerNo;	
	private int comno;
	private String id;					
	private String password;				
	private String name;					
	private String phone;
	private String task;
	Authority authority = new Authority();
	Company company = new Company();
	
	public Manager(String id, String password, String name, 
				Company company, String phone, Authority authority) {
			this.id = id;
			this.password = password;
			this.name = name;
			this.company = company;
			this.phone = phone;
			this.authority = authority;
	}
	public Manager() {
		super();
	}
	
	public Manager(int managerNo) {
		this.managerNo = managerNo;
	}
	public Manager(String name) {
		this.name = name;
	}
	
	public Manager(String id, String password, String name, 
				int comno, String phone, String task) {
			this.id = id;
			this.password = password;
			this.name = name;
			this.comno = comno;
			this.phone = phone;
			this.task = task;
	}
	public Manager(String id, String password, String name, 
			String phone, String task) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.task = task;
}
	public Manager(String id, String password, String name, 
			 String phone, Authority authority) {
			this.id = id;
			this.password = password;
			this.name = name;
			this.phone = phone;
			this.authority = authority;
	}
	public int getManagerNo() {
		return managerNo;    
	}	   
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;    
	}
	
	public String getId() {
		return id;    
	}	   
	public void setId(String id) {
		this.id = id;    
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
	

	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public int getComno() {
		return comno;
	}
	public void setComno(int comno) {
		this.comno = comno;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String id) {
        return this.id.equals(id);
    }
	
}
