package net.hncu.hcq.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Grade;
import net.hncu.hcq.domain.Suggestion;
import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.impl.CourseServiceImpl;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TeacherProcessAction extends BaseAction<Course> {

	private int open_grade;
	private String open_sugg;

	private int middle_grade;
	private String middle_sugg;

	private int thesis1_grade;
	private int thesis2_grade;
	private int thesis3_grade;
	private int thesis4_grade;
	private int thesis5_grade;
	private int thesis6_grade;
	private int thesis7_grade;
	private int thesis8_grade;
	private int thesis9_grade;
	private int thesis10_grade;
	private int thesis11_grade;

	private File file;
	private String fileFileName;
	private String fileContentType;

	// 开题文档审查页面
	public String openDocStuListUI() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
		// 找到该老师发布的课程(find this teacher's Courses)
		List<Course> teacherCourses = teacherService.getMyCourses(teacher.getId());
		ActionContext.getContext().put("teacherCourses", teacherCourses);
		return "openDocStuListUI";
	}

	// 开题审查评分意见页面
	public String openDocCheckUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("course", c);

		// 回显问题
		// Grade g = c.getGrade();
		// if (g != null) {
		// // ActionContext.getContext().put("grade", g);
		// ActionContext.getContext().getValueStack().push(g);
		// }
		// Suggestion s = c.getSuggestion();
		// if (s != null) {
		// ActionContext.getContext().put("suggestion", s);
		// }

		return "openDocCheckUI";
	}

	// 开题审查评分意见提交
	public String openDocCheck() throws Exception {

		Course c = courseService.getById(model.getId());

		// 如果是第一次批改学生成绩 及生成一个Grade对象\

		//
		if (c.getGrade() == null && c.getSuggestion() == null) {
			Suggestion s = new Suggestion();
			Grade g = new Grade();
			g.setOpen_grade(open_grade);
			gradeService.save(g);
			s.setOpen_sug(open_sugg);
			suggestionService.save(s);
			c.setGrade(g);
			c.setSuggestion(s);

		}
		if (c.getGrade() != null && c.getSuggestion() != null) {
			c.getGrade().setOpen_grade(open_grade);
			c.getSuggestion().setOpen_sug(open_sugg);
		}

		courseService.update(c);
		return "success";
	}

	// 带文档的审查
	public String openDocCheckDocUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("course", c);

		return "openDocCheckDocUI";
	}

	// 中期文档审查页面
	public String openDocCheckDoc() throws Exception {

		Course c = courseService.getById(model.getId());
		c.getGrade().setOpen_grade(open_grade);

		long courseId = c.getId();
		String root = ServletActionContext.getServletContext().getRealPath("/openCheckDoc");

		InputStream is = new FileInputStream(file);

		OutputStream os = new FileOutputStream(new File(root, (courseId + ".doc")));

		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();

		courseService.update(c);

		return "success";
	}

	public String middleDocStuListUI() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
		// 找到该老师发布的课程(find this teacher's Courses)
		List<Course> teacherCourses = teacherService.getMyCourses(teacher.getId());
		ActionContext.getContext().put("teacherCourses", teacherCourses);

		return "middleDocStuListUI";
	}

	// 中期文档评分意见页面
	public String middleDocCheckUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("course", c);
		return "middleDocCheckUI";
	}

	public String middleDocCheckDocUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("course", c);

		return "middleDocCheckDocUI";
	}

	// 中期文档评分意见提交
	public String middleDocCheckDoc() throws Exception {

		Course c = courseService.getById(model.getId());
		c.getGrade().setMiddle_grade(middle_grade);

		long courseId = c.getId();
		String root = ServletActionContext.getServletContext().getRealPath("/middleCheckDoc");

		InputStream is = new FileInputStream(file);

		OutputStream os = new FileOutputStream(new File(root, (courseId + ".doc")));

		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();

		courseService.update(c);

		return "success";
	}

	// 中期文档评分意见提交
	public String middleDocCheck() throws Exception {

		Course c = courseService.getById(model.getId());
		c.getGrade().setMiddle_grade(middle_grade);
		c.getSuggestion().setMiddle_sug(middle_sugg);
		courseService.update(c);

		return "success";
	}

	// 论文审查学生页面
	public String thesisStuListUI() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
		// 找到该老师发布的课程(find this teacher's Courses)
		List<Course> teacherCourses = teacherService.getMyCourses(teacher.getId());
		ActionContext.getContext().put("teacherCourses", teacherCourses);
		return "thesisStuListUI";
	}

	// 论文审查评分页面
	public String thesisDocCheckUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("course", c);
		return "thesisCheckUI";
	}

	// 论文审查评分页面
	public String thesisCheck() throws Exception {
		/*
		 * 
		 * Course c = courseService.getById(model.getId());
		 * 
		 * if (thesis_grade != null) { int thesis_grad =
		 * int.parseint(thesis_grade);
		 * c.getGrade().setThesis_grade(thesis_grad); } if (thesis_sugg != null)
		 * { c.getSuggestion().setThesis_sug(thesis_sugg); }
		 * courseService.update(c);
		 */

		return "success";
	}

	public String thesisDocCheckDocUI() throws Exception {
		Course c = courseService.getById(model.getId());
		ActionContext.getContext().put("course", c);

		return "thesisDocCheckDocUI";
	}

	public String thesisDocCheckDoc() throws Exception {

		Course c = courseService.getById(model.getId());
		c.getGrade().setThesis1_grade(thesis1_grade);
		c.getGrade().setThesis2_grade(thesis2_grade);
		c.getGrade().setThesis3_grade(thesis3_grade);
		c.getGrade().setThesis4_grade(thesis4_grade);
		c.getGrade().setThesis5_grade(thesis5_grade);
		c.getGrade().setThesis6_grade(thesis6_grade);
		c.getGrade().setThesis7_grade(thesis7_grade);
		c.getGrade().setThesis8_grade(thesis8_grade);
		c.getGrade().setThesis9_grade(thesis9_grade);
		c.getGrade().setThesis10_grade(thesis10_grade);
		c.getGrade().setThesis11_grade(thesis11_grade);
		courseService.update(c);
		return "success";
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public int getOpen_grade() {
		return open_grade;
	}

	public void setOpen_grade(int open_grade) {
		this.open_grade = open_grade;
	}

	public String getOpen_sugg() {
		return open_sugg;
	}

	public void setOpen_sugg(String open_sugg) {
		this.open_sugg = open_sugg;
	}

	public int getMiddle_grade() {
		return middle_grade;
	}

	public void setMiddle_grade(int middle_grade) {
		this.middle_grade = middle_grade;
	}

	public String getMiddle_sugg() {
		return middle_sugg;
	}

	public void setMiddle_sugg(String middle_sugg) {
		this.middle_sugg = middle_sugg;
	}

	public int getThesis1_grade() {
		return thesis1_grade;
	}

	public void setThesis1_grade(int thesis1_grade) {
		this.thesis1_grade = thesis1_grade;
	}

	public int getThesis2_grade() {
		return thesis2_grade;
	}

	public void setThesis2_grade(int thesis2_grade) {
		this.thesis2_grade = thesis2_grade;
	}

	public int getThesis3_grade() {
		return thesis3_grade;
	}

	public void setThesis3_grade(int thesis3_grade) {
		this.thesis3_grade = thesis3_grade;
	}

	public int getThesis4_grade() {
		return thesis4_grade;
	}

	public void setThesis4_grade(int thesis4_grade) {
		this.thesis4_grade = thesis4_grade;
	}

	public int getThesis5_grade() {
		return thesis5_grade;
	}

	public void setThesis5_grade(int thesis5_grade) {
		this.thesis5_grade = thesis5_grade;
	}

	public int getThesis6_grade() {
		return thesis6_grade;
	}

	public void setThesis6_grade(int thesis6_grade) {
		this.thesis6_grade = thesis6_grade;
	}

	public int getThesis7_grade() {
		return thesis7_grade;
	}

	public void setThesis7_grade(int thesis7_grade) {
		this.thesis7_grade = thesis7_grade;
	}

	public int getThesis8_grade() {
		return thesis8_grade;
	}

	public void setThesis8_grade(int thesis8_grade) {
		this.thesis8_grade = thesis8_grade;
	}

	public int getThesis9_grade() {
		return thesis9_grade;
	}

	public void setThesis9_grade(int thesis9_grade) {
		this.thesis9_grade = thesis9_grade;
	}

	public int getThesis10_grade() {
		return thesis10_grade;
	}

	public void setThesis10_grade(int thesis10_grade) {
		this.thesis10_grade = thesis10_grade;
	}

	public int getThesis11_grade() {
		return thesis11_grade;
	}

	public void setThesis11_grade(int thesis11_grade) {
		this.thesis11_grade = thesis11_grade;
	}

}
