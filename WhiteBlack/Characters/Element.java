package WhiteBlack.Characters;

import WhiteBlack.Team;
import WhiteBlack.WBStatus;

public abstract class Element {

	public WBStatus wbstatus;
	public int star;// 阶级
	public int atk;
	public int def;
	public int accumulator;// 蓄力条
	public int accumulatorSpeed;// 蓄力条获取力
	public Team belongTeam;
	public String name;// 名字
	public String description;// 描述

	public Element() {

	}

	public void changeStatue() {

		int temp = 0;
		temp = this.atk;
		this.atk = this.def;
		this.def = temp;
		
		
		if (wbstatus.equals(WBStatus.white)) {
			System.out.println("触发效果-白");
			whiteSkill();
			this.wbstatus = WBStatus.black;
		} else {
			System.out.println("触发效果-黑");
			blackSkill();
			this.wbstatus = WBStatus.white;
		}
		
		accumulator += accumulatorSpeed;//聚齐槽增加
		
		belongTeam.refreshAtkDef();//team要刷新攻防
	}

	abstract void whiteSkill();

	abstract void blackSkill();

	abstract void specialSkill();

	public WBStatus getWbstatus() {
		return wbstatus;
	}

	public void setWbstatus(WBStatus wbstatus) {
		this.wbstatus = wbstatus;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
	public int getStar() {
		return star;
	}
	
	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getAccumulator() {
		return accumulator;
	}

	public void setAccumulator(int accumulator) {
		this.accumulator = accumulator;
	}

	public int getAccumulatorSpeed() {
		return accumulatorSpeed;
	}

	public void setAccumulatorSpeed(int accumulatorSpeed) {
		this.accumulatorSpeed = accumulatorSpeed;
	}

	public Team getBelongTeam() {
		return belongTeam;
	}

	public void setBelongTeam(Team belongTeam) {
		this.belongTeam = belongTeam;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
