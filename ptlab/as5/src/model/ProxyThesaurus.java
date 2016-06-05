package model;

import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;

import services.JSONThesaurus;

public class ProxyThesaurus implements Thesaurus {

	private WordThesaurus thesaurus;
	private WordFactory wordFactory;

	public ProxyThesaurus() {
	}

	private void checkAndInitThesaurus() {
		if (thesaurus == null) {
			thesaurus = new WordThesaurus();
			wordFactory = new WordFactory(thesaurus);
		}
	}
	
	public Word createWord(String wordText) {
		checkAndInitThesaurus();
		return wordFactory.createWord(wordText);
	}

	public void saveThesaurus(String filePath) throws IOException {
		checkAndInitThesaurus();
		JSONThesaurus.instance().save(filePath, thesaurus);
	}

	public void loadThesaurus(String filePath) throws IOException, ParseException {
		checkAndInitThesaurus();
		thesaurus = JSONThesaurus.instance().load(filePath);
	}

	@Override
	public void addWordSynonym(Word word, Word synonym) {
		checkAndInitThesaurus();
		thesaurus.addWordSynonym(word, synonym);
	}

	@Override
	public void removeWord(Word word) {
		checkAndInitThesaurus();
		thesaurus.removeWord(word);
	}

	@Override
	public boolean isWellFormed() {
		checkAndInitThesaurus();
		return thesaurus.isWellFormed();
	}

	@Override
	public Set<Word> getWords() {
		checkAndInitThesaurus();
		return thesaurus.getWords();
	}

	@Override
	public Set<Word> getSynonyms(Word key) {
		checkAndInitThesaurus();
		return thesaurus.getSynonyms(key);
	}
}
