package cn.smartmvc.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于管理，URL与子控制器方法的映射关系 如: /list.do -> Handler(controller, execute()) /add.do ->
 * Handler(controller, add())
 */
public class HandlerMapping {
	private Map<String, Handler> mapping = new HashMap<String, Handler>();

	public HandlerMapping() {
	}

	/**
	 * 将 一个控制器类解析并且添加到map中 1. 动态加载类到内存 2. 找到全部的方法，并且查找方法上是否有 RequestMapping 注解 3.
	 * 如果有注解，就将注解URL，和控制器以及方法添加到 map中
	 * 
	 * @param className
	 *            一个控制器类名
	 */
	public void parseController(String className) throws Exception {
		// 加载类到内存中
		Class cls = Class.forName(className);
		// 创建控制器对象
		Object controller = cls.newInstance();
		// 找到全部方法
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			// 在方法上查找 RequestMapping 注解
			RequestMapping ann = method.getAnnotation(RequestMapping.class);
			// 如果ann不为空，则方法上包含RequestMapping注解
			if (ann != null) {
				String url = ann.value();
				// 将找到的方法添加到map中
				mapping.put(url, new Handler(controller, method));
			}
		}
	}

	@Override
	public String toString() {
		return "HandlerMapping [mapping=" + mapping + "]";
	}

	/**
	 * ���������·��url���ҵ�Handler����
	 * 
	 * @param url
	 * @return
	 */
	public Handler get(String url) {
		return mapping.get(url);
	}

	public static void main(String[] args) throws Exception {
		HandlerMapping handlerMapping = new HandlerMapping();
		handlerMapping.parseController("test.Demo");
		System.out.println(handlerMapping.mapping);
	}
}
