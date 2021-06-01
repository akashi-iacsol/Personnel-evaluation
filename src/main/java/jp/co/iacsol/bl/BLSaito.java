package jp.co.iacsol.bl;

import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.iacsol.dao.EmployeeDao;
import jp.co.iacsol.to.Employee;
import jp.co.iacsol.util.IniFileCon;


public class BLSaito {

	EmployeeDao dao = null;

	//コネクト
	BLSaito(){
		try {
			dao = new EmployeeDao(IniFileCon.con());
		}catch(Exception e) {

		}
	}

	//クローズ
	public void disConnection() {
		try {
			if (IniFileCon.con() != null) {
				IniFileCon.con().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Employee> EmployeeSelect(){
		ArrayList<Employee> arrayEmp = dao.EmployeeSelect();
		disConnection();
		return arrayEmp;
	}

	//任意の社員データを取得するメソッドここから
	public Employee EmployeeSlect(int employeeNumber) {
		Employee emp = dao.EmployeeSlect(employeeNumber);
		disConnection();
		return emp;
	}

	//社員マスタへのINSERTメソッドここから
	public void EmployeeInsert(int employeeNumber,
								String password,
								String employeeName,
								String dateJoiningCompany,
								long phoneNumber,
								String mailAddress,
								String slackUser_name,
								int musterAdministrativeAuthority
								) {
		//処理はここから
		dao.EmployeeInsert(employeeNumber, password, employeeName, dateJoiningCompany,
							phoneNumber, mailAddress, slackUser_name,
							musterAdministrativeAuthority);
		disConnection();
	}

	//社員マスタへのUPDATEメソッドここから
	public void EmployeeUpdate(int employeeNumber,
								String password,
								String employeeName,
								String dateJoiningCompany,
								long phoneNumber,
								String mailAddress,
								String slackUser_name,
								int musterAdministrativeAuthority
								) {
		//処理はここから
		dao.EmployeeUpdate(employeeNumber, password, employeeName, dateJoiningCompany,
							phoneNumber, mailAddress, slackUser_name,
							musterAdministrativeAuthority);
		disConnection();
	}

	//社員マスタへのDELETEメソッドここから
	public void EmployeeDelete(int employeeNumber) {
		dao.EmployeeDelete(employeeNumber);
		disConnection();
	}

}
