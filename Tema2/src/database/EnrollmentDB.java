package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.Course;
import model.Student;

public class EnrollmentDB {

	private static final String INSERT = "INSERT INTO `lab2`.`enrollment`(studentId, courseId) VALUES (?,?)";
	private static final String SELECTSTUDENTS = "Select lab2.student.id, lab2.student.name, lab2.student.birthday, "
			+ "lab2.student.address From lab2.student, lab2.course, lab2.enrollment where "
			+ "course.name = ? and enrollment.courseID = course.id and enrollment.studentID=student.id;";
	private static final String ID = "id";
	private static final String BIRTHDAY = "birthday";
	private static final String ADDRESS = "address";
	private static final String NAME = "name";

	public void enrollStudentToCourse(Student student, Course course) {
		PreparedStatement stmt = null;
		DBConnection db = new DBConnection();
		StudentDB sDB = new StudentDB();
		CourseDB cDB = new CourseDB();
		try {
			Student s2 = sDB.readStudent(student.getName());
			Course c2 = cDB.readCourse(course.getName());
			stmt = (PreparedStatement) db.connect().prepareStatement(INSERT);
			stmt.setInt(1, s2.getId());
			stmt.setInt(2, c2.getId());
			stmt.execute();
			db.disconnect();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> readStudentsInCourse(String name) {
		PreparedStatement stmt = null;
		List<Student> list = new ArrayList<Student>();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(SELECTSTUDENTS);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt(ID));
				student.setName(rs.getString(NAME));
				student.setDate(rs.getString(BIRTHDAY));
				student.setAddress(rs.getString(ADDRESS));
				list.add(student);
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
		return list;
	}

}
