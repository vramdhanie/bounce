package com.vincentramdhanie.bounce;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

public class BouncePanel extends JPanel implements KeyListener{

	public static final Color BACKGROUND_COLOUR= Color.GREEN;
	public static final int REPAINT_SPEED = 30;

	public static final int MAX_BALLS = 30;
	List<PoolBall> balls;

	public BouncePanel(){
		super();
		setBackground(BACKGROUND_COLOUR);
		balls = new ArrayList<PoolBall>();
		Random ran = new Random();
		for(int i = 1; i <= MAX_BALLS; i++){
			PoolBall p = new PoolBall(
				ran.nextDouble() * BounceFrame.WINDOW_WIDTH,
				ran.nextDouble() * BounceFrame.WINDOW_HEIGHT,
				new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
			balls.add(p);
		}

		Thread t = new Thread(new Repainter());
		t.start();
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(PoolBall ball: balls){
			ball.draw((Graphics2D)g);
		}
	}

	private class Repainter implements Runnable{
		public void run(){
			while(true){
				try{
					repaint();
					Thread.sleep(REPAINT_SPEED);
				}catch(InterruptedException e){}

			}
		}
	}


	public void keyPressed(KeyEvent e){

	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){
		if(e.getKeyChar() == KeyEvent.VK_A){
			for(PoolBall ball: balls){
				ball.start();
			}
		}else{
			if(e.getKeyChar() == KeyEvent.VK_Z){
				for(PoolBall ball: balls){
					ball.stop();
				}
			}else{
				if(e.getKeyChar() == KeyEvent.VK_S){
					try(ObjectOutputStream out = 
						new ObjectOutputStream(
							new BufferedOutputStream(
								new FileOutputStream("output.obj")))){
						for(PoolBall ball:balls){
							out.writeObject(ball);
						}
						JOptionPane.showMessageDialog(this, "Game Saved");
					}catch(FileNotFoundException ex){
						ex.printStackTrace();
					}catch(IOException ex){
						ex.printStackTrace();
					}	
				}
			}
		}
	}


}
