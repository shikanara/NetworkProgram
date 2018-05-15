package com.shika.application.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AuthorController {
	
	public AuthorController() {
		String[] buttons = { "Biết rồi" };
		ImageIcon icon = new ImageIcon(getClass().getResource("/Image/author.jpg"));

		int rc = JOptionPane.showOptionDialog(null,
				"1. Nguyễn Tâm Luân (shika).\n2. Phạm Đăng Hải (hankyung).",
				"Thông tin tác giả", JOptionPane.INFORMATION_MESSAGE, 0, icon, buttons, buttons[0]);
		if (rc == 0) {
			System.out.println(rc);
		} else {
			System.out.println("nothing");

		}
	}

}
