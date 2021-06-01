package jp.co.iacsol.to;

public class PESPosition {
	private int fiscalYear = 0;
	private int positionCode = 0;
	private String positionName;
	private String positionAbbreviation;
	private int maxRank = 0;

	//デフォルトコンストラクタ
	public PESPosition() {
	}

	public PESPosition(int fiscalYear, int positionCode, String positionName, String positionAbbreviation, int maxRank) {
		this.fiscalYear = fiscalYear;
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.positionAbbreviation = positionAbbreviation;
		this.maxRank = maxRank;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public int getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(int positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionAbbreviation() {
		return positionAbbreviation;
	}

	public void setPositionAbbreviation(String positionAbbreviation) {
		this.positionAbbreviation = positionAbbreviation;
	}

	public int getMaxRank() {
		return maxRank;
	}

	public void setMaxRank(int maxRank) {
		this.maxRank = maxRank;
	}
}
