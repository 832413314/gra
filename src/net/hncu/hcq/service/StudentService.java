package net.hncu.hcq.service;

import java.util.List;

import net.hncu.hcq.base.DaoSupport;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;



import com.opensymphony.xwork2.ActionContext;


public interface StudentService extends DaoSupport<Student> {

	//enter check
	Student findByIdAndPassword(Long id, String password);


	// 查询自己的课程
	Course getMyCourse(Long id);

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
