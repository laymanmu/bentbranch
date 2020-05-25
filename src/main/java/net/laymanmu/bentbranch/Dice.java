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
        int baseAttackRoll  = roll(1,20);
        int averageStrength = 10;
        int strengthDiff    = attacker.getStrength() - averageStrength;
        int baseAttack      = baseAttackRoll + strengthDiff;
        if (baseAttack < 1) {
            return new AttackEvent(attacker.toString(), defender.toString(), 0);
        }
        defender.takeDamage(baseAttack);
        return new AttackEvent(attacker.toString(), defender.toString(), baseAttack);
    }
}
