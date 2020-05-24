package net.laymanmu.bentbranch;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import net.laymanmu.bentbranch.Resource.ResourceName;

public class MobTest {

    private Mob mob;

    @Before
    public void setup() {
        mob = new Mob("mob");
    }

    @Test
    public void testDefaults() {
        // identifiers:
        Assert.assertTrue("id", mob.getId().length() > 0);
        Assert.assertTrue("name", mob.getName().length() > 0);

        // progression:
        Assert.assertEquals("experience", mob.getExperience(), 0);
        Assert.assertEquals("level", mob.getLevel(), 0);

        // resources:
        Assert.assertEquals("health", mob.getResourceValue(ResourceName.Health), 100);

        // attributes:
        Assert.assertEquals("dexterity", mob.getDexterity(), 10);
        Assert.assertEquals("intelligence", mob.getIntelligence(), 10);
        Assert.assertEquals("strength", mob.getStrength(), 10);
        Assert.assertEquals("constitution", mob.getConstitution(), 10);
    }

}
