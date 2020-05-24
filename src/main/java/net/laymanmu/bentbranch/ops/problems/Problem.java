package net.laymanmu.bentbranch.ops.problems;

import net.laymanmu.bentbranch.Mob;
import net.laymanmu.bentbranch.Point;
import net.laymanmu.bentbranch.Resource;
import net.laymanmu.bentbranch.actions.Action;

public class Problem extends Exception {
    enum Name {
        MoveFailed,
        ResourceOverConsumed, ResourceNotFound,
        ActionInputInvalid
    }

    public final Problem.Name name;
    public final String description;
    public final Object data;

    protected Problem(Problem.Name problemName, String description, Object data) {
        this.name = problemName;
        this.description = description;
        this.data = data;
    }

    public static MoveProblem MoveFailed(Mob mob, Point targetPosition, String reason) {
        var fmt  = "%s failed to move from %s to %s per %s";
        var desc = String.format(fmt, mob, mob.getPosition(), targetPosition, reason);
        return new MoveProblem(Problem.Name.MoveFailed, desc, null);
    }

    public static ResourceProblem ResourceOverConsumed(Mob consumer, Resource resource, int consumeAmount) {
        var fmt  = "%s failed to consume %d from resource %s which only has %d";
        var desc = String.format(fmt, consumer, consumeAmount, resource, resource.getValue());
        return new ResourceProblem(Problem.Name.ResourceOverConsumed, desc, null);
    }

    public static ResourceProblem ResourceNotFound(Mob owner, Resource.Name resourceName) {
        var fmt  = "%s failed to find resource %s";
        var desc = String.format(fmt, owner, resourceName);
        return new ResourceProblem(Problem.Name.ResourceNotFound, desc, null);
    }

    public static ActionProblem ActionInputInvalid(Mob actor, Action.Name actionName, String inputName, Object inputValue) {
        var fmt  = "%s failed to %s with input %s set to %s";
        var desc = String.format(fmt, actor, actionName, inputName, inputValue);
        return new ActionProblem(Problem.Name.ActionInputInvalid, desc, null);
    }
}
