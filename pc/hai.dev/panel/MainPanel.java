package demo.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import demo.flashcard.CardList;

public class MainPanel {
	public JPanel container=new JPanel();
	   public CardLayout cards=new CardLayout();

	   public MainPanel(CardList object){
	     container.setLayout(cards);
	     container.setPreferredSize(new Dimension(800,300));
	     Dashboard Dashboard=new Dashboard();
	     Dashboard.createDashboard(object);
	     container.add(Dashboard.Dashboard,"Dashboard");
	     QuanLi QuanLi=new QuanLi();
	     /*QuanLi.createQuanLiPanel(CardList.CardListArray.getFirst());*/
	     container.add(QuanLi.QuanLi,"QuanLi");
	     ThemNhanh ThemNhanh=new ThemNhanh();
	     ThemNhanh.createThemNhanh();
	     container.add(ThemNhanh.ThemNhanh,"Them Nhanh FlashCard");
	     container.setVisible(true);
	     cards.show(container, "Dashboard");
	     
	     /*butt.addActionListener(
	                new ActionListener() {
	                    public void actionPerformed(ActionEvent actionEvent) {
	                        cards.show(container, "index");

	                    }
	                }
	        );*/
	}

}
