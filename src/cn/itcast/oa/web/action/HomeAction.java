package cn.itcast.oa.web.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

//首页action
@SuppressWarnings("serial")
@Controller
public class HomeAction extends ActionSupport {
	public String top() {

		return "top";
	}

	public String left() {

		return "left";
	}

	public String right() {

		return "right";
	}

	public String bottom() {

		return "bottom";
	}

	public String index() {
		return "index";
	}
}
