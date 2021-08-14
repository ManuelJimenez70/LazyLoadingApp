package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

public class WelcomePanel extends JPanel{
	
	public static final String EVENT_ACEPT = "acept";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BTN_TEXT = "Aceptar";
	private static final String WELCOME_TEXT = "¡Bienvenido! Porfavor escoge una cantidad de tarjetas para consultar";
	private static final URL WELCOME_ICON = View.class.getResource("/rsce/images/welcome.png");
	private static final URL LOADING_ICON = View.class.getResource("/rsce/images/spinner.gif");

	private JLabel welcomeIcon, welcometxt, quantity;
	private JSlider jSlider;
	private JButton btn;
	
	public WelcomePanel(ActionListener actionListener) {
		super();
		this.setLayout(new GridBagLayout());
		this.setSize(new Dimension(1000, 30));
		this.setBackground(Color.WHITE);
		initComponents(actionListener);
		addcomponents();
	}

	private void initComponents(ActionListener actionListener) {
		this.welcomeIcon = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(WELCOME_ICON)));
		this.welcometxt = new JLabel(WELCOME_TEXT);
		generateSlider();
		this.btn = new JButton(BTN_TEXT);
		this.btn.setFocusable(false);
		this.btn.setForeground(Color.WHITE);
		this.btn.setBackground(Color.decode("#51AD81"));
		this.btn.addActionListener(actionListener);
		this.btn.setActionCommand(EVENT_ACEPT);
	}
	
	public void showLoadPanel() {
		removeAll();
		this.welcomeIcon = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(WELCOME_ICON)));
		this.welcometxt = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(LOADING_ICON)));
		this.quantity = new JLabel("Cargando...");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets.set(5, 5, 5, 5);
		this.add(welcometxt, c);
		
		c.gridy = 1;
		this.add(quantity, c);		
		revalidate();
		repaint();
	}

	private void generateSlider() {
		this.jSlider = new JSlider(1, 391){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void updateUI() {
                setUI(new CustomSliderUI(this));
            }
        };
        setPropertiesToSlider();
		Hashtable<Integer, JLabel> position = setPositions();
		this.jSlider.setLabelTable(position); 
		setQuantity();
		
	}

	private void setQuantity() {
		this.quantity = new JLabel("Cantidad: " + String.valueOf(jSlider.getValue()));
		this.jSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				quantity.setText("Cantidad: " + String.valueOf(((JSlider)e.getSource()).getValue()));
			}
		});
	}
	
	public int getQuantity() {
		return this.jSlider.getValue();
	}

	private Hashtable<Integer, JLabel> setPositions() {
		Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		position.put(0, new JLabel("0"));
		position.put(50, new JLabel("50"));
		position.put(150, new JLabel("150"));
		position.put(250, new JLabel("250"));
		position.put(350, new JLabel("350"));
		return position;
	}

	private void setPropertiesToSlider() {
		this.jSlider.setPreferredSize(new Dimension(400,47));
		this.jSlider.setBackground(Color.WHITE);
		this.jSlider.setForeground(Color.decode("#51AD81"));
		this.jSlider.setMajorTickSpacing(50);
		this.jSlider.setMinorTickSpacing(10);
		this.jSlider.setPaintTicks(true);
		this.jSlider.setPaintLabels(true);
	}
	
	private void addcomponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets.set(5, 5, 15, 5);
		this.add(welcomeIcon, c);
		
		c.gridy = 1;
		this.add(welcometxt, c);
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 2;
		this.add(jSlider, c);
		c.fill = GridBagConstraints.NONE;

		c.gridy = 3;
		this.add(quantity, c);
		
		c.gridy = 4;
		this.add(btn, c);
	}
	
	private static class CustomSliderUI extends BasicSliderUI {

        private static final int TRACK_HEIGHT = 8;
        private static final int TRACK_WIDTH = 8;
        private static final int TRACK_ARC = 5;
        private static final Dimension THUMB_SIZE = new Dimension(20, 20);
        private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();

        public CustomSliderUI(final JSlider b) {
            super(b);
        }

        @Override
        protected void calculateTrackRect() {
            super.calculateTrackRect();
            if (isHorizontal()) {
                trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) / 2;
                trackRect.height = TRACK_HEIGHT;
            } else {
                trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
                trackRect.width = TRACK_WIDTH;
            }
            trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
        }

        @Override
        protected void calculateThumbLocation() {
            super.calculateThumbLocation();
            if (isHorizontal()) {
                thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
            } else {
                thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
            }
        }

        @Override
        protected Dimension getThumbSize() {
            return THUMB_SIZE;
        }

        private boolean isHorizontal() {
            return slider.getOrientation() == JSlider.HORIZONTAL;
        }

        @Override
        public void paint(final Graphics g, final JComponent c) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paint(g, c);
        }

        @Override
        public void paintTrack(final Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Shape clip = g2.getClip();

            boolean horizontal = isHorizontal();
            boolean inverted = slider.getInverted();

            // Paint shadow.
            g2.setColor(new Color(170, 170 ,170));
            g2.fill(trackShape);

            // Paint track background.
            g2.setColor(new Color(200, 200 ,200));
            g2.setClip(trackShape);
            trackShape.y += 1;
            g2.fill(trackShape);
            trackShape.y = trackRect.y;

            g2.setClip(clip);

            // Paint selected track.
            if (horizontal) {
                boolean ltr = slider.getComponentOrientation().isLeftToRight();
                if (ltr) inverted = !inverted;
                int thumbPos = thumbRect.x + thumbRect.width / 2;
                if (inverted) {
                    g2.clipRect(0, 0, thumbPos, slider.getHeight());
                } else {
                    g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
                }

            } else {
                int thumbPos = thumbRect.y + thumbRect.height / 2;
                if (inverted) {
                    g2.clipRect(0, 0, slider.getHeight(), thumbPos);
                } else {
                    g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
                }
            }
            g2.setColor(Color.decode("#51AD81"));
            g2.fill(trackShape);
            g2.setClip(clip);
        }

        @Override
        public void paintThumb(final Graphics g) {
            g.setColor(Color.decode("#217C51"));
            g.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        public void paintFocus(final Graphics g) {}
    }

}
