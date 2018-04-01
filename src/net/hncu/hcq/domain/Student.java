package net.hncu.hcq.domain;

public class Student {
	private Long id;
	private String name;
	private String password;

	private String major;
	private String phone;
	private String email;
	private boolean sex;

	private Boolean isChoosed;
	private Boolean isFull;
	private Boolean hadPassed;
	private Teacher teacher;
	private Course course;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsFull() {
		return isFull;
	}

	public void setIsFull(Boolean isFull) {
		this.isFull = isFull;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getHadPassed() {
		return hadPassed;
	}

	public void setHadPassed(Boolean hadPassed) {
		this.hadPassed = hadPassed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsChoosed() {
		return isChoosed;
	}

	public void setIsChoosed(Boolean isChoosed) {
		this.isChoosed = isChoosed;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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
