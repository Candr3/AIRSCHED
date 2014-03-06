package utils;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import src.Partition;
import src.PeriodicTask;
import src.SchedulingPolicy;

public class XmlPartitionParser {

	// le as partições dos ficheiros xml e retorna um array com os objectos que
	// representão as mesmas
	public static ArrayList<Partition> parsePartitions() {
		ArrayList<Partition> ret = new ArrayList<Partition>();
		File folder = new File("partitions");
		File[] listOfXmlPart = folder.listFiles();
		for (File f : listOfXmlPart) {
			if (f.getName().endsWith(".xml")) {
				try {
					ret.add(parsePartition(f.getAbsolutePath()));
				} catch (XmlReaderException e) {
					System.out.println("lol");
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	private static Partition parsePartition(String path)
			throws XmlReaderException {
		Document partXml = XmlReader.getDoc(path);
		Node partNod = partXml.getDocumentElement();
		Element partElem = (Element) partNod;

		String name = partElem.getElementsByTagName("partition_name").item(0)
				.getTextContent();

		int id = Integer.parseInt(partElem.getElementsByTagName("partition_id")
				.item(0).getTextContent());

		SchedulingPolicy sp = SchedulingPolicy.valueOf(partElem
				.getElementsByTagName("partition_sched_policy").item(0)
				.getTextContent());

		// System.out.println("n: " + name + "\nid: " + id + "\nsp: " + sp +
		// "\n");

		Partition ret = new Partition(name, id, sp);

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
