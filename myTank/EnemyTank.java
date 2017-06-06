package myTank;

import java.awt.Color;
import java.util.Random;

public class EnemyTank extends Tank implements Runnable {

	Random random = new Random();

	public EnemyTank(int x, int y) {
		super(x, y);
		setColor(Color.RED);
		this.speed = 2;
	}

	@Override
	public void run() {
		
		//这里的其实就是AI 
		while (true) {

			this.shoot();

			switch (this.direct) {
			case 0:
				// 上
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					this.moveUp();
				}

				break;

			case 1:
				// 下
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					this.moveDown();
				}
				break;

			case 2:
				// 左
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					this.moveLeft();
				}
				break;

			case 3:
				// 右
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					this.moveRight();
				}
				break;
			}

			// 随机产生一个方向
			this.direct = random.nextInt(3);
			// 判断死亡
			if (this.isAlive == false) {
				break;
			}

		}

	}

}
