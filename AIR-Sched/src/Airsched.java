package src;

import java.io.IOException;

import utils.XmlPartitionParser;
import utils.XmlReader;

public class Airsched {

	public static void main(String[] args) {

		// XmlReader.printXmlFile("partitions/partition1.xml");

		XmlPartitionParser.parsePartitions();

	}

}
