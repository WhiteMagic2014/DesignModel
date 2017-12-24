package myTank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

/**
 * @author chenhaoyu 记录以及保存设置
 */
public class Recorder {

	/**
	 * @author chenhaoyu 内部类
	 */
	class Node {
		int x;
		int y;
		int direct;

		public Node(int x, int y, int direct) {
			this.x = x;
			this.y = y;
			this.direct = direct;
		}
	}

	private static Recorder instanceRecorder;

	private int enNum = 20;
	private int myLife = 3;
	private int hitAll = 0;

	private FileWriter fw = null;
	private BufferedWriter bw = null;
	private FileReader fr = null;
	private BufferedReader br = null;

	private Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
	private Vector<Node> nodes = new Vector<Node>();

	public static Recorder getInstanceRecorder() {

		if (instanceRecorder == null) {
			instanceRecorder = new Recorder();
		}
		return instanceRecorder;
	}

	public void readRecord(Boolean readAll) {
		try {

			fr = new FileReader("/Users/chenhaoyu/Downloads/learnIO/tank存档.txt");
			br = new BufferedReader(fr);

			// read
			String allhit = br.readLine();
			hitAll = Integer.parseInt(allhit);

			if (readAll) {
				String enemynum = br.readLine();
				enNum = Integer.parseInt(enemynum);
				String life = br.readLine();
				myLife = Integer.parseInt(life);

				String n = "";
				while ((n = br.readLine()) != null) {
					String[] xyd = n.split(" ");
					Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
					nodes.add(node);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void saveRecord() {
		try {
			fw = new FileWriter("/Users/chenhaoyu/Downloads/learnIO/tank存档.txt");
			bw = new BufferedWriter(fw);

			bw.write(hitAll + "\r\n");
			bw.write(enNum + "\r\n");
			bw.write(myLife + "\r\n");

			for (int i = 0; i < enemyTanks.size(); i++) {
				EnemyTank et = enemyTanks.get(i);

				// if the object is alive then save it
				if (et.isAlive) {
					String zuobiao = et.getX() + " " + et.getY() + " " + et.direct;
					bw.write(zuobiao + "\r\n");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();// 先开后关
				fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void setNodes(Vector<Node> nodes) {
		this.nodes = nodes;
	}

	public Vector<Node> getNodes() {
		return nodes;
	}

	public Vector<EnemyTank> getEnemyTanks() {
		return enemyTanks;
	}

	public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
		this.enemyTanks = enemyTanks;
	}

	public int getEnNum() {
		return enNum;
	}

	public void setEnNum(int enNum) {
		this.enNum = enNum;
	}

	public int getMyLife() {
		return myLife;
	}

	public void setMyLife(int myLife) {
		this.myLife = myLife;
	}

	public int getHitAll() {
		return hitAll;
	}

	public void setHitAll(int hitAll) {
		this.hitAll = hitAll;
	}
}
