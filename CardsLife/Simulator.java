package CardsLife;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Simulator {

	public static void main(String[] args) throws Exception {

		Game mGame = new Game();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("这是一个无聊的抽卡生存游戏....");
		System.out.println("draw——抽卡");
		System.out.println("draw10——十连抽");
		System.out.println("eat——吃饭");
		System.out.println("drink——喝水");
		System.out.println("clam——使用镇静剂");
		System.out.println("treat——治疗");
		System.out.println("next——下一天");
		System.out.println("help——帮助");
		System.out.println("exit——退出");

		while (true) {

			System.out.println("请问下一步要做什么...");
			String operType = br.readLine();

			if (operType.equals("draw")) {
				mGame.drawCard();
			} else if (operType.equals("draw10")) {
				draw10times(mGame);
			} else if (operType.equals("eat")) {
				mGame.eat();
			} else if (operType.equals("drink")) {
				mGame.drink();
			} else if (operType.equals("clam")) {
				mGame.clamdown();
			} else if (operType.equals("treat")) {
				mGame.Treatment();
			} else if (operType.equals("next")) {
				mGame.nextday();
			} else if (operType.equals("help")) {
				System.out.println("这是一个无聊的抽卡生存游戏....");
				System.out.println("draw——抽卡");
				System.out.println("draw10——十连抽");
				System.out.println("eat——吃饭");
				System.out.println("drink——喝水");
				System.out.println("clam——使用镇静剂");
				System.out.println("treat——治疗");
				System.out.println("next——下一天");
				System.out.println("help——帮助");
				System.out.println("exit——退出");
			} else if (operType.equals("exit")) {
				// 退出系统
				System.exit(0);// 状态码 0 为正常退出 非零表示异常
			}

		}

	}

	public static void draw10times(Game game) {
		for (int i = 0; i < 10; i++) {
			game.drawCard();
		}
	}

}
