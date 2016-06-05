package model;

public class Word {
	/**
	 * The string representing the word
	 */
	private String wordText;

	public Word(String word) {
		this.wordText = word;
	}

	public String getWordText() {
		return wordText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wordText == null) ? 0 : wordText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (wordText == null) {
			if (other.wordText != null)
				return false;
		} else if (!wordText.equals(other.wordText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return wordText;
	}
}
