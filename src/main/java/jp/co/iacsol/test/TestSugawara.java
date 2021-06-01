package jp.co.iacsol.test;

import java.util.ArrayList;

import jp.co.iacsol.bl.BLSugawara;
import jp.co.iacsol.to.ListingTO;
import jp.co.iacsol.to.OtherEvaluationTO;

public class TestSugawara {

	public static void main(String[] args) {
		otherEvaluationTest();
	}

	static void listingTest() {
		ArrayList<ListingTO> listingArray = new ArrayList<ListingTO>();
		BLSugawara bl = new BLSugawara();
		listingArray = bl.findByListing("菅原　大知", 0);
		for (ListingTO listing : listingArray) {
			System.out.println(listing.getFiscalYear()
					+ " " + listing.getEmployeeNumber()
					+ " " + listing.getEmployeeName()
					+ " " + listing.getDepartmentName()
					+ " " + listing.getMiddleComment()
					+ " " + listing.getLastComment());
		}

	}

	static void otherEvaluationTest() {
		ArrayList<OtherEvaluationTO> otherEvaluationArray = new ArrayList<OtherEvaluationTO>();
		BLSugawara bl = new BLSugawara();
		otherEvaluationArray = bl.findByEmployeeName();
		for (OtherEvaluationTO otherEvaluation : otherEvaluationArray) {
			System.out.println(otherEvaluation.getOtherEvaluation()
					+ " " + otherEvaluation.getEmployeeName());
		}

	}

}
