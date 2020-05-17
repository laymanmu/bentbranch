package net.laymanmu.BentBranch.Actions;

import java.util.List;

public interface Action {
    String getName();
    boolean isReady();
    boolean act(Object input);
    void update();
}
