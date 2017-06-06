package learnSet;

public class People {
	private String id;
	private String name;
	private int age;
	private float salary;

	public People(String id, String name, int age, float salary) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;

	}

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public float getSalary() {
		return salary;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

}
