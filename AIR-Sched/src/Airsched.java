package src;

import java.util.ArrayList;

import cartsInterface.CartsInterface;

import utils.XmlPartitionParser;

public class Airsched {

	public static void main(String[] args) {
		// if(args[1] = "-dir") {
		// }
		// int[] v1 = { 7, 3, 1 };
		// int[] v2 = { 29, 10, 5 };
		// System.out.println(MathUtils.lcm(v1));
		// System.out.println(MathUtils.lcm(v2));

		/*
		 * String[] cmd = { "/bin/bash", "-c", "ls | grep log" }; try { Process
		 * p = Runtime.getRuntime().exec(cmd);
		 * 
		 * try { p.waitFor(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * BufferedReader br = new BufferedReader(new InputStreamReader(
		 * p.getInputStream()));
		 * 
		 * StringBuilder sb = new StringBuilder(""); String line;
		 * 
		 * while ((line = br.readLine()) != null) { sb.append(line + "\n"); }
		 * 
		 * System.out.println(sb.toString());
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// CompositionalAnalyzer.PRM(p)
		// XmlReader.printXmlFile("partitions/partition1.xml");
		ArrayList<Partition> al = XmlPartitionParser.parsePartitions();
		// CompositionalAnalyzer.PRM(al.get(0));
		for (Partition p : al)
			System.out.println(p.toString());
		CartsInterface.PartToCartsXml(al);
		CartsInterface.CartsAnalyse();
		CartsInterface.XmlExport();
		/*
		 * Double d1 = 1.23; Double d2 = 1.78;
		 * 
		 * int dd1 = d1.intValue(); int dd2 = d2.intValue();
		 * 
		 * System.out.println("d1->" + d1 + " d2->" + d2);
		 */
	}
}