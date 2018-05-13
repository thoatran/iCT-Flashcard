package pc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.DropMode;

public class Dashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static Dashboard instance = null;
	private JTextField pfFname;
	private JTextField pfDescription;
	private JTextField pfEmail;
	
	public static synchronized Dashboard getInstance() {
		if (instance == null) {
            instance = new Dashboard();
        }
        return instance;
	}
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrame frame = Dashboard.getInstance();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	private void setColor(JPanel pane) {
		pane.setBackground(new Color(240,240,240));
	}
	
	private void resetColor(JPanel pane, JPanel indicator) {
		pane.setBackground(new Color(245,245,245));
		indicator.setOpaque(false);
	}

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 829, 556);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(119, 136, 153));
		TopPanel.setBounds(211, 0, 618, 37);
		contentPane.add(TopPanel);
		TopPanel.setLayout(null);
		
		JLabel Close = new JLabel("X");
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setFont(new Font("Tahoma", Font.BOLD, 16));
		Close.setForeground(new Color(255, 255, 255));
		Close.setBounds(562, 0, 46, 39);
		TopPanel.add(Close);
		
		JLabel Miniature = new JLabel("_");
		Miniature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		Miniature.setHorizontalAlignment(SwingConstants.CENTER);
		Miniature.setFont(new Font("Tahoma", Font.BOLD, 16));
		Miniature.setForeground(new Color(255, 255, 255));
		Miniature.setBounds(515, 0, 46, 39);
		TopPanel.add(Miniature);
		
		JLabel lbiCT = new JLabel("iCT Flashcard");
		lbiCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbiCT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbiCT.setForeground(new Color(255, 255, 255));
		lbiCT.setBounds(239, -1, 141, 39);
		TopPanel.add(lbiCT);
		
		JPanel side_panel = new JPanel();
		side_panel.setBackground(new Color(245, 245, 245));
		side_panel.setBounds(10, 0, 202, 556);
		contentPane.add(side_panel);
		side_panel.setLayout(null);
		
		JLabel lblXinCho = new JLabel("");
		lblXinCho.setBackground(new Color(245, 245, 245));
		lblXinCho.setIcon(new ImageIcon(Dashboard.class.getResource("/images/imgonline-com-ua-shape-EoeaJBkEiNsZrG.png")));
		lblXinCho.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinCho.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblXinCho.setBounds(0, 0, 202, 96);
		side_panel.add(lblXinCho);
		
		JPanel btnHome = new JPanel();
		btnHome.setBounds(0, 96, 212, 49);
		side_panel.add(btnHome);
		btnHome.setLayout(null);
		
		JPanel indHome = new JPanel();
		indHome.setBackground(Color.DARK_GRAY);
		indHome.setBounds(0, 0, 4, 49);
		btnHome.add(indHome);
		
		JLabel lbHome = new JLabel("   Trang ch\u1EE7");
		lbHome.setBounds(31, 0, 148, 49);
		btnHome.add(lbHome);
		lbHome.setIcon(new ImageIcon(Dashboard.class.getResource("/images/002-web-page-home.png")));
		lbHome.setBackground(new Color(70, 130, 180));
		lbHome.setForeground(Color.DARK_GRAY);
		lbHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHome.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel btnFlashcard = new JPanel();
		btnFlashcard.setBackground(new Color(245, 245, 245));
		btnFlashcard.setBounds(0, 144, 212, 49);
		side_panel.add(btnFlashcard);
		btnFlashcard.setLayout(null);
		
		JPanel indFlashcard = new JPanel();
		indFlashcard.setBackground(Color.DARK_GRAY);
		indFlashcard.setOpaque(false);
		indFlashcard.setBounds(0, 0, 4, 49);
		btnFlashcard.add(indFlashcard);
		
		JLabel lbFlashcard = new JLabel("   Flashcard");
		lbFlashcard.setBounds(28, 0, 148, 49);
		btnFlashcard.add(lbFlashcard);
		lbFlashcard.setIcon(new ImageIcon(Dashboard.class.getResource("/images/004-web.png")));
		lbFlashcard.setForeground(Color.DARK_GRAY);
		lbFlashcard.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbFlashcard.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel btnReview = new JPanel();
		btnReview.setBackground(new Color(245, 245, 245));
		btnReview.setBounds(0, 192, 212, 49);
		side_panel.add(btnReview);
		btnReview.setLayout(null);
		
		JPanel indReview = new JPanel();
		indReview.setBackground(Color.DARK_GRAY);
		indReview.setOpaque(false);
		indReview.setBounds(0, 0, 4, 49);
		btnReview.add(indReview);
		
		JLabel lbReview = new JLabel("   \u00D4n t\u1EADp");
		lbReview.setBounds(35, 0, 148, 49);
		btnReview.add(lbReview);
		lbReview.setIcon(new ImageIcon(Dashboard.class.getResource("/images/001-star.png")));
		lbReview.setForeground(Color.DARK_GRAY);
		lbReview.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbReview.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel btnAccount = new JPanel();
		btnAccount.setBackground(new Color(245, 245, 245));
		btnAccount.setBounds(0, 241, 212, 49);
		side_panel.add(btnAccount);
		btnAccount.setLayout(null);
		
		JPanel indAccount = new JPanel();
		indAccount.setBackground(Color.DARK_GRAY);
		indAccount.setOpaque(false);
		indAccount.setBounds(0, 0, 4, 49);
		btnAccount.add(indAccount);
		
		JLabel lbAccount = new JLabel("   T\u00E0i kho\u1EA3n");
		lbAccount.setBounds(34, 0, 148, 49);
		btnAccount.add(lbAccount);
		lbAccount.setIcon(new ImageIcon(Dashboard.class.getResource("/images/user (3).png")));
		lbAccount.setForeground(Color.DARK_GRAY);
		lbAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbAccount.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel btnAbout = new JPanel();
		btnAbout.setBackground(new Color(245, 245, 245));
		btnAbout.setBounds(0, 289, 212, 49);
		side_panel.add(btnAbout);
		btnAbout.setLayout(null);
		
		JPanel indAbout = new JPanel();
		indAbout.setBackground(Color.DARK_GRAY);
		indAbout.setOpaque(false);
		indAbout.setBounds(0, 0, 4, 49);
		btnAbout.add(indAbout);
		
		JLabel lbAbout = new JLabel("   Th\u00F4ng tin");
		lbAbout.setBounds(33, 0, 153, 49);
		btnAbout.add(lbAbout);
		lbAbout.setIcon(new ImageIcon(Dashboard.class.getResource("/images/005-round.png")));
		lbAbout.setForeground(Color.DARK_GRAY);
		lbAbout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbAbout.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel sub_title = new JPanel();
		sub_title.setBackground(new Color(255, 255, 255));
		sub_title.setBounds(211, 37, 618, 37);
		contentPane.add(sub_title);
		sub_title.setLayout(null);
		
		JButton btnTin = new JButton("T\u1EEB \u0111i\u1EC3n");
		btnTin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Dictionary dict = new Dictionary();
				dict.setVisible(true);
			}
		});
		btnTin.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnTin.setBackground(new Color(255, 255, 255));
		btnTin.setIcon(new ImageIcon(Dashboard.class.getResource("/images/book (1).png")));
		btnTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTin.setBounds(498, 11, 99, 26);
		sub_title.add(btnTin);
		
		JPanel DisplayCard = new JPanel();
		DisplayCard.setBackground(Color.WHITE);
		DisplayCard.setBounds(211, 77, 618, 479);
		contentPane.add(DisplayCard);
		DisplayCard.setLayout(new CardLayout(0, 0));
		
		JPanel Home = new JPanel();
		DisplayCard.add(Home, "name_1630474127553");
		Home.setBackground(Color.WHITE);
		Home.setLayout(null);
		
		JLabel lbHistory = new JLabel("Flashcard g\u1EA7n \u0111\u00E2y");
		lbHistory.setBounds(34, 11, 143, 37);
		Home.add(lbHistory);
		lbHistory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		JPanel table_flashcard = new JPanel();
		table_flashcard.setBounds(34, 62, 551, 214);
		Home.add(table_flashcard);
		table_flashcard.setBackground(new Color(245, 245, 245));
		
		JPanel divPanel = new JPanel();
		divPanel.setBounds(34, 45, 551, 3);
		Home.add(divPanel);
		divPanel.setBackground(new Color(245, 245, 245));
		
		JButton btnAddCard = new JButton("Th\u00EAm nhanh Flashcard");
		btnAddCard.setBounds(33, 316, 277, 91);
		Home.add(btnAddCard);
		btnAddCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AddWord add = new AddWord();
				add.setVisible(true);
			}
		});
		btnAddCard.setForeground(new Color(255, 255, 255));
		btnAddCard.setBackground(new Color(0, 204, 102));
		btnAddCard.setIcon(new ImageIcon(Dashboard.class.getResource("/images/plus-6-24.png")));
		btnAddCard.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnCollection = new JButton("Qu\u1EA3n l\u00FD Flashcard");
		btnCollection.setBounds(323, 316, 263, 91);
		Home.add(btnCollection);
		btnCollection.setForeground(new Color(255, 255, 255));
		btnCollection.setBackground(new Color(153, 153, 255));
		btnCollection.setIcon(new ImageIcon(Dashboard.class.getResource("/images/edit-24.png")));
		btnCollection.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel Flashcard = new JPanel();
		DisplayCard.add(Flashcard, "name_1635249150999");
		Flashcard.setBackground(Color.WHITE);
		btnCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColor(btnFlashcard);
				indFlashcard.setOpaque(true);
				resetColor(btnHome, indHome);
				resetColor(btnReview, indReview);
				resetColor(btnAccount, indAccount);
				resetColor(btnAbout, indAbout);
				
				DisplayCard.removeAll();
				DisplayCard.add(Flashcard);
				DisplayCard.repaint();
				DisplayCard.revalidate();
			}
		});
		
		JPanel Review = new JPanel();
		DisplayCard.add(Review, "name_1636745232616");
		Review.setBackground(Color.WHITE);
		
		JPanel Account = new JPanel();
		DisplayCard.add(Account, "name_1638893159540");
		Account.setBackground(Color.WHITE);
		Account.setLayout(null);
		
		JLabel pfPicture = new JLabel("");
		pfPicture.setIcon(new ImageIcon(Dashboard.class.getResource("/images/user (2).png")));
		pfPicture.setHorizontalAlignment(SwingConstants.CENTER);
		pfPicture.setBounds(274, 57, 80, 73);
		Account.add(pfPicture);
		
		JLabel lbUsername = new JLabel("Tên tài khoản");
		lbUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lbUsername.setBounds(248, 24, 132, 22);
		Account.add(lbUsername);
		
		JLabel lbFname = new JLabel("Họ và tên");
		lbFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFname.setBounds(87, 168, 78, 22);
		Account.add(lbFname);
		
		JLabel lbDescription = new JLabel("Giới thiệu");
		lbDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDescription.setBounds(87, 214, 78, 22);
		Account.add(lbDescription);
		
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEmail.setBounds(87, 334, 78, 22);
		Account.add(lbEmail);
		
		pfFname = new JTextField();
		pfFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pfFname.setBounds(175, 168, 290, 31);
		Account.add(pfFname);
		pfFname.setColumns(10);
		
		pfDescription = new JTextField();
		pfDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pfDescription.setColumns(10);
		pfDescription.setBounds(175, 219, 290, 93);
		Account.add(pfDescription);
		
		pfEmail = new JTextField();
		pfEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pfEmail.setColumns(10);
		pfEmail.setBounds(175, 334, 290, 31);
		Account.add(pfEmail);
		
		JLabel btnChangePw = new JLabel("Thay đổi mật khẩu");
		btnChangePw.setForeground(new Color(30, 144, 255));
		btnChangePw.setHorizontalAlignment(SwingConstants.CENTER);
		btnChangePw.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnChangePw.setBounds(223, 376, 181, 22);
		Account.add(btnChangePw);
		
		JLabel btnEditProfile = new JLabel("Chỉnh sửa thông tin cá nhân");
		btnEditProfile.setForeground(new Color(30, 144, 255));
		btnEditProfile.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditProfile.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnEditProfile.setBounds(223, 398, 181, 22);
		Account.add(btnEditProfile);
		
		JLabel btnLogout = new JLabel("Đăng xuất");
		btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
		btnLogout.setForeground(new Color(30, 144, 255));
		btnLogout.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnLogout.setBounds(223, 420, 181, 22);
		Account.add(btnLogout);
		
		JLabel btnChangPicture = new JLabel("Thay đổi ảnh đại diện");
		btnChangPicture.setHorizontalAlignment(SwingConstants.CENTER);
		btnChangPicture.setForeground(new Color(30, 144, 255));
		btnChangPicture.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnChangPicture.setBounds(223, 128, 181, 22);
		Account.add(btnChangPicture);
		
		JPanel About = new JPanel();
		DisplayCard.add(About, "name_1640839116004");
		About.setBackground(Color.WHITE);
		About.setLayout(null);
		
		JLabel lbiNameProgram = new JLabel("ICT FLASHCARD");
		lbiNameProgram.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbiNameProgram.setHorizontalAlignment(SwingConstants.CENTER);
		lbiNameProgram.setBounds(0, 43, 618, 25);
		About.add(lbiNameProgram);
		
		JLabel lbConsNVA = new JLabel("Nguyễn Việt Anh");
		lbConsNVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbConsNVA.setHorizontalAlignment(SwingConstants.CENTER);
		lbConsNVA.setBounds(77, 307, 123, 25);
		About.add(lbConsNVA);
		
		JLabel lbConsTTT = new JLabel("Trần Thị Thoa");
		lbConsTTT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbConsTTT.setHorizontalAlignment(SwingConstants.CENTER);
		lbConsTTT.setBounds(226, 307, 174, 25);
		About.add(lbConsTTT);
		
		JLabel lbConsBTPA = new JLabel("Bùi Thị Phương Anh");
		lbConsBTPA.setHorizontalAlignment(SwingConstants.CENTER);
		lbConsBTPA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbConsBTPA.setBounds(410, 307, 143, 25);
		About.add(lbConsBTPA);
		
		JLabel lbConsTVH = new JLabel("Trần Vũ Hải");
		lbConsTVH.setHorizontalAlignment(SwingConstants.CENTER);
		lbConsTVH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbConsTVH.setBounds(164, 414, 135, 25);
		About.add(lbConsTVH);
		
		JLabel lbConsPNKP = new JLabel("Phạm Nguyên Khánh Phong");
		lbConsPNKP.setHorizontalAlignment(SwingConstants.CENTER);
		lbConsPNKP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbConsPNKP.setBounds(309, 414, 217, 25);
		About.add(lbConsPNKP);
		
		JLabel iconNVA = new JLabel("");
		iconNVA.setHorizontalAlignment(SwingConstants.CENTER);
		iconNVA.setIcon(new ImageIcon(Dashboard.class.getResource("/images/Webp.net-resizeimage.png")));
		iconNVA.setBounds(90, 216, 96, 77);
		About.add(iconNVA);
		
		JLabel iconTTT = new JLabel("");
		iconTTT.setIcon(new ImageIcon(Dashboard.class.getResource("/images/Webp.net-resizeimage (1).png")));
		iconTTT.setHorizontalAlignment(SwingConstants.CENTER);
		iconTTT.setBounds(264, 216, 96, 77);
		About.add(iconTTT);
		
		JLabel iconBTPA = new JLabel("");
		iconBTPA.setIcon(new ImageIcon(Dashboard.class.getResource("/images/Webp.net-resizeimage (2).png")));
		iconBTPA.setHorizontalAlignment(SwingConstants.CENTER);
		iconBTPA.setBounds(432, 216, 96, 77);
		About.add(iconBTPA);
		
		JLabel iconTVH = new JLabel("");
		iconTVH.setIcon(new ImageIcon(Dashboard.class.getResource("/images/Webp.net-resizeimage (3).png")));
		iconTVH.setHorizontalAlignment(SwingConstants.CENTER);
		iconTVH.setBounds(184, 337, 96, 77);
		About.add(iconTVH);
		
		JLabel iconPNKP = new JLabel("");
		iconPNKP.setIcon(new ImageIcon(Dashboard.class.getResource("/images/Webp.net-resizeimage (4).png")));
		iconPNKP.setHorizontalAlignment(SwingConstants.CENTER);
		iconPNKP.setBounds(357, 337, 96, 77);
		About.add(iconPNKP);
		
		JTextPane textPaneDescription = new JTextPane();
		textPaneDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneDescription.setText("iCT FLashcard là một ứng dụng cho phép người dùng tạo các bộ Flashcard để học từ vựng một cách dễ dàng, trực quan qua hình ảnh.");
		textPaneDescription.setBounds(58, 107, 495, 51);
		About.add(textPaneDescription);
		//Description.setLineWrap();
		
		
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setColor(btnHome);
				indHome.setOpaque(true);
				resetColor(btnFlashcard, indFlashcard);
				resetColor(btnReview, indReview);
				resetColor(btnAccount, indAccount);
				resetColor(btnAbout, indAbout);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DisplayCard.removeAll();
				DisplayCard.add(Home);
				DisplayCard.repaint();
				DisplayCard.revalidate();
			}
		});
		
		btnFlashcard.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setColor(btnFlashcard);
				indFlashcard.setOpaque(true);
				resetColor(btnHome, indHome);
				resetColor(btnReview, indReview);
				resetColor(btnAccount, indAccount);
				resetColor(btnAbout, indAbout);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				DisplayCard.removeAll();
				DisplayCard.add(Flashcard);
				DisplayCard.repaint();
				DisplayCard.revalidate();
			}
		});
		
		btnReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setColor(btnReview);
				indReview.setOpaque(true);
				resetColor(btnFlashcard, indFlashcard);
				resetColor(btnHome, indHome);
				resetColor(btnAccount, indAccount);
				resetColor(btnAbout, indAbout);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayCard.removeAll();
				DisplayCard.add(Review);
				DisplayCard.repaint();
				DisplayCard.revalidate();
			}
		});
		
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setColor(btnAccount);
				indAccount.setOpaque(true);
				resetColor(btnFlashcard, indFlashcard);
				resetColor(btnReview, indReview);
				resetColor(btnHome, indHome);
				resetColor(btnAbout, indAbout);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayCard.removeAll();
				DisplayCard.add(Account);
				DisplayCard.repaint();
				DisplayCard.revalidate();
			}
		});
		
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setColor(btnAbout);
				indAbout.setOpaque(true);
				resetColor(btnFlashcard, indFlashcard);
				resetColor(btnReview, indReview);
				resetColor(btnAccount, indAccount);
				resetColor(btnHome, indHome);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayCard.removeAll();
				DisplayCard.add(About);
				DisplayCard.repaint();
				DisplayCard.revalidate();
			}
		});
		
		setUndecorated(true);
		
	}
}
