package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import models.Villager;

public class VillagerCard extends JPanel {

	private static final int STROKE_SEPARATOR = 8;

	private Villager villager;
	private VillagerImage villagerImage;
	private VillagerInfo villagerInfo;

	public VillagerCard(Villager villager) {
		super();
		this.setSize(new Dimension(250, 355));
		this.setPreferredSize(new Dimension(250, 355));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.villager = villager;
		initComponents();
	}

	private void initComponents() {
		this.villagerImage = new VillagerImage(villager.getImageUrl(), villager.getBubbleColor());
		this.add(villagerImage, BorderLayout.NORTH);

		this.villagerInfo = new VillagerInfo(this.villager);
		this.add(villagerInfo, BorderLayout.CENTER);
	}

}
