package com.shika.application.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CopyMoveController {
	public void warningCopy() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn thư mục hoặc tập tin cần copy!", "Yêu cầu thư mục đầu vào!", JOptionPane.ERROR_MESSAGE, 0,
				iconError, buttons, buttons[0]);
	}
	public void warningMove() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn thư mục hoặc tập tin cần move!", "Yêu cầu thư mục đầu vào!", JOptionPane.ERROR_MESSAGE, 0,
				iconError, buttons, buttons[0]);
	}
	public void warningSavePath() {
		String[] buttons = { "Okay" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn thư mục cần lưu file pack!", "Yêu cầu thư mục cần lưu!", JOptionPane.ERROR_MESSAGE, 0,
				iconError, buttons, buttons[0]);
	}
	public void warningSuccess() {
		String[] buttons = { "Okay" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/checked.png"));
		JOptionPane.showOptionDialog(null, "Hoàn thành!", "Hoàn thành!", JOptionPane.OK_OPTION, 0,
				iconError, buttons, buttons[0]);
	}
	private void transfer(File sFolder, String destFolder) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sFolder));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFolder + "\\" + sFolder.getName()));
		byte[] data = new byte[102400];
		long remainSize = sFolder.length();
		System.out.println(remainSize);

		while (remainSize > 0) {
			if (remainSize >= data.length) {
				bis.read(data);
				bos.write(data, 0, data.length);
				remainSize -= data.length;
			} else {
				bis.read(data);
				bos.write(data, 0, (int) remainSize);
				remainSize -= remainSize;
			}
		}
		bis.close();
		bos.close();

	}

	private void fileCopyBuff(File sFolder, String destFolder) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sFolder));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFolder + "\\" + sFolder.getName()));

		byte[] data = new byte[102400];
		int buff;
		while ((buff = bis.read(data)) != -1) {
			bos.write(data, 0, buff);
		}
		bis.close();
		bos.close();
	}

	private void fileCopyNone(File sFolder, String destFolder) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sFolder), 10240);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFolder + "\\" + sFolder.getName()),
				10240);

		int buff;
		while ((buff = bis.read()) != -1) {
			bos.write(buff);
		}
		bis.close();
		bos.close();
	}

	private void fileCopyArr(File sFolder, String destFolder) throws IOException {
		FileInputStream fis = new FileInputStream(sFolder);
		FileOutputStream fos = new FileOutputStream(destFolder + "\\" + sFolder.getName());

		byte[] data = new byte[10240];
		int i;
		while ((i = fis.read(data)) != -1) {
			fos.write(data, 0, i);
		}
		fos.close();
		fis.close();
	}

	public void fileCopy(File sFolder, String destFolder, boolean moved, boolean arrCopy, boolean bufferCopy,
			boolean transfer) throws IOException {
		if (arrCopy) {
			long start_time = System.currentTimeMillis();
			fileCopyArr(sFolder, destFolder);
			long end_time = System.currentTimeMillis();
			System.out.println("Done in: " + (end_time - start_time) + " ms");
		} else if ( bufferCopy) {
			long start_time = System.currentTimeMillis();
			fileCopyBuff(sFolder, destFolder);
			long end_time = System.currentTimeMillis();
			System.out.println("Done in: " + (end_time - start_time) + " ms");
		} else if (transfer) {
			long start_time = System.currentTimeMillis();
			transfer(sFolder, destFolder);
			long end_time = System.currentTimeMillis();
			System.out.println("Done in: " + (end_time - start_time) + " ms");
		} else {
			long start_time = System.currentTimeMillis();
			fileCopyNone(sFolder, destFolder);
			long end_time = System.currentTimeMillis();
			System.out.println("Done in: " + (end_time - start_time) + " ms");
		}

		if (moved == true) {
			sFolder.delete();
		}
	}

	public void folderCopy(File srcFolder, File destFolders, boolean moved, boolean arrCopy, boolean bufferCopy,
			boolean transfer) throws IOException {
		File[] list = srcFolder.listFiles();

		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				File destNextFile = new File(destFolders.getAbsolutePath() + "\\" + list[i].getName());
				destNextFile.mkdirs();
				folderCopy(list[i], destNextFile, moved, arrCopy, bufferCopy, transfer);

			} else if (list[i].isFile()) {
				fileCopy(list[i], destFolders.getAbsolutePath(), moved, arrCopy, bufferCopy, transfer);
			}
		}
		if (moved == true) {
			srcFolder.delete();
		}

	}

	public void copy(String sFolder, String destFolder, boolean moved, boolean arrCopy, boolean bufferCopy,
			boolean transfer) throws IOException {
		File srcFolder = new File(sFolder);
		long start_time = System.currentTimeMillis();
		if (srcFolder.isFile()) {
			fileCopy(srcFolder, destFolder, moved, arrCopy, bufferCopy, transfer);
		} else {
			File destFolders = new File(destFolder + "\\" + srcFolder.getName());
			if (!destFolders.exists()) {
				destFolders.mkdirs();
			}
			folderCopy(srcFolder, destFolders, moved, arrCopy, bufferCopy, transfer);
		}
		long end_time = System.currentTimeMillis();
		System.out.println("Total Time is done in: " + (end_time - start_time) + " ms");

	}
	
}
