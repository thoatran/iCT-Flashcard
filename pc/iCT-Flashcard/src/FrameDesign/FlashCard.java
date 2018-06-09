package FrameDesign;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FlashCard {
	private JPanel FlashCard=new JPanel();

	public JPanel Card(ImageIcon icon1) {
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
        Eng_sound.setPreferredSize(new Dimension(60,30));
        sound.add(Eng_sound);
        
        sound.add(Box.createRigidArea(new Dimension(2,0)));

        JButton Vie_sound = new JButton();
        Vie_sound.setPreferredSize(new Dimension(60,30));
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
        Flip.setPreferredSize(new Dimension(180,30));
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