package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.MyVillagerManager;
import views.View;
import views.WelcomePanel;
import views.WelcomeView;

public class Controller implements ActionListener{
	
	private View view;
	private WelcomeView welcome;
	private MyVillagerManager model;
	
	public Controller() {
		this.welcome = new WelcomeView(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case WelcomePanel.EVENT_ACEPT:
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					welcome.showLoadPanel();
					model = new MyVillagerManager(welcome.getQuantity());
					welcome.dispose();
					view = new View(model.getVillagers());
				}
			}).start();
			break;
		default:
			break;
		}
	}
}
