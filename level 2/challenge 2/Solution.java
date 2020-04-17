public class Solution {
    public static int k=1;
    public static int treeSize;
    public static int[] solution(int h, int[] q) {
        treeSize=(int)(Math.pow(2,h)-1);
        int listSize=q.length;
        int []tree=new int[treeSize];
        createTree(tree,0);
        int []sol=new int[listSize];
        for(int i=0;i<listSize;i++)
        {
            if(q[i]==7)
            {
                sol[i]=-1;
            }
            else
            {
                sol[i]=getParent(tree,q[i]);
            }
        }
        return sol;
    }

    public static int getParent(int []tree,int k)
    {
        for(int i=0;i<treeSize;i++)
        {
            if(tree[i]==k)
            return tree[((i-1)/2)];
        }
        return -1;
    }

    public static void createTree(int []tree, int ptr)
    {
        if(!((2*ptr)+1>treeSize-1))
        {
            createTree(tree,(2*ptr)+1);
            createTree(tree,(2*ptr)+2);
            tree[ptr]=k;
            k++;
        }
        else
        {
            tree[ptr]=k;
            k++;
        }
    }
}
