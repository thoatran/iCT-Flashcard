package demo.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import demo.flashcard.CardList;

public class ThemNhanh {
	public JPanel ThemNhanh=new JPanel();
	public JPanel ThemNhanhPanel=new JPanel();
	public JScrollPane ThemNhanhScroll=new JScrollPane();
	
	public void createThemNhanh() {
		ThemNhanhPanel.setPreferredSize(new Dimension(800,280));
		ThemNhanhPanel.setLayout(new BoxLayout(ThemNhanhPanel,BoxLayout.LINE_AXIS));
		ThemNhanh.setPreferredSize(new Dimension(850,400));
		ThemNhanh.setBackground(Color.WHITE);
		ThemNhanh.setLayout(new BoxLayout(ThemNhanh,BoxLayout.PAGE_AXIS));
		for (CardList element: CardList.CardListArray) {
			JPanel card=new JPanel();
			card.setPreferredSize(new Dimension(100,300));
			card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
			card.setBorder(new LineBorder(Color.GRAY, 1, true));
			
			JPanel card1=element.getDescribe();
			card1.setAlignmentX(Component.LEFT_ALIGNMENT);
			card.add(card1);
			
			JPanel card2=new JPanel();
			card2.setPreferredSize(new Dimension(100,20));
			card2.setLayout(new BoxLayout(card2, BoxLayout.LINE_AXIS));
			card2.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			JButton Xem=new JButton("Xem");
			card2.add(Xem);
			
			JButton On_tap=new JButton("On Tap");
			card2.add(On_tap);
			
			JPanel card3=new JPanel();
			card3.setPreferredSize(new Dimension(100,20));
			card3.setLayout(new BoxLayout(card3,BoxLayout.LINE_AXIS));
			card3.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			JButton Chinh_sua=new JButton("Chinh sua");
			card3.add(Chinh_sua);
			
			JButton Xoa=new JButton("Xoa");
			card3.add(Xoa);
			
			card.add(card2);
			card.add(card3);
			ThemNhanhPanel.add(card);
			ThemNhanhPanel.add(Box.createRigidArea(new Dimension(10,0)));
		}
		ThemNhanhScroll.setViewportView(ThemNhanhPanel);;
		
        JScrollBar horizonscroll=new JScrollBar(JScrollBar.HORIZONTAL);
        horizonscroll.setMaximum(10);
        horizonscroll.setMinimum(1);
		
		ThemNhanhScroll.setHorizontalScrollBar(horizonscroll);
        ThemNhanhScroll.setPreferredSize(new Dimension(800,320));

        ThemNhanhScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ThemNhanhScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        ThemNhanhScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel header=new JLabel("cac bo falshcard");
        header.setPreferredSize(new Dimension(800,20));
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        ThemNhanh.add(header);
        
        JSeparator separator=new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(180,5));
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        ThemNhanh.add(separator);
        
        JButton Tao_moi=new JButton("Tao moi");
        Tao_moi.setAlignmentX(Component.LEFT_ALIGNMENT);
        ThemNhanh.add(Tao_moi);
        
        ThemNhanh.add(ThemNhanhScroll);
        ThemNhanh.setVisible(true);
	}
	
	public JScrollPane getThemNhanh() {
		return ThemNhanhScroll;
	}
}
