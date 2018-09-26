package model;

import java.util.List;

public class Employee {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private int manager;
	private List<Reimbursement> reimbursements;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}
	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", manager=" + manager + ", reimbursements=" + reimbursements + "]";
	}

	
}
