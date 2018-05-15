package com.shika.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.shika.application.controller.MainViewController;

public class GUITree {

	JPanel pnlPackage, pnlPackContent, pnlUnPackContent;
	JButton btnChooseFile1, btnChooseFile2;
	JTextField txtLink1, txtLink2;
	JLabel lblLink1, lblLink2;
	MainViewController mainView;

	public GUITree(MainViewController mainView) {
		this.mainView = mainView;
	}

	public JPanel nplTree() {
		pnlPackage = new JPanel(new BorderLayout());
		pnlPackage.setBorder(new TitledBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Package and UnPackage", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		pnlPackContent = new JPanel(new FlowLayout());
		pnlPackContent.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Folder Tree", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#ffffff"))));
		pnlUnPackContent = new JPanel(new FlowLayout());

		lblLink1 = new JLabel("Link File|Folder: ");
		lblLink1.setFont(new Font("Sans Serif", Font.BOLD, 12));
		txtLink1 = new JTextField(45);
		txtLink1.setEnabled(false);
		btnChooseFile1 = new JButton("Choose");
		pnlPackContent.add(lblLink1);
		pnlPackContent.add(txtLink1);
		pnlPackContent.add(btnChooseFile1);
		pnlPackContent.setBackground(new Color(255, 255, 255, 0));

		pnlPackage.add(pnlPackContent, BorderLayout.NORTH);
		pnlPackage.add(pnlUnPackContent, BorderLayout.CENTER);
		pnlPackage.setBackground(new Color(255, 255, 255, 0));
		pnlPackage.setVisible(true);
		return pnlPackage;

	}

}
