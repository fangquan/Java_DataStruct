/*
 *  Replace all spaces in a string with "%20" 
 *  The idea is to scan the CharArray once and count how many spaces
 *  Calculate the new size of char array.
*/

public class cup1_5 {
	public static void main(String[] args) {
		String s = "abc f  ";
		System.out.println(Replace_spaces(s));
	}
	
	public static String Replace_spaces (String s) {
		char[] char_array = s.toCharArray();
		int len = char_array.length;
		int num_spaces = 0;
		for (char ch : char_array) {
			if ( ch == ' ') {
				num_spaces ++;
			}
		}
		char [] new_char_array = new char[len+num_spaces*2];
		for (int i=0,j = 0; i<len; i++) {
			if (char_array[i] != ' ') {
				new_char_array[j] = char_array[i];
				j++;
			}
			else {
				new_char_array[j]   = '%';
				new_char_array[j+1] = '2';
				new_char_array[j+2] = '0';
				j += 3;
			}
		}
		s =new String(new_char_array);
		return s;
	}
}