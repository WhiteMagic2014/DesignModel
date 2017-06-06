package myTank;

import java.awt.Color;
import java.util.Vector;

/**
 * @author chenhaoyu
 *
 */
public class Tank {
	int x = 0;
	int y = 0;
	int direct = 0;// 方向 0上 1下 2左 3右
	int speed = 5;
	Boolean isAlive = true;
	Color color = Color.WHITE;// 所有坦克默认白色
	Vector<Bullet> bullets = new Vector<Bullet>();
	Bullet bullet = null;

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;

	}

	// 开火
	public void shoot() {

		switch (this.direct) {

		case 0:
			bullet = new Bullet(this.x + 15, this.y - 2, 0);
			break;

		case 1:
			bullet = new Bullet(this.x + 15, this.y + 32, 1);
			break;

		case 2:
			bullet = new Bullet(this.x - 2, this.y + 15, 2);
			break;

		case 3:
			bullet = new Bullet(this.x + 32, this.y + 15, 3);
			break;

		}

		bullets.add(bullet);

		// 给每一个子弹开一个线程
		Thread t = new Thread(bullet);
		t.start();

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getDirect() {
		return direct;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void moveUp() {
		this.direct = 0;
		if (this.y > 0) {
			y -= speed;
		}

	}

	public void moveDown() {
		this.direct = 1;
		if (this.y < Simulator.LENGTH - 30) {
			y += speed;
		}

	}

	public void moveLeft() {
		this.direct = 2;
		if (this.x > 0) {
			x -= speed;
		}

	}

	public void moveRight() {
		this.direct = 3;
		if (this.x < Simulator.WIDTH - 30) {
			x += speed;
		}

	}

}
