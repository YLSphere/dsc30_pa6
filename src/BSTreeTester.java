import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTreeTester {

    BSTree test;
    @Before
    public void setup() {
        test = new BSTree();
    }

    @Test
    public void getRoot() {
        assertEquals(null, test.getRoot());

        test.insert(42);
        assertEquals(42, test.getRoot().getKey());

        test.insert(13);
        assertEquals(42, test.getRoot().getKey());

    }

    @Test
    public void getSize() {
        assertEquals(0, test.getSize());

        test.insert(42);
        test.insert(12);
        test.insert(5);
        test.insert(60);
        assertEquals(4, test.getSize());

        test.insert(109);
        test.insert(75);
        test.insert(4);
        assertEquals(7, test.getSize());
    }

    @Test (expected = NullPointerException.class)
    public void insertEx() {
        test.insert(null);
    }
    @Test
    public void insert() {
        test.insert(42);
        test.insert(75);
        test.insert(4);
        assertEquals(42, test.getRoot().getKey());
        assertEquals(75, test.getRoot().getRight().getKey());
        assertEquals(4, test.getRoot().getLeft().getKey());
    }

    @Test (expected = NullPointerException.class)
    public void findKeyEx() {
        test.findKey(null);
    }

    @Test
    public void findKey() {
        test.insert(42);
        test.insert(75);
        test.insert(4);
        test.insert(13);
        assertTrue(test.findKey(42));
        assertTrue(test.findKey(13));
        assertFalse(test.findKey(2));

    }


    @Test (expected = NullPointerException.class)
    public void insertDataEx1() {
        test.insert(42);
        test.insertData(null, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void insertDataEx2() {
        test.insert(42);
        test.insertData(3, 1);
    }

    @Test
    public void insertData() {

        test.insert(42);
        test.insert(6);
        test.insert(75);
        test.insertData(42, "yote");
        test.insertData(6, "ya");
        test.insertData(6, "yeet");
        assertEquals("yote", test.findDataList(42).get(0));
        assertEquals("ya", test.findDataList(6).get(0));
        assertEquals("yeet", test.findDataList(6).get(1));

    }


    @Test (expected = NullPointerException.class)
    public void findDataListEx1() {
        test.insert(42);
        test.findDataList(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findDataListEx2() {
        test.insert(42);
        test.findDataList(3);
    }

    @Test
    public void findDataList() {
        test.insert(42);
        test.insert(6);
        test.insert(75);
        test.insertData(42, "yote");
        test.insertData(6, "ya");
        test.insertData(6, "yeet");
        assertEquals("yote", test.findDataList(42).get(0));
        assertEquals("ya", test.findDataList(6).get(0));
        assertEquals("yeet", test.findDataList(6).get(1));
    }

    @Test
    public void findHeight() {
        assertEquals(-1, test.findHeight());
        test.insert(42);
        test.insert(6);
        test.insert(75);
        assertEquals(1, test.findHeight());
        test.insert(76);
        assertEquals(2, test.findHeight());
        test.insert(74);
        test.insert(5);
        test.insert(7);
        test.insert(9);
        assertEquals(3, test.findHeight());

    }

    @Test
    public void leafCount() {
        assertEquals(0, test.leafCount());
        test.insert(42);
        assertEquals(1, test.leafCount());
        test.insert(6);
        test.insert(75);
        assertEquals(2, test.leafCount());
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorEx() {
        Iterator<Integer> iter = test.iterator();
        iter.next();
    }
    @Test
    public void iterator() {


        test.insert(8);
        test.insert(3);
        test.insert(10);
        test.insert(1);
        test.insert(6);
        test.insert(14);
        test.insert(4);
        test.insert(7);
        test.insert(13);

        Iterator<Integer> iter = test.iterator();
        assertTrue(iter.hasNext());

        assertEquals(new Integer(1), iter.next());
        iter.next();

        assertEquals(new Integer(4), iter.next());
        iter.next();
        iter.next();

        assertTrue(iter.hasNext());

        assertEquals(new Integer(8), iter.next());
        iter.next();
        iter.next();
        iter.next();

        assertFalse(iter.hasNext());

    }

    @Test
    public void intersection() {
        BSTree test2 = new BSTree();
        test2.insert(42);
        test2.insert(5);
        test2.insert(12);
        test2.insert(10);
        test2.insert(59);

        test.insert(42);
        test.insert(12);
        test.insert(55);
        test.insert(13);
        test.insert(11);
        test.insert(59);

        Iterator<Integer> iter = test.iterator();
        Iterator<Integer> iter1 = test2.iterator();
        ArrayList<Integer> arrayFinal = test.intersection(iter, iter1);

        assertEquals(new Integer(12), arrayFinal.get(0));
        assertEquals(new Integer(42), arrayFinal.get(1));
        assertEquals(new Integer(59), arrayFinal.get(2));

    }

    @Test
    public void levelCount() {
        test.insert(42);
        test.insert(12);
        test.insert(55);
        test.insert(13);
        test.insert(11);
        assertEquals(1, test.levelCount(0));
        assertEquals(2, test.levelCount(2));
        assertEquals(-1, test.levelCount(4));

    }
}