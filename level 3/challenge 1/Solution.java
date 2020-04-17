public class Solution {
    public static int tCount, ntCount, len;
    public static int []total;
    public static Fraction r[][];
    public static Fraction q[][];
    public static Fraction f[][];
    public static int[] solution(int[][] m) {
        if(m.length==1&&m[0].length==1)
        return new int[]{1,1};
        else
        {
        len = m.length;
        orderTheMatrix(m);
        r = createR_Matrix(m);
        q = createQ_Matrix(m);
        f = createF_Matrix(q,q.length);
        Fraction result[][] = multiplyMatrix(f,r);
        int []ans = new int[result[0].length+1];
        int lcm = findLCM(result);
        multiplyByConstant(result,new Fraction(lcm,1));
        int i;
        for(i=0;i<result[0].length;i++)
        {
          ans[i]=result[0][i].getNumerator();
        }
        ans[i]=lcm;
        return ans;
      }
    }

    public static int findLCM(Fraction [][]result) {
      int []arr = new int[result[0].length];
      for(int i=0;i<result[0].length;i++) {
        arr[i] = result[0][i].getDenominator();
      }
      int ans = arr[0];
      for (int i = 1; i < result[0].length; i++)
          ans = (((arr[i] * ans)) / (Fraction.calculateGCD(arr[i], ans)));
      return ans;
    }

    public static void orderTheMatrix(int [][]m) {
      total = new int[len];
      outer:
      for (int i=0; i<len; i++) {
        for (int j=0; j<len; j++) {
          if(m[i][j]!=0) {
            ntCount++;
            continue outer;
          }
          else {
            if(j==len-1)
            tCount++;
          }
        }
      }

      for (int i=0; i<len; i++) {
        for (int j=0; j<len; j++) {
          total[i]+=m[i][j];
        }
      }
    }

    public static Fraction[][] createR_Matrix(int [][]m) {
      Fraction [][]r = new Fraction[ntCount][tCount];
      int k=0, l=0;
      for(int i=0;i<len;i++)
      {
        if(total[i]!=0)
        {
          for(int j=0;j<len&&l<tCount;j++)
          {
            if(total[j]==0)
            {
              if(m[i][j]==0)
              r[k][l]= new Fraction(0,1);
              else
              r[k][l]= new Fraction(m[i][j],total[i]);
              l++;
            }
          }
          k++;
        }
        l=0;
      }
      return r;
    }

    public static Fraction[][] createQ_Matrix(int [][]m) {
      Fraction [][]q = new Fraction[ntCount][ntCount];
      int k=0, l=0;
      for(int i=0;i<len;i++)
      {
        if(total[i]!=0)
        {
          for(int j=0;j<len&&l<ntCount;j++)
          {
            if(total[j]!=0)
            {
              if(m[i][j]==0)
              q[k][l] = new Fraction(0,1);
              else
              q[k][l]= new Fraction(m[i][j],total[i]);
              l++;
            }
          }
          k++;
        }
        l=0;
      }
      return q;
    }

    public static Fraction[][] createF_Matrix(Fraction [][]q, int length) {
      Fraction [][]x = createIdentityMatrix(length);
      subtractMatrix(x,q,length);
      return invertMatrix(x);
    }

    public static Fraction[][] createIdentityMatrix(int l) {
      Fraction mat[][] = new Fraction[l][l];
      for (int j=0;j<l;j++) {
        for (int k=0;k<l;k++) {
          if(j==k) {
            mat[j][k] = new Fraction(1,1);
          }
          else {
            mat[j][k] = new Fraction(0,1);
          }
        }
      }
      return mat;
    }

    public static Fraction[][] multiplyMatrix(Fraction mat1[][],Fraction mat2[][]) {
        Fraction res[][]=new Fraction[mat1.length][mat2[0].length];
        for (int j=0;j<mat1.length;j++) {
          for (int k=0;k<mat2[0].length;k++) {
            res[j][k] = new Fraction(0,1);
          }
        }
          int i, j, k;
          for (i = 0; i < mat1.length; i++) {
              for (j = 0; j < mat2[0].length; j++) {
                  for (k = 0; k < mat2.length; k++){
                      res[i][j] = res[i][j].add(mat1[i][k].multiply(mat2[k][j]));
                  }
              }
          }
          return res;
      }

    public static void subtractMatrix(Fraction [][]x, Fraction [][]q, int len) {
      for (int j=0;j<len;j++ ) {
        for (int k=0;k<len;k++ ) {
          x[j][k]=x[j][k].subtract(q[j][k]);
        }
      }
    }

    public static Fraction[][] invertMatrix(Fraction [][]matrix) {
      return multiplyByConstant(transpose(cofactor(matrix)),new Fraction(1,1).divide(determinant(matrix)));
    }

    public static Fraction[][] multiplyByConstant(Fraction [][]matrix,Fraction f) {
      for (int j=0;j<matrix.length;j++ ) {
        for (int k=0;k<matrix[0].length;k++ ) {
          matrix[j][k]=matrix[j][k].multiply(f);
        }
      }
      return matrix;
    }

    public static Fraction[][] cofactor(Fraction matrix[][]) {

        Fraction [][]mat  = new Fraction[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length;i++) {
        for (int j=0; j<matrix[0].length;j++) {
        mat[i][j]=changeSign(i).multiply(changeSign(j)).multiply(determinant(createSubMatrix(matrix, i, j)));
        }
      }
    return mat;
   }

    public static Fraction determinant(Fraction matrix[][]) {
        if (matrix.length==1) {
    	     return matrix[0][0];
        }
        if (matrix.length==2) {
            return (matrix[0][0].multiply(matrix[1][1])).subtract( matrix[0][1].multiply(matrix[1][0]));
        }
        Fraction sum = new Fraction(0,1);
        for (int i=0; i<matrix.length; i++) {
            sum = sum.add(matrix[0][i].multiply(determinant(createSubMatrix(matrix, 0, i))).multiply(changeSign(i)));
        }
        return sum;
    }

     public static Fraction changeSign(int i) {
       if(i%2==0)
       return(new Fraction(1,1));
       else
       return(new Fraction(-1,1));
     }

     public static Fraction[][] createSubMatrix(Fraction matrix[][], int excluding_row, int excluding_col) {
      Fraction [][]mat = new Fraction[matrix.length-1][matrix[0].length-1];
      int r = -1;
      for (int i=0;i<matrix.length;i++) {
          if (i==excluding_row)
              continue;
              r++;
              int c = -1;
          for (int j=0;j<matrix[0].length;j++) {
              if (j==excluding_col)
                  continue;
              mat[r][++c]=matrix[i][j];
          }
      }
      return mat;
    }

    public static Fraction[][] transpose(Fraction [][]matrix) {
      Fraction [][]transposedMatrix = new Fraction[ntCount][ntCount];
      for (int i=0;i<ntCount;i++) {
          for (int j=0;j<ntCount;j++) {
              transposedMatrix[j][i]=matrix[i][j];
            }
          }
      return transposedMatrix;
    }
}

