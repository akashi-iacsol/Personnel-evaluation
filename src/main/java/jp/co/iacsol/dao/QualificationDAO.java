//中原　来以安
package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.iacsol.to.Credential;
import jp.co.iacsol.to.Department;
import jp.co.iacsol.to.Employee;
import jp.co.iacsol.to.Qualification;

public class QualificationDAO {
	private Connection con;

	public QualificationDAO(Connection con) {
		this.con = con;
	}

	//資格保有者検索 (入力情報、部署、社員情報）
	public ArrayList<Qualification> QualificationSearch(String input, int departmentCode, int employeeNumber) {
		ArrayList<Qualification> qua = new ArrayList<Qualification>();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Employee "
				+ "INNER JOIN Qualification ON Employee.employee_number = Qualification.employee_number"
				+ "INNER JOIN Department ON Belongs.department_code = Department.department_code"
				+ "INNER JOIN Belongs ON Belongs.employee_number =  Employee.employee_number "
				+ "INNER JOIN Credentials ON Qualification.credentials_code = Credentials.credentials_code"
				+ "WHERE credentials_name Like '%\" " + input + " \"%'"
				+ "employee_number = " + employeeNumber + ""
				+ "department_code = " + departmentCode + "";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				String deptName = rs.getString("department_name");
				String credentialsName = rs.getString("credentials_name");
				String acquisitionDate = rs.getString("acquisition_date");
				Employee emp = new Employee(empNumber, empName);
				qua.add(new Qualification(emp, deptName, credentialsName, acquisitionDate));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qua;
	}

	//資格保有者検索 (資格情報、部署、社員情報）
	public ArrayList<Qualification> QualificationSearch(int credentialsCode, int departmentCode, int employeeNumber) {
		ArrayList<Qualification> qua = new ArrayList<Qualification>();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Employee "
				+ "INNER JOIN Qualification ON Employee.employee_number = Qualification.employee_number"
				+ "INNER JOIN Department ON Belongs.department_code = Department.department_code"
				+ "INNER JOIN Belongs ON Belongs.employee_number =  Employee.employee_number "
				+ "INNER JOIN Credentials ON Qualification.credentials_code = Credentials.credentials_code"
				+ "WERE credentials_name = " + credentialsCode + ""
				+ "department_code = " + employeeNumber + ""
				+ "employee_number = " + employeeNumber + "";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				String deptName = rs.getString("department_name");
				String credentialsName = rs.getString("credentials_name");
				String acquisitionDate = rs.getString("acquisition_date");
				Employee emp = new Employee(empNumber, empName);
				qua.add(new Qualification(emp, deptName, credentialsName, acquisitionDate));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qua;

		//資格保有者検索 (入力情報、部署情報）
	}

	public ArrayList<Qualification> QualificationSearch(String input, int departmentCode) {
		ArrayList<Qualification> qua = new ArrayList<Qualification>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee "
				+ "INNER JOIN Qualification ON Employee.employee_number = Qualification.employee_number"
				+ "INNER JOIN Department ON Belongs.department_code = Department.department_code"
				+ "INNER JOIN Belongs ON Belongs.employee_number =  Employee.employee_number "
				+ "INNER JOIN Credentials ON Qualification.credentials_code = Credentials.credentials_code"
				+ "WHERE credentials_name Like '%\" " + input + " \"%'"
				+ "employee_number = " + departmentCode + "";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				String deptName = rs.getString("department_name");
				String credentialsName = rs.getString("credentials_name");
				String acquisitionDate = rs.getString("acquisition_date");
				Employee emp = new Employee(empNumber, empName);
				qua.add(new Qualification(emp, deptName, credentialsName, acquisitionDate));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qua;
	}

