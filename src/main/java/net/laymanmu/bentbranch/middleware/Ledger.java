package net.laymanmu.bentbranch.middleware;

import java.util.ArrayList;

public class Ledger {
    public enum EntryType {
        None, Moved, Rested
    };

    public class Entry {
        public final EntryType type;
        public final int delta;
        public Entry(EntryType type, int delta) {
            this.type = type;
            this.delta = delta;
        }
    }

    public final int startingValue;
    private ArrayList<Entry> entries;

    public Ledger(int startingValue) {
        this.startingValue = startingValue;
        this.entries = new ArrayList<>();
    }

    public void addEntry(EntryType type, int delta) {
        this.entries.add(new Entry(type, delta));
    }

    public int getValue() {
        return startingValue + entries.stream().mapToInt(entry->entry.delta).sum();
    }
}
