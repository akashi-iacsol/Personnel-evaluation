package jp.co.iacsol.to;

public class Position {
	private int fiscalYear = 0;
	private int positionCode = 0;
	private String positionName;
	private String positionAbbreviation;

	//デフォルトコンストラクタ
	public Position() {
	}

	public Position(int fiscalYear, int positionCode, String positionName, String positionAbbreviation) {
		this.fiscalYear = fiscalYear;
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.positionAbbreviation = positionAbbreviation;
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
}
