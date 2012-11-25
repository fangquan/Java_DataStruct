import java.util.Random;
/*
 * Implement an algorithm to find the kth to last element of a singly linked list 
 */

public class cup2_2 {
	public static void RandomList (LinkedList l, int size) {
		Random generator = new Random();
		int r;
		for (int i=0; i < size; i++) {
			r = generator.nextInt(size);
			l.Append(r);
		}
	}
		
	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		//Java does manipulate objects by reference, and all object variables are references.
		RandomList(l,20);
		Node p = l.nth_to_last(10);
		l.PrintList();
		System.out.println(p.element);
	}

}
