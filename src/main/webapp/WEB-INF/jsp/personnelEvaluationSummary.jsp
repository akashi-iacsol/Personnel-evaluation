<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.iacsol.to.*" import="java.util.*"
	import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="ja">
<jsp:useBean id="defaultDepartmentList" type="java.util.ArrayList<Department>"
	scope="request" />
<jsp:useBean id="defaultSummaryList" type="java.util.ArrayList<PESummary>"
	scope="request" />
<jsp:useBean id="departmentList" type="java.util.ArrayList<Department>"
	scope="request" />
<jsp:useBean id="summaryList" type="java.util.ArrayList<PESummary>"
	scope="request" />
<jsp:useBean id="departmentAnnualSalesList"
	type="java.util.ArrayList<DepartmentAnnualSales>" scope="request" />
<jsp:useBean id="annualSalesList"
	type="java.util.ArrayList<AnnualSales>" scope="request" />
<jsp:useBean id="positionList" type="java.util.ArrayList<PESPosition>"
	scope="request" />
<jsp:useBean id="fiscalYearList" type="java.util.ArrayList<Integer>"
	scope="request" />
<%
	int nowFiscalYear = LocalDate.now().getYear();
if (LocalDate.now().getMonthValue() < 10) {
	nowFiscalYear = nowFiscalYear - 1;
}

int personnelPeriod = 0;
Set<Integer> personnelStatusChecker = new HashSet<>();
for (PESummary p : summaryList) {
	if (p.getFiscalYear() == nowFiscalYear) {
		personnelStatusChecker.add(p.getStatus());
	}
}
if (personnelStatusChecker.size() == 1) {
	ArrayList<Integer> personnelStatus = new ArrayList<>(personnelStatusChecker);
	switch (personnelStatus.get(0)) {
	case 1:
		if (LocalDate.now().getMonthValue() >= 2 && LocalDate.now().getMonthValue() <= 4) {
	personnelPeriod = 1;
		} else if (LocalDate.now().getMonthValue() >= 5 && LocalDate.now().getMonthValue() <= 6) {
	personnelPeriod = 4;
		}
		break;
	case 2:
		if (LocalDate.now().getMonthValue() >= 2 && LocalDate.now().getMonthValue() <= 4) {
	personnelPeriod = 2;
		} else if (LocalDate.now().getMonthValue() >= 5 && LocalDate.now().getMonthValue() <= 6) {
	personnelPeriod = 5;
		}
		break;
	case 3:
		if (LocalDate.now().getMonthValue() >= 2 && LocalDate.now().getMonthValue() <= 4) {
	personnelPeriod = 3;
		} else if (LocalDate.now().getMonthValue() >= 7 && LocalDate.now().getMonthValue() <= 9) {
	personnelPeriod = 6;
		}
		break;
	}
}

int mostHigherDepartment = (Integer) request.getAttribute("mostHigherDepartment");
int operatorDepartment = (Integer) request.getAttribute("operatorDepartment");

