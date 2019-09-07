package recursion;

public class Faculteitsfunctie {
     public int factorialRecursive(int n){
         int result;
         if(n==1){
             return 1;
         }
         result = factorialRecursive(n-1)*n;
         return result;
    }
    public int factorialNonRecursive(int n){
        int sum=n;
        for(int i = n-1; i>1; i--){
            sum = sum*i;
        }
        return sum;
    }
}
