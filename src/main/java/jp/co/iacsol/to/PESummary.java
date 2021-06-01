package jp.co.iacsol.to;

public class PESummary {
	private Employee emp = null;
	private int rank = 0;
	private int fiscalYear = 0;
	private int bonusRecommendation = 0;
	private String middleComment = null;
	private int bonusComfirm = 0;
	private int lastPositionCode = 0;
	private int lastRank = 0;
	private String lastComment = null;
	private int appraisalPositionCode = 0;
	private int appraisalRank = 0;
	private String appraisalComment = null;
	private int status = 0;

	/**
	 * デフォルトコンストラクタ
	 */
	public PESummary() {
	}

	/**
	 * 社員マスタオブジェクト, 昨年度ランク, 年度, 賞与推薦, コメント[中間], 賞与確定, 役職コード[最終], ランク[最終], コメント[最終], 役職コード[役員], ランク[役員], コメント[役員], ステータス
	 * @param emp
	 * @param rank
	 * @param fiscalYear
	 * @param bonusRecommendation
	 * @param middleComment
	 * @param bonusComfirm
	 * @param lastPositionCode
	 * @param lastRank
	 * @param lastComment
	 * @param appraisalPositionCode
	 * @param appraisalRank
	 * @param appraisalComment
	 * @param status
	 */
	public PESummary(Employee emp, int rank, int fiscalYear, int bonusRecommendation, String middleComment,
			int bonusComfirm, int lastPositionCode, int lastRank, String lastComment, int appraisalPositionCode,
			int appraisalRank, String appraisalComment, int status) {

		this.emp = emp;
		this.rank = rank;
		this.fiscalYear = fiscalYear;
		this.bonusRecommendation = bonusRecommendation;
		this.middleComment = middleComment;
		this.bonusComfirm = bonusComfirm;
		this.lastPositionCode = lastPositionCode;
		this.lastRank = lastRank;
		this.lastComment = lastComment;
		this.appraisalPositionCode = appraisalPositionCode;
		this.appraisalRank = appraisalRank;
		this.appraisalComment = appraisalComment;
		this.status = status;
	}

	/**
	 * 役職コード[最終], ランク[最終], 役職コード[役員], ランク[役員]
	 * @param lastPositionCode
	 * @param lastRank
	 * @param appraisalPositionCode
	 * @param appraisalRank
	 */
	public PESummary(int lastPositionCode, int lastRank, int appraisalPositionCode,
			int appraisalRank) {
		this.lastPositionCode = lastPositionCode;
		this.lastRank = lastRank;
		this.appraisalPositionCode = appraisalPositionCode;
		this.appraisalRank = appraisalRank;
	}

	/**
	 * 社員マスタオブジェクト
	 * 	 * @return
	 */
	public Employee getEmp() {
		return emp;
	}

	/**
	 * 社員マスタオブジェクト
	 * @param emp
	 */
	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	/**
	 * 昨年度ランク
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * 昨年度ランク
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
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
	 * 賞与推薦
	 * @return
	 */
	public int getBonusRecommendation() {
		return bonusRecommendation;
	}

	/**
	 * 賞与推薦
	 * @param bonusRecommendation
	 */
	public void setBonusRecommendation(int bonusRecommendation) {
		this.bonusRecommendation = bonusRecommendation;
	}

	/**
	 * コメント[中間]
	 * @return
	 */
	public String getMiddleComment() {
		return middleComment;
	}

	/**
	 * コメント[中間]
	 * @param middleComment
	 */
	public void setMiddleComment(String middleComment) {
		this.middleComment = middleComment;
	}

	/**
	 * 賞与確定
	 * @return
	 */
	public int getBonusComfirm() {
		return bonusComfirm;
	}

	/**
	 * 賞与確定
	 * @param bonusComfirm
	 */
	public void setBonusComfirm(int bonusComfirm) {
		this.bonusComfirm = bonusComfirm;
	}

	/**
	 * 役職コード[最終]
	 * @return
	 */
	public int getLastPositionCode() {
		return lastPositionCode;
	}

	/**
	 * 役職コード[最終]
	 * @param lastPositionCode
	 */
	public void setLastPositionCode(int lastPositionCode) {
		this.lastPositionCode = lastPositionCode;
	}

	/**
	 * ランク[最終]
	 * @return
	 */
	public int getLastRank() {
		return lastRank;
	}

	/**
	 * ランク[最終]
	 * @param lastRank
	 */
	public void setLastRank(int lastRank) {
		this.lastRank = lastRank;
	}

	/**
	 * コメント[最終]
	 * @return
	 */
	public String getLastComment() {
		return lastComment;
	}

	/**
	 * コメント[最終]
	 * @param lastComment
	 */
	public void setLastComment(String lastComment) {
		this.lastComment = lastComment;
	}

	/**
	 * 役職コード[役員]
	 * @return
	 */
	public int getAppraisalPositionCode() {
		return appraisalPositionCode;
	}

	/**
	 * 役職コード[役員]
	 * @param appraisalPositionCode
	 */
	public void setAppraisalPositionCode(int appraisalPositionCode) {
		this.appraisalPositionCode = appraisalPositionCode;
	}

	/**
	 * ランク[役員]
	 * @return
	 */
	public int getAppraisalRank() {
		return appraisalRank;
	}

	/**
	 * ランク[役員]
	 * @param appraisalRank
	 */
	public void setAppraisalRank(int appraisalRank) {
		this.appraisalRank = appraisalRank;
	}

	/**
	 * コメント[役員]
	 * @return
	 */
	public String getAppraisalComment() {
		return appraisalComment;
	}

	/**
	 * コメント[役員]
	 * @param appraisalComment
	 */
	public void setAppraisalComment(String appraisalComment) {
		this.appraisalComment = appraisalComment;
	}

	/**
	 * ステータス
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * ステータス
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
