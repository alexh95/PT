package services;

import java.util.Iterator;
import java.util.Set;

import model.Thesaurus;
import model.Word;

public class ThesaurusIterator {
	private final Thesaurus thesaurus;
	private Iterator<Word> wordIterator;
	private Set<Word> nextSynonyms;
	
	public ThesaurusIterator(Thesaurus thesaurus) {
		this.thesaurus = thesaurus;
		wordIterator = thesaurus.getWords().iterator();
	}
	
	public boolean hasNext() {
		return wordIterator.hasNext();
	}
	
	public Word getNextWord() {
		Word nextWord = wordIterator.next();
		nextSynonyms = thesaurus.getSynonyms(nextWord);
		return nextWord;
	}
	
	public Set<Word> getNextSynonyms() {
		return nextSynonyms;
	}
}
