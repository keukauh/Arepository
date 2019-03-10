package cn.smartmvc.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.smartmvc.dao.UserDAO;
import cn.smartmvc.entity.User;

public class Controller {
	/**
	 * 子控制器方法
	 * 
	 * @param request
	 *            用于在控制器和JSP直接传递数据
	 * @return 转发的目标JSP页面
	 * @throws Exception
	 */
	@RequestMapping("/list.do")
	public String execute(HttpServletRequest request) throws Exception {
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		// 将users数据传递到JSP
		request.setAttribute("users", users);
		return "list";
	}

	@RequestMapping("/add.do")
	public String add(HttpServletRequest request) throws Exception {
		return "add"; // add.jsp
	}

	@RequestMapping("/save.do")
	public String save(HttpServletRequest request) throws Exception {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");

		UserDAO dao = new UserDAO();
		// 检查用户名是否是同名的
		User u = dao.find(uname);
		if (u != null) {
			// 返回到添加页面，继续输入信息
			request.setAttribute("uname", uname);
			request.setAttribute("email", email);
			request.setAttribute("unameError", "用户名重复");
			return "add";
		}

		User user = new User();
		user.setUname(uname);
		user.setPwd(pwd);
		user.setEmail(email);
		dao.save(user);
		request.setAttribute("message", "�ɹ�");
		// return "success"; //success.jsp
		return "redirect:/list.do";
		// return "redirect:http://tmooc.cn";
	}

	@RequestMapping("/delete.do")
	public String delete(HttpServletRequest request) throws Exception {

		String str = request.getParameter("id");
		int id = Integer.parseInt(str);
		UserDAO dao = new UserDAO();
		dao.delete(id);

		return "redirect:/list.do";
	}

}
