package laymanmu.bentbranch.ai;

import laymanmu.bentbranch.mob.Creature;
import net.laymanmu.bentbranch.world.Tile;

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
