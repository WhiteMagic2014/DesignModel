package CardsLife;

import java.util.Random;

public class Game {
	enum status {
		diseaseA, diseaseB, diseaseC, nodisease
	}

	int money;
	int day;
	int hp;// 体力
	int sp;// 水
	int san;// 精神
	status mydisease;
	Warehouse myWarehouse;// 仓库
	Probability myProbability;// 数据类
	Random random = null;

	public Game() {
		money = 100;
		day = 1;
		hp = 100;
		sp = 100;
		san = 100;
		myWarehouse = new Warehouse();
		myProbability = new Probability();
		mydisease = status.nodisease;
		random = new Random();

		showinfo();
	}

	public void drawCard() {
		if (money >= 10) {
			money -= 10;

			float thisrandom = random.nextFloat() * myProbability.sum();

			if (thisrandom <= myProbability.C_B()) {
				// c
				myWarehouse.cardC++;
				san -= myProbability.sanDecrease;
				System.out.println("抽到了 C 卡");
			} else if (thisrandom > myProbability.C_B() && thisrandom <= myProbability.B_S()) {
				// b
				myWarehouse.cardB++;
				san -= myProbability.sanDecrease;
				System.out.println("抽到了 B 卡");

			} else if (thisrandom > myProbability.B_S() && thisrandom <= myProbability.S_R()) {
				// s
				myWarehouse.cardS++;
				san -= myProbability.sanDecrease;
				System.out.println("抽到了 S 卡");

			} else if (thisrandom > myProbability.S_R() && thisrandom <= myProbability.R_SR()) {
				// R
				myWarehouse.cardR++;
				san -= myProbability.sanDecrease;
				System.out.println("抽到了 R 卡");

			} else if (thisrandom > myProbability.R_SR() && thisrandom <= myProbability.SR_SSR()) {
				// SR
				myWarehouse.cardSR++;
				san -= myProbability.sanDecrease;
				System.out.println("抽到了 SR 卡");

			} else if (thisrandom > myProbability.SR_SSR() && thisrandom <= myProbability.SSR_FOOD()) {
				// SSR
				myWarehouse.cardSSR++;
				sanIncreaseDrawCard();
				System.out.println("！！！！！！SSR！！！！！！ ");

			} else if (thisrandom > myProbability.SSR_FOOD() && thisrandom <= myProbability.FOOD_WATER()) {
				// FOOD
				myWarehouse.cardfood++;
				sanIncreaseDrawCard();
				System.out.println("抽到了 食物 卡");

			} else if (thisrandom > myProbability.FOOD_WATER() && thisrandom <= myProbability.WATER_MA()) {
				// WATER
				myWarehouse.cardwater++;
				sanIncreaseDrawCard();
				System.out.println("抽到了 水 卡");

			} else if (thisrandom > myProbability.WATER_MA() && thisrandom <= myProbability.MA_MB()) {
				// MA
				myWarehouse.cardMedicineA++;
				sanIncreaseDrawCard();
				System.out.println("抽到了 MedicineA 卡");

			} else if (thisrandom > myProbability.MA_MB() && thisrandom <= myProbability.MB_MC()) {
				// MB
				myWarehouse.cardMedicineB++;
				sanIncreaseDrawCard();
				System.out.println("抽到了 MedicineB 卡");

			} else if (thisrandom > myProbability.MB_MC() && thisrandom <= myProbability.MC_SAN()) {
				// MC
				myWarehouse.cardMedicineC++;
				sanIncreaseDrawCard();
				System.out.println("抽到了 MedicineC 卡");

			} else if (thisrandom > myProbability.MC_SAN()) {
				// san
				myWarehouse.cardSAN++;
				sanIncreaseDrawCard();
				System.out.println("抽到了 镇静剂 卡");

			}

			showinfo();

		} else {
			System.out.println("没钱了，等明天再来吧");
		}
	}

	public void eat() {
		if (myWarehouse.getCardfood() > 0) {
			if (this.hp == 100) {
				System.out.println("体力已满，无需进食");
			} else {
				this.hp += myProbability.hpIncrease;
				myWarehouse.cardfood--;
				if (this.hp >= 100) {
					this.hp = 100;
				}
			}

		} else {
			System.out.println("没有食物卡");
		}
	}

	public void drink() {
		if (myWarehouse.getCardwater() > 0) {

			if (this.sp == 100) {
				System.out.println("水份已满，无需喝水");

			} else {
				this.sp += myProbability.spIncrease;
				myWarehouse.cardwater--;
				if (this.sp >= 100) {
					this.sp = 100;
				}
			}

		} else {
			System.out.println("没有水卡");
		}
	}

