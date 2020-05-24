package net.laymanmu.bentbranch.actions;

import net.laymanmu.bentbranch.Context;
import net.laymanmu.bentbranch.Mob;
import net.laymanmu.bentbranch.ops.problems.ActionProblem;

public abstract class Action {
    public enum Name {
        None, Move
    }

    protected final Action.Name name;
    protected final Mob actor;
    protected int cost;

    public Action(Action.Name name, Mob actor) {
        this.name  = name;
        this.actor = actor;
        this.cost  = 1;
    }

    public Action.Name getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public boolean hasEnoughEnergy(Context ctx) {
        return ctx.getEnergyFinalBalance() >= cost;
    }

    abstract boolean isReady();
    abstract boolean act(Context context) throws ActionProblem;
    abstract void update();
}
