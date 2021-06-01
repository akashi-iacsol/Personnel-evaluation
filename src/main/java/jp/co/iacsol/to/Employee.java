package jp.co.iacsol.to;

public class Employee {
	private int employeeNumber = 0;
	private String password;
	private String employeeName;
	private String dateJoiningCompany;
	private String phoneNumber ;
	private String mailAddress;
	private String slackUserName;
	private int musterAdministrativeAuthority = 0;
	private Department depar;
	private Position posi;
	private Belongs belo;
	private Qualification qua;

	//デフォルトコンストラクタ
	public Employee(){
	}

	public Employee(int employeeNumber,String password, String employeeName,
			String dateJoiningCompany, String phoneNumber,
			String mailAddress, String slackUserName,
			int musterAdministrativeAuthority, Department depar,
			Position posi, Belongs belo) {

		this.employeeNumber = employeeNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.dateJoiningCompany = dateJoiningCompany;
		this.phoneNumber = phoneNumber;
		this.mailAddress = mailAddress;
		this.slackUserName = slackUserName;
		this.musterAdministrativeAuthority = musterAdministrativeAuthority;
		this.depar = depar;
		this.posi = posi;
		this.belo = belo;
	}
	public Employee(int employeeNumber, String password, String employeeName,
			String dateJoiningCompany, String phoneNumber,
			String mailAddress, String slackUserName,
			int musterAdministrativeAuthority) {

		this.employeeNumber = employeeNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.dateJoiningCompany = dateJoiningCompany;
		this.phoneNumber = phoneNumber;
		this.mailAddress = mailAddress;
		this.slackUserName = slackUserName;
		this.musterAdministrativeAuthority = musterAdministrativeAuthority;
	}
	public Employee(int employeeNumber, String password, String employeeName, String dateJoiningCompany, String phoneNumber,
			String mailAddress, String slackUserName, int musterAdministrativeAuthority, Department depar,
			Position posi, Belongs belo,Qualification qua) {

		this.employeeNumber = employeeNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.dateJoiningCompany = dateJoiningCompany;
		this.phoneNumber = phoneNumber;
		this.mailAddress = mailAddress;
		this.slackUserName = slackUserName;
		this.musterAdministrativeAuthority = musterAdministrativeAuthority;
		this.depar = depar;
		this.posi = posi;
		this.belo = belo;
		this.qua =qua;
	}
//資格情報検索用のコンストラクタ
	public Employee(int empNumber, String empName) {
		this.employeeNumber=empNumber;
		this.employeeName=empName;
	}
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDateJoiningCompany() {
		return dateJoiningCompany;
	}

	public void setDateJoiningCompany(String dateJoiningCompany) {
		this.dateJoiningCompany = dateJoiningCompany;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getSlackUserName() {
		return slackUserName;
	}

	public void setSlackUserName(String slackUserName) {
		this.slackUserName = slackUserName;
	}

	public int getMusterAdministrativeAuthority() {
		return musterAdministrativeAuthority;
	}

	public void setMusterAdministrativeAuthority(int musterAdministrativeAuthority) {
		this.musterAdministrativeAuthority = musterAdministrativeAuthority;
	}

	public Department getDepar() {
		return depar;
	}

	public void setDepar(Department depar) {
		this.depar = depar;
	}

	public Position getPosi() {
		return posi;
	}

	public void setPosi(Position posi) {
		this.posi = posi;
	}

	public Belongs getBelo() {
		return belo;
	}

	public void setBelo(Belongs belo) {
		this.belo = belo;
	}

	public Qualification getQua() {
		return qua;
	}

	public void setQua(Qualification qua) {
		this.qua = qua;
	}

}