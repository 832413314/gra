package net.hncu.hcq.domain;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
	private Long id;
	private String name;
	private String password;
	private String description;
	private String phone;
	private String email;
	//如果是男的 则为TRUE
	private boolean sex;
	// 课程编号
	private Long courseNumber;
	private Set<Student> students;

	private Set<Course> courses = new HashSet<Course>();

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Long courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

}
