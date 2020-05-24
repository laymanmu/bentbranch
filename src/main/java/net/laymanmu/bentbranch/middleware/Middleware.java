package net.laymanmu.bentbranch.middleware;

import java.util.Map;

public interface Middleware {
    void invoke(Map<String, String> state);
}
