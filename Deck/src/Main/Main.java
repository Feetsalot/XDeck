package Main;

import javax.swing.JFrame;

public class Main extends JFrame {
    
    GamePanel gp;
    
    public Main(){
    	int xSize = 1126;
    	int ySize = 726;
        gp = new GamePanel(xSize, ySize);
        setSize(xSize+6, ySize+25);
        setTitle("XDeck");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(gp);
    }
    
    public static void main(String[] args) {
        Main m = new Main();
    }
    
}
