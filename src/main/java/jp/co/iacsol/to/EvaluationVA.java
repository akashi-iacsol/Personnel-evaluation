package jp.co.iacsol.to;

//評価／職業能力インスタンス
public class EvaluationVA {

	private int fiscalYear=0;//年度
	private int employeeNumber=0; //社員番号
	private int vaItemCode=0; //項目コード
	private String topComment1; //上段コメント１(300文字まで)
	private String bottomComment1; //下段コメント１(300文字まで)
	private String topComment2; //上段コメント2(300文字まで)
	private String bottomComment2; //下段コメント2(300文字まで)
	private String topComment3; //上段コメント3(300文字まで)
	private String bottomComment3; //下段コメント3(300文字まで)
	private String topComment4; //上段コメント4(300文字まで)
	private String bottomComment4; //下段コメント4(300文字まで)
	private int personnelResult=0; //結果 (０-、１〇、２×)
	private ItemVA  itemVA;  //項目／職業能力オブジェクト
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param vaItemCode
	 * @param topComment1
	 * 年度目標入力
	 */
	public EvaluationVA(int fiscalYear, int employeeNumber, int vaItemCode, String topComment1) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.vaItemCode = vaItemCode;
		this.topComment1 = topComment1;
	}
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param vaItemCode
	 * @param topComment1
	 * @param bottomComment1
	 * 年度目標表示
	 */
	public EvaluationVA(int fiscalYear, int employeeNumber, int vaItemCode, String topComment1, String bottomComment1) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.vaItemCode = vaItemCode;
		this.topComment1 = topComment1;
		this.bottomComment1 = bottomComment1;
	}
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param vaItemCode
	 * @param topComment1
	 * @param bottomComment1
	 * @param topComment2
	 * @param bottomComment2
	 * 中間考課
	 */
	public EvaluationVA(int fiscalYear, int employeeNumber, int vaItemCode, String topComment1, String bottomComment1,
			String topComment2, String bottomComment2) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.vaItemCode = vaItemCode;
		this.topComment1 = topComment1;
		this.bottomComment1 = bottomComment1;
		this.topComment2 = topComment2;
		this.bottomComment2 = bottomComment2;
	}
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param vaItemCode
	 * @param topComment1
	 * @param bottomComment1
	 * @param topComment2
	 * @param bottomComment2
	 * @param topComment3
	 * @param personnelResult
	 * 最終考課入力
	 */
	public EvaluationVA(int fiscalYear, int employeeNumber, int vaItemCode, String topComment1, String bottomComment1,
			String topComment2, String bottomComment2, String topComment3, int personnelResult) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.vaItemCode = vaItemCode;
		this.topComment1 = topComment1;
		this.bottomComment1 = bottomComment1;
		this.topComment2 = topComment2;
		this.bottomComment2 = bottomComment2;
		this.topComment3 = topComment3;
		this.personnelResult = personnelResult;
	}
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param vaItemCode
	 * @param topComment1
	 * @param bottomComment1
	 * @param topComment2
	 * @param bottomComment2
	 * @param topComment3
	 * @param bottomComment3
	 * @param personnelResult
	 * 最終考課表示
	 */
	public EvaluationVA(int fiscalYear, int employeeNumber, int vaItemCode, String topComment1, String bottomComment1,
			String topComment2, String bottomComment2, String topComment3, String bottomComment3, int personnelResult) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.vaItemCode = vaItemCode;
		this.topComment1 = topComment1;
		this.bottomComment1 = bottomComment1;
		this.topComment2 = topComment2;
		this.bottomComment2 = bottomComment2;
		this.topComment3 = topComment3;
		this.bottomComment3 = bottomComment3;
		this.personnelResult = personnelResult;
	}
	/**
	 * @param fiscalYear
	 * @param employeeNumber
	 * @param vaItemCode
	 * @param topComment1
	 * @param bottomComment1
	 * @param topComment2
	 * @param bottomComment2
	 * @param topComment3
	 * @param bottomComment3
	 * @param topComment4
	 * @param bottomComment4
	 * @param personnelResult
	 * 課長代理以上
	 */
	public EvaluationVA(int fiscalYear, int employeeNumber, int vaItemCode, String topComment1, String bottomComment1,
			String topComment2, String bottomComment2, String topComment3, String bottomComment3, String topComment4,
			String bottomComment4, int personnelResult) {
		super();
		this.fiscalYear = fiscalYear;
		this.employeeNumber = employeeNumber;
		this.vaItemCode = vaItemCode;
		this.topComment1 = topComment1;
		this.bottomComment1 = bottomComment1;
		this.topComment2 = topComment2;
		this.bottomComment2 = bottomComment2;
		this.topComment3 = topComment3;
		this.bottomComment3 = bottomComment3;
		this.topComment4 = topComment4;
		this.bottomComment4 = bottomComment4;
		this.personnelResult = personnelResult;
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
	 * @return topComment1
	 */
	public String getTopComment1() {
		return topComment1;
	}
	/**
	 * @param topComment1 セットする topComment1
	 */
	public void setTopComment1(String topComment1) {
		this.topComment1 = topComment1;
	}
	/**
	 * @return bottomComment1
	 */
	public String getBottomComment1() {
		return bottomComment1;
	}
	/**
	 * @param bottomComment1 セットする bottomComment1
	 */
	public void setBottomComment1(String bottomComment1) {
		this.bottomComment1 = bottomComment1;
	}
	/**
	 * @return topComment2
	 */
	public String getTopComment2() {
		return topComment2;
	}
	/**
	 * @param topComment2 セットする topComment2
	 */
	public void setTopComment2(String topComment2) {
		this.topComment2 = topComment2;
	}
	/**
	 * @return bottomComment2
	 */
	public String getBottomComment2() {
		return bottomComment2;
	}
	/**
	 * @param bottomComment2 セットする bottomComment2
	 */
	public void setBottomComment2(String bottomComment2) {
		this.bottomComment2 = bottomComment2;
	}
	/**
	 * @return topComment3
	 */
	public String getTopComment3() {
		return topComment3;
	}
	/**
	 * @param topComment3 セットする topComment3
	 */
	public void setTopComment3(String topComment3) {
		this.topComment3 = topComment3;
	}
	/**
	 * @return bottomComment3
	 */
	public String getBottomComment3() {
		return bottomComment3;
	}
	/**
	 * @param bottomComment3 セットする bottomComment3
	 */
	public void setBottomComment3(String bottomComment3) {
		this.bottomComment3 = bottomComment3;
	}
	/**
	 * @return topComment4
	 */
	public String getTopComment4() {
		return topComment4;
	}
	/**
	 * @param topComment4 セットする topComment4
	 */
	public void setTopComment4(String topComment4) {
		this.topComment4 = topComment4;
	}
	/**
	 * @return bottomComment4
	 */
	public String getBottomComment4() {
		return bottomComment4;
	}
	/**
	 * @param bottomComment4 セットする bottomComment4
	 */
	public void setBottomComment4(String bottomComment4) {
		this.bottomComment4 = bottomComment4;
	}
	/**
	 * @return personnelResult
	 */
	public int getPersonnelResult() {
		return personnelResult;
	}
	/**
	 * @param personnelResult セットする personnelResult
	 */
	public void setPersonnelResult(int personnelResult) {
		this.personnelResult = personnelResult;
	}
	/**
	 * @return itemVA
	 */
	public ItemVA getItemVA() {
		return itemVA;
	}
	/**
	 * @param itemVA セットする itemVA
	 */
	public void setItemVA(ItemVA itemVA) {
		this.itemVA = itemVA;
	}

}
