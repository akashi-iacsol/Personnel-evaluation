package jp.co.iacsol.to;

//評価／態度・業務遂行能力インスタンス
public class EvaluationAttirude {

	private int fiscalYear=0;//年度
	private int employeeNumber=0; //社員番号
	private int atItemCode=0; //項目コード
	private int selfEvaluation; //自己評価   (数値1〜5)
	private int otherEvaluation=0; //評価点数
	private String selfEvaluationReason; //本人評価理由(文字300まで)
	private String otherEvaluationReason; //担当者評価理由(文字300まで)
	private ItemAttitude itemAttitude; //評価／態度・業務遂行能力オブジェクト
	/**
	 * @param fiscalYear
	 * @param atItemCode
	 * @param itemAttitude
	 * 中間考課前に
	 */
	public EvaluationAttirude(int fiscalYear, int atItemCode, ItemAttitude itemAttitude) {
		super();
		this.fiscalYear = fiscalYear;
		this.atItemCode = atItemCode;
		this.itemAttitude = itemAttitude;
	}
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param atItemCode
	 * @param selfEvaluation
	 * @param selfEvaluationReason
	 * 追加するため
	 */
	public EvaluationAttirude(int fiscalYear, int employeeNumber, int atItemCode, int selfEvaluation,
			String selfEvaluationReason) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.atItemCode = atItemCode;
		this.selfEvaluation = selfEvaluation;
		this.selfEvaluationReason = selfEvaluationReason;
	}
	/**
	 * @param selfEvaluation
	 * @param otherEvaluation
	 * @param selfEvaluationReason
	 * @param otherEvaluationReason
	 * @param itemAttitude
	 * 表示するため
	 */
	public EvaluationAttirude(int selfEvaluation, int otherEvaluation, String selfEvaluationReason,
			String otherEvaluationReason, ItemAttitude itemAttitude) {
		super();
		this.selfEvaluation = selfEvaluation;
		this.otherEvaluation = otherEvaluation;
		this.selfEvaluationReason = selfEvaluationReason;
		this.otherEvaluationReason = otherEvaluationReason;
		this.itemAttitude = itemAttitude;
	}
	/**
	 * @return fiscalYear
	 */
	public int getFiscalYear() {
		return fiscalYear;
	}
	/**
	 * @param fiscalYear セットする fiscalYear
	 */
	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	/**
	 * @return employeeNumber
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	/**
	 * @param employeeNumber セットする employeeNumber
	 */
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	/**
	 * @return atItemCode
	 */
	public int getAtItemCode() {
		return atItemCode;
	}
	/**
	 * @param atItemCode セットする atItemCode
	 */
	public void setAtItemCode(int atItemCode) {
		this.atItemCode = atItemCode;
	}
	/**
	 * @return selfEvaluation
	 */
	public int getSelfEvaluation() {
		return selfEvaluation;
	}
	/**
	 * @param selfEvaluation セットする selfEvaluation
	 */
	public void setSelfEvaluation(int selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
	}
	/**
	 * @return otherEvaluation
	 */
	public int getOtherEvaluation() {
		return otherEvaluation;
	}
	/**
	 * @param otherEvaluation セットする otherEvaluation
	 */
	public void setOtherEvaluation(int otherEvaluation) {
		this.otherEvaluation = otherEvaluation;
	}
	/**
	 * @return selfEvaluationReason
	 */
	public String getSelfEvaluationReason() {
		return selfEvaluationReason;
	}
	/**
	 * @param selfEvaluationReason セットする selfEvaluationReason
	 */
	public void setSelfEvaluationReason(String selfEvaluationReason) {
		this.selfEvaluationReason = selfEvaluationReason;
	}
	/**
	 * @return otherEvaluationReason
	 */
	public String getOtherEvaluationReason() {
		return otherEvaluationReason;
	}
	/**
	 * @param otherEvaluationReason セットする otherEvaluationReason
	 */
	public void setOtherEvaluationReason(String otherEvaluationReason) {
		this.otherEvaluationReason = otherEvaluationReason;
	}
	/**
	 * @return itemAttitude
	 */
	public ItemAttitude getItemAttitude() {
		return itemAttitude;
	}
	/**
	 * @param itemAttitude セットする itemAttitude
	 */
	public void setItemAttitude(ItemAttitude itemAttitude) {
		this.itemAttitude = itemAttitude;
	}

}
