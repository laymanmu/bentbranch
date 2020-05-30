package laymanmu.roguelike.ai;

import laymanmu.roguelike.mob.Creature;
import laymanmu.roguelike.world.Tile;

public class CreatureAI {

	protected Creature creature;
	
	public CreatureAI(Creature creature) {
		this.creature = creature;
		this.creature.setCreatureAI(this);
	}

	public void onEnter(int x, int y, int z, Tile tile) {}
	
	public void onUpdate() {}

	public void onNotify(String message) {}
	
}
