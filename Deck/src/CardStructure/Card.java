package CardStructure;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {

	
	public Image cImage;
	
	public static int cAmount;
	public Card[] lib = new Card[cAmount];

	public static int id;

	
	public Card(int mCost, Essence type, String img, int id){
		cImage = new ImageIcon(img).getImage().getScaledInstance(250, 350, 0);
		cAmount++;
		this.id = id;
	}
	
}
