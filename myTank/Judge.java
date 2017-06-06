package myTank;

/**
 * @author chenhaoyu 子弹的判定类
 */
public class Judge {

	int up, down, left, right;// 坦克判定边界

	public void hitTank(Bullet bullet, Tank tank) {

		switch (tank.direct) {
		// 上下
		case 0:
		case 1:
			up = tank.y;
			down = tank.y + 30;
			left = tank.x + 5;
			right = tank.x + 25;

			break;
		// 左右

		case 2:
		case 3:

			up = tank.y + 5;
			down = tank.y + 25;
			left = tank.x;
			right = tank.x + 30;

			break;
		}

		// 判定
		if (bullet.x >= left && bullet.x <= right && bullet.y >= up && bullet.y <= down) {
			tank.isAlive = false;
			bullet.isAlive = false;

		}

	}

}
