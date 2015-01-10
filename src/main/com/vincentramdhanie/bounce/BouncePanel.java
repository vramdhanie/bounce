package com.vincentramdhanie.bounce;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BouncePanel extends JPanel{

	public static final Color BACKGROUND_COLOUR= Color.GREEN;
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
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(PoolBall ball: balls){
			ball.draw((Graphics2D)g);
		}
	}
	
}
