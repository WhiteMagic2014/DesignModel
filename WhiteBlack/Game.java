package WhiteBlack;

import WhiteBlack.Characters.Element;
import WhiteBlack.Characters.TestElement;

public class Game {
	
	
	
	public static void main(String[] args) {

		Element aa = new TestElement();
		aa.setName("aa");
		Element bb = new TestElement();
		bb.setName("bb");
		Element cc = new TestElement();
		cc.setName("cc");
		
		Team team = new Team(3);
		
		team.addMember(aa);
		team.addMember(bb);
		team.addMember(cc);
		
		team.refreshAtkDef();
		
		aa.changeStatue();
		team.refreshAtkDef();

		aa.changeStatue();	
		team.refreshAtkDef();

		aa.changeStatue();	

		
		team.refreshAtkDef();

	}
	
	
	
	
	

}
