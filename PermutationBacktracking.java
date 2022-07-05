package recursion;

import java.util.*;
/**
 * @author Animesh
 *
 */
public class PermutationBacktracking {
	int nc;
	int[] c;
	
	PermutationBacktracking(int n) {
		nc = 0;
		c = new int[n+1];
	}
	
	public static boolean isSolution(int[] a, int k, int n) {
		if(k != n) return false;
		for(int i = 1; i <= n; i++)
			if(a[i] == i) return false;
		return true;
	}
	
	public static void printSubset(int[] a, int k) {
		for(int i = 1; i <= k; i++) 
			System.out.print(a[i]+" ");
		System.out.println();
	}
	
	public static void constructCandidates(int[] a, int k, int n, int[] c, PermutationBacktracking obj) {
		boolean[] in_perm = new boolean[n+1];
		for(int i = 1; i <= n; i++) in_perm[i] = false;
		for(int i = 1; i < k; i++) in_perm[a[i]] = true;
		
		obj.nc = 0;
		for(int i = 1; i <= n; i++) {
			if(!in_perm[i]) {
				c[obj.nc] = i;
				obj.nc+=1;
			}
		}
	}
	
	public static void backtrack(int[] a, int k, int n) {
		PermutationBacktracking obj = new PermutationBacktracking(n);
		
		if(isSolution(a, k, n)) {
			printSubset(a, k);
		} else {
			k = k + 1;
			constructCandidates(a, k, n, obj.c, obj);
			for(int i = 0; i < obj.nc; i++) {
				a[k] = obj.c[i];
				backtrack(a, k, n);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of elements for permutation:");
		int n = sc.nextInt();
		int[] arr = new int[n+1];
		Arrays.fill(arr, 0);
		System.out.println("Derangements are as follows:");
		backtrack(arr, 0, n);
	}
}
