package learnGUI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Simulator extends JFrame {

	JButton jButton = null;

	public static void main(String[] args) {
		Simulator sim = new Simulator();

	}

	public Simulator() {

		jButton = new JButton("btn");

		this.setTitle("magic");

		this.setSize(200, 200);
		this.setLocation(500, 200);
		
		this.setResizable(false);

		// 设置退出即关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
