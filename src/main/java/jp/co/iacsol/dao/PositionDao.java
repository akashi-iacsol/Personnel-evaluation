//中原　来以安
package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.iacsol.to.Position;

public class PositionDao {

	private PreparedStatement ps = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private Connection con = null;

	public PositionDao(Connection con) {
		this.con = con;
	}

	//すべてを参照する
	public ArrayList<Position> PositionSelect() {

		ArrayList<Position> arrayPos = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Position ";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Position pos = new Position();
				pos.setFiscalYear(result.getInt("fiscal_year"));
				pos.setPositionCode(result.getInt("position_code"));
				pos.setPositionName(result.getString("position_name"));
				pos.setPositionAbbreviation(result.getString("position_abbreviation"));
				arrayPos.add(pos);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayPos;
	}

	//役職コードをもとに参照する
	public ArrayList<Position> PositionSelect(int positionCode) {

		ArrayList<Position> arrayPos = new ArrayList<>();

		try {
			stmt = con.createStatement();
			String sql = "SELECT * from Position where position_code = " + positionCode + "";
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Position pos = new Position();
				pos.setFiscalYear(result.getInt("fiscal_year"));
				pos.setPositionCode(result.getInt("position_code"));
				pos.setPositionName(result.getString("position_name"));
				pos.setPositionAbbreviation(result.getString("position_abbreviation"));
				arrayPos.add(pos);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayPos;
	}

	//引数をもとに追加する
	public void BelongsInsert(int positionCode, String positionName, String positionAbbreviation) {

		try {
			String sql = "INSERT INTO Belongs values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, positionCode);
			ps.setString(2, positionName);
			ps.setString(3, positionAbbreviation);
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//引数をもとに更新する
	public void BelongsUpdate(int positionCode, String positionName, String positionAbbreviation) {

		try {
			String sql = "UPDATE Position SET "
					+ " position_name = ? "
					+ " position_abbreviation = ? "
					+ " WHERE  position_code = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, positionName);
			ps.setString(2, positionAbbreviation);
			ps.setInt(3, positionCode);
			ps.executeUpdate();
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//役職コードをもとに削除する
	public void BelongsDelete(int positionCode) {
		try {
			String sql = "DELETE FROM Position WHERE position_code = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, positionCode);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
