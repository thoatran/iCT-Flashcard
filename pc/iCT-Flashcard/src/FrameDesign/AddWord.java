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
	private JTextField inputWord;
	private JTextField inputPronounciation;
	private JTextField inputMean;
	private JTextField pathPicture;
	private JTextField txtBFlashcard;
	private JTextField txtT;
	private JTextField txtPhinm;

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
		setBounds(350, 210, 700, 400);
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
		
		JPanel PrePanel = new JPanel();
		PrePanel.setBounds(187, 189, -147, -175);
		panel.add(PrePanel);
		PrePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		PrePanel.setBackground(new Color(255, 255, 255));
		PrePanel.setLayout(null);
		
		JButton btnUploadPicture = new JButton("Upload");
		btnUploadPicture.setBounds(48, 167, 81, 25);
		PrePanel.add(btnUploadPicture);
		btnUploadPicture.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		inputWord = new JTextField();
		inputWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inputWord.setBounds(27, 70, 123, 25);
		PrePanel.add(inputWord);
		inputWord.setBackground(new Color(255, 255, 255));
		inputWord.setColumns(10);
		
		JLabel lbWord = new JLabel("T\u1EEB");
		lbWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbWord.setBounds(27, 45, 46, 14);
		PrePanel.add(lbWord);
		
		pathPicture = new JTextField();
		pathPicture.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pathPicture.setColumns(10);
		pathPicture.setBackground(Color.WHITE);
		pathPicture.setBounds(27, 131, 123, 25);
		PrePanel.add(pathPicture);
		
		JLabel lbPicture = new JLabel("H\u00ECnh \u1EA3nh");
		lbPicture.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPicture.setBounds(27, 106, 72, 14);
		PrePanel.add(lbPicture);
		
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
		
		JLabel lbBehind = new JLabel("M\u1EB7t sau");
		lbBehind.setBounds(217, 245, 181, 23);
		panel.add(lbBehind);
		lbBehind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbBehind.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbPre = new JLabel("M\u1EB7t tr\u01B0\u1EDBc");
		lbPre.setBounds(13, 245, 181, 23);
		panel.add(lbPre);
		lbPre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPre.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(13, 13, 181, 221);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 181, 112);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JButton button_1 = new JButton("Upload ảnh");
		button_1.setBounds(48, 89, 89, 23);
		panel_5.add(button_1);
		
		txtT = new JTextField();
		txtT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtT.setText("Từ");
		txtT.setBounds(10, 134, 161, 31);
		panel_2.add(txtT);
		txtT.setColumns(10);
		
		txtPhinm = new JTextField();
		txtPhinm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPhinm.setText("Phiên âm");
		txtPhinm.setColumns(10);
		txtPhinm.setBounds(10, 179, 161, 31);
		panel_2.add(txtPhinm);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(217, 13, 181, 221);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JTextPane txtpnNgha = new JTextPane();
		txtpnNgha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnNgha.setText("Nghĩa");
		txtpnNgha.setBounds(20, 26, 140, 173);
		txtpnNgha.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.add(txtpnNgha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(23, 85, 176, 276);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 11, 176, 110);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JButton button = new JButton("Upload ảnh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(42, 87, 89, 23);
		panel_4.add(button);
		
		txtBFlashcard = new JTextField();
		txtBFlashcard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBFlashcard.setText("Bộ Flashcard");
		txtBFlashcard.setBounds(10, 132, 156, 30);
		panel_1.add(txtBFlashcard);
		txtBFlashcard.setColumns(10);
		
		JTextArea txtrMT = new JTextArea();
		txtrMT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrMT.setText("Mô tả");
		txtrMT.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtrMT.setBackground(Color.WHITE);
		txtrMT.setBounds(10, 173, 156, 80);
		panel_1.add(txtrMT);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AddWord.class.getResource("/images/right-arrow (1).png")));
		label.setBounds(644, 179, 46, 71);
		contentPane.add(label);
		
		btnUploadPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				pathPicture.setText(filename);
			}
		});
		
		setUndecorated(true);
	}
}
