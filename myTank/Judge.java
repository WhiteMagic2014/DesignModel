package myTank;

import java.util.Vector;

/**
 * @author chenhaoyu 判定类
 */
public class Judge {

	int up, down, left, right;// 坦克判定边界

	/**
	 * @param bullet
	 * @param tank
	 * 子弹击中判定
	 */
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

	/**
	 * @param tank
	 * @param otherTanks
	 * @return tank 是否与 otherTanks中 坦克对象  重合判定
	 */
	public boolean isTouchedOtherTank(Tank tank, Vector<Tank> otherTanks) {

		switch (tank.direct) {
		case 0:
			for (int i = 0; i < otherTanks.size(); i++) {
				Tank oTank = otherTanks.get(i);
				if (oTank != tank) {
					if (oTank.direct == 0 || oTank.direct == 1) {
						if (tank.x + 5 >= oTank.x + 5 && tank.x + 5 <= oTank.x + 25 && tank.y >= oTank.y
								&& tank.y <= oTank.y + 30) {
							return true;
						}

						if (tank.x + 25 >= oTank.x + 5 && tank.x + 25 <= oTank.x + 25 && tank.y >= oTank.y
								&& tank.y <= oTank.y + 30) {
							return true;
						}

					}

					if (oTank.direct == 2 || oTank.direct == 3) {

						if (tank.x + 5 >= oTank.x && tank.x + 5 <= oTank.x + 30 && tank.y >= oTank.y + 5
								&& tank.y <= oTank.y + 25) {
							return true;
						}

						if (tank.x + 25 >= oTank.x && tank.x + 25 <= oTank.x + 30 && tank.y >= oTank.y + 5
								&& tank.y <= oTank.y + 25) {
							return true;
						}
					}

				}
			}

			break;

		case 1:

			for (int i = 0; i < otherTanks.size(); i++) {
				Tank oTank = otherTanks.get(i);
				if (oTank != tank) {
					if (oTank.direct == 0 || oTank.direct == 1) {
						if (tank.x + 5 >= oTank.x + 5 && tank.x + 5 <= oTank.x + 25 && tank.y + 30 >= oTank.y
								&& tank.y + 30 <= oTank.y + 30) {
							return true;
						}

						if (tank.x + 25 >= oTank.x + 5 && tank.x + 25 <= oTank.x + 25 && tank.y + 30 >= oTank.y
								&& tank.y + 30 <= oTank.y + 30) {
							return true;
						}

					}

					if (oTank.direct == 2 || oTank.direct == 3) {

						if (tank.x + 5 >= oTank.x && tank.x + 5 <= oTank.x + 30 && tank.y + 30 >= oTank.y + 5
								&& tank.y + 30 <= oTank.y + 25) {
							return true;
						}

						if (tank.x + 25 >= oTank.x && tank.x + 25 <= oTank.x + 30 && tank.y + 30 >= oTank.y + 5
								&& tank.y + 30 <= oTank.y + 25) {
							return true;
						}
					}

				}
			}

			break;

		case 2:

			for (int i = 0; i < otherTanks.size(); i++) {
				Tank oTank = otherTanks.get(i);
				if (oTank != tank) {
					if (oTank.direct == 0 || oTank.direct == 1) {
						if (tank.x >= oTank.x + 5 && tank.x <= oTank.x + 25 && tank.y + 5 >= oTank.y
								&& tank.y + 5 <= oTank.y + 30) {
							return true;
						}

						if (tank.x >= oTank.x + 5 && tank.x <= oTank.x + 25 && tank.y + 25 >= oTank.y
								&& tank.y + 25 <= oTank.y + 30) {
							return true;
						}

					}

					if (oTank.direct == 2 || oTank.direct == 3) {

						if (tank.x >= oTank.x && tank.x <= oTank.x + 30 && tank.y + 5 >= oTank.y + 5
								&& tank.y + 5 <= oTank.y + 25) {
							return true;
						}

						if (tank.x >= oTank.x && tank.x <= oTank.x + 30 && tank.y + 25 >= oTank.y + 5
								&& tank.y + 25 <= oTank.y + 25) {
							return true;
						}
					}

				}
			}

			break;

		case 3:
			for (int i = 0; i < otherTanks.size(); i++) {
				Tank oTank = otherTanks.get(i);
				if (oTank != tank) {
					if (oTank.direct == 0 || oTank.direct == 1) {
						if (tank.x + 30 >= oTank.x + 5 && tank.x + 30 <= oTank.x + 25 && tank.y + 5 >= oTank.y
								&& tank.y + 5 <= oTank.y + 30) {
							return true;
						}

						if (tank.x + 30 >= oTank.x + 5 && tank.x + 30 <= oTank.x + 25 && tank.y + 5 >= oTank.y
								&& tank.y + 25 <= oTank.y + 30) {
							return true;
						}

					}

					if (oTank.direct == 2 || oTank.direct == 3) {

						if (tank.x + 30 >= oTank.x && tank.x + 30 <= oTank.x + 30 && tank.y + 5 >= oTank.y + 5
								&& tank.y + 5 <= oTank.y + 25) {
							return true;
						}

						if (tank.x + 30 >= oTank.x && tank.x + 30 <= oTank.x + 30 && tank.y + 25 >= oTank.y + 5
								&& tank.y + 25 <= oTank.y + 25) {
							return true;
						}
					}

				}
			}

			break;

		}

		return false;

	}

}
