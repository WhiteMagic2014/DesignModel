package myTank;

public class Bomb {
	int x, y;
	int life = 8;// 炸弹的生命
	boolean isAlive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isAlive = false;
		}
	}

}
