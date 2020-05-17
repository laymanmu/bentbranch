package net.laymanmu.BentBranch;

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

    public Resource(String name) {
        this.name  = name;
        this.max   = Settings.Resources.DefaultMax;
        this.delta = Settings.Resources.DefaultDelta;
        this.min   = Settings.Resources.DefaultMin;
        this.value = this.max;
    }

    public void update() {
        int v = value + delta;
        if (v > max) {
            v = max;
        }
        if (v < min) {
            v = min;
        }
        if (value != v) {
            value = v;
        }
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

    public void setValue(int value) {
        this.value = value;
    }
}
