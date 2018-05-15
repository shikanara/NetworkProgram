package com.shika.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.shika.application.controller.AuthorController;
import com.shika.application.controller.GuideController;
import com.shika.application.controller.MainViewController;
import com.shika.application.model.UserDAO;

@SuppressWarnings("serial")
public class GUIView extends JFrame implements WindowListener, ActionListener {

	private JMenuBar menuBar;
	private JMenu file, infor;
	private JMenuItem fresh, exit, guide, inforAuthor;
	GuideController guideController;
	AuthorController authorController;
	JPanel pnlIcon_File;
	JButton btnIcon;
	JButton btnFile;
	JPanel pnlSouth, pnlSouthParent, pnlVideo, pnlTmp, pnlNorth, pnlCenter;
	JButton btnOpenWC, btnPause, bntCloseWC, btnMinium, btnMicrohone, btnConnect;
	public Image image;
	MainViewController mViewController;
	GUILogin login;
	GUIClosing close;
	UserDAO userDao;
	GUIWest west;
	GUINorth GUINorth;
	GUICenter GUICenter;
	GUISouth GUISouth;

	public GUIView() throws Exception {
		super();
		mViewController = new MainViewController(this, this);
		userDao = new UserDAO();// khoi tao userDao
		login = new GUILogin(userDao);//
		close = new GUIClosing();
		GUICenter = new GUICenter(mViewController, this);
		west = new GUIWest(this, this, GUICenter);
		GUINorth = new GUINorth();
		GUISouth = new GUISouth();
		view();
	}

	public JButton getBtnIcon() {
		return btnIcon;
	}

	public void setBtnIcon(JButton btnIcon) {
		this.btnIcon = btnIcon;
	}

	// view tổng
	public void view() throws IOException {
		// Tạo hình nền
		BufferedImage myImage = ImageIO.read(getClass().getResource("/image/spot.jpg"));
		setContentPane(new JLabel(new ImageIcon(myImage)));
		setLayout(new BorderLayout());
		// Tạo Menubar
		menuBar();
		// Thêm các jpanel
		add(GUINorth.north(), BorderLayout.NORTH);

		add(west.west(), BorderLayout.WEST);
		// add(GUICenter.returnView(false,false,false,false,false),
		// BorderLayout.CENTER);
		add(GUISouth.south(), BorderLayout.SOUTH);
		add(GUICenter.addJpanel(), BorderLayout.CENTER);

		repaint();
		setTitle("Shika's application");
		// icon
		Image imgAvatar1 = ImageIO.read(getClass().getResource("/image/icon.png"));
		getContentPane().setBackground(null);
		// setUndecorated(true);//excute with title bar (không cho phép thu nhỏ phóng to
		setIconImage(imgAvatar1);
		setSize(new Dimension(780, 620));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		// setResizable(true);
		this.addWindowListener(this);
		setExtendedState(JFrame.HEIGHT); // full size
		setVisible(true);
		// sự kiện thoát game
		login.login();

	}

	// menuBar
	public void menuBar() {
		menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		// đổi màu cho cái background menuBar
		try {
			/*
			 * Thuc hien phuong thuc o duoi se cho ra giao hien khac voi giao dien mac dinh
			 * cua swing
			 */
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
//	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//	        if ("Nimbus".equals(info.getName())) {
//	            try {
//					UIManager.setLookAndFeel(info.getClassName());
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            break;
//	        }
//	    }

		file = new JMenu("File");
		file.setIcon(new ImageIcon(getClass().getResource("/image/file.png")));
		file.setMnemonic(KeyEvent.VK_F);

		fresh = new JMenuItem("New");
		fresh.setMnemonic(KeyEvent.VK_N);
		fresh.setIcon(new ImageIcon(getClass().getResource("/image/new.png")));
		file.add(fresh);

		exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setIcon(new ImageIcon(getClass().getResource("/image/shutdown.png")));
		file.add(exit);

		infor = new JMenu("About");
		infor.setIcon(new ImageIcon(getClass().getResource("/image/question.png")));
		infor.setMnemonic(KeyEvent.VK_A);

		guide = new JMenuItem("Guide");
		guide.setMnemonic(KeyEvent.VK_G);
		guide.setIcon(new ImageIcon(getClass().getResource("/image/description.png")));
		infor.add(guide);

		inforAuthor = new JMenuItem("Introduce");
		inforAuthor.setMnemonic(KeyEvent.VK_I);
		inforAuthor.setIcon(new ImageIcon(getClass().getResource("/image/info.png")));
		infor.add(inforAuthor);

		menuBar.add(file);
		menuBar.add(infor);

		fresh.addActionListener(this);
		guide.addActionListener(this);
		inforAuthor.addActionListener(this);
		exit.addActionListener(this);
		setJMenuBar(menuBar);

	}

	// chứa các thành phần ở phía nam của frame
	public JPanel south() throws IOException {
		pnlSouth = new JPanel(new BorderLayout());// gom tất cả thành phần và ép nó về phía dưới của frame tổng

		pnlSouth.setBackground(new Color(255, 255, 255, 0));
		return pnlSouth;

	}

	// sự kiện các button
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {// sự kiện exit
			close.windowsClosing();
		}
		if (e.getSource() == fresh) {// fresh lại ứng dụng
		}
		if (e.getSource() == guide) {// hướng dẫn sử dụng
			guideController = new GuideController();
		}
		if (e.getSource() == inforAuthor) {// thông tin tác giả
			authorController = new AuthorController();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		close.windowsClosing();

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	public static void main(String[] args) throws Exception {
		new GUIView();
	}
}
