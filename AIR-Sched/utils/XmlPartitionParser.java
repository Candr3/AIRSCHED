package utils;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import src.Partition;

public class XmlPartitionParser {

	// le as partições dos ficheiros xml e retorna um array com os objectos que
	// representão as mesmas
	public static ArrayList<Partition> parsePartitions() {
		File folder = new File("partitions");
		File[] listOfXmlPart = folder.listFiles();
		for (File f : listOfXmlPart) {
			if (f.getName().endsWith(".xml")) {
				try {
					parsePartition(f.getAbsolutePath());
				} catch (XmlReaderException e) {
					System.out.println("lol");
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	private static Partition parsePartition(String path)
			throws XmlReaderException {
		Document partXml = XmlReader.getDoc(path);
		Node partNod = partXml.getDocumentElement();
		Element partElem = (Element) partNod;

		System.out.println(partElem.getElementsByTagName("partition_name")
				.item(0).getTextContent());
		System.out.println(partElem.getElementsByTagName("partition_id")
				.item(0).getTextContent());
		System.out.println(partElem
				.getElementsByTagName("partition_sched_policy").item(0)
				.getTextContent());
		return null;

		// Partition ret = new Partition(, partElem
		// .getElementsByTagName("partition_id").item(0).getTextContent(),
		// partElem.getElementsByTagName("partition_sched_policy").item(0)
		// .getTextContent());

	}
}
