package learnAlgorithm;

public class Bubble {
	
	/**
	 * @param arr
	 * 冒泡思路
	 * 假如有 1 2 3 4 5 五个数 
	 * 那么两两比较 大的往右走
	 * 比如 1 2 号位置的比较，大的放到2号位，然后 23位比较，大的放到3号位..一直45比较完成
	 * 以上第一轮走完，最大的数字在5号位，那么要排序的数字变成了 1234 四个
	 * 重复上面的操作，将1234中最大的数放在4号位；123中大的放在3号位...直至全部排序完成
	 */
//	冒泡排序 从小到大
	public void sort(int arr[]) {
		int temp = 0 ; //换位中间量
//		外循环，一共排多少次
		for (int i = 0; i < arr.length-1; i++) {
//			内循环，两两比较
			for (int j = 0; j < arr.length-1-i; j++) {
				if (arr[j]>arr[j+1]) {
					//换位
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		
	}
   
   
   
   

}
