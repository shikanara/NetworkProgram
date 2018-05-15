package com.shika.application.model;

import java.util.ArrayList;

public class UserDAO {
	public static ArrayList<User> userList;

	public UserDAO() {
		addListUser();
	}

	public boolean checkUser(String name, String pass) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUser().equalsIgnoreCase(name) && userList.get(i).getPass().equals(pass)) {
				return true;
			}
		}
		return false;

	}

	public ArrayList<User> addListUser() {
		userList = new ArrayList<>();
		userList.add(new User("Shika", "luan"));
		userList.add(new User("Nhat", "123"));
		userList.add(new User("Hankyung", "123"));
		userList.add(new User("Tung-MTP", "123"));
		return userList;
	}

}
