class Solution
{
  public static int solution(int []x,int []y)
  {
    int sumx=0, sumy=0;
    int lengthx=x.length;
    int lengthy=y.length;
    for(int i=0;i<lengthx;i++)
    {
      sumx+=x[i];
    }
    for(int i=0;i<lengthy;i++)
    {
      sumy+=y[i];
    }
    if(lengthx>lengthy)
    {
      return sumx-sumy;
    }
    else
    {
      return sumy-sumx;
    }
  }
}
