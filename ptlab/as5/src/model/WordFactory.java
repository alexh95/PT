package model;

public class WordFactory {
	private WordThesaurus wordThesaurus;
	
	public WordFactory(WordThesaurus wordThesaurus) {
		this.wordThesaurus = wordThesaurus;
	}
	
	public Word createWord(String wordText) {
		for (Word word : wordThesaurus.getWords()) {
			if (word.getWordText().equals(wordText)) {
				return word;
			}
		}
		return new Word(wordText);
	}
}
