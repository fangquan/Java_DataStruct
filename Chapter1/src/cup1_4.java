import java.util.Arrays;

// Write a method to decide if 2 strings are anagrams or not
// Idea 1, just sort 2 strings


public class cup1_4 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(IsAnagram(s1,s2));
	}

	public static boolean IsAnagram (String s1, String s2) {
		char [] s1_char = s1.toCharArray();
		char [] s2_char = s2.toCharArray();
		Arrays.sort(s1_char);
		Arrays.sort(s2_char);
		return (Arrays.equals(s1_char, s2_char));
	}
}