package testHearthStone;

import testHearthStone.heros.Magian;

public class Simulator {

	public static void main(String[] args) {
		Magian me = new Magian("吉安娜");

		me.showHp();

		me.setObject(me);//设置目标
		
		me.useskill();//使用技能

		me.showHp();

		
		me.useskill();//使用技能
		
		me.showHp();


	}

}
