package se.tevej.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class TravisTestClassTest {
    @Test
    public void getThree() throws Exception {
        // Should pass!
        assertEquals(3, TravisTestClass.getThree());
    }

}