package recursion;

public class BinaryReturner {
    public int returner(int n) {
         int aantal = 0;
            if (n % 2 == 1) {
                aantal++;
            return returner(n / 2 + 1);
            }else {
                return returner(n%2);
            }
    }
}