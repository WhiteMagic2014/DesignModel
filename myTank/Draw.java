package myTank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw {

	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	Image image4 = null;
	Image image5 = null;
	Image image6 = null;
	Image image7 = null;
	Image image8 = null;

	public Draw() {
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		// System.out.println(path);
		try {
			image1 = ImageIO.read(new File(path + "/blast1.gif"));
			image2 = ImageIO.read(new File(path + "/blast2.gif"));
			image3 = ImageIO.read(new File(path + "/blast3.gif"));
			image4 = ImageIO.read(new File(path + "/blast4.gif"));
			image5 = ImageIO.read(new File(path + "/blast5.gif"));
			image6 = ImageIO.read(new File(path + "/blast6.gif"));
			image7 = ImageIO.read(new File(path + "/blast7.gif"));
			image8 = ImageIO.read(new File(path + "/blast8.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// image1 =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/blast1.gif"));

	}

	/**
	 * @param g
	 * @param bomb
	 * @param jPanel
	 * 画爆炸效果
	 */
	public void drawBomb(Graphics g, Bomb bomb, JPanel jPanel) {

		if (bomb.life == 8) {
			g.drawImage(image1, bomb.x, bomb.y, 30, 30, jPanel);
		} else if (bomb.life == 7) {
			g.drawImage(image2, bomb.x, bomb.y, 30, 30, jPanel);

		} else if (bomb.life == 6) {
			g.drawImage(image3, bomb.x, bomb.y, 30, 30, jPanel);

		} else if (bomb.life == 5) {
			g.drawImage(image4, bomb.x, bomb.y, 30, 30, jPanel);

		} else if (bomb.life == 4) {
			g.drawImage(image5, bomb.x, bomb.y, 30, 30, jPanel);

		} else if (bomb.life == 3) {
			g.drawImage(image6, bomb.x, bomb.y, 30, 30, jPanel);

		} else if (bomb.life == 2) {
			g.drawImage(image7, bomb.x, bomb.y, 30, 30, jPanel);

		} else if (bomb.life == 1) {
			g.drawImage(image8, bomb.x, bomb.y, 30, 30, jPanel);

		}

		bomb.lifeDown();

	}

	/**
	 * @param g
	 * @param tank
	 *            画子弹
	 */
	public void drawBullet(Graphics g, Bullet bullet) {

		g.setColor(Color.WHITE);
		g.fillRect(bullet.x - 1, bullet.y - 1, 2, 2);
		// g.drawLine(tank.bullet.x, tank.bullet.y, tank.bullet.x,
		// tank.bullet.y);
	}

	/**
	 * 画坦克
	 * 
	 * @param x
	 *            横坐标
	 * @param y
	 *            纵坐标
	 * @param g
	 *            画笔
	 * @param direct
	 *            方向
	 * @param type
	 *            坦克种类
	 */
	// public void drawTank(int x, int y, Graphics g, int direct, int type,
	// Color color) {

	public void drawTank(Graphics g, Tank tank, int type) {

		// 区分种类
		switch (type) {
		case 0:

			break;

		case 1:

			break;
		}

		g.setColor(tank.getColor());

		// 区分方向
		switch (tank.getDirect()) {
		case 0:
			// 上
			// 左轮
			g.fill3DRect(tank.getX() + 5, tank.getY(), 5, 30, false);
			// 右轮
			g.fill3DRect(tank.getX() + 20, tank.getY(), 5, 30, false);
			// 本体
			g.fill3DRect(tank.getX() + 10, tank.getY() + 5, 10, 20, false);
			// 头部
			g.fillOval(tank.getX() + 10, tank.getY() + 10, 10, 10);
			// 炮
			g.drawLine(tank.getX() + 15, tank.getY() + 15, tank.getX() + 15, tank.getY() - 2);

			break;

		case 1:
			// 下
			// 左轮
			g.fill3DRect(tank.getX() + 5, tank.getY(), 5, 30, false);
			// 右轮
			g.fill3DRect(tank.getX() + 20, tank.getY(), 5, 30, false);
			// 本体
			g.fill3DRect(tank.getX() + 10, tank.getY() + 5, 10, 20, false);
			// 头部
			g.fillOval(tank.getX() + 10, tank.getY() + 10, 10, 10);
			// 炮
			g.drawLine(tank.getX() + 15, tank.getY() + 15, tank.getX() + 15, tank.getY() + 32);

			break;

		case 2:
			// 左
			// 上轮
			g.fill3DRect(tank.getX(), tank.getY() + 5, 30, 5, false);
			// 下轮
			g.fill3DRect(tank.getX(), tank.getY() + 20, 30, 5, false);
			// 本体
			g.fill3DRect(tank.getX() + 5, tank.getY() + 10, 20, 10, false);
			// 头部
			g.fillOval(tank.getX() + 10, tank.getY() + 10, 10, 10);
			// 炮
			g.drawLine(tank.getX() + 15, tank.getY() + 15, tank.getX() - 2, tank.getY() + 15);

			break;

		case 3:
			// 右
			// 上轮
			g.fill3DRect(tank.getX(), tank.getY() + 5, 30, 5, false);
			// 下轮
			g.fill3DRect(tank.getX(), tank.getY() + 20, 30, 5, false);
			// 本体
			g.fill3DRect(tank.getX() + 5, tank.getY() + 10, 20, 10, false);
			// 头部
			g.fillOval(tank.getX() + 10, tank.getY() + 10, 10, 10);
			// 炮
			g.drawLine(tank.getX() + 15, tank.getY() + 15, tank.getX() + 32, tank.getY() + 15);
			break;
		}

	}
	
	
	public void drawinfo(Graphics g) {
		
		Tank meinfo =  new MTank(Simulator.WIDTH + 20, 40);
		Tank enemyinfo = new EnemyTank(Simulator.WIDTH + 20, 85);
		
		// 画标识
		g.setColor(Color.black);
		g.drawString("累计消灭"+Recorder.getInstanceRecorder().getHitAll(), Simulator.WIDTH + 55, 24);
		
		drawTank(g, meinfo, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getInstanceRecorder().getMyLife() + "", Simulator.WIDTH + 55, 54);
		drawTank(g, enemyinfo, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getInstanceRecorder().getEnNum() + "", Simulator.WIDTH + 55, 104);
	}
	
	

}
