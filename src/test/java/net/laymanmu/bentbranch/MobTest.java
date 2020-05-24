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
        Assert.assertEquals("level", mob.getLevel(), 1);

        // resources:
        Assert.assertEquals("health", mob.getResourceValue(ResourceName.Health), 100);
        Assert.assertEquals("energy", mob.getResourceValue(ResourceName.Energy), 100);

        // attributes:
        Assert.assertEquals("dexterity", mob.getDexterity(), 10);
        Assert.assertEquals("intelligence", mob.getIntelligence(), 10);
        Assert.assertEquals("strength", mob.getStrength(), 10);
        Assert.assertEquals("constitution", mob.getConstitution(), 10);
    }

    @Test
    public void testMoving() {
        Point startPos = mob.getPosition();
        Point nextPos = startPos.northWest(4);
        Assert.assertEquals("start", startPos, mob.getPosition());
        Assert.assertNotEquals("diff", startPos, nextPos);
        mob.setPosition(nextPos);
        Assert.assertEquals("next", nextPos, mob.getPosition());
    }

    @Test
    public void testTargeting() {
        Point startTarget = mob.getTarget();
        Point nextTarget = new Point(33,44);
        Assert.assertEquals("start", startTarget, mob.getTarget());
        Assert.assertNotEquals("diff", startTarget, nextTarget);
        mob.setTarget(nextTarget);
        Assert.assertEquals("next", nextTarget, mob.getTarget());
    }

    @Test
    public void testResource() {
        Assert.assertEquals("start", 100, mob.health());
        mob.consume(ResourceName.Health, 50);
        Assert.assertEquals("half", 50, mob.health());
        mob.produce(ResourceName.Health, 20);
        Assert.assertEquals("heal", 70, mob.health());
        mob.produce(ResourceName.Health, 130);
        Assert.assertEquals("over heal", 100, mob.health());

        mob.consume(ResourceName.Health, 200);
    }

}