	public void clamdown() {
		if (myWarehouse.getCardSAN() > 0) {

			if (this.san == 100) {
				System.out.println("精神正常，无需镇静剂");

			} else {

				this.san += myProbability.sanIncreaseMedicine;
				myWarehouse.cardSAN--;

				if (this.san >= 100) {
					this.san = 100;
				}
			}

		} else {
			System.out.println("没有镇静剂");
		}
	}

	public void Treatment() {
		switch (mydisease) {
		case nodisease:
			System.out.println("没有疾病，无需治疗");
			break;

		case diseaseA:
			if (myWarehouse.getCardMedicineA() > 0) {
				mydisease = status.nodisease;
				myWarehouse.cardMedicineA--;
			} else {
				System.out.println("没有A疾病的药物");
			}
			break;

		case diseaseB:
			if (myWarehouse.getCardMedicineB() > 0) {
				mydisease = status.nodisease;
				myWarehouse.cardMedicineB--;
			} else {
				System.out.println("没有B疾病的药物");
			}
			break;

		case diseaseC:
			if (myWarehouse.getCardMedicineC() > 0) {
				mydisease = status.nodisease;
				myWarehouse.cardMedicineC--;
			} else {
				System.out.println("没有C疾病的药物");
			}
			break;
		}
	}

	public void nextday() {

		if (isAlive()) {
			this.day++;
			int moneyIncrease = 90 + random.nextInt(50);// 每天增量90-140元
			System.out.println("\n\n******************今天获得了 " + moneyIncrease + "元******************");
			this.money += moneyIncrease;

			if (mydisease.equals(status.nodisease)) {
				// 这应该根据疾病类型来增加消耗sp hp数值 暂时不细分

				if (random.nextInt(99) >= 90) {
					// 10%的几率生病
					switch (random.nextInt(2)) {
					case 0:
						mydisease = status.diseaseA;
						System.out.println("******************患上了diseaseA******************");
						break;

					case 1:
						mydisease = status.diseaseB;
						System.out.println("******************患上了diseaseB******************");

						break;

					case 2:
						mydisease = status.diseaseC;
						System.out.println("******************患上了diseaseC******************");

						break;
					}
				}

			} else {
				this.san -= myProbability.sanDecreaseDisease;// 有疾病睡觉继续减少san
			}

			this.hp -= myProbability.hpDecrease;
			this.sp -= myProbability.spDecrease;

			showinfo();

			if (myWarehouse.getCardSSR() == 100) {
				System.out.println("---YOU WIN---");
			} else {
				if (day == 365) {
					System.out.println("挑战失败");
					System.out.println("---GAME OVER---");
				}
			}

		} else {
			System.out.println("---GAME OVER---");
			if (hp <= 0) {
				System.out.println("死于饥饿");
			} else if (sp <= 0) {
				System.out.println("死于口渴");
			}
		}

	}

	public boolean isAlive() {

		if (hp > 0 && sp > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void sanIncreaseDrawCard() {
		san += myProbability.sanIncreaseDrawCard;
		if (san >= 100) {
			san = 100;
		}

	}

	public void showinfo() {
		System.out.println("——————————————————————————————————————————————————");
		System.out.println("金钱：" + money + "  天数：" + day + "  状态：" + mydisease.toString());
		System.out.println("体力：" + hp + "/100");
		System.out.println("水分：" + sp + "/100");
		System.out.println("精神：" + san + "/100");

		System.out.println("仓库：");
		System.out.println("C级卡 x " + myWarehouse.getCardC() + "   B级卡 x " + myWarehouse.getCardB() + "   S级卡 x "
				+ myWarehouse.getCardS());
		System.out.println("R级卡 x " + myWarehouse.getCardR() + "   SR级卡 x " + myWarehouse.getCardSR() + "   SSR级卡 x "
				+ myWarehouse.getCardSSR());
		System.out.println("抗感染卡 x " + myWarehouse.getCardMedicineA() + "   镇静剂卡 x " + myWarehouse.getCardSAN());
		System.out.println("维生素卡 x " + myWarehouse.getCardMedicineB() + "   抗生素卡 x " + myWarehouse.getCardMedicineC());
		System.out.println("食物卡 x " + myWarehouse.getCardfood() + "   水卡 x " + myWarehouse.getCardwater());
		System.out.println();
	}
}
