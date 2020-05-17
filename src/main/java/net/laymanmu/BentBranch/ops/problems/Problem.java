package net.laymanmu.BentBranch.ops.problems;

import net.laymanmu.BentBranch.Mob;
import net.laymanmu.BentBranch.Point;
import net.laymanmu.BentBranch.Resource;

public class Problem extends Exception {
    public final String name;
    public final String description;
    public final Object data;

    protected Problem(String name, String description, Object data) {
        this.name = name;
        this.description = description;
        this.data = data;
    }

    public static MoveProblem MoveFailed(Mob mob, Point to, String reason) {
        var name = "MoveFailed";
        var fmt  = "%s failed to move from %s to %s per %s";
        var desc = String.format(fmt, mob, mob.getPosition(), to, reason);
        return new MoveProblem(name, desc, null);
    }

    public static ResourceProblem ResourceOverConsumed(Mob consumer, Resource resource, int consumeAmount) {
        var name = "ResourceOverConsumed";
        var fmt  = "%s failed to consume %d from resource %s which only has %d";
        var desc = String.format(fmt, consumer, consumeAmount, resource, resource.getValue());
        return new ResourceProblem(name, desc, null);
    }
}
