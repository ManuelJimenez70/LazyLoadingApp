package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollPane extends JScrollPane {
	
	private static final int SIZE = 5;
	private static final Color COLOR_SCROLL_ON = Color.decode("#51AD81");
	private static final Color COLOR_SCROLL_OFF = Color.decode("#217C51");

	public CustomScrollPane() {
		Image imageThumb = createImage(SIZE, SIZE, COLOR_SCROLL_OFF);
		Image imageThumbPressed = createImage(SIZE, SIZE, COLOR_SCROLL_ON);
		Image imageTrack = createImage(SIZE, SIZE, Color.WHITE);
		
		setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL, imageThumb, imageThumbPressed, imageTrack));
		setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL, imageThumb, imageThumbPressed, imageTrack));
	}

	private Image createImage(int width, int height, Color color) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
		return image;
	}
}

	class CustomScrollBar extends JScrollBar {
	private boolean isThumbPressed;

	public CustomScrollBar(final int orientation, final Image thumb, final Image thumbPressed, final Image track) {
		super(orientation);
		changeColor();
		drawScroll(thumb, thumbPressed, track);
	}

	private void drawScroll(final Image thumb, final Image thumbPressed, final Image track) {
		setUI(new BasicScrollBarUI() {
			@Override
			protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
				if (isThumbPressed)
					g.drawImage(thumbPressed, r.x, r.y, r.width, r.height, null);
				else
					g.drawImage(thumb, r.x, r.y, r.width, r.height, null);
			}

			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
				g.drawImage(track, r.x, r.y, r.width, r.height, null);
			}
		});
	}

	private void changeColor() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isThumbPressed = true;
			}

			public void mouseReleased(MouseEvent e) {
				isThumbPressed = false;
			}
		});
	}
}
