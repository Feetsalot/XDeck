package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Library {

	private Image Background, XDeck_Logo, Card_Library, Button, Highlight, Card;
	public boolean mousehover;
	
	public Library(){
	    Background = new ImageIcon("C:/Users/Administrator/Desktop/XDeck/cardback.png").getImage().getScaledInstance(1126, 726, 0);
	    Card = new ImageIcon("C:/Users/Administrator/Desktop/XDeck/servantofthefirelord.png").getImage().getScaledInstance(250, 350, 0);
	}
	
	public void mousehover(int mx, int my, int x, int width, int y, int height){
		if(mx > x && mx < x + width && my > y && my < y + height){
			mousehover =  true;
		} else {
			mousehover = false;
		}
	}
	
	
	public void draw(Graphics g){
		g.drawImage(Background, 0, 0, null);
		g.drawImage(Card, 500, 226, null);
	}
}
