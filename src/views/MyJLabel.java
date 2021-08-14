package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class MyJLabel extends JLabel{

	private static final int ARC_SIZE = 10;
	private static final String HTML = "<html>";
	private String bubbleColor;

	public MyJLabel(String message, String bubbleColor, String textColor) {
		super();
		setPreferredSize(new Dimension(195,50));
		setText(HTML+ message + HTML);
		setForeground(Color.decode(textColor));
		Border margin = new EmptyBorder(10,10,10,10);
		setBorder(new CompoundBorder(getBorder(), margin));
		this.bubbleColor = bubbleColor;
		this.setFont(new Font("SansSerif", Font.BOLD,12));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.decode(bubbleColor));
		g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_SIZE, ARC_SIZE);
		super.paintComponent(g);
	}
	
}
