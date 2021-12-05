package com.mememe653.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Window extends JPanel {
	
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	
	public Window() {
		initWindow();
	}
	
	private void initWindow() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

}
