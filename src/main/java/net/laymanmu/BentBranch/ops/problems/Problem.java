package net.laymanmu.BentBranch.ops.problems;

import net.laymanmu.BentBranch.Mob;
import net.laymanmu.BentBranch.Point;
import net.laymanmu.BentBranch.Resource;

public class Problem extends Exception {
    enum ProblemName {
        MoveFailed, ResourceOverConsumed
    }

    public final ProblemName name;
    public final String description;
    public final Object data;

    protected Problem(ProblemName problemName, String description, Object data) {
        this.name = problemName;
        this.description = description;
        this.data = data;
    }

    public static MoveProblem MoveFailed(Mob mob, Point targetPosition, String reason) {
        var fmt  = "%s failed to move from %s to %s per %s";
        var desc = String.format(fmt, mob, mob.getPosition(), targetPosition, reason);
        return new MoveProblem(ProblemName.MoveFailed, desc, null);
    }

    public static ResourceProblem ResourceOverConsumed(Mob consumer, Resource resource, int consumeAmount) {
        var fmt  = "%s failed to consume %d from resource %s which only has %d";
        var desc = String.format(fmt, consumer, consumeAmount, resource, resource.getValue());
        return new ResourceProblem(ProblemName.ResourceOverConsumed, desc, null);
    }
}
