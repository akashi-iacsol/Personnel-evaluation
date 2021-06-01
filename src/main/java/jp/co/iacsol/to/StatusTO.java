package jp.co.iacsol.to;

public class StatusTO {

	private int employeeNumber;
	private String employeeName;
	private String departmentName;
	private char submissionStatus;
	private String submissionDate;
	private int personnelStatus;

	public StatusTO(int employeeNumber,
			String employeeName,
			String departmentName,
			char submissionStatus,
			String submissionDate,
			int personnelStatus) {
		this.employeeNumber = employeeNumber;
		this.employeeName = employeeName;
		this.departmentName = departmentName;
		this.submissionStatus = submissionStatus;
		this.submissionDate = submissionDate;
		this.personnelStatus = personnelStatus;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public char getSubmissionStatus() {
		return submissionStatus;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public int getPersonnelStatus() {
		return personnelStatus;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setSubmissionStatus(char submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public void setPersonnelStatus(int personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

}
