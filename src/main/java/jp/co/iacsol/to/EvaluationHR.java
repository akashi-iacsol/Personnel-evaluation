package jp.co.iacsol.to;

//評価/人事評価インスタンス
public class EvaluationHR {

	private int fiscalYear = 0;//年度
	private int employeeNumber = 0; //社員番号
	private int superiorEmployeeNumber = 0; //上長社員番号
	private String superiorComment; //上長コメント
	private int superiorFinish = 0; //上長入力完了
	private String superioStampDirectory;//上長印ディレクトリ
	private int personnelEmployeeNumber = 0; //人事社員番号
	private String personnelComment; //人事コメント
	private int personnelFinish = 0; //人事入力完了
	private String personnelStampDirectory; //人事印ディレクトリ
	private int salesEmployeeNumber = 0;//年度
	private String customerComment; //客先コメント
	private int salesEmployeeFinish = 0; //営業入力完了
	private String salesStampDirectory; //営業印ディレクトリ
	private int newPositionCode = 0; //新役職コード
	private int newPositionClass = 0;//新クラス
	private int personnelStatus = 0; //ステータス
	private String submissionDate; //提出年月日

	/**
	 *
	 */
	public EvaluationHR() {
		super();
	}

	/**
	 * @param superiorComment
	 * @param personnelComment
	 * @param customerComment
	 * 上長、人事、営業コメント
	 */
	public EvaluationHR(String superiorComment, String personnelComment, String customerComment) {
		super();
		this.superiorComment = superiorComment;
		this.personnelComment = personnelComment;
		this.customerComment = customerComment;
	}

	/**
	 * @param superiorComment
	 * @param personnelComment
	 * @param customerComment
	 * @param newPositionCode
	 * @param newPositionClass
	 * 上長、人事、営業コメント、新役職、新クラス
	 */
	public EvaluationHR(String superiorComment, String personnelComment, String customerComment, int newPositionCode,
			int newPositionClass) {
		super();
		this.superiorComment = superiorComment;
		this.personnelComment = personnelComment;
		this.customerComment = customerComment;
		this.newPositionCode = newPositionCode;
		this.newPositionClass = newPositionClass;
	}

	public EvaluationHR(int superiorEmployeeNumber, String superiorComment,
		int superiorFinish, String superiorStampDirectory,
		int personnelEmployeeNumber, String personnelComment,
		int personnelFinish, String personnelStampDirectory,
		int salesEmployeeNumber, String customerComment,
		int salesEmployeeFinish, String salesStampDirectory,
		int newPositionCode, int newPositionClass, int personnelStatus,
		String submissionDate) {
		this.superiorEmployeeNumber = superiorEmployeeNumber;
		this.superiorComment = superiorComment;
		this.superiorFinish = superiorFinish;
		this.superioStampDirectory = superiorStampDirectory;
		this.personnelEmployeeNumber = personnelEmployeeNumber;
		this.personnelComment = personnelComment;
		this.personnelFinish = personnelFinish;
		this.personnelStampDirectory = personnelStampDirectory;
		this.salesEmployeeNumber = salesEmployeeNumber;
		this.customerComment = customerComment;
		this.salesEmployeeFinish = salesEmployeeFinish;
		this.salesStampDirectory = salesStampDirectory;
		this.newPositionCode = newPositionCode;
		this.newPositionClass = newPositionClass;
		this.personnelStatus = personnelStatus;
		this.submissionDate = submissionDate;

	}

	/**
	 * @param personnelStatus
	 * @param submissionDate
	 * 提出年月、ステータス
	 */
	public EvaluationHR(int personnelStatus, String submissionDate) {
		super();
		this.personnelStatus = personnelStatus;
		this.submissionDate = submissionDate;
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
	 * @return superiorEmployeeNumber
	 */
	public int getSuperiorEmployeeNumber() {
		return superiorEmployeeNumber;
	}

	/**
	 * @param superiorEmployeeNumber セットする superiorEmployeeNumber
	 */
	public void setSuperiorEmployeeNumber(int superiorEmployeeNumber) {
		this.superiorEmployeeNumber = superiorEmployeeNumber;
	}

	/**
	 * @return superiorComment
	 */
	public String getSuperiorComment() {
		return superiorComment;
	}

	/**
	 * @param superiorComment セットする superiorComment
	 */
	public void setSuperiorComment(String superiorComment) {
		this.superiorComment = superiorComment;
	}

	/**
	 * @return superiorFinish
	 */
	public int getSuperiorFinish() {
		return superiorFinish;
	}

	/**
	 * @param superiorFinish セットする superiorFinish
	 */
	public void setSuperiorFinish(int superiorFinish) {
		this.superiorFinish = superiorFinish;
	}

	/**
	 * @return personnelEmployeeNumber
	 */
	public int getPersonnelEmployeeNumber() {
		return personnelEmployeeNumber;
	}

	/**
	 * @param personnelEmployeeNumber セットする personnelEmployeeNumber
	 */
	public void setPersonnelEmployeeNumber(int personnelEmployeeNumber) {
		this.personnelEmployeeNumber = personnelEmployeeNumber;
	}

	/**
	 * @return personnelComment
	 */
	public String getPersonnelComment() {
		return personnelComment;
	}

	/**
	 * @param personnelComment セットする personnelComment
	 */
	public void setPersonnelComment(String personnelComment) {
		this.personnelComment = personnelComment;
	}

	/**
	 * @return personnelFinish
	 */
	public int getPersonnelFinish() {
		return personnelFinish;
	}

	/**
	 * @param personnelFinish セットする personnelFinish
	 */
	public void setPersonnelFinish(int personnelFinish) {
		this.personnelFinish = personnelFinish;
	}

	/**
	 * @return salesEmployeeNumber
	 */
	public int getSalesEmployeeNumber() {
		return salesEmployeeNumber;
	}

	/**
	 * @param salesEmployeeNumber セットする salesEmployeeNumber
	 */
	public void setSalesEmployeeNumber(int salesEmployeeNumber) {
		this.salesEmployeeNumber = salesEmployeeNumber;
	}

	/**
	 * @return customerComment
	 */
	public String getCustomerComment() {
		return customerComment;
	}

	/**
	 * @param customerComment セットする customerComment
	 */
	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}

	/**
	 * @return salesEmployeeFinish
	 */
	public int getSalesEmployeeFinish() {
		return salesEmployeeFinish;
	}

	/**
	 * @param salesEmployeeFinish セットする salesEmployeeFinish
	 */
	public void setSalesEmployeeFinish(int salesEmployeeFinish) {
		this.salesEmployeeFinish = salesEmployeeFinish;
	}

	/**
	 * @return newPositionCode
	 */
	public int getNewPositionCode() {
		return newPositionCode;
	}

	/**
	 * @param newPositionCode セットする newPositionCode
	 */
	public void setNewPositionCode(int newPositionCode) {
		this.newPositionCode = newPositionCode;
	}

	/**
	 * @return newPositionClass
	 */
	public int getNewPositionClass() {
		return newPositionClass;
	}

	/**
	 * @param newPositionClass セットする newPositionClass
	 */
	public void setNewPositionClass(int newPositionClass) {
		this.newPositionClass = newPositionClass;
	}

	/**
	 * @return personnelStatus
	 */
	public int getPersonnelStatus() {
		return personnelStatus;
	}

	/**
	 * @param personnelStatus セットする personnelStatus
	 */
	public void setPersonnelStatus(int personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	/**
	 * @return submissionDate
	 */
	public String getSubmissionDate() {
		return submissionDate;
	}

	/**
	 * @param submissionDate セットする submissionDate
	 */
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

}
