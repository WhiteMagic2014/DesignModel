package myTank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * @author chenhaoyu 画板
 */
public class MPanle extends JPanel implements KeyListener, Runnable {

	Draw dt = null;
	MTank magicTank = null;
	Vector<EnemyTank> enemyTanks = null;
	Vector<Bomb> bombs = null;
	Judge judge = null;
	Random random = null;
	Vector<Tank> allTanks = null;

	public MPanle() {
		Recorder.getInstanceRecorder().readRecord(false);

		dt = new Draw();
		magicTank = new MTank(50, 100);
		judge = new Judge();
		enemyTanks = new Vector<EnemyTank>();// 考虑到多线程，用vector
		bombs = new Vector<Bomb>();// 爆炸效果
		random = new Random();
		allTanks = new Vector<Tank>();
		allTanks.add(magicTank);

		// 敌方坦克组
		for (int i = 0; i < Recorder.getInstanceRecorder().getEnNum(); i++) {
			// x= 0-350 y= 0-350 刷怪区域
			int x = random.nextInt(350);
			int y = random.nextInt(350);
			EnemyTank eTank = new EnemyTank(x, y);
			eTank.setDirect(random.nextInt(3));
			enemyTanks.add(eTank);
			allTanks.add(eTank);
			eTank.setOtherTanks(allTanks);
			new Thread(eTank).start();
		}

		magicTank.setOtherTanks(allTanks);
		Recorder.getInstanceRecorder().setEnemyTanks(enemyTanks);

	}

	// 覆盖paint 当屏幕初次显示时 paint会被自动调动一次 以下3种情况，paint也会被调用
	// 1，窗口最小化，再最大化
	// 2，窗口的大小发声变化
	// 3，repaint函数被调用
	public void paint(Graphics g) {// Graphics 相当于画笔

		super.paint(g);// 调用父类函数完成初始化，这句话很重要，必须

		// panle区域
		g.fillRect(0, 0, Simulator.WIDTH, Simulator.LENGTH);

		// 画info
		dt.drawinfo(g);

		// 结算
		if (Recorder.getInstanceRecorder().getEnNum() == 0) {
			g.setColor(Color.YELLOW);
			g.drawString("你赢了", Simulator.WIDTH / 2, Simulator.LENGTH / 2);
		} else if (Recorder.getInstanceRecorder().getMyLife() == 0) {
			g.setColor(Color.WHITE);
			g.drawString("GG", Simulator.WIDTH / 2, Simulator.LENGTH / 2);
		} else {
			// 画己方坦克
			if (magicTank.isAlive) {
				dt.drawTank(g, magicTank, 0);
			}
			// 画我的子弹
			drawbullet(magicTank, g);
			// 画炸爆炸效果
			for (int i = 0; i < bombs.size(); i++) {
				Bomb b = bombs.get(i);
				dt.drawBomb(g, b, this);

				if (b.life == 0) {
					bombs.remove(b);
				}
			}
			// 画敌方坦克
			for (int i = 0; i < enemyTanks.size(); i++) {
				EnemyTank enemyTank = enemyTanks.get(i);
				if (enemyTank.isAlive) {
					dt.drawTank(g, enemyTank, 1);
				}
				// 画敌方子弹
				drawbullet(enemyTank, g);
			}
		}

	}

	public Boolean needPaint = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() 定时刷新画布
	 */
	@Override
	public void run() {

		while (needPaint) {

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}

			this.hitEnemyJudge();
			this.hitMagicJudge();
			this.repaint();
		}

	}

	public void hitMagicJudge() {
		if (magicTank.isAlive) {
			for (int i = 0; i < enemyTanks.size(); i++) {
				EnemyTank eTank = enemyTanks.get(i);
				for (int j = 0; j < eTank.bullets.size(); j++) {
					Bullet eBullet = eTank.bullets.get(j);
					if (eBullet.isAlive) {
						bombjudge(eBullet, magicTank);
					}
				}
			}

			if (!magicTank.isAlive) {
				// 挂了 就判断是不是还有命
				if (Recorder.getInstanceRecorder().getMyLife() > 0) {
					Recorder.getInstanceRecorder().setMyLife(Recorder.getInstanceRecorder().getMyLife() - 1);
					magicTank.isAlive = true;
				}

			}
		}
	}

	/**
	 * 用已方的每一个子弹去匹配每一个地方坦克位置
	 */
	public void hitEnemyJudge() {
		for (int i = 0; i < magicTank.bullets.size(); i++) {
			// 取出子弹
			Bullet mBullet = magicTank.bullets.get(i);
			if (mBullet.isAlive) {
				// 取出敌方坦克
				for (int j = 0; j < enemyTanks.size(); j++) {
					EnemyTank eTank = enemyTanks.get(j);
					if (eTank.isAlive) {
						// 如果bullet 和 tank 都alive 开始判定
						// 判定接触后将会改变isalive
						bombjudge(mBullet, eTank);
						if (!eTank.isAlive) {
							Recorder.getInstanceRecorder().setEnNum(Recorder.getInstanceRecorder().getEnNum() - 1);
							Recorder.getInstanceRecorder().setHitAll(Recorder.getInstanceRecorder().getHitAll() + 1);
							enemyTanks.remove(eTank);
							allTanks.remove(eTank);
						}

					}

				}
			}
		}

	}

	// 子弹与坦克接触判断
	public void bombjudge(Bullet bullet, Tank tank) {
		judge.hitTank(bullet, tank);
		if (tank.isAlive == false) {
			// 爆炸
			Bomb bomb = new Bomb(tank.x, tank.y);
			bombs.add(bomb);
		}
	}

	public void drawbullet(Tank tank, Graphics g) {

		// if (tank.isAlive) {
		// //本来想让活着才能发子弹，死前打的子弹不算
		// }

		for (int i = 0; i < tank.bullets.size(); i++) {
			// 从坦克实例中取bulletsVector
			Bullet bullet = tank.bullets.get(i);
			// 画坦克的子弹(一颗。。)
			if (bullet != null && bullet.isAlive == true) {
				dt.drawBullet(g, bullet);
			}

			// 从vector移除消失的子弹
			if (bullet.isAlive == false) {
				tank.bullets.remove(bullet);
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {

			this.magicTank.moveUp();

		} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {

			this.magicTank.moveLeft();

		} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {

			this.magicTank.moveDown();

		} else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {

			this.magicTank.moveRight();

		}

		// 如果和上面一起 用else if ，那在移动中就无法发射了
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (magicTank.isAlive) {
				this.magicTank.shoot();
			}
		}

		// 重新绘制
		// this.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
