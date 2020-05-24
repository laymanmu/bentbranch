package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.actions.Action;
import net.laymanmu.bentbranch.ops.problems.MoveProblem;
import net.laymanmu.bentbranch.ops.problems.Problem;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

public class MobTest {

    private Mob mob;

    @Before
    public void setup() {
        mob = new Mob("mob");
    }

    @Test
    public void testDefaults() throws Problem {
        // identifiers:
        Assert.assertTrue("id", mob.getId().length() > 0);
        Assert.assertTrue("name", mob.getName().length() > 0);

        // progression:
        Assert.assertEquals("experience", mob.getExperience(), 0);
        Assert.assertEquals("level", mob.getLevel(), 0);

        // resources:
        Assert.assertEquals("health", mob.getResourceValue(Resource.Name.Health), 100);

        // attributes:
        Assert.assertEquals("dexterity", mob.getDexterity(), 10);
        Assert.assertEquals("intelligence", mob.getIntelligence(), 10);
        Assert.assertEquals("strength", mob.getStrength(), 10);
        Assert.assertEquals("constitution", mob.getConstitution(), 10);

        // actions:
        Assert.assertEquals("actions has 1", mob.getActions().size(), 1);
        Assert.assertNotNull("move action exists", mob.getActions().get(Action.Name.Move));
    }

    @Test
    public void testMiddleware() throws Problem {
        HashMap<String,String> state = new HashMap<>();

        mob.addMiddleware(s -> {
            s.put("hello", "h");
        });
        mob.addMiddleware(s -> {
            s.put("hello", s.get("hello")+"e");
        });
        mob.addMiddleware(s -> {
            s.put("hello", s.get("hello")+"l");
        });
        mob.addMiddleware(s -> {
            s.put("hello", s.get("hello")+"l");
        });
        mob.addMiddleware(s -> {
            s.put("hello", s.get("hello")+"o");
        });

        Assert.assertEquals("before", null, state.get("hello"));
        mob.update(state);
        Assert.assertEquals("after", "hello", state.get("hello"));
    }

    @Test
    public void testMoving() throws MoveProblem {

    }
}
