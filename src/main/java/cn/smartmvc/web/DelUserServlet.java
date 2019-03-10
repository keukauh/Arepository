package cn.smartmvc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.smartmvc.dao.UserDAO;

public class DelUserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 读取要删除的用户的id
		int id = Integer.parseInt(request.getParameter("id"));
		// 删除指定id的用户
		UserDAO dao = new UserDAO();
		try {
			dao.delete(id);
			// 重定向到用户列表
			response.sendRedirect("list");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("系统繁忙，稍后重试");
		}
	}

}
