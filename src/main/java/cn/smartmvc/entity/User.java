package cn.smartmvc.entity;

/**
 * 实体类: 实体类与表的结构要一致，表有哪些字段，对应的 实体类也要有对应的属性(类型要匹配)。
 */
public class User {
	private int id;
	private String uname;
	private String pwd;
	private String email;

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", pwd=" + pwd + ", email=" + email + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
