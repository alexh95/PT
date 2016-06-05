package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionListener;

import model.Word;

public class ThesaurusFrame extends JFrame {
	private static final long serialVersionUID = 2283975092137009246L;
	private static final String TITLE = "Thesaurus";

	private static final double WIDTH_RATIO = 0.75;
	private static final double HEIGHT_RATIO = 0.75;

	// options
	// filter
	private JTextField filterTextField;
	// save load
	private JTextField saveLoadFilePathTextField;
	private JButton saveThesaurusButton;
	private JButton loadThesaurusButton;

	// word link
	private JList<Word> wordList;
	private JList<Word> synonymList;

	// add word synonym
	private JTextField addWordTextField;
	private JTextField addSynonymTextField;
	private JButton addWordSynonymButton;
	// remove word
	private JTextField removeWordTextField;
	private JButton removeWordButton;

	public ThesaurusFrame() {
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension d = getToolkit().getScreenSize();
		int w = (int) (d.width * WIDTH_RATIO);
		int h = (int) (d.height * HEIGHT_RATIO);
		int x = (d.width - w) / 2;
		int y = (d.height - h) / 2;
		setBounds(x, y, w, h);

		JPanel mainPanel = new JPanel(new BorderLayout());
		// options panel
		JPanel optionsPanel = new JPanel(new GridLayout(1, 2));
		// filter panel
		JPanel filterPanel = new JPanel(new GridLayout(1, 2));
		JLabel filterLabel = new JLabel("Type filter and press return");
		filterPanel.add(filterLabel);
		filterTextField = new JTextField();
		filterPanel.add(filterTextField);
		filterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(filterPanel);
		optionsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		// save load panel
		JPanel saveLoadPanel = new JPanel(new GridLayout(1, 4));
		JLabel saveLoadLabel = new JLabel("File path");
		saveLoadPanel.add(saveLoadLabel);
		saveLoadFilePathTextField = new JTextField();
		saveLoadPanel.add(saveLoadFilePathTextField);
		saveThesaurusButton = new JButton("Save");
		saveLoadPanel.add(saveThesaurusButton);
		loadThesaurusButton = new JButton("Load");
		saveLoadPanel.add(loadThesaurusButton);
		saveLoadPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(saveLoadPanel);
		// add options panel
		mainPanel.add(optionsPanel, BorderLayout.NORTH);

		// word link panel
		JPanel wordLinkPanel = new JPanel(new GridLayout(1, 2));
		// words panel
		JPanel wordsPanel = new JPanel(new BorderLayout());
		JLabel wordsLabel = new JLabel("Words");
		wordsPanel.add(wordsLabel, BorderLayout.NORTH);
		wordList = new JList<>();
		wordsPanel.add(new JScrollPane(wordList), BorderLayout.CENTER);
		wordsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		wordLinkPanel.add(wordsPanel);

		// synonyms panel
		JPanel synonymsPanel = new JPanel(new BorderLayout());
		JLabel synonymsLabel = new JLabel("Synonyms");
		synonymsPanel.add(synonymsLabel, BorderLayout.NORTH);
		synonymList = new JList<>();
		synonymsPanel.add(new JScrollPane(synonymList), BorderLayout.CENTER);
		synonymsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		wordLinkPanel.add(synonymsPanel);
		// add word link panel
		wordLinkPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		mainPanel.add(wordLinkPanel, BorderLayout.WEST);

		// word synonym panel
		JPanel wordSynonymPanel = new JPanel(new GridLayout(2, 1));
		// add word add synonym panel
		JPanel addWordSynonymPanel = new JPanel(new BorderLayout());
		// top label
		JLabel addWordSynonymLabel = new JLabel("Type new or existing words");
		addWordSynonymPanel.add(addWordSynonymLabel, BorderLayout.NORTH);
		// components center
		JPanel addWordSynonymComponentsPanel = new JPanel(new GridLayout(2, 3));
		JLabel wordTextLabel = new JLabel("New or existing word");
		addWordSynonymComponentsPanel.add(wordTextLabel);
		JLabel synonymTextLabel = new JLabel("New or existing word");
		addWordSynonymComponentsPanel.add(synonymTextLabel);
		JLabel addWordSynonymButtonLabel = new JLabel("Add Synonym");
		addWordSynonymComponentsPanel.add(addWordSynonymButtonLabel);
		addWordTextField = new JTextField();
		addWordSynonymComponentsPanel.add(addWordTextField);
		addSynonymTextField = new JTextField();
		addWordSynonymComponentsPanel.add(addSynonymTextField);
		addWordSynonymButton = new JButton("Confirm");
		addWordSynonymComponentsPanel.add(addWordSynonymButton);
		// add components
		addWordSynonymComponentsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		addWordSynonymPanel.add(addWordSynonymComponentsPanel, BorderLayout.CENTER);
		// add to word synonym panel
		wordSynonymPanel.add(addWordSynonymPanel);
		// remove word panel
		JPanel removeWordPanel = new JPanel(new BorderLayout());
		// top label
		JLabel removeWordLabel = new JLabel("Remove a word");
		removeWordPanel.add(removeWordLabel, BorderLayout.NORTH);
		// components center
		JPanel removeWordCompnentsPanel = new JPanel(new GridLayout(2, 2));
		JLabel removeWordTextLabel = new JLabel("Select a word from the list");
		removeWordCompnentsPanel.add(removeWordTextLabel);
		JLabel removeWordButtonLabel = new JLabel("Remove word and all links");
		removeWordCompnentsPanel.add(removeWordButtonLabel);
		removeWordTextField = new JTextField();
		removeWordTextField.setEditable(false);
		removeWordCompnentsPanel.add(removeWordTextField);
		removeWordButton = new JButton("Confirm");
		removeWordCompnentsPanel.add(removeWordButton);
		// add components
		removeWordCompnentsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		removeWordPanel.add(removeWordCompnentsPanel, BorderLayout.CENTER);
		// add remove panel
		wordSynonymPanel.add(removeWordPanel);
		// add word synonym panel
		wordSynonymPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		mainPanel.add(wordSynonymPanel, BorderLayout.CENTER);

		setContentPane(mainPanel);

		setVisible(true);
	}

