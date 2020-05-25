package net.laymanmu.bentbranch.events;

public class DeathEvent extends Event {
    public final String name;
    public final String description;

    public DeathEvent(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
