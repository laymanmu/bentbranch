package net.laymanmu.BentBranch;

import net.laymanmu.BentBranch.Actions.Action;
import net.laymanmu.BentBranch.Actions.MoveAction;
import net.laymanmu.BentBranch.ops.Settings;

import java.util.HashMap;
import java.util.UUID;

public class Mob {
    // identifiers:
    private final String id;
    private final String name;

    // position:
    private Point position;

    // progression:
    private long experience;
    private int level;

    // attributes:
    private int dexterity;
    private int intelligence;
    private int strength;
    private int constitution;

    // lists:
    private HashMap<String,Resource> resources;
    private HashMap<String, Action> actions;

    public Mob(String name) {
        this.id   = UUID.randomUUID().toString();
        this.name = name;

        this.position = new Point(Settings.Mob.DefaultPositionX, Settings.Mob.DefaultPositionY);

        this.dexterity    = Settings.Mob.DefaultDexterity;
        this.intelligence = Settings.Mob.DefaultIntelligence;
        this.strength     = Settings.Mob.DefaultStrength;
        this.constitution = Settings.Mob.DefaultConstitution;

        this.resources = new HashMap<>();
        addResource(new Resource("health"));
        addResource(new Resource("energy"));

        this.actions = new HashMap<>();
        addAction(new MoveAction(this));
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void update() {
        if (!isAlive()) {
            return;
        }
        for (var resource : resources.values()) {
            resource.update();
        }
        for (var action : actions.values()) {
            action.update();
        }
    }

    public void addResource(Resource resource) {
        this.resources.put(resource.getName(), resource);
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public int getHealth() {
        return this.resources.get("health").getValue();
    }

    public int getEnergy() {
        return this.resources.get("energy").getValue();
    }

    @Override
    public String toString() {
        return "mob:"+ getName();
    }


    // accessors:


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public HashMap<String, Resource> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Resource> resources) {
        this.resources = resources;
    }

    public HashMap<String, Action> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, Action> actions) {
        this.actions = actions;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
