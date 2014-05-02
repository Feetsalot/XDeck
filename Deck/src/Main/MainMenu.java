package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MainMenu {

	private Image Background, XDeck_Logo, Card_Library, Button, Highlight;
	public boolean mousehover;
	
	public MainMenu(){
	    Background = new ImageIcon("C:/Users/Administrator/Desktop/XDeck/cardback.png").getImage().getScaledInstance(1126, 726, 0);
	    XDeck_Logo = new ImageIcon("C:/Users/Administrator/Desktop/XDeck/XDeck_Logo.png").getImage();
	    Button= new ImageIcon("C:/Users/Administrator/Desktop/XDeck/Button_Template.png").getImage();
	    Card_Library = new ImageIcon("C:/Users/Administrator/Desktop/XDeck/library.png").getImage();
	    Highlight = new ImageIcon("C:/Users/Administrator/Desktop/XDeck/highlight.png").getImage().getScaledInstance(275, 75, 0);;
	}
	
	public void mousehover(int mx, int my, int x, int width, int y, int height){
		if(mx > x && mx < x + width && my > y && my < y + height){
			System.out.println("HI");
			mousehover =  true;
		} else {
			mousehover = false;
		}
	}
	
	
	public void draw(Graphics g){
		g.drawImage(Background, 0, 0, null);
		g.drawImage(XDeck_Logo, 400, 50, null);
		g.drawImage(Button, 425, 200, null);
		g.drawImage(Card_Library, 352, 200, null);
		if(mousehover){
			g.drawImage(Highlight, 425, 200, null);
		}
	}
}
