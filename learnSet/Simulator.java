package learnSet;

import java.io.*;
import java.util.*;

public class Simulator {

	public static void main(String[] args) throws Exception {

		PeopleManager pManager = new PeopleManager();

		//
		// People p1 = new People("1","magic", 23,200);
		// People p2 = new People("2","panda",24,300);
		//
		//
		// //使用ArrayList
		// ArrayList arrayList1 = new ArrayList();
		// arrayList1.add(p1);
		// arrayList1.add(p2);
		// for (int i = 0; i < arrayList1.size(); i++) {
		// //添加进Arraylist中的一定是对象，将子类添加进父类object中时，会自动进行类型转换
		// //但是反过来 取出来的object无法自动转成子类，所以要强制类型转换
		//
		// People temp = (People)arrayList1.get(i);
		// System.out.println(temp.getName()+temp.getAge()+"sui");
		//
		// }

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("请选择操作");
			System.out.println("1:增加一个人员");
			System.out.println("2:根据id查询人员");
			System.out.println("3:根据id修改人员薪水");
			System.out.println("4:删除人员");
			System.out.println("5:退出系统");

			String operType = br.readLine();

			if (operType.equals("1")) {
				System.out.println("请输入编号");
				String id = br.readLine();

				System.out.println("请输入名字");
				String name = br.readLine();

				System.out.println("请输入年龄");
				int age = Integer.parseInt(br.readLine());

				System.out.println("请输入薪水");
				float salary = Float.parseFloat(br.readLine());

				People people = new People(id, name, age, salary);
				pManager.addPeople(people);

			} else if (operType.equals("2")) {
				System.out.println("请输入编号");
				String id = br.readLine();

				pManager.showInfo(id);

			} else if (operType.equals("3")) {
				System.out.println("请输入编号");
				String id = br.readLine();

				System.out.println("请输入薪水");
				float salary = Float.parseFloat(br.readLine());

				pManager.updateSalary(id, salary);

			} else if (operType.equals("4")) {
				System.out.println("请输入编号");
				String id = br.readLine();
				pManager.removePeople(id);
			} else if (operType.equals("5")) {
				// 退出系统
				System.exit(0);//状态码 0 为正常退出  非零表示异常
			}

		}

	}

}
