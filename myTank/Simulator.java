package myTank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Simulator extends JFrame implements ActionListener {

	public static final int LENGTH = 400;
	public static final int WIDTH = 400;
	MPanle mypanle = null;
	StartPanle startPanle = null;

	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmiStartItem = null;
	JMenuItem jmiExitItem = null;
	JMenuItem continueItem = null;

	public static void main(String[] args) {
		Simulator mysm = new Simulator();
	}

	public Simulator() {

		jmb = new JMenuBar();

		jm1 = new JMenu("游戏(G)");
		jm1.setMnemonic('G');

		jmiStartItem = new JMenuItem("开始新游戏");
		jmiStartItem.addActionListener(this);
		jmiStartItem.setActionCommand("start");

		jmiExitItem = new JMenuItem("保存并退出");
		jmiExitItem.addActionListener(this);
		jmiExitItem.setActionCommand("exit");

		continueItem = new JMenuItem("继续上一盘");
		continueItem.addActionListener(this);
		continueItem.setActionCommand("continue");

		this.setJMenuBar(jmb);

		jmb.add(jm1);

		jm1.add(jmiStartItem);
		jm1.add(continueItem);
		jm1.add(jmiExitItem);

		startPanle = new StartPanle();
		this.add(startPanle);
		Thread thread = new Thread(startPanle);
		thread.start();

		this.setTitle("magic");
		this.setSize(Simulator.WIDTH + 150, Simulator.LENGTH);
		this.setLocation(500, 200);
		// 设置退出即关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	Thread gameThread;

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("start")) {

			if (mypanle != null) {
				mypanle.needPaint = false;
				mypanle = null;
				gameThread.interrupt();
			}

			this.remove(startPanle);
			mypanle = new MPanle();
			gameThread = new Thread(mypanle);
			gameThread.start();
			this.add(mypanle);
			this.addKeyListener(mypanle);
			// 启动画布线程，让他开始自动重绘
			this.setVisible(true);

		} else if (e.getActionCommand().equals("exit")) {

			if (mypanle != null) {
				Recorder.getInstanceRecorder().saveRecord();
			}
			System.exit(0);// 退出操作
		} else if (e.getActionCommand().equals("continue")) {

		}

	}

}