	// filter
	public void addFilterTextFieldActionListener(ActionListener l) {
		filterTextField.addActionListener(l);
	}

	public String getFilterTextFieldText() {
		return filterTextField.getText();
	}

	public void setFilterTextFieldText(String text) {
		filterTextField.setText(text);
	}

	// save load
	public String getFilePathText() {
		return saveLoadFilePathTextField.getText();
	}

	public void setFilePathText(String text) {
		saveLoadFilePathTextField.setText(text);
	}

	public void addSaveThesaurusButtonActionListener(ActionListener l) {
		saveThesaurusButton.addActionListener(l);
	}

	public void addLoadThesaurusButtonActionListener(ActionListener l) {
		loadThesaurusButton.addActionListener(l);
	}

	// word list
	public void addWordListSelectionListener(ListSelectionListener l) {
		wordList.addListSelectionListener(l);
	}

	public void setWordListModel(ListModel<Word> lm) {
		wordList.setModel(lm);
	}

	public boolean isWordListSelectionValid() {
		return wordList.getSelectedValue() != null;
	}

	public Word getWordListSelection() {
		return wordList.getSelectedValue();
	}

	// synonym list
	public void addSynonymListSelectionListener(ListSelectionListener l) {
		synonymList.addListSelectionListener(l);
	}

	public void setSynonymListModel(ListModel<Word> lm) {
		synonymList.setModel(lm);
	}

	public boolean isSynonymListSelectionValid() {
		return synonymList.getSelectedValue() != null;
	}

	public Word getSynonymListSelection() {
		return synonymList.getSelectedValue();
	}

	// add word panel
	public void setAddWordTextFieldText(String text) {
		addWordTextField.setText(text);
	}

	public String getAddWordTextFieldText() {
		return addWordTextField.getText();
	}

	public void setAddSynonymTextFieldText(String text) {
		addSynonymTextField.setText(text);
	}

	public String getAddSynonymTextFieldText() {
		return addSynonymTextField.getText();
	}

	public void addAddWordSynonymButtonActionListener(ActionListener l) {
		addWordSynonymButton.addActionListener(l);
	}

	// remove word panel
	public void setRemoveWordTextFieldText(String text) {
		removeWordTextField.setText(text);
	}

	public String getRemoveWordTextFieldText() {
		return removeWordTextField.getText();
	}

	public void addRemoveWordSynonymButtonActionListener(ActionListener l) {
		removeWordButton.addActionListener(l);
	}
}
