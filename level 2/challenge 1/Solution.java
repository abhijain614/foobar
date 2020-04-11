public class Solution {
    public static String[] solution(String[] l) {
        QS(l,0,l.length-1);
        return l;
    }
    public static void QS(String a[],int p,int q)
  {
    if(p==q||p>q)
    return;
    int pivot=partition(a,p,q);
    QS(a,p,pivot-1);
    QS(a,pivot+1,q);
  }

  public static int partition(String []a,int p,int q)
  {
    int i=p;
    for(int j=i+1;j<=q;j++)
    {
      if(compare(a[j],a[p]))
      {
        continue;
      }
      else
      {
        i=i+1;
        //swap a[i] and a[j]
        String temp=a[i];
        a[i]=a[j];
        a[j]=temp;
      }
    }
    //swap a[p] and a[i]
    String temp=a[p];
    a[p]=a[i];
    a[i]=temp;
    return i;
  }

  public static boolean compare(String s1,String s2)
  {
    String []s1str = s1.split("[.]");
    String []s2str = s2.split("[.]");
    int bound=s1str.length>s2str.length?s2str.length:s1str.length;
    for(int i=0;i<bound;i++)
    {
      int m=Integer.parseInt(s1str[i]);
      int n=Integer.parseInt(s2str[i]);
      if(m>n)
      {
        return true;
      }
      else if(m<n)
      {
        return false;
      }
    }
    if(s1str.length==bound)
    return false;
    else
    return true;
  }
}
