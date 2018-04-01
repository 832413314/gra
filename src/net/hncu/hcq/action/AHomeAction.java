package net.hncu.hcq.action;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Administrator;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Notice;
import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.impl.AdminServiceImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("aHomeAction")
@Scope("prototype")
public class AHomeAction extends BaseAction<Teacher> {

	public String index() throws Exception {
		// 检测是否有session存在
		if (ActionContext.getContext().getSession().get("admin") == null) {
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
		List<Notice> notices =	noticeService.findAll();
		ActionContext.getContext().put("notices", notices);
		return "right";
	}

	public String loginUI() throws Exception {
		return "loginUI";
	}

	public String login() {

		Administrator admin = adminService.findByIdAndPassword(model.getId(),
				model.getPassword());
		if (admin == null) {
			addFieldError("login", "用户名或密码不正确！");
			return "loginUI";
		} else {
			// 登录管理员
			ActionContext.getContext().getSession().put("admin", admin);
			return "toIndex";

		}
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("admin");
		return "logout";
	}

}
