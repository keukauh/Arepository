package cn.smartmvc.mvc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理者，处理器
 */
public class Handler {
	/**
	 * 子控制器对象
	 */
	private Object controller;
	/**
	 * 子控制的方法
	 */
	private Method method;

	public Handler() {
	}

	public Handler(Object controller, Method method) {
		this.controller = controller;
		this.method = method;
	}

	@Override
	public String toString() {
		return "Handler [controller=" + controller + ", method=" + method + "]";
	}

	public String execute(HttpServletRequest req) throws Exception {
		// 利用反射执行方法
		return (String) method.invoke(controller, req);
	}

}
