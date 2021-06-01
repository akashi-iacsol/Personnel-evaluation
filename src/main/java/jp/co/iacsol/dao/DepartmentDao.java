//中原　来以安
package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.iacsol.to.Department;

public class DepartmentDao {

	private PreparedStatement ps = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private Connection con = null;

	public DepartmentDao(Connection con) {
		this.con = con;
	}

	//部門マスタに追加
	public ArrayList<Department> DepartmentSelect() {

		ArrayList<Department> arrayDep = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Employee";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Department dep = new Department();
				dep.setFiscalYear(result.getInt("fiscal_year"));
				dep.setDepartmentCode(result.getInt("department_code"));
				dep.setDepartmentName(result.getString("department_name"));
				dep.setDepartmentAbbreviation(result.getString("department_abbreviation"));
				dep.setDepartmentType(result.getInt("department_type"));
				dep.setSuperiorEmployeeNumber(result.getInt("superior_employee_number"));
				arrayDep.add(dep);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayDep;
	}

	//コードをもとに参照
	public ArrayList<Department> DepartmentSelect(int departmentCode) {

		ArrayList<Department> arrayDep = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Employee where department_code = " + departmentCode + "";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Department dep = new Department();
				dep.setFiscalYear(result.getInt("fiscal_year"));
				dep.setDepartmentCode(result.getInt("department_code"));
				dep.setDepartmentName(result.getString("department_name"));
				dep.setDepartmentAbbreviation(result.getString("department_abbreviation"));
				dep.setDepartmentType(result.getInt("department_type"));
				dep.setSuperiorEmployeeNumber(result.getInt("superior_employee_number"));
				arrayDep.add(dep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayDep;
	}

	//追加用
	public void DepartmentInsert(int departmentCode, int higherDepartmentCode, String departmentName,
			String departmentAbbreviation, int departmentType,
			int superiorEmployeeNumber, int holidayApproverEmployeeNumber) {

		try {
			String sql = "INSERT INTO Employee values(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, departmentCode);
			ps.setInt(2, higherDepartmentCode);
			ps.setString(3, departmentName);
			ps.setString(4, departmentAbbreviation);
			ps.setInt(5, departmentType);
			ps.setInt(6, superiorEmployeeNumber);
			ps.setInt(7, holidayApproverEmployeeNumber);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//更新
	public void DepartmentUpdate(int departmentCode, int higherDepartmentCode, String departmentName,
			String departmentAbbreviation, int departmentType,
			int superiorEmployeeNumber, int holidayApproverEmployeeNumber) {

		try {
			String sql = "UPDATE Department SET higher_department_code = ? ,"
					+ " department_name = ?, department_abbreviation = ?, department_type = ?,"
					+ " superior_employee_number = ?,"
					+ " holiday_approver_employee_number = ?"
					+ " WHERE department_code = ?";
			ps = con.prepareStatement(sql);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//削除
	public void DeparatmentDelete(int departmentCode) {
		try {
			String sql = "DELETE FROM Department WHERE department_code = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, departmentCode);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
