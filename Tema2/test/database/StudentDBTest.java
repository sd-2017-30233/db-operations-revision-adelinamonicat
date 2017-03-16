package database;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Student;

public class StudentDBTest {

	private StudentDB studentDB;

	@Before
	public void setUp() {
		studentDB = new StudentDB();
	}

	@Test
	public void addStudentTest() throws SQLException {
		Student student = new Student("adelina", "13-02-1993", "adresa pt adelina Update");
		Student studentResult = new Student();
		studentDB.addStudent(student);
		studentResult = studentDB.readStudent(student.getName());
		studentDB.deleteStudent(studentResult.getId());
		Assert.assertEquals(student.getName(), studentResult.getName());
		Assert.assertEquals(student.getAddress(), studentResult.getAddress());
		Assert.assertEquals(student.getDate(), studentResult.getDate());

	}

	@Test
	public void updateStudentTest() throws SQLException {
		Student student = new Student("adelina", "13-02-1993", "adresa pt adelina Update");
		Student studentResult = new Student();
		studentDB.addStudent(student);
		student.setAddress("adddddddddd");
		studentDB.updateStudent(student);
		studentResult = studentDB.readStudent(student.getName());
		studentDB.deleteStudent(studentResult.getId());
		Assert.assertEquals(student.getName(), studentResult.getName());
		Assert.assertEquals("adddddddddd", studentResult.getAddress());
		Assert.assertEquals(student.getDate(), studentResult.getDate());

	}

	@Test(expected = SQLException.class)
	public void deleteCourseTest() throws SQLException {
		Student student = new Student("adelina", "13-02-1993", "adresa pt adelina Update");
		Student studentResult = new Student();
		studentDB.addStudent(student);
		studentResult = studentDB.readStudent(student.getName());
		studentDB.deleteStudent(studentResult.getId());
		studentDB.readStudent("adelina");
	}

}
