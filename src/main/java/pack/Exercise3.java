package pack;

import java.util.PriorityQueue;
import java.util.Random;

//Generate 500 random numbers and create a method to print the nth smallest number in a programming language of your choice.

public class Exercise3 {

	int[] generateRandomNo() {
		int n=500;
		Random rnum = new Random();
		int counter;
		int[] numbers = new int[n]; 
		numbers[0] = rnum.nextInt(n); 
		for (counter = 1; counter < n; counter++) {
			numbers[counter] = rnum.nextInt(1000); 
		}
		return numbers;

	}
	
	//using heap to find the nth smallest no
	int findNthSmallest(int[] nums, int n) {

		PriorityQueue<Integer> q = new PriorityQueue<Integer>((o1,o2)->o2-o1);
		for (int i : nums) {
			q.offer(i);

			if (q.size() > n) {
				q.poll();
			}
		}

		return q.peek();

	}
	
	public static void main(String[] args) {
		Exercise3 exercise3=new Exercise3();
		
		int[] nums=exercise3.generateRandomNo();
		System.out.println(exercise3.findNthSmallest(nums, 3));
	}
}
