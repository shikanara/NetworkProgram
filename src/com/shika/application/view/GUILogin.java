package com.shika.application.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.shika.application.controller.LoginController;
import com.shika.application.model.UserDAO;

public class GUILogin {
	JTextField user, pass;
	LoginController loginControll;
	UserDAO userDao;

	public GUILogin(UserDAO userDao) {
		this.userDao = userDao;
		loginControll = new LoginController(this.userDao);
	}

	public void login()  {
		user = new JTextField(5);
		pass = new JPasswordField(5);
		user.setText("shika");
		Object[] message = { "Tên người dùng:", user, "Mật khấu:", pass };
		String[] button = { "Đăng nhập" };
		
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/cry1.gif"));
		ImageIcon iconSuccess = new ImageIcon(getClass().getResource("/image/hello1.gif"));
		// videocall.png
		ImageIcon iconLogin = new ImageIcon(getClass().getResource("/image/hello1.gif"));

		int option = JOptionPane.showOptionDialog(null, message, "Nhập thông tin", JOptionPane.INFORMATION_MESSAGE, 0,
				iconLogin, button, button[0]);

		if (option == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}
		if (option == JOptionPane.OK_OPTION) {
			if (user.getText().equals("") || user.getText().equals(null) || pass.getText().equals("")
					|| pass.getText().equals(null)) {
				String[] buttons = { "Biết rồi" };
				JOptionPane.showOptionDialog(null, "Bạn chưa nhập thông tin!", "Lỗi đăng nhập!",
						JOptionPane.ERROR_MESSAGE, 0, iconError, buttons, buttons[0]);
				login();
			} else {
				if (loginControll.checkUser(user.getText().trim(), pass.getText().trim())) {
					String[] buttons = { "Biết rồi" };
					JOptionPane.showOptionDialog(null, "Đăng nhập thành công!", "Đăng nhập!",
							JOptionPane.INFORMATION_MESSAGE, 0, iconSuccess, buttons, buttons[0]);
				} else {
					String[] buttons = { "Biết rồi" };
					JOptionPane.showOptionDialog(null, "Nhập sai user hoặc password!", "Lỗi đăng nhập!",
							JOptionPane.INFORMATION_MESSAGE, 0, iconError, buttons, buttons[0]);
					login();
				}

			}
		}
	}

//	public static void main(String[] args) throws IOException {
//		new GUILogin(new UserDAO());
//	}

}
