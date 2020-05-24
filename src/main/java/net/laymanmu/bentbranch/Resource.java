package net.laymanmu.bentbranch;

import static java.lang.Math.abs;

public class Resource {
    public enum ResourceName {
        None, Health, Energy
    }

    private ResourceName name;
    private int max;
    private int delta;
    private int min;
    private int value;

    public Resource() {
        this.name  = ResourceName.None;
        this.max   = Settings.Resources.DefaultMax;
        this.delta = Settings.Resources.DefaultDelta;
        this.min   = Settings.Resources.DefaultMin;
        this.value = this.max;
    }

    public void update() {
        changeBy(delta);
    }

    public boolean consume(int amount) {
        var balance = value - abs(amount);
        if (balance < min) {
            return false;
        }
        value = balance;
        return true;
    }

    public void consumeUpTo(int amount) {
        // set value to min if not enough to consume amount:
        if (!consume(amount)) {
            value = min;
        }
    }

    public boolean produce(int amount) {
        return changeBy(abs(amount));
    }

    public void drain() {
        value = min;
    }

    public void fill() {
        value = max;
    }

    private boolean changeBy(int amount) {
        var v = value + amount;
        if (v < min) {
            v = min;
        }
        if (v > max) {
            v = max;
        }
        if (value != v) {
            value = v;
            return true;
        }
        return false;
    }

    //region accessors

    public ResourceName getName() {
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

        public ResourceBuilder withName(ResourceName name) {
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
