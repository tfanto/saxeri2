package se.fanto.saxeri;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ValidationRequestParser {

	public List<DocElement> parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		SAXParser saxParser = factory.newSAXParser();
		ValidationRequestHandler handler = new ValidationRequestHandler();
		saxParser.parse(is, handler);
		List<DocElement> list = handler.getAll();
		return list;
	}

}
