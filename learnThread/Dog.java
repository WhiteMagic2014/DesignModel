package learnThread;

public class Dog implements Runnable {
	int times = 0;

	@Override
	public void run() {

		while (true) {
			// 休眠一秒，sleep会想线程进入到Blocked状态，并且释放资源
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			times++;
			System.out.println("汪汪汪 " + times);

			if (times >= 10) {
				break;
			}

		}
	}

}
