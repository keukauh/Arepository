package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.smartmvc.dao.UserDAO;
import cn.smartmvc.entity.User;

public class TestCase {
	
	private UserDAO dao;
	
	@Before
	/*
	 * ����@Before�ķ�����
	 * ���ڼ���@Test�ķ���ִ��֮ǰ��ִ�С�
	 */
	public void init() {
		dao = new UserDAO();
	}
	
	@Test
	public void test1() throws SQLException {
		List<User> users = 
				dao.findAll();
		System.out.println(users);
	}
	
	@Test
	public void test2() throws SQLException {
		User user = new User();
		user.setUname("��ʦ��");
		user.setPwd("1234");
		user.setEmail("dsy@126.com");
		dao.save(user);
	}
	
	@Test
	public void test3() throws SQLException {
		dao.delete(6);
	}
	
	@Test
	public void test4() throws SQLException {
		User user = dao.find("С��");
		System.out.println("user:" + user);
	}
	
	
}








