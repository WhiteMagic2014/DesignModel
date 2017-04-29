package observerModel;

public class Test {

	public static void main(String[] args) {
		Xueba laofoye = new Xueba();
		Xuezha xuezha1 = new Xuezha("萌萌");
		Xuezha xuezha2 = new Xuezha("我");

		xuezha1.receive(laofoye);
		xuezha2.receive(laofoye);

		laofoye.zuoti("111", "1111");
		
		xuezha1.notReceive();//观察者主动放弃订阅
		laofoye.zuoti("222", "2222");
		
		laofoye.removeObserver(xuezha2);//被观察者踢人
		laofoye.zuoti("333", "3333");

	}

}
