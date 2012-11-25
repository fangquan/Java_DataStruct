import java.util.Random;
public class cup2_1 {
	
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
		RandomList(l,10);
		l.PrintList();
		l.Removedup2();
		l.PrintList();
	}

}
