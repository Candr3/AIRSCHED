package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReader {

	public static void printXmlFile(String filePath) {

		try {
			Document doc = getDoc(filePath);
			System.out.println(doc.getDocumentElement().getNodeName());

			NodeList nl = doc.getDocumentElement().getChildNodes();
			for (int i = 0; i < nl.getLength(); i++)
				System.out.println(" -> " + nl.item(i).getNodeName());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Le um ficheiro xml, e retorna um doc
	 * 
	 **/
	public static Document getDoc(String filePath)
			throws ParserConfigurationException, SAXException, IOException {

		File file = new File(filePath);

		// leitura do file com merdas do java
		// http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		// normalizar
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		// mostrar versao
		// System.out.println("version\n" + doc.getXmlVersion()
		// + "\nencoding\n" + doc.getXmlEncoding());

		// mostar root
		// System.out.println("root\n"
		// + doc.getDocumentElement().getNodeName());

		return doc;

	}
}
