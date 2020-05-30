package laymanmu.roguelike.text;

import laymanmu.roguelike.mob.Creature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextFactory {

	public static String[] verbAdjectives = { "quickly", "weakly", "slowly", "forcefully", "quietly", "loudly",
			"regrettably", "critically", "barely", "wholeheartedly", "disturbingly", "mournfully", "grudgingly",
			"angrily", "mockingly", "accidentally", "intentionally"};

	public static String[] creatureAdjectives = {"fuzzy", "green", "blue", "dirty", "slimy", "filthy", "stinky",
			"glowing", "oily", "fast", "slow", "big", "small", "tiny", "strong", "weak", "scary", "strange", "little",
			"angry", "mellow", "intelligent", "hungry", "cranky", "crabby", "killer", "drunken", "tall", "short", "famous",
			"evil", "muddy", "bloody", "tired", "anxious", "thoughtful", "screaming", "singing", "boastful" };


	public static String[] randomVerbAdjectives(double chance) {
		return randomAdjectives(verbAdjectives, chance);
	}

	public static String[] randomCreatureAdjectives(double chance) {
		return randomAdjectives(creatureAdjectives, chance);
	}

	public static String action(Creature creature, String verb) {
		Sentence sentence = new Sentence().verb(verb, TextFactory.randomVerbAdjectives(0.008));
		if (creature.isPlayer()) {
			sentence.subject("you");
		} else {
			sentence.subjectDeterminer("the").subject(creature.name(), creature.adjectives());
		}
		return sentence.string();
	}


	public static void notifyAttack(Creature attacker, Creature target, int amount, String damageType) {
		String damage = String.format("for %d %s damage", amount, damageType);

		Sentence sentence = new Sentence()
				.verb("attack", TextFactory.randomVerbAdjectives(0.008))
				.complement(damage);

		if (attacker.isPlayer()) {
			sentence.subject("you").object(target.name(), target.adjectives());
		}
		if (target.isPlayer()) {
			sentence.subjectDeterminer("the").subject(attacker.name(), attacker.adjectives()).object("you");
		}

		attacker.notify(sentence.string());
		target.notify(sentence.string());
	}


	public static String[] randomAdjectives(String[] adjectives, double chance) {
		List<String> shuffled = Arrays.asList(adjectives);
		Collections.shuffle(shuffled);

		List<String> selected = new ArrayList<>();
		for (String adjective : shuffled) {
			if (Math.random() < chance) {
				chance -= chance/2.0;
				selected.add(adjective);
			}
		}
		return selected.toArray(new String[selected.size()]);
	}
}
