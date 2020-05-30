package laymanmu.roguelike.ai;

import laymanmu.roguelike.mob.Creature;
import laymanmu.roguelike.mob.CreatureFactory;

public class FungusAI extends CreatureAI {

	private CreatureFactory creatureFactory;
	private int spreadCount;
	
	public FungusAI(Creature creature, CreatureFactory creatureFactory) {
		super(creature);
		this.creatureFactory = creatureFactory;
		this.spreadCount     = 0;
	}
	
	public void onUpdate() {
		if (spreadCount < 5 && Math.random() < 0.02) {
			spread();
		}
	}
	
	public void spread() {
		int x = creature.x + (int)(Math.random() * 2) - 1;
		int y = creature.y + (int)(Math.random() * 2) - 1;
		
		if (!creature.canEnter(x, y, creature.z)) {
			return;
		}
		
		Creature child = creatureFactory.newFungus(creature.z);
		child.x = x;
		child.y = y;
		spreadCount++;
	}

}
