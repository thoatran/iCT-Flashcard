package FrameDesign;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class QuanLi {
	public  JPanel QuanLi=new JPanel();
	private BoxLayout layout=new BoxLayout(QuanLi, BoxLayout.LINE_AXIS);
	
	public void createQuanLiPanel(CardList listObject) {
		QuanLi.setLayout(layout);
		QuanLi.add(listObject.getDescribe());
		QuanLi.add(Box.createRigidArea(new Dimension(20,0)));
		QuanLi.add(listObject.getCardList());
        QuanLi.setPreferredSize(new Dimension(850,400));
	}
	public JPanel getQuanLi() {
		return QuanLi;
	}

}
