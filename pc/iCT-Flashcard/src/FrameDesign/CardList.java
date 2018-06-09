package FrameDesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CardList {
	public JPanel CardListPanel = new JPanel();
	private LinkedList<FlashCard> FlashCardArray=new LinkedList<FlashCard>();
	public static final LinkedList<CardList> CardListArray=new LinkedList<CardList>();
	private JPanel Describe =new JPanel();
	private BoxLayout layout=new BoxLayout(CardListPanel, BoxLayout.LINE_AXIS);
	public JScrollPane CardListScroll =new JScrollPane();
	
	public CardList() {
		CardListPanel.setLayout(layout);
        CardListScroll.setPreferredSize(new Dimension(800,280));
		/*CardListArray.add(this);*/
	}
	
	public CardList(ImageIcon icon,String Name,String description) {
		Describe.setLayout(new BoxLayout(Describe, BoxLayout.PAGE_AXIS));
		Describe.setPreferredSize(new Dimension(200,300));
		Describe.setBackground(Color.WHITE);
		
        Image newimg =icon.getImage();
        Image img =newimg.getScaledInstance(140,140,Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel iconlabel= new JLabel(Name,icon,JLabel.CENTER);
        iconlabel.setFont(new Font("Calibri", Font.BOLD, 30));
        iconlabel.setHorizontalTextPosition(JLabel.CENTER);
        iconlabel.setHorizontalAlignment(SwingConstants.LEFT);
        iconlabel.setVerticalTextPosition(JLabel.BOTTOM);
        
        JLabel descriptionlabel= new JLabel(description, JLabel.LEFT);
        
        Describe.add(iconlabel);
        Describe.add(descriptionlabel);
        
        CardListArray.add(this);
        
	}
	public void AddCardList(CardList CardList) {
		CardListArray.add(CardList);
	}
	public void FlashCardList(FlashCard card){       
		FlashCardArray.add(card);
        CardListPanel.add(card.getCard());
        CardListPanel.add(Box.createRigidArea(new Dimension (10,0)));
        /*CardListPanel.setVisible(true);*/

    }
	public JScrollPane getCardList() {
		CardListScroll.setViewportView(CardListPanel);
		
        JScrollBar horizonscroll=new JScrollBar(JScrollBar.HORIZONTAL);
        horizonscroll.setMaximum(10);
        horizonscroll.setMinimum(1);
		
		CardListScroll.setHorizontalScrollBar(horizonscroll);
        CardListScroll.setPreferredSize(new Dimension(800,280));

        CardListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        CardListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        /*CardListScroll.setVisible(true);*/
		
        return CardListScroll;
	}
	public JPanel getDescribe() {
		return Describe;
	}

}
