package observerModel;

public class Xuezha implements Observer {

	private String name;
	private Subject dashen;// 多态，针对接口编程，表示不管是学霸，还是一般同学，报了答案就是大神

	public Xuezha(String name) {
		this.name = name;
	}

	@Override
	public void update(String info1, String info2) {
		System.out.println("我是" + name + ",我收到答案了\n第一题答案：" + info1 + "\n第二题答案：" + info2);
	}

	@Override
	public void receive(Subject sub) {
		System.out.println("我是" + name + ",我要收答案");
		this.dashen = sub;
		dashen.registerObserver(this);
	}

	@Override
	public void notReceive() {
		System.out.println("我是" + name + ",良心发现，要好好学习，不看答案了\n");
		dashen.removeObserver(this);
	}

}
