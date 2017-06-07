package com.poolm.pojo;

public class User {
	private Integer id;

	private String username;

	private String password;

	private String gender;

	private String phone;

	private Double height;

	private Double weight;

	private Integer weiteng;

	private Integer kouqiangky;

	private Integer yayingcx;

	private Integer jianfei;

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getWeiteng() {
		return weiteng;
	}

	public void setWeiteng(Integer weiteng) {
		this.weiteng = weiteng;
	}

	public Integer getKouqiangky() {
		return kouqiangky;
	}

	public void setKouqiangky(Integer kouqiangky) {
		this.kouqiangky = kouqiangky;
	}

	public Integer getYayingcx() {
		return yayingcx;
	}

	public void setYayingcx(Integer yayingcx) {
		this.yayingcx = yayingcx;
	}

	public Integer getJianfei() {
		return jianfei;
	}

	public void setJianfei(Integer jianfei) {
		this.jianfei = jianfei;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}