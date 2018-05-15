package com.shika.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.shika.application.controller.MainViewController;
import com.shika.application.controller.PackController;

public class GUIPackage implements ActionListener {
	JPanel pnlPackage, pnlPackContent, pnlUnPackContent;
	JButton btnChooseFile1, btnChooseFile2, btnPack, btnUnpack,btnExtract,btnSaveFile1,btnSaveFile2;
	JTextField txtOpenFile1, txtOpenFile2,txtSaveFile1,txtSaveFile2;
	JLabel lblOpenFile1, lblOpenFile2,lblSaveFile1,lblSaveFile2;
	MainViewController mainView;
	BufferedImage icoBtnChoose, icoBtnUnpack, icoBtnPack,icoBtnExtract,icoBtnSave;
	PackController pckControlelr;

	public GUIPackage(MainViewController mainView) {
		this.mainView = mainView;
		pckControlelr = new PackController();
	}

	public JPanel nplPackage() {
		pnlPackage = new JPanel(new GridLayout(2, 3));
		pnlPackage.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		pnlPackContent = new JPanel(new FlowLayout());
		pnlPackContent.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Package", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		

		try {
			icoBtnChoose = ImageIO.read(getClass().getResource("/image/folder.png"));
			icoBtnSave = ImageIO.read(getClass().getResource("/image/save.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblOpenFile1 = new JLabel("Open File|Folder: ");
		lblOpenFile1.setFont(new Font("Sans Serif", Font.BOLD, 12));
		txtOpenFile1 = new JTextField(45);
		txtOpenFile1.setEnabled(false);
		btnChooseFile1 = new JButton();
		btnChooseFile1.setIcon(new ImageIcon(icoBtnChoose));
		btnChooseFile1.addActionListener(this);
		
		lblSaveFile1 = new JLabel("Save Path:            ");
		lblSaveFile1.setFont(new Font("Sans Serif", Font.BOLD, 12));
		txtSaveFile1 = new JTextField(45);
		txtSaveFile1.setEnabled(false);
		btnSaveFile1 = new JButton("");
		btnSaveFile1.setIcon(new ImageIcon(icoBtnSave));
		btnSaveFile1.addActionListener(this);
		
		btnPack = new JButton("Pack");
		btnPack.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnPack = ImageIO.read(getClass().getResource("/image/box.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnPack.setIcon(new ImageIcon(icoBtnPack));
		btnPack.addActionListener(this);
		pnlPackContent.add(lblOpenFile1);
		pnlPackContent.add(txtOpenFile1);
		pnlPackContent.add(btnChooseFile1);
		pnlPackContent.add(lblSaveFile1);
		pnlPackContent.add(txtSaveFile1);
		pnlPackContent.add(btnSaveFile1);
		pnlPackContent.add(btnPack);
		pnlPackContent.setBackground(new Color(255, 255, 255, 30));

		pnlUnPackContent = new JPanel(new FlowLayout());
		pnlUnPackContent.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "UnPackage", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		lblOpenFile2 = new JLabel("Open file.pack:   ");
		lblOpenFile2.setFont(new Font("Sans Serif", Font.BOLD, 12));
		txtOpenFile2= new JTextField(45);
		txtOpenFile2.setEnabled(false);
		btnChooseFile2 = new JButton();
		btnChooseFile2.setIcon(new ImageIcon(icoBtnChoose));
		btnChooseFile2.addActionListener(this);
		lblSaveFile2 = new JLabel("Save Path:            ");
		lblSaveFile2.setFont(new Font("Sans Serif", Font.BOLD, 12));
		txtSaveFile2 = new JTextField(45);
		txtSaveFile2.setEnabled(false);
		btnSaveFile2 = new JButton("");
		btnSaveFile2.setIcon(new ImageIcon(icoBtnSave));
		btnSaveFile2.addActionListener(this);
		
		btnUnpack = new JButton("Unpack");
		// btnUnpack.setContentAreaFilled(false);// background of button
		// btnUnpack.setBackground(new Color(117, 117, 117, 200));
		// btnUnpack.setVerticalTextPosition(SwingConstants.);
		btnUnpack.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnUnpack = ImageIO.read(getClass().getResource("/image/unbox.png"));
			btnUnpack.setIcon(new ImageIcon(icoBtnUnpack));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnUnpack.addActionListener(this);
		btnExtract = new JButton("Extract");
		btnExtract.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnExtract.addActionListener(this);
		btnExtract.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnExtract = ImageIO.read(getClass().getResource("/image/extract.png"));
			btnExtract.setIcon(new ImageIcon(icoBtnExtract));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		pnlUnPackContent.add(lblOpenFile2);
		pnlUnPackContent.add(txtOpenFile2);
		pnlUnPackContent.add(btnChooseFile2);
		pnlUnPackContent.add(lblSaveFile2);
		pnlUnPackContent.add(txtSaveFile2);
		pnlUnPackContent.add(btnSaveFile2);
		pnlUnPackContent.add(btnUnpack);
		pnlUnPackContent.add(btnExtract);
		pnlUnPackContent.setBackground(new Color(255, 255, 255, 30));

		pnlPackage.add(pnlPackContent, BorderLayout.NORTH);
		pnlPackage.add(pnlUnPackContent, BorderLayout.SOUTH);
		pnlPackage.setBackground(new Color(255, 255, 255, 0));
		pnlPackage.setVisible(true);
		return pnlPackage;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChooseFile1) {
			try {
				File fileChoosen = mainView.chooseFileFolder();
				if (fileChoosen != null) {
					txtOpenFile1.setText(fileChoosen.getAbsolutePath());
				} else {
					System.out.println("no choose");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnChooseFile2) {
			try {
				File fileChoosen = mainView.chooseFile();
				if (fileChoosen != null) {
					txtOpenFile2.setText(fileChoosen.getAbsolutePath());
				} else {
					System.out.println("no choose");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnPack) {
			if (txtOpenFile1.getText() == null||txtOpenFile1.getText().equals("")) {
				System.out.println("enter the input plz!");
				pckControlelr.warningPack();
			} else {
				if (txtSaveFile1.getText() == null||txtSaveFile1.getText().equals("")) {
					pckControlelr.warningSavePath();
				}else {
					try {
						System.out.println("Pack");
						pckControlelr.packFolder(txtOpenFile1.getText(), txtSaveFile1.getText());
						pckControlelr.warningSuccess();
						txtOpenFile1.setText("");
						txtSaveFile1.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
		if (e.getSource() == btnUnpack) {
			if (txtOpenFile2.getText() == null||txtOpenFile2.getText().equals("")) {
				System.out.println("enter the input plz!");
				pckControlelr.warningPack();
			} else {
				if (txtSaveFile2.getText() == null||txtSaveFile2.getText().equals("")) {
					pckControlelr.warningSavePath();
				}else {
					try {
						System.out.println("Unpack");
						pckControlelr.unpackFolder(txtOpenFile2.getText(), txtSaveFile2.getText());
						pckControlelr.warningSuccess();
						txtOpenFile2.setText("");
						txtSaveFile2.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
		if (e.getSource() == btnSaveFile1) {
			try {
				File fileChoosen = mainView.chooseFileFolder();
				if (fileChoosen != null) {
					txtSaveFile1.setText(fileChoosen.getAbsolutePath());
				} else {
					System.out.println("no choose");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnSaveFile2) {
			try {
				File fileChoosen = mainView.chooseFileFolder();
				if (fileChoosen != null) {
					txtSaveFile2.setText(fileChoosen.getAbsolutePath());
				} else {
					System.out.println("no choose");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
