package com.ds.example;

import com.ds.example.implementations.AbstractList;
import com.ds.example.interfaces.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbstractListTest {
    protected List<Integer> abstractList;

//    @BeforeAll
    @BeforeEach
    void setUp() {
        abstractList = new AbstractList<Integer>();

        abstractList.add(2);
        abstractList.add(5);
        abstractList.add(22);
        abstractList.add(26);
        abstractList.add(9);
        abstractList.add(22);
    }

    @Test
    public void sizeTest() {
        Assertions.assertEquals(5, abstractList.size());
    }

    @Test
    public void indexOfTest(){
        Assertions.assertEquals(2, abstractList.indexOf(22));
    }

    @Test
    public void lastIndexOfTest(){
        Assertions.assertEquals(5, abstractList.lastIndexOf(22));
    }

}
