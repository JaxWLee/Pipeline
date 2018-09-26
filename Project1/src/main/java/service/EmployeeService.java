package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;
import model.Reimbursement;

public class EmployeeService {
	public static Employee login(String username, String password) {
		return new EmployeeDAO().login(username,password);
	}
	public static List<Reimbursement> viewPending(String username){
		return new EmployeeDAO().viewPending(username);
	}
	public static List<Reimbursement> viewResolved(String username){
		return new EmployeeDAO().viewResolved(username);
	}
	public static boolean submitReimbursement(Reimbursement reimbursement) {
		return new EmployeeDAO().submitReimbursement(reimbursement);
	}
	public static Employee viewInfo(String username) {
		return new EmployeeDAO().viewInfo(username);
	}
	public static boolean updateInfo(Employee employee) {
		return new EmployeeDAO().updateInfo(employee);
	}
}
