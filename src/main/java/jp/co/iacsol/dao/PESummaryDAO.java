package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import jp.co.iacsol.to.AnnualSales;
import jp.co.iacsol.to.Belongs;
import jp.co.iacsol.to.Department;
import jp.co.iacsol.to.DepartmentAnnualSales;
import jp.co.iacsol.to.Employee;
import jp.co.iacsol.to.PESPosition;
import jp.co.iacsol.to.PESummary;
import jp.co.iacsol.to.Position;

public class PESummaryDAO {
	private Connection con = null;

	public PESummaryDAO(Connection con) {
		this.con = con;
	}

	/**
	 * エンジニア部門の最上位部門の部門コードを引数にエンジニア部門の全部門を取得。同一の部門は一つのみ。
	 * リストの(0)番目がエンジニア部門の最上層部門
	 * @param departmentCode
	 * @return
	 */
	public ArrayList<Department> findDepartment(int departmentCode) {
		ArrayList<Department> departmentList = new ArrayList<>();
		PreparedStatement psHigh = null;
		ResultSet rsHigh = null;
		PreparedStatement psLow = null;
		ResultSet rsLow = null;
		PreparedStatement psTeam = null;
		ResultSet rsTeam = null;

		try {
			con.setAutoCommit(false);
			// エンジニア部門の最上層部門をリストに入れる
			psHigh = con
					.prepareStatement("SELECT * from Department WHERE department_code = ?");
			psHigh.setInt(1, departmentCode);
			rsHigh = psHigh.executeQuery();

			while (rsHigh.next()) {
				departmentList.add(new Department(rsHigh.getInt("fiscal_year"),
						rsHigh.getInt("department_code"),
						rsHigh.getInt("higher_department_code"),
						rsHigh.getString("department_name"), rsHigh.getString("department_abbreviation"),
						rsHigh.getInt("department_type"), rsHigh.getInt("superior_employee_number"),
						rsHigh.getInt("holiday_approver_employee_number")));

				// エンジニア部門の最上層部門の部門コードと上層部門コードが一致する部門をリストに入れる
				psLow = con
						.prepareStatement(
								"SELECT * from Department WHERE fiscal_year = ? AND higher_department_code = ?");
				psLow.setInt(1, rsHigh.getInt("fiscal_year"));
				psLow.setInt(2, rsHigh.getInt("department_code"));
				rsLow = psLow.executeQuery();

				while (rsLow.next()) {

					departmentList.add(new Department(rsLow.getInt("fiscal_year"),
							rsLow.getInt("department_code"),
							rsLow.getInt("higher_department_code"),
							rsLow.getString("department_name"), rsLow.getString("department_abbreviation"),
							rsLow.getInt("department_type"), rsLow.getInt("superior_employee_number"),
							rsLow.getInt("holiday_approver_employee_number")));

					// 上の処理で一致した部門コードと上層部門コードが一致するチームをリストに入れる
					psTeam = con.prepareStatement(
							"SELECT * from Department WHERE fiscal_year = ? AND higher_department_code = ?");
					psTeam.setInt(1, rsLow.getInt("fiscal_year"));
					psTeam.setInt(2, rsLow.getInt("department_code"));
					rsTeam = psTeam.executeQuery();

					while (rsTeam.next()) {

						departmentList.add(new Department(rsTeam.getInt("fiscal_year"),
								rsTeam.getInt("department_code"),
								rsTeam.getInt("higher_department_code"),
								rsTeam.getString("department_name"),
								rsTeam.getString("department_abbreviation"),
								rsTeam.getInt("department_type"), rsTeam.getInt("superior_employee_number"),
								rsTeam.getInt("holiday_approver_employee_number")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psHigh != null) {
					psHigh.close();
				}
				if (rsHigh != null) {
					rsHigh.close();
				}
				if (psLow != null) {
					psLow.close();
				}
				if (rsLow != null) {
					rsLow.close();
				}
				if (psTeam != null) {
					rsLow.close();
				}
				if (rsLow != null) {
					rsTeam.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return departmentList;
	}

	/**
	 * エンジニア部門の全部門を引数に人事考課サマリ配列を作成
	 * 部門コードを基に所属マスタに対してSELECT文で所属オブジェクトに必要な情報を取得しオブジェクトを作成。
	 * 年度と所属オブジェクトを基に部門マスタ、役職マスタ、社員マスタに対してSELECT文で部門・役職・社員オブジェクトに必要な情報を取得し、オブジェクトを作成。
	 * 年度と社員オブジェクトを基に人事考課サマリと評価／人事評価から人事考課サマリオブジェクトに必要な情報を取得し、オブジェクトを作成。
	 * 昨年度ランク・については所属オブジェクトの役職コードとクラスと年度を基に評価モデルマスタからランクを取得。
	 * 配列は部門の昇順、昨年度・ランクの降順でソート。
	 * @param departmentSelect
	 * @return
	 */
	public ArrayList<PESummary> findPESummary(ArrayList<Department> departmentList) {
		ArrayList<PESummary> summaryList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"SELECT * FROM PESummary ps LEFT JOIN Employee e ON ps.employee_number = e.employee_number LEFT JOIN Belongs b ON ps.fiscal_year = b.fiscal_year AND ps.employee_number = b.employee_number LEFT JOIN Department d ON ps.fiscal_year = d.fiscal_year AND b.department_code = d.department_code LEFT JOIN Position p ON ps.fiscal_year = p.fiscal_year AND b.position_code = p.position_code LEFT JOIN Evaluation_model m ON ps.fiscal_year = m.fiscal_year AND b.position_code = m.position_code AND b.position_class = m.position_class LEFT JOIN Evaluation_HR h ON ps.fiscal_year = h.fiscal_year AND ps.employee_number = h.employee_number");
			rs = ps.executeQuery();

			while (rs.next()) {
				boolean isThere = false;

				for (Department dep : departmentList) {
					if (dep.getDepartmentCode() == rs.getInt("department_code")
							|| dep.getDepartmentCode() == rs.getInt("higher_department_code")) {
						isThere = true;
					}
				}

				if (isThere) {
					summaryList.add(new PESummary(
							new Employee(rs.getInt("employee_number"), rs.getString("password"),
									rs.getString("employee_name"), rs.getString("date_joining_company"),
									rs.getString("phone_number"), rs.getString("mail_address"),
									rs.getString("slack_user_name"),
									rs.getInt("muster_administrative_authority"),
									new Department(rs.getInt("fiscal_year"),
											rs.getInt("department_code"),
											rs.getInt("higher_department_code"),
											rs.getString("department_name"),
											rs.getString("department_abbreviation"),
											rs.getInt("department_type"),
											rs.getInt("superior_employee_number"),
											rs.getInt("holiday_approver_employee_number")),
									new Position(rs.getInt("fiscal_year"), rs.getInt("position_code"),
											rs.getString("position_name"),
											rs.getString("position_abbreviation")),
									new Belongs(rs.getInt("fiscal_year"),
											rs.getInt("employee_number"),
											rs.getInt("department_code"),
											rs.getInt("position_code"),
											rs.getInt("position_class"))),
							rs.getInt("rank"), rs.getInt("fiscal_year"), rs.getInt("bonus_recommendation"),
							rs.getString("middle_comment"), rs.getInt("bonus_comfirm"),
							rs.getInt("last_position_code"), rs.getInt("last_rank"),
							rs.getString("last_comment"), rs.getInt("appraisal_position_code"),
							rs.getInt("appraisal_rank"), rs.getString("appraisal_comment"),
							rs.getInt("personnel_status")));
				}
			}

			summaryList.sort(new Comparator<PESummary>() {
				public int compare(PESummary obj1, PESummary obj2) {
					int temp = obj1.getEmp().getBelo().getDepartmentCode()
							- obj2.getEmp().getBelo().getDepartmentCode();
					if (temp == 0) {
						temp = obj1.getEmp().getBelo().getPositionCode()
								- obj2.getEmp().getBelo().getPositionCode();
						if (temp == 0) {
							temp = obj1.getEmp().getBelo().getPositionClass()
									- obj2.getEmp().getBelo().getPositionClass();
						}
					}
					return temp;
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return summaryList;
	}

	/**
	 * 年度売上目標データ配列を取得
	 * @return
	 */
	public ArrayList<AnnualSales> findAnnualSales() {
		ArrayList<AnnualSales> annualSalesList = new ArrayList<>();
		;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT * from Annual_sales");
			rs = ps.executeQuery();

			while (rs.next()) {
				annualSalesList.add(new AnnualSales(rs.getInt("fiscal_year"), rs.getLong("sales_target"),
						rs.getLong("sales_result"), rs.getFloat("sales_increase_rate"),
						rs.getFloat("rank_increase_rate")));
			}

			annualSalesList.sort(new Comparator<AnnualSales>() {
				public int compare(AnnualSales obj1, AnnualSales obj2) {
					return obj1.getFiscalYear() - obj2.getFiscalYear();
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return annualSalesList;
	}

	/**
	 * 部門別年度目標データ配列を取得
	 * @param departmentAnnualSales
	 * @return
	 */
	public ArrayList<DepartmentAnnualSales> findDepartmentAnnualSales(ArrayList<Department> departmentList) {
		ArrayList<DepartmentAnnualSales> departmentAnnualSalesList = new ArrayList<>();
		ArrayList<Department> highDepartmentList = new ArrayList<>();

		for (Department d : departmentList) {
			if (d.getDepartmentType() == 0) {
				highDepartmentList.add(d);
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			for (int i = 0; i < highDepartmentList.size(); i++) {
				ps = con.prepareStatement(
						"SELECT * from Department_Annual_sales WHERE fiscal_year = ? AND department_code = ?");
				ps.setInt(1, highDepartmentList.get(i).getFiscalYear());
				ps.setInt(2, highDepartmentList.get(i).getDepartmentCode());
				rs = ps.executeQuery();

				while (rs.next()) {
					departmentAnnualSalesList.add(
							new DepartmentAnnualSales(rs.getInt("fiscal_year"),
									rs.getInt("department_code"),
									rs.getLong("first_half_sales_target"),
									rs.getLong("first_half_sales_result"),
									rs.getLong("second_half_sales_target"),
									rs.getLong("second_half_sales_result"),
									rs.getInt("department_rank_target"),
									rs.getInt("department_rank_result")));
				}
			}
			departmentAnnualSalesList.sort(new Comparator<DepartmentAnnualSales>() {
				public int compare(DepartmentAnnualSales obj1, DepartmentAnnualSales obj2) {
					int temp = obj1.getFiscalYear() - obj2.getFiscalYear();
					if (temp == 0) {
						temp = obj1.getDepartmentCode() - obj2.getDepartmentCode();
					}
					return temp;
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return departmentAnnualSalesList;
	}

	/**
	 * 役職配列を取得
	 * @return
	 */
	public ArrayList<PESPosition> findPESPosition() {
		ArrayList<PESPosition> positionList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con.setAutoCommit(false);
			ps = con
					.prepareStatement(
							"SELECT p.fiscal_year, p.position_code, p.position_name, p.position_abbreviation, e.rank FROM Position p INNER JOIN Evaluation_model e ON p.fiscal_year = e.fiscal_year And p.position_code = e.position_code WHERE (p.position_code, e.rank) IN (SELECT p.position_code, MAX(e.rank) FROM Position p INNER JOIN Evaluation_model e ON p.fiscal_year = e.fiscal_year And p.position_code = e.position_code GROUP BY p.position_code)");
			rs = ps.executeQuery();

			while (rs.next()) {
				positionList.add(new PESPosition(rs.getInt("fiscal_year"),
						rs.getInt("position_code"),
						rs.getString("position_name"), rs.getString("position_abbreviation"), rs.getInt("rank")));
			}
			positionList.sort(new Comparator<PESPosition>() {
				public int compare(PESPosition obj1, PESPosition obj2) {
					int temp = obj1.getFiscalYear() - obj2.getFiscalYear();
					if (temp == 0) {
						temp = obj1.getPositionCode() - obj2.getPositionCode();
					}
					return temp;
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return positionList;
	}

	/**
	 * 人事考課サマリ配列・年度売上目標データオブジェクト・部門別年度目標データ配列を引数に
	 * 人事考課サマリ配列の内容で人事考課サマリと評価／人事評価を更新。
	 * 年度売上目標データオブジェクトの売上目標の内容で年度売上目標データの売上目標を更新。
	 * 部門別年度目標データ配列の内容で部門別年度目標データを更新。
	 * @param summaryList
	 * @param annualSales
	 * @param departmentAnnualSalesList
	 */
	public void updatePESummary(ArrayList<PESummary> summaryList, AnnualSales annualSales,
			ArrayList<DepartmentAnnualSales> departmentAnnualSalesList) {
		PreparedStatement psSummary = null;
		PreparedStatement psStatus = null;
		PreparedStatement psAnnualSales = null;
		PreparedStatement psDepartmentAnnualSales = null;
		try {
			con.setAutoCommit(false);
			for (PESummary summary : summaryList) {
				psSummary = con.prepareStatement(
						"UPDATE PESummary SET bonus_recommendation = ?, middle_comment = ?, bonus_comfirm = ?, last_position_code = ?, last_rank = ?, last_comment = ?, appraisal_position_code = ?, appraisal_rank = ?, appraisal_comment = ? WHERE fiscal_year = ? AND employee_number = ?");
				psSummary.setInt(1, summary.getBonusRecommendation());
				if (!summary.getMiddleComment().equals("")) {
					psSummary.setString(2, summary.getMiddleComment());
				} else {
					psSummary.setObject(2, null);
				}
				psSummary.setInt(3, summary.getBonusComfirm());
				if (summary.getLastPositionCode() != 0) {
					psSummary.setInt(4, summary.getLastPositionCode());
				} else {
					psSummary.setObject(4, null);
				}
				if (summary.getLastRank() != 0) {
					psSummary.setInt(5, summary.getLastRank());
				} else {
					psSummary.setObject(5, null);
				}
				if (!summary.getLastComment().equals("")) {
					psSummary.setString(6, summary.getLastComment());
				} else {
					psSummary.setObject(6, null);
				}
				if (summary.getAppraisalPositionCode() != 0) {
					psSummary.setInt(7, summary.getAppraisalPositionCode());
				} else {
					psSummary.setObject(7, null);
				}
				if (summary.getAppraisalRank() != 0) {
					psSummary.setInt(8, summary.getAppraisalRank());
				} else {
					psSummary.setObject(8, null);
				}
				if (!summary.getAppraisalComment().equals("")) {
					psSummary.setString(9, summary.getAppraisalComment());
				} else {
					psSummary.setObject(9, null);
				}
				psSummary.setInt(10, summary.getFiscalYear());
				psSummary.setInt(11, summary.getEmp().getEmployeeNumber());

				psStatus = con.prepareStatement(
						"UPDATE Evaluation_HR SET personnel_status = ? WHERE fiscal_year = ? AND employee_number = ?");
				psStatus.setInt(1, summary.getStatus());
				psStatus.setInt(2, summary.getFiscalYear());
				psStatus.setInt(3, summary.getEmp().getEmployeeNumber());

				try {
					psSummary.executeUpdate();
					psStatus.executeUpdate();
					con.commit();
				} catch (Exception e) {
					con.rollback();
					e.printStackTrace();
				}
			}
			psAnnualSales = con.prepareStatement(
					"UPDATE Annual_sales SET sales_target = ?, sales_result = ?, sales_increase_rate  = ?, Rank_increase_rate = ? WHERE fiscal_year = ?");
			psAnnualSales.setLong(1, annualSales.getSalesTarget());
			psAnnualSales.setLong(2, annualSales.getSalesResult());
			psAnnualSales.setFloat(3, annualSales.getSalesIncreaseRate());
			psAnnualSales.setFloat(4, annualSales.getRankIncreaseRate());
			psAnnualSales.setInt(5, annualSales.getFiscalYear());

			try {
				psAnnualSales.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
			for (DepartmentAnnualSales departmentAnnualSales : departmentAnnualSalesList) {
				con.setAutoCommit(false);
				psDepartmentAnnualSales = con.prepareStatement(
						"UPDATE Department_Annual_sales SET department_rank_target = ?, department_rank_result = ? WHERE fiscal_year = ? AND department_code = ?");
				psDepartmentAnnualSales.setInt(1, departmentAnnualSales.getDepartmentRankTarget());
				psDepartmentAnnualSales.setInt(2, departmentAnnualSales.getDepartmentRankResult());
				psDepartmentAnnualSales.setInt(3, departmentAnnualSales.getFiscalYear());
				psDepartmentAnnualSales.setInt(4, departmentAnnualSales.getDepartmentCode());

				try {
					psDepartmentAnnualSales.executeUpdate();
					con.commit();
				} catch (Exception e) {
					con.rollback();
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psSummary != null) {
					psSummary.close();
				}
				if (psStatus != null) {
					psStatus.close();
				}
				if (psAnnualSales != null) {
					psAnnualSales.close();
				}
				if (psDepartmentAnnualSales != null) {
					psDepartmentAnnualSales.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 人事考課サマリに対してSELECT・INSERT文で今年度の社員番号を全て取得し、来年度のデータを書き込む。
	 * 年度・社員番号以外は0または空白。
	 * @param fiscalYear
	 */
	public void insertNextYearPESummary(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO PESummary (fiscal_year, employee_number) SELECT fiscal_year + 1, employee_number FROM PESummary WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 部門マスタに対してSELECT・INSERT文で今年度の情報を全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * その他は今年度と同一。
	 * @param fiscalYear
	 */
	public void insertNextYearDepartment(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO department (fiscal_year, department_code, higher_department_code, department_name, department_abbreviation, department_type, superior_employee_number, holiday_approver_employee_number) SELECT fiscal_year + 1, department_code, higher_department_code, department_name, department_abbreviation, department_type, superior_employee_number, holiday_approver_employee_number FROM department WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 役職マスタに対してSELECT・INSERT文で今年度の情報を全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * その他は今年度と同一。
	 * @param fiscalYear
	 */
	public void insertNextYearPosition(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Position (fiscal_year, position_code, position_name, position_abbreviation) SELECT fiscal_year + 1, position_code, position_name, position_abbreviation FROM Position WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度・人事考課サマリ配列を引数に今年度のデータを取得し、来年度を作成。
	 * 所属マスタに対してSELECT・INSERT文で今年度の情報を全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * その他は今年度と同一。
	 * その後人事考課サマリ配列から社員番号を取得し
	 * 所属マスタに対してUPDATE文を実行し、役職コードとクラスを役員考課結果で上書きする。
	 * 役職コードは役職コード[役員]から取得。
	 * クラスは役職コード[役員]・ランク[役員]を基に評価モデルマスタに対してSELECT文でクラスを取得。
	 * @param fiscalYear
	 * @param summary
	 */
	public void insertNextYearBelongs(int fiscalYear, ArrayList<PESummary> summaryList) {
		PreparedStatement psInsert = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		PreparedStatement psUpdate = null;
		try {
			con.setAutoCommit(false);
			psInsert = con.prepareStatement(
					"INSERT INTO Belongs (fiscal_year, employee_number, department_code, position_code, position_class ) SELECT fiscal_year + 1, employee_number, department_code, position_code, position_class FROM Belongs WHERE fiscal_year = ?");
			psInsert.setInt(1, fiscalYear);

			try {
				psInsert.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}

			for (PESummary summary : summaryList) {
				psSelect = con.prepareStatement(
						"SELECT position_class from Evaluation_model WHERE fiscal_year = ? AND rank = ? AND position_code = ?");
				psSelect.setInt(1, fiscalYear);
				psSelect.setInt(2, summary.getAppraisalRank());
				psSelect.setInt(3, summary.getAppraisalPositionCode());
				rs = psSelect.executeQuery();

				while (rs.next()) {
					psUpdate = con.prepareStatement(
							"UPDATE Belongs SET position_code = ?, position_class = ? WHERE fiscal_year = ? AND employee_number = ?");
					psUpdate.setInt(1, summary.getAppraisalPositionCode());
					psUpdate.setInt(2, rs.getInt("position_class"));
					psUpdate.setInt(3, fiscalYear + 1);
					psUpdate.setInt(4, summary.getEmp().getEmployeeNumber());
				}

				try {
					psUpdate.executeUpdate();
					con.commit();
				} catch (Exception e) {
					con.rollback();
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psInsert != null) {
					psInsert.close();
				}
				if (psSelect != null) {
					psSelect.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (psUpdate != null) {
					psUpdate.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 保有資格データに対してSELECT文で今年度の情報を全て取得。
	 * 取得した資格コードを基に資格マスタに対してSELECT文で有効期限を取得。
	 * 取得年月と有効期限を基に失効日を算出。
	 * 現在の年の9月までに失効日を迎えるデータ
	 * および結果が１(合格),3(保有)以外のデータを除き
	 * 保有資格データに対してINSERT文で来年度のデータを書き込む。
	 * 年度は今年度+1
	 * その他は今年度と同一。
	 * @param fiscalYear
	 */
	public void insertNextYearQualification(int fiscalYear) {
		LocalDate today = LocalDate.now();
		int expirationPeriod = Integer.parseInt(today.getYear() + "09");
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		PreparedStatement psInsert = null;
		try {
			con.setAutoCommit(false);
			psSelect = con.prepareStatement(
					"SELECT * from Qualification q LEFT JOIN Credentials c ON q.credentials_code = c.credentials_code WHERE fiscal_year = ?");
			psSelect.setInt(1, fiscalYear);
			rs = psSelect.executeQuery();

			while (rs.next()) {
				int expirationYear = Integer.parseInt(rs.getString("acquisition_date").substring(0, 4))
						+ (rs.getInt("credentials_expiration") / 12);
				String expirationMonth = String.format("%02d",
						Integer.parseInt(rs.getString("acquisition_date").substring(4))
								+ (rs.getInt("credentials_expiration") % 12));
				int expirationYearMonth = Integer.parseInt(expirationYear + expirationMonth);

				if (expirationYearMonth > expirationPeriod
						&& (rs.getInt("qualification_result") == 1 || rs.getInt("qualification_result") == 3)) {
					psInsert = con.prepareStatement(
							"INSERT INTO Qualification (fiscal_year, employee_number, credentials_code, acquisition_date, how_to_get_qualified, qualification_result) SELECT fiscal_year + 1, employee_number, credentials_code, acquisition_date, how_to_get_qualified, 3 FROM Qualification WHERE fiscal_year = ?");
					psInsert.setInt(1, fiscalYear);

					try {
						psInsert.executeUpdate();
						con.commit();
					} catch (Exception e) {
						con.rollback();
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psSelect != null) {
					psSelect.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (psInsert != null) {
					psInsert.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 評価／人事評価に対してSELECT・INSERT文で今年度の社員番号を全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * 社員番号は今年度と同一
	 * その他はデータ型により0または空白。
	 * @param fiscalYear
	 */
	public void insertNextYearEvaluationHR(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Evaluation_HR (fiscal_year, employee_number, superior_finish,personnel_finish, sales_employee_finish, personnel_status) SELECT fiscal_year + 1, employee_number, 0, 0, 0, 0 FROM Evaluation_HR WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 評価／職業能力に対してSELECT・INSERT文で今年度の社員番号・項目コードを全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * 社員番号・項目コードは今年度と同一
	 * その他はデータ型により0または空白。
	 * @param fiscalYear
	 */
	public void insertNextYearEvaluationVA(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Evaluation_VA (fiscal_year, employee_number, va_item_code, personnel_result) SELECT fiscal_year + 1, employee_number, va_item_code, 0 FROM Evaluation_VA WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 評価／態度・業務遂行能力に対してSELECT・INSERT文で今年度の社員番号・項目コードを全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * 社員番号・項目コードは今年度と同一
	 * その他はデータ型により0または空白。
	 * @param fiscalYear
	 */
	public void insertNextYearEvaluationAttirude(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Evaluation_Attirude (fiscal_year, employee_number, at_item_code) SELECT fiscal_year + 1, employee_number, at_item_code FROM Evaluation_Attirude WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 年度売上目標データに対してSELECT・INSERT文で今年度の情報を取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * 売上目標は今年度売上結果＋(今年度売上結果×今年度売上目標上昇率÷100)
	 * 売上結果は0
	 * その他は今年度と同一。
	 * @param fiscalYear
	 */
	public void insertNextYearAnnualSales(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Annual_sales (fiscal_year, sales_target, sales_increase_rate, rank_increase_rate) SELECT fiscal_year + 1, sales_result + (sales_result * sales_increase_rate /100),  sales_increase_rate, rank_increase_rate FROM Annual_sales WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 年度売上目標データに対してSELECT文で今年度の売上目標上昇率とランク目標上昇率を取得。
	 * 部門別年度目標データに対してSELECT・INSERT文で今年度の情報を全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * 部門コードは今年度と同一
	 * 上期売上目標は今年度上期売上結果＋(今年度上期売上結果×売上目標上昇率÷100)
	 * 上期売上結果は0
	 * 下期売上目標は今年度下期売上結果＋(今年度下期売上結果×売上目標上昇率÷100)
	 * 下期売上結果は0
	 * 部門ランク目標は今年度部門ランク結果＋(今年度部門ランク結果×ランク目標上昇率÷100)
	 * 部門ランク結果は0。
	 * @param fiscalYear
	 */
	public void insertNextYearDepartmentAnnualSales(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Department_Annual_sales (fiscal_year, department_code, department_rank_target) SELECT d.fiscal_year + 1, d.department_code, round(CAST(d.department_rank_result + (d.department_rank_result * a.rank_increase_rate /100) as numeric), -1) FROM Department_Annual_sales d LEFT JOIN Annual_sales a ON d.fiscal_year = a.fiscal_year WHERE d.fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 今年度を引数に今年度のデータを取得し、来年度を作成。
	 * 評価モデルマスタに対してSELECT・INSERT文で今年度の情報を全て取得し、来年度のデータを書き込む。
	 * 年度は今年度+1
	 * その他は今年度と同一。
	 * @param fiscalYear
	 */
	public void insertNextYearEvaluationModel(int fiscalYear) {
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO Evaluation_model (fiscal_year, rank, position_code, position_class, years_experience_start, years_experience_end, work_concept, professional_attitude, management_ability, technology_ability, execution_ability) SELECT fiscal_year + 1, rank, position_code, position_class, years_experience_start, years_experience_end, work_concept, professional_attitude, management_ability, technology_ability, execution_ability FROM Evaluation_model WHERE fiscal_year = ?");
			ps.setLong(1, fiscalYear);

			try {
				ps.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}