package learnAlgorithm;

import java.util.Calendar;

public class main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int len = 2000000;
		int[] arr = new int[len];
		
		for (int i = 0; i < len; i++) {
			int num = (int)(Math.random()*10000);
			arr[i] =num;
		}
		
		Bubble bubble = new Bubble();
		Instert instert = new Instert();
		Select select = new Select();
		Quick quick =new Quick();
		
		
		Calendar calendar = Calendar.getInstance();
		
		System.out.println("before: "+calendar.getTime());
		
//		bubble.sort(arr);
//		select.sort(arr);
//		instert.sort(arr);
		quick.sort(0, arr.length-1, arr);
		
		
		calendar =Calendar.getInstance();
		
		System.out.println("after: "+calendar.getTime());

//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]+" ");
//		}
//		
	}
	
	
	
	
	

}


	
	

