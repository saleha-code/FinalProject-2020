package csv2xmlConverter;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {

	public static void writeXMLFile(List<String> headers, List<List<String>> records, String xmlFilename) {

		// create an XML document
		Document xmlDocument = null;
		try {
			xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// root elements (XML can only have one root element, you can rename it as you
		// like)

		Element root = xmlDocument.createElement("root");

		// we loop through each row one by one
		for (int i = 1; i < records.size(); i++) {

			// create a new row element
			Element rowElement = xmlDocument.createElement("row");

			// now for every row we create nodes from headers
			for (int x = 0; x < records.get(i).size(); x++) {

				Element columnElement = xmlDocument.createElement(headers.get(x));

				// add value of each column to the heading e.g. <Timestamp>11/27/2012
				// 3:55:25</Timestamp>

				columnElement.setTextContent(records.get(i).get(x));

				// now add each column to the row
				rowElement.appendChild(columnElement);
			}

			// add all rows to the root element in xml
			root.appendChild(rowElement);
		}

		// add root element to the document
		xmlDocument.appendChild(root);

		// write the content of xml into xml file
		DOMSource source = new DOMSource(xmlDocument);
		StreamResult result = new StreamResult(new File(xmlFilename));

		try {
			TransformerFactory.newInstance().newTransformer().transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
