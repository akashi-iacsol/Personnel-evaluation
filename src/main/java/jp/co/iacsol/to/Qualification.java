package jp.co.iacsol.to;

import java.util.ArrayList;

public class Qualification {
	private int credentialsCode = 0;
	private String credentialsName;
	private ArrayList<String> qualificationList = new ArrayList<>();
	private String credentialsExpiration;
	private String credentialsOverview;
	private Employee emp;
	private String deptName;
	private String acquisitionDate;
	private Credential cre;

	public Qualification(int credentialsCode,String credentialsName,ArrayList<String>qualificationList,
	String credentialsExpiration,String credentialsOverview,Employee emp){
	this.credentialsCode=credentialsCode;
	this.credentialsName=credentialsName;
	this.qualificationList=qualificationList;
	this.credentialsExpiration=credentialsExpiration;
	this.credentialsOverview=credentialsOverview;
	this.emp=emp;

	}
	public Qualification(Employee emp, String deptName, String credentialsName, String acquisitionDate) {

	this.emp=emp;
	this.deptName=deptName;
	this.credentialsName=credentialsName;
	this.acquisitionDate=acquisitionDate;
	}

	public int getCredentialsCode() {
		return credentialsCode;
	}

	public void setCredentialsCode(int credentialsCode) {
		this.credentialsCode = credentialsCode;
	}

	public String getCredentialsName() {
		return credentialsName;
	}

	public void setCredentialsName(String credentialsName) {
		this.credentialsName = credentialsName;
	}

	public ArrayList<String> getQualificationList() {
		return this.qualificationList;
	}

	public void setQualificationList(ArrayList<String> qualificationList) {
		this.qualificationList = qualificationList;
	}

	public String getCredentialsExpiration() {
		return credentialsExpiration;
	}

	public void setCredentialsExpiration(String credentialsExpiration) {
		this.credentialsExpiration = credentialsExpiration;
	}

	public String getCredentialsOverview() {
		return credentialsOverview;
	}

	public void setCredentialsOverview(String credentialsOverview) {
		this.credentialsOverview = credentialsOverview;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp=emp;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName=deptName;
	}
	public String getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
}