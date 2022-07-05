package recursion;

import java.util.*;
/**
 * @author Animesh
 *
 */
public class AllSubsets {
		int nc;
		int[] c;
		
		AllSubsets() {
			nc = 0;
			c = new int[2];
		}
		
		public static boolean isSolution(int[] a, int k, int[] arr) {
			return k == a.length-1;
		}
			
		public static void constructCandidates(int[] a, int k, AllSubsets obj) {
			obj.c[0] = 0;
			obj.c[1] = 1;
			obj.nc = 2;
		}
		
		public static void printSubset(int[] a, int k, int[] arr) {
			System.out.print("{ ");
			StringBuilder str = new StringBuilder();
			for(int i = 0; i <= k; i++) {
				if(a[i] == 1 && str.length() == 0) {
					str.append(arr[i]);
				} else if(a[i] == 1 && str.length() >= 1) {
					str.append(", "+arr[i]);
				}
			}
			System.out.print(str+" }\n");
		}
		
		public static void backtrack(int[] a, int k, int[] arr) {
			AllSubsets obj = new AllSubsets();
			if(isSolution(a, k, arr)) {
				printSubset(a, k, arr);
			} else {
				k = k + 1;
				if(k == arr.length) return;
				constructCandidates(a, k, obj);
				for(int i = 0; i < obj.nc; i++) {
					a[k] = obj.c[i];
					backtrack(a, k, arr);
				}
			}
		}
		
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("Enter number of elements for subset generation: ");
			int n = sc.nextInt();
			
			int[] arr = new int[n+1];
			int[] a = new int[n+1];
			Arrays.fill(a, 0);
			
			System.out.println("Enter all numbers for the collection: ");
			arr[0] = 0;
			for(int i = 1; i <= n; i++) {
				arr[i] = sc.nextInt();
			}
			
			backtrack(a, 0, arr);
		}
	
}
