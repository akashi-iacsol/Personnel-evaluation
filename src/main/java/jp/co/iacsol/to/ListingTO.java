package jp.co.iacsol.to;

public class ListingTO {
	private int fiscalYear;
	private int employeeNumber;
	private String employeeName;
	private String departmentName;
	private String middleComment;
	private String lastComment;

	public ListingTO(int fiscalYear,int employeeNumber, String employeeName, String departmentName,
			String middleComment, String lastComment) {
		this.fiscalYear=fiscalYear;
		this.employeeNumber = employeeNumber;
		this.employeeName = employeeName;
		this.departmentName = departmentName;
		this.middleComment = middleComment;
		this.lastComment = lastComment;

	}

	public int getFiscalYear() {
		return fiscalYear;
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

	public String getMiddleComment() {
		return middleComment;
	}

	public String getLastComment() {
		return lastComment;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public int setEmployeeNumber() {
		return employeeNumber;
	}

	public String setEmployeeName() {
		return employeeName;
	}

	public String setDepartmentName() {
		return departmentName;
	}

	public String setMiddleComment() {
		return middleComment;
	}

	public String setLastComment() {
		return lastComment;
	}

}
