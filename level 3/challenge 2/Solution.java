import java.math.BigInteger;
public class Solution
{
  static BigInteger m;
  static BigInteger f;
  public static String solution(String x, String y) {
     m = new BigInteger(x);
     f = new BigInteger(y);
     if(m.equals(BigInteger.ZERO)||f.equals(BigInteger.ZERO))
     return (new String("impossible"));

     BigInteger count = BigInteger.ZERO;
     if(m.subtract(f.multiply(m.divide(f))).compareTo(BigInteger.ZERO)==1&&f.subtract(m.multiply(f.divide(m))).compareTo(BigInteger.ZERO)==1)
     if(m.compareTo(f)==1)
     {
       count=count.add(m.divide(f));
       m=m.subtract(f.multiply(m.divide(f)));
     }
     else {
       count=count.add(f.divide(m));
       f=f.subtract(m.multiply(f.divide(m)));
     }

       String ans = findMinGenerations(m,f,count);
       if(ans==null)
       return (new String("impossible"));
       else
       return ans;
  }

  public static String findMinGenerations(BigInteger x, BigInteger y, BigInteger c) {
     if(!(x.compareTo(BigInteger.ZERO)==1||y.compareTo(BigInteger.ZERO)==1))
     return null;

     if(x.equals(BigInteger.ONE)&&y.equals(BigInteger.ONE))
     return c.toString();

     if(x.equals(y))
     return null;

     if(x.compareTo(y)==1)
     return findMinGenerations(x.subtract(y),y,c.add(BigInteger.ONE));
     else
     return findMinGenerations(x,y.subtract(x),c.add(BigInteger.ONE));
  }
}
