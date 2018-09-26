package dao;

import java.util.List;

import model.Employee;
import model.Reimbursement;

public interface IManager {
	public List<Reimbursement> viewPending();
	public List<Reimbursement> viewResolved();
	public boolean updateRequest(int id, String status, String username);
	public Employee viewEmployee(String username);
	public List<Reimbursement> viewReimbursementsByEmployee(String username);	
}
