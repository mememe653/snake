package com.mememe653.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Snake extends JFrame {
	
	public Snake() {
		initUI();
	}
	
	private void initUI() {
		add(new Window());
		setResizable(false);
		pack();
		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new Snake();
			frame.setVisible(true);
		});
	}
}
