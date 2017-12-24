package testHearthStone.heros;

import testHearthStone.interfaces.*;
import testHearthStone.skills.*;

public abstract class Hero implements Equip, Target {
	int hp;
	Skill skillObject;
	String name;
	int crystalEmpty;// 空水晶
	int crystal;// 水晶
	int times;// 技能使用次数，一般来说一回合一次
	boolean ifcanuse;// 使用次数不为0就可以继续使用
	Target object;

	public Hero() {
		this.hp = 30;// 初始血量为30
		this.times = 1;
		ifcanuse = true;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getTimes() {
		return times;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void showHp() {
		System.out.println(name + "当前生命值为 " + hp + " 点");
	}

	public void useskill() {
		if (judgeCanUse()) {
			System.out.println(name + "使用技能：" + skillObject.name);
			skillObject.execute(object);
			times--;
		} else {
			System.out.println("本回合无法再次使用");
		}

	}

	public Boolean judgeCanUse() {
		if (times < 1) {
			ifcanuse = false;
		} else if (times >= 1) {
			ifcanuse = true;
		}

		return ifcanuse;
	}

	public void setObject(Target object) {
		this.object = object;
	}

	@Override
	public String getname() {
		return this.name;
	}

}
