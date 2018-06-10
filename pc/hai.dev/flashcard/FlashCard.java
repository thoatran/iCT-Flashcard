package demo.flashcard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;

import demo.media.Sound;
import sun.applet.Main;

public class FlashCard {
	private JPanel FlashCard=new JPanel();
	private Sound Engsound = new Sound();
	private Sound Viesound = new Sound();

	public JPanel Card(ImageIcon icon1,String Engpath,String Viepath) {
		FlashCard.setPreferredSize(new Dimension(200,250));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        FlashCard.setLayout(layout);
        constraints.insets = new Insets(2,2,2,2);
        FlashCard.setBorder(new LineBorder(Color.GRAY, 1, true));
        FlashCard.setBackground(Color.WHITE);

        Image newimg =icon1.getImage();
        Image img =newimg.getScaledInstance(180,120,Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(img);
        JLabel lab1= new JLabel("mouse",icon1,JLabel.CENTER);
        lab1.setFont(new Font("Calibri", Font.BOLD, 30));
        lab1.setHorizontalTextPosition(JLabel.CENTER);
        lab1.setHorizontalAlignment(SwingConstants.LEFT);
        lab1.setVerticalTextPosition(JLabel.BOTTOM);

        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridheight=6;
        constraints.gridwidth=1;
        layout.setConstraints(lab1,constraints);
        FlashCard.add(lab1);

        JPanel sound = new JPanel();
        sound.setLayout(new GridLayout(1, 2));
        sound.setBackground(Color.WHITE);

        JButton Eng_sound = new JButton();
        Eng_sound.setBackground(Color.WHITE);
        Eng_sound.setForeground(Color.BLACK);
        Eng_sound.setPreferredSize(new Dimension(60,30));
        Eng_sound.setBorder(new LineBorder(Color.GRAY, 1, true));
        
        Engsound.SetSound(Engpath);
        Eng_sound.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Engsound.play();
				
			}
		});        
        sound.add(Eng_sound);
        
        sound.add(Box.createRigidArea(new Dimension(2,0)));

        JButton Vie_sound = new JButton();
        Vie_sound.setBackground(Color.WHITE);
        Vie_sound.setForeground(Color.BLACK);
        Vie_sound.setPreferredSize(new Dimension(60,30));
        Vie_sound.setBorder(new LineBorder(Color.GRAY, 1, true));
        
        Viesound.SetSound(Viepath);
        Eng_sound.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Viesound.play();
				
			}
		});        
        sound.add(Vie_sound);

        constraints.gridy=7;
        constraints.gridheight=4;
        layout.setConstraints(sound,constraints);
        FlashCard.add(sound);
        
        JSeparator separator=new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(180,5));
        constraints.gridy=11;
        constraints.gridheight=1;
        layout.setConstraints(separator, constraints);
        FlashCard.add(separator);

        JButton Flip = new JButton("m„Út sau");
        Flip.setBackground(Color.CYAN);
        Flip.setForeground(Color.WHITE);
        Flip.setPreferredSize(new Dimension(180,30));
        Flip.setBorder(new LineBorder(Color.GRAY,1,true));
        constraints.gridy=22;
        constraints.gridheight=1;
        layout.setConstraints(Flip,constraints);
        FlashCard.add(Flip);

        return FlashCard;
	}
	public JPanel getCard() {
		return FlashCard;
	}

}

