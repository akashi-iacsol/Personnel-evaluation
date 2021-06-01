//中原　来以安
package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.iacsol.to.Belongs;

public class BelongsDao {

	private PreparedStatement ps = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private Connection con = null;

	public BelongsDao(Connection con) {
		this.con = con;
	}

	//すべての情報をもってくる
	public ArrayList<Belongs> BelongsSelect() {

		ArrayList<Belongs> arrayBel = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Belongs";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Belongs bel = new Belongs();
				bel.setFiscalYear(result.getInt("fiscal_year"));
				bel.setEmployeeNumber(result.getInt("employee_number"));
				bel.setDepartmentCode(result.getInt("department_code"));
				bel.setPositionCode(result.getInt("position_code"));
				bel.setPositionClass(result.getInt("position_class"));
				arrayBel.add(bel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayBel;
	}

	//社員番号をもとに持ってくる
	public ArrayList<Belongs> BelongsSelect(int employeeNumber) {

		ArrayList<Belongs> arrayBel = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Belongs where employee_number = " + employeeNumber + "";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Belongs bel = new Belongs();
				bel.setFiscalYear(result.getInt("fiscal_year"));
				bel.setEmployeeNumber(result.getInt("employee_number"));
				bel.setDepartmentCode(result.getInt("department_code"));
				bel.setPositionCode(result.getInt("position_code"));
				bel.setPositionClass(result.getInt("position_class"));
				arrayBel.add(bel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayBel;
	}

	//所属データ追加
	public void BelongsInsert(int employeeNumber, int departmentCode, int positionCode, int positionClass) {

		try {
			String sql = "INSERT INTO Belongs values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, employeeNumber);
			ps.setInt(2, departmentCode);
			ps.setInt(3, positionCode);
			ps.setInt(4, positionClass);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//社員番号をもとに更新
	public void BelongsUpdate(int employeeNumber, int departmentCode, int positionCode, int positionClass) {

		try {
			String sql = "UPDATE Belongs SET "
					+ "department_code = ? "
					+ "position_code = ? "
					+ "position_class = ?"
					+ " WHERE  employee_number = ?";
			ps = con.prepareStatement(sql);

			ps.setInt(1, departmentCode);
			ps.setInt(2, positionCode);
			ps.setInt(3, positionClass);
			ps.setInt(4, employeeNumber);
			ps.executeUpdate();
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//削除
	public void BelongsDelete(int employeeNumber) {
		try {
			String sql = "DELETE FROM  WHERE employee_number = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, employeeNumber);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
