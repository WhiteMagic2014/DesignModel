package testHearthStone.skills;

import testHearthStone.interfaces.*;

public class FlameBuster extends Skill {
	private int damage;

	public FlameBuster() {
		damage = 1;// 火冲1点伤害
		name = "火焰冲击";
	}

	@Override
	public void execute(Target obj) {
		System.out.println(name+" 对 "+obj.getname() +" 造成 " + damage + " 点伤害 ");
		obj.setHp(obj.getHp() - damage);
	}

}
