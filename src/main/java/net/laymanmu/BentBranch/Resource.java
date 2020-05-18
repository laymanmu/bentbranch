package net.laymanmu.BentBranch;

import net.laymanmu.BentBranch.ops.problems.Problem;
import net.laymanmu.BentBranch.ops.Settings;

import static java.lang.Math.abs;

public class Resource {
    private String name;
    private int max;
    private int delta;
    private int min;
    private int value;

    public Resource(String name, int max, int delta, int min, int value) {
        this.name  = name;
        this.max   = max;
        this.delta = delta;
        this.min   = min;
        this.value = value;
    }

    protected Resource() {
        this.name  = "unknown";
        this.max   = Settings.Resources.DefaultMax;
        this.delta = Settings.Resources.DefaultDelta;
        this.min   = Settings.Resources.DefaultMin;
        this.value = this.max;
    }

    public Resource(String name) {
        this.name  = name;
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

    // helpers:

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

    // accessors:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getValue() {
        return value;
    }

    //region builder

    public static ResourceBuilder build() {
        return ResourceBuilder.resource();
    }

    public static class ResourceBuilder {
        private Resource resource;
        private ResourceBuilder() {
            resource = new Resource();
        }
        public ResourceBuilder withName(String name) {
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
