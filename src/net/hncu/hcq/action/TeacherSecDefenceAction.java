package net.hncu.hcq.action;

import java.util.HashSet;
import java.util.List;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Teacher;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TeacherSecDefenceAction extends BaseAction<Teacher> {

	// 二辩学生列表页面
	public String stuListUI() throws Exception {
		return "stuListUI";
	}

	// 二辩学生列表页面（有评分功能）
	public String cStuListUI() throws Exception {
		return "cStuListUI";
	}

	// 论文审查评分页面
	public String thesisCheckUI() throws Exception {
		return "thesisCheckUI";
	}

	// 论文审查评分
	public String thesisCheck() throws Exception {
		return "thesisCheck";
	}

}
