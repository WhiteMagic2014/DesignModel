package learnSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class PeopleManager_Map {

	private Map hm;
	
//	这里用 Map hm = new HashMap(); 其实用的是多态，map是一个接口，HashMap是实现了Map接口的类。

	public PeopleManager_Map() {
		hm = new HashMap<String, People>();  
	}

	/**
	 * @param id
	 * @param obj
	 * 存放时 存的是键值对
	 */
	public void addPeople(String id, People obj) {
		hm.put(id, obj);
	}

	
	
	/**
	 * @param id
	 * 相比较隔壁的arraylist 查询更加方便
	 */
	public void showInfo(String id) {
		if (hm.containsKey(id)) {
			People people = (People)hm.get(id);
			System.out.println("有"+ people.getName());
			
		}else {
			System.out.println("没有");
		}
	}
	
	
	/**
	 * HashMap的遍历无法像ArrayList那样使用for循环
	 * 因为他使用key来取值，而不像list那样有序取值
	 * 
	 * 这里使用到了迭代器 Iterator(java.util.Iterator)
	 * 同时 取值的时候 并非一定是有序的
	 * 
	 */
	public void showAll() {
		Iterator iterator = hm.keySet().iterator();
		
		//hasNext 返回一个boolen值 代表是否还有下一个
		while (iterator.hasNext()) {
			
			String key = iterator.next().toString();
			
			People people = (People)hm.get(key);
			System.out.println(people.getId() +" "+people.getName() +" "+ people.getAge()+" " + people.getSalary());
			
		}
	}

}
