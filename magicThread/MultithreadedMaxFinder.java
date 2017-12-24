package magicThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedMaxFinder {

	public static int max(int[] data) throws InterruptedException, ExecutionException {

		if (data.length == 1) {
			return data[0];
		} else if (data.length == 0) {
			throw new IllegalArgumentException();
		}

		FindMaxCallable task1 = new FindMaxCallable(data, 0, data.length / 2);
		FindMaxCallable task2 = new FindMaxCallable(data, data.length / 2, data.length);

		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> future1 = service.submit(task1);
		Future<Integer> future2 = service.submit(task2);

		service.shutdown();

		return Math.max(future1.get(), future2.get());

	}
	
	
	public static void main(String args[]){
		int len = 2000000;
		int[] arr = new int[len];
		
		for (int i = 0; i < len; i++) {
			int num = (int)(Math.random()*10000000);
			arr[i] =num;
		}
		
		try {
			System.out.println(max(arr));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
				
	}
	

}
