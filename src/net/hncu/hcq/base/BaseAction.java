package net.hncu.hcq.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	// =============== ModelDriven的支持 ==================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================
	@Resource
	protected TeacherService teacherService;

	@Resource
	protected StudentService studentService;

	@Resource
	protected CourseService courseService;

	@Resource
	protected SuggestionService suggestionService;

	@Resource
	protected GradeService gradeService;

	@Resource
	protected AdminService adminService;
	
	@Resource
	protected NoticeService noticeService;
}
