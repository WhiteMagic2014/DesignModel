package learnThread;

public class Sale implements Runnable {

	int ticketNum = 20;

	@Override
	public void run() {

		synchronized (this) {
			while (true) {

				if (ticketNum > 0) {
					System.out.println(Thread.currentThread().getName() + " 第 " + ticketNum + " 个");

					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ticketNum--;

				} else {
					break;
				}

			}
		}

	}
}
