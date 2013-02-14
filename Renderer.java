import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyListener;

public class Renderer extends Canvas{
    private JFrame frame;
    private BufferStrategy buffer;
    private Graphics graphic;

    public final int width;
    public final int height;
    //constructor
    public Renderer(Input inp){
	width = 800;
	height = 600;
	frame = new JFrame("OOTanks");
	//get content of the frame, determine size
	JPanel panel = (JPanel) frame.getContentPane();
	panel.setPreferredSize(new Dimension(width,height));
	panel.setLayout(null);

	//set up canvas and boundaries, add panel to 'this' canvas
	setBounds(0,0,width,height);
	panel.add(this);

	//we will paint manually, so
	setIgnoreRepaint(true);
	frame.setIgnoreRepaint(true);

	frame.pack();
	frame.setResizable(false);
	frame.setVisible(true);
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//creating buffering strategy
	createBufferStrategy(2);
	buffer = getBufferStrategy();
	
	//handle input init. Sadly, we need to you this out of our canvas
	setKeyListener(inp);
    }
    
    //function to draw square at (x,y)
    public void drawTank(int x,int y){
  	int h = 50;
  	int w = 50;
 		
 	graphic.setColor(Color.darkGray);
  	graphic.drawRect(x, y, h, w);
  	graphic.fillRect(x, y, h, w);
  	graphic.setColor(Color.GRAY);
  	graphic.drawRect(x+50, y+20, 35, 10);
  	graphic.fillRect(x+50, y+20, 35, 10);
    }
    public void init(){
	//load resources here
    }
    public void update(){
	//reset the graphics
	graphic = null;
	
	// get ready to draw
	graphic = buffer.getDrawGraphics();
	
	//fill background to green (Green for no reason)
	graphic.setColor(Color.GREEN);
	graphic.fillRect(0,0,width,height);

	//drawing will be done here
	
	// drawTank function test: SUCCESS
	drawTank(500,100);
	// Garbage {
	/* graphic.setColor(Color.blue);

	int thickness = 4;
	//not the most wise thing to do
	for (int i = 0; i <= thickness; i++)
	    graphic.draw3DRect(600 - i, 510 - i, 80 + 2 * i, 30 + 2 * i, true); //use tabbing, especially when not using {}
	
	//same as above
	for (int i = 0; i < thickness; i++)
	    graphic.draw3DRect(600 - i, 550 - i, 80 + 2 * i, 30 + 2 * i, false); //use tabbing, especially when not using {}
	
	int height = 200;
	int width = 120;
	  
       	graphic.setColor(Color.red);
      	graphic.drawRect(10, 10, height, width);

      	graphic.setColor(Color.gray);
      	graphic.fillRect(10, 10, height, width);

      	graphic.setColor(Color.red);
     	graphic.drawOval(250, 250, height, width);

     	graphic.setColor(Color.yellow);
     	graphic.fillOval(250, 250, height, width);
       */ 
	// }
	//end of drawing

	//syncs everything to smooth java frames
	Toolkit.getDefaultToolkit().sync();
	if(!buffer.contentsLost()){
	    buffer.show();
	} else {
	    System.out.println("Data Lost in buffer");
	}
    }
    public void release(){

    }
    //input handler
    public void setKeyListener(Input inp) {
	addKeyListener(inp);
       	requestFocus();
    }
};
