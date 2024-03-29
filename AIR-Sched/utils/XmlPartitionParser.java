package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import models.Partition;
import models.PeriodicTask;
import models.SchedulingPolicy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import src.Airsched;

public class XmlPartitionParser {

	// le as partições dos ficheiros xml e retorna um array com os objectos que
	// representão as mesmas
	public static ArrayList<Partition> parsePartitions() {
		ArrayList<Partition> ret = new ArrayList<Partition>();
		File folder = new File(Airsched.DEFAULT_INPUT_DIR);
		File[] listOfXmlPart = folder.listFiles();
		for (File f : listOfXmlPart) {
			if (f.getName().endsWith(".xml")) {
				try {
					ret.add(parsePartition(f.getAbsolutePath()));
				} catch (Exception e) {
					System.out.println("Partition parsing error.");
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	public static ArrayList<Partition> parsePartitions(String dir) {
		ArrayList<Partition> ret = new ArrayList<Partition>();
		File folder = new File(dir);
		File[] listOfXmlPart = folder.listFiles();
		for (File f : listOfXmlPart) {
			if (f.getName().endsWith(".xml")) {
				try {
					ret.add(parsePartition(f.getAbsolutePath()));
				} catch (Exception e) {
					System.out.println("Partition parsing error.");
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	private static Partition parsePartition(String path)
			throws ParserConfigurationException, SAXException, IOException {
		Document partXml = XmlReader.getDoc(path);
		Node partNod = partXml.getDocumentElement();
		Element partElem = (Element) partNod;

		String name = partElem.getElementsByTagName("partition_name").item(0)
				.getTextContent();

		int id = Integer.parseInt(partElem.getElementsByTagName("partition_id")
				.item(0).getTextContent());

		int criticality = Integer.parseInt(partElem
				.getElementsByTagName("partition_criticality").item(0)
				.getTextContent());

		// <partition_duration>200</partition_duration>
		int duration = Integer.parseInt(partElem
				.getElementsByTagName("partition_duration").item(0)
				.getTextContent());

		SchedulingPolicy sp = SchedulingPolicy.valueOf(partElem
				.getElementsByTagName("partition_sched_policy").item(0)
				.getTextContent());

		// System.out.println("n: " + name + "\nid: " + id + "\nsp: " + sp +
		// "\n");

		Partition ret = new Partition(name, id, criticality, duration, sp);

		NodeList nl = partXml.getElementsByTagName("task");

		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			String task_name = e.getElementsByTagName("task_name").item(0)
					.getTextContent();
			int task_id = Integer.parseInt(partElem
					.getElementsByTagName("task_id").item(i).getTextContent());
			int task_period = Integer.parseInt(partElem
					.getElementsByTagName("task_period").item(i)
					.getTextContent());
			int task_capacity = Integer
					.parseInt(partElem.getElementsByTagName("task_wcet")
							.item(i).getTextContent());
			ret.addTask(new PeriodicTask(task_name, task_id, task_period,
					task_capacity));
		}

		return ret;

		// Partition ret = new Partition(, partElem
		// .getElementsByTagName("partition_id").item(0).getTextContent(),
		// partElem.getElementsByTagName("partition_sched_policy").item(0)
		// .getTextContent());

	}
}
