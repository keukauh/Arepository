package cn.smartmvc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.smartmvc.dao.UserDAO;
import cn.smartmvc.entity.User;

public class ListUserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 将所有用户信息查询出来
		UserDAO dao = new UserDAO();
		try {
			List<User> users = dao.findAll();
			// 依据查询到的用户信息，输出表格
			/*
			 * 因为Servlet不擅长处理表示逻辑， 所以使用转发机制，将数据绑订到 request对象上，然后转发给jsp来处理。
			 */
			request.setAttribute("users", users);
			request.getRequestDispatcher("listUser.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			out.println("系统繁忙，稍后重试");
		}

	}

}
