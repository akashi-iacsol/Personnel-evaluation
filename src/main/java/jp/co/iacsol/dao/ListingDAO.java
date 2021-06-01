package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.iacsol.to.ListingTO;
import jp.co.iacsol.to.OtherEvaluationTO;

public class ListingDAO {

	private Connection con = null;

	public ListingDAO(Connection con) {
		this.con = con;
	}

	public ArrayList<ListingTO> findByListing(String employeeName, int fiscalYear) {
		Statement stmt = null;

		String sql = "SELECT department.fiscal_year,employee.employee_number,employee.employee_name ,"
				+ "department.department_name,pesummary.middle_comment,pesummary.last_comment FROM employee "
				+ "INNER JOIN belongs ON employee.employee_number=belongs.employee_number "
				+ "INNER JOIN department ON belongs.department_code=department.department_code "
				+ "LEFT JOIN pesummary ON employee.employee_number=pesummary.employee_number ";
		ArrayList<ListingTO> listingArray = new ArrayList<ListingTO>();

		try {

			if (employeeName != null && fiscalYear != 0) {
				sql = sql + "WHERE employee.employee_name='" + employeeName
						+ "' AND department.fiscal_year='"
						+ fiscalYear + "'";
			} else if (employeeName != null && fiscalYear == 0) {
				sql = sql + "WHERE employee.employee_name='" + employeeName + "'";
			} else if (employeeName == null && fiscalYear != 0) {
				sql = sql + "WHERE department.fiscal_year='" + fiscalYear + "'";
			}
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ListingTO listing = new ListingTO(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
				listingArray.add(listing);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listingArray;

	}

	public ArrayList<OtherEvaluationTO> findByEmployeeName() {
		Statement stmt = null;

		String sql = "SELECT Evaluation_Attirude.other_evaluation,employee.employee_name FROM employee" +
				" LEFT JOIN Evaluation_Attirude ON employee.employee_number=Evaluation_Attirude.employee_number";
		ArrayList<OtherEvaluationTO> otherEvaluationArray = new ArrayList<OtherEvaluationTO>();

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if(rs.getInt(1)!=0) {
				OtherEvaluationTO otherEvaluation = new OtherEvaluationTO(
						rs.getInt(1),
						rs.getString(2));
				otherEvaluationArray.add(otherEvaluation);
			}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return otherEvaluationArray;
	}

}
