package learnAlgorithm;

public class Instert {
/**
 * 插入排序
 * @param arr
 */
	public void sort(int arr[]){
		for (int i = 0; i < arr.length; i++) {
			
			int instertVal = arr[i];
			
			int index = i-1;
			
			while (index >= 0 && instertVal<arr[index]) {
				arr[index+1] = arr[index];
				index--;
			}
			
			arr[index+1]=instertVal;
		}
	}
}
