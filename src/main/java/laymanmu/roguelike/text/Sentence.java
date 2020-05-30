package laymanmu.roguelike.text;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.PhraseElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class Sentence {
	private final static Lexicon lexicon       = Lexicon.getDefaultLexicon();
	private final static NLGFactory nlgFactory = new NLGFactory(lexicon);
	private final static Realiser realiser     = new Realiser(lexicon);

	private String subject;
	private String subjectDeterminer;
	private String verb;
	private String object;
	private String objectDeterminer;

	private boolean  isPlural;
	private Tense    tense;
	private String[] subjectAdjectives = {};
	private String[] verbAdjectives    = {};
	private String[] objectAdjectives  = {};
	private String[] complements       = {};

	public Sentence() {
		isPlural = false;
		tense    = Tense.PRESENT;
	}

	public static String pluralize(int amount, String thing) {
		InflectedWordElement pluralWord = new InflectedWordElement(lexicon.getWord(thing));
		pluralWord.setPlural(true);
		return realiser.realiseSentence(pluralWord);
	}

	// subject:

	public Sentence subject(String subject) {
		this.subject = subject;
		return this;
	}
	public Sentence subject(String subject, String... adjectives) {
		this.subject = subject;
		this.subjectAdjectives = adjectives;
		return this;
	}
	public Sentence subjectDeterminer(String determiner) {
		subjectDeterminer = determiner;
		return this;
	}

	// verb:

	public Sentence verb(String verb) {
		this.verb = verb;
		return this;
	}
	public Sentence verb(String verb, String... adjectives) {
		this.verb = verb;
		this.verbAdjectives = adjectives;
		return this;
	}

	// object:

	public Sentence objects(int amount, String object, String...adjectives) {
		isPlural    = (amount == 0 || amount > 1) ? true : false;
		this.object = object;
		this.objectAdjectives = adjectives;
		objectDeterminer      = Integer.toString(amount);
		return this;
	}
	public Sentence object(String object, String... adjectives) {
		isPlural    = false;
		this.object = object;
		this.objectAdjectives = adjectives;
		objectDeterminer = "the";
		return this;
	}

	// tense:

	public Sentence presentTense() {
		tense = Tense.PRESENT;
		return this;
	}
	public Sentence futureTense() {
		tense = Tense.FUTURE;
		return this;
	}
	public Sentence pastTense() {
		tense = Tense.PAST;
		return this;
	}

	// complements: (phrases about the verb at the end, ex: bravely, very quickly):

	public Sentence complement(String... complements) {
		this.complements = complements;
		return this;
	}

	// build:

	public String string() {
		SPhraseSpec sentence = nlgFactory.createClause();
		if (subject != null) {
			NPPhraseSpec subjectPhrase = nlgFactory.createNounPhrase(subject);
			for (String adjective : subjectAdjectives) {
				subjectPhrase.addPreModifier(adjective);
			}
			if (subjectDeterminer != null) {
				subjectPhrase.setDeterminer(subjectDeterminer);
			}
			sentence.setSubject(subjectPhrase);
		}

		if (verb != null) {
			VPPhraseSpec verbPhrase = nlgFactory.createVerbPhrase(verb);
			for (String adjective : verbAdjectives) {
				verbPhrase.addPreModifier(adjective);
			}
			sentence.setVerb(verbPhrase);
		}

		if (object != null) {
			NPPhraseSpec objectPhrase = nlgFactory.createNounPhrase(object);
			objectPhrase.setPlural(isPlural);
			for (String adjective : objectAdjectives) {
				objectPhrase.addPreModifier(adjective);
			}
			objectPhrase.setDeterminer(objectDeterminer);
			sentence.setObject(objectPhrase);
		}

		for (String complement : complements) {
			sentence.addComplement(complement);
		}

		sentence.setFeature(Feature.TENSE, tense);

		return realiser.realiseSentence(sentence);
	}

	// helpers:

	private Sentence modify(PhraseElement element, String[] modifiers) {
		for (String modifier : modifiers) {
			element.addPreModifier(modifier);
		}
		return this;
	}

}
