package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;

import models.Villager;

public class View extends JFrame{

	private static final String TITTLE = "Animal Crossing Villagers";
	private static final String ICON_IMAGE = "/rsce/images/titleIcon.jpg";
	private static final Font MY_FONT = new Font("SansSerif", Font.PLAIN, 20);
	private static final String LABEL_FONT_PROP = "Label.font";
	private Header header;
	private Body body;
	
	
	public View(ArrayList<Villager> villagers) {
		super();
		UIManager.put(LABEL_FONT_PROP, MY_FONT);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource(ICON_IMAGE)));
		this.setTitle(TITTLE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(villagers);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents(ArrayList<Villager> villagers) {
		this.header = new Header();
		CustomScrollPane js = new CustomScrollPane();
		this.body = new Body(villagers);
		js.setPreferredSize(this.body.getPreferredSize());
		js.setViewportView(body);
		js.setBorder(null);
		this.add(js, BorderLayout.CENTER);
		this.add(this.header,BorderLayout.NORTH);
	}
	
}
