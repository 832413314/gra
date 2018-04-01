package net.hncu.hcq.service.impl;

import java.util.Collection;
import java.util.List;


import net.hncu.hcq.base.DaoSupportImpl;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.CourseService;
import net.hncu.hcq.service.StudentService;
import net.hncu.hcq.service.TeacherService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class StudentServiceImpl extends DaoSupportImpl<Student> implements
		StudentService {

	private static final String Course = null;

	public Student findByIdAndPassword(Long id, String password) {
		// 使用密码的MD5摘要进行对比

		// String md5Digest = DigestUtils.md5Hex(password);
		// return (User) getSession().createQuery(//
		// "FROM User u WHERE u.loginName=? AND u.password=?")//
		// .setParameter(0, loginName)//
		// .setParameter(1, md5Digest)//
		// .uniqueResult();

		return (Student) getSession()
				.createQuery("FROM Student s WHERE s.id=? AND s.password=?")
				.setParameter(0, id).setParameter(1, password).uniqueResult();

	}

	
	public Course getMyCourse(Long id) {
		return (Course) getSession()
				.createQuery("FROM Course c WHERE c.student.id=?")//
				.setParameter(0, id)//
				.uniqueResult();
	}


	public List<net.hncu.hcq.domain.Course> getMyApplication(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
