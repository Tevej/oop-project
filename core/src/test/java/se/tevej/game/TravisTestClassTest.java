package se.tevej.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class TravisTestClassTest {
    @Test
    public void getThree() throws Exception {
        // Should pass!

        assertEquals(3, TravisTestClass.getThree());
        // Should not pass, here to see if gradle/travis is setup properly.
        assertEquals(4, TravisTestClass.getThree());
    }

}