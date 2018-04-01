package net.hncu.hcq.service.impl;

import net.hncu.hcq.base.DaoSupportImpl;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Suggestion;
import net.hncu.hcq.domain.Teacher;
import net.hncu.hcq.service.CourseService;
import net.hncu.hcq.service.StudentService;
import net.hncu.hcq.service.SuggestionService;
import net.hncu.hcq.service.TeacherService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SuggestionServiceImpl extends DaoSupportImpl<Suggestion> implements
		SuggestionService {

}
