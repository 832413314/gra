package net.hncu.hcq.action;

import java.util.HashSet;
import java.util.List;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class StudentDefenceAction extends BaseAction<Student> {

	// 各阶段分数及总分页面
	public String gradeList() throws Exception {
		Student s = (Student) ActionContext.getContext().getSession()
				.get("student");
		Course course = studentService.getMyCourse(s.getId());
		ActionContext.getContext().put("course", course);

		return "gradeList";
	}

	// 答辩时间
	public String defenceTime() throws Exception {
		return "defenceTime";
	}

}
