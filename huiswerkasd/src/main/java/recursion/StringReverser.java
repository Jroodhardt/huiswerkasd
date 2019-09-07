package recursion;

public class StringReverser {
    public String reverseNonRecusive(String input){
        String result="";
        for(int i = input.length() - 1; i >= 0; i--) {
            result = result + input.charAt(i); }
        return result;
    }

    public String reverseRecusive(String input){
        if(input==null||input.length()==1){
            return input;
        }
        return input.charAt(input.length()-1) +reverseRecusive(input.substring(0,input.length()-1));
    }
}
