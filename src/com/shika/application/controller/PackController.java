package com.shika.application.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PackController {
	
	public void warningPack() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn thư mục cần pack!", "Yêu cầu thư mục đầu vào!", JOptionPane.ERROR_MESSAGE, 0,
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

	public void warningUnpack() {
		String[] buttons = { "Biết rồi" };
		ImageIcon iconError = new ImageIcon(getClass().getResource("/image/warning.png"));
		JOptionPane.showOptionDialog(null, "Chưa chọn tập tin cần unpack!", "Yêu cầu tập tin đầu vào!", JOptionPane.ERROR_MESSAGE, 0,
				iconError, buttons, buttons[0]);
	}

	public void pack(File srcFile, RandomAccessFile raf) throws IOException {
		File[] list = srcFile.listFiles();// lấy danh sách file

		raf.writeInt(list.length);// ghi số file

		for (int i = 0; i < list.length; i++) {// duyệt danh sách file
			if (list[i].isFile()) {// nếu là file thì ghi tên, định dạng file hay folder,
				// kích thước, data
				raf.writeUTF(list[i].getAbsolutePath());
				raf.writeInt(1);
				long size = list[i].length();
				raf.writeLong(size);

				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(list[i]));
				byte[] data = new byte[(int) size];

				if (bis.read(data) != -1) {
					raf.write(data);
				}
				bis.close();
			} else {
				raf.writeUTF(list[i].getAbsolutePath());
				raf.writeInt(0);
				pack(list[i], raf);
			}

		}

	}

	// hàm pack ghi như sau: +ghi số file trong danh sách hiện tại
	// +nếu là file thì ghi:+Đường dẫn tuyệt đối file
	// +Mã định dang file(1)
	// +Kích thước file
	// +Dữ liệu
	// +Nếu là thư mục thì ghi:
	// +Đường dẫn tuyệt đối thư mục
	// +Mã định dạng thư mục(0)
	public boolean packFolder(String src, String pathSave) throws IOException {
		File srcFile = new File(src);

		if (!srcFile.exists()) {
			System.out.println("Thư mục không tồn tại!");
			return false;
		} else {
			RandomAccessFile raf = new RandomAccessFile(pathSave + "\\" + srcFile.getName() + ".pack", "rw");
			pack(srcFile, raf);
			raf.close();
		}
		System.out.println("PackFolder successful!");
		return true;
	}

	// thư mục cha và thư mục con giống tên nhau
	// tên thư mục extract là thư mục con con con
	public void extract(RandomAccessFile raf, String extractName, String pathSave) throws IOException {
		int size = raf.readInt();

		for (int i = 0; i < size; i++) {
			System.out.println(i);
			String name = raf.readUTF();
			System.out.println(name);
			int format = raf.readInt();

			String substring = name.substring(2, name.length());
			String name1 = pathSave + substring;

			if (format == 1) {

				long sizeFile = raf.readLong();

				if (name.indexOf(extractName) != -1) {
					File parentFile = new File(name1);
					String parent = parentFile.getParent();
					File parentg = new File(parent);
					parentg.mkdirs();
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(name1));
					byte[] data = new byte[(int) sizeFile];

					if (raf.read(data) != -1) {
						bos.write(data);
						bos.close();

					}
				} else
					raf.seek(raf.getFilePointer() + sizeFile);

			} else {
				if (name.indexOf(extractName) != -1) {
					File subFolder = new File(name1);
					subFolder.mkdirs();
					extract(raf, extractName, pathSave);
					break;

				} else {
					extract(raf, extractName, pathSave);
				}
			}
		}
	}

	public boolean extractFolder(String src, String extractName, String des) throws IOException {
		/*Tuong tu nhu Unpack
		 * ket qua thu duoc khi extrac thanh cong la thu muc EXTRACT 
		 * cac file co ten la extractName va giu nguyen cau truc thu muc neu 
		 * co nhieu file extracName trung nhau nhung khac duong dan con*/
		
		File srcFile = new File(src);
		File file = new File(src);
		String substring = file.getName().substring(0, file.getName().length() - 5);
		String pathSave = des + "\\EXTRACT";
		File fileUnpack = new File(pathSave + "\\" + substring);
		System.out.println(pathSave);
		if (!fileUnpack.exists()) {
			fileUnpack.mkdirs();
		}
		if (!srcFile.exists()) {
			System.out.println("Thư mục không tồn tại!");
		} else {
			RandomAccessFile raf = new RandomAccessFile(srcFile, "rw");
			extract(raf, extractName, pathSave);
			raf.close();
		}
		System.out.println("Extract " + src + " done!");
		return false;

	}

	private void unpack1(RandomAccessFile raf, String src, String pathSave) throws IOException {
		/*
		 * Theo cau truc cua mot file pack Ban dau doc so file va thu muc cua thu muc
		 * cha co trong file pack Tiep theo lay thong tin cua tung file bao gom path va
		 * dinh danh Dinh dang o day : neu format bang 0 thi file là thu muc Neu format
		 * bang 1 thì file la tap tin
		 */
		// Doc so file cua thu muc cha
		int size = raf.readInt();
		// Duyet danh sach file va lay thong tin
		for (int i = 0; i < size; i++) {
			String name = raf.readUTF();
			int format = raf.readInt();

			String substring = name.substring(2, name.length());
			String name1 = pathSave + substring;
			System.out.println(name1);

			// File la tap tin
			if (format == 1) {
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(name1));
				long sizeFile = raf.readLong();
				byte[] data = new byte[(int) sizeFile];

				if (raf.read(data) != -1) {
					bos.write(data);
					bos.close();
				}
				// File la thu muc, ta can tao ra thu muc cha de goi de quy va tien hanh
				// di sau vao cac thu muc con cua file pack
			} else {
				File subFolder = new File(name1);
				subFolder.mkdirs();
				unpack1(raf, src, pathSave);

			}
		}
	}

	public void unpackFolder(String src, String des) throws IOException {
		/*
		 * Doi voi method nay ta can mot path nguon la file co duoi la .pack Dau tien ta
		 * can tach lay ten cua file.pack de su dung lam noi luu tru Tiep theo vi tuong
		 * tac voi du lieu va yeu cau cua de bai su dung ramdomaccessfile Sau do ta goi
		 * ham unpack1() thuc hien Sau khi hoan tat se dong RAF va thong bao unpack
		 * thanh cong, khi thanh cong chuong trinh se tao ra mot thu muc co ten UNPACK
		 * la noi luu tru toan bo file da duoc dong goi
		 */
		// tach lay ten cua file.pack de su dung lam noi luu tru
		File file = new File(src);
		String substring = file.getName().substring(0, file.getName().length() - 5);
		String pathSave = des + "\\UNPACK";
		File fileUnpack = new File(pathSave + "\\" + substring);
		System.out.println(fileUnpack.mkdirs());
		// Tao RAF
		RandomAccessFile raf = new RandomAccessFile(src, "rw");
		// goi ham unpack1() thuc hien
		unpack1(raf, src, pathSave);
		// Dong RAF
		raf.close();
		// Ket qua
		System.out.println("Unpack all successful!");
	}
}
