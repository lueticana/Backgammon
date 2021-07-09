package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	
	public Platno() {
		setBackground(Color.PINK);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(700, 400);
	}

	
	// Relativna širina črte
	private final static double LINE_WIDTH = 0.05;
	
	// Širina enega kvadratka
	private double skatlice() {
		return getWidth() / 10;
	}
	
	private double rob() {
		return getWidth() / 28;
	}
	
	private double spice() {
		return (getWidth() - skatlice() - 4 * rob()) / 12;
	}
	
	
	
	
	// Relativni prostor okoli X in O
	private final static double PADDING = 0.18;
	
	
	private void paintBela(Graphics2D g2, int i) {
		double zamik;
		if (i < 12) {}
		double w = spice();
		double d = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // premer O
		double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
		double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
		g2.drawOval((int)x, (int)y, (int)d , (int)d);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		double skatlice = skatlice();
		double rob = rob();
		double spice = spice();
		
		g2.setColor(Color.WHITE);
		int sirina =(int)((getWidth() - skatlice - 4 * rob) /2);
		g2.fillRect((int)rob, (int)rob, (int)sirina, (int)(getHeight() - 2* rob));
		g2.fillRect((int)(3*rob + sirina), (int)rob, (int)sirina, (int)(getHeight() - 2* rob));
		
		g2.setColor(Color.GREEN);
		g2.fillRect((int)(getWidth() - skatlice), (int)rob, (int)(skatlice - rob), (int)(getHeight() / 2 - 2 * rob));
		g2.fillRect((int)(getWidth() - skatlice), (int)(rob + getHeight() /2 ), (int)(skatlice - rob), (int)((getHeight() - 4 * rob) /2 ));
		
		boolean modra = false;
		for (int i = 0;i < 12;i++) {
			double zamik;
			if (i < 6) {zamik = 0;} else {zamik = 2 * rob;}
			if (modra) {g2.setColor(Color.BLUE);} else {g2.setColor(Color.GREEN);}
			g2.fillPolygon(new int[] {(int)(rob + i * spice + zamik), (int)(rob + (i + 1) * spice + zamik), (int)(rob + i * spice + spice/2 + zamik)}, 
					new int[] {(int)rob, (int)rob, (int)(getHeight()/2 - rob)}, 3);
			if (modra) {g2.setColor(Color.BLUE);} else {g2.setColor(Color.GREEN);}
			modra = ! modra;
			g2.fillPolygon(new int[] {(int)(rob + i * spice + zamik), (int)(rob + (i + 1) * spice + zamik), (int)(rob + i * spice + spice/2 + zamik)}, 
					new int[] {(int)(getHeight() -rob), (int)(getHeight() -rob), (int)(getHeight()/2 + rob)}, 3);
		
		}
	}

}










