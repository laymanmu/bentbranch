package laymanmu.roguelike.mob;

import laymanmu.roguelike.ai.FungusAI;
import laymanmu.roguelike.ai.PlayerAI;
import laymanmu.roguelike.text.TextFactory;
import laymanmu.roguelike.world.World;

import java.awt.*;
import java.util.List;

public class CreatureFactory {
	private World world;

	public CreatureFactory(World world) {
		this.world = world;
	}
	
	public Creature newPlayer(List<String> messages, int z) {
		Creature player = new Creature(world, '@', Color.white, 100, 20, 5);
		player.name("player");
		player.isPlayer(true);
		player.adjectives(TextFactory.randomCreatureAdjectives(0.1));
		world.addAtEmptyLocation(player, z);
		new PlayerAI(player, messages);
		return player;
	}
	
	public Creature newFungus(int z) {
		Creature fungus = new Creature(world, 'f', Color.green, 10, 0, 0);
		fungus.name("fungus");
		fungus.adjectives(TextFactory.randomCreatureAdjectives(0.05));
		world.addAtEmptyLocation(fungus, z);
		new FungusAI(fungus, this);
		return fungus;
	}

}
