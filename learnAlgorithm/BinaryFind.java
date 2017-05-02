package learnAlgorithm;

/**
 * 二分法查找
 * 
 * @author chenhaoyu
 *
 */
public class BinaryFind {

	public void find(int leftIndex, int rightIndex, int arr[], int val) {
		// 首先找到中间数
		int midIndex = (rightIndex + leftIndex) / 2;
		int midVal = arr[midIndex];

		if (rightIndex >= leftIndex) {
			// 如果要找的数比midVal大
			if (midVal > val) {
				// 在arr左边找
				find(leftIndex, midIndex - 1, arr, val);
			} else if (midVal < val) {
				// 在arr右边找
				find(midIndex + 1, rightIndex, arr, val);
			} else if (midVal == val) {
				System.out.println("找到了，在第" + (midIndex + 1) + "个");
			}
		}else {
			System.out.println("找不到");
		}

	}
}
