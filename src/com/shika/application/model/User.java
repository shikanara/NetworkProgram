package com.shika.application.model;

public class User {
	String user;
	String pass;
	

	public User(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	

	public User() {
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "UserModel [user=" + user + ", pass=" + pass + "]";
	}

	
}
