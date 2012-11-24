import java.util.LinkedList;


public class cup2_1 {

	static int Max_Value = 10;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList <String> list = new LinkedList<String>();
		
		for (int i=0; i<Max_Value;i++) {
			String r = Integer.toString((int) (Math.random()*Max_Value));
			list.add(r);
		}
		
		
		System.out.println(list);
		System.out.println(list);

	}

}
