package datastructures;

import java.util.EmptyStackException;
import java.util.concurrent.ExecutionException;

public class BalancedSymbolChecker {
   private HANStack<Character> stack= new HANStack<Character>();
   public void insert(String insert){
       for(int i=0; i<insert.length();i++){
           char a= insert.charAt(i);
           if (a == ('(')||a == ('{')){
               stack.push(a);
           }
           if (a == (')')||a == ('}')){
               if(stack.getSize()==0){
                   throw new EmptyStackException();
               }else{
                   stack.pop();
               }
           }
       }
           if(stack.getSize()!=0){
               throw new IllegalArgumentException();
           }
   }
}
