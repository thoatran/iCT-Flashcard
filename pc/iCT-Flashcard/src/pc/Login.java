package pc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameLogin;
	private JPasswordField PasswordLogin;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 180, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameLogin = new JTextField();
		UsernameLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UsernameLogin.setBounds(177, 157, 279, 31);
		contentPane.add(UsernameLogin);
		UsernameLogin.setColumns(10);
		
		JButton btnLogin = new JButton("\u0110\u0103ng nh\u1EADp");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				Network check = new Network();
				String user = UsernameLogin.getText();
				String pw = PasswordLogin.getText();
				System.out.println("Login Infor: " + user +" - "+ pw);
				try {
					check.getLoginState(user, pw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Dashboard main = new Dashboard();
				main.setVisible(true);
				dispose();
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(65, 105, 225));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnLogin.setBounds(177, 273, 131, 31);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("\u0110\u0103ng k\u00FD");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Register reg = new Register();
				reg.setVisible(true);
			}
		});
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(65, 105, 225));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegister.setBounds(325, 273, 131, 31);
		contentPane.add(btnRegister);
		
		PasswordLogin = new JPasswordField();
		PasswordLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PasswordLogin.setBounds(177, 199, 279, 31);
		contentPane.add(PasswordLogin);
		
		JLabel lbEmail = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lbEmail.setForeground(new Color(0, 0, 0));
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEmail.setBounds(74, 157, 93, 31);
		contentPane.add(lbEmail);
		
		JLabel lbPassword = new JLabel("M\u1EADt kh\u1EA9u");
		lbPassword.setForeground(new Color(0, 0, 0));
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPassword.setBounds(74, 201, 74, 27);
		contentPane.add(lbPassword);
		
		JLabel lbLogin = new JLabel("\u0110\u0102NG NH\u1EACP");
		lbLogin.setIcon(null);
		lbLogin.setForeground(new Color(0, 0, 0));
		lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogin.setFont(new Font("Tahoma", Font.BOLD, 23));
		lbLogin.setBounds(0, 107, 629, 39);
		contentPane.add(lbLogin);
		
		JLabel lbForgot = new JLabel("Qu\u00EAn m\u1EADt kh\u1EA9u?");
		lbForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword fgpw = new ForgotPassword();
				fgpw.setVisible(true);
				dispose();
			}
		});
		lbForgot.setForeground(new Color(0, 0, 205));
		lbForgot.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lbForgot.setBounds(177, 241, 131, 21);
		contentPane.add(lbForgot);
		
		JLabel icon = new JLabel("");
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(Login.class.getResource("/images/imgonline-com-ua-shape-EoeaJBkEiNsZrG.png")));
		icon.setBounds(0, 42, 629, 66);
		contentPane.add(icon);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(119, 136, 153));
		TopPanel.setBounds(0, 0, 629, 31);
		contentPane.add(TopPanel);
		TopPanel.setLayout(null);
		
		JLabel Miniature = new JLabel("_");
		Miniature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		
		JLabel Close = new JLabel("X");
		Close.setBounds(577, 0, 52, 31);
		TopPanel.add(Close);
		Close.setBackground(new Color(240, 255, 240));
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		Close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Close.setForeground(new Color(255, 255, 255));
		Close.setFont(new Font("Tahoma", Font.BOLD, 14));
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Miniature.setForeground(new Color(255, 255, 255));
		Miniature.setBounds(549, 0, 37, 31);
		Miniature.setHorizontalAlignment(SwingConstants.CENTER);
		Miniature.setFont(new Font("Tahoma", Font.BOLD, 16));
		TopPanel.add(Miniature);
		
		JLabel lbICT = new JLabel("iCT Flashcard");
		lbICT.setForeground(new Color(255, 255, 255));
		lbICT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbICT.setHorizontalAlignment(SwingConstants.CENTER);
		lbICT.setBounds(0, 0, 629, 31);
		TopPanel.add(lbICT);
		
		JTextPane LoginFalsePane = new JTextPane();
		LoginFalsePane.setForeground(new Color(255, 0, 0));
		LoginFalsePane.setFont(new Font("Tahoma", Font.ITALIC, 14));
		LoginFalsePane.setText("T\u00E0i kho\u1EA3n ho\u1EB7c m\u1EADt kh\u1EA9u kh\u00F4ng \u0111\u00FAng. Vui l\u00F2ng th\u1EED l\u1EA1i.");
		LoginFalsePane.setBounds(143, 314, 346, 31);
		contentPane.add(LoginFalsePane);
		LoginFalsePane.setVisible(false);
		
		setUndecorated(true);
	}
}
