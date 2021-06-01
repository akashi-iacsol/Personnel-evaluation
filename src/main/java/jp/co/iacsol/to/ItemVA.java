package jp.co.iacsol.to;

//項目／職業能力
public class ItemVA {

	private int vaItemCode=0;//項目コード
	private String yearTarget; //年度目標
	/**
	 * @param vaItemCode
	 * @param yearTarget
	 */
	public ItemVA(int vaItemCode, String yearTarget) {
		super();
		this.vaItemCode = vaItemCode;
		this.yearTarget = yearTarget;
	}

	/**
	 * @param vaItemCode
	 */
	public ItemVA(int vaItemCode) {
		super();
		this.vaItemCode = vaItemCode;
	}

	/**
	 * @return vaItemCode
	 */
	public int getVaItemCode() {
		return vaItemCode;
	}
	/**
	 * @param vaItemCode セットする vaItemCode
	 */
	public void setVaItemCode(int vaItemCode) {
		this.vaItemCode = vaItemCode;
	}
	/**
	 * @return yearTarget
	 */
	public String getYearTarget() {
		return yearTarget;
	}
	/**
	 * @param yearTarget セットする yearTarget
	 */
	public void setYearTarget(String yearTarget) {
		this.yearTarget = yearTarget;
	}
}
