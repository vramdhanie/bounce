package com.vincentramdhanie.bounce;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BounceFrame extends JFrame{

	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 400;
	JPanel bouncePanel;

	public BounceFrame(){
		super("Bounce");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bouncePanel = new BouncePanel();
		add(bouncePanel, BorderLayout.CENTER);

		setVisible(true);
	}


	public static void main(String args[]){
		new BounceFrame();
	}
}
