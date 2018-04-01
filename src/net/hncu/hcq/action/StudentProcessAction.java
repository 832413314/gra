package net.hncu.hcq.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Course;
import net.hncu.hcq.domain.Student;
import net.hncu.hcq.domain.Teacher;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class StudentProcessAction extends BaseAction<Course> {

	// 下面是文件上传的
	// 注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;
	// 提交过来的file的名字
	private String fileFileName;
	// 提交过来的file的MIME类型
	private String fileContentType;

	// 开题文档提交页面
	public String openDocSubUI() throws Exception {
		return "openDocSubUI";
	}

	// 开题文档提交
	public String openDocSub() throws Exception {

		Student student = (Student) ActionContext.getContext().getSession().get("student");

		long courseId = studentService.getMyCourse(student.getId()).getId();

		// 路径 写到openDoc文档里面，名字就按课题的id号
		String root = ServletActionContext.getServletContext().getRealPath("/openDoc");

		InputStream is = new FileInputStream(file);

		// 把任务书的名字先 定位老师名字+.doc
		OutputStream os = new FileOutputStream(new File(root, (courseId + ".doc")));

		System.out.println("fileFileName: " + fileFileName);

		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		System.out.println("file: " + file.getName());
		System.out.println("file: " + file.getPath());

		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();
		return "openDocSub";
	}

	// 中期文档提交页面
	public String middleDocSubUI() throws Exception {
		Student student = (Student) ActionContext.getContext().getSession().get("student");
		Course c = studentService.getMyCourse(student.getId());

		if (c.getGrade() == null || c.getGrade().getOpen_grade() < 60) {
			return "openGradeError";
		} else {
			return "middleDocSubUI";
		}
	}

	// 中期文档文档提交
	public String middleDocSub() throws Exception {

		Student student = (Student) ActionContext.getContext().getSession().get("student");

		long courseId = studentService.getMyCourse(student.getId()).getId();

		// 路径 写到openDoc文档里面，名字就按课题的id号
		String root = ServletActionContext.getServletContext().getRealPath("/middleDoc");

		InputStream is = new FileInputStream(file);

		// 把任务书的名字先 定位老师名字+.doc
		OutputStream os = new FileOutputStream(new File(root, (courseId + ".doc")));

		System.out.println("fileFileName: " + fileFileName);

		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		System.out.println("file: " + file.getName());
		System.out.println("file: " + file.getPath());

		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();

		return "middleDocSub";
	}

	// 论文提交页面
	public String thesisDocSubUI() throws Exception {
		Student student = (Student) ActionContext.getContext().getSession().get("student");
		Course c = studentService.getMyCourse(student.getId());

		if (c.getGrade() == null || c.getGrade().getMiddle_grade() < 60) {
			return "middleGradeError";
		} else {
			return "thesisDocSubUI";
		}
	}

	// 论文提交
	public String thesisDocSub() throws Exception {

		Student student = (Student) ActionContext.getContext().getSession().get("student");

		long courseId = studentService.getMyCourse(student.getId()).getId();

		// 路径 写到thesisDoc文档里面，名字就按课题的id号
		String root = ServletActionContext.getServletContext().getRealPath("/thesisDoc");

		InputStream is = new FileInputStream(file);

		// 把任务书的名字先 定位老师名字+.doc
		OutputStream os = new FileOutputStream(new File(root, (courseId + ".doc")));

		System.out.println("fileFileName: " + fileFileName);

		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		System.out.println("file: " + file.getName());
		System.out.println("file: " + file.getPath());

		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();
		return "thesisDocSub";
	}

	// 已评分的文档及论文
	public String gradeList() throws Exception {

		Student s = (Student) ActionContext.getContext().getSession().get("student");
		Course course = studentService.getMyCourse(s.getId());
		ActionContext.getContext().put("course", course);

		return "gradeList";
	}

	public String thesisGrade() throws Exception {

		Student s = (Student) ActionContext.getContext().getSession().get("student");
		Course course = studentService.getMyCourse(s.getId());
		ActionContext.getContext().put("course", course);
		// 老师批改的成绩
		double teacherGrade;
		int thrsis1 = course.getGrade().getThesis1_grade();
		int thrsis2 = course.getGrade().getThesis2_grade();
		int thrsis3 = course.getGrade().getThesis3_grade();
		int thrsis4 = course.getGrade().getThesis4_grade();
		int thrsis5 = course.getGrade().getThesis5_grade();
		int thrsis6 = course.getGrade().getThesis6_grade();
		int thrsis7 = course.getGrade().getThesis7_grade();
		int thrsis8 = course.getGrade().getThesis8_grade();
		int thrsis9 = course.getGrade().getThesis9_grade();
		int thrsis10 = course.getGrade().getThesis10_grade();
		int thrsis11 = course.getGrade().getThesis11_grade();
		teacherGrade = (thrsis1 + thrsis2 + thrsis3 + thrsis4 + thrsis5 + thrsis6 + thrsis7 + thrsis8 + thrsis9
				+ thrsis10 + thrsis11) * 0.4;
		// 材料组
		double materialGrade;
		int thrsis12 = course.getGrade().getThesis12_grade();
		int thrsis13 = course.getGrade().getThesis13_grade();
		int thrsis14 = course.getGrade().getThesis14_grade();
		int thrsis15 = course.getGrade().getThesis15_grade();
		int thrsis16 = course.getGrade().getThesis16_grade();
		int thrsis17 = course.getGrade().getThesis17_grade();
		materialGrade = (thrsis12 + thrsis13 + thrsis14 + thrsis15 + thrsis16 + thrsis17) * 0.3;
		// 答辩老师
		double defenceGrade;
		int thrsis18 = course.getGrade().getThesis18_grade();
		int thrsis19 = course.getGrade().getThesis19_grade();
		int thrsis20 = course.getGrade().getThesis20_grade();
		int thrsis21 = course.getGrade().getThesis21_grade();
		defenceGrade = (thrsis18 + thrsis19 + thrsis20 + thrsis21) * 0.3;
		ActionContext.getContext().put("teacherGrade", teacherGrade);
		ActionContext.getContext().put("materialGrade", materialGrade);
		ActionContext.getContext().put("defenceGrade", defenceGrade);
		return "thesisGrade";
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
