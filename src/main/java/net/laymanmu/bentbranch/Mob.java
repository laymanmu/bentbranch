package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.actions.Action;
import net.laymanmu.bentbranch.actions.MoveAction;
import net.laymanmu.bentbranch.middleware.Middleware;
import net.laymanmu.bentbranch.ops.Settings;
import net.laymanmu.bentbranch.ops.problems.Problem;
import net.laymanmu.bentbranch.ops.problems.ResourceProblem;

import java.util.ArrayList;
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
    private HashMap<Resource.Name,Resource> resources;
    private HashMap<Action.Name, Action> actions;
    private ArrayList<Middleware> middleware;

    public Mob(String name) {
        this.id   = UUID.randomUUID().toString();
        this.name = name;

        this.position = new Point(Settings.Mob.DefaultPositionX, Settings.Mob.DefaultPositionY);

        this.dexterity    = Settings.Mob.DefaultDexterity;
        this.intelligence = Settings.Mob.DefaultIntelligence;
        this.strength     = Settings.Mob.DefaultStrength;
        this.constitution = Settings.Mob.DefaultConstitution;

        this.resources = new HashMap<>();
        addResource(Resource.builder().withName(Resource.Name.Health).build());
        addResource(Resource.builder().withName(Resource.Name.Energy).withDelta(10).build());

        this.actions = new HashMap<>();
        addAction(new MoveAction(this));

        this.middleware = new ArrayList<>();
    }

    public boolean isAlive() throws ResourceProblem {
        return getResourceValue(Resource.Name.Health) > 0;
    }

    public void update(HashMap<String,String> state) throws Problem {
        if (!isAlive()) {
            return;
        }
        for (var resource : resources.values()) {
            resource.update();
        }
        for (var action : actions.values()) {
            action.update();
        }

        // invoke middleware in order:
        middleware.forEach(mw -> mw.invoke(state));
    }


    public void addResource(Resource resource) {
        this.resources.put(resource.getName(), resource);
    }

    public int getResourceValue(Resource.Name resourceName) throws ResourceProblem {
        var resource = this.resources.get(resourceName);
        if (resource == null) {
            throw Problem.ResourceNotFound(this, resourceName);
        }
        return resource.getValue();
    }


    public void addMiddleware(Middleware mw) {
        middleware.add(mw);
    }


    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public Action getAction(Action.Name actionName) {
        return this.actions.get(actionName);
    }








    @Override
    public String toString() {
        return "mob:"+ getName();
    }



    //region generated accessors

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

    public HashMap<Resource.Name, Resource> getResources() {
        return resources;
    }

    public void setResources(HashMap<Resource.Name, Resource> resources) {
        this.resources = resources;
    }

    public HashMap<Action.Name, Action> getActions() {
        return actions;
    }

    public void setActions(HashMap<Action.Name, Action> actions) {
        this.actions = actions;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    //endregion accessors


    //region generated builder

    public static MobBuilder build(String mobName) {
        return MobBuilder.mob(mobName);
    }

    public static class MobBuilder {
        private final Mob mob;

        private MobBuilder(String name) {
            mob = new Mob(name);
        }

        public MobBuilder withPosition(Point position) {
            mob.position = position;
            return this;
        }

        public MobBuilder withExperience(long experience) {
            mob.experience = experience;
            return this;
        }

        public MobBuilder withLevel(int level) {
            mob.level = level;
            return this;
        }

        public MobBuilder withDexterity(int dexterity) {
            mob.dexterity = dexterity;
            return this;
        }

        public MobBuilder withIntelligence(int intelligence) {
            mob.intelligence = intelligence;
            return this;
        }

        public MobBuilder withStrength(int strength) {
            mob.strength = strength;
            return this;
        }

        public MobBuilder withConstitution(int constitution) {
            mob.constitution = constitution;
            return this;
        }

        public MobBuilder withResources(HashMap<Resource.Name, Resource> resources) {
            mob.resources = resources;
            return this;
        }

        public MobBuilder withActions(HashMap<Action.Name, Action> actions) {
            mob.actions = actions;
            return this;
        }

        public static MobBuilder mob(String name) {
            return new MobBuilder(name);
        }

        public Mob build() {
            return mob;
        }
    }

    //endregion builder



}
