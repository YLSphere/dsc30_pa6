import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

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

    @Test
    public void iterator() {
    }

    @Test
    public void intersection() {
    }

    @Test
    public void levelCount() {
    }
}