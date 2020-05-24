package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.actions.Action;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActionTest {

    private Mob actor;

    @Before
    public void setup() {
        actor = new Mob("actor");
    }

    @Test
    public void testMoveActionSuccess() {
        var moveAction = actor.getAction(Action.Name.Move);
        Assert.assertNotNull("moveAction", moveAction);

        var origPosition = actor.getPosition();
        var nextPosition = origPosition.east();
        moveAction.act(nextPosition);
        Assert.assertEquals("nextPosition", nextPosition, actor.getPosition());

        nextPosition.northWest(2);
        moveAction.act(nextPosition);
        Assert.assertEquals("nextPosition", nextPosition, actor.getPosition());

        nextPosition.southEast(12);
        moveAction.act(nextPosition);
        Assert.assertEquals("nextPosition", nextPosition, actor.getPosition());
    }

}
