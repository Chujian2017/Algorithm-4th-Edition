/**
Social network connectivity. Given a social network containing n members and a log file containing m timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or better and use extra space proportional to n.

Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
*/


/**
                                       NL API
 * public class NL
 * ************************************************************************************
 * NL(int n)                           initialize the social network containing numbers with n objects(0 to N-1)
 * void form(int p,int q)              formed friendship between p and q
 * boolean connected(int p,int q)      are p and q friend?
 * boolean allConnected()              are all objects friend?
 * int find(int p)                     component identifier for p (0 to n¨C1)
 * int[] re()                          return array
 * Stack<String> sta()                 return Stack<String>
 ***************************************************************************************/
public class NL {
	private int []id;
	private int []sz;
	Date date=new Date();
	Stack<String> qu=new Stack<>();
	public NL(int n){
		id=new int[n];
		sz=new int[n];
		for(int i=0;i<n;i++){
			id[i]=i;
			sz[i]=1;
		}
	}
	public int[] re(){
		return id;
	}
	public Stack<String> sta(){
		return qu;
	}
	public boolean connected(int p ,int q)
	{return find(p)==find(q);}
	public boolean allConnected(int[]id){
		int n=id.length;
		int cnt=0;
		for(int i=0;i<n;i++){
			if(BinarySearch.rank(id[i], id)>i)
			 cnt++; 
			 }
		return cnt>0;
	}
	public int find(int p){
		int temp=p;
		while(p!=id[p])p=id[p];
		
		while(temp!=id[p]){
			int tempId=id[temp];
			id[temp]=id[p];
			temp=tempId;
		}
		return id[p];
	}
	public void form(int p,int q)
	{
		int i=find(p);
		int j=find(q);
		if(sz[i]<sz[j]){id[i]=j;sz[i]+=sz[j];qu.push((date.toString()));}
		else {id[j]=i;sz[i]+=sz[j];qu.push(date.toString());}	
	}
	
	public static void main(String[] args) throws IOException {
		String s;
		int n=StdIn.readInt();
		NL nl=new NL(n);
		PrintWriter out=new PrintWriter(
				new BufferedWriter(new FileWriter("C:\\Users\\wen\\Desktop\\log.txt")));
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(nl.connected(p, q)) continue;
			nl.form(p, q);
			StdOut.println(p+" "+q);
		}
		if(nl.allConnected(nl.re())==true)
			StdOut.println(nl.sta().pop());
		
		for(String st:nl.qu)
			out.println(st);
		out.close();
	}
}