package jp.co.iacsol.bl;

import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.iacsol.dao.PESummaryDAO;
import jp.co.iacsol.to.AnnualSales;
import jp.co.iacsol.to.Department;
import jp.co.iacsol.to.DepartmentAnnualSales;
import jp.co.iacsol.to.PESPosition;
import jp.co.iacsol.to.PESummary;
import jp.co.iacsol.util.IniFileCon;

public class BLAkashi {
	private PESummaryDAO dao = null;

	public BLAkashi() {
		dao = new PESummaryDAO(IniFileCon.con());
	}

	public ArrayList<Department> findDepartment(int departmentCode) {
		ArrayList<Department> list = dao.findDepartment(departmentCode);
		disConnection();
		return list;
	}

	public ArrayList<PESummary> findPESummary(ArrayList<Department> departmentList) {
		ArrayList<PESummary> list = dao.findPESummary(departmentList);
		disConnection();
		return list;
	}

	public ArrayList<AnnualSales> findAnnualSales() {
		ArrayList<AnnualSales> list = dao.findAnnualSales();
		disConnection();
		return list;
	}

	public ArrayList<DepartmentAnnualSales> findDepartmentAnnualSales(ArrayList<Department> departmentList) {
		ArrayList<DepartmentAnnualSales> list = dao.findDepartmentAnnualSales(departmentList);
		disConnection();
		return list;
	}

	public ArrayList<PESPosition> findPESPosition() {
		ArrayList<PESPosition> list = dao.findPESPosition();
		disConnection();
		return list;
	}

	public void updatePESummary(ArrayList<PESummary> summaryList, AnnualSales annualSales, ArrayList<DepartmentAnnualSales> departmentAnnualSalesList) {
		dao.updatePESummary(summaryList, annualSales, departmentAnnualSalesList);
		disConnection();
	}

	public void insertNextYearPESummary(int fiscalYear) {
		dao.insertNextYearPESummary(fiscalYear);
		disConnection();
	}

	public void insertNextYearDepartment(int fiscalYear) {
		dao.insertNextYearDepartment(fiscalYear);
		disConnection();
	}

	public void insertNextYearPosition(int fiscalYear) {
		dao.insertNextYearPosition(fiscalYear);
		disConnection();
	}

	public void insertNextYearBelongs(int fiscalYear, ArrayList<PESummary> summaryList) {
		dao.insertNextYearBelongs(fiscalYear, summaryList);
		disConnection();
	}

	public void insertNextYearQualification(int fiscalYear) {
		dao.insertNextYearQualification(fiscalYear);
		disConnection();
	}

	public void insertNextYearEvaluationHR(int fiscalYear) {
		dao.insertNextYearEvaluationHR(fiscalYear);
		disConnection();
	}

	public void insertNextYearEvaluationVA(int fiscalYear) {
		dao.insertNextYearEvaluationVA(fiscalYear);
		disConnection();
	}

	public void insertNextYearEvaluationAttirude(int fiscalYear) {
		dao.insertNextYearEvaluationAttirude(fiscalYear);
		disConnection();
	}

	public void insertNextYearAnnualSales(int fiscalYear) {
		dao.insertNextYearAnnualSales(fiscalYear);
		disConnection();
	}

	public void insertNextYearDepartmentAnnualSales(int fiscalYear) {
		dao.insertNextYearDepartmentAnnualSales(fiscalYear);
		disConnection();
	}

	public void insertNextYearEvaluationModel(int fiscalYear) {
		dao.insertNextYearEvaluationModel(fiscalYear);
		disConnection();
	}

	public void disConnection() {
		try {
			if (IniFileCon.con() != null) {
				IniFileCon.con().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}