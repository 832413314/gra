package net.hncu.hcq.service.impl;

import java.util.List;


import net.hncu.hcq.base.DaoSupportImpl;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.TeacherService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class TeacherServiceImpl extends DaoSupportImpl<Teacher> implements
		TeacherService {

	public Teacher findByIdAndPassword(Long id, String password) {
		// 使用密码的MD5摘要进行对比

		// String md5Digest = DigestUtils.md5Hex(password);
		// return (User) getSession().createQuery(//
		// "FROM User u WHERE u.loginName=? AND u.password=?")//
		// .setParameter(0, loginName)//
		// .setParameter(1, md5Digest)//
		// .uniqueResult();

		return (Teacher) getSession()
				.createQuery("FROM Teacher t WHERE t.id=? AND t.password=?")
				.setParameter(0, id).setParameter(1, password).uniqueResult();

	}

	// 取得我发布的课程(get My Courses By id)
	public List<Course> getMyCourses(Long id) {

		return (List<Course>) getSession()
				.createQuery("select courses FROM Teacher t WHERE t.id=?")//
				.setParameter(0, id)//
				.list();
	}

	public void chooseStudent(Long id, Long studentId) {
	}


}
