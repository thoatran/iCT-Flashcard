package pc;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.border.LineBorder;

public class ForgotPassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nhapEmail;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ForgotPassword frame = new ForgotPassword();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 210, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nhapEmail = new JTextField();
		nhapEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nhapEmail.setBounds(181, 178, 273, 29);
		contentPane.add(nhapEmail);
		nhapEmail.setColumns(10);
		
		JLabel lbEmail = new JLabel("Nh\u1EADp Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEmail.setBounds(86, 176, 85, 29);
		contentPane.add(lbEmail);
		
		JButton btnForgotPw = new JButton("L\u1EA5y l\u1EA1i m\u1EADt kh\u1EA9u");
		btnForgotPw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = nhapEmail.getText().toString();
			}
		});
		btnForgotPw.setForeground(new Color(255, 255, 255));
		btnForgotPw.setBackground(new Color(65, 105, 225));
		btnForgotPw.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnForgotPw.setBounds(249, 224, 133, 29);
		contentPane.add(btnForgotPw);
		
		JLabel lbRemind = new JLabel("C\u00E1c b\u1EA1n ki\u1EC3m tra email \u0111\u1EC3 \u0111\u01B0\u1EE3c h\u1ED7 tr\u1EE3 l\u1EA5y l\u1EA1i m\u1EADt kh\u1EA9u.");
		lbRemind.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lbRemind.setBounds(160, 264, 330, 29);
		contentPane.add(lbRemind);
		
		JLabel lbForgot = new JLabel("QU\u00CAN M\u1EACT KH\u1EA8U");
		lbForgot.setHorizontalAlignment(SwingConstants.CENTER);
		lbForgot.setFont(new Font("Tahoma", Font.BOLD, 23));
		lbForgot.setBounds(181, 122, 273, 41);
		contentPane.add(lbForgot);
		
		JLabel icon = new JLabel("");
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(ForgotPassword.class.getResource("/images/imgonline-com-ua-shape-EoeaJBkEiNsZrG.png")));
		icon.setBounds(181, 53, 273, 58);
		contentPane.add(icon);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(119, 136, 153));
		TopPanel.setBounds(0, 0, 629, 29);
		contentPane.add(TopPanel);
		TopPanel.setLayout(null);
		
		JLabel Miniature = new JLabel("_");
		Miniature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		Miniature.setForeground(new Color(255, 255, 255));
		Miniature.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Miniature.setHorizontalAlignment(SwingConstants.CENTER);
		Miniature.setBounds(550, 0, 39, 29);
		TopPanel.add(Miniature);
		
		JLabel lbiCT = new JLabel("iCT Flashcard");
		lbiCT.setForeground(new Color(255, 255, 255));
		lbiCT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbiCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbiCT.setBounds(0, 0, 629, 29);
		TopPanel.add(lbiCT);
		
		JLabel Close = new JLabel("X");
		Close.setBounds(575, 0, 54, 29);
		TopPanel.add(Close);
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login log = new Login();
				log.setVisible(true);
				
			}
		});
		Close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Close.setBackground(new Color(240, 255, 240));
		Close.setForeground(new Color(255, 255, 255));
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		
		setUndecorated(true);
	}
}
