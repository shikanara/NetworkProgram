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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.shika.application.controller.MainViewController;
import com.shika.application.controller.PackController;
import com.shika.application.controller.SplitController;

public class GUISplit implements ActionListener {
	JPanel nplSplit;
	JPanel pnlSplitage, pnlSplitContent, pnlUnSplitContent;
	JButton btnChooseFile1, btnChooseFile2, btnSplit, btnJoin, btnExtract, btnSaveFile1, btnSaveFile2;
	JTextField txtOpenFile1, txtOpenFile2, txtSaveFile1, txtSaveFile2, txtNumber, txtSize;
	JLabel lblOpenFile1, lblOpenFile2, lblSaveFile1, lblSaveFile2, lblSize;
	MainViewController mainView;
	BufferedImage icoBtnChoose, icoBtnJoin, icoBtnSplit, icoBtnExtract, icoBtnSave;
	JRadioButton rdiNumber, rdiSize;
	ButtonGroup btnGroup;
	JFrame frame;
	SplitController sptController;

	public GUISplit(MainViewController mainView, JFrame frame) {
		this.frame = frame;
		this.mainView = mainView;
		sptController = new SplitController();
	}

	public JPanel nplSplit() {
		nplSplit = new JPanel(new GridLayout(2, 3));
		nplSplit.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		pnlSplitContent = new JPanel(new FlowLayout());
		pnlSplitContent.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Split", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));

		try {
			icoBtnChoose = ImageIO.read(getClass().getResource("/image/folder.png"));
			icoBtnSave = ImageIO.read(getClass().getResource("/image/save.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblOpenFile1 = new JLabel("Open File:             ");
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
		rdiNumber = new JRadioButton("File number: ");
		rdiNumber.setSelected(true);
		rdiNumber.requestFocus(true);
		// rdiNumber.setBackground(new Color(255, 255, 255,0));
		rdiNumber.addActionListener(this);
		txtNumber = new JTextField(15);
		txtNumber.setForeground(Color.BLUE);
		rdiSize = new JRadioButton("File size: ");
		// rdiSize.setBackground(new Color(255, 255, 255,0));
		rdiSize.addActionListener(this);
		txtSize = new JTextField(14);
		txtSize.setForeground(Color.BLUE);
		txtSize.setEnabled(false);
		lblSize = new JLabel("(KB)");
		btnGroup = new ButtonGroup();
		btnGroup.add(rdiNumber);
		btnGroup.add(rdiSize);

		btnSplit = new JButton("Split");
		btnSplit.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnSplit = ImageIO.read(getClass().getResource("/image/split.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnSplit.setIcon(new ImageIcon(icoBtnSplit));
		btnSplit.addActionListener(this);
		pnlSplitContent.add(lblOpenFile1);
		pnlSplitContent.add(txtOpenFile1);
		pnlSplitContent.add(btnChooseFile1);
		pnlSplitContent.add(lblSaveFile1);
		pnlSplitContent.add(txtSaveFile1);
		pnlSplitContent.add(btnSaveFile1);
		pnlSplitContent.add(rdiNumber);
		pnlSplitContent.add(txtNumber);
		pnlSplitContent.add(rdiSize);
		pnlSplitContent.add(txtSize);
		pnlSplitContent.add(lblSize);
		pnlSplitContent.add(btnSplit);
		pnlSplitContent.setBackground(new Color(255, 255, 255, 30));

		pnlUnSplitContent = new JPanel(new FlowLayout());
		pnlUnSplitContent.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Join", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		lblOpenFile2 = new JLabel("Open file join:     ");
		lblOpenFile2.setFont(new Font("Sans Serif", Font.BOLD, 12));
		txtOpenFile2 = new JTextField(45);
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

		btnJoin = new JButton("Join");
		btnJoin.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnJoin = ImageIO.read(getClass().getResource("/image/join.png"));
			btnJoin.setIcon(new ImageIcon(icoBtnJoin));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnJoin.addActionListener(this);

		pnlUnSplitContent.add(lblOpenFile2);
		pnlUnSplitContent.add(txtOpenFile2);
		pnlUnSplitContent.add(btnChooseFile2);
		pnlUnSplitContent.add(lblSaveFile2);
		pnlUnSplitContent.add(txtSaveFile2);
		pnlUnSplitContent.add(btnSaveFile2);
		pnlUnSplitContent.add(btnJoin);
		pnlUnSplitContent.setBackground(new Color(255, 255, 255, 30));

		nplSplit.add(pnlSplitContent, BorderLayout.NORTH);
		nplSplit.add(pnlUnSplitContent, BorderLayout.SOUTH);
		nplSplit.setBackground(new Color(255, 255, 255, 0));
		nplSplit.setVisible(true);
		return nplSplit;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChooseFile1) {
			try {
				File fileChoosen = mainView.chooseFileSplit();
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
				File fileChoosen = mainView.chooseFileFolder();
				if (fileChoosen != null) {
					txtOpenFile2.setText(fileChoosen.getAbsolutePath());
				} else {
					System.out.println("no choose");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnSplit) {
			if (txtOpenFile1.getText() == null || txtOpenFile1.getText().equals("")) {
				System.out.println("enter the input plz!");
				sptController.warningSplit();
			} else {
				if (txtSaveFile1.getText() == null || txtSaveFile1.getText().equals("")) {
					sptController.warningSavePath();
				} else {
					if (rdiNumber.isSelected()) {
						try {
							int number = Integer.parseInt(txtNumber.getText());

							if (txtNumber.getText().equals("") || txtNumber.getText() == null || number < 0) {
								sptController.warningSplit();
							} else {
								sptController.spliter(txtOpenFile1.getText(), txtSaveFile1.getText(), Integer.parseInt(txtNumber.getText()));
								sptController.warningSuccess();
								txtOpenFile1.setText("");
								txtSaveFile1.setText("");
							}

						} catch (Exception e2) {
							System.out.println("Exception number");
							sptController.warningSDFNumber();
						}
					}
					if (rdiSize.isSelected()) {
						try {
							int number = Integer.parseInt(txtSize.getText());

							if (txtSize.getText().equals("") || txtSize.getText() == null || number < 0) {
								sptController.warningSplit();
							} else {
								sptController.spliterQuanlity(txtOpenFile1.getText(), txtSaveFile1.getText(), number);
								sptController.warningSuccess();
								txtOpenFile1.setText("");
								txtSaveFile1.setText("");
							}

						} catch (Exception e2) {
							System.out.println("Exception number");
							sptController.warningSDFNumber();
						}
					}

				}
			}
		}
		if (e.getSource() == btnJoin) {
			if (txtOpenFile2.getText() == null || txtOpenFile2.getText().equals("")) {
				System.out.println("enter the input plz!");
				sptController.warningSplit();
			} else {
				if (txtSaveFile2.getText() == null || txtSaveFile2.getText().equals("")) {
					sptController.warningSavePath();
				} else {
					try {
						System.out.println("Join");

						if (sptController.joiner(txtOpenFile2.getText(), txtSaveFile2.getText()) == false) {
							sptController.warningNoChildrenFile();
						} else {
							sptController.warningSuccess();
							txtOpenFile2.setText("");
							txtSaveFile2.setText("");
						}

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

		if (!rdiNumber.isSelected()) {
			// frame.repaint();
			// rdiSize.repaint();
			// rdiNumber.repaint();
			txtNumber.setEnabled(false);
			txtSize.setEnabled(true);
			txtSize.requestFocus(true);

		} else if (rdiNumber.isSelected()) {
			// frame.repaint();
			// rdiNumber.repaint();
			// rdiSize.repaint();
			txtNumber.setEnabled(true);
			txtSize.setEnabled(false);
			txtNumber.requestFocus(true);
		}

	}
}
