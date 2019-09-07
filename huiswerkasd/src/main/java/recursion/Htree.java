package recursion;

import javax.swing.*;

import static java.lang.StrictMath.sqrt;

public class Htree extends JFrame {


    public static void draw(int depth, double x, double y, double size) {
        if (depth == 0) return;
        double x0 = x - size/2;
        double x1 = x + size/2;
        double y0 = y - size/2;
        double y1 = y + size/2;

        StdDraw.line(x0,y,x1,y);//conn
        StdDraw.line(x0,y0,x0,y1);//left
        StdDraw.line(x1,y0,x1,y1);//right

        draw(depth-1,x0,y0,size/2);
        draw(depth-1,x1,y1,size/2);
        draw(depth-1,x1,y0,size/2);
        draw(depth-1,x0,y1,size/2);
    }


    public static void main(String[] args) {
        int n = 5;
        double x = 0.5, y = 0.5;
        double size = 0.5;
        draw(n, x, y, size);
    }

}
