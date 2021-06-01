package jp.co.iacsol.to;

public class OtherEvaluationTO {
	private int otherEvaluation;
	private String employeeName;

	public OtherEvaluationTO(int otherEvaluation, String employeeName) {
		this.otherEvaluation = otherEvaluation;
		this.employeeName = employeeName;
	}

	public int getOtherEvaluation() {
		return otherEvaluation;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setOtherEvaluation(int otherEvaluation) {
		this.otherEvaluation = otherEvaluation;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
