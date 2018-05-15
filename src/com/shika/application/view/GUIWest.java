package com.shika.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.shika.application.controller.MainViewController;

public class GUIWest {

	JPanel pnlGrid, pnlEast;
	JFrame frame;
	MainViewController mainView;
	GUIView GUIView;
	GUICenter GUICenter;
	JPanel pnlGrid1, pnlGrid2, pnlGrid3, pnlGrid4, pnlGrid5;
	JButton bntGrid1, bntGrid2, bntGrid3, bntGrid4, bntGrid5;

	public GUIWest(JFrame frame, GUIView GUIView, GUICenter GUICenter) {
		this.GUIView = GUIView;
		this.frame = frame;
		mainView = new MainViewController(GUIView, frame);
		this.GUICenter = GUICenter;

	}

	public JPanel west() {

		pnlEast = new JPanel(new FlowLayout());

		pnlEast.setBorder(new TitledBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.decode("#fbfbfb"))));

		pnlGrid = new JPanel(new GridLayout(5, 1));
		pnlGrid.setBackground(new Color(117, 117, 117, 100));

		bntGrid1 = addButton("/image/lock.png", "Package", 1);
		bntGrid1.setEnabled(false);
		bntGrid2 = addButton("/image/treeview.png", "Folder Tree ", 2);
		bntGrid3 = addButton("/image/slipt.png", "Split|Join", 3);
		bntGrid4 = addButton("/image/copy.png", "Copy|Cut", 4);
		bntGrid5 = addButton("/image/filetype.png", "File Type", 5);

		pnlGrid.add(bntGrid1);
		pnlGrid.add(bntGrid2);
		pnlGrid.add(bntGrid3);
		pnlGrid.add(bntGrid4);
		pnlGrid.add(bntGrid5);
		
		pnlEast.add(pnlGrid);
		pnlEast.setBackground(new Color(117, 117, 117, 25));

		return pnlEast;

	}

//	public JPanel addGridFunction(String imagePath, String nameFunction, final int id) {
//		final JPanel pnlGrid = new JPanel(new BorderLayout());
//		try {
//			JLabel iconImage = new JLabel();
//			Image imgAvatar = ImageIO.read(getClass().getResource(imagePath));
//			Image newimg = imgAvatar.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
//			iconImage.setIcon(new ImageIcon(newimg));
//
//			JLabel content = new JLabel(nameFunction);
//			content.setFont(new Font("Courier New", Font.BOLD, 15));
//			content.setForeground(Color.decode("#ffffff"));
//			content.setBackground(new Color(255, 255, 255, 0));
//
//			pnlGrid.add(iconImage, BorderLayout.BEFORE_LINE_BEGINS);
//			pnlGrid.add(content, BorderLayout.CENTER);
//
//			pnlGrid.setBackground(new Color(255, 255, 255, 0));
//
//			pnlGrid.addMouseListener(new MouseListener() {
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					pnlGrid.setBackground(new Color(255, 255, 255, 50));
//					frame.repaint();
//				}
//
//				@Override
//				public void mousePressed(MouseEvent e) {
//					pnlGrid.setBackground(new Color(117, 117, 117, 200));
//					frame.repaint();
//
//				}
//
//				@Override
//				public void mouseExited(MouseEvent e) {
//					pnlGrid.setBackground(new Color(255, 255, 255, 0));
//					frame.repaint();
//
//				}
//
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					pnlGrid.setBackground(new Color(117, 117, 117, 100));
//					frame.repaint();
//
//				}
//
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					pnlGrid.setBackground(new Color(117, 117, 117, 150));
//					if (id == 1) {
//						frame.add(GUICenter.returnView(true, false, false, false, false), BorderLayout.CENTER);
//						pnlGrid.setEnabled(false);
//						pnlGrid2.setEnabled(true);
//						pnlGrid3.setEnabled(true);
//						pnlGrid4.setEnabled(true);
//						pnlGrid5.setEnabled(true);
//						frame.repaint();
//						System.out.println("1");
//					} else if (id == 2) {
//						pnlGrid.setEnabled(false);
//						pnlGrid1.setEnabled(true);
//						pnlGrid3.setEnabled(true);
//						pnlGrid4.setEnabled(true);
//						pnlGrid5.setEnabled(true);
//						frame.add(GUICenter.returnView(false, true, false, false, false), BorderLayout.CENTER);
//						frame.repaint();
//						System.out.println("2");
//					} else if (id == 3) {
//						System.out.println("3");
//					} else if (id == 4) {
//						System.out.println("4");
//					} else if (id == 5) {
//						System.out.println("5");
//					}
//					frame.repaint();
//				}
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return pnlGrid;
//
//	}

	public JButton addButton(String imagePath, String nameFunction, final int id) {
		final JButton btn = new JButton(nameFunction);

		try {

			BufferedImage iconBufferUbpack = ImageIO.read(getClass().getResource(imagePath));
			Image newimg = iconBufferUbpack.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			btn.setIcon(new ImageIcon(newimg));

//			btn.setContentAreaFilled(false);// background of button
			// btnUnpack.setVerticalTextPosition(SwingConstants.);
			btn.setHorizontalTextPosition(SwingConstants.RIGHT);

			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (id == 1) {
						System.out.println("1");
						frame.add(GUICenter.returnView(true, false, false, false, false), BorderLayout.CENTER);
						bntGrid1.setEnabled(false);
						bntGrid2.setEnabled(true);
						bntGrid3.setEnabled(true);
						bntGrid4.setEnabled(true);
						bntGrid5.setEnabled(true);
						frame.repaint();
					} else if (id == 2) {
						System.out.println("2");
						bntGrid1.setEnabled(true);
						bntGrid2.setEnabled(false);
						bntGrid3.setEnabled(true);
						bntGrid4.setEnabled(true);
						bntGrid5.setEnabled(true);
						frame.add(GUICenter.returnView(false, true, false, false, false), BorderLayout.CENTER);
						frame.repaint();
					} else if (id == 3) {
						System.out.println("3");
						bntGrid1.setEnabled(true);
						bntGrid2.setEnabled(true);
						bntGrid3.setEnabled(false);
						bntGrid4.setEnabled(true);
						bntGrid5.setEnabled(true);
						frame.add(GUICenter.returnView(false, false, true, false, false), BorderLayout.CENTER);
						frame.repaint();
					} else if (id == 4) {
						System.out.println("4");
						bntGrid1.setEnabled(true);
						bntGrid2.setEnabled(true);
						bntGrid3.setEnabled(true);
						bntGrid4.setEnabled(false);
						bntGrid5.setEnabled(true);
						frame.add(GUICenter.returnView(false, false, false, true, false), BorderLayout.CENTER);
						frame.repaint();
					} else if (id == 5) {
						System.out.println("5");
						bntGrid1.setEnabled(true);
						bntGrid2.setEnabled(true);
						bntGrid3.setEnabled(true);
						bntGrid4.setEnabled(true);
						bntGrid5.setEnabled(false);
					}
					
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return btn;

	}

}
