/**
Successor with delete. Given a set of N integers S={0,1,...,N?1} and a sequence of requests of the following form:

Remove x from S
Find the successor of x: the smallest y in S such that y¡Ýx.
design a data type so that all operations (except construction) should take logarithmic time or better.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Delete {
	public  List<Integer> delete(List<Integer> list,int[]N,int x){
		int n=BinarySearch.rank(x, N);
		list.remove(n);
		return list;	
	}
	public void find(int[]N,int x){
		int y=BinarySearch.rank(x,N);
		StdOut.println(N[y-1]);
	}
	public static void main(String[] args) {
		int[] whitelist =In.readInts("C:\\Users\\wen\\Desktop\\algs\\test.txt");
        Arrays.sort(whitelist);
		List<Integer> list=new ArrayList<>();
		for(Integer in:whitelist){
			list.add(in);
		}
		Delete de=new Delete();
		de.find(whitelist,10);
		for(Integer in:de.delete(list,whitelist,10)){
			System.out.println(in);
		}
		
	
	}

}
