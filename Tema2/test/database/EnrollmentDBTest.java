package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.Course;
import model.Student;

public class EnrollmentDBTest {

	private StudentDB studentDB;
	private CourseDB courseDB;
	private EnrollmentDB enrollDB;
	private List<Student> list;

	@Before
	public void setUp() {
		studentDB = new StudentDB();
		courseDB = new CourseDB();
		enrollDB = new EnrollmentDB();
		list = new ArrayList<Student>();
	}

	@Test
	public void enrollStudentToCourseTest() throws SQLException {
		Student student = new Student("adelina", "13-02-1993", "adresa pt adelina Update");
		Course course = new Course("nou curs", "nume curs", 2017);
		studentDB.addStudent(student);
		courseDB.addCourse(course);
		enrollDB.enrollStudentToCourse(student, course);
		list = enrollDB.readStudentsInCourse(course.getName());
		studentDB.deleteStudent(studentDB.readStudent(student.getName()).getId());
		courseDB.deleteCourse(courseDB.readCourse(course.getName()).getId());
		Assert.assertEquals(list.size(), 1);
	}

	@Test
	public void readStudentsInCourseTest() throws SQLException {
		Student student = new Student("adelina", "13-02-1993", "adresa pt adelina Update");
		Student student1 = new Student("adelina1", "13-02-1993", "adresa pt adelina Update");
		Student student2 = new Student("adelina2", "13-02-1993", "adresa pt adelina Update");
		Course course = new Course("nou curs", "nume curs", 2017);
		studentDB.addStudent(student);
		studentDB.addStudent(student1);
		studentDB.addStudent(student2);
		courseDB.addCourse(course);
		enrollDB.enrollStudentToCourse(student, course);
		enrollDB.enrollStudentToCourse(student1, course);
		enrollDB.enrollStudentToCourse(student2, course);
		list = enrollDB.readStudentsInCourse(course.getName());
		studentDB.deleteStudent(studentDB.readStudent(student.getName()).getId());
		studentDB.deleteStudent(studentDB.readStudent(student1.getName()).getId());
		studentDB.deleteStudent(studentDB.readStudent(student2.getName()).getId());
		courseDB.deleteCourse(courseDB.readCourse(course.getName()).getId());
		Assert.assertEquals(list.size(), 3);
	}

}
