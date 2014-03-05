package src;

import java.io.IOException;
import java.util.ArrayList;

import utils.XmlPartitionParser;
import utils.XmlReader;

public class Airsched {

	public static void main(String[] args) {

		// XmlReader.printXmlFile("partitions/partition1.xml");

		ArrayList<Partition> al = XmlPartitionParser.parsePartitions();

		for (Partition p : al)
			System.out.println(p.toString());

	}

}
