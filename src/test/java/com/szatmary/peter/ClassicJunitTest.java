package com.szatmary.peter;

import org.junit.Assert;
import org.junit.Test;

public class ClassicJunitTest {

    @Test
    public void run() {
        Assert.assertTrue("Hello Junit test".substring(0, 5).equals("Hello"));
    }

}
