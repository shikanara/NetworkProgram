package com.shika.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUINorth {
	JPanel pnlNorth;
	
	public GUINorth() {
	}
	
	public JPanel north() throws IOException {
		pnlNorth = new JPanel(new FlowLayout());
		JLabel image = new JLabel();
		Image imgAvatar = ImageIO.read(getClass().getResource("/image/icon.png"));
		Image newimg = imgAvatar.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
		image.setIcon(new ImageIcon(newimg));

		JPanel pnlContent = new JPanel(new BorderLayout());
		JLabel content = new JLabel("v1.0.2.8.2018 (64 bit)");
		content.setFont(new Font("Courier New", Font.BOLD, 9));
		content.setForeground(Color.decode("#ffffff"));
		content.setBackground(new Color(255, 255, 255, 0));

		pnlContent.setBackground(new Color(255, 255, 255, 0));
		pnlContent.add(image, BorderLayout.CENTER);
		pnlContent.add(content, BorderLayout.SOUTH);

		pnlNorth.add(pnlContent);
		pnlNorth.setBackground(new Color(255, 255, 255, 150));
		pnlNorth.setOpaque(false);
		return pnlNorth;

	}

}
