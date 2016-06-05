package model;

import java.util.Set;

public interface Thesaurus {

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
	void addWordSynonym(Word word, Word synonym);

	/**
	 * @param word
	 * @Precondition (word != null)
	 * @Postcondition (pre-thesaurus set size > post-thesaurus set size)
	 */
	void removeWord(Word word);

	/**
	 * @Invariant
	 * @return boolean
	 */
	boolean isWellFormed();

	Set<Word> getWords();

	Set<Word> getSynonyms(Word key);
}
