package com.vincentramdhanie.bounce;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.util.Random;
import java.awt.geom.Area;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class PoolBall implements Serializable {

	public static final int BALL_SIZE = 20;
	public static  int BALL_SPEED;

	double x;
	double y;
	double size;

	
	transient Shape shape;

	Color colour;

	double dx;
	double dy;
	boolean moving;



	public PoolBall(){
		this(200, 200, Color.BLACK);
	}

	public PoolBall(double x, double y, Color colour){
		this.x = x;
		this.y = y;
		this.colour = colour;
		size = BALL_SIZE;

		Area nucleus = new Area(new Ellipse2D.Double(8, 8, 4, 4));

		Area a = new Area(new Ellipse2D.Double(4, 0, 12, size));
		Area b = new Area(new Ellipse2D.Double(5, 1, 10, 18));
		a.subtract(b);

		AffineTransform af = new AffineTransform();
		af.shear(0.3, 0.2);
		Area c1 = new Area(af.createTransformedShape(a));

		AffineTransform af2 = new AffineTransform();
		af.shear(-0.3, 0.2);
		Area c2 = new Area(af2.createTransformedShape(a));

		nucleus.add(c1);
		nucleus.add(c2);

		shape = nucleus;


		Random ran = new Random();

		dx = ran.nextDouble() * 10;
		dy = ran.nextDouble() * 10;
		BALL_SPEED = ran.nextInt(20) + 20;

		moving = true;

		Thread t = new Thread(new Mover());
		t.start();
	}

	public void start(){
		if(!moving){
			moving = true;
			Thread t = new Thread(new Mover());
			t.start();
		}
	}

	public void stop(){
		moving = false;
	}


	public void draw(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
		Paint origin = g.getPaint();
		g.translate(x, y);
		g.setPaint(new GradientPaint(0,0,Color.WHITE, 20, 20, colour));
		g.fill(shape);
		g.translate(-x, -y);
		g.setPaint(origin);
	}

	public void move(){


		if(x > BounceFrame.WINDOW_WIDTH - BALL_SIZE || x < 0){
			dx *= -1;
		}

		if(y > BounceFrame.WINDOW_HEIGHT - BALL_SIZE || y < 0){
			dy *= -1;
		}

		x += dx;
		y += dy;

	}

	private class Mover implements Runnable{
		public void run(){
			while(moving){
				try{
					move();
					Thread.sleep(BALL_SPEED);
				}catch(InterruptedException e){}
			}
		}
	}

}
