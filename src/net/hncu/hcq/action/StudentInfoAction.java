package net.hncu.hcq.action;


import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller("studentInfoAction")
@Scope("prototype")
public class StudentInfoAction extends BaseAction<Student> {
	private String newPassword;

	public String infoModifyUI() throws Exception {
		Student student = studentService.getById(model.getId());
		ActionContext.getContext().put("student", student);
		return "infoModifyUI";
	}

	public String infoModify() throws Exception {

		Student student = studentService.findByIdAndPassword(model.getId(),
				model.getPassword());

		if (student == null) {
			addFieldError("login", "Password Eroor!");
			return "infoModifyUI";
		} else {
			// 登录用户
			student.setName(model.getName());
			student.setPhone(model.getPhone());
			student.setEmail(model.getEmail());
			student.setMajor(model.getMajor());
			studentService.update(student);
			return "infoModify";

		}

	}

	public String passwordModifyUI() throws Exception {
		Student student = studentService.getById(model.getId());
		ActionContext.getContext().put("student", student);
		return "passwordModifyUI";
	}

	public String passwordModify() throws Exception {
		Student student = studentService.findByIdAndPassword(model.getId(),
				model.getPassword());
		if (student == null) {
			addFieldError("login", "Password Error!");
			return "passwordModifyUI";
		} else {
			// 登录用户
			student.setPassword(newPassword);
			studentService.update(student);
			return "passwordModify";
		}
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
