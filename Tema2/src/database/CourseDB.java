package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import model.Course;

public class CourseDB {

	private static final String INSERT = "INSERT INTO `lab2`.`course`(name, teacher, studyYear) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE `lab2`.`course` SET  `teacher`=?, `studyYear`=? WHERE `name`= ?";
	private static final String DELETE = "DELETE FROM `lab2`.`course` WHERE `id`= ?";
	private static final String SELECT = "SELECT * FROM lab2.course where `name` = ?";
	private static final String ID = "id";
	private static final String YEAR = "studyYear";
	private static final String NAME = "name";
	private static final String TEACHER = "teacher";

	public void addCourse(Course course) {
		PreparedStatement stmt = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(INSERT);
			stmt.setString(1, course.getName());
			stmt.setString(2, course.getTeacher());
			stmt.setInt(3, course.getYear());
			stmt.execute();
			db.disconnect();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCourse(Course course) {
		PreparedStatement stmt = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(UPDATE);
			stmt.setString(1, course.getTeacher());
			stmt.setInt(2, course.getYear());
			stmt.setString(3, course.getName());
			stmt.executeUpdate();
			db.disconnect();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCourse(int id) {
		PreparedStatement stmt = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.execute();
			db.disconnect();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Course readCourse(String name) throws SQLException {
		PreparedStatement stmt = null;
		Course course = new Course();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(SELECT);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				course.setId(rs.getInt(ID));
				course.setName(rs.getString(NAME));
				course.setTeacher(rs.getString(TEACHER));
				course.setYear(rs.getInt(YEAR));
			}
			db.disconnect();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (course.getName() == null) {
			throw new SQLException();
		} else
			return course;
	}

}
