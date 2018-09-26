package service;

import java.util.List;

import dao.ManagerDAO;
import model.Employee;
import model.Reimbursement;

public class ManagerService {
	public List<Reimbursement> viewPending(){
		return new ManagerDAO().viewPending();
	}
	public List<Reimbursement> viewResolved(){
		return new ManagerDAO().viewResolved();
	}
	public boolean updateRequest(int id, String status, String username) {
		return new ManagerDAO().updateRequest(id, status, username);
	}
	public Employee viewEmployee(String username) {
		return new ManagerDAO().viewEmployee(username);
	}
	public List<Reimbursement> viewReimbursementsByEmployee(String username){
		return new ManagerDAO().viewReimbursementsByEmployee(username);
	}
}
