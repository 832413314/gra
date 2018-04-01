package net.hncu.hcq.service;

import java.util.List;

import net.hncu.hcq.base.DaoSupport;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Teacher;



import com.opensymphony.xwork2.ActionContext;


public interface TeacherService extends DaoSupport<Teacher> {

	Teacher findByIdAndPassword(Long id, String password);

	List<Course> getMyCourses(Long id);

	void chooseStudent(Long id, Long studentId);

	// // 查询所有
	// List<Role> findAll();
	//
	// void delete(Long id);
	//
	// void save(Role role);
	//
	// Role getById(Long id);
	//
	// void update(Role role);
	

}
