package com.vincentramdhanie.bounce;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.RenderingHints;

public class PoolBall {

	public static final int BALL_SIZE = 20;

	double x;
	double y;
	double size;
	Shape shape;
	Color colour;

	public PoolBall(){
		this(200, 200, Color.BLACK);
	}

	public PoolBall(double x, double y, Color colour){
		this.x = x;
		this.y = y;
		this.colour = colour;
		size = BALL_SIZE;
		shape = new Ellipse2D.Double(0, 0, size, size);
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

}
