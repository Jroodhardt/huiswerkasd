package recursion;

public class Somfunctie {
     public int somRecursive(int n){
         if(n==1){
             return 1;
         }else{
             return n+ somRecursive(n-1);
         }
    }
     public int somNonRecursive(int n){
         int result=0;
         for(int i=1;i<=n;i++){
             result=i+result;
         }
         return result;
    }
}
