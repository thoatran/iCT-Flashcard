package demo.panel;

import javax.swing.JPanel;

import demo.flashcard.CardList;

public class Dashboard {
	public JPanel Dashboard=new JPanel();
	
	public void createDashboard(CardList cardlist) {
		Dashboard.add(cardlist.getCardList());
	}
	
	public JPanel getDashboard() {
		return Dashboard;
	}

}
