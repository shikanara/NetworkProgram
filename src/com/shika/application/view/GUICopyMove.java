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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.shika.application.controller.CopyMoveController;
import com.shika.application.controller.MainViewController;
import com.shika.application.controller.PackController;

public class GUICopyMove implements ActionListener {
	JPanel pnlCopyMove, pnlCopyContent;
	JButton btnChooseFile1, btnPack, btnCopy, btnMove, btnSaveFile1;
	ButtonGroup btnGroup;
	JTextField txtOpenFile1, txtSaveFile1;
	JLabel lblOpenFile1, lblSaveFile1, lblHasuses;
	JCheckBox cecBoxByte, cecBoxBuffer, cecBoxTransfer, cecBoxNone;
	MainViewController mainView;
	BufferedImage icoBtnChoose, icoBtnCopy, icoBtnPack, icoBtnMove, icoBtnSave;
	CopyMoveController copyMController;

	public GUICopyMove(MainViewController mainView) {
		this.mainView = mainView;
		copyMController = new CopyMoveController();
	}

	public JPanel nplCopyMove() {
		pnlCopyMove = new JPanel(new GridLayout(2, 3));

		pnlCopyContent = new JPanel(new FlowLayout());
		pnlCopyContent.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Copy|Move", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));

		try {
			icoBtnChoose = ImageIO.read(getClass().getResource("/image/folder.png"));
			icoBtnSave = ImageIO.read(getClass().getResource("/image/save.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblOpenFile1 = new JLabel("Open folder|file:   ");
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
		lblHasuses = new JLabel("Has uses: ");
		lblHasuses.setFont(new Font("Sans Serif", Font.BOLD, 12));

		btnGroup = new ButtonGroup();
		cecBoxByte = new JCheckBox("Byte array");
		cecBoxByte.setSelected(true);
		cecBoxByte.addActionListener(this);
		cecBoxBuffer = new JCheckBox("Buffer stream");
		cecBoxBuffer.addActionListener(this);
		cecBoxTransfer = new JCheckBox("Transfer copy");
		cecBoxTransfer.addActionListener(this);
		cecBoxNone = new JCheckBox("None");
		cecBoxNone.addActionListener(this);
		btnGroup.add(cecBoxBuffer);
		btnGroup.add(cecBoxByte);
		btnGroup.add(cecBoxTransfer);
		btnGroup.add(cecBoxNone);

		btnCopy = new JButton("Copy");
		// btnUnpack.setContentAreaFilled(false);// background of button
		// btnUnpack.setBackground(new Color(117, 117, 117, 200));
		// btnUnpack.setVerticalTextPosition(SwingConstants.);
		btnCopy.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnCopy = ImageIO.read(getClass().getResource("/image/copy2.png"));
			btnCopy.setIcon(new ImageIcon(icoBtnCopy));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnCopy.addActionListener(this);
		btnMove = new JButton("Move");
		btnMove.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnMove.addActionListener(this);
		btnMove.setHorizontalTextPosition(SwingConstants.RIGHT);
		try {
			icoBtnMove = ImageIO.read(getClass().getResource("/image/move.png"));
			btnMove.setIcon(new ImageIcon(icoBtnMove));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		pnlCopyContent.add(lblOpenFile1);
		pnlCopyContent.add(txtOpenFile1);
		pnlCopyContent.add(btnChooseFile1);
		pnlCopyContent.add(lblSaveFile1);
		pnlCopyContent.add(txtSaveFile1);
		pnlCopyContent.add(btnSaveFile1);
		pnlCopyContent.add(lblHasuses);
		pnlCopyContent.add(cecBoxByte);
		pnlCopyContent.add(cecBoxBuffer);
		pnlCopyContent.add(cecBoxTransfer);
		pnlCopyContent.add(cecBoxNone);
		pnlCopyContent.add(btnCopy);
		pnlCopyContent.add(btnMove);
		pnlCopyContent.setBackground(new Color(255, 255, 255, 30));

		pnlCopyMove.add(pnlCopyContent, BorderLayout.NORTH);
		// pnlCopyMove.add(pnlCopyContent, BorderLayout.SOUTH);
		pnlCopyMove.setBackground(new Color(255, 255, 255, 0));
		pnlCopyMove.setVisible(true);
		return pnlCopyMove;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChooseFile1) {
			try {
				File fileChoosen = mainView.chooseFileCopy();
				if (fileChoosen != null) {
					txtOpenFile1.setText(fileChoosen.getAbsolutePath());
				} else {
					System.out.println("no choose");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
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
		if (e.getSource() == btnCopy) {
			if (txtOpenFile1.getText() == null || txtOpenFile1.getText().equals("")) {
				System.out.println("enter the input plz!");
				copyMController.warningCopy();
			} else {
				if (txtSaveFile1.getText() == null || txtSaveFile1.getText().equals("")) {
					copyMController.warningSavePath();
				} else {
					try {
						if (cecBoxByte.isSelected()) {
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), false, true, false,
									false);
						} else if (cecBoxBuffer.isSelected()){
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), false, false, true,
									false);
						}else if(cecBoxTransfer.isSelected()) {
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), false, false, false,
									true);
						}else {
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), false, false, false,
									false);
						}
						copyMController.warningSuccess();
						txtOpenFile1.setText("");
						txtSaveFile1.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		}
		
		if (e.getSource() == btnMove) {
			if (txtOpenFile1.getText() == null || txtOpenFile1.getText().equals("")) {
				System.out.println("enter the input plz!");
				copyMController.warningMove();
			} else {
				if (txtSaveFile1.getText() == null || txtSaveFile1.getText().equals("")) {
					copyMController.warningSavePath();
				} else {
					try {
						if (cecBoxByte.isSelected()) {
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), true, true, false,
									false);
						} else if (cecBoxBuffer.isSelected()){
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), true, false, true,
									false);
						}else if(cecBoxTransfer.isSelected()) {
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), true, false, false,
									true);
						}else {
							copyMController.copy(txtOpenFile1.getText(), txtSaveFile1.getText(), true, false, false,
									false);
						}
						copyMController.warningSuccess();
						txtOpenFile1.setText("");
						txtSaveFile1.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		}
	}
}
