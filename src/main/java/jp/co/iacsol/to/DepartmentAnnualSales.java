package jp.co.iacsol.to;

public class DepartmentAnnualSales {
	private int fiscalYear = 0;
	private int departmentCode = 0;
	private long firstHalfSalesTarget = 0;
	private long firstHalfSalesResult = 0;
	private long secondHalfSalesTarget = 0;
	private long secondHalfSalesResult = 0;
	private int departmentRankTarget = 0;
	private int departmentRankResult = 0;

	/**
	 * 	年度, 部門コード, 上期売上目標, 上期売上結果, 下期売上目標, 下期売上結果, 部門ランク目標, 部門ランク結果
	 * @param fiscalYear
	 * @param departmentCode
	 * @param firstHalfSalesTarget
	 * @param firstHalfSalesResult
	 * @param secondHalfSalesTarget
	 * @param secondHalfSalesResult
	 * @param departmentRankTarget
	 * @param departmentRankResult
	 */
	public DepartmentAnnualSales(int fiscalYear, int departmentCode, long firstHalfSalesTarget,
			long firstHalfSalesResult, long secondHalfSalesTarget, long secondHalfSalesResult, int departmentRankTarget,
			int departmentRankResult) {

		this.fiscalYear = fiscalYear;
		this.departmentCode = departmentCode;
		this.firstHalfSalesTarget = firstHalfSalesTarget;
		this.firstHalfSalesResult = firstHalfSalesResult;
		this.secondHalfSalesTarget = secondHalfSalesTarget;
		this.secondHalfSalesResult = secondHalfSalesResult;
		this.departmentRankTarget = departmentRankTarget;
		this.departmentRankResult = departmentRankResult;
	}

	/**
	 * 年度
	 * @return
	 */
	public int getFiscalYear() {
		return fiscalYear;
	}

	/**
	 * 年度
	 * @param fiscalYear
	 */
	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	/**
	 * 部門コード
	 * @return
	 */
	public int getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * 部門コード
	 * @param departmentCode
	 */
	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * 上期売上目標
	 * @return
	 */
	public long getFirstHalfSalesTarget() {
		return firstHalfSalesTarget;
	}

	/**
	 * 上期売上目標
	 * @param firstHalfSalesTarget
	 */
	public void setFirstHalfSalesTarget(long firstHalfSalesTarget) {
		this.firstHalfSalesTarget = firstHalfSalesTarget;
	}

	/**
	 * 上期売上結果
	 * @return
	 */
	public long getFirstHalfSalesResult() {
		return firstHalfSalesResult;
	}

	/**
	 * 上期売上結果
	 * @param firstHalfSalesResult
	 */
	public void setFirstHalfSalesResult(long firstHalfSalesResult) {
		this.firstHalfSalesResult = firstHalfSalesResult;
	}

	/**
	 * 下期売上目標
	 * @return
	 */
	public long getSecondHalfSalesTarget() {
		return secondHalfSalesTarget;
	}

	/**
	 * 下期売上目標
	 * @param secondHalfSalesTarget
	 */
	public void setSecondHalfSalesTarget(long secondHalfSalesTarget) {
		this.secondHalfSalesTarget = secondHalfSalesTarget;
	}

	/**
	 * 下期売上結果
	 * @return
	 */
	public long getSecondHalfSalesResult() {
		return secondHalfSalesResult;
	}

	/**
	 * 下期売上結果
	 * @param secondHalfSalesResult
	 */
	public void setSecondHalfSalesResult(long secondHalfSalesResult) {
		this.secondHalfSalesResult = secondHalfSalesResult;
	}

	/**
	 * 部門ランク目標
	 * @return
	 */
	public int getDepartmentRankTarget() {
		return departmentRankTarget;
	}

	/**
	 * 部門ランク目標
	 * @param departmentRankTarget
	 */
	public void setDepartmentRankTarget(int departmentRankTarget) {
		this.departmentRankTarget = departmentRankTarget;
	}

	/**
	 * 部門ランク結果
	 * @return
	 */
	public int getDepartmentRankResult() {
		return departmentRankResult;
	}

	/**
	 * 部門ランク結果
	 * @param departmentRankResult
	 */
	public void setDepartmentRankResult(int departmentRankResult) {
		this.departmentRankResult = departmentRankResult;
	}
}
