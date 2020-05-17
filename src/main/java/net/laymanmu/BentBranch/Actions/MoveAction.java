package net.laymanmu.BentBranch.Actions;

import net.laymanmu.BentBranch.Mob;
import net.laymanmu.BentBranch.Point;

public class MoveAction implements Action {

    private Mob actor;

    public MoveAction(Mob actor) {
        this.actor = actor;
    }

    @Override
    public String getName() {
        return "move";
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public boolean act(Object input) {
        if (!(input instanceof Point)) {
            return false;
        }
        var position = (Point)input;
        actor.setPosition(position);
        return true;
    }

    @Override
    public void update() {
    }
}
