package net.laymanmu.bentbranch;

public class TileMapSymbols {
    public final String none;
    public final String wall;
    public final String floor;
    public final String gate;

    public TileMapSymbols() {
        none  = "?";
        wall  = "#";
        floor = ".";
        gate  = ">";
    }

    public TileMapSymbols(String none, String wall, String floor, String gate) {
        this.none  = none;
        this.wall  = wall;
        this.floor = floor;
        this.gate  = gate;
    }

    public String getSymbol(TileType tileType) {
        return switch (tileType) {
            case None -> none;
            case Wall -> wall;
            case Floor -> floor;
            case Gate -> gate;
        };
    }
}