ArrayList<ArrayList<Integer>> departmentRankList = new ArrayList<>();
for (Department d : defaultDepartmentList) {
	if (d.getDepartmentType() == 0 && d.getDepartmentCode() != mostHigherDepartment) {
		int departRankWithoutRookie = 0;
		int departRank = 0;
		for (PESummary p : defaultSummaryList) {
			if (p.getFiscalYear() == d.getFiscalYear()) {
				if (p.getEmp().getDepar().getDepartmentCode() == d.getDepartmentCode()) {
					if(Integer.parseInt(
								p.getEmp().getDateJoiningCompany().substring(0, 4)) < p.getFiscalYear()
							|| (Integer.parseInt(
								p.getEmp().getDateJoiningCompany().substring(0, 4)) == p.getFiscalYear()
							&& Integer.parseInt(
								p.getEmp().getDateJoiningCompany().substring(4)) < 1000)) {
						departRankWithoutRookie += p.getRank();
					}
					departRank += p.getRank();
				} else if (p.getEmp().getDepar().getHigherDepartmentCode() == d.getDepartmentCode()) {
					if(Integer.parseInt(
								p.getEmp().getDateJoiningCompany().substring(0, 4)) < p.getFiscalYear()
							|| (Integer.parseInt(
								p.getEmp().getDateJoiningCompany().substring(0, 4)) == p.getFiscalYear()
							&& Integer.parseInt(
								p.getEmp().getDateJoiningCompany().substring(4)) < 1000)) {
						departRankWithoutRookie += p.getRank();
					}
					departRank += p.getRank();
				}
			}
		}
		ArrayList<Integer> departmentRank = new ArrayList<Integer>();
		departmentRank.add(d.getFiscalYear());
		departmentRank.add(d.getDepartmentCode());
		departmentRank.add(departRankWithoutRookie);
		departmentRank.add(departRank);
		departmentRankList.add(departmentRank);
	}
}
%>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="personnelEvaluationSummary.css">
</head>
<body>
	<form id="modelButtonForm" method="post" target="_blank"
		action="targetAkashi.jsp">
		<input type="hidden" id="selectYearModel" name="selectYear" value="" />
	</form>
	<form id="employeeButtonForm" method="post" target="_blank"
		action="targetAkashi.jsp">
		<input type="hidden" id="selectYearEmployee" name="selectYear"
			value="" /> <input type="hidden" id="selectEmployee"
			name="selectEmployee" value="" />
	</form>
	<input type="hidden" id="nowFiscalYear" name="nowFiscalYear" value=<%=nowFiscalYear%> form="inputSummary" />
	<select id="nowPersonnelPeriod" name="nowPersonnelPeriod" form="inputSummary">
		<%
			for (int i = 0; i <= 6; i++) {
		%><option id="all" value=<%=i%> <%if (personnelPeriod == i) {%>
			selected <%}%>><%=i%>
		</option>
		<%
			}
		%>
	</select>
	<input type="text" id="tBox" class="font-main"
		value=<%=personnelPeriod%> />
	<div class="content">
		<div class="title">
			<table class="buttonBox">
				<tr>
					<td class="noBorder"><button
							onclick="location.href='./index.html'">トップに戻る</button></td>
					<td class="noBorder"><button type="button" id="save" disabled>保存</button></td>
					<td class="noBorder"><button type="button" id="confirm"
							disabled>確定</button></td>
				</tr>
			</table>
			<h3>人事考課システム</h3>
			人事考課サマリ <br> <br>
			<div>
				<span class="noBorder selectBox">部署選択: <select class="table"
					id="depart">
						<option id="all" value="all">&emsp;***部署を選択してください***</option>
						<%
							for (int i = 0; i < departmentList.size(); i++) {
						%><option value=<%=departmentList.get(i).getDepartmentCode()%>
							data-year=<%=departmentList.get(i).getFiscalYear()%>>
							<%=departmentList.get(i).getDepartmentName()%>
						</option>
						<%
							}
						%>
				</select>
				</span> <span class="noBorder selectBox right">年度: <select
					class="table" id="year">
						<%
							for (int i = 0; i < fiscalYearList.size(); i++) {
						%><option value=<%=fiscalYearList.get(i)%>
							data-val=<%=fiscalYearList.get(i)%>>
							<%=fiscalYearList.get(i)%>年度
						</option>
						<%
							}
						%>
				</select><br> <font color="red" size=-2>※過去年度は参照機能のみ</font>
					<button type="button" id="modelButton">年度ごとの評価基準</button>
				</span>
			</div>
		</div>
		<div class="input">
			<form id="inputSummary" name="inputSummary" method="post"
				action="PESummaryServlet" target="iframe">
				<!-- target="iframe" -->
				<table class="table" id="summaryTable">
					<tr align="center">
						<td class="rows1 z3 cols0 cols1" rowspan="2">所属</td>
						<td class="rows1 z3 cols2" rowspan="2">氏名</td>
						<td class="rows1" colspan="2">昨年度</td>
						<td class="rows1" colspan="3">中間考課</td>
						<td class="rows1" colspan="2">最終考課結果</td>
						<td class="rows1">最終考課</td>
						<td class="rows1" colspan="2">役員考課結果</td>
						<td class="rows1">役員考課</td>
					</tr>
					<tr align="center">
						<td class="rows2">職位</td>
						<td class="rows2">ランク</td>
						<td class="rows2">推薦(特別賞与)</td>
						<td class="rows2">コメント(全員記載)</td>
						<td class="rows2">確定(特別賞与)</td>
						<td class="rows2">職位</td>
						<td class="rows2">ランク</td>
						<td class="rows2">コメント(全員記載)</td>
						<td class="rows2">職位</td>
						<td class="rows2">ランク</td>
						<td class="rows2">コメント(全員記載)
					</tr>
					<%
						for (int i = 0; i < summaryList.size(); i++) {
					%><tr class="summaryData"
						data-year=<%=summaryList.get(i).getFiscalYear()%>
						data-depart=<%=summaryList.get(i).getEmp().getDepar().getDepartmentCode()%>
						data-higher=<%=summaryList.get(i).getEmp().getDepar().getHigherDepartmentCode()%>
						data-employee=<%=summaryList.get(i).getEmp().getEmployeeNumber()%>
						data-rookie=<%if (Integer.parseInt(
		summaryList.get(i).getEmp().getDateJoiningCompany().substring(0, 4)) > summaryList.get(i).getFiscalYear()
		|| (Integer.parseInt(
				summaryList.get(i).getEmp().getDateJoiningCompany().substring(0, 4)) == summaryList.get(i)
						.getFiscalYear()
				&& Integer.parseInt(
						summaryList.get(i).getEmp().getDateJoiningCompany().substring(4)) > 1000)) {%>
						<%=1%> <%} else {%> <%=0%> <%}%>>
						<td class="cols1"><%=summaryList.get(i).getEmp().getDepar().getDepartmentAbbreviation()%></td>
						<td class="cols2"><a class="employeeButton" href=""><%=summaryList.get(i).getEmp().getEmployeeName()%></a></td>
						<td align="center"><%=summaryList.get(i).getEmp().getPosi().getPositionAbbreviation()%>
							<input type="hidden" class="nowPositionCode"
							value=<%=summaryList.get(i).getEmp().getPosi().getPositionCode()%> /></td>
						<td class="nowRank" align="right"><%=summaryList.get(i).getRank()%></td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input1" align="center">
								<input type="hidden" name="employeeNumber"
									value=<%=summaryList.get(i).getEmp().getEmployeeNumber()%> />
								<input type="hidden" name="rank"
									value=<%=summaryList.get(i).getRank()%> /> <input
									type="hidden" name="fiscalYear"
									value=<%=summaryList.get(i).getFiscalYear()%> /> <select
									name="bonusRecommendation" class="inputArea">
									<option value=0></option>
									<option value=1
										<%if (summaryList.get(i).getBonusRecommendation() == 1) {%>
										selected <%}%>>-</option>
									<option value=2
										<%if (summaryList.get(i).getBonusRecommendation() == 2) {%>
										selected <%}%>>〇</option>
								</select>
							</div>
							<%
								}
							%><div class="output1" align="center">
								<%
									if (summaryList.get(i).getBonusRecommendation() == 2) {
								%>〇<%
									} else if (summaryList.get(i).getBonusRecommendation() == 1) {
								%>-<%
									}
								%>
							</div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input2">
								<textarea name="middleComment" class="inputArea" rows="2" cols="150"
									<%if (personnelPeriod == 1 || personnelPeriod == 2) {%>
									required <%}%>><%
										if (summaryList.get(i).getMiddleComment() != null) {
									%><%=summaryList.get(i).getMiddleComment()%><%
										}
									%></textarea>
							</div>
							<%
								}
							%><div class="output2">
								<%
									if (summaryList.get(i).getMiddleComment() != null) {
								%><%=summaryList.get(i).getMiddleComment()%>
								<%
									}
								%>
							</div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input3" align="center">
								<select name="bonusComfirm" class="inputArea">
									<option value=0></option>
									<option value=1
										<%if (summaryList.get(i).getBonusComfirm() == 1) {%> selected
										<%}%>>-</option>
									<option value=2
										<%if (summaryList.get(i).getBonusComfirm() == 2) {%> selected
										<%}%>>〇</option>
								</select>
							</div>
							<%
								}
							%><div class="output3" align="center">
								<%
									if (summaryList.get(i).getBonusComfirm() == 2) {
								%>〇<%
									} else if (summaryList.get(i).getBonusComfirm() == 1) {
								%>-<%
									}
								%>
							</div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input4" align="center">
								<select name="lastPositionCode"
									class="inputArea selectLastPositionCode position">
									<option value=0></option>
									<%
										for (PESPosition p : positionList) {
										if (p.getFiscalYear() == nowFiscalYear) {
									%><option value=<%=p.getPositionCode()%>
										data-maxrank=<%=p.getMaxRank()%>
										<%if (p.getPositionCode() == summaryList.get(i).getLastPositionCode()) {%>
										selected <%}%>><%=p.getPositionAbbreviation()%></option>
									<%
										}
									}
									%>
								</select>
							</div>
							<%
								}
							%><div class="output4" align="center">
								<%
									for (PESPosition p : positionList) {
									if (p.getPositionCode() == summaryList.get(i).getLastPositionCode()) {
								%><span class="firmPosition" data-year=<%=p.getFiscalYear()%>><%=p.getPositionAbbreviation()%></span>
								<%
									}
								}
								%>
							</div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input4" align="center">
								<input name="lastRank" type="number" class="inputArea selectLastRank"
									value=<%=summaryList.get(i).getLastRank()%> max=110 min=10
									step=10 maxlength="3"
									onKeyup="this.value=this.value.replace(/[^0-9]+/i,'')" />
							</div>
							<%
								}
							%><div class="output4 lastRank" align="right"><%=summaryList.get(i).getLastRank()%></div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input4">
								<textarea name="lastComment" class="inputArea" rows="2" cols="150"><%
										if (summaryList.get(i).getLastComment() != null) {
									%><%=summaryList.get(i).getLastComment()%><%
										}
									%></textarea>
							</div>
							<%
								}
							%><div class="output4">
								<%
									if (summaryList.get(i).getLastComment() != null) {
								%><%=summaryList.get(i).getLastComment()%>
								<%
									}
								%>
							</div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input5" align="center">
								<select name="appraisalPositionCode"
									class="inputArea selectAppraisalPositionCode position">
									<option value=0></option>
									<%
										for (PESPosition p : positionList) {
										if (p.getFiscalYear() == nowFiscalYear) {
									%><option value=<%=p.getPositionCode()%>
										data-maxrank=<%=p.getMaxRank()%>
										<%if (p.getPositionCode() == summaryList.get(i).getAppraisalPositionCode()) {%>
										selected <%} else {%> disabled <%}%>><%=p.getPositionAbbreviation()%></option>
									<%
										}
									}
									%>
								</select>
							</div>
							<%
								}
							%><div class="output5" align="center">
								<%
									for (PESPosition p : positionList) {
									if (p.getPositionCode() == summaryList.get(i).getAppraisalPositionCode()) {
								%><span class="firmPosition" data-year=<%=p.getFiscalYear()%>><%=p.getPositionAbbreviation()%></span>
								<%
									}
								}
								%>
							</div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%><div class="input5" align="center">
								<table class="noBorder collapse">
									<tr>
										<td rowspan="2" class="noBorder"><input
											name="appraisalRank" class="inputArea selectAppraisalRank rankBox"
											type="text" value=<%=summaryList.get(i).getAppraisalRank()%>
											onKeyup="this.value=this.value.replace(/[^0-9]+/i,'')" /></td>
										<td class="noBorder"><button type="button"
												class="okButton radioButton">〇</button></td>
									</tr>
									<tr>
										<td class="noBorder"><button type="button"
												class="noButton radioButton">Ｘ</button></td>
									</tr>
								</table>
							</div>
							<%
								}
							%><div class="output5 appraisalRank" align="right"><%=summaryList.get(i).getAppraisalRank()%></div>
						</td>
						<td>
							<%
								if (summaryList.get(i).getFiscalYear() == nowFiscalYear) {
							%>
							<div class="input5">
								<textarea name="appraisalComment" class="inputArea" rows="2" cols="150"><%
										if (summaryList.get(i).getAppraisalComment() != null) {
									%><%=summaryList.get(i).getAppraisalComment()%><%
										}
									%></textarea>
								<input type="hidden" name="status" class="status"
									value=<%=summaryList.get(i).getStatus()%> />
							</div>
							<%
								}
							%>
							<div class="output5">
								<%
									if (summaryList.get(i).getAppraisalComment() != null) {
								%><%=summaryList.get(i).getAppraisalComment()%>
								<%
									}
								%>
							</div>
						</td>
					</tr>
					<%
						}
					%><tr align="center">
						<td class="bottom cols1 z3"></td>
						<td class="bottom cols2 z3"></td>
						<td class="bottom">総合力</td>
						<td class="bottom"><input id="totalNowRank" class="rankBox"
							type="text" value="" readonly></td>
						<td class="noBorder bottom"></td>
						<td class="noBorder bottom"></td>
						<td class="noBorder bottom"></td>
						<td class="bottom">総合力</td>
						<td class="bottom"><input id="totalLastRank" class="rankBox"
							type="text" value="" readonly></td>
						<td class="noBorder bottom"></td>
						<td class="bottom">総合力</td>
						<td class="bottom"><input id="totalAppraisalRank"
							class="rankBox" type="text" value="" readonly></td>
						<td class="noBorder bottom"></td>
					</tr>
				</table>
			</form>
			<iframe name="iframe" class="iframe"></iframe>
		</div>
		<br>
		<div class="target" align="center">
			<%
				long salesTarget = 0;
			float salesIncreaseRate = 0;
			float rankIncreaseRate = 0;
			for (int targetYear : fiscalYearList) {
				for (int i = 0; i < annualSalesList.size(); i++) {
					if (annualSalesList.get(i).getFiscalYear() == targetYear) {
				salesTarget = Math.round(annualSalesList.get(i - 1).getSalesResult()
						+ (annualSalesList.get(i - 1).getSalesResult()
								* annualSalesList.get(i).getSalesIncreaseRate() / 100));
				salesIncreaseRate = annualSalesList.get(i).getSalesIncreaseRate();
				rankIncreaseRate = annualSalesList.get(i).getRankIncreaseRate();
					}
				}
			%><table class="targetData collapse" data-year=<%=targetYear%>>
				<tr>
					<td class="noBorder" colspan="2" align="center">
						<table class="noBorder">
							<tr>
								<td class="noBorder">
									<table class="noBorder collapse">
										<tr>
											<td colspan="2" align="center">最終考課結果</td>
										</tr><%
											for (ArrayList<Integer> departmentRank : departmentRankList) {
											if (departmentRank.get(0) == targetYear) {
												for (Department d : departmentList) {
											if (d.getDepartmentCode() == departmentRank.get(1)
													&& d.getFiscalYear() == departmentRank.get(0)
													&& (mostHigherDepartment == operatorDepartment
															|| d.getDepartmentCode() == operatorDepartment)) {
										%><tr>
											<td><%=d.getDepartmentAbbreviation()%></td>
											<td class="departLastRank" align="right"
												data-depart=<%=departmentRank.get(1)%>></td>
										</tr>
										<%
											}
										}
										}
										}
										%><tr>
											<td>総合</td>
											<td class="totalDepartLastRank" align="right"></td>
										</tr>
										<tr>
											<td>新人除く</td>
											<td class="totalDepartLastRankWithoutRookie" align="right"></td>
										</tr>
									</table>
								</td>
								<td class="noBorder">&ensp;</td>
								<td class="noBorder">
									<table class="noBorder collapse">
										<tr>
											<td colspan="2" align="center">役員考課結果</td>
										</tr><%
											for (ArrayList<Integer> departmentRank : departmentRankList) {
											if (departmentRank.get(0) == targetYear) {
												for (Department d : departmentList) {
											if (d.getDepartmentCode() == departmentRank.get(1)
													&& d.getFiscalYear() == departmentRank.get(0)
													&& (mostHigherDepartment == operatorDepartment
															|| d.getDepartmentCode() == operatorDepartment)) {
										%><tr>
											<td><%=d.getDepartmentAbbreviation()%></td>
											<td class="departAppraisalRank" align="right"
												data-depart=<%=departmentRank.get(1)%>></td>
										</tr>
										<%
											}
										}
										}
										}
										%><tr>
											<td>総合</td>
											<td class="totalDepartAppraisalRank" align="right"></td>
										</tr>
										<tr>
											<td>新人除く</td>
											<td class="totalDepartAppraisalRankWithoutRookie"
												align="right"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2"><br>※総合力はSE部門の稼ぎの指標値</td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2">(目標総売上高を各部門の指標値でノルマを作成する。※新人は除く)</td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2"><br>部門目標売上高 =
						エンジニア部門の目標売上高 × 部門総合力 ÷ 全体総合力</td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2"><br>
						<%
							int totalRankWithoutRookie = 0;
							int totalRank = 0;
						for (ArrayList<Integer> departmentRank : departmentRankList) {
							if (departmentRank.get(0) == targetYear) {
								for (Department d : defaultDepartmentList) {
							if (d.getDepartmentCode() == departmentRank.get(1)
									&& d.getFiscalYear() == departmentRank.get(0)) {
								totalRankWithoutRookie += departmentRank.get(2);
								totalRank += departmentRank.get(3);
								if(mostHigherDepartment == operatorDepartment
										|| d.getDepartmentCode() == operatorDepartment) {
						%><%=d.getDepartmentAbbreviation()%>&ensp;<%=departmentRank.get(2)%>、<%
								}
							}
						}
						}
						}
						%>総合&ensp;<%=totalRank%>&ensp; (新人除く&ensp;<%=totalRankWithoutRookie%>)</td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2">目標売上： <%=String.format("%,d", salesTarget)%>
						<%
							if (targetYear == nowFiscalYear) {
						%><input type="hidden" name="ASFiscalYear"
						value=<%=nowFiscalYear%> form="inputSummary" /><input
						type="hidden" name="ASSalesTarget" value=<%=salesTarget%>
						form="inputSummary" />
						<%
							for (AnnualSales a : annualSalesList) {
							if (a.getFiscalYear() == nowFiscalYear) {
						%><input type="hidden" name="ASSalesResult"
						value=<%=a.getSalesResult()%> form="inputSummary" /><input
						type="hidden" name="ASSalesIncreaseRate"
						value=<%=a.getSalesIncreaseRate()%> form="inputSummary" /><input
						type="hidden" name="ASRankIncreaseRate"
						value=<%=a.getRankIncreaseRate()%> form="inputSummary" />
						<%
							}
						}
						}
						%></td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2">
						<%
							for (ArrayList<Integer> departmentRank : departmentRankList) {
							if (departmentRank.get(0) == targetYear) {
								for (Department d : departmentList) {
							if (d.getDepartmentCode() == departmentRank.get(1)
									&& d.getFiscalYear() == departmentRank.get(0)
									&& (mostHigherDepartment == operatorDepartment
											|| d.getDepartmentCode() == operatorDepartment)) {
						%><%=d.getDepartmentAbbreviation()%>目標売上高&emsp;=&emsp;<%=String.format(
		"%,d", salesTarget)%>&emsp;×&emsp;<%=departmentRank.get(2)%>&emsp;÷&emsp;<%=totalRankWithoutRookie%>
						<%
							}
						}
						break;
						}
						}
						%>
					</td>
				</tr>
				<tr>
					<td class="noBorder" colspan="2"><br></td>
				</tr>
				<tr>
					<td class="noBorder"></td>
					<td bgcolor="lightblue">売上高目標</td>
				</tr>
				<%
					long departSalesTotal = 0;
				for (ArrayList<Integer> departmentRank : departmentRankList) {
					if (departmentRank.get(0) == targetYear) {
						for (Department d : defaultDepartmentList) {
					if (d.getDepartmentCode() == departmentRank.get(1) && d.getFiscalYear() == departmentRank.get(0)) {
						String departSalesTargetStr = "0";
						if (departmentRank.get(2) != 0) {
							long departSalesTarget = salesTarget * departmentRank.get(2) / totalRankWithoutRookie;
							departSalesTargetStr = String.format("%,d", departSalesTarget);
							departSalesTotal += departSalesTarget;
						}
						if(mostHigherDepartment == operatorDepartment
								|| d.getDepartmentCode() == operatorDepartment) {
				%><tr>
					<td><%=d.getDepartmentAbbreviation()%></td>
					<td align="right"><%=departSalesTargetStr%></td>
				</tr><%
						}
					}
				}
				}
				}
				%><tr>
					<td>合計</td>
					<td align="right"><%=String.format("%,d", departSalesTotal)%></td>
				</tr>
				<tr>
					<td class="noBorder"><br></td>
				</tr>
				<tr>
					<td class="noBorder"></td>
					<td bgcolor="lightblue">また、課長職以上は、この数値も<%
						if (rankIncreaseRate == (int) rankIncreaseRate) {
					%><%=String.format("%d", (int) rankIncreaseRate)%><%
							} else {
						%><%=String.format("%s", rankIncreaseRate)%><%
							}
						%>%以上高めることも目標とする。
					</td>
				</tr>
				<%
					int departRankTotal = 0;
				for (ArrayList<Integer> departmentRank : departmentRankList) {
					if (departmentRank.get(0) == targetYear) {
						for (Department d : defaultDepartmentList) {
					if (d.getDepartmentCode() == departmentRank.get(1)
							&& d.getFiscalYear() == departmentRank.get(0)) {
						int departmentRankTarget = 0;
						for (int i = 0; i < annualSalesList.size(); i++) {
							if (annualSalesList.get(i).getFiscalYear() == targetYear) {
								departmentRankTarget = (int)(Math.ceil(
									(departmentRank.get(2) + (departmentRank.get(2)
									* annualSalesList.get(i).getRankIncreaseRate() / 100)) / 10) * 10);
								}
						}
						departRankTotal += departmentRankTarget;
						if(mostHigherDepartment == operatorDepartment
								|| d.getDepartmentCode() == operatorDepartment) {
				%><tr class="departRank" data-depart=<%=d.getDepartmentCode()%>>
					<td><%=d.getDepartmentAbbreviation()%></td>
					<td align="center"><font color="red"><%=departmentRankTarget%></font>
						<%
							for (DepartmentAnnualSales das : departmentAnnualSalesList) {
							if (das.getDepartmentCode() == departmentRank.get(1)
							&& das.getFiscalYear() == departmentRank.get(0)
							&& das.getFiscalYear() == nowFiscalYear) {
						%><input type="hidden" name="DSFiscalYear"
						value=<%=das.getFiscalYear()%> form="inputSummary" /> <input
						class="dsdepartmentcode" type="hidden" name="DSDepartmentCode"
						value=<%=das.getDepartmentCode()%> form="inputSummary" /> <input
						type="hidden" name="DSFirstHalfSalesTarget"
						value=<%=das.getFirstHalfSalesTarget()%> form="inputSummary" /> <input
						type="hidden" name="DSFirstHalfSalesResult"
						value=<%=das.getFirstHalfSalesResult()%> form="inputSummary" /> <input
						type="hidden" name="DSSecondHalfSalesTarget"
						value=<%=das.getSecondHalfSalesTarget()%> form="inputSummary" />
						<input type="hidden" name="DSSecondHalfSalesResult"
						value=<%=das.getSecondHalfSalesResult()%> form="inputSummary" />
						<input class="departranktarget" type="hidden"
						name="DSDepartmentRankTarget" value=0 form="inputSummary" /> <input
						class="departrankresult" type="hidden" data-depart=<%=d.getDepartmentCode()%>
						name="DSDepartmentRankResult" value=0 form="inputSummary" />
						<%
							}
						}
						%></td>
				</tr>
				<%
						}
					}
				}
				}
				}
				%><tr>
					<td>合計</td>
					<td align="center"><font color="red"><%=departRankTotal%></font></td>
				</tr>
			</table>
			<%
				}
			%><br>
		</div>
	</div>
	<script type="text/javascript" src="jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="personnelEvaluationSummary.js"></script>
</body>
</html>