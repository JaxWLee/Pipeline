package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Reimbursement;
import util.JDBCconnection;

public class EmployeeDAO implements IEmployee {

	public Employee login(String username, String password) {
		try {
			System.out.println("in Login");
			Connection conn = JDBCconnection.getConnection();

			String sql = "Select * FROM Employees WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			Employee employee = new Employee();
			while(rs.next()) {
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setFirst_name(rs.getString("first_name"));
				employee.setLast_name(rs.getString("last_name"));
				employee.setManager(rs.getInt("manager"));
			}
			System.out.println(employee.toString());
			return employee;

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Reimbursement> viewPending(String username) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "Select * FROM Reimbursements WHERE submitted_by = ? AND status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, "pending");
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(rs.getInt("id"));
				reimbursement.setValue(rs.getDouble("value"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setSubmittedBy(rs.getString("submitted_by"));
				reimbursement.setDecidedBy(rs.getString("decided_by"));
				reimbursements.add(reimbursement);
			}
			return reimbursements;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Reimbursement> viewResolved(String username) {
		try {
			Connection conn = JDBCconnection.getConnection();

			String sql = "Select * FROM Reimbursements WHERE submitted_by = ?, status != ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, "pending");
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(rs.getInt("id"));
				reimbursement.setValue(rs.getDouble("value"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setSubmittedBy(rs.getString("submitted_by"));
				reimbursement.setDecidedBy(rs.getString("decided_by"));
				reimbursements.add(reimbursement);
			}
			return reimbursements;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean submitReimbursement(Reimbursement reimbursement) {
		try {
			Connection conn = JDBCconnection.getConnection();

			String sql = "call addReimbursement(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursement.getId());
			cs.setDouble(2, reimbursement.getValue());
			cs.setString(3, reimbursement.getSubmittedBy());
			cs.execute();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Employee viewInfo(String username) {
		try {
			Connection conn = JDBCconnection.getConnection();

			String sql = "Select * FROM Employees WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			Employee employee = new Employee();
			while(rs.next()) {
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setFirst_name(rs.getString("first_name"));
				employee.setLast_name(rs.getString("last_name"));
				employee.setManager(rs.getInt("manager"));
			}

			return employee;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateInfo(Employee employee) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "Update Employee set first_name = ?, last_name = ?, password = ? WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setString(3, employee.getPassword());
			ps.setString(4, employee.getUsername());
			ps.execute();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
