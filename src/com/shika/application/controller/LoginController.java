package com.shika.application.controller;

import com.shika.application.model.UserDAO;

public class LoginController {
	UserDAO dao;

	public LoginController(UserDAO dao) {
		this.dao = dao;
	}

	public boolean checkUser(String user, String pass) {
		return dao.checkUser(user, pass);
	}

}
