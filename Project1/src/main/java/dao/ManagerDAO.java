package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Reimbursement;
import util.JDBCconnection;

public class ManagerDAO implements IManager {

	public List<Reimbursement> viewPending() {
		try {
			Connection conn = JDBCconnection.getConnection();

			String sql = "Select * FROM Reimbursements WHERE status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "pending");
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

	public List<Reimbursement> viewResolved() {
		try {
			Connection conn = JDBCconnection.getConnection();

			String sql = "Select * FROM Reimbursements WHERE status != ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "pending");
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

	public boolean updateRequest(int id, String status, String username) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "Update Reimbursement set status = ?, decided_by = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, username);
			ps.setInt(3,id);
			ps.execute();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Employee viewEmployee(String username) {
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

	public List<Reimbursement> viewReimbursementsByEmployee(String username) {
		try {
			Connection conn = JDBCconnection.getConnection();

			String sql = "Select * FROM Reimbursements WHERE submitted_by = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
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

}
