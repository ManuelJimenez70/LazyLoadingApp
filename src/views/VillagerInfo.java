package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Villager;

public class VillagerInfo extends JPanel{//nombre, especie, genero, cumpleaños
	
	private static final String BIRTHDAY = "Birthday: ";
	private static final String GENDER = "Gender: ";
	private static final String SPECIES = "Species: ";

	private JLabel name, species, gender, birthday ;
	private MyJLabel message;
	
	public VillagerInfo(Villager villager) {
		this.setLayout(new GridBagLayout());
		this.setSize(new Dimension(250, 205));
		this.setPreferredSize(new Dimension(250, 205));
		this.setBackground(Color.WHITE);
		initComponents(villager);
	}

	private void initComponents(Villager villager) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets.set(5, 5, 5, 5);
		name = new JLabel(villager.getName());
		add(name, c);
		
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		species = new JLabel(SPECIES + villager.getSpecies());
		add(species,c);
		
		c.gridy = 2;
		gender = new JLabel(GENDER + villager.getGender());
		add(gender,c);

		c.gridy = 3;
		birthday = new JLabel(BIRTHDAY + villager.getBirthday());
		add(birthday,c);
		
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		message = new MyJLabel(villager.getMessage(), villager.getBubbleColor(), villager.getTextColor());
		add(message,c);
	}



}
