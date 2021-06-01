package jp.co.iacsol.to;

public class Credential {

	private int	 credentialsCode=0;
	private String credentialsName=null;
	private int credentialsExpiration=0;
	private String credentialsOverview=null;

	Credential(int	 credentialsCode,String credentialsName,
			String credentialsOverview,int credentialsExpiration)
	{
	this.credentialsCode=credentialsCode;
	this.credentialsName=credentialsName;
	this.credentialsExpiration=credentialsExpiration;
	this.credentialsOverview=credentialsOverview;

	}
//資格名取得用
	public Credential(int credentialsCode,String credentialsName ){
		this.credentialsCode=credentialsCode;
		this.credentialsName=credentialsName;
	}


	public Credential() {

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

	public int  getCredentialsExpiration() {
		return credentialsExpiration;
	}

	public void setCredentialsExpiration(int  credentialsExpiration) {
		this.credentialsExpiration = credentialsExpiration;
	}

	public String getCredentialsOverview() {
		return credentialsOverview;
	}

	public void setCredentialsOverview(String  credentialsOverview) {
		this.credentialsOverview = credentialsOverview;
	}

}
