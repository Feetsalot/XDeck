package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import CardLibrary.WaterAffinity.AzalWhelp;
import CardStructure.Card;

public class Library {

	public boolean mousehover;
	public Card card;
	
	public Library(){
		for(int i = 1; i < Card.cAmount; i++){
			if(Card.id == 1){
				System.out.println("HI");
			}
		}
	}
	
	public void mousehover(int mx, int my, int x, int width, int y, int height){
		if(mx > x && mx < x + width && my > y && my < y + height){
			mousehover =  true;
		} else {
			mousehover = false;
		}
	}
	
	
	public void draw(Graphics g){
		g.drawImage(GamePanel.Background, 0, 0, null);
	//	g.drawImage(card.cImage, 500, 226, null);
	}
}
