package services;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Word;
import model.WordFactory;
import model.WordThesaurus;

public class JSONThesaurus {
	private static final String JSON_WORD_TAG = "_w_o_r_d_s_";

	private static JSONThesaurus instance;

	public static JSONThesaurus instance() {
		if (instance == null) {
			instance = new JSONThesaurus();
		}
		return instance;
	}

	private JSONThesaurus() {
	}

	public void save(String filePath, WordThesaurus wordThesaurus) throws IOException {
		JSONObject thesaurusJSON = new JSONObject();

		JSONArray wordList = new JSONArray();
		for (Word word : wordThesaurus.getWords()) {
			wordList.add(word.getWordText());
		}
		thesaurusJSON.put(JSON_WORD_TAG, wordList);
		ThesaurusIterator iterator = new ThesaurusIterator(wordThesaurus);
		while (iterator.hasNext()) {
			Word word = iterator.getNextWord();
			JSONArray synonymsList = new JSONArray();
			for (Word synonym : iterator.getNextSynonyms()) {
				synonymsList.add(synonym.toString());
			}
			thesaurusJSON.put(word.getWordText(), synonymsList);
		}

		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(thesaurusJSON.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}

	public WordThesaurus load(String filePath) throws IOException, ParseException {
		FileReader fileReader = new FileReader(filePath);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(fileReader);
		
		WordThesaurus wordThesaurus = new WordThesaurus();
		WordFactory wordFactory = new WordFactory(wordThesaurus);

		JSONArray wordList = (JSONArray) obj.get(JSON_WORD_TAG);
		for (Object wordObject : wordList) {
			Word word = wordFactory.createWord(wordObject.toString());
			JSONArray synonymsList = (JSONArray) obj.get(word.getWordText());
			for (Object synonymObject : synonymsList) {
				Word synonym = wordFactory.createWord(synonymObject.toString());
				wordThesaurus.addWordSynonym(word, synonym);
			}
		}

		fileReader.close();
		return wordThesaurus;
	}
}
