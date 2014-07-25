package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import models.CommMode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import src.Airsched;

public class XmlCommParser {

	public static ArrayList<CommMode> parseCommunications() {
		ArrayList<CommMode> ret = new ArrayList<CommMode>();
		File folder = new File(Airsched.DEFAULT_COMM_DIR);
		File[] listOfXmlComm = folder.listFiles();
		for (File f : listOfXmlComm) {
			if (f.getName().endsWith(".xml")) {
				try {
					List<CommMode> loc = parseComm(f.getAbsolutePath());
					ret.addAll(loc);
				} catch (Exception e) {
					System.out.println("Partition parsing error.");
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	public static ArrayList<CommMode> parseCommunications(String dir) {
		ArrayList<CommMode> ret = new ArrayList<CommMode>();
		File folder = new File(dir);
		File[] listOfXmlComm = folder.listFiles();
		for (File f : listOfXmlComm) {
			if (f.getName().endsWith(".xml")) {
				try {
					List<CommMode> loc = parseComm(f.getAbsolutePath());
					ret.addAll(loc);
				} catch (Exception e) {
					System.out.println("Partition parsing error.");
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	private static LinkedList<CommMode> parseComm(String path)
			throws ParserConfigurationException, SAXException, IOException {
		// xml get root
		Document partXml = XmlReader.getDoc(path);
		Node partNod = partXml.getDocumentElement();
		Element partElem = (Element) partNod;
		// the return
		LinkedList<CommMode> ret = new LinkedList<CommMode>();
		// get comm nodes
		NodeList coms = partElem.getElementsByTagName("comm");

		for (int i = 0; i < coms.getLength(); i++) {
			// the node
			Element node = (Element) coms.item(i);
			// get comm type
			String mode = node.getElementsByTagName("comm_type").item(0)
					.getTextContent();
			// System.out.println("mode:" + mode);
			if (mode.equals(Airsched.MODE_SAMPLING)) {
				
				
				
				//SamplingComm smp = new 
			} else if (mode.equals(Airsched.MODE_QUEUEING)) {

			} else {
				System.out.println("Mode exception:" + mode);
			}
		}
		return ret;
		// return null;
	}

}