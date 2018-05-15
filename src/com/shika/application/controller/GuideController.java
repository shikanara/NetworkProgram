package com.shika.application.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GuideController {

	public GuideController() {
		String[] buttons = { "Biết rồi" };
		ImageIcon icon = new ImageIcon(getClass().getResource("/image/hello1.gif"));

		int rc = JOptionPane.showOptionDialog(null,
				"1. Kết nối tới server.\n2. Xem và nói chuyện trực tiếp với nhiều người\n3. "
				+ "Tương tác với nhau qua khung chat\n4. "
				+ "Lưu ý là không thực hiện kết nối khi mạng chập chờn.\n Enjoy your chat!",
				"Hướng dẫn", JOptionPane.PLAIN_MESSAGE, 0, icon, buttons, buttons[0]);
		if (rc == 0) {
			System.out.println(rc);
		}
	}

}
