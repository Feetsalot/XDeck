package Main;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    //Double buffering
    private Image dbImage;
    private Graphics dbg;
    //JPanel variable
    int GWIDTH, GHEIGHT;
    Dimension gameDim;
    //Game variables
    private Thread game;
    private volatile boolean running = false;
    private long period = 6*1000000; //ms -> nano
    private static final int DELAYS_BEFORE_YEILD = 10;
    //Game Objects
    public static boolean gameState = true;
    public boolean inState = false;
    public boolean menuState = true;
    public boolean libState = false;
    private int x, y;
    public MainMenu m;
    private Library lib;
    public static Image Background;
    
    public GamePanel(int xSize, int ySize){
    	
	    Background = new ImageIcon("C:/Users/Owner/Desktop/XDeck/card elements/cardback.png").getImage().getScaledInstance(1126, 726, 0);
    	lib = new Library();
        m = new MainMenu();
        GWIDTH = xSize;
        GHEIGHT = ySize;
        gameDim = new Dimension(GWIDTH, GHEIGHT);
        
        setPreferredSize(gameDim);
        setBackground(Color.WHITE);
        
        setFocusable(true);
        requestFocusInWindow();
        
        if(gameState == true){
        	//Handle all mouse inputs from user
        	addMouseMotionListener(new MouseAdapter(){
        		@Override
                public void mouseMoved(MouseEvent e){
                	m.mousehover(e.getX(), e.getY(), 425, 275, 200, 75);
                }

        	});
        	
        	addMouseListener(new MouseAdapter(){	
        		@Override
                public void mouseClicked(MouseEvent e){
        			menuState = false;
        			libState = true;
                }
        	});
        	
      //Handle all key inputs from user
        addKeyListener(new KeyAdapter(){
        	
			@Override
            public void keyPressed(KeyEvent e){
				
            }
            @Override
            public void keyReleased(KeyEvent e){
                	
            }      
        });
        }
        
    }


    
    public void run(){
    	long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
        int delays = 0;
    	while(running){
        	beforeTime = System.nanoTime();
        	
            gameUpdate();
            gameRender();
            paintScreen();
            
            afterTime = System.nanoTime();
            diff = afterTime - beforeTime;
        	sleepTime = (period - diff) - overSleepTime;
           // If the sleep time is between 0 and the period sleep
        	if(sleepTime < period && sleepTime > 0){
            	try {
					game.sleep(sleepTime / 1000000L);
					overSleepTime = 0;
				} catch (InterruptedException e) {
					//Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null);
				}
            }
        	// The diff was greater than the period
        	else if(diff > period){
        		overSleepTime = diff - period;
        	}
        	// Accumulate the amount of delays and evantuall yeild
        	else if(++delays >= DELAYS_BEFORE_YEILD){
        		game.yield();
        		delays = 0;
        		overSleepTime = 0;
        	}
        	// The loop took less time then expectted but we need to make up for the over sleep time
        	else{
        		overSleepTime = 0;
        	}
        	//Print out game stats
        	/*log(
        		"beforeTime:	" + beforeTime + "\n" +
        		"afterTime:		" + afterTime + "\n" +
        		"diff:			" + diff + "\n" +
        		"SleepTime:		" + sleepTime / 1000000L + "\n" +
        		"delays:		" + delays +"\n"
        		);*/
        }
    }
    
    private void gameUpdate(){
        if(running && game != null){
            
        }
    }
    
    private void gameRender(){
        if(dbImage == null){ // Create the buffer
            dbImage = createImage(GWIDTH, GHEIGHT);
            if(dbImage == null){
                System.err.println("dbImage is still null!");
                return;
            }else{
                dbg = dbImage.getGraphics();
            }
        }
        //Clear the screen
        dbg.setColor(Color.WHITE);
        dbg.fillRect(0, 0, GWIDTH, GHEIGHT);
        //Draw Game elements
        draw(dbg);
    }
    
    /* Draw all game content in this method */
    public void draw(Graphics g){
    	if(menuState){
        m.draw(g);
    	}
    	if(libState){
    	lib.draw(g);
    	}

    }
    
    private void paintScreen(){
        Graphics g;
        try{
            g = this.getGraphics();
            if(dbImage != null && g != null){
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync(); //For some operating systems
            g.dispose();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public void addNotify(){
        super.addNotify();
        startGame();
    }
    
    private void startGame(){
        if(game == null || !running){
            game = new Thread(this);
            game.start();
            running = true;
        }
    }
    
    public void stopGame(){
        if(running){
            running = false;
        }
    }
    
    private void log(String s){
        System.out.println(s);
    }
}
