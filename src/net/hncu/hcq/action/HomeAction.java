package net.hncu.hcq.action;

import com.opensymphony.xwork2.ActionContext;
import net.hncu.hcq.base.BaseAction;
import net.hncu.hcq.domain.Administrator;
import net.hncu.hcq.domain.Notice;
import net.hncu.hcq.domain.Teacher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("homeAction")
@Scope("prototype")
public class HomeAction extends BaseAction<Object> {
    private Long id;
    private String password;
    private String type;

    public HomeAction() {
    }

    public String index() throws Exception {
        // 检测是否有session存在
//        if (ActionContext.getContext().getSession().get("admin") == null) {
//            return "loginUI";
//        }
        return "index";
    }

    public String top() throws Exception {
        return "top";
    }

    public String bottom() throws Exception {
        return "bottom";
    }

    public String left() throws Exception {
        return "left";
    }

    public String right() throws Exception {
        List<Notice> notices = noticeService.findAll();
        ActionContext.getContext().put("notices", notices);
        return "right";
    }

    public String loginUI() throws Exception {
        return "loginUI";
    }

    public String login() {
        System.out.println(type);
        return "toIndex";

    }

    public String logout() throws Exception {
        ActionContext.getContext().getSession().remove("admin");
        return "logout";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
