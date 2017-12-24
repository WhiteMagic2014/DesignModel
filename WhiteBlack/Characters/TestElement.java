package WhiteBlack.Characters;

import WhiteBlack.WBStatus;
import WhiteBlack.Interface.TeamBuff;
import WhiteBlack.Workers.TeamBuffWorker;

public class TestElement extends Element {

	TeamBuff teamWorker;
	

	public TestElement() {
		star = 2;
		atk = 1;
		def = 1;
		wbstatus= WBStatus.white;
		accumulator = 3;
		accumulatorSpeed = 1;
		name = "测试用角色";
		description = "一个基本的测试用角色";
		
		teamWorker = new TeamBuffWorker();
		
	}

	@Override
	void whiteSkill() {
		//全队buff-全队攻击力上升-1
		System.out.println("技能白释放——");		
		teamWorker.teamAtkBuff(belongTeam,1);
	}

	@Override
	void blackSkill() {
		System.out.println("技能黑释放——");

		// TODO Auto-generated method stub

	}

	@Override
	void specialSkill() {
		// TODO Auto-generated method stub

	}

}
