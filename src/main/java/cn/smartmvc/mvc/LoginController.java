package cn.smartmvc.mvc;

import javax.servlet.http.HttpServletRequest;

import cn.smartmvc.dao.UserDAO;
import cn.smartmvc.entity.User;

public class LoginController {

	@RequestMapping("/login-form.do")
	public String form(HttpServletRequest request) throws Exception {
		return "login";
	}

	@RequestMapping("/login.do")
	public String login(HttpServletRequest request) throws Exception {
		// 检查用户名和密码是否正确
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserDAO dao = new UserDAO();
		User user = dao.find(uname);
		// 数据中没有用户信息，拒绝登录返回 "login"
		if (user == null) {
			request.setAttribute("unameError", "用户名或密码错误");
			request.setAttribute("uname", uname);
			return "login";
		}
		if (user.getPwd().equals(pwd)) {
			// 密码也一样则登录成功！
			request.getSession().setAttribute("loginUser", user);
			return "redirect:/list.do";
		} // 密码不同，返回登录页面继续登录
		request.setAttribute("pwdError", "用户名或密码错误");
		request.setAttribute("uname", uname);
		return "login";
	}
}
