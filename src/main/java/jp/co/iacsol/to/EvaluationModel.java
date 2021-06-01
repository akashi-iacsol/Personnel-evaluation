package jp.co.iacsol.to;

public class EvaluationModel {

	private int fiscalYear = 0;
	private int rank = 0;
	private String positionAbbreviation = null;
	private int positionClass = 0;
	private int yearsExperienceStart = 0;
	private int yearsExperienceEnd= 0;
	private String workConcept = null;
	private String professionalAttitude = null;
	private String managementAbility = null;
	private String technologyAbility = null;
	private String executionAbility = null;

	public EvaluationModel(int fiscalYear,int rank,String positionAbbreviation,
			        int positionClass,int yearsExperienceStart,int yearsExperienceEnd,
			        String workConcept,String professionalAttitude,String managementAbility,
			        String technologyAbility,String executionAbility){

		this.fiscalYear = fiscalYear ;
		this.rank  = rank ;
		this.positionAbbreviation = positionAbbreviation;
		this.positionClass = positionClass;
		this.yearsExperienceStart = yearsExperienceStart;
		this.yearsExperienceEnd = yearsExperienceEnd;
		this.workConcept = workConcept;
        this.professionalAttitude = professionalAttitude;
        this.managementAbility = managementAbility;
        this.technologyAbility = technologyAbility;
        this.executionAbility = executionAbility;

      }

	public int getFiscalYear() {
		return fiscalYear;
	}
	public int getRank() {
		return rank;
	}
	public String getPositionAbbreviation() {
		return positionAbbreviation;
	}
	public int getPositionClass() {
		return positionClass;
		}
	public int getYearsExperienceStart() {
		return yearsExperienceStart;
	}
	public int getYearsExperienceEnd() {
		return yearsExperienceEnd;
	}
	public String getWorkConcept() {
		return workConcept;
	}
	public String getProfessionalAttitude() {
		return professionalAttitude;
	}
	public String getManagementAbility() {
		return managementAbility;
	}
	public String getTechnologyAbility() {
		return technologyAbility;
	}
	public String getExecutionAbility() {
		return executionAbility;
	}
	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setPositionAbbreviation(String positionAbbreviation) {
		this.positionAbbreviation = positionAbbreviation;
	}
	public void setPositionClass(int positionClass) {
		this.positionClass = positionClass;
	}
	public void setYearsExperienceStart(int yearsExperienceStart) {
		this.yearsExperienceStart = yearsExperienceStart;
	}
	public void setYearsExperienceEnd(int yearsExperienceEnd) {
		this.yearsExperienceEnd = yearsExperienceEnd;
	}
	public void setWorkConcept(String workConcept) {
		this.workConcept = workConcept;
	}
	public void setProfessionalAttitude(String professionalAttitude) {
		this.professionalAttitude = professionalAttitude;
	}
	public void setManagementAbility(String managementAbility) {
		this.managementAbility = managementAbility;
	}
	public void setTechnologyAbility(String technologyAbility) {
		this.technologyAbility = technologyAbility;
	}
	public void setExecutionAbility(String executionAbility) {
		this.executionAbility = executionAbility;
	}

}