	//資格保有者検索 (社員名、入力情報）
	public ArrayList<Qualification> QualificationSearch(int employeeNumber, String input) {
		ArrayList<Qualification> qua = new ArrayList<Qualification>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee "
				+ "INNER JOIN Qualification ON Employee.employee_number = Qualification.employee_number"
				+ "INNER JOIN Department ON Belongs.department_code = Department.department_code"
				+ "INNER JOIN Belongs ON Belongs.employee_number =  Employee.employee_number "
				+ "INNER JOIN Credentials ON Qualification.credentials_code = Credentials.credentials_code"
				+ "WHERE credentials_name Like '%\" " + input + " \"%'"
				+ "employee_number = " + employeeNumber + "";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				String deptName = rs.getString("department_name");
				String credentialsName = rs.getString("credentials_name");
				String acquisitionDate = rs.getString("acquisition_date");
				Employee emp = new Employee(empNumber, empName);
				qua.add(new Qualification(emp, deptName, credentialsName, acquisitionDate));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qua;
	}

	//資格保有者検索 (部署）
	public ArrayList<Qualification> QualificationSearch(int departmentCode) {
		ArrayList<Qualification> qua = new ArrayList<Qualification>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee "
				+ "INNER JOIN Qualification ON Employee.employee_number = Qualification.employee_number"
				+ "INNER JOIN Department ON Belongs.department_code = Department.department_code"
				+ "INNER JOIN Belongs ON Belongs.employee_number =  Employee.employee_number "
				+ "INNER JOIN Credentials ON Qualification.credentials_code = Credentials.credentials_code"
				+ "WHERE employee_number = " + departmentCode + "";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				String deptName = rs.getString("department_name");
				String credentialsName = rs.getString("credentials_name");
				String acquisitionDate = rs.getString("acquisition_date");
				Employee emp = new Employee(empNumber, empName);
				qua.add(new Qualification(emp, deptName, credentialsName, acquisitionDate));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qua;
	}

	public ArrayList<Qualification> QualificationSearchAll() {
		ArrayList<Qualification> qua = new ArrayList<Qualification>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee "
				+ "INNER JOIN Qualification ON Employee.employee_number = Qualification.employee_number"
				+ "INNER JOIN Department ON Belongs.department_code = Department.department_code"
				+ "INNER JOIN Belongs ON Belongs.employee_number =  Employee.employee_number "
				+ "INNER JOIN Credentials ON Qualification.credentials_code = Credentials.credentials_code";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				String deptName = rs.getString("department_name");
				String credentialsName = rs.getString("credentials_name");
				String acquisitionDate = rs.getString("acquisition_date");
				Employee emp = new Employee(empNumber, empName);
				qua.add(new Qualification(emp, deptName, credentialsName, acquisitionDate));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qua;
	}

	//社員情報取得
	public ArrayList<Employee> AcquisitionAll() {
		ArrayList<Employee> arrayEmp = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * from Employee";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeNumber(rs.getInt("employee_number"));
				emp.setPassword(rs.getString("password"));
				emp.setEmployeeName(rs.getString("employee_name"));
				emp.setDateJoiningCompany(rs.getString("date_joining_company"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setMailAddress(rs.getString("mail_address"));
				emp.setSlackUserName(rs.getString("slack_user_name"));
				emp.setMusterAdministrativeAuthority(rs.getInt("muster_administrative_authority"));
				arrayEmp.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return arrayEmp;
	}

	//資格情報取得
	public ArrayList<Credential> CredentialAcquisition() {
		ArrayList<Credential> cre = new ArrayList<Credential>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM  Qualification"
				+ "INNER JOIN Credentialsredentials ON Qualification.credentials_code = Credentials.credentials_code";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int credentialsCode = rs.getInt("credentials_code");
				String credentialsName = rs.getString("credentials_name");
				cre.add(new Credential(credentialsCode, credentialsName));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return cre;
	}

	//部門情報取得
	public ArrayList<Department> DepartmentAcquisition() {
		ArrayList<Department> depa = new ArrayList<Department>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT department_name FROM Department ";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int fiscalYear = rs.getInt("fiscal_year");
				int departmentCode = rs.getInt("department_code");
				int higherDepartmentCode = rs.getInt("higher_department_code");
				String departmentAbbreviation = rs.getString("department_abbreviation");
				int departmentType = rs.getInt("department_type");
				int superiorEmployeeNumber = rs.getInt("superior_employee_number");

				depa.add(new Department(fiscalYear, departmentCode, higherDepartmentCode, departmentAbbreviation,
						departmentType, superiorEmployeeNumber));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return depa;
	}
}