package net.laymanmu.BentBranch;

import net.laymanmu.BentBranch.ops.problems.Problem;
import net.laymanmu.BentBranch.ops.problems.ResourceProblem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResourceTest {

    private Mob      consumer;
    private Resource health;
    private Resource energy;

    @Before
    public void setup() {
        consumer = new Mob("consumer");
        health = Resource.ResourceBuilder.resource()
                .withName("health")
                .withMax(100)
                .withMin(0)
                .withDelta(1)
                .withValue(100)
                .build();
        energy = Resource.ResourceBuilder.resource()
                .withName("energy")
                .withMax(100)
                .withMin(0)
                .withDelta(10)
                .withValue(100)
                .build();
    }

    @Test
    public void testDefaults() {
        Assert.assertEquals("name", "health", health.getName());
        Assert.assertEquals("max", 100, health.getMax());
        Assert.assertEquals("delta", 1, health.getDelta());
        Assert.assertEquals("min", 0, health.getMin());
        Assert.assertEquals("value", 100, health.getValue());
    }

    @Test
    public void testUpdate() throws Problem {
        var expect = 20;
        health.consume(consumer, 80);
        Assert.assertEquals("value", expect, health.getValue());
        for (int i=0; i<1000; i++) {
            health.update();
            if (expect < 100) {
                expect++;
            }
            Assert.assertEquals("value", expect, health.getValue());
        }
    }

    @Test
    public void testConsume() throws Problem {
        var consumeAmount = 40;
        energy.consume(consumer, consumeAmount);
        Assert.assertEquals("consumed", 100, energy.getValue()+consumeAmount);

        energy.consume(consumer, consumeAmount);
        Assert.assertEquals("consumed", 100, energy.getValue()+(consumeAmount*2));
    }

    @Test(expected = ResourceProblem.class)
    public void testOverConsumption() throws Problem {
        energy.consume(consumer, 80);
        Assert.assertEquals("consumed", 20, energy.getValue());
        energy.consume(consumer, 80);
    }

    @Test
    public void testProduce() throws Problem {
        energy.consume(consumer, 100);
        Assert.assertEquals("starting value", 0, energy.getValue());

        energy.produce(30);
        Assert.assertEquals("produce increase", 30, energy.getValue());

        energy.update();
        Assert.assertEquals("update increase", 40, energy.getValue());

        energy.produce(60);
        Assert.assertEquals("produce to max", 100, energy.getValue());

        energy.update();
        Assert.assertEquals("update no increase", 100, energy.getValue());

        energy.produce(60);
        Assert.assertEquals("produce no increase", 100, energy.getValue());
    }
}
