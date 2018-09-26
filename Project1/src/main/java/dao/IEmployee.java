package dao;

import java.util.List;

import model.Employee;
import model.Reimbursement;

public interface IEmployee {
	public Employee login(String username, String password);
	public List<Reimbursement> viewPending(String username);
	public List<Reimbursement> viewResolved(String username);
	public boolean submitReimbursement(Reimbursement reimbursement);
	public Employee viewInfo(String username);
	public boolean updateInfo(Employee employee);
}
