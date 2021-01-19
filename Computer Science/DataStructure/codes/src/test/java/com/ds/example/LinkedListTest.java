package com.ds.example;

import com.ds.example.implementations.LinkedList;
import com.ds.example.interfaces.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    protected List<Integer> linkedList;

//    @BeforeAll
    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();

        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(22);
        linkedList.add(26);
        linkedList.add(9);
        linkedList.add(22);
    }

    @Test
    public void sizeTest() {
        Assertions.assertEquals(5, linkedList.size());
    }

    @Test
    public void indexOfTest(){
        Assertions.assertEquals(2, linkedList.indexOf(22));
    }

    @Test
    public void lastIndexOfTest(){
        Assertions.assertEquals(5, linkedList.lastIndexOf(22));
    }

}
