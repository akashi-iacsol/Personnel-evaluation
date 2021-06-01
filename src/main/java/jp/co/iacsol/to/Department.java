package jp.co.iacsol.to;

public class Department {
	private int fiscalYear = 0;
	private int departmentCode = 0;
	private int higherDepartmentCode = 0;
	private String departmentName;
	private String departmentAbbreviation;
	private int departmentType = 0;
	private int superiorEmployeeNumber = 0;
	private int holidayApproverEmployeeNumber = 0;

	public Department(int fiscalYear, int departmentCode, int higherDepartmentCode, String departmentName,
			String departmentAbbreviation, int departmentType,int superiorEmployeeNumber,int holidayApproverEmployeeNumber) {


		this.fiscalYear = fiscalYear;
		this.departmentCode = departmentCode;
		this.higherDepartmentCode = higherDepartmentCode;
		this.departmentName = departmentName;
		this.departmentAbbreviation = departmentAbbreviation;
		this.departmentType = departmentType;
		this.superiorEmployeeNumber = superiorEmployeeNumber;
		this.holidayApproverEmployeeNumber = holidayApproverEmployeeNumber;

	}
//資格情報コンストラクタ
	public Department(int fiscalYear, int departmentCode, int higherDepartmentCode, String departmentAbbreviation, int departmentType, int superiorEmployeeNumber)
	{
		this.fiscalYear=fiscalYear;
		this.departmentCode=departmentCode;
		this.higherDepartmentCode=higherDepartmentCode;
		this.departmentAbbreviation=departmentAbbreviation;
		this.departmentType=departmentType;
		this.superiorEmployeeNumber=superiorEmployeeNumber;
	}





	public Department() {
		
	}
	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public int getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}

	public int getHigherDepartmentCode() {
		return higherDepartmentCode;
	}

	public void setHigherDepartmentCode(int higherDepartmentCode) {
		this.higherDepartmentCode = higherDepartmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentAbbreviation() {
		return departmentAbbreviation;
	}

	public void setDepartmentAbbreviation(String departmentAbbreviation) {
		this.departmentAbbreviation = departmentAbbreviation;
	}

	public int getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(int departmentType) {
		this.departmentType = departmentType;
	}

	public int getSuperiorEmployeeNumber() {
		return superiorEmployeeNumber;
	}

	public void setSuperiorEmployeeNumber(int superiorEmployeeNumber) {
		this.superiorEmployeeNumber = superiorEmployeeNumber;
	}

	public int getHolidayApproverEmployeeNumber() {
		return holidayApproverEmployeeNumber;
	}

	public void setHolidayApproverEmployeeNumber(int holidayApproverEmployeeNumber) {
		this.holidayApproverEmployeeNumber = holidayApproverEmployeeNumber;
	}
}
