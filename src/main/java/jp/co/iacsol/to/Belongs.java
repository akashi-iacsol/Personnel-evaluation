package jp.co.iacsol.to;

public class Belongs {
	private int fiscalYear = 0;
	private int employeeNumber = 0;
	private int departmentCode = 0;
	private int positionCode = 0;
	private int positionClass = 0;

	//デフォルトコンストラクタ
	public Belongs() {
	}

	public Belongs(int fiscalYear, int employeeNumber, int departmentCode, int positionCode, int positionClass) {
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.departmentCode = departmentCode;
		this.positionCode = positionCode;
		this.positionClass = positionClass;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public int getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}

	public int getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(int positionCode) {
		this.positionCode = positionCode;
	}

	public int getPositionClass() {
		return positionClass;
	}

	public void setPositionClass(int positionClass) {
		this.positionClass = positionClass;
	}

}
