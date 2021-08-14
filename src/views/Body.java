package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Villager;

public class Body extends JPanel{
	
	private static final int CARD_SIZE = 360;

	public Body(ArrayList<Villager> villagers) {
		this.setLayout(new FlowLayout());
		double size = villagers.size();
		double m = Math.ceil(size/6)*CARD_SIZE;
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,(int)m));
		setBackground(Color.WHITE);
		initComponents(villagers);
	}

	private void initComponents(ArrayList<Villager> villagers) {
		for (Villager villager : villagers) {
			VillagerCard villagerCard = new VillagerCard(villager);
			add(villagerCard);
		}
		this.revalidate();
		double size = villagers.size();
		double m = Math.ceil(size/6)*CARD_SIZE;
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,(int) m));
	}
	
}
