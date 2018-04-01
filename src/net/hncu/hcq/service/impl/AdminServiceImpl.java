package net.hncu.hcq.service.impl;

import net.hncu.hcq.base.DaoSupportImpl;
import net.hncu.hcq.domain.Administrator;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.AdminService;
import net.hncu.hcq.service.CourseService;
import net.hncu.hcq.service.StudentService;
import net.hncu.hcq.service.TeacherService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl extends DaoSupportImpl<Administrator> implements
		AdminService {

	public Administrator findByIdAndPassword(Long id, String password) {
		// 使用密码的MD5摘要进行对比

		// String md5Digest = DigestUtils.md5Hex(password);
		// return (User) getSession().createQuery(//
		// "FROM User u WHERE u.loginName=? AND u.password=?")//
		// .setParameter(0, loginName)//
		// .setParameter(1, md5Digest)//
		// .uniqueResult();

		return (Administrator) getSession()
				.createQuery(
						"FROM Administrator t WHERE t.id=? AND t.password=?")
				.setParameter(0, id).setParameter(1, password).uniqueResult();
	}

}
