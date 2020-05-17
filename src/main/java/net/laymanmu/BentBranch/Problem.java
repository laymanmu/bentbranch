package net.laymanmu.BentBranch;

public class Problem {
    public final String name;
    public final String description;
    public final Object data;

    private Problem(String name, String description, Object data) {
        this.name = name;
        this.description = description;
        this.data = data;
    }

    public static Problem MoveFailed(Mob mob, Point to, String reason) {
        var name = "MoveFailed";
        var desc = String.format("%s failed to move from %s to %s per %s", mob, mob.getPosition(), to, reason);
        return new Problem(name, desc, null);
    }
}
