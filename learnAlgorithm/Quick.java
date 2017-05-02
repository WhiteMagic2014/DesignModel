package learnAlgorithm;

public class Quick {

	
	/**
	 * 快排
	 * @param leftIndex
	 * @param rightIndex
	 * @param array
	 */
	public void sort(int leftIndex ,int rightIndex, int[] array) {
		
		int l = leftIndex;
		int r = rightIndex;
		int pivot = array[(leftIndex+rightIndex)/2];
		int temp = 0;
		
		while (l<r) {
			
			while (array[l]<pivot) l++;
			while (array[r]>pivot) r--;		
			if (l>=r) break;
			
			temp = array[l];
			array[l]=array[r];
			array[r]=temp;
			
			if (array[l]==pivot) --r;	
			if (array[r]==pivot) ++l;
		
		}
		
		if (l==r) {
			l++;
			r--;
		}
		
		if(leftIndex<r) sort(leftIndex, r, array);
		if(rightIndex>l) sort(l, rightIndex, array);
	}
}
