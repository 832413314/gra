package net.hncu.hcq.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class StudentChooseAction extends BaseAction<Course> {

	private Long studentId;

	// 选择课题页面
	public String chooseCourUI() throws Exception {
		List<Course> publishedCourses = courseService.findAll();
		ActionContext.getContext().put("publishedCourses", publishedCourses);

		Student student = studentService.getById(model.getId());
		ActionContext.getContext().put("student", student);
		return "chooseCourUI";
	}

	// 选择课题
	public String chooseCour() throws Exception {
		Course course = courseService.getById(model.getId());
		Student student = studentService.getById(studentId);
		// 把该学生改为确认已经选择课题
		student.setIsChoosed(true);
		// student.setTeacher(course.getTeacher());
		studentService.update(student);
		course.setStudent(student);
		course.setBeChoosed(true);
		// 保存课程的时候会顺带把上面student修改完的字段（setIsChoosed）一起修改了
		// 所以就不用特地在用student的update方法
		courseService.update(course);
		return "chooseCour";
	}

	// 查看我的课题
	public String myCourse() throws Exception {
		Student s = (Student) ActionContext.getContext().getSession()
				.get("student");
		Course course = studentService.getMyCourse(s.getId());

		ActionContext.getContext().put("course", course);
		Student student = studentService.getById(model.getId());
		ActionContext.getContext().put("student", student);
		return "myCourse";
	}

	public String teacherInfo() throws Exception {
		Course course = courseService.getById(model.getId());
		Teacher t = course.getTeacher();
		ActionContext.getContext().put("teacher", t);
		return "teacherInfo";
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

}
