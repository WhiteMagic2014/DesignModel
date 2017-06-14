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

class WhiteSingletion {

	// 饿汉
	private static WhiteSingletion uniqueInstance = new WhiteSingletion();

	private WhiteSingletion() {
	}

	public static WhiteSingletion getInstance() {

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
