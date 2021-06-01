package jp.co.iacsol.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.iacsol.bl.BLAkashi;
import jp.co.iacsol.to.AnnualSales;
import jp.co.iacsol.to.DepartmentAnnualSales;
import jp.co.iacsol.to.Employee;
import jp.co.iacsol.to.PESummary;

@WebServlet("/PESummaryServlet")
public class PESummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PESummaryServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//		HttpSession session = request.getSession();
		int fiscalYear = Integer.parseInt(request.getParameter("nowFiscalYear"));
		int personnelPeriod = Integer.parseInt(request.getParameter("nowPersonnelPeriod"));

		String[] employeeNumberList = request.getParameterValues("employeeNumber");
		String[] rankList = request.getParameterValues("rank");
		String[] fiscalYearList = request.getParameterValues("fiscalYear");
		String[] bonusRecommendationList = request.getParameterValues("bonusRecommendation");
		String[] middleCommentList = request.getParameterValues("middleComment");
		String[] bonusComfirmList = request.getParameterValues("bonusComfirm");
		String[] lastPositionCodeList = request.getParameterValues("lastPositionCode");
		String[] lastRankList = request.getParameterValues("lastRank");
		String[] lastCommentList = request.getParameterValues("lastComment");
		String[] appraisalPositionCodeList = request.getParameterValues("appraisalPositionCode");
		String[] appraisalRankList = request.getParameterValues("appraisalRank");
		String[] appraisalCommentList = request.getParameterValues("appraisalComment");
		String[] statusList = request.getParameterValues("status");

		ArrayList<PESummary> summaryList = new ArrayList<>();

		for (int i = 0; i < employeeNumberList.length; i++) {
			summaryList.add(new PESummary(
					new Employee(Integer.parseInt(employeeNumberList[i]), null, null, null, null, null, null,
							0),
					Integer.parseInt(rankList[i]),
					Integer.parseInt(fiscalYearList[i]),
					Integer.parseInt(bonusRecommendationList[i]),
					middleCommentList[i],
					Integer.parseInt(bonusComfirmList[i]),
					Integer.parseInt(lastPositionCodeList[i]),
					Integer.parseInt(lastRankList[i]),
					lastCommentList[i],
					Integer.parseInt(appraisalPositionCodeList[i]),
					Integer.parseInt(appraisalRankList[i]),
					appraisalCommentList[i],
					Integer.parseInt(statusList[i])));
		}

		AnnualSales annualSales = new AnnualSales(Integer.parseInt(request.getParameter("ASFiscalYear")),
				Long.parseLong(request.getParameter("ASSalesTarget")),
				Long.parseLong(request.getParameter("ASSalesResult")),
				Float.parseFloat(request.getParameter("ASSalesIncreaseRate")),
				Float.parseFloat(request.getParameter("ASRankIncreaseRate")));

		String[] DSFiscalYearList = request.getParameterValues("DSFiscalYear");
		String[] DSDepartmentCodeList = request.getParameterValues("DSDepartmentCode");
		String[] DSFirstHalfSalesTargetList = request.getParameterValues("DSFirstHalfSalesTarget");
		String[] DSFirstHalfSalesResultList = request.getParameterValues("DSFirstHalfSalesResult");
		String[] DSSecondHalfSalesTargetList = request.getParameterValues("DSSecondHalfSalesTarget");
		String[] DSSecondHalfSalesResultList = request.getParameterValues("DSSecondHalfSalesResult");
		String[] DSDepartmentRankTargetList = request.getParameterValues("DSDepartmentRankTarget");
		String[] DSDepartmentRankResultList = request.getParameterValues("DSDepartmentRankResult");
		ArrayList<DepartmentAnnualSales> departmentAnnualSalesList = new ArrayList<>();

		for (int i = 0; i < DSFiscalYearList.length; i++) {
			departmentAnnualSalesList
					.add(new DepartmentAnnualSales(Integer.parseInt(DSFiscalYearList[i]),
							Integer.parseInt(DSDepartmentCodeList[i]),
							Long.parseLong(DSFirstHalfSalesTargetList[i]),
							Long.parseLong(DSFirstHalfSalesResultList[i]),
							Long.parseLong(DSSecondHalfSalesTargetList[i]),
							Long.parseLong(DSSecondHalfSalesResultList[i]),
							Integer.parseInt(DSDepartmentRankTargetList[i]),
							Integer.parseInt(DSDepartmentRankResultList[i])));
		}


		request.setAttribute("summaryList", summaryList);
		request.setAttribute("annualSales", annualSales);
		request.setAttribute("departmentAnnualSalesList", departmentAnnualSalesList);
		BLAkashi bl = new BLAkashi();
		bl.updatePESummary(summaryList, annualSales, departmentAnnualSalesList);
		request.getRequestDispatcher("targetAkashi2.jsp").forward(request, response);
//				if (personnelPeriod == 6) {
//					bl.insertNextYearPosition(fiscalYear);
//					bl.insertNextYearDepartment(fiscalYear);
//					bl.insertNextYearPESummary(fiscalYear);
//					bl.insertNextYearBelongs(fiscalYear, summaryList);
//					bl.insertNextYearQualification(fiscalYear);
//					bl.insertNextYearEvaluationHR(fiscalYear);
//					bl.insertNextYearEvaluationVA(fiscalYear);
//					bl.insertNextYearEvaluationAttirude(fiscalYear);
//					bl.insertNextYearDepartmentAnnualSales(fiscalYear);
//					bl.insertNextYearAnnualSales(fiscalYear);
//					bl.insertNextYearEvaluationModel(fiscalYear);
//					request.getRequestDispatcher("testAkashi.jsp").forward(request, response);
//				}
	}
}