package learnThread;

/**
 * @author chenhaoyu 使用继承实现线程
 */
public class Cat extends Thread {
	
	int times = 0;
	
	@Override
	public void run() {
//		 super.run();

		while (true) {
			//休眠一秒，sleep会想线程进入到Blocked状态，并且释放资源
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			times++;
			System.out.println("喵喵喵 "+ times);
			
			if (times >= 10) {
				break;
			}

		}

	}
}
