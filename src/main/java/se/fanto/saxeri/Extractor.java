package se.fanto.saxeri;

import java.util.ArrayList;
import java.util.List;

import se.fanto.saxeri.exceptions.MoreThanOneOccurrenceException;
import se.fanto.saxeri.exceptions.NoElementFoundException;

public class Extractor {

	// prevent instantiation
	private Extractor() {
		// ok
	}

	/**
	 * 
	 * @param doc
	 * @param key
	 * @return docElement
	 * @throws MoreThanOneOccurrenceException
	 * @throws NoElementFoundException
	 * @throws IllegalArgumentException
	 */
	public static DocElement getElement(List<DocElement> doc, String key) throws MoreThanOneOccurrenceException, NoElementFoundException, IllegalArgumentException {

		if (doc == null) {
			throw new IllegalArgumentException();
		}
		if (key == null) {
			throw new IllegalArgumentException();
		}

		DocElement foundElement = null;
		boolean alreadyFound = false;
		for (DocElement docElement : doc) {
			if (docElement.path.equals(key)) {
				if (!alreadyFound) {
					foundElement = docElement;
					alreadyFound = true;
				} else {
					throw new MoreThanOneOccurrenceException();
				}
			}
		}
		if (!alreadyFound) {
			throw new NoElementFoundException();
		}
		return foundElement;
	}

	/**
	 * 
	 * @param doc
	 * @param key
	 * @return List<DocElement>
	 * @throws IllegalArgumentException
	 */
	public static List<DocElement> getElements(List<DocElement> doc, String key) throws IllegalArgumentException {
		if (doc == null) {
			throw new IllegalArgumentException();
		}
		if (key == null) {
			throw new IllegalArgumentException();
		}

		List<DocElement> list = new ArrayList<>();
		for (DocElement docElement : doc) {
			if (docElement.path.equals(key)) {
				list.add(docElement);
			}
		}

		return list;
	}

}
