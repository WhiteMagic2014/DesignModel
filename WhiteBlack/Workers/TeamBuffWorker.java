package WhiteBlack.Workers;

import WhiteBlack.Team;
import WhiteBlack.Interface.TeamBuff;

public class TeamBuffWorker implements TeamBuff{
	@Override
	public void teamAtkBuff(Team team ,int num ) {
		
		for (int i = 0; i < team.getMemberList().size(); i++) {
			team.getMemberList().get(i).setAtk(team.getMemberList().get(i).getAtk()+num);
		}
		
		System.out.println(this.getClass().getName().toString()+"实现接口释放 "+new Exception().getStackTrace()[0].getMethodName()+" 技能");
	}
	
	
	@Override
	public void teamDefBuff(Team team, int num) {
		for (int i = 0; i < team.getMemberList().size(); i++) {
			team.getMemberList().get(i).setDef(team.getMemberList().get(i).getDef()+num);
		}
		
		System.out.println(this.getClass().getName().toString()+"实现接口释放 "+new Exception().getStackTrace()[0].getMethodName()+" 技能");
	
	}
}
