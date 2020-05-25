package net.laymanmu.bentbranch;

import net.laymanmu.bentbranch.Resource.ResourceName;
import java.util.HashMap;
import java.util.UUID;

public class Mob {
    // identifiers:
    private final String id;
    private final String name;

    // positions:
    private Point position;
    private Point target;

    // progressions:
    private long experience;
    private int level;

    // attributes:
    private int dexterity;
    private int intelligence;
    private int strength;
    private int constitution;

    // resources:
    private HashMap<ResourceName, Resource> resources;

    public Mob(String name) {
        // identifiers:
        this.id = UUID.randomUUID().toString();
        this.name = name;

        // progressions:
        experience = 0;
        level = 1;

        // positions:
        this.position = new Point(Settings.Mob.DefaultPositionX, Settings.Mob.DefaultPositionY);
        this.target = new Point(position.x, position.y);

        // attributes:
        this.dexterity = Settings.Mob.DefaultDexterity;
        this.intelligence = Settings.Mob.DefaultIntelligence;
        this.strength = Settings.Mob.DefaultStrength;
        this.constitution = Settings.Mob.DefaultConstitution;

        // resources:
        this.resources = new HashMap<>();
        addResource(Resource.builder().withName(ResourceName.Health).build());
        addResource(Resource.builder().withName(ResourceName.Energy).withDelta(5).build());
    }

    public boolean isAlive() {
        return getResourceValue(ResourceName.Health) > 0;
    }

    public void update(TileMap tileMap) {
        if (!isAlive()) {
            return;
        }
        resources.values().forEach(r -> r.update());
    }

    public void addResource(Resource resource) {
        this.resources.put(resource.getName(), resource);
    }

    public int getResourceValue(ResourceName resourceName) {
        return this.resources.get(resourceName).getValue();
    }


    public void produce(ResourceName resourceName, int amount) {
        resources.get(resourceName).produce(amount);
    }

    public void consume(ResourceName resourceName, int amount) {
        resources.get(resourceName).consume(amount);
    }

    public void takeDamage(int amount) {
        resources.get(ResourceName.Health).consumeUpTo(amount);
    }

    public int health() {
        return getResourceValue(ResourceName.Health);
    }

    public int energy() {
        return getResourceValue(ResourceName.Energy);
    }


    @Override
    public String toString() {
        return "mob:" + getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
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

    public HashMap<ResourceName, Resource> getResources() {
        return resources;
    }

    public void setResources(HashMap<ResourceName, Resource> resources) {
        this.resources = resources;
    }



    public static MobBuilder build(String name) {
        return MobBuilder.mob(name);
    }




    public static class MobBuilder
    {
        private Mob mob;

        private MobBuilder(String name)
        {
            mob = new Mob(name);
        }

        public MobBuilder withPosition(Point position)
        {
            mob.position = position;
            return this;
        }

        public MobBuilder withTarget(Point target)
        {
            mob.target = target;
            return this;
        }

        public MobBuilder withExperience(long experience)
        {
            mob.experience = experience;
            return this;
        }

        public MobBuilder withLevel(int level)
        {
            mob.level = level;
            return this;
        }

        public MobBuilder withDexterity(int dexterity)
        {
            mob.dexterity = dexterity;
            return this;
        }

        public MobBuilder withIntelligence(int intelligence)
        {
            mob.intelligence = intelligence;
            return this;
        }

        public MobBuilder withStrength(int strength)
        {
            mob.strength = strength;
            return this;
        }

        public MobBuilder withConstitution(int constitution)
        {
            mob.constitution = constitution;
            return this;
        }

        public MobBuilder withResources(HashMap<ResourceName, Resource> resources)
        {
            mob.resources = resources;
            return this;
        }

        public static MobBuilder mob(String name)
        {
            return new MobBuilder(name);
        }

        public Mob build()
        {
            return mob;
        }
    }
}
