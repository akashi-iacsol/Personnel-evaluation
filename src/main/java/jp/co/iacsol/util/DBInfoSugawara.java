package jp.co.iacsol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInfoSugawara {

	private static Connection con = null;
	private static String url = "jdbc:postgresql://localhost:5432/iacsol";
	private static String user = "iacsol";
	private static String password = "iacsol2013";

	public static Connection getConnection() {

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
