package testHearthStone.skills;

import testHearthStone.interfaces.Target;

public abstract class Skill {
	public int cost;// 技能费用，一般来说2费
	public String name;

	public Skill() {
		this.cost = 2;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	
	public abstract void execute(Target obj);
}
