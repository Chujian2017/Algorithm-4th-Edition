/**
Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.

For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.
*/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {

		private int[]id;//����id(�Դ�����Ϊ����)
		private int[]sz;
		private int count;
		private int[]max;
		public WeightedQuickUnionUF(int N)
		{//��ʼ������id����
			count=N;
			id=new int[N];
			for(int i=0;i<N;i++)
				id[i]=i;
			sz=new int[N];
			max=new int[N];
			for(int i=0;i<N;i++){
				sz[i]=1;
			    max[i]=0;
			}
		}
		public int find(int p){
			int temp=root(p);
			return max[temp];
			
		}
		public int count()
		{return count;}
		public boolean connected(int p ,int q)
		{return root(p)==root(q);}
		public int root(int p)
		{
			int temp=p;
			while(p!=id[p]) p=id[p];
			
			while(temp!=id[p]){
				int tempId=id[temp];
				id[temp] = id[p];
				temp = tempId;	}
				
			return id[p];
		}
		public void union(int p,int q)
		{
			int temp=0;
			int temp2=0;
			int temp3=0;
			int i=root(p);
			int j=root(q);
			if(i>j) temp=i;else temp=j;
			if(max[i]>max[j])temp2=max[i];else temp2=max[j];
			if(temp>temp2)temp3=temp;else temp3=temp2;
			if(sz[i]<sz[j]){
				id[i]=j;sz[i]+=sz[j];max[j]=temp3;
			}
			else {
				id[j]=i;sz[i]+=sz[j];max[i]=temp3;
			}
			count--;
		}
		public static void main(String[] args) {
			//�����StdIn�õ��Ķ�̬����������
			int N=StdIn.readInt();//��ȡ��������
			WeightedQuickUnionUF uf=new WeightedQuickUnionUF(N);//��ʼ��N������
			while(!StdIn.isEmpty())
			{
				int p=StdIn.readInt();
				int q=StdIn.readInt();//��ȡ������
				if(uf.connected(p, q)) continue;//����Ѿ���ͨ�����
				uf.union(p, q);
				StdOut.println(p+" "+q);
			}
			StdOut.println(uf.count()+"components");
			StdOut.println(uf.find(5));
		}
		}

