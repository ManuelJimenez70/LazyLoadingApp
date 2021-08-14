package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class VillagerImage extends JPanel {

	private static final URL LOADER_SPINNER = View.class.getResource("/rsce/images/spinner.gif");
	private Image image;
	private String color;
	private static final int INITIAL_X_POS = 15;
	private static final int STROKE = 5;
	private static final int STROKE_SEPARATOR = 20;

	private boolean isLoaded;
	private int time;

	public VillagerImage(String imageurl, String color) {
		this.image = new ImageIcon(LOADER_SPINNER).getImage();
		this.color = color;
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(250, 150));
		this.setPreferredSize(new Dimension(230, 150));
		this.setBackground(Color.WHITE);
		loadImage(imageurl);
	}

	private void loadImage(String imageurl) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (!isLoaded) {
						Thread.sleep(1000);
						checkLoad();
					}
					image = new ImageIcon(new URL(imageurl)).getImage();
					repaint();
				} catch (MalformedURLException | InterruptedException e) {
					e.printStackTrace();
				}
				;
			}
		}).start();
	}

	private void checkLoad() {
		time++;
		if (time > 6) {
			isLoaded = true;
		}
	}

	@Override
	public void paint(Graphics r) {
		super.paint(r);
		Graphics2D g2 = (Graphics2D) r;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.decode(color));
		g2.fillRect(0, 0, getWidth(), STROKE);
		g2.drawImage(this.image, 0, INITIAL_X_POS, getPreferredSize().width,
				getPreferredSize().height - INITIAL_X_POS, this);
		g2.setColor(Color.WHITE);
		g2.fillRect(getWidth() - STROKE_SEPARATOR, 0, STROKE_SEPARATOR, getHeight());
	}
}
