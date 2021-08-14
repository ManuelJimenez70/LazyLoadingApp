package views;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;

import models.Villager;

public class WelcomeView extends JFrame{
	private static final String TITTLE = "Animal Crossing Villagers";
	private static final String ICON_IMAGE = "/rsce/images/titleIcon.jpg";
	
	private WelcomePanel welcome;
	
	public WelcomeView(ActionListener actionListener) {
		super();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource(ICON_IMAGE)));
		this.setTitle(TITTLE);
		this.setSize(500,330);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(actionListener);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		this.welcome = new WelcomePanel(actionListener);
		add(welcome);
	}
	
	public int getQuantity() {
		return welcome.getQuantity();
	}

	public void showLoadPanel() {
		welcome.showLoadPanel();
	}
}
