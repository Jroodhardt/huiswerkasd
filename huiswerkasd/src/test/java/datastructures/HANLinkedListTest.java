package datastructures;

import generics.Cirkel;
import generics.Shape;
import generics.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HANLinkedListTest {


    @Test
    void LinkedListAddFirstTest(){
        HANLinkedList<Shape> test = new HANLinkedList<Shape>();
        Shape cirkel=new Cirkel();
        Shape square=new Square();
        test.insertFirst(cirkel);
        test.insertFirst(square);

        assertEquals(square, test.getNode(0).getValue());
        assertEquals(cirkel, test.getNode(1).getValue());

    }
    @Test
    void LinkedListRemoveFirstTest() {
        HANLinkedList<Shape> test = new HANLinkedList<Shape>();
        Shape cirkel = new Cirkel();
        Shape square = new Square();
        test.insertFirst(cirkel);
        test.insertFirst(square);
        test.removeFirst();

        assertEquals(cirkel, test.getNode(0).getValue());

    }
}
