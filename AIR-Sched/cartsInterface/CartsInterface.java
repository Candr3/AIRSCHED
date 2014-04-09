package cartsInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import src.Partition;
import src.PeriodicTask;
import src.SchedSystem;
import utils.BashUtils;
import utils.PartitionUtils;
import utils.XmlReader;
import utils.XmlReaderException;

public class CartsInterface {

	public static boolean PartToCartsXml(List<Partition> lop) {

		try {

			// xml writing method
			// http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/

			// input xml for carts
			// <system os_scheduler="DM" min_period="1" max_period="50">
			// <component name="c1" scheduler="DM" min_period="1"
			// max_period="50">
			// <task name="t1" p="30.0" d="30.0" e="5.0"></task>
			// <task name="t2" p="50.0" d="50.0" e="2.0"></task>
			// </component>
			// <component name="c2" scheduler="DM" min_period="1"
			// max_period="50">
			// <task name="t4" p="100.0" d="100.0" e="10.0"></task>
			// </component>
			// <component name="c3" scheduler="DM" min_period="1"
			// max_period="50">
			// <task name="t5" p="150.0" d="150.0" e="10.0"></task>
			// <task name="t6" p="200.0" d="200.0" e="10.0"></task>
			// </component>
			// </system>

			// hyperperiod
			int HyperPeriod = PartitionUtils.getPartsHp(lop);
			System.out.println("lol -> " + HyperPeriod);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.newDocument();

			// root element - system
			Element root = doc.createElement("system");
			doc.appendChild(root);

			// os_scheduler
			Attr ossched = doc.createAttribute("os_scheduler");
			ossched.setValue("DM");
			root.setAttributeNode(ossched);

			// min period
			Attr min_period = doc.createAttribute("min_period");
			min_period.setValue("1");
			root.setAttributeNode(min_period);

			// max period
			Attr max_period = doc.createAttribute("max_period");
			max_period.setValue(String.valueOf(HyperPeriod));
			root.setAttributeNode(max_period);

			for (Partition p : lop) {

				Element component = doc.createElement("component");
				root.appendChild(component);

				// name
				Attr c_name = doc.createAttribute("name");
				c_name.setValue(p.getName());
				component.setAttributeNode(c_name);

				// scheduler - cheddar only works with RM so this will be change
				// once multiples possibilities exist
				Attr c_sched = doc.createAttribute("scheduler");
				c_sched.setValue("DM");
				component.setAttributeNode(c_sched);

				// min_period
				Attr c_minp = doc.createAttribute("min_period");
				c_minp.setValue("1");
				component.setAttributeNode(c_minp);

				// max_period
				Attr c_maxp = doc.createAttribute("max_period");
				c_maxp.setValue(String.valueOf(HyperPeriod));
				component.setAttributeNode(c_maxp);

				for (PeriodicTask pt : p.getWorkload()) {

					Element task = doc.createElement("task");
					component.appendChild(task);

					// name
					Attr t_name = doc.createAttribute("name");
					t_name.setValue(pt.getName());
					task.setAttributeNode(t_name);

					// p
					Attr t_period = doc.createAttribute("p");
					t_period.setValue(String.valueOf(pt.getPeriod()));
					task.setAttributeNode(t_period);

					// d
					Attr t_deadline = doc.createAttribute("d");
					t_deadline.setValue(String.valueOf(pt.getPeriod()));
					task.setAttributeNode(t_deadline);

					// e
					Attr t_exect = doc.createAttribute("e");
					t_exect.setValue(String.valueOf(pt.getCapacity()));
					task.setAttributeNode(t_exect);

				}
			}

			// to xml
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");

			doc.setXmlStandalone(true);
			doc.normalize();
			doc.normalizeDocument();
			DOMSource ds = new DOMSource(doc);

			// -> file
			File dir = new File("carts/xml");
			File file = new File("carts/xml/input.xml");
			// System.out.println(dir.getCanonicalPath());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			// dir.mkdir();

			StreamResult sr = new StreamResult(file);

			// -> sys.out
			// StreamResult sr = new StreamResult(System.out);

			t.transform(ds, sr);

		} catch (Exception e) {

			// TODO
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void CartsAnalyse() {
		String[] cmd = { "java", "-jar", "carts/Carts.jar",
				"carts/xml/input.xml", "PRM", "carts/xml/output.xml" };
		System.out.println(BashUtils.cmdInterpreter(cmd));
	}

	public static boolean XmlExport(SchedSystem ss) {

		Document doc;
		try {
			doc = XmlReader.getDoc("carts/xml/output.xml");
		} catch (XmlReaderException e) {
			// TODO
			e.printStackTrace();
			return false;
		}

		// TODO THHHHHHHOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRRRRRRRR
		NodeList SUPER_NODE = doc.getChildNodes().item(0).getChildNodes();

		NodeList resource = SUPER_NODE.item(1).getChildNodes();
		NodeList p_task = SUPER_NODE.item(3).getChildNodes();
		NodeList[] components = new NodeList[ss.numberOfPartitions()];

		for (int i = 0; i < ss.numberOfPartitions(); i++) {
			components[i] = SUPER_NODE.item(5 + i * 2).getChildNodes();
		}

		for (int i = 0; i < resource.getLength(); i++) {
			// System.out.println(resource.item(i).getNodeName() + " " +
			// resource.item(i).hasAttributes());
			Node curr = resource.item(i);
			if (curr.hasAttributes()) {
				NamedNodeMap lol = curr.getAttributes();
				System.out.println(lol.getNamedItem("period"));
				System.out.println(lol.getNamedItem("bandwidth"));
				System.out.println(lol.getNamedItem("deadline"));
			}
		}

		return true;
	}

}