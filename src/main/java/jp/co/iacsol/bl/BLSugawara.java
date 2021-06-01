package jp.co.iacsol.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.iacsol.dao.ListingDAO;
import jp.co.iacsol.to.ListingTO;
import jp.co.iacsol.to.OtherEvaluationTO;
import jp.co.iacsol.util.DBInfoSugawara;

public class BLSugawara {
	Connection con = DBInfoSugawara.getConnection();

	public ArrayList<ListingTO> findByListing(String employeeName, int fiscalYear) {
		ArrayList<ListingTO> listingArray = new ArrayList<ListingTO>();
		try {
			ListingDAO dao = new ListingDAO(con);
			listingArray = dao.findByListing(employeeName, fiscalYear);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listingArray;
	}

	public ArrayList<OtherEvaluationTO> findByEmployeeName() {
		ArrayList<OtherEvaluationTO> otherEvaluationArray = new ArrayList<OtherEvaluationTO>();

		try {
			ListingDAO dao = new ListingDAO(con);
			otherEvaluationArray = dao.findByEmployeeName();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return otherEvaluationArray;
	}

}