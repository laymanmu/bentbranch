package laymanmu.roguelike.mob;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import laymanmu.roguelike.ai.CreatureAI;
import laymanmu.roguelike.text.TextFactory;
import laymanmu.roguelike.world.Tile;
import laymanmu.roguelike.world.World;

public class Creature {
	
	private World world;

	public int x;
	public int y;
	public int z;

	private boolean  isPlayer;
	public  boolean  isPlayer() { return isPlayer; }
	public  Creature isPlayer(boolean isPlayer) { this.isPlayer = isPlayer; return this; }

	private String name;
	public  String name() { return name; }
	public Creature name(String name) { this.name = name; return this; }

	private String[] adjectives;
	public String[] adjectives() { return adjectives; }
	public Creature adjectives(String... adjectives) { this.adjectives = adjectives; return this; }
	
	private char glyph;
	public  char glyph() { return glyph; }
	
	private Color color;
	public  Color color() { return color; }
	
	private CreatureAI ai;
	public void setCreatureAI(CreatureAI ai) { this.ai = ai; }

	private List<Creature> creaturesInView;
	public List<Creature> creaturesInView() { return creaturesInView; }

	// stats:
	private int maxHP;
	public  int maxHP() { return maxHP; }
	
	private int hp;
	public  int hp() { return hp; }
	
	private int attackValue;
	public  int attackValue() { return attackValue; }
	
	private int defenseValue;
	public  int defenseValue() { return defenseValue; }


	public Creature(World world, char glyph, Color color, int maxHP, int attack, int defense) {
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.maxHP = maxHP;
		this.hp    = maxHP;
		this.attackValue  = attack;
		this.defenseValue = defense;
		creaturesInView = new ArrayList<>();
		isPlayer = false;
	}
	
	public void dig(int wx, int wy, int wz) {
		world.dig(wx, wy, wz);
		notify(TextFactory.action(this, "dig"));
	}
	
	public void moveBy(int mx, int my, int mz) {
		Tile tile = world.tile(x+mx, y+mx, z+mz);
		if (mz == -1) {
			if (tile == Tile.STAIRS_DOWN) {
				notify(TextFactory.action(this, "ascend to level "+ (z+mz)));
			} else {
				notify("you can't go down here");
				return;
			}
		} else if (mz == 1) {
			if (tile == Tile.STAIRS_UP) {
				notify(TextFactory.action(this, "descend to level "+ (z+mz)));
			} else {
				notify("you can't go up here");
				return;
			}
		}

		Creature other = world.creatureAt(x+mx, y+my, z+mz);
		if (other == null) {
			ai.onEnter(x+mx, y+my, z+mz, world.tile(x+mx, y+my, z+mz));
		} else {
			attack(other);
		}
	}
	
	public void attack(Creature other) {
		int amount = Math.max(0,  attackValue() - other.defenseValue());
		amount = (int)(Math.random() * amount) + 1;

		TextFactory.notifyAttack(this, other, amount, "physical");

		other.modifyHP(-amount);
	}
		
	public void modifyHP(int amount) {
		hp += amount;
		if (hp < 1) {
			notifyCreaturesInView(TextFactory.action(this, "die"));
			world.remove(this);
		}
	}


	public void update() {
		ai.onUpdate();
	}
	
	public boolean canEnter(int x, int y, int z) {
		return !world.isBlocked(x, y, z);
	}


	public void notify(String message, Object... params) {
		ai.onNotify(String.format(message, params));
	}

	public void notifyCreaturesInView(String message, Object... params) {
		updateCreaturesInView();
		for (Creature c : creaturesInView) {
			c.notify(message, params);
		}
	}

	public void updateCreaturesInView() {
		creaturesInView.clear();
		int r = 9;
		int squared = r*r;
		for (int ox=-r; ox<=r; ox++) {
			for (int oy = -r; oy <= r; oy++) {
				if (ox * ox + oy * oy > squared) {
					continue;
				}
				Creature c = world.creatureAt(x + ox, y + oy, z);
				if (c != null) {
					creaturesInView.add(c);
				}
			}
		}
	}

}
