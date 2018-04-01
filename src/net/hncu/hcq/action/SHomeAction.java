package net.hncu.hcq.action;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Notice;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("sHomeAction")
@Scope("prototype")
public class SHomeAction extends BaseAction<Student> {

	public String index() throws Exception {
		if (ActionContext.getContext().getSession().get("student") == null) {
			return "loginUI";
		}
		return "index";
	}

	public String top() throws Exception {
		return "top";
	}

	public String bottom() throws Exception {
		return "bottom";
	}

	public String left() throws Exception {
		return "left";
	}

	public String right() throws Exception {
		List<Notice> notices = noticeService.findAll();
		ActionContext.getContext().put("notices", notices);
		return "right";
	}

	public String loginUI() throws Exception {
		return "loginUI";
	}

	public String login() {

		Student student = studentService.findByIdAndPassword(model.getId(), model.getPassword());
		if (student == null) {
			addFieldError("login", "密码错误,请重新输入");
			return "loginUI";

		} else {
			// 登录用户
			ActionContext.getContext().getSession().put("student", student);
			return "toIndex";

		}
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("student");
		return "logout";
	}

}
