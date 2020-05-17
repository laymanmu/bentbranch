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
}
