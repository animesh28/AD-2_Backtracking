package recursion;

import java.util.*;
/**
 * @author Animesh
 *
 */
public class SubsetSumBacktracking {
	int nc;
	int[] c;
	
	SubsetSumBacktracking() {
		nc = 0;
		c = new int[2];
	}
	
	public static boolean isSolution(int[] a, int k, int sum, int[] arr) {
		int tmp = 0;
		for(int i = 0; i <= k; i++) {
			if(tmp > sum) return false;
			
			if(a[i] == 1)
				tmp += arr[i];
		}
		return tmp == sum;
	}
		
	public static void constructCandidates(int[] a, int k, int sum, SubsetSumBacktracking obj) {
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
	
	public static void backtrack(int[] a, int k, int sum, int[] arr) {
		SubsetSumBacktracking obj = new SubsetSumBacktracking();
		
		if(isSolution(a, k, sum, arr)) {
			printSubset(a, k, arr);
		} else {
			k = k + 1;
			if(k == a.length) return;
			constructCandidates(a, k, sum, obj);
			for(int i = 0; i < obj.nc; i++) {
				a[k] = obj.c[i];
				backtrack(a, k, sum, arr);
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
		arr[0] = 0;
		System.out.println("Enter all numbers for the collection: ");
		for(int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		
		System.out.print("Enter required sum: ");
		int sum = sc.nextInt();
		System.out.println("Required sum: "+sum);
		backtrack(a, 0, sum, arr);
	}
}
