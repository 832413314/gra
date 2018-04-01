package net.hncu.hcq.action;

import java.io.*;

import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Course;
import sun.misc.CharacterEncoder;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component("fileDownloadAction")
public class FileDownloadAction extends BaseAction<Course> {

	// 先用课程的编号来定文档名字
	// private String courseId;
	// 弃掉自己写的 用model的自动装载ID来确定

	private String filename;
	// 这个是自己定义的 为了到时候区分 mission paper等文档类型 然后根据文档类型来选择返回哪个文件
	private String DocType;

	@Override
	public String execute() throws Exception {
		//Course c = courseService.getById(model.getId());
		// 回显 为了用户下载文档的时候用这个名字 修改名字格式在Struts的配置里面
		ActionContext.getContext().put("downLoadName", model.getId()+DocType);
		return "success";
	}

	public InputStream getDownloadFile() {

		// //要获取的文档路径及文件
		// return ServletActionContext.getServletContext().getResourceAsStream(
		// DocType + "missionDocDoc/" + 6 + ".doc");

		// 要获取的文档路径及文件（括号里面写的是文件路径 前面DocType+Doc相当雨missionDoc文件路径下）
		return ServletActionContext.getServletContext().getResourceAsStream(DocType + "Doc/" + model.getId() + ".doc");
	}

	public String getDocType() {
		return DocType;
	}

	public void setDocType(String docType) {
		DocType = docType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}