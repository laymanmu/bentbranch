package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.ops.problems.Problem;
import net.laymanmu.bentbranch.ops.Settings;

import static java.lang.Math.abs;

public class Resource {
    public enum Name {
        None, Health, Energy
    }

    private Resource.Name name;
    private int max;
    private int delta;
    private int min;
    private int value;

    public Resource() {
        this.name  = Resource.Name.None;
        this.max   = Settings.Resources.DefaultMax;
        this.delta = Settings.Resources.DefaultDelta;
        this.min   = Settings.Resources.DefaultMin;
        this.value = this.max;
    }

    public void update() {
        changeBy(delta);
    }

    public void consume(Mob consumer, int amount) throws Problem {
        var debit = abs(amount);
        if (debit > value) {
            throw Problem.ResourceOverConsumed(consumer, this, amount);
        }
        changeBy(debit * -1);
    }

    public void produce(int amount) {
        var credit = abs(amount);
        changeBy(credit);
    }

    public void drain() {
        value = min;
    }

    public void fill() {
        value = max;
    }


    private void changeBy(int amount) {
        var v = value + amount;
        if (v < min) {
            v = min;
        }
        if (v > max) {
            v = max;
        }
        value = v;
    }

    //region accessors

    public Name getName() {
        return name;
    }

    public int getMax() {
        return max;
    }

    public int getDelta() {
        return delta;
    }

    public int getMin() {
        return min;
    }

    public int getValue() {
        return value;
    }

    //endregion


    //region builder

    public static ResourceBuilder builder() {
        return ResourceBuilder.resource();
    }

    public static class ResourceBuilder {
        private final Resource resource;

        private ResourceBuilder() {
            resource = new Resource();
        }

        public ResourceBuilder withName(Name name) {
            resource.name = name;
            return this;
        }

        public ResourceBuilder withMax(int max) {
            resource.max = max;
            return this;
        }

        public ResourceBuilder withDelta(int delta) {
            resource.delta = delta;
            return this;
        }

        public ResourceBuilder withMin(int min) {
            resource.min = min;
            return this;
        }

        public ResourceBuilder withValue(int value) {
            resource.value = value;
            return this;
        }

        public static ResourceBuilder resource() {
            return new ResourceBuilder();
        }

        public Resource build() {
            return resource;
        }
    }

    //endregion builder

}
