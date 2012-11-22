
public class cup1_2 {
	/**
	 * Write code to reverse a C-Style String  
	 * C-String means that "abcdefghijk" is represented as five characters, including the null character.
	 * 
	 * Idea:
	 * Sawp the head and tail, iteratively  
	 * 
	 */
	public static void main(String[] args) 
	{	
		String a = "abcdefghijklmn\0";
		String b = "abc\0";
		System.out.println(a);
		System.out.println(Reverse(a));
		
		System.out.println(b);
		System.out.println(Reverse(b));
		
	}
	
	public static String Reverse (String a)
	{
		
		char arr[] = a.toCharArray();
		int head = 0;
		int tail = a.length()-2;
		
		while ( head < tail)
		{
			char tmp;
			tmp = arr[head];
			arr[head] = arr[tail];
			arr[tail] = tmp;
			head ++;
			tail --;
		}
		String str = new String(arr);
		return str;
	}

	

}
