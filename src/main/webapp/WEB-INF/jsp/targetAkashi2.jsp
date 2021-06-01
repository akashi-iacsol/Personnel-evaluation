<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.iacsol.to.*" import="java.util.*"%>
<!DOCTYPE html>
<jsp:useBean id="summaryList" type="java.util.ArrayList<PESummary>"
	scope="request" />
<jsp:useBean id="annualSales" type="jp.co.iacsol.to.AnnualSales"
	scope="request" />
<jsp:useBean id="departmentAnnualSalesList" type="java.util.ArrayList<DepartmentAnnualSales>"
	scope="request" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=request.getParameter("nowFiscalYear")%>
	<br>
	<%=request.getParameter("nowPersonnelPeriod")%>
	<br>
	<%
		for (PESummary summary : summaryList) {
	%>
	<%=summary.getEmp().getEmployeeNumber()%>
	<%=summary.getRank()%>
	<%=summary.getBonusRecommendation()%>
	<%=summary.getMiddleComment()%>
	<%=summary.getBonusComfirm()%>
	<%=summary.getLastPositionCode()%>
	<%=summary.getLastRank()%>
	<%=summary.getLastComment()%>
	<%=summary.getAppraisalPositionCode()%>
	<%=summary.getAppraisalRank()%>
	<%=summary.getAppraisalComment()%>
	<%=summary.getStatus()%>
	<br>
	<%
		}
	%>
	<br>
	<%=annualSales.getFiscalYear()%>
	<br>
	<%=annualSales.getSalesTarget()%>
	<br>
	<%=annualSales.getSalesResult()%>
	<br>
	<%=annualSales.getSalesIncreaseRate()%>
	<br>
	<%=annualSales.getRankIncreaseRate()%>
	<br>
	<%
		for (DepartmentAnnualSales ds : departmentAnnualSalesList) {
	%>
	<%=ds.getFiscalYear()%>
	<%=ds.getDepartmentCode()%>
	<%=ds.getFirstHalfSalesTarget()%>
	<%=ds.getFirstHalfSalesResult()%>
	<%=ds.getSecondHalfSalesTarget()%>
	<%=ds.getSecondHalfSalesResult()%>
	<%=ds.getDepartmentRankTarget()%>
	<%=ds.getDepartmentRankResult()%>
	<br>
	<%
		}
	%>
</body>
</html>