package model;

public class Reimbursement {
	private int id;
	private double value;
	private String status;
	private String submittedBy;
	private String decidedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
	public String getDecidedBy() {
		return decidedBy;
	}
	public void setDecidedBy(String decidedBy) {
		this.decidedBy = decidedBy;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", value=" + value + ", status=" + status + ", submittedBy=" + submittedBy
				+ ", decidedBy=" + decidedBy + "]";
	}
	
}
