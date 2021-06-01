package jp.co.iacsol.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class IniFileCon {
	public static String getIni(String Entry) {
	String ret = "";

	String iniStrage = Location.location+"db.ini";

	try
	{
		// 生のバイトのストリーム
		FileInputStream fis = new FileInputStream(iniStrage);
		// SHIFT_JIS として読み込む為の準備
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		// 行単位で読み込む為の準備
		BufferedReader br = new BufferedReader(isr);

		String line_buffer = "";
		// BufferedReader は、readLine が null を返すと読み込み終了
		String str = "";
		while (null != (line_buffer = br.readLine())) {
			String[] result = line_buffer.split("=");
			str = (String) result[0];
			str = str.trim();
			if (str.equalsIgnoreCase(Entry)) {
				ret = (String) result[1];
				ret = ret.trim();
			}
		}

		// 閉じる
		br.close();
		isr.close();
		fis.close();
	}catch(
	Exception e)
	{
		ret = e.getMessage();
	}

	return ret;

	}

	public static Connection con(){
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url(),user(),password());
		}catch(Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	public static String url() {
		return getIni("url");
	}

	public static String user() {
		return getIni("user");
	}

	public static String password() {
		return getIni("password");
	}

}
