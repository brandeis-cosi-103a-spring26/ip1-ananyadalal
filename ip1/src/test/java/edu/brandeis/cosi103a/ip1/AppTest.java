package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class AppTest {

    @Test
    public void rollDieProducesValuesInRange() throws Exception {
        Method m = App.class.getDeclaredMethod("rollDie", Random.class);
        m.setAccessible(true);

        Random rng = new Random(12345L);
        for (int i = 0; i < 100; i++) {
            int val = (Integer) m.invoke(null, rng);
            assertTrue("rollDie produced out-of-range value: " + val, val >= 1 && val <= 6);
        }
    }

    @Test
    public void askRerollParsesYesAndNo() throws Exception {
        Method m = App.class.getDeclaredMethod("askReroll", Scanner.class, int.class);
        m.setAccessible(true);

        boolean yes = (Boolean) m.invoke(null, new Scanner("y\n"), 1);
        assertTrue(yes);

        boolean no = (Boolean) m.invoke(null, new Scanner("n\n"), 1);
        assertFalse(no);
    }

    @Test
    public void takeTurnAddsRollToScore_withoutRerolls() throws Exception {
        Method m = App.class.getDeclaredMethod("takeTurn", int.class, int.class, int[].class, Scanner.class,
                Random.class);
        m.setAccessible(true);

        // stub Random that always returns 3 (so die result is 4)
        Random stub = new Random() {
            private static final long serialVersionUID = 1L;

            @Override
            public int nextInt(int bound) {
                return 3; // die = 3 + 1 = 4
            }
        };

        int[] scores = new int[2];

        // capture stdout to avoid clutter
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            m.invoke(null, 0, 0, scores, new Scanner("n\n"), stub);
        } finally {
            System.setOut(oldOut);
        }

        assertEquals(4, scores[0]);
    }
}
