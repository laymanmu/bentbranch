package net.laymanmu.bentbranch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class DiceTest {

    private Dice dice;

    @Before
    public void setup() {
        dice = new Dice();
    }

    @Test
    public void testRoll() {
        HashMap<Integer,Integer> counts = new HashMap<>();
        for (int i=1; i<21; i++) {
            counts.put(i, 0);
        }

        int numRolls = 10000;
        for (int i=0; i<numRolls; i++) {
            int x = dice.roll(1,20);
            Assert.assertTrue("min", x>0);
            Assert.assertTrue("max", x<21);
            counts.put(x, counts.get(x)+1);
        }

        counts.keySet().forEach(key -> {
            int value = counts.get(key);
            var msg = String.format("d20 only rolled a \"%d\" %d times out of %d rolls", key, value, numRolls);
            Assert.assertTrue(msg, value > numRolls/25);
        });
    }
}
