package learnSet;

import java.util.*;

public class PeopleManager {

	private ArrayList<People> arrayList;

	public PeopleManager() {
		this.arrayList = new ArrayList<People>();
	}

	/**
	 * @param people
	 *            加入系统
	 */
	public void addPeople(People people) {
		arrayList.add(people);
	}

	/**
	 * @param id
	 *            查找
	 */
	public void showInfo(String id) {
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getId().equals(id)) {
				System.out.println(arrayList.get(i).getId() + "  " + arrayList.get(i).getName() + "  "
						+ arrayList.get(i).getAge()+"  "+ arrayList.get(i).getSalary());
			}
		}
	}

	/**
	 * @param idString
	 * @param salary
	 *            修改指定人员的薪水
	 */
	public void updateSalary(String idString, float salary) {
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getId().equals(idString)) {
				arrayList.get(i).setSalary(salary);
			}
		}
	}

	/**
	 * @param id  
	 *            移除
	 */
	public void removePeople(String id) {
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getId().equals(id)) {
				arrayList.remove(i);
			}
		}
	}

}
