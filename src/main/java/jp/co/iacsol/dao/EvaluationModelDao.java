package jp.co.iacsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.iacsol.to.EvaluationModel;

public class EvaluationModelDao {

		private Connection con;

		public EvaluationModelDao() {

		}

		public ArrayList<EvaluationModel> findByEvaluationModel(int fiscal_year, int password) {
			ArrayList<EvaluationModel> result = new ArrayList<EvaluationModel>();
			PreparedStatement pStmt = null;
			ResultSet rs = null;

			try {
				String sql = "SELECT * FROM";
				pStmt = con.prepareStatement(sql);
				pStmt.setInt(1, fiscal_year);
				rs = pStmt.executeQuery();

				while (rs.next()) {
					int fiscalYear  = rs.getInt("fiscal_year");
					int rank = rs.getInt("rank");
					String positionAbbreviation = rs.getString("position_abbreviation");
					int positionClass = rs.getInt("position_class");
					int yearsExperienceStart = rs.getInt("years_experience_start");
					int yearsExperienceEnd = rs.getInt("years_experience_end");
					String workConcept = rs.getString("work_concept");
					String professionalAttitude = rs.getString("professional_attitude");
					String managementAbility = rs.getString("management_ability");
					String technologyAbility = rs.getString("technology_ability");
					String executionAbility = rs.getString("execution_ability");
					EvaluationModel evaluationmodel = new EvaluationModel(fiscalYear, rank, positionAbbreviation,
							positionClass, yearsExperienceStart, yearsExperienceEnd, workConcept,
							professionalAttitude, managementAbility, technologyAbility, executionAbility);


				}
				rs.close();
				pStmt.close();

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
			return result;

		}

	}