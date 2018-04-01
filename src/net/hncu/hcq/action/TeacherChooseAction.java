package net.hncu.hcq.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TeacherChooseAction extends BaseAction<Course> {

	private Long teacherId;
	private Long studentId;

	// 下面是文件上传的
	// 注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;
	// 提交过来的file的名字
	private String fileFileName;
	// 提交过来的file的MIME类型
	private String fileContentType;

	// 发布课题页面
	public String publishUI() throws Exception {
		return "publishUI";
	}

	// 发布课题
	public String publish() throws Exception {

		Teacher teacher = (Teacher) ActionContext.getContext().getSession()
				.get("teacher");
		// -------下面是把设置课程ID为 年份+老师工号+课程编号（1.2.3等 ）
		String teacherId = String.valueOf(teacher.getId());
		long couN = teacher.getCourseNumber();

		String courseNumber = String.valueOf(couN < 10 ? ("0" + couN) : couN);
		// courseTId课程临时的ID 用来转调试 和转成下面的long类型做铺垫
		String courseTId = 2016 + teacherId + courseNumber;
		long courseId = Long.parseLong(courseTId);
		System.out.println(courseId);
		// 把编号加1
		teacher.setCourseNumber(teacher.getCourseNumber() + 1);

		// 为课程文件做填写
		Course c = new Course();
		c.setId(courseId);
		c.setName(model.getName());
		c.setDescription(model.getDescription());
		c.setTeacher(teacher);
		c.setBeChoosed(false);
		c.setIsFull(false);
		courseService.save(c);
		teacherService.update(teacher);

		// 路径
		String root = ServletActionContext.getServletContext().getRealPath(
				"/missionDoc");

		InputStream is = new FileInputStream(file);

		// 把任务书的名字先 定位老师名字+.doc
		OutputStream os = new FileOutputStream(new File(root,
				(courseId + ".doc")));

		System.out.println("fileFileName: " + fileFileName);

		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		System.out.println("file: " + file.getName());
		System.out.println("file: " + file.getPath());

		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();

		return "publish";
	}

	// 选择学生页面
	public String chooseStuUI() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession()
				.get("teacher");
		// 找到该老师发布的课程(find this teacher's Courses)
		List<Course> teacherCourses = teacherService.getMyCourses(teacher
				.getId());
		ActionContext.getContext().put("teacherCourses", teacherCourses);
		return "chooseStuUI";
	}

	// 选择学生
	public String chooseStu() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession()
				.get("teacher");

		// 得到所选课程对象
		Course course = courseService.getById(model.getId());

		// 为该课程设置学生
		course.setStudent(studentService.getById(studentId));

		// 为课程设置成满员
		course.setIsFull(true);

		// 为该学生确定课程
		Student student = studentService.getById(studentId);
		student.setCourse(course);
		student.setTeacher(teacher);

		// 为学生设置成满员
		student.setIsFull(true);
		studentService.update(student);
		return "chooseStu";
	}

	// 查看课题信息
	public String courseList() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession()
				.get("teacher");

		List<Course> teacherCourses = teacherService.getMyCourses(teacher
				.getId());
		ActionContext.getContext().put("teacherCourses", teacherCourses);
		return "courseList";
	}

	// 取消学生申请
	public String cancelChoose() throws Exception {
		Teacher teacher = (Teacher) ActionContext.getContext().getSession()
				.get("teacher");
		// 得到该课程对象
		Course course = courseService.getById(model.getId());
		// 得到该学生对象
		Student student = studentService.getById(studentId);
		student.setIsChoosed(false);
		course.setBeChoosed(false);
		student.setTeacher(null);
		studentService.update(student);
		courseService.update(course);
		return "cancelChoose";
	}

	// 删除该课程
	public String deleteCourse() throws Exception {
		// 得到该课程对象
		Course course = courseService.getById(model.getId());

		if (course.getIsFull()) {
			Student student = studentService.getById(studentId);
			student.setIsChoosed(false);
			student.setCourse(null);
			student.setIsFull(false);
			student.setTeacher(null);
			studentService.update(student);
			course.setBeChoosed(false);
		}
		if (course.getBeChoosed()) {
			System.out.println("false");
			Student student = studentService.getById(studentId);
			student.setIsChoosed(false);
			student.setTeacher(null);
			studentService.update(student);
		}
		// 考虑用注入？
		if (course.getGrade() != null) {
			long gradeId = course.getGrade().getId();
			long suggetionId = course.getSuggestion().getId();
			courseService.delete(model.getId());
			gradeService.delete(gradeId);
			suggestionService.delete(suggetionId);
		}
		if (course.getGrade() == null) {
			courseService.delete(model.getId());
		}

		return "deleteCourse";
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

}
