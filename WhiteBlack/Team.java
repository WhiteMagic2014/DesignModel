package WhiteBlack;

import java.util.ArrayList;

import WhiteBlack.Characters.Element;

public class Team {

	private ArrayList<Element> memberList;

	private int memberNum;// 队伍人数
	private int hp;
	private int atk;
	private int def;

	public Team(int maxnum) {
		this.memberNum = maxnum;
		hp = memberNum * 10;
		atk = 0;
		def = 0;
		memberList = new ArrayList<Element>();
	}

	public void addMember(Element obj) {
		obj.setBelongTeam(this);// 在元素入队的时候，将本队的实例给元素，以此可以来寻找某一元素的队友（由元素找到队伍，然后返回
								// 队伍里所有的元素）
		memberList.add(obj);


	}

	public void removeMember(int i) {
		memberList.remove(i);
	}

	public void refreshAtkDef() {
		this.atk = 0;
		this.def = 0;
		for (int i = 0; i < memberList.size(); i++) {
			this.atk += memberList.get(i).getAtk();
			this.def += memberList.get(i).getDef();
		}
		
		System.out.println("atk = "+this.atk +" def = "+this.def);
	}

	public boolean ifSameCorlor() {

		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(0).wbstatus != memberList.get(i).wbstatus) {
				return false;
			}
		}
		return true;
	}

	public void showall() {
		for (int i = 0; i < memberList.size(); i++) {
			System.out.println(memberList.get(i).getName());
		}
		
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
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
	
	public ArrayList<Element> getMemberList() {
		return memberList;
	}

}
