import java.util.Arrays;
public class Solution {
 static int table[][] = new int[201][201];
 public static int solution(int n) {
  int sum = 0;
  for (int i = 2; i <= 19; i++) {
   int temp = Q(n, i);
   if (temp != 0)
    sum += temp;
   else
    break;
  }
  return sum;
 }

 public static int Q(int n, int k) {
  if (n >= 1) {

   if (k == 1 && n == 1)
    return 1;

   if (k == 1)
    return 1;

   if (k == 0)
    return 0;

   if (n > k && k >= 1) {
    if (table[n - k][k] == 0)
     table[n - k][k] = Q(n - k, k);

    int x = table[n - k][k];

    if (table[n - k][k - 1] == 0)
     table[n - k][k - 1] = Q(n - k, k - 1);

    int y = table[n - k][k - 1];

    return x + y;
   } else
    return 0;
  } else {
   return 0;
  }
 }
}
