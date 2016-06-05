package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.parser.ParseException;

import model.ProxyThesaurus;
import model.Word;
import view.ThesaurusFrame;

public class ThesaurusController {
	private ThesaurusFrame frame;
	private ProxyThesaurus proxyThesaurus;

	public ThesaurusController() {
		frame = new ThesaurusFrame();
		frame.addFilterTextFieldActionListener(new FilterTextFieldActionListener());
		frame.addSaveThesaurusButtonActionListener(new SaveThesaurusButtonActionListener());
		frame.addLoadThesaurusButtonActionListener(new LoadThesaurusButtonActionListener());
		frame.addWordListSelectionListener(new WordListSelectionListener());
		frame.addAddWordSynonymButtonActionListener(new AddWordSynonymButtonActionListener());
		frame.addRemoveWordSynonymButtonActionListener(new RemoveWordButtonActionListener());

		proxyThesaurus = new ProxyThesaurus();
	}

	private void updateWords(String stringPattern) {
		String spattern;
		if (stringPattern == null || stringPattern.length() == 0) {
			spattern = ".*";
		} else {
			spattern = stringPattern.replaceAll("[*]", ".*").replaceAll("[?]", ".");
		}
		Pattern pattern = Pattern.compile(spattern);
		DefaultListModel<Word> lm = new DefaultListModel<>();
		proxyThesaurus.getWords().stream().filter(word -> pattern.matcher(word.getWordText()).matches())
				.forEach(lm::addElement);
		frame.setWordListModel(lm);
	}

	private void updateSynonyms(Word key) {
		DefaultListModel<Word> lm = new DefaultListModel<>();
		proxyThesaurus.getSynonyms(key).stream().forEach(lm::addElement);
		frame.setSynonymListModel(lm);
	}

	private class FilterTextFieldActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String pattern = frame.getFilterTextFieldText();
			updateWords(pattern);
			frame.setFilterTextFieldText("");
			frame.setRemoveWordTextFieldText("");
		}
	}

	private class SaveThesaurusButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String filePath = frame.getFilePathText();
			try {
				proxyThesaurus.saveThesaurus(filePath);
			} catch (IOException e) {
				frame.setFilePathText("Invalid");
			}
		}
	}

	private class LoadThesaurusButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String filePath = frame.getFilePathText();
			try {
				proxyThesaurus.loadThesaurus(filePath);
				updateWords("*");
			} catch (IOException | ParseException e) {
				frame.setFilePathText("Invalid");
			}
		}
	}

	private class WordListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (frame.isWordListSelectionValid()) {
				Word word = frame.getWordListSelection();
				updateSynonyms(word);
				frame.setRemoveWordTextFieldText(word.getWordText());
			} else {
				frame.setSynonymListModel(new DefaultListModel<>());
				frame.setRemoveWordTextFieldText("");
			}
		}
	}

	private class AddWordSynonymButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Word word = proxyThesaurus.createWord(frame.getAddWordTextFieldText());
			Word synonym = proxyThesaurus.createWord(frame.getAddSynonymTextFieldText());
			proxyThesaurus.addWordSynonym(word, synonym);
			frame.setAddWordTextFieldText("");
			frame.setAddSynonymTextFieldText("");
			updateWords("*");
		}
	}

	private class RemoveWordButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Word word = proxyThesaurus.createWord(frame.getRemoveWordTextFieldText());
			proxyThesaurus.removeWord(word);
			frame.setRemoveWordTextFieldText("");
			updateWords("*");
		}
	}
}
