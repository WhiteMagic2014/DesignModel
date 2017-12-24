package SingletonPattern;

public class MagicSingletion {

	// 懒汉
	private static MagicSingletion uniqueInstance;

	private MagicSingletion() {
	}

	public static MagicSingletion getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new MagicSingletion();
		}

		return uniqueInstance;
	}

}

class WMSingletion {

	// 双重检查锁，首先检查实例是不是已经创建了，如果未创建，才进行同步，这样只有第一次会同步
	private volatile static WMSingletion uniqueInstance;

	private WMSingletion() {
	}

	public static WMSingletion getInstance() {

		if (uniqueInstance == null) {
			// 加个文件锁
			synchronized (WMSingletion.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new WMSingletion();
				}
			}

		}
		return uniqueInstance;
	}

}

/**
 * @author chenhaoyu 这种好一些，既实现了线程安全，又避免了同步带来的性能影响
 * 
 */
class Singleton {
	private static class LazyHolder {
		private static final Singleton INSTANCE = new Singleton();
	}

	private Singleton() {
	}

	public static final Singleton getInstance() {
		return LazyHolder.INSTANCE;
	}
}



class WhiteSingletion {

	// 饿汉
	private static WhiteSingletion uniqueInstance = new WhiteSingletion();

	private WhiteSingletion() {
	}

	public static WhiteSingletion getInstance() {

		return uniqueInstance;
	}

}

