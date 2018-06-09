package FrameDesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class AddWord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputPronounciation;
	private JTextField inputMean;
	private JTextField nameCol;
	private JTextField txtWord;
	private JTextField txtPronunciation;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddWord frame = new AddWord();
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
	public AddWord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 210, 700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBounds(0, 0, 700, 32);
		TopPanel.setBackground(new Color(119, 136, 153));
		contentPane.add(TopPanel);
		TopPanel.setLayout(null);
		
		JLabel Close = new JLabel("X");
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFrame frame = Dashboard.getInstance();
				frame.setVisible(true);
				dispose();
			}
		});
		Close.setForeground(new Color(255, 255, 255));
		Close.setFont(new Font("Tahoma", Font.BOLD, 15));
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setBounds(641, 0, 59, 31);
		TopPanel.add(Close);
		
		JLabel Miniature = new JLabel("_");
		Miniature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		Miniature.setHorizontalAlignment(SwingConstants.CENTER);
		Miniature.setForeground(new Color(255, 255, 255));
		Miniature.setFont(new Font("Tahoma", Font.BOLD, 16));
		Miniature.setBounds(609, 0, 46, 31);
		TopPanel.add(Miniature);
		
		JLabel lbiCT = new JLabel("iCT Flashcard");
		lbiCT.setBounds(0, 0, 629, 31);
		TopPanel.add(lbiCT);
		lbiCT.setForeground(new Color(255, 255, 255));
		lbiCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbiCT.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lbAddFlc = new JLabel("  TH\u00CAM FLASHCARD");
		lbAddFlc.setIcon(new ImageIcon(AddWord.class.getResource("/images/Webp.net-resizeimage(3).png")));
		lbAddFlc.setBounds(49, 43, 596, 35);
		lbAddFlc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbAddFlc.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbAddFlc);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(220, 85, 414, 276);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel BehindPanel = new JPanel();
		BehindPanel.setBounds(193, 13, 1, 1);
		panel.add(BehindPanel);
		BehindPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		BehindPanel.setBackground(Color.WHITE);
		BehindPanel.setLayout(null);
		
		inputPronounciation = new JTextField();
		inputPronounciation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inputPronounciation.setBounds(24, 68, 136, 26);
		BehindPanel.add(inputPronounciation);
		inputPronounciation.setColumns(10);
		
		JLabel lbPronunciation = new JLabel("Phi\u00EAn \u00E2m");
		lbPronunciation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPronunciation.setBounds(24, 43, 66, 14);
		BehindPanel.add(lbPronunciation);
		
		inputMean = new JTextField();
		inputMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inputMean.setColumns(10);
		inputMean.setBounds(24, 130, 136, 26);
		BehindPanel.add(inputMean);
		
		JLabel lbMean = new JLabel("Ngh\u0129a");
		lbMean.setVerticalAlignment(SwingConstants.TOP);
		lbMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMean.setBounds(24, 105, 46, 26);
		BehindPanel.add(lbMean);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(13, 0, 181, 234);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 181, 112);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnFlcPicture = new JButton("Thay hình");
		btnFlcPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				System.out.println(filename);
			}
		});
		btnFlcPicture.setForeground(new Color(105, 105, 105));
		btnFlcPicture.setBounds(48, 89, 89, 23);
		panel_5.add(btnFlcPicture);
		
		txtWord = new JTextField();
		txtWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtWord.setText("Từ");
		txtWord.setBounds(10, 134, 161, 31);
		panel_2.add(txtWord);
		txtWord.setColumns(10);
		
		txtPronunciation = new JTextField();
		txtPronunciation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPronunciation.setText("Phiên âm");
		txtPronunciation.setColumns(10);
		txtPronunciation.setBounds(10, 179, 161, 31);
		panel_2.add(txtPronunciation);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(217, 0, 181, 234);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JTextPane txtMeaning = new JTextPane();
		txtMeaning.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMeaning.setText("Nghĩa của từ");
		txtMeaning.setBounds(20, 26, 140, 173);
		txtMeaning.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.add(txtMeaning);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_6.setBackground(new Color(245, 245, 245));
		panel_6.setBounds(13, 233, 181, 32);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblMtTrc = new JLabel("Mặt trước");
		lblMtTrc.setForeground(new Color(128, 128, 128));
		lblMtTrc.setHorizontalAlignment(SwingConstants.CENTER);
		lblMtTrc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtTrc.setBounds(0, 0, 181, 32);
		panel_6.add(lblMtTrc);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_7.setBackground(new Color(245, 245, 245));
		panel_7.setBounds(217, 233, 181, 32);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblMtSau = new JLabel("Mặt sau");
		lblMtSau.setForeground(new Color(128, 128, 128));
		lblMtSau.setHorizontalAlignment(SwingConstants.CENTER);
		lblMtSau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtSau.setBounds(0, 0, 181, 32);
		panel_7.add(lblMtSau);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(23, 43, 176, 318);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(25, 0, 120, 108);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		nameCol = new JTextField();
		nameCol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameCol.setText("Bộ Flashcard");
		nameCol.setBounds(10, 144, 156, 30);
		panel_1.add(nameCol);
		nameCol.setColumns(10);
		
		JTextArea DescriptionCol = new JTextArea();
		DescriptionCol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DescriptionCol.setText("Mô tả");
		DescriptionCol.setBorder(new LineBorder(Color.LIGHT_GRAY));
		DescriptionCol.setBackground(Color.WHITE);
		DescriptionCol.setBounds(10, 185, 156, 80);
		panel_1.add(DescriptionCol);
		
		JButton btUploadCol = new JButton("Upload ảnh");
		btUploadCol.setForeground(new Color(105, 105, 105));
		btUploadCol.setBounds(35, 110, 97, 23);
		panel_1.add(btUploadCol);
		
		JButton btnLuThayi = new JButton("Lưu thay đổi");
		btnLuThayi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update collection infor
			}
		});
		btnLuThayi.setForeground(new Color(255, 255, 255));
		btnLuThayi.setBackground(new Color(100, 149, 237));
		btnLuThayi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuThayi.setBounds(10, 273, 156, 34);
		panel_1.add(btnLuThayi);
		btUploadCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				System.out.println(filename);
			}
		});
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//update the flashcard and refresh the panel to add other flashcard
			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AddWord.class.getResource("/images/right-arrow (1).png")));
		label.setBounds(644, 179, 46, 71);
		contentPane.add(label);
		
		setUndecorated(true);
	}
}
