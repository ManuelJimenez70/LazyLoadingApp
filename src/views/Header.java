package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {

	private static final URL HEADER_ICON = View.class.getResource("/rsce/images/header.png");
	private JLabel txtheader;

	public Header() {
		super();
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(1000, 30));
		this.setBackground(Color.WHITE);
		initComponents();
	}

	private void initComponents() {
		this.txtheader = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(HEADER_ICON)));
		this.add(txtheader, BorderLayout.WEST);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#51AD81"));
		g.fillRect(0, getHeight() - 3, getWidth(), 3);
	}
}
