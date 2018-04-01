package net.hncu.hcq.action;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Notice;
import net.hncu.hcq.domain.Teacher;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("tHomeAction")
@Scope("prototype")
public class THomeAction extends BaseAction<Teacher> {

	public String index() throws Exception {
		// 检测是否有session存在
		if (ActionContext.getContext().getSession().get("teacher") == null) {
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
		// ActionContext.getContext().getValueStack().push()
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

		Teacher teacher = teacherService.findByIdAndPassword(model.getId(), model.getPassword());
		if (teacher == null) {
			addFieldError("login", "Password Eroor!");
			return "loginUI";
		} else {
			// 登录用户
			ActionContext.getContext().getSession().put("teacher", teacher);
			return "toIndex";

		}
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("teacher");
		return "logout";
	}

}
