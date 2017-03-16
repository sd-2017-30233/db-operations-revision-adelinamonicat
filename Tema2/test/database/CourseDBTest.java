package database;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Course;

public class CourseDBTest {

	private CourseDB courseDB;

	@Before
	public void setUp() {
		courseDB = new CourseDB();
	}

	@Test
	public void addCourseTest() throws SQLException {
		Course course = new Course("nou", "teacher", 890);
		Course courseResult = new Course();
		courseDB.addCourse(course);
		courseResult = courseDB.readCourse(course.getName());
		courseDB.deleteCourse(courseResult.getId());
		Assert.assertEquals(course.getName(), courseResult.getName());
		Assert.assertEquals(course.getTeacher(), courseResult.getTeacher());
		Assert.assertEquals(course.getYear(), courseResult.getYear());
	}

	@Test
	public void updateCourseTest() throws SQLException {
		Course course = new Course("nou", "teacher", 890);
		Course courseResult = new Course();
		courseDB.addCourse(course);
		course.setTeacher("nou nou");
		courseDB.updateCourse(course);
		courseResult = courseDB.readCourse(course.getName());
		courseDB.deleteCourse(courseResult.getId());
		Assert.assertEquals(course.getName(), courseResult.getName());
		Assert.assertEquals("nou nou", courseResult.getTeacher());
		Assert.assertEquals(course.getYear(), courseResult.getYear());
	}

	@Test(expected = SQLException.class)
	public void deleteCourseTest() throws SQLException {
		Course course = new Course("nou", "teacher", 890);
		Course courseResult = new Course();
		courseDB.addCourse(course);
		courseResult = courseDB.readCourse(course.getName());
		courseDB.deleteCourse(courseResult.getId());
		courseDB.readCourse("nou");
	}

}
