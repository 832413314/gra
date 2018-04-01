package net.hncu.hcq.action;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller("teacherInfoAction")
@Scope("prototype")
public class TeacherInfoAction extends BaseAction<Teacher> {
	private String newPassword;

	public String infoModifyUI() throws Exception {
		Teacher teacher = teacherService.getById(model.getId());
		ActionContext.getContext().put("teacher", teacher);
		return "infoModifyUI";
	}

	public String infoModify() throws Exception {

		Teacher t = teacherService.findByIdAndPassword(model.getId(),
				model.getPassword());

		if (t == null) {
			addFieldError("login", "Password Eroor!");
			return "infoModifyUI";
		} else {
			// 登录用户
			t.setName(model.getName());
			t.setPhone(model.getPhone());
			t.setEmail(model.getEmail());
			t.setDescription(model.getDescription());
			teacherService.update(t);
			return "infoModify";
		}

	}

	public String passwordModifyUI() throws Exception {
		Teacher teacher = teacherService.getById(model.getId());
		ActionContext.getContext().put("teacher", teacher);
		return "passwordModifyUI";
	}

	public String passwordModify() throws Exception {
		Teacher teacher = teacherService.findByIdAndPassword(model.getId(),
				model.getPassword());
		if (teacher == null) {
			addFieldError("login", "Password Error!");
			return "passwordModifyUI";
		} else {
			// 登录用户
			teacher.setPassword(newPassword);
			teacherService.update(teacher);
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
