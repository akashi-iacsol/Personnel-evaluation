package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.iacsol.to.Employee;

public class EmployeeDao {
	private PreparedStatement ps = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private Connection con = null;

	public EmployeeDao(Connection con){
		this.con = con;
	}

	//社員マスタデータすべて取得するメソッドここから
	public ArrayList<Employee> EmployeeSelect(){
		ArrayList<Employee> arrayEmp = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sqlEmployee = "SELECT * from Employee";
			result = stmt.executeQuery(sqlEmployee);
			while (result.next()) {
				Employee emp = new Employee();
				emp.setEmployeeNumber(result.getInt("employee_number"));
				emp.setPassword(result.getString("password"));
				emp.setEmployeeName(result.getString("employee_name"));
				emp.setDateJoiningCompany(result.getString("date_joining_company"));
				emp.setPhoneNumber(result.getString("phone_number"));
				emp.setMailAddress(result.getString("mail_address"));
				emp.setSlackUserName(result.getString("slack_user_name"));
				emp.setMusterAdministrativeAuthority(result.getInt("muster_administrative_authority"));
				arrayEmp.add(emp);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return arrayEmp;
	}

	//任意の社員データを取得するメソッドここから
	public Employee EmployeeSlect(int employeeNumber) {
		Employee emp = new Employee();
		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Employee WHERE employee_number = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, employeeNumber);
			result = ps.executeQuery();
			result.next();

			emp.setEmployeeNumber(result.getInt("employee_number"));
			emp.setPassword(result.getString("password"));
			emp.setEmployeeName(result.getString("employee_name"));
			emp.setDateJoiningCompany(result.getString("date_joining_company"));
			emp.setPhoneNumber(result.getString("phone_number"));
			emp.setMailAddress(result.getString("mail_address"));
			emp.setSlackUserName(result.getString("slack_user_name"));
			emp.setMusterAdministrativeAuthority(result.getInt("muster_administrative_authority"));

		}catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	//社員マスタへのINSERTメソッドここから
	public void EmployeeInsert(int employeeNumber,
								String password,
								String employeeName,
								String dateJoiningCompany,
								String phoneNumber,
								String mailAddress,
								String slackUser_name,
								int musterAdministrativeAuthority
								) {
		//処理はここから
		try {
			String sql = "INSERT INTO Employee values(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, employeeNumber);
			ps.setString(2, password);
			ps.setString(3, employeeName);
			ps.setString(4, dateJoiningCompany);
			ps.setString(5, phoneNumber);
			ps.setString(6, mailAddress);
			ps.setString(7, slackUser_name);
			ps.setInt(8, musterAdministrativeAuthority);

			ps.executeUpdate();
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	//社員マスタへのUPDATEメソッドここから
	public void EmployeeUpdate(int employeeNumber,
								String password,
								String employeeName,
								String dateJoiningCompany,
								String phoneNumber,
								String mailAddress,
								String slackUser_name,
								int musterAdministrativeAuthority
								) {
		//処理はここから
		try {
			String sql = "UPDATE Employee SET password=?,"
					+ " employee_name=?, date_joining_company=?, phone_number=?,"
					+ " mail_address=?, slack_user_name=?,"
					+ " muster_administrative_authority=?"
					+ " WHERE employee_number=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, employeeName);
			ps.setString(3, dateJoiningCompany);
			ps.setString(4, phoneNumber);
			ps.setString(5, mailAddress);
			ps.setString(6, slackUser_name);
			ps.setInt(7, musterAdministrativeAuthority);
			ps.setInt(8, employeeNumber);

			ps.executeUpdate();
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//社員マスタへのDELETEメソッドここから
	public void EmployeeDelete(int employeeNumber) {
		try {
			String sql = "DELETE FROM Employee WHERE employee_number=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, employeeNumber);

			ps.executeUpdate();
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
