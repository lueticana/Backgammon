package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import splosno.Vodja;
import splosno.PoljeInZetoni;
import splosno.Poteza;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener{
	
	public Platno() {
		
		// Nastavimo barvo ozadja
		setBackground(new Color(153, 77, 0));
		
		// Dodamo MouseListener, da lahko sledimo klikom
		this.addMouseListener(this);
	}
	
	// Dimenzije platna
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
	
	// Relativni prostor okoli žetonov
	private final static double PADDING = 0.18;
	
	// Nariše bele žetone
	private void paintBela(Graphics2D g2, int polje, int stZetonov) {
		double y;
		double x = 0;
		double spice = spice();
		double premer = spice/2;
		double rob = rob();
		double skatlice = skatlice();
		
		if (polje < 13) { 
			y = getHeight() - rob - premer * 1.25 - (0.5 * LINE_WIDTH + PADDING);
		}
		else {
			y = rob + premer / 4 + 0.5 * LINE_WIDTH + PADDING;
		}
		if (polje == 25) {
			x = getWidth() - rob - skatlice/2 - (0.5 * LINE_WIDTH + PADDING);
		}
		else if (polje == 26) {
			x = 2 *rob + 6 * spice - premer/2 - (0.5 * LINE_WIDTH + PADDING);
			y = 3 * getHeight() / 4 - premer/2;
		}
		else if (polje > 6 && polje < 13) {
			x = rob + (12 - polje) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje > 12 && polje < 19) {
			x = rob + (polje - 13) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje < 7 && polje > 0) {
			x = 3 * rob + (12 - polje) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje > 18 && polje < 25) {
			x = 3 * rob + (polje - 13) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING);
		}
		// Nariše žeton
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke((float) (spice * LINE_WIDTH)));
		g2.fillOval((int)x, (int)y, (int)premer , (int)premer);
		// Izpiše število žetonov na določenem polju
		g2.setColor(Color.BLACK);
		if (stZetonov < 10) {
			g2.drawString(Integer.toString(stZetonov), (int)(x + premer/2 - 4), (int)(y + premer/2 + 4));
		}
		else {
			g2.drawString(Integer.toString(stZetonov), (int)(x + premer/2 - 8), (int)(y + premer/2 + 4));
		}
	}
	
	// Nariše črne žetone
	private void paintCrna(Graphics2D g2, int polje, int stZetonov) {
		double y;
		double x = 0;
		double spice = spice();
		double premer = spice/2;
		double rob = rob();
		double skatlice = skatlice();
		
		if (polje < 12) { 
			y = getHeight() - rob - premer * 1.25 - (0.5 * LINE_WIDTH + PADDING);
		}
		else {
			y = rob + premer / 4 + 0.5 * LINE_WIDTH + PADDING;
		}
		if (polje == 0) {
			x = getWidth() - rob - skatlice/2 - (0.5 * LINE_WIDTH + PADDING);
			//y -= premer * 2.25;
			
		}
		else if (polje == 27) {
			x = 2 *rob + 6 * spice - premer/2 - (0.5 * LINE_WIDTH + PADDING);
			y = getHeight() / 4 - premer/2;
		}
		else if (polje > 6 && polje < 13) {
			x = rob + (12 - polje) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje > 12 && polje < 19) {
			x = rob + (polje - 13) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje < 7 && polje > 0) {
			x = 3 * rob + (12 - polje) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje > 18 && polje < 25) {
			x = 3 * rob + (polje - 13) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING);
		}
		
		// Nariše žetone
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke((float) (spice * LINE_WIDTH)));
		g2.fillOval((int)x, (int)y, (int)premer , (int)premer);
		// Napiše število žetonov na določenem polju
		g2.setColor(Color.WHITE);
		if (stZetonov < 10) {
			g2.drawString(Integer.toString(stZetonov), (int)(x + premer/2 - 4), (int)(y + premer/2 + 4));
		}
		else {
			g2.drawString(Integer.toString(stZetonov), (int)(x + premer/2 - 8), (int)(y + premer/2 + 4));
		}
	}
	
	// Obkroži žeton, na katerega kliknemo
	private void round(Graphics2D g2, int polje) {
		double y;
		double x = 0;
		double spice = spice();
		double premer = spice/2;
		double rob = rob();
		double skatlice = skatlice();
		
		if (polje < 13) { 
			y = getHeight() - rob - premer * 1.25 - (0.5 * LINE_WIDTH + PADDING);
		}
		else {
			y = rob + premer / 4 + 0.5 * LINE_WIDTH + PADDING;
		}
		if (polje == 25) {
			x = getWidth() - rob - skatlice/2 - (0.5 * LINE_WIDTH + PADDING);
		}
		else if (polje == 26) {
			x = 2 *rob + 6 * spice - premer/2 - (0.5 * LINE_WIDTH + PADDING);
			y = 3 * getHeight() / 4 - premer/2;
		}
		else if (polje == 27) {
			x = 2 *rob + 6 * spice - premer/2 - (0.5 * LINE_WIDTH + PADDING);
			y = getHeight() / 4 - premer/2;
		}
		else if (polje > 6 && polje < 13) {
			x = rob + (12 - polje) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje > 12 && polje < 19) {
			x = rob + (polje - 13) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje < 7 && polje > 0) {
			x = 3 * rob + (12 - polje) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING); 
		}
		else if (polje > 18 && polje < 25) {
			x = 3 * rob + (polje - 13) * spice + spice/2 - premer/2 - (0.5 * LINE_WIDTH + PADDING);
		}
		if (polje == 0) {
			x = getWidth() - rob - skatlice/2 - (0.5 * LINE_WIDTH + PADDING);	
		}
		g2.setColor(Color.YELLOW);
		g2.setStroke(new BasicStroke((float) (spice * LINE_WIDTH)));
		g2.drawOval((int)x, (int)y, (int)premer , (int)premer);
	}
	
	// Nariše kocke
	public void paintKocke(Graphics2D g2, int prva, int druga) {
		double spice = spice();
		double rob = rob();
		double premer =  2 * spice / 3;
		double y = getHeight() / 2 - premer / 2;
		double x1 = 3 * rob + 7 * spice;
		double x2 = x1 + 2 * premer;
		
		g2.setColor(Color.RED);
		g2.fillRoundRect((int)x1, (int)y, (int)premer, (int)premer, (int)premer/4, (int)premer/4);
		g2.fillRoundRect((int)x2, (int)y, (int)premer, (int)premer, (int)premer/4, (int)premer/4);
		paintPike(g2, prva, x1, y, premer);
		paintPike(g2, druga, x2, y, premer);
		if (prva == druga) {
			g2.setColor(Color.RED);
			g2.fillRoundRect((int)(x2 + 2*premer), (int)y, (int)premer, (int)premer, (int)premer/4, (int)premer/4);
			g2.fillRoundRect((int)(x2 + 4*premer), (int)y, (int)premer, (int)premer, (int)premer/4, (int)premer/4);
			paintPike(g2, prva, x2 + 2*premer, y, premer);
			paintPike(g2, prva, x2 + 4*premer, y, premer);
		}
	}
	
	// Nariše pike na kocke
	public void paintPike(Graphics2D g2, int stPik, double x, double y, double premer) {
		double polmerPike = premer / 10;
		g2.setColor(Color.WHITE);
		
		switch (stPik) {
		case 1: g2.fillOval((int)(x + premer/2 - polmerPike), (int)(y + premer/2 - polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			break;
			
		case 2: 
			g2.fillOval((int)(x + polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			break;

		case 3:
			g2.fillOval((int)(x + polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + 4 * polmerPike), (int)(y + 4 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			break;

		case 4:
			g2.fillOval((int)(x + polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			break;

		case 5:
			g2.fillOval((int)(x + polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + 4 * polmerPike), (int)(y + 4 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			break;

		case 6:
			g2.fillOval((int)(x + polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + premer - 3 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + polmerPike), (int)(y + 4 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);
			g2.fillOval((int)(x + premer - 3 * polmerPike), (int)(y + 4 * polmerPike), (int)polmerPike * 2, (int)polmerPike * 2);	
			break;
		}
	}
	
	// S pomočjo ostalih funkcij izriše vse objekte na platno
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		double skatlice = skatlice();
		double rob = rob();
		double spice = spice();
		
		// Nariše ozadje (pravokotniki)
		g2.setColor(new Color(204, 136, 0));
		int sirina =(int)((getWidth() - skatlice - 4 * rob) /2);
		g2.fillRect((int)rob, (int)rob, (int)sirina, (int)(getHeight() - 2* rob));
		g2.fillRect((int)(3*rob + sirina), (int)rob, (int)sirina, (int)(getHeight() - 2* rob));
		
		g2.setColor(new Color(204, 136, 0));
		g2.fillRect((int)(getWidth() - skatlice), (int)rob, (int)(skatlice - rob), (int)(getHeight() / 2 - 2 * rob));
		g2.fillRect((int)(getWidth() - skatlice), (int)(rob + getHeight() /2 ), (int)(skatlice - rob), (int)((getHeight() - 4 * rob) /2 ));
		
		// Nariše špice kot polja
		boolean modra = false;
		for (int i = 0;i < 12;i++) {
			double zamik;
			if (i < 6) {zamik = 0;} else {zamik = 2 * rob;}
			if (modra) {g2.setColor(new Color(255, 191, 0));} else {g2.setColor(new Color(128, 64, 0));}
			g2.fillPolygon(new int[] {(int)(rob + i * spice + zamik), (int)(rob + (i + 1) * spice + zamik), (int)(rob + i * spice + spice/2 + zamik)}, 
					new int[] {(int)rob, (int)rob, (int)(getHeight()/2 - rob)}, 3);
			if (modra) {g2.setColor(new Color(128, 64, 0));} else {g2.setColor(new Color(255, 191, 0));}
			modra = ! modra;
			g2.fillPolygon(new int[] {(int)(rob + i * spice + zamik), (int)(rob + (i + 1) * spice + zamik), (int)(rob + i * spice + spice/2 + zamik)}, 
					new int[] {(int)(getHeight() -rob), (int)(getHeight() -rob), (int)(getHeight()/2 + rob)}, 3);
		}
			
		// Nariše žetone
		PoljeInZetoni[] plosca = null;
		if (Vodja.igra != null) {
			plosca = Vodja.igra.getPlosca();

			for (int i = 0; i < 28; i++) {
				// Če je število žetonov na polju enako 0, jih ne nariše
				if (plosca[i].steviloZetonov == 0) {continue;}
				switch(plosca[i].polje) {
				case Bela: 
					paintBela(g2, i, plosca[i].steviloZetonov); 
					break;
				
				case Crna: 
					paintCrna(g2, i, plosca[i].steviloZetonov);
					break;
					
				default:
					break;
				}
			}
		}
		
		// Obkroži žeton na katerega kliknemo
		if (klik > -1) {round(g2, klik);}
		
		// Nariše kocke
		if (Vodja.igra != null) {
			paintKocke(g2, Vodja.prviMet, Vodja.drugiMet);
		}
	}
	
	// Pomožni konstanti za delovanje metode mouseClicked,
	// da lahko posledice metode uporabimo tudi v drugih funkcijah
	private int zacetnoPolje = -1;
	private int klik = -10;
	
	// Odziv ob kliku z miško - odigra človekovo potezo
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (Vodja.clovekNaVrsti) {
			int x = e.getX();
			int y = e.getY();
			double rob = rob();
			double spice = spice();
			double skatlice = skatlice();
			int polje = -1;
			
			if (y > rob && y < getHeight()/2 - rob) {
				if (x > rob && x < rob + 6 * spice) {
					polje = (int)((x - rob) / spice) + 13;
				}
				if (x > 3 * rob + 6 * spice && x < 3 * rob + 12 * spice) {
					polje = (int)((x - 3 * rob) / spice) + 13;
				}
				if (x > rob + 6 * spice && x < 3 * rob + 6 * spice) {
					polje = 27;
				}
				if (x > 4 * rob + 12 * spice && x < 4 * rob + 12 * spice + skatlice) {
					polje = 25;
				}
			}
			else if (y > getHeight()/2 + rob && y < getHeight() - rob) {
				if (x > rob && x < rob + 6 * spice) {
					polje = 12 - (int)((x - rob) / spice);
				}
				if (x > 3 * rob + 6 * spice && x < 3 * rob + 12 * spice) {
					polje = 12 - (int)((x - 3 * rob) / spice);
				}
				if (x > rob + 6 * spice && x < 3 * rob + 6 * spice) {
					polje = 26;
				}
				if (x > 4 * rob + 12 * spice && x < 4 * rob + 12 * spice + skatlice) {
					polje = 0;
				}
			}
			
			klik = polje;
			if (polje != -1) {
				if (zacetnoPolje == -1) {
					zacetnoPolje = polje;
				}
				else {
					Vodja.igrajClovekovoPotezo(new Poteza(zacetnoPolje, polje));
					zacetnoPolje = -1;
				}
			}
		}
		repaint();
	}

	// Metode, ki jih ne uporabimo
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
