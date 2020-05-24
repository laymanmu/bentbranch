package net.laymanmu.bentbranch.actions;

import net.laymanmu.bentbranch.Context;
import net.laymanmu.bentbranch.Mob;
import net.laymanmu.bentbranch.ops.problems.ActionProblem;
import net.laymanmu.bentbranch.ops.problems.Problem;

/**
 * makes direct changes to Mob state
 */
public class MoveAction extends Action {

    public MoveAction(Mob actor) {
        super(Action.Name.Move, actor);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void update() {
    }

    @Override
    public boolean act(Context ctx) throws ActionProblem {
        if (!this.hasEnoughEnergy(ctx)) {
            return false;
        }
        if (ctx.targetPoint == null) {
            throw Problem.ActionInputInvalid(actor, name, "targetPoint", null);
        }

        ctx.energyDeltaLog.put(Context.EnergyEvent.Moved, this.cost*-1);
        actor.setPosition(ctx.targetPoint);
        return true;
    }
}
