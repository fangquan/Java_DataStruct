/*
 * Given an image representing in NxN matrix, write a method to rotate the image by 90 degrees.
 * Work in place.
 * The idea is, in clockwise direction, pixel in (i,j) will be rotated to (j,N-i), and is copied from (N-j,i);
 * in counterclockwise direction, pixel in (i,j) will be rotated to (N-j,i), and is copied from (j,N-i)
 * 
 * */

public class cup1_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 10;
		int [][] array = new int [N][N];
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				array[i][j] = N*i+j;
			}
		}
		PrintMatrix(array);
		ClockWiseRotate(array);
		PrintMatrix(array);

	}
	
	public static void PrintMatrix(int [][] array) {
		for (int i=0;i<array.length;i++) {
			for (int j=0;j<array.length;j++) {
				System.out.print("\t"+array[i][j]);
			}
			System.out.println();
		}
		System.out.println("=============");		
	}
	public static void ClockWiseRotate(int [][] array) {
		int N = array.length;
		for (int layers=0; layers<=N/2; layers++) {
			// Keep the last bit
			for (int y = layers;y < N-1-layers;y++) { 
				// save top
				int top = array[layers][y];
				// left to top
				array[layers][y] =  array[N-1-y][layers];
				// bottom to left
				array[N-1-y][layers] = array[N-1-layers][N-1-y];
				// right to bottom
				array[N-1-layers][N-1-y] = array[y][N-1-layers];
				// top to right
				array[y][N-1-layers] = top;	
				}
			}
		}
}
