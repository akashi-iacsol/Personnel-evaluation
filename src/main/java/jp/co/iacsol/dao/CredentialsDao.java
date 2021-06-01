//中原　来以安
package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.iacsol.to.Credential;

public class CredentialsDao {

	private PreparedStatement ps = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private Connection con = null;

	public CredentialsDao(Connection con) {
		this.con = con;
	}

	//すべての情報を持ってくる
	public ArrayList<Credential> CredentialsSelect() {

		ArrayList<Credential> arrayCre = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Credential";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Credential cre = new Credential();
				cre.setCredentialsCode(result.getInt("credentials_code"));
				cre.setCredentialsName(result.getString("credentials_name"));
				cre.setCredentialsOverview(result.getString("credentials_overview"));
				cre.setCredentialsExpiration(result.getInt("credentials_expiration"));
				arrayCre.add(cre);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayCre;
	}

	//資格コードをもとに持ってくる
	public ArrayList<Credential> CredentialsSelect(int credentialsCode) {

		ArrayList<Credential> arrayCre = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Credential where  credentials_code = " + credentialsCode + "";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Credential cre = new Credential();
				cre.setCredentialsCode(result.getInt("credentials_code"));
				cre.setCredentialsName(result.getString("credentials_name"));
				cre.setCredentialsOverview(result.getString("credentials_overview"));
				cre.setCredentialsExpiration(result.getInt("credentials_expiration"));
				arrayCre.add(cre);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayCre;
	}

	//引数をもとに追加する
	public void CredentialInsert(int credentialsCode, String credentialsName, String credentialsOverview,
			int credentialsExpiration) {

		try {
			String sql = "INSERT INTO Credential values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, credentialsCode);
			ps.setString(2, credentialsName);
			ps.setString(3, credentialsOverview);
			ps.setInt(4, credentialsExpiration);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//引数をもとに更新する
	public void CredentialUpdate(int credentialsCode, String credentialsName, String credentialsOverview,
			int credentialsExpiration) {
		try {
			String sql = "UPDATE Credential SET "
					+ "credentials_name = ? "
					+ "credentials_overview = ? "
					+ "credentials_overview = ?"
					+ "credentialsCode  =  ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, credentialsName);
			ps.setString(2, credentialsOverview);
			ps.setInt(3, credentialsExpiration);
			ps.setInt(4, credentialsCode);
			ps.executeUpdate();
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//資格コードをもとに削除する
	public void CredentialDelete(int credentialCode) {
		try {
			String sql = "DELETE FROM Credential WHERE credentials_code = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, credentialCode);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
