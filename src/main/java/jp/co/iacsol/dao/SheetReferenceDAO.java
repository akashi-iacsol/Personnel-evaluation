//中原 来以安
package jp.co.iacsol.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.riversun.slacklet.SlackletService;

import jp.co.iacsol.to.Department;
import jp.co.iacsol.to.Employee;
import jp.co.iacsol.util.SlackiniFile;

public class SheetReferenceDAO {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	public SheetReferenceDAO(Connection con) {
		this.con = con;
	}

	//部下の特定
	public ArrayList<Employee> SubordinateIdentification(int fiscalYear, int departmentCode) {
		ArrayList<Department> identlist = new ArrayList<>();
		ArrayList<Employee> emplist = new ArrayList<>();
		String sql = "SELECT * from Department WHERE fiscal_year = ? AND higher_department_code = ?";
		try {
			con.prepareStatement(sql);
			ps.setInt(1, fiscalYear);
			ps.setInt(2, departmentCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				identlist.add(new Department(rs.getInt("fiscal_year"),
						rs.getInt("department_code"),
						rs.getInt("higher_department_code"),
						rs.getString("department_name"),
						rs.getString("department_abbreviation"),
						rs.getInt("department_type"),
						rs.getInt("superior_employee_number"),
						rs.getInt("holiday_approver_employee_number")));
			}

			PreparedStatement ps2 = con.prepareStatement(
					"SELECT * from Department WHERE fiscal_year = ? AND higher_department_code = ?");
			ps2.setInt(1, fiscalYear);
			ps2.setInt(2, rs.getInt("department_code"));
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {

				identlist.add(new Department(rs.getInt("fiscal_year"),
						rs2.getInt("department_code"),
						rs2.getInt("higher_department_code"),
						rs2.getString("departmnt_name"),
						rs2.getString("department_abbreviation"),
						rs2.getInt("department_type"),
						rs2.getInt("superior_employee_number"),
						rs2.getInt("holiday_approver_employee_number")));
			}
			PreparedStatement ps3 = con.prepareStatement(
					"Slect * from Employee "
							+ "INNER JOIN Department ON Employee.department_code = Department.department_code"
							+ "Where department_code = ? or department_code = ? ");
			ps3.setInt(1, rs.getInt("department_code"));
			ps3.setInt(2, rs2.getInt("department_code"));
			ResultSet rs3 = ps3.executeQuery();
			while (rs3.next()) {
				int empNumber = rs.getInt("employee_number");
				String empName = rs.getString("employee_name");
				emplist.add(new Employee(empNumber, empName));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return emplist;

	}

	//時刻
	public int CheckMonth() {
		int now = 0;
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.MONTH)==1||cal.get(Calendar.MONTH)==10
		 ||cal.get(Calendar.MONTH)==11||cal.get(Calendar.MONTH)==12){
		now = 10;
		}else if(cal.get(Calendar.MONTH)==2||cal.get(Calendar.MONTH)==3
				||cal.get(Calendar.MONTH)==4){
		now = 2;
		}else {
		now = 5;
		}
		/*1,10,11,12
		  2,3,4,
		 5,6,7,8,9*/
		return now;

	}
	//差し戻し用のメソッド
	public void Remand(int empNumber) throws IOException {
		PreparedStatement ps = null;
		String slackadress = null;
		try {
			ps = con.prepareStatement(
					"Slect slack_user_name from Employee where employee_number = ? ");
			ps.setInt(1, empNumber);
			ResultSet rs = ps.executeQuery();
			slackadress = rs.getString("slack_user_name");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		String botToken = SlackiniFile.token();

		SlackletService slackService = new SlackletService(botToken);
		slackService.start();

		// ユーザーに対してダイレクトメッセージを送る
		String userName = slackadress;
		slackService.sendDirectMessageTo(userName, "差し戻しが行われました");

		// slackとの接続を終了
		slackService.stop();

		PreparedStatement ps1 = null;
		try {
			ps1 = con.prepareStatement(
					"Update Evaluation_HR SET personnel_status = 9");
			ps.setInt(1, empNumber);
			ResultSet rs = ps.executeQuery();
			slackadress = rs.getString("slack_user_name");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	//確定
	public void Confirm(int empNumber) throws IOException {
		PreparedStatement ps = null;
		String slackadress = null;

		try {
			ps = con.prepareStatement(
					"Slect slack_user_name from Employee where employee_number = ? ");
			ps.setInt(1, empNumber);
			ResultSet rs = ps.executeQuery();
			slackadress = rs.getString("slack_user_name");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		String botToken = SlackiniFile.token();

		SlackletService slackService = new SlackletService(botToken);
		slackService.start();

		// ユーザーに対してダイレクトメッセージを送る
		String userName = slackadress;
		slackService.sendDirectMessageTo(userName, "確定が行われました");

		// slackとの接続を終了
		slackService.stop();

		PreparedStatement ps1 = null;
		try {
			ps1 = con.prepareStatement(
					"Update Evaluation_HR SET personnel_status = 9");
			ps.setInt(1, empNumber);
			ResultSet rs = ps.executeQuery();
			slackadress = rs.getString("slack_user_name");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public ArrayList SelectEvaluationSheetData(int employeeNumber) {
		//ArrayList<>  = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Employee"
				+ "INNER JOIN  Evaluation_HR ON Employee.employee_number = Evaluation_HR.employee_number "
				+ "INNER JOIN  Evaluation_VA ON Employee.employee_number = Evaluation_VA.employee_number"
				+ "INNER JOIN  Evaluation_Attirude ON Employee.employee_number = Evaluation_Attirude.employee_number"
				+ "INNER JOIN  Item_Attitude ON Evaluation_Attirude.va_item_code =  Item_Attitude.va_item_code"
				+ "WHERE Employee_number = ? ";

		try {
			ps.setInt(1, employeeNumber);
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				/*
				int superiorEmployeeNumber = rs.getInt();
				String superiorComment = rs.getString();
				int superiorFinish = rs.getInt();
				String superiorStampDirectory = rs.getString();
				int personnelEmployeeNumber = rs.getInt();
				String personnelComment = rs.getString();
				int personnelFinish = rs.getInt();
				String personnelStampDirectory =  rs.getString();
				int salesEmployeeNumber = rs.getInt();
				String customerComment = rs.getString();
				int salesEmployeeFinish rs.getInt();
				String salesStampDirectory = rs.getString();
				int newPositionCode = rs.getInt();
				int newPositionClass = rs.getInt();
				int personnelStatus = rs.getInt();
				String submissionDate = rs.getString();

				評価人事評価分

				int rank  = rs.getInt();
				String positionAbbreviation = rs.getString();
				int positionClass = rs.getInt();
				int yearsExperienceStart = rs.getInt();
				int yearsExperienceEnd, = rs.getInt();
				String workConcept
				String professionalAttitude = rs.getString();
				String managementAbility = rs.getString();
				String technologyAbility = rs.getString();
				String executionAbility = rs.getString();

				評価モデル

				int employeeNumber = rs.getInt();
				int vaItemCode = rs.getInt();
				String topComment1
				String bottomComment1,
				String topComment2
				String bottomComment2
				String topComment3
				String bottomComment3
				int personnelResult = rs.getInt();

				職業能力



				EvaluationHR HR = new EvaluationHR(superiorEmployeeNumber,superiorComment,
				superiorFinish,superiorStampDirectory,personnelEmployeeNumber
				personnelComment, personnelFinish, personnelStampDirectory,
				salesEmployeeNumber, customerComment, salesEmployeeFinish,
				salesStampDirectory, newPositionCode, newPositionClass,
				personnelStatus, submissionDate);

				EvaluationModel Mo = new EvaluationModel(rank,positoonAbberviation,
				positionClass, yearsExperienceStart, yearsExperienceEnd, workConcept
				professionalAttitude, managementAblity, technologyAbility , executionAbility);

				EvaluationVA  VA = new EvaluationVA(employeeNumber,vaItemCode,topComment1,bottomComment1,
				topComment2,bottomComment2,topComment3,bottomComment3,personnelResult);


				*/

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return null;

	}

}
