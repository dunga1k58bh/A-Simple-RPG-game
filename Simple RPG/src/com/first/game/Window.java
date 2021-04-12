package com.first.game;

import javax.swing.JFrame;
public class Window  extends JFrame {
	public Window () {
		setTitle("Adventure 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(1000, 600));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
