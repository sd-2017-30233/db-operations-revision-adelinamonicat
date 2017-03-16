package model;

public class Student {
	
	public int id;
	public String name;
	public String date;
	public String address;
	
	public Student( String name, String date, String address) {
		super();
		this.name = name;
		this.date = date;
		this.address = address;
	}

	public Student() {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", date=" + date + ", address=" + address + "]";
	}
	
	

}
