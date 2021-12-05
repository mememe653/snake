package com.mememe653.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JPanel implements ActionListener {
	
	public enum Direction {
		LEFT,
		RIGHT,
		UP,
		DOWN
	}
	
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
	
	private Direction snakeDirection = Direction.RIGHT;
	
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
		int tail_x = x[dots-1];
		int tail_y = y[dots-1];
		moveSnake();
		repaint();
		if (checkCollision()) {
			gameOver();
		}
		if (checkAppleEaten()) {
			x[dots] = tail_x;
			y[dots] = tail_y;
			dots++;
			relocateApple();
		}
	}
	
	private void moveSnake() {
		for (int i = dots - 1; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch (snakeDirection) {
		case LEFT:
			x[0] -= DOT_WIDTH;
			x[0] %= WIDTH;
			break;
		case RIGHT:
			x[0] += DOT_WIDTH;
			x[0] %= WIDTH;
			break;
		case UP:
			y[0] -= DOT_WIDTH;
			y[0] %= HEIGHT;
			break;
		case DOWN:
			y[0] += DOT_WIDTH;
			y[0] %= HEIGHT;
			break;
		}
	}
	
	private boolean checkCollision() {
		for (int i = 1; i < dots; i++) {
			if (x[0] == x[i] && y[0] == y[i]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkAppleEaten() {
		if (x[0] == apple_x && y[0] == apple_y) {
			return true;
		} else {
			return false;
		}
	}
	
	private void gameOver() {
		timer.stop();
	}

}
