package WhiteBlack.Characters;

import WhiteBlack.WBStatus;
import WhiteBlack.Interface.TeamBuff;
import WhiteBlack.Workers.TeamBuffWorker;

public class ElementCreator {

	private static ElementCreator uniqueInstance;
	private TeamBuff teamWorker;

	private ElementCreator() {
	}

	public static ElementCreator getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new ElementCreator();
		}

		return uniqueInstance;
	}

	
	public Element createElement(int star, int atk ,int def,WBStatus wbstatus,int accumulator,int accumulatorSpeed,String name,String description) {
		
//		teamWorker = new TeamBuffWorker();
		
		Element element = new Element() {
			
			@Override
			void whiteSkill() {
//				teamWorker.teamAtkBuff(this.belongTeam, 1);
			}
			
			@Override
			void specialSkill() {
				
			}
			
			@Override
			void blackSkill() {
				
			}
		};
		
		element.setAccumulator(accumulator);
		element.setAccumulatorSpeed(accumulator);
		element.setAtk(atk);
		element.setDef(def);
		element.setStar(star);
		element.setWbstatus(wbstatus);
		element.setName(name);
		element.setDescription(description);
		
		
		return element;
	}

}
