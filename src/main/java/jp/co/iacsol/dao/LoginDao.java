package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.iacsol.to.Belongs;
import jp.co.iacsol.to.Department;
import jp.co.iacsol.to.Employee;
import jp.co.iacsol.to.Position;


public class LoginDao {

	private Connection con;


	public LoginDao() {
	}

	public LoginDao(Connection con) {
		this.con = con;
	}


	public Employee findByEmployee(int employee_number, String password) {

		String strSQL = "SELECT * FROM Employee LEFT OTHER JOIN    ";
		Employee employee = null;

		try {

			PreparedStatement pStmt = con.prepareStatement(strSQL);

			pStmt.setInt(1, employee_number);
			pStmt.setString(2, password);


			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int employeeNumber = rs.getInt("employee_number");
				String Password = rs.getString("password");
				String employeeName = rs.getString("employee_name");
				String dateJoiningCompany = rs.getString("date_joining_company");
				String phoneNumber = rs.getString("phone_number");
				String mailAddress= rs.getString("mail_address");
				String slackUserName = rs.getString("slack_user_name");
				int musterAdministrativeAuthority = rs.getInt("muster_administrative_authority");
				int fiscalYear = rs.getInt("fiscal_year");
			    int departmentCode = rs.getInt("department_code");
				int higherDepartmentCode = rs.getInt("higher_department_code");
                String departmentName = rs.getString("department_name");
                String departmentAbbreviation = rs.getString("department_abbreviation");
                int departmentType = rs.getInt("department_type");
                int superiorEmployeeNumber = rs.getInt("superior_employee_number");
                int holidayApproverEmployeeNumber = rs.getInt("holiday_approver_employee_number");
                int positionCode = rs.getInt("position_code");
                String positionName = rs.getString("position_name");
                String positionAbbreviation = rs.getString("position_abbreviation");
                int positionClass = rs.getInt("position_class");

                Department depar = new Department(fiscalYear,departmentCode,higherDepartmentCode,
                		                          departmentName,departmentAbbreviation,departmentType,
                		                          superiorEmployeeNumber,holidayApproverEmployeeNumber);

                Position posi = new Position(positionCode,positionName,positionAbbreviation);

                Belongs belo = new Belongs(fiscalYear,employeeNumber,departmentCode,positionCode,
                		                   positionClass);

				employee = new Employee(employeeNumber, Password, employeeName, dateJoiningCompany,
						                phoneNumber, mailAddress, slackUserName,
						                musterAdministrativeAuthority, depar, posi, belo);
			}

			rs.close();
			pStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
}