import java.util.Random;

public class WhatToEat {

	
	Random random = null;

	
	public WhatToEat() {
		random = new Random();
//		System.out.println(random.nextInt(9));
		
		switch (random.nextInt(9)) {
		case 0:
			System.out.println("黄焖系列");
			break;
		case 1:
			System.out.println("拌面");
			break;
		case 2:
			System.out.println("凉皮");
			break;
		case 3:
			System.out.println("沙拉");
			break;
		case 4:
			System.out.println("炒饭");
			break;
		case 5:
			System.out.println("香锅");
			break;
		case 6:
			System.out.println("饺子");
			break;
		case 7:
			System.out.println("馄饨");
			break;
		case 8:
			System.out.println("烧腊套餐");
			break;
		case 9:
			System.out.println("烤冷面");
			break;
		}
		
	}
	
	public static void main(String args[]){
		new WhatToEat();
	}
	
	
	
}
