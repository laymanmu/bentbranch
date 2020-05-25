package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.events.AttackEvent;

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

    public AttackEvent attack(Mob attacker, Mob defender) {
        int baseAmount = roll(1,20);
        float deduced  = baseAmount / 10f;
        int amount     = Math.round(deduced * attacker.getStrength());
        defender.takeDamage(amount);
        return new AttackEvent(attacker.toString(), defender.toString(), amount);
    }
}
