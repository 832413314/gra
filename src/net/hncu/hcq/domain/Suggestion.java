package net.hncu.hcq.domain;

public class Suggestion {
	private Long id;
	private String open_sug;
	private String middle_sug;
	private String thesis_sug;
	private String defence_sug;
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpen_sug() {
		return open_sug;
	}

	public void setOpen_sug(String open_sug) {
		this.open_sug = open_sug;
	}

	public String getMiddle_sug() {
		return middle_sug;
	}

	public void setMiddle_sug(String middle_sug) {
		this.middle_sug = middle_sug;
	}

	public String getThesis_sug() {
		return thesis_sug;
	}

	public void setThesis_sug(String thesis_sug) {
		this.thesis_sug = thesis_sug;
	}

	public String getDefence_sug() {
		return defence_sug;
	}

	public void setDefence_sug(String defence_sug) {
		this.defence_sug = defence_sug;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
