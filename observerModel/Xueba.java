package observerModel;

import java.util.ArrayList;

public class Xueba implements Subject {

	ArrayList<Observer> observers;// 学霸要发答案的名单
	String str1,str2;

	/**
	 * 构造方法 初始化 ArrayList
	 */
	public Xueba() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = observers.indexOf(observer);
		if (i>=0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(str1, str2);
		}
	}
	
	
	public void zuoti(String answer1,String answer2) {
		this.str1 = answer1;
		this.str2 = answer2;
		notifyObservers();
	}

}
