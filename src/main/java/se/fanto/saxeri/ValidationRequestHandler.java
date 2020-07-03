package se.fanto.saxeri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ValidationRequestHandler extends DefaultHandler {

	private int startElementInDoc = 0;

	private Stack<DocElement> stack = new Stack<>();
	private Stack<String> pathStack = new Stack<>();
	StringBuilder CURRENT_DATA = new StringBuilder();

	List<DocElement> ret = new ArrayList<>();

	private String pathStackToString() {
		int size = pathStack.size();
		String arr[] = new String[size];
		pathStack.copyInto(arr);
		List<String> theList = Arrays.asList(arr);
		StringBuilder b = new StringBuilder();
		theList.forEach(s -> {

			// remove q for cleaner readbility
			int pos = s.lastIndexOf(":");
			if ((pos > 0) && pos < s.length() + 1)
				s = s.substring(pos + 1);

			b.append(s);
			b.append("/");
		});
		return b.toString();
	}

	public List<DocElement> getAll() {		
		return ret;
	}

	@Override
	public void startDocument() {

	}

	@Override
	public void endDocument() {
		//System.out.println("STARTELEMENTS = " + startElementInDoc);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		startElementInDoc++;

		DocElement current = new DocElement();
		pathStack.push(qName);
		if (attributes != null) {
			for (int index = 0; index < attributes.getLength(); index++) {
				String attrName = attributes.getLocalName(index);
				String attrValue = attributes.getValue(index);
				NVP nvp = new NVP(attrName, attrValue);
				current.attributes.add(nvp);
			}
		}
		current.path = pathStackToString();
		stack.push(current);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		DocElement current = stack.peek();

		String val = CURRENT_DATA.toString().trim();
		current.value = val;
		ret.add(current);
		CURRENT_DATA = new StringBuilder();
		pathStack.pop();
		stack.pop();
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		CURRENT_DATA.append(ch, start, length);
	}

}
