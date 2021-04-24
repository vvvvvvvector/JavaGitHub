package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringLinkedListTest {
    StringLinkedList testList;
    @BeforeEach
    void init(){
        testList =new StringLinkedList();
    }

    @Test
    void singleValuePrependTest() {
        testList.prepend("2");
        testList.prepend("1");
        Assertions.assertEquals("1",testList.first());
    }

    @Test
    void multipleValuePrependTest() {
        testList.prepend("1");
        testList.prepend("2");
        Assertions.assertEquals("2",testList.first());
    }

    @Test
    void firstThrowsIndexOutOfBoundsException () {
        Assertions.assertThrows( IndexOutOfBoundsException.class,testList::first);
    }

    @Test
    void removeFirstThrowsIndexOutOfBoundsException () {
        Assertions.assertThrows( IndexOutOfBoundsException.class,testList::removeFirst);
    }


    @Test
    void removeFirstTest() {
        testList.prepend("1");
        testList.prepend("2");
        testList.removeFirst();
        Assertions.assertEquals("1",testList.first());
    }

    //Zadanie 4
    @Test
    void appendTestFirst(){
        testList.append("1");
        testList.append("2");
        testList.append("3");
        Assertions.assertEquals("1",testList.first());
    }
    @Test
    void appendTestLast(){
        testList.append("1");
        testList.append("2");
        testList.append("3");
        Assertions.assertEquals("3",testList.last());
    }

     @Test
    void removeLastThrowsIndexOutOfBoundsException () {
        Assertions.assertThrows( IndexOutOfBoundsException.class,testList::removeLast);
    }
    @Test
    void removeLastTest() {
        testList.prepend("1");
        testList.prepend("2");
        testList.removeLast();
        Assertions.assertEquals("2",testList.last());
    }
    @Test
    void getTest() {
        testList.prepend("2");
        testList.prepend("1");
        Assertions.assertEquals("2",testList.get(1));
    }
    @Test
    void getThrowsIndexOutOfBoundsException () {
        testList.prepend("2");
        testList.prepend("1");
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->testList.get(5));
    }

    @Test
    void singleInsertTest() {
        testList.prepend("2");
        testList.insert("1",0);
        Assertions.assertEquals("2",testList.get(1));
    }
    @Test
    void multipleInsertTest() {
        testList.prepend("4");
        testList.prepend("3");
        testList.prepend("2");
        testList.prepend("1");
        testList.insert("1.1",1);
        Assertions.assertEquals("1.1",testList.get(1));
    }
    @Test
    void multipleInsertTest2() {
        testList.prepend("4");
        testList.prepend("3");
        testList.prepend("2");
        testList.prepend("1");
        testList.insert("1.1",1);
        Assertions.assertEquals("2",testList.get(2));
    }
    @Test
    void insertThrowsIndexOutOfBoundsException () {
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->testList.insert("5",10));
    }
    @Test
    void removeTest() {
        testList.prepend("2");
        testList.prepend("1");
        testList.remove(1);
        Assertions.assertEquals("2",testList.last());
    }
    @Test
    void removeThrowsIndexOutOfBoundsException () {
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->testList.remove(3));
    }

}