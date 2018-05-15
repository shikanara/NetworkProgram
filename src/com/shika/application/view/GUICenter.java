package com.shika.application.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shika.application.controller.MainViewController;

public class GUICenter {
	GUIPackage GUIPackage;
	GUISplit GUISplit;
	GUICopyMove GUICopyMove;
	MainViewController mainView;
	JFrame frame;
	JPanel pnlView;

	public GUICenter(MainViewController mainView, JFrame frame) {
		this.mainView = mainView;
		this.frame = frame;
		GUIPackage = new GUIPackage(mainView);
		GUISplit = new GUISplit(mainView,frame);
		GUICopyMove= new GUICopyMove(mainView);
	}

	public JPanel GUIPackage() {
		return GUIPackage.nplPackage();
	}
	public JPanel GUISplit() {
		return GUISplit.nplSplit();
	}
	public JPanel GUICopyMove() {
		return GUICopyMove.nplCopyMove();
	}
	public JPanel addJpanel() {
		pnlView  = GUIPackage();
		pnlView.setVisible(true);
		frame.repaint();
		return pnlView;
	}

	public JPanel returnView(boolean pack, boolean tree, boolean split, boolean copy, boolean fileType) {
		pnlView.setVisible(false);
		if (tree) {
			pnlView = new JPanel();
			pnlView.setVisible(true);
			frame.repaint();
		} else if (pack) {
			pnlView = GUIPackage();
			pnlView.setVisible(true);
			frame.repaint();
		} else if (split) {
			pnlView = GUISplit();
			pnlView.setVisible(true);
			frame.repaint();
		} else if (copy) {
			pnlView = GUICopyMove();
			pnlView.setVisible(true);
			frame.repaint();
		} else if (fileType) {

		}
		return pnlView;

	}

}
