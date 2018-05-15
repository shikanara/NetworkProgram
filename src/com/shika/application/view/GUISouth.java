package com.shika.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUISouth {
	JPanel pnlSouth;

	public GUISouth() {
	}

	public JPanel south() throws IOException {
		pnlSouth = new JPanel(new BorderLayout());// gom tất cả thành phần và ép nó về phía dưới của frame tổng

		JLabel content = new JLabel("An Application is design by Shika");
		content.setFont(new Font("Courier New", Font.BOLD, 15));
		content.setForeground(Color.decode("#ffffff"));
		content.setBackground(new Color(255, 255, 255, 0));
		
		pnlSouth.add(content,BorderLayout.AFTER_LINE_ENDS);
		pnlSouth.setBackground(new Color(117, 117, 117, 85));
		return pnlSouth;

	}

}
