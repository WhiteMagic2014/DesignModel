package myTank;

public class Bullet implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 3;
	boolean isAlive = true;

	public Bullet(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}

			switch (direct) {
			case 0:
				// up
				y -= speed;
				break;

			case 1:
				// down
				y += speed;
				break;

			case 2:
				// left
				x -= speed;
				break;

			case 3:

				// right
				x += speed;
				break;

			}
//			System.out.println("x = " + x + ",y = " + y);

			// 什么时候线程死亡
			// 当超过边际时
			if (this.x < 0 || this.x > Simulator.WIDTH || this.y < 0 || y > Simulator.LENGTH) {
				this.isAlive = false;
				break;
			}

		}

	}

}
