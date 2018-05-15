package com.shika.application.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.shika.application.view.GUICenter;
import com.shika.application.view.GUIView;

public class MainViewController implements ActionListener {
	JTextField user, pass;
	JFrame jframe, mainFrame;
	GUIView main;
	GUICenter GUICenter;

	public MainViewController(GUIView main, JFrame frame) {
		this.main = main;
		this.mainFrame = frame;
		jframe = new JFrame("Chọn file");
		GUICenter = new GUICenter(this, mainFrame);

	}

	public File chooseFileFolder() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File transferFile = null;
		chooser.showOpenDialog(jframe);

		transferFile = chooser.getSelectedFile();

		return transferFile;

	}

	public File chooseFile() throws IOException {
		JFileChooser chooser = new JFileChooser();
		File transferFile = null;

		FileNameExtensionFilter filter = new FileNameExtensionFilter("gif", "pack");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(jframe);

		transferFile = chooser.getSelectedFile();

		return transferFile;

	}

	public File chooseFileSplit() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File transferFile = null;
		chooser.showOpenDialog(jframe);

		transferFile = chooser.getSelectedFile();

		return transferFile;

	}

	public File chooseFileCopy() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		File transferFile = null;
		chooser.showOpenDialog(jframe);

		transferFile = chooser.getSelectedFile();

		return transferFile;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
