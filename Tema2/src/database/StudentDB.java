package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import model.Student;

public class StudentDB {

	private static final String INSERT = "INSERT INTO `lab2`.`student`(name, birthDay, address) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE `lab2`.`student` SET `birthDay`=?, `address`=? WHERE `name`= ?";
	private static final String DELETE = "DELETE FROM `lab2`.`student` WHERE `id`= ?";
	private static final String SELECT = "SELECT `id`, `name`, `birthday`, `address` FROM `lab2`.`student` WHERE `name`= ?;";
	private static final String ID = "id";
	private static final String BIRTHDAY = "birthday";
	private static final String ADDRESS = "address";
	private static final String NAME = "name";

	public void addStudent(Student student) {
		PreparedStatement stmt = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(INSERT);
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getDate());
			stmt.setString(3, student.getAddress());
			stmt.execute();
			db.disconnect();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student) {
		PreparedStatement stmt = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(UPDATE);
			stmt.setString(1, student.getDate());
			stmt.setString(2, student.getAddress());
			stmt.setString(3, student.getName());
			stmt.executeUpdate();
			db.disconnect();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {
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

	public Student readStudent(String name) throws SQLException {
		PreparedStatement stmt = null;
		Student student = new Student();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			stmt = (PreparedStatement) db.connect().prepareStatement(SELECT);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				student.setId(rs.getInt(ID));
				student.setName(rs.getString(NAME));
				student.setDate(rs.getString(BIRTHDAY));
				student.setAddress(rs.getString(ADDRESS));
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
		if (student.getName() == null) {
			throw new SQLException();
		} else
			return student;
	}

}
