package jp.co.iacsol.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.iacsol.bl.BLAkashi;
import jp.co.iacsol.to.AnnualSales;
import jp.co.iacsol.to.Department;
import jp.co.iacsol.to.DepartmentAnnualSales;
import jp.co.iacsol.to.PESPosition;
import jp.co.iacsol.to.PESummary;

@WebServlet("/TestAkashi")
public class TestAkashi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestAkashi() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int mostHigherDepartment = 2100;
		int defaultOperatorDepartment = 2110;
		int operatorDepartment = mostHigherDepartment;
		BLAkashi bl = new BLAkashi();
		ArrayList<Department> defaultDepartmentList = bl.findDepartment(mostHigherDepartment);
		ArrayList<PESummary> defaultSummaryList = bl.findPESummary(defaultDepartmentList);
		for (Department d :defaultDepartmentList) {
			if (d.getDepartmentCode() == defaultOperatorDepartment) {
				operatorDepartment = d.getDepartmentCode();
			}
		}
		ArrayList<Department> departmentList = bl.findDepartment(operatorDepartment);
		ArrayList<PESummary> summaryList = bl.findPESummary(departmentList);
		ArrayList<AnnualSales> annualSalesList = bl.findAnnualSales();
		ArrayList<DepartmentAnnualSales> departmentAnnualSalesList = bl.findDepartmentAnnualSales(departmentList);
		ArrayList<PESPosition> positionList = bl.findPESPosition();

		request.setAttribute("personnelPeriod", 2);
		request.setAttribute("fiscalYearList", getFiscalYearList(summaryList));
		request.setAttribute("defaultDepartmentList", defaultDepartmentList);
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("mostHigherDepartment", mostHigherDepartment);
		request.setAttribute("operatorDepartment", operatorDepartment);
		request.setAttribute("defaultSummaryList", defaultSummaryList);
		request.setAttribute("summaryList", summaryList);
		request.setAttribute("annualSalesList", annualSalesList);
		request.setAttribute("departmentAnnualSalesList", departmentAnnualSalesList);
		request.setAttribute("positionList", positionList);
		request.getRequestDispatcher("personnelEvaluationSummary.jsp").forward(request, response);
	}

	private ArrayList<Integer> getFiscalYearList (ArrayList<PESummary> summaryList) {
				Set<Integer> set = new HashSet<>();
		for (PESummary summary : summaryList) {
			set.add(summary.getFiscalYear());
		}
		ArrayList<Integer> fiscalYearList = new ArrayList<>();
		for (int i : set) {
			fiscalYearList.add(i);
		}
		Collections.sort(fiscalYearList, Collections.reverseOrder());
		return fiscalYearList;
	}
}