package com.coursera1.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


/**
 * Created by mamahendru on 3/17/16.
 */
public class TestNGSimpleTest {
    @Test
    public void testAdd() {
        String str = "TestNG is working fine";
        assertEquals("TestNG is working fine", str);
    }
}
