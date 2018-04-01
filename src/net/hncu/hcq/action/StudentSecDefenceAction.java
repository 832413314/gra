package net.hncu.hcq.action;

import java.util.HashSet;
import java.util.List;


import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Student;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;




import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class StudentSecDefenceAction extends BaseAction<Student> {

	//二辩文档提交页面
	public String secDefenceSubUI() throws Exception {
		return "secDefenceSubUI";
	}

	//阶段文档提交	
	public String secDefenceSub() throws Exception {
		return "secDefenceSub";
	}

	//二辩成绩查询
	public String secondGrade() throws Exception {
		return "secondGrade";
	}

}
