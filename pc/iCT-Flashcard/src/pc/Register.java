package pc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserReg;
	private JTextField NameReg;
	private JTextField EmailReg;
	private JPasswordField PassReg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 120, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(119, 136, 153));
		TopPanel.setBounds(0, 0, 400, 36);
		contentPane.add(TopPanel);
		TopPanel.setLayout(null);
		
		JLabel Close = new JLabel("X");
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Login log = new Login();
				log.setVisible(true);
			}
		});
		
		JLabel lbICT = new JLabel("iCT Flashcard");
		lbICT.setBounds(0, 0, 400, 36);
		TopPanel.add(lbICT);
		lbICT.setForeground(new Color(255, 255, 255));
		lbICT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbICT.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setForeground(new Color(255, 255, 255));
		Close.setFont(new Font("Tahoma", Font.BOLD, 16));
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setBounds(354, 0, 46, 36);
		TopPanel.add(Close);
		
		JLabel Miniature_1 = new JLabel("_");
		Miniature_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		Miniature_1.setForeground(new Color(255, 255, 255));
		Miniature_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		Miniature_1.setHorizontalAlignment(SwingConstants.CENTER);
		Miniature_1.setBounds(308, 0, 46, 36);
		TopPanel.add(Miniature_1);
		
		JLabel icon = new JLabel("");
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(Register.class.getResource("/images/imgonline-com-ua-shape-EoeaJBkEiNsZrG.png")));
		icon.setBounds(0, 59, 400, 58);
		contentPane.add(icon);
		
		JLabel LableReg = new JLabel("\u0110\u0102NG K\u00DD");
		LableReg.setFont(new Font("Tahoma", Font.BOLD, 20));
		LableReg.setHorizontalAlignment(SwingConstants.CENTER);
		LableReg.setBounds(0, 128, 400, 25);
		contentPane.add(LableReg);
		
		UserReg = new JTextField();
		UserReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UserReg.setBounds(75, 201, 258, 31);
		contentPane.add(UserReg);
		UserReg.setColumns(10);
		
		JLabel lbUser = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbUser.setBounds(75, 171, 102, 25);
		contentPane.add(lbUser);
		
		JLabel lbPassword = new JLabel("M\u1EADt kh\u1EA9u");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPassword.setBounds(75, 239, 102, 25);
		contentPane.add(lbPassword);
		
		JLabel lbFname = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
		lbFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFname.setBounds(75, 298, 102, 25);
		contentPane.add(lbFname);
		
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEmail.setBounds(75, 357, 102, 25);
		contentPane.add(lbEmail);
		
		NameReg = new JTextField();
		NameReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NameReg.setColumns(10);
		NameReg.setBounds(75, 322, 258, 31);
		contentPane.add(NameReg);
		
		EmailReg = new JTextField();
		EmailReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		EmailReg.setColumns(10);
		EmailReg.setBounds(75, 381, 258, 31);
		contentPane.add(EmailReg);
		
		JButton btnRegister = new JButton("\u0110\u0103ng k\u00FD");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = UserReg.getText();
				@SuppressWarnings("deprecation")
				String pw = PassReg.getText();
				String email = EmailReg.getText();
				String fname = NameReg.getText();
				
				System.out.println(user + pw + email + fname);
				Network check = new Network();
				try {
					check.register(user, pw, email, fname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnRegister.setBackground(new Color(65, 105, 225));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(144, 433, 112, 36);
		contentPane.add(btnRegister);
		
		PassReg = new JPasswordField();
		PassReg.setBounds(75, 262, 258, 31);
		contentPane.add(PassReg);
		
		setUndecorated(true);
	}
}
