package pc;

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
		setBounds(350, 210, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBounds(0, 0, 629, 32);
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
		Close.setBounds(570, 0, 59, 31);
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
		Miniature.setBounds(538, 0, 46, 31);
		TopPanel.add(Miniature);
		
		JLabel lbiCT = new JLabel("iCT Flashcard");
		lbiCT.setBounds(0, 0, 629, 31);
		TopPanel.add(lbiCT);
		lbiCT.setForeground(new Color(255, 255, 255));
		lbiCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbiCT.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lbAddFlc = new JLabel("  TH\u00CAM FLASHCARD");
		lbAddFlc.setIcon(new ImageIcon(AddWord.class.getResource("/images/Webp.net-resizeimage(3).png")));
		lbAddFlc.setBounds(49, 43, 518, 35);
		lbAddFlc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbAddFlc.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbAddFlc);
		
		JLabel lbPre = new JLabel("M\u1EB7t tr\u01B0\u1EDBc");
		lbPre.setBounds(110, 322, 181, 23);
		lbPre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbPre);
		
		JLabel lbBehind = new JLabel("M\u1EB7t sau");
		lbBehind.setBounds(346, 322, 181, 23);
		lbBehind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbBehind.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbBehind);
		
		JPanel PrePanel = new JPanel();
		PrePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		PrePanel.setBackground(new Color(255, 255, 255));
		PrePanel.setBounds(110, 89, 181, 224);
		contentPane.add(PrePanel);
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
		BehindPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		BehindPanel.setBackground(Color.WHITE);
		BehindPanel.setBounds(346, 89, 181, 224);
		contentPane.add(BehindPanel);
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
