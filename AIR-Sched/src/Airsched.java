package src;

import java.util.ArrayList;

import utils.XmlPartitionParser;

public class Airsched {

	public static void main(String[] args) {

		//CompositionalAnalyzer.PRM(p)
		
		// XmlReader.printXmlFile("partitions/partition1.xml");

		ArrayList<Partition> al = XmlPartitionParser.parsePartitions();

		CompositionalAnalyzer.PRM(al.get(0));
		
		
		// for (Partition p : al)
		// System.out.println(p.toString());
/*
		Double d1 = 1.23;
		Double d2 = 1.78;
		
		int dd1 = d1.intValue();
		int dd2 = d2.intValue();

		System.out.println("d1->" + d1 + " d2->" + d2);
*/
	}

}
