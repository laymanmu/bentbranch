package net.laymanmu.BentBranch;

import org.junit.Test;
import org.junit.Assert;

public class LibraryTest {

    @Test
    public void testMobDefaults() {
        var library = new Library();
        var mob = library.getMob();
        Assert.assertNotNull(mob);

        // identifiers:
        Assert.assertTrue("id", mob.getId().length() > 0);
        Assert.assertTrue("name", mob.getName().length() > 0);

        // progression:
        Assert.assertEquals("experience", mob.getExperience(), 0);
        Assert.assertEquals("level", mob.getLevel(), 0);

        // resources:
        Assert.assertEquals("health", mob.getHealth(), 100);

        // attributes:
        Assert.assertEquals("dexterity", mob.getDexterity(), 10);
        Assert.assertEquals("intelligence", mob.getIntelligence(), 10);
        Assert.assertEquals("strength", mob.getStrength(), 10);
        Assert.assertEquals("constitution", mob.getConstitution(), 10);

        // actions:
        Assert.assertEquals("actions", mob.getActions().size(), 1);
        Assert.assertEquals("move", mob.getActions().get("move").getName(), "move");
    }

    @Test
    public void testMoveAction() {
        var library = new Library();
        var mob = library.getMob();

        var moveAction = mob.getActions().get("move");
        Assert.assertNotNull("moveAction", moveAction);

        var origPosition = mob.getPosition();
        Assert.assertEquals("origPosition", origPosition, mob.getPosition());

        var nextPosition = origPosition.east();
        moveAction.act(nextPosition);
        Assert.assertEquals("nextPosition", nextPosition, mob.getPosition());
    }

}
