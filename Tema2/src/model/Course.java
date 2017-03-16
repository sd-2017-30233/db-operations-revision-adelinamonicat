package model;

public class Course {

	public int id;
	public String name;
	public String teacher;
	public int year;

	public Course( String name, String teacher, int year) {
		super();
		this.name = name;
		this.teacher = teacher;
		this.year = year;
	}

	public Course() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", teacher=" + teacher + ", year=" + year + "]";
	}

}
