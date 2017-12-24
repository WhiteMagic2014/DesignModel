package CardsLife;

public class Probability {

	public float cardC;// 34.6
	public float cardB;// 19.4
	public float cardS;// 13
	public float cardR;// 9.6
	public float cardSR;// 6.4
	public float cardSSR;// 1.4
	public float cardfood;// 5.8
	public float cardwater;// 5.8
	public float cardMedicineA;// 1
	public float cardMedicineB;// 1
	public float cardMedicineC;// 1
	public float cardSAN;// 1

	public int hpIncrease;// 吃饭恢复体力
	public int hpDecrease;// 每天消耗体力
	public int spIncrease;// 喝水恢复体力
	public int spDecrease;// 每天消耗体力

	public int sanIncreaseSleep;// 每天睡觉恢复san
	public int sanIncreaseMedicine;// 吃药恢复san
	public int sanIncreaseDrawCard;// 抽到ssr/生存卡精神振奋
	public int sanDecrease;// 每次抽不到卡san减少
	public int sanDecreaseDisease;// 每次抽不到卡san减少

	public Probability() {
		cardC = 34.6f;
		cardB = 19.4f;
		cardS = 13f;
		cardR = 9.6f;
		cardSR = 6.4f;
		cardSSR = 1.4f;
		cardfood = 5.8f;
		cardwater = 5.8f;
		cardMedicineA = 1f;
		cardMedicineB = 1f;
		cardMedicineC = 1f;
		cardSAN = 1f;

		hpIncrease = 5;
		hpDecrease = 10;
		spIncrease = 5;
		spDecrease = 10;
		sanIncreaseSleep = 5;
		sanIncreaseDrawCard = 10;
		sanIncreaseMedicine = 10;
		sanDecrease = 1;
		sanDecreaseDisease = 10;
	}

	public float sum() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR + cardfood + cardwater + cardMedicineA + cardMedicineB
				+ cardMedicineC + cardSAN;
	}

	public float C_B() {
		return cardC;
	}

	public float B_S() {
		return cardC + cardB;
	}

	public float S_R() {
		return cardC + cardB + cardS;
	}

	public float R_SR() {
		return cardC + cardB + cardS + cardR;
	}

	public float SR_SSR() {
		return cardC + cardB + cardS + cardR + cardSR;
	}

	public float SSR_FOOD() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR;
	}

	public float FOOD_WATER() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR + cardfood;
	}

	public float WATER_MA() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR + cardfood + cardwater;
	}

	public float MA_MB() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR + cardfood + cardwater + cardMedicineA;
	}

	public float MB_MC() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR + cardfood + cardwater + cardMedicineA + cardMedicineB;
	}

	public float MC_SAN() {
		return cardC + cardB + cardS + cardR + cardSR + cardSSR + cardfood + cardwater + cardMedicineA + cardMedicineB
				+ cardMedicineC;
	}

}
