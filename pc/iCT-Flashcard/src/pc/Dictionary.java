package pc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Dictionary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SearchWord;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Dictionary frame = new Dictionary();
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
	public Dictionary() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 210, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(119, 136, 153));
		TopPanel.setBounds(0, 0, 629, 31);
		contentPane.add(TopPanel);
		TopPanel.setLayout(null);
		
		JLabel lbiCT = new JLabel("iCT Flashcard");
		lbiCT.setIcon(null);
		lbiCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbiCT.setForeground(new Color(255, 255, 255));
		lbiCT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbiCT.setBounds(0, 0, 629, 31);
		TopPanel.add(lbiCT);
		
		JLabel Close = new JLabel("X");
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = Dashboard.getInstance();
				frame.setVisible(true);
				dispose();
			}
		});
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setForeground(new Color(255, 255, 255));
		Close.setFont(new Font("Tahoma", Font.BOLD, 15));
		Close.setBounds(583, 0, 46, 31);
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
		Miniature.setBounds(544, 0, 46, 31);
		TopPanel.add(Miniature);
		
		JLabel lbFindWord = new JLabel("  TRA T\u1EEA \u0110I\u1EC2N");
		lbFindWord.setIcon(new ImageIcon(Dictionary.class.getResource("/images/Webp.net-resizeimage(3).png")));
		lbFindWord.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbFindWord.setHorizontalAlignment(SwingConstants.CENTER);
		lbFindWord.setBounds(38, 42, 549, 36);
		contentPane.add(lbFindWord);
		
		SearchWord = new JTextField();
		SearchWord.setBorder(new LineBorder(new Color(171, 173, 179)));
		SearchWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SearchWord.setBounds(38, 95, 464, 31);
		contentPane.add(SearchWord);
		SearchWord.setColumns(10);
		
		
		
		JButton btnSearch = new JButton("Tra t\u1EEB");
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(512, 95, 75, 31);
		contentPane.add(btnSearch);
		
		JScrollPane scrollMeaningPane = new JScrollPane();
		scrollMeaningPane.setBorder(null);
		scrollMeaningPane.setBounds(36, 149, 549, 163);
		contentPane.add(scrollMeaningPane);
		
		JTextArea MeaningArea = new JTextArea();
		scrollMeaningPane.setViewportView(MeaningArea);
		MeaningArea.setEditable(false);
		MeaningArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		MeaningArea.setLineWrap(true);
		MeaningArea.setMargin(new Insets(10, 10, 10, 10));
		
		SearchWord.addInputMethodListener(new InputMethodListener() {
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				MeaningArea.setText("");
			}

			@Override
			public void caretPositionChanged(InputMethodEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//get the text field
				String word;
				word = SearchWord.getText().toString();
				Network network = new Network();
				try {
					DictWord found = network.searchWord(word);
					System.out.println(found.getIpapron()+"\n"+found.getMeaning());
					if(found.getSuccess() == "true") {
						MeaningArea.setText(found.getIpapron());
						MeaningArea.append("\n");
						MeaningArea.append(found.getMeaning());
					} 
					if (found.getSuccess() == "false") {
						MeaningArea.setText("Từ hiện chưa có trong từ điển.");
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		SearchWord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String word;
					word = SearchWord.getText().toString();
					Network network = new Network();
					try {
						DictWord found = network.searchWord(word);
						System.out.println(found.getIpapron()+"\n"+found.getMeaning());
						if(found.getSuccess() == "true") {
							MeaningArea.setText(found.getIpapron());
							MeaningArea.append("\n");
							MeaningArea.append(found.getMeaning());
						} 
						if (found.getSuccess() == "false") {
							MeaningArea.setText(" Từ hiện chưa có trong từ điển.\n");
						}
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		setUndecorated(true);
	}
}
