
/*
 * Design an algorithm to remove the duplicates in a string without using any additional buffer.
 * The idea is running 2 index, quick index and slow index. quick index is the out loop, scanning the whole array
 */
public class cup1_3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "adfadf";
		System.out.println(RemoveDuplicates(s));
		s = "abcd";
		System.out.println(RemoveDuplicates(s));
		s = "aaaa";
		System.out.println(RemoveDuplicates(s));
		s = "";
		System.out.println(RemoveDuplicates(s));
		s = "aaabbb";
		System.out.println(RemoveDuplicates(s));
	}
	
	public static char[] RemoveDuplicates(String s) {
		char [] s_array = s.toCharArray();
		if (s_array.length < 2) {
			return s_array;
		}
		int tail = 1;
		int len = s_array.length;
		for (int i=1; i<len;i++) {
			for (int j = 0; j < tail; j++) {
				if ( s_array[i] == s_array[j]) {
					break;
				}
				if (j==tail-1) {
					s_array[tail] = s_array[i];
					tail++;
				}
			}
		}
		char [] new_s_array = new char[tail];
		System.arraycopy(s_array,0,new_s_array,0,tail);
		return new_s_array;
	}
}
