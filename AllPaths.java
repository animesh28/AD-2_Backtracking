package recursion;

import java.util.*;
/**
 * @author Animesh
 *
 */
public class AllPaths {
	int nc;
	int[] c;
	
	AllPaths(int n) {
		nc = 0;
		c = new int[n+1];
	}
	
	static class Graph {
		int v;
		LinkedList<Integer>[] nbrs;
		
		Graph(int v) {
			this.v = v;
			this.nbrs = new LinkedList[v];
		}
		
		void addEdge(int x, int y, boolean undir) {
			if(nbrs[x] == null) nbrs[x] = new LinkedList<Integer>();
			
			nbrs[x].add(y);
			
			if(undir) {
				if(nbrs[y] == null) nbrs[y] = new LinkedList<Integer>();
				nbrs[y].add(x);
			}
		}
		
		LinkedList<Integer> getNbrs(int v) {
			int i = 0;
			for(LinkedList<Integer> ll: this.nbrs) {
				if(i == v) return ll;
				i++;
			}
			return null;
		}
	}
	
	public static void construct_candidates(int[] a, int k, int n, int s, AllPaths obj, Graph g) {
		boolean[] in_sol = new boolean[n+1];
		Arrays.fill(in_sol, false);
		for(int i = 1; i < k; i++) in_sol[a[i]] = true;
		
		if(k == 1) {
			obj.c[0] = s;
			obj.nc = 1;
		} else {
			obj.nc = 0;
			int last = a[k-1];
			LinkedList<Integer> ll = g.getNbrs(last);
			if(ll != null) {
				for(int x: ll) {
					if(!in_sol[x]) {
						obj.c[obj.nc] = x;
						obj.nc += 1;
					}
				}
			}
		}
	}
	
	public static void backtrack(int[] a, int k, int n, int s, int t, Graph g) {
		AllPaths obj = new AllPaths(n);
		if(isSolution(a, k, t)) {
			processSolution(a, k);
		} else {
			k = k + 1;
			construct_candidates(a, k, n, s, obj, g);
			for(int i = 0; i < obj.nc; i++) {
				a[k] = obj.c[i];
				backtrack(a, k, n, s, t, g);
			}
		}
	}
	
	public static boolean isSolution(int[] a, int k, int t) {
		return a[k] == t;
	}
	
	public static void processSolution(int[] a, int k) {
		for(int i = 1; i <= k; i++) {
			String str = " -> ";
			if(i == k) str = "";
			System.out.print(a[i]+str);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices in graph: ");
		int n = sc.nextInt();
		
		System.out.print("Enter number of edges in graph: ");
		int e = sc.nextInt();
		
		Graph g = new Graph(n+1);
		
		System.out.print("Directed(n) || Undirected(y): ");
		boolean undir = sc.next().charAt(0) == 'y';
		
		for(int i = 0; i < e; i++) {
			System.out.print("Enter start vertex and end vertex for edge "+(i+1)+": ");
			int s = sc.nextInt();
			int t = sc.nextInt();
			
			g.addEdge(s, t, undir);
		}
		
		int[] a = new int[g.v];
		System.out.print("Enter source for path: ");
		int src = sc.nextInt();
		System.out.print("Enter destination for path: ");
		int dest = sc.nextInt();
		
		backtrack(a, 0, n, src, dest, g);
	}

}
