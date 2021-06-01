package jp.co.iacsol.to;

public class AnnualSales {
	private int fiscalYear = 0;
	private long salesTarget = 0;
	private long salesResult = 0;
	private float salesIncreaseRate = 0;
	private float rankIncreaseRate = 0;

	/**
	 * 年度, 売上目標, 売上結果, 売上目標上昇率, ランク目標上昇率
	 * @param fiscalYear
	 * @param salesTarget
	 * @param salesResult
	 * @param salesIncreaseRate
	 * @param rankIncreaseRate
	 */
	public AnnualSales(int fiscalYear, long salesTarget, long salesResult, float salesIncreaseRate,
			float rankIncreaseRate) {

		this.fiscalYear = fiscalYear;
		this.salesTarget = salesTarget;
		this.salesResult = salesResult;
		this.salesIncreaseRate = salesIncreaseRate;
		this.rankIncreaseRate = rankIncreaseRate;
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
	 * 売上目標
	 * @return
	 */
	public long getSalesTarget() {
		return salesTarget;
	}

	/**
	 * 売上目標
	 * @param salesTarget
	 */
	public void setSalesTarget(long salesTarget) {
		this.salesTarget = salesTarget;
	}

	/**
	 * 売上結果
	 * @return
	 */
	public long getSalesResult() {
		return salesResult;
	}

	/**
	 * 売上結果
	 * @param salesResult
	 */
	public void setSalesResult(long salesResult) {
		this.salesResult = salesResult;
	}

	/**
	 * 売上目標上昇率
	 * @return
	 */
	public float getSalesIncreaseRate() {
		return salesIncreaseRate;
	}

	/**
	 * 売上目標上昇率
	 * @param salesIncreaseRate
	 */
	public void setSalesIncreaseRate(float salesIncreaseRate) {
		this.salesIncreaseRate = salesIncreaseRate;
	}

	/**
	 * ランク目標上昇率
	 * @return
	 */
	public float getRankIncreaseRate() {
		return rankIncreaseRate;
	}

	/**
	 * ランク目標上昇率
	 * @param rankIncreaseRate
	 */
	public void setRankIncreaseRate(float rankIncreaseRate) {
		this.rankIncreaseRate = rankIncreaseRate;
	}
}
