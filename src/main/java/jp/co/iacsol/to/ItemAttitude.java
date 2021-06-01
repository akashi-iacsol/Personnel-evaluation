package jp.co.iacsol.to;

//項目／態度・業務遂行能力インスタンス
public class ItemAttitude {

	private int atItemCode=0; //項目コード
	private String evaluationCategory; //評価区分
	private String evaluationItem; //評価項目
	private String evaluationCriteria; //評価基準
	private int evaluator=0; //評価担当者  (0上長、1人事)
	private int evaluationPeriod=0; //評価期間(0中間考課、1最終考課)
	/**
	 * @param atItemCode
	 * @param evaluationCategory
	 * @param evaluationItem
	 * @param evaluationCriteria
	 * @param evaluator
	 * @param evaluationPeriod
	 */
	public ItemAttitude(int atItemCode, String evaluationCategory, String evaluationItem, String evaluationCriteria,
			int evaluator, int evaluationPeriod) {
		super();
		this.atItemCode = atItemCode;
		this.evaluationCategory = evaluationCategory;
		this.evaluationItem = evaluationItem;
		this.evaluationCriteria = evaluationCriteria;
		this.evaluator = evaluator;
		this.evaluationPeriod = evaluationPeriod;
	}
	/**
	 * @param atItemCode
	 * @param evaluationItem
	 * @param evaluationCriteria
	 * @param evaluator
	 * @param evaluationPeriod
	 */
	public ItemAttitude(int atItemCode, String evaluationItem, String evaluationCriteria, int evaluator,
			int evaluationPeriod) {
		super();
		this.atItemCode = atItemCode;
		this.evaluationItem = evaluationItem;
		this.evaluationCriteria = evaluationCriteria;
		this.evaluator = evaluator;
		this.evaluationPeriod = evaluationPeriod;
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
	 * @return evaluationCategory
	 */
	public String getEvaluationCategory() {
		return evaluationCategory;
	}
	/**
	 * @param evaluationCategory セットする evaluationCategory
	 */
	public void setEvaluationCategory(String evaluationCategory) {
		this.evaluationCategory = evaluationCategory;
	}
	/**
	 * @return evaluationItem
	 */
	public String getEvaluationItem() {
		return evaluationItem;
	}
	/**
	 * @param evaluationItem セットする evaluationItem
	 */
	public void setEvaluationItem(String evaluationItem) {
		this.evaluationItem = evaluationItem;
	}
	/**
	 * @return evaluationCriteria
	 */
	public String getEvaluationCriteria() {
		return evaluationCriteria;
	}
	/**
	 * @param evaluationCriteria セットする evaluationCriteria
	 */
	public void setEvaluationCriteria(String evaluationCriteria) {
		this.evaluationCriteria = evaluationCriteria;
	}
	/**
	 * @return evaluator
	 */
	public int getEvaluator() {
		return evaluator;
	}
	/**
	 * @param evaluator セットする evaluator
	 */
	public void setEvaluator(int evaluator) {
		this.evaluator = evaluator;
	}
	/**
	 * @return evaluationPeriod
	 */
	public int getEvaluationPeriod() {
		return evaluationPeriod;
	}
	/**
	 * @param evaluationPeriod セットする evaluationPeriod
	 */
	public void setEvaluationPeriod(int evaluationPeriod) {
		this.evaluationPeriod = evaluationPeriod;
	}

}
