package learnAlgorithm;


public class Select {
	
//	选择排序
	/**
	 * @param arr
	 * 选择思路
	 * 假如有 1 2 3 4 5 五个数 
	 * 首先 假定1号位的数字是最小的
	 * 然后用1号位的数分别和 2345号比较 ，选出最小的那一个数字
	 * 最后把那x号位的数字与1号位的数交换
	 * 
	 * 然后 再2345号里，假定2号位的数字最小...同上
	 * 多次重复后完成排序
	 */
	public void sort(int arr[]) {
		
		int temp = 0;
		
//		最后一个数不用比，因为那时就他一个了
		for (int i = 0; i < arr.length-1; i++) {
			
//			先记录下最小的数，假设第一个是最小的
			int min = arr[i];
			int minIndex = i;
			
//			将记录下的数字，与他后面的数字开始比较
			for (int j = i+1; j < arr.length; j++) {
//				如果发现了比预设的数字还小的数字
				if (min>arr[j]) {
//					那么先把这个数字 和 他的位数记下来
					min = arr[j];
					minIndex = j;
				}
				
			}
			
//			出了这次循环，找到了这一批里面的最小值了
//			那么就将他和第i个交换
			
			temp = arr[i];
			arr[i]=arr[minIndex];
			arr[minIndex]=temp;
			
		}
		
	}
	
}