//Fraction class
class Fraction{
    int numerator;
    int denominator;

    public Fraction(int num,int den)
    {
      numerator=num;
      denominator=den;
      reduce();
    }

    public void reduce() {
	     int gcd = calculateGCD(numerator, denominator);
         numerator /= gcd;
  	     denominator /= gcd;
    }

    public static int calculateGCD(int numerator, int denominator) {
      if(numerator==0||denominator==0)
      return 1;
       if (numerator % denominator == 0) {
             return denominator;
        }
	  return calculateGCD(denominator, numerator % denominator);
	 }

   public Fraction add(Fraction fractionTwo) {
     int numer = (numerator * fractionTwo.getDenominator()) +
                           (fractionTwo.getNumerator() * denominator);
     int denr = denominator * fractionTwo.getDenominator();
     return new Fraction(numer, denr);
   }

   public Fraction subtract(Fraction fractionTwo) {
       int newNumerator = (numerator * fractionTwo.denominator) - (fractionTwo.numerator * denominator);
       int newDenominator = denominator * fractionTwo.denominator;
       Fraction result = new Fraction(newNumerator, newDenominator);
       return result;
   }

   public Fraction multiply(Fraction fractionTwo) {
     int newNumerator = numerator * fractionTwo.numerator;
     int newDenominator;
     if(newNumerator==0)
     newDenominator=1;
     else
     newDenominator = denominator * fractionTwo.denominator;
     Fraction result = new Fraction(newNumerator, newDenominator);
     return result;
   }

   public Fraction divide(Fraction fractionTwo) {
     int newNumerator = numerator * fractionTwo.getDenominator();
     int newDenominator = denominator * fractionTwo.numerator;
     Fraction result = new Fraction(newNumerator, newDenominator);
     return result;
   }

    public int getNumerator() {
	     return numerator;
    }

    public int getDenominator() {
	     return denominator;
    }
 }
