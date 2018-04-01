package net.hncu.hcq.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Administrator;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Notice;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction<Administrator> {

	// 不分学生跟老师的了
	private String description;
	private String phone;
	private String email;
	private String major;
	private String newPassword;
	// 公告内容
	private String content;

	private int thesis12_grade;
	private int thesis13_grade;
	private int thesis14_grade;
	private int thesis15_grade;
	private int thesis16_grade;
	private int thesis17_grade;
	private int thesis18_grade;
	private int thesis19_grade;
	private int thesis20_grade;
	private int thesis21_grade;

	public String addStuUI() throws Exception {
		return "addStuUI";
	}

	public String addStu() throws Exception {
		Student s = new Student();
		s.setId(model.getId());
		s.setName(model.getName());
		s.setPassword("123456");
		s.setHadPassed(false);
		s.setIsChoosed(false);
		s.setIsFull(false);
		studentService.save(s);
		return "addStu";
	}

	public String addTeacherUI() throws Exception {
		return "addTeacherUI";
	}

	public String addTeacher() throws Exception {

		System.out.println("add t successful");
		System.out.println(model.getId() + "  name :  " + model.getName());
		Teacher t = new Teacher();
		t.setId(model.getId());
		t.setName(model.getName());
		t.setPassword("123456");
		t.setCourseNumber(1l);
		teacherService.save(t);
		return "addTeacher";
	}

	public String courseList() throws Exception {
		List<Course> courses = courseService.findAll();
		ActionContext.getContext().put("courses", courses);
		return "courseList";
	}

	public String studentList() throws Exception {
		List<Student> students = studentService.findAll();
		ActionContext.getContext().put("students", students);
		return "studentList";
	}

	public String teacherList() throws Exception {
		List<Teacher> teachers = teacherService.findAll();
		ActionContext.getContext().put("teachers", teachers);
		return "teacherList";
	}

	public String publishNoticeUI() throws Exception {
		Administrator admin = adminService.getById(model.getId());
		ActionContext.getContext().put("admin", admin);
		return "publishNoticeUI";
	}

	public String publishNotice() throws Exception {
		Administrator admin = adminService.findByIdAndPassword(model.getId(), model.getPassword());
		if (admin == null) {
			addFieldError("login", "密码不正确！");
			return "publishNoticeUI";
		} else {
			Notice n = new Notice();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			n.setPublishTime(sf.format(d));
			n.setContent(content);
			noticeService.save(n);
			return "publishNotice";
		}
	}

	public String noticeDelete() {
		noticeService.delete(model.getId());
		return "noticeDelete";
	}

	public String noticeModifyUI()

	{
		Notice notice = noticeService.getById(model.getId());
		ActionContext.getContext().put("notice", notice);
		return "noticeModifyUI";
	}

	public String noticeModify()

	{
		Notice n = noticeService.getById(model.getId());
		n.setContent(content);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		n.setPublishTime(sf.format(d));
		noticeService.update(n);
		return "noticeModify";
	}

	public String studentChooseInfo() throws Exception {
		List<Student> students = studentService.findAll();
		ActionContext.getContext().put("students", students);
		return "studentChooseInfo";
	}

	public String docuSearchUI() throws Exception {
		return "docuSearchUI";
	}

	public String gradeManage() throws Exception {
		List<Course> courses = courseService.findAll();
		ActionContext.getContext().put("courses", courses);
		return "gradeManage";
	}

	public String teacherInfoModifyUI() throws Exception {
		Teacher teacher = teacherService.getById(model.getId());
		ActionContext.getContext().put("teacher", teacher);
		return "teacherInfoModifyUI";
	}

	public String teacherInfoModify() throws Exception {
		Teacher t = teacherService.getById(model.getId());
		t.setName(model.getName());
		t.setPhone(phone);
		t.setEmail(email);
		t.setDescription(description);
		teacherService.update(t);
		return "teacherInfoModify";
	}

	public String studentInfoModifyUI() throws Exception {
		Student student = studentService.getById(model.getId());
		ActionContext.getContext().put("student", student);
		return "studentInfoModifyUI";
	}

	public String studentInfoModify() throws Exception {
		Student s = studentService.getById(model.getId());
		s.setName(model.getName());
		s.setPhone(phone);
		s.setEmail(email);
		s.setMajor(major);
		studentService.update(s);
		return "studentInfoModify";
	}

	public String courseInfoModifyUI() throws Exception {
		Course course = courseService.getById(model.getId());
		ActionContext.getContext().put("course", course);
		return "courseInfoModifyUI";
	}

	public String courseInfoModify() throws Exception {
		Course s = courseService.getById(model.getId());
		s.setName(model.getName());
		s.setDescription(description);
		courseService.update(s);
		return "courseInfoModify";
	}

	public String passwordModifyUI() throws Exception {
		Administrator admin = adminService.getById(model.getId());
		ActionContext.getContext().put("admin", admin);
		return "passwordModifyUI";
	}

	public String passwordModify() throws Exception {
		Administrator admin = adminService.findByIdAndPassword(model.getId(), model.getPassword());
		if (admin == null) {
			addFieldError("login", "Password Error!");
			return "passwordModifyUI";
		} else {
			// 登录用户
			admin.setPassword(getNewPassword());
			adminService.update(admin);
			return "passwordModify";
		}
	}

	public String materialInputList() throws Exception {
		List<Course> courses = courseService.findAll();
		ActionContext.getContext().put("courses", courses);
		return "materialInputList";
	}

	public String materialInputUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("courses", c);
		return "materialInputUI";
	}

	public String materialInput() throws Exception {
		Course c = courseService.getById(model.getId());
		c.getGrade().setThesis12_grade(thesis12_grade);
		c.getGrade().setThesis13_grade(thesis13_grade);
		c.getGrade().setThesis14_grade(thesis14_grade);
		c.getGrade().setThesis15_grade(thesis15_grade);
		c.getGrade().setThesis16_grade(thesis16_grade);
		c.getGrade().setThesis17_grade(thesis17_grade);
		courseService.update(c);
		return "materialInput";
	}

	public String defenceInputList() throws Exception {
		List<Course> courses = courseService.findAll();
		ActionContext.getContext().put("courses", courses);
		return "defenceInputList";
	}

	public String defenceInputUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("courses", c);
		return "defenceInputUI";
	}

	public String defenceInput() throws Exception {
		Course c = courseService.getById(model.getId());
		c.getGrade().setThesis18_grade(thesis18_grade);
		c.getGrade().setThesis19_grade(thesis19_grade);
		c.getGrade().setThesis20_grade(thesis20_grade);
		c.getGrade().setThesis21_grade(thesis21_grade);
		courseService.update(c);
		return "defenceInput";
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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getThesis12_grade() {
		return thesis12_grade;
	}

	public void setThesis12_grade(int thesis12_grade) {
		this.thesis12_grade = thesis12_grade;
	}

	public int getThesis13_grade() {
		return thesis13_grade;
	}

	public void setThesis13_grade(int thesis13_grade) {
		this.thesis13_grade = thesis13_grade;
	}

	public int getThesis14_grade() {
		return thesis14_grade;
	}

	public void setThesis14_grade(int thesis14_grade) {
		this.thesis14_grade = thesis14_grade;
	}

	public int getThesis15_grade() {
		return thesis15_grade;
	}

	public void setThesis15_grade(int thesis15_grade) {
		this.thesis15_grade = thesis15_grade;
	}

	public int getThesis16_grade() {
		return thesis16_grade;
	}

	public void setThesis16_grade(int thesis16_grade) {
		this.thesis16_grade = thesis16_grade;
	}

	public int getThesis17_grade() {
		return thesis17_grade;
	}

	public void setThesis17_grade(int thesis17_grade) {
		this.thesis17_grade = thesis17_grade;
	}

	public int getThesis18_grade() {
		return thesis18_grade;
	}

	public void setThesis18_grade(int thesis18_grade) {
		this.thesis18_grade = thesis18_grade;
	}

	public int getThesis19_grade() {
		return thesis19_grade;
	}

	public void setThesis19_grade(int thesis19_grade) {
		this.thesis19_grade = thesis19_grade;
	}

	public int getThesis20_grade() {
		return thesis20_grade;
	}

	public void setThesis20_grade(int thesis20_grade) {
		this.thesis20_grade = thesis20_grade;
	}

	public int getThesis21_grade() {
		return thesis21_grade;
	}

	public void setThesis21_grade(int thesis21_grade) {
		this.thesis21_grade = thesis21_grade;
	}

}
