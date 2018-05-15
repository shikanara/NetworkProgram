package com.shika.application.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SplitController {

	public void warningSuccess() {
		String[] buttons = { "Okay" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/checked.png"));
		JOptionPane.showOptionDialog(null, "Hoàn thành!", "Hoàn thành!", JOptionPane.OK_OPTION, 0, iconError, buttons,
				buttons[0]);
	}

	public void warningSavePath() {
		String[] buttons = { "Okay" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn thư mục cần lưu file split!", "Yêu cầu thư mục cần lưu!",
				JOptionPane.ERROR_MESSAGE, 0, iconError, buttons, buttons[0]);
	}

	public void warningSplit() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn tập tin cần split!", "Yêu cầu tập tin đầu vào!",
				JOptionPane.ERROR_MESSAGE, 0, iconError, buttons, buttons[0]);
	}
	
	public void warningSDFNumber() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Số file cần split nhập không hợp lệ\nYêu cầu giá trị nhập là số nguyên dương và không chứa ký tự!", "Yêu cầu tập tin đầu vào!",
				JOptionPane.ERROR_MESSAGE, 0, iconError, buttons, buttons[0]);
	}
	public void warningSDFSize() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Kích thước file cần split nhập không hợp lệ\nYêu cầu giá trị nhập là số nguyên dương và không chứa ký tự!", "Yêu cầu tập tin đầu vào!",
				JOptionPane.ERROR_MESSAGE, 0, iconError, buttons, buttons[0]);
	}
	public void warningNoChildrenFile() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Not found children file in your directory!", "Yêu cầu tập tin đầu vào!",
				JOptionPane.ERROR_MESSAGE, 0, iconError, buttons, buttons[0]);
	}
	public boolean spliterQuanlity(String src, String dest, long sizeFile) throws IOException {

		File srcFile = new File(src);

		if (!srcFile.exists()) {
			System.out.println("File not found!");
		} else {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));

			long numFile = srcFile.length() / sizeFile;
			long remainFile = srcFile.length() % sizeFile;

			for (int i = 0; i < numFile; i++) {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(dest +"\\"+ srcFile.getName() + decoration(i + 1)));
				transfers(bis, bos, sizeFile);
				bos.close();
			}

			if (remainFile > 0) {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(dest  +"\\"+ srcFile.getName() + decoration((int) numFile + 1)));
				transfers(bis, bos, remainFile);
				bos.close();
			}
			bis.close();

		}
		System.out.println("Split file size is Done!");
		return true;

	}

	public void transfers(BufferedInputStream bis, BufferedOutputStream bos, long partSize) throws IOException {
		byte[] data = new byte[102400];
		long remain = partSize;

		while (remain > 0) {
			if (remain >= data.length) {
				bis.read(data);
				bos.write(data);
				remain -= data.length;
			} else {
				byte[] remainData = new byte[(int) remain];
				bis.read(remainData);
				bos.write(remainData);
				remain -= remain;
			}
		}

	}

	// Cắt theo số lương cho trước
	public boolean spliter(String src, String dest, long numFile) throws IOException {
		File srcFile = new File(src);

		if (!srcFile.exists()) {
			System.out.println("File not found!");
			return false;
		} else {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));

			long mustSize = srcFile.length() / numFile;
			long remainSize = srcFile.length() % numFile;
			System.out.println(mustSize);
			System.out.println(remainSize);

			for (int i = 0; i < numFile; i++) {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(dest +"\\"+ srcFile.getName() + decoration(i + 1)));

				if (i == numFile - 1) {
					transfer(bis, bos, mustSize + remainSize);
					// data = new byte[(int) (mustSize + remainSize)];
					// bis.read(data);
					// bos.write(data, 0, data.length);
					bos.close();

				} else {
					transfer(bis, bos, mustSize);
					// data = new byte[(int) mustSize];
					// bis.read(data);
					// bos.write(data, 0, data.length);
					bos.close();
				}
				// bos.close();
			}
			bis.close();
		}
		System.out.println("Split file size is done!");
		return true;
	}

	private void transfer(BufferedInputStream bis, BufferedOutputStream bos, long partSize) throws IOException {
		byte[] data = new byte[102400];

		long remain = partSize;

		while (remain > 0) {
			if (remain > data.length) {
				bis.read(data);
				bos.write(data);
				remain -= data.length;
			} else {
				byte[] remainData = new byte[(int) remain];
				bis.read(remainData);
				bos.write(remainData);
				remain -= remain;
			}
		}
		// bos.close();

	}

	private String decoration(int i) {
		if (i <= 9) {
			return ".00" + i;
		} else if (i >= 10 && i <= 99) {
			return ".0" + i;
		} else
			return "." + i;
	}

	public boolean joiner(String src, String dest) throws IOException {
		File srcFile = new File(src);

		if (!srcFile.exists()) {
			System.out.println("File not found!");
		} else {
			File[] list = srcFile.listFiles();
			if (list.length<=0) {
				System.out.println("Not found children file to join");
				return false;
			}else {
				String nameDes = list[0].getName().substring(0, list[0].getName().length()-4);
				
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest+"\\"+nameDes));
				
				for (File name : list) {
					// byte[] data = new byte[(int) name.length()];
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(name));
					// if (bis.read(data, 0, data.length) != -1) {
					// bos.write(data);
					// bis.close();
					// }
					
					transfer(bis, bos, name.length());
					bis.close();
				}
				bos.close();
			}
				
		}
		System.out.println("Join is successful!");
		return true;
	}

}
