package com.mememe653.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JPanel implements ActionListener {
	
	private final int DELAY = 100;
	
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	
	private final Color SNAKE_COLOR = Color.green;
	private final Color APPLE_COLOR = Color.red;
	
	private final int DOT_WIDTH = 10;
	private final int MAX_DOTS = (int) (WIDTH * HEIGHT / Math.pow(DOT_WIDTH, 2));
	private final int INIT_SNAKE_LENGTH = 5;
	private int dots = 0;
	
	private int x[] = new int[MAX_DOTS];
	private int y[] = new int[MAX_DOTS];
	
	private int apple_x;
	private int apple_y;
	
	private final Timer timer = new Timer(DELAY, this);
	
	public Window() {
		initWindow();
		initSnake();
		initApple();
		
		timer.start();
	}
	
	private void initWindow() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	private void initSnake() {
		for (int i = 0; i < INIT_SNAKE_LENGTH; i++) {
			x[INIT_SNAKE_LENGTH - i - 1] = i * DOT_WIDTH;
			y[i] = 0;
			dots++;
		}
	}
	
	private void initApple() {
		relocateApple();
	}
	
	private void relocateApple() {
		apple_x = (int) (Math.random() * WIDTH);
		apple_y = (int) (Math.random() * HEIGHT);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw apple
		g.setColor(APPLE_COLOR);
		g.fillRect(apple_x, apple_y, DOT_WIDTH, DOT_WIDTH);
		
		// Draw snake
		g.setColor(SNAKE_COLOR);
		for (int i = 0; i < dots; i++) {
			g.fillRect(x[i], y[i], DOT_WIDTH, DOT_WIDTH);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		moveSnake();
		repaint();
	}
	
	private void moveSnake() {
		for (int i = dots - 1; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		x[0] += DOT_WIDTH;
		x[0] = x[0] % WIDTH;
	}

}
