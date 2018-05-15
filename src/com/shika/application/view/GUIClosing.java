package com.shika.application.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GUIClosing {

	public GUIClosing() {
	}

	public void windowsClosing() {
		String[] buttons = { "Có", "Không" };
		ImageIcon icon = new ImageIcon(getClass().getResource("/image/cry1.gif"));

		int quit = JOptionPane.showOptionDialog(null, "Chắc chắn muốn đóng Shika's app chứ?", "Đóng ứng dụng",
				JOptionPane.INFORMATION_MESSAGE, 0, icon, buttons, null);

		System.out.println(quit);
		if (quit == 0) {
			System.exit(0);
		}

		else {
			// e.getWindow().dispose();
		}

	}
	//
	// public static void main(String[] args) {
	// new GUIClosing();
	// }

}
