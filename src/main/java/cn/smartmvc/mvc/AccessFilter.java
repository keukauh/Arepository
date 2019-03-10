package cn.smartmvc.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.smartmvc.entity.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 如果当前请求是 /login 开始的，就放过
		String path = req.getRequestURI();
		String ctxPath = req.getContextPath();
		path = path.substring(ctxPath.length());
		if (path.startsWith("/login")) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = req.getSession();
		// 检查登录用户信息
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			// 没有登录过,重定向到登录页面
			res.sendRedirect(req.getContextPath() + "/login-form.do");
		} else {
			// 如果登录了就放过
			chain.doFilter(req, res);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
