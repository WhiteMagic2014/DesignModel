package learnSet;

public class Simulator_map {

	public static void main(String[] args) {
		PeopleManager_Map pm = new PeopleManager_Map();
		
		 People p1 = new People("1","magic", 23,200);
		 People p2 = new People("2","panda",24,300);
		 
		 pm.addPeople(p1.getId(), p1);
		 pm.addPeople(p2.getId(), p2);
		 
//		 pm.showInfo("121");
		 pm.showAll();
		 
		 
		 
		 
		
	
		 
	}
	
	
	public static void magicss() {
		System.out.println("sss");
	}
	

}
