package cheddarInterface;

import java.io.File;
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

import cartsInterface.CartsModel;
import src.Partition;

public class CheddarParser {

	public static boolean createCheddarXml(List<Partition> lop, CartsModel cm) {

		try {
			int id = 0;

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// docs root node
			Element root = doc.createElement("cheddar");
			doc.appendChild(root);

			id++;
			root.appendChild(genCoreUnitElem(doc, id));

			// to xml
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			t.setOutputProperty(OutputKeys.INDENT, "yes");

			doc.setXmlStandalone(true);
			doc.normalize();
			doc.normalizeDocument();
			DOMSource ds = new DOMSource(doc);

			// -> file
			File dir = new File("cheddarFiles/xml");
			File file = new File("cheddarFiles/xml/input.xml");
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

	// gera o node com o xml de um core_unit
	// <core_units>
	// <core_unit id=" 1">
	// <object_type>CORE_OBJECT_TYPE</object_type>
	// <name>core1</name>
	// <scheduling>
	// <scheduling_parameters>
	// <scheduler_type>HIERARCHICAL_CYCLIC_PROTOCOL</scheduler_type>
	// <quantum>0</quantum>
	// <preemptive_type>PREEMPTIVE</preemptive_type>
	// <capacity>0</capacity>
	// <period>0</period>
	// <priority>0</priority>
	// <start_time>0</start_time>
	// </scheduling_parameters>
	// </scheduling>
	// <speed>1.00000</speed>
	// </core_unit>
	// </core_units>
	private static Element genCoreUnitElem(Document doc, int id) {

		// core_units
		Element ret = doc.createElement("core_units");

		// core_unit
		Element cu = doc.createElement("core_unit");
		ret.appendChild(cu);

		// core_unit id
		Attr cu_id = doc.createAttribute("id");
		cu_id.setValue(String.valueOf(id));
		cu.setAttributeNode(cu_id);

		// object_type
		Element objt = doc.createElement("object_type");
		objt.appendChild(doc.createTextNode("CORE_OBJECT_TYPE"));
		cu.appendChild(objt);

		// name
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode("core1"));
		cu.appendChild(name);

		// scheduling
		Element sched = doc.createElement("scheduling");
		cu.appendChild(sched);

		// speed
		Element speed = doc.createElement("speed");
		speed.appendChild(doc.createTextNode("1.00000"));
		cu.appendChild(speed);

		// 2nd level - append no scheduling
		// scheduling parameters
		Element schedp = doc.createElement("scheduling_parameters");
		sched.appendChild(schedp);

		// 3rd level - append no scheduling parameters
		// <scheduler_type>HIERARCHICAL_CYCLIC_PROTOCOL</scheduler_type>
		Element schedt = doc.createElement("scheduler_type");
		schedt.appendChild(doc.createTextNode("HIERARCHICAL_CYCLIC_PROTOCOL"));
		schedp.appendChild(schedt);

		// <quantum>0</quantum>
		Element quantum = doc.createElement("quantum");
		quantum.appendChild(doc.createTextNode("0"));
		schedp.appendChild(quantum);

		// <preemptive_type>PREEMPTIVE</preemptive_type>
		Element pt = doc.createElement("preemptive_type");
		pt.appendChild(doc.createTextNode("PREEMPTIVE"));
		schedp.appendChild(pt);

		// <capacity>0</capacity>
		Element capacity = doc.createElement("capacity");
		capacity.appendChild(doc.createTextNode("0"));
		schedp.appendChild(capacity);

		// <period>0</period>
		Element period = doc.createElement("period");
		period.appendChild(doc.createTextNode("0"));
		schedp.appendChild(period);

		// <priority>0</priority>
		Element priority = doc.createElement("priority");
		priority.appendChild(doc.createTextNode("0"));
		schedp.appendChild(priority);

		// <start_time>0</start_time>
		Element start_time = doc.createElement("start_time");
		start_time.appendChild(doc.createTextNode("0"));
		schedp.appendChild(start_time);

		return ret;
	}
}
