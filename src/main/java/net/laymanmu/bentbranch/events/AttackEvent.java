package net.laymanmu.bentbranch.events;

public class AttackEvent extends Event {
    public final String attacker;
    public final String defender;
    public final int amount;

    public AttackEvent(String attacker, String defender, int amount) {
        this.attacker = attacker;
        this.defender = defender;
        this.amount   = amount;
    }
}
