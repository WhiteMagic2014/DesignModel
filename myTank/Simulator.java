package myTank;

import javax.swing.JFrame;

public class Simulator extends JFrame {

	MPanle mypanle = null;
	public static final int LENGTH = 400;
	public static final int WIDTH = 400;


	public static void main(String[] args) {
		Simulator mysm = new Simulator();
	}

	public Simulator() {
		mypanle = new MPanle();
		this.add(mypanle);
		this.addKeyListener(mypanle);
		
		//启动画布线程，让他开始自动重绘
		Thread thread = new Thread(mypanle);
		thread.start();

		this.setTitle("magic");
		this.setSize(Simulator.WIDTH, Simulator.LENGTH);
		this.setLocation(500, 200);
		// 设置退出即关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
