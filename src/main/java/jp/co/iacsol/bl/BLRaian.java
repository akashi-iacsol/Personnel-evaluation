package jp.co.iacsol.bl;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.iacsol.dao.SheetReferenceDAO;

public class BLRaian {
	Connection con = null;
	public static int Month() {



		try {
			SheetReferenceDAO SR = new SheetReferenceDAO(con);
			int month = SR.CheckMonth();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
