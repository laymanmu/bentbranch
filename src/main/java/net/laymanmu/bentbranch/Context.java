package net.laymanmu.bentbranch;

import java.util.HashMap;

public class Context {
    public enum EnergyEvent {
        None, Moved, Rested
    }

    public final Point targetPoint;
    public Point startingPosition;

    public final int energyStartingBalance;
    public HashMap<EnergyEvent,Integer> energyDeltaLog;

    public Context(Point startingPos Point targetPoint, int energyStartingBalance) {
        this.targetPoint = targetPoint;
        this.energyStartingBalance = energyStartingBalance;
        this.energyDeltaLog = new HashMap<>();
    }

    public Point getTargetPoint() {
        return targetPoint;
    }

    public int getEnergyStartingBalance() {
        return energyStartingBalance;
    }

    public int getEnergyFinalBalance() {
        return energyStartingBalance + energyDeltaLog.values().stream().mapToInt(i->i).sum();
    }

    public HashMap<EnergyEvent,Integer> getEnergyDeltaLog() {
        return energyDeltaLog;
    }

}
