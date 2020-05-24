package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.Resource.ResourceName;
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

        health = Resource.builder()
                .withName(ResourceName.Health)
                .withMin(0)
                .withMax(100)
                .withValue(100)
                .withDelta(1)
                .build();

        energy = Resource.builder()
                .withName(ResourceName.Energy)
                .withMin(0)
                .withMax(100)
                .withValue(100)
                .withDelta(10)
                .build();
    }

    @Test
    public void testDefaults() {
        Assert.assertEquals("name", ResourceName.Health, health.getName());
        Assert.assertEquals("max", 100, health.getMax());
        Assert.assertEquals("delta", 1, health.getDelta());
        Assert.assertEquals("min", 0, health.getMin());
        Assert.assertEquals("value", 100, health.getValue());

        Assert.assertEquals("name", ResourceName.Energy, energy.getName());
        Assert.assertEquals("max", 100, energy.getMax());
        Assert.assertEquals("delta", 10, energy.getDelta());
        Assert.assertEquals("min", 0, energy.getMin());
        Assert.assertEquals("value", 100, energy.getValue());
    }

    @Test
    public void testUpdate() {
        var expect = 20;
        Assert.assertTrue(health.consume(80));
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
    public void testConsume() {
        var consumeAmount = 40;
        Assert.assertTrue(energy.consume(consumeAmount));
        Assert.assertEquals("consumed", 60, energy.getValue());

        Assert.assertTrue(energy.consume(consumeAmount));
        Assert.assertEquals("consumed", 20, energy.getValue());
    }

    @Test
    public void testOverConsumption() {
        Assert.assertTrue(energy.consume(80));
        Assert.assertEquals("consumed", 20, energy.getValue());
        Assert.assertFalse(energy.consume(80));
    }

    @Test
    public void testProduce() {
        Assert.assertTrue(energy.consume(100));
        Assert.assertEquals("starting value", 0, energy.getValue());

        Assert.assertTrue(energy.produce(30));
        Assert.assertEquals("produce increase", 30, energy.getValue());

        energy.update();
        Assert.assertEquals("update increase", 40, energy.getValue());

        Assert.assertTrue(energy.produce(60));
        Assert.assertEquals("produce to max", 100, energy.getValue());

        energy.update();
        Assert.assertEquals("update no increase", 100, energy.getValue());

        Assert.assertFalse(energy.produce(60));
        Assert.assertEquals("produce no increase", 100, energy.getValue());
    }

    @Test
    public void testOverProduction() {
        Assert.assertTrue(energy.consume(10));
        Assert.assertEquals("starting value", 90, energy.getValue());
        Assert.assertTrue("use some, waste some", energy.produce(34));
        Assert.assertEquals("filled", 100, energy.getValue());
    }
}
