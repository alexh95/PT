package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordThesaurus implements Thesaurus {
	private Map<Word, Set<Word>> thesaurus;

	public WordThesaurus() {
		thesaurus = new HashMap<>();
	}

	private void addWordLink(Word word, Word synonym) {
		// get the word set
		Set<Word> wordSet;
		if (thesaurus.containsKey(word)) {
			wordSet = thesaurus.get(word);
		} else {
			wordSet = new HashSet<>();
			thesaurus.put(word, wordSet);
		}

		// get the synonym set
		Set<Word> synonymSet;
		if (thesaurus.containsKey(synonym)) {
			synonymSet = thesaurus.get(synonym);
		} else {
			synonymSet = new HashSet<>();
			thesaurus.put(synonym, synonymSet);
		}

		// join the two sets
		if (!wordSet.contains(synonym)) {
			wordSet.add(synonym);
		}

		if (!synonymSet.contains(word)) {
			synonymSet.add(word);
		}

		// get the joined sets
		Set<Word> joinedSet = new HashSet<>();
		for (Word wordSetSynonym : wordSet) {
			if (!joinedSet.contains(wordSetSynonym)) {
				joinedSet.add(wordSetSynonym);
			}
		}
		for (Word synonymSetSynonym : synonymSet) {
			if (!joinedSet.contains(synonymSetSynonym)) {
				joinedSet.add(synonymSetSynonym);
			}
		}
		for (Word wordToUpdate : joinedSet) {
			Set<Word> setToUpdate = thesaurus.get(wordToUpdate);
			for (Word wordToAdd : joinedSet) {
				if (!wordToAdd.equals(wordToUpdate) && !setToUpdate.contains(wordToAdd)) {
					setToUpdate.add(wordToAdd);
				}
			}
		}
	}

	/**
	 * @param word
	 * @param synonym
	 * @Precondition (word != null)
	 * @Precondition (synonym != null)
	 * @Precondition (!word.equals(synonym))
	 * @Postcondition (thesaurus contains word and synonym)
	 * @Postcondition (pre-word set size + 1 = post-word set size)
	 * @Postcondition (pre-synonym set size + 1 = post-synonym set size)
	 */
	@Override
	public void addWordSynonym(Word word, Word synonym) {
		assert isWellFormed() : "pre add syn not well formed";
		assert word != null : "word is null";
		assert synonym != null : "synonim is null";
		assert !word.equals(synonym) : "word is synonim";
		int precondPreWordSetSize = 0;
		if (thesaurus.containsKey(word)) {
			precondPreWordSetSize = thesaurus.get(word).size();
		}
		int precondPreSynonimSetSize = 0;
		if (thesaurus.containsKey(synonym)) {
			precondPreSynonimSetSize = thesaurus.get(synonym).size();
		}

		if (!word.equals(synonym)) {
			addWordLink(word, synonym);
		}

		assert thesaurus.containsKey(word) : "word not was not added";
		assert thesaurus.containsKey(synonym) : "synomin was not added";
		assert thesaurus.get(word).size() == precondPreWordSetSize + 1 : "pre word set size + 1 != post word set size";
		assert thesaurus.get(synonym).size() == precondPreSynonimSetSize
				+ 1 : "pre synonym set size + 1 != post synonym set size";
		assert isWellFormed() : "post add syn not well formed";
	}

	private void removeWordAndLinks(Word word) {
		Set<Word> wordSet = thesaurus.get(word);
		Set<Word> toBeRemoved = new HashSet<>();
		for (Word synonym : wordSet) {
			Set<Word> synonymSet = thesaurus.get(synonym);
			synonymSet.remove(word);
			if (synonymSet.isEmpty()) {
				toBeRemoved.add(synonym);
			}
		}
		thesaurus.remove(word);
		for (Word rem : toBeRemoved) {
			thesaurus.remove(rem);
		}
	}

	/**
	 * @param word
	 * @Precondition (word != null)
	 * @Postcondition (pre-thesaurus set size => post-thesaurus set size)
	 */
	@Override
	public void removeWord(Word word) {
		assert isWellFormed() : "pre remove syn not well formed";
		assert word != null : "word is null";
		int precondPreWordSetSize = thesaurus.size();

		if (thesaurus.containsKey(word)) {
			removeWordAndLinks(word);
		}

		if (precondPreWordSetSize > 0) {
			int postcondPreWordSetSize = thesaurus.size();
			assert postcondPreWordSetSize <= precondPreWordSetSize : "pre word set size - 1 != post word set size";
		}
		assert isWellFormed() : "post remove syn not well formed";
	}

	/**
	 * @Invariant
	 * @return boolean
	 */
	@Override
	public boolean isWellFormed() {
		for (Word key : thesaurus.keySet()) {
			for (Word synonym : thesaurus.get(key)) {
				if (!thesaurus.containsKey(synonym)) {
					return false;
				} else {
					if (!thesaurus.get(synonym).contains(key)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public Set<Word> getWords() {
		return thesaurus.keySet();
	}

	@Override
	public Set<Word> getSynonyms(Word key) {
		return thesaurus.get(key);
	}
}
