package net.laymanmu.bentbranch;

import java.util.Random;

public class Dice {
    public final Random random;

    public Dice() {
        random = new Random();
    }

    public Dice(long seed) {
        random = new Random(seed);
    }

    public int roll(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public void attack(Mob attacker, Mob defender) {
        int amount = roll(1,20) + 100;
        System.out.println(attacker +" attacks "+ defender +" for "+ amount);
        defender.takeDamage(amount);
    }

}
