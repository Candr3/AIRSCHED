package src;

import java.util.ArrayList;

import cheddarInterface.CheddarParser;

import cartsInterface.CartsInterface;
import cartsInterface.CartsModel;

import utils.XmlPartitionParser;

public class Airsched {

	public static final double UTILIZATION_THRESHOLD = 1.0;

	public static final int NO_PARTITION_PADDING = 1;
	public static final int DUMMY_PARTITION_PADDING = 2;
	public static final int PARAMETRIC_PARTITION_PADDING = 3;

	private static int partition_padding_mode;

	public static void main(String[] args) {

		//partition_padding_mode = DUMMY_PARTITION_PADDING;
		partition_padding_mode = NO_PARTITION_PADDING;

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

		SchedSystem ss = new SchedSystem();

		// CompositionalAnalyzer.PRM(p)
		// XmlReader.printXmlFile("partitions/partition1.xml");
		ArrayList<Partition> al = XmlPartitionParser.parsePartitions();
		// CompositionalAnalyzer.PRM(al.get(0));
		for (Partition p : al) {
			ss.addPartition(p);
			// System.out.println(p.toString());
		}
		CartsInterface.PartToCartsXml(al);
		CartsInterface.CartsAnalyse();
		ss.addModels(CartsInterface.XmlExport(ss));
		System.out.println(ss.toString());
		for (int i = 0; i < ss.getModels().size(); i++) {
			String name = "input" + i;
			CheddarParser.createCheddarXml(ss.getPartitions(), ss.getModel(i),
					name);
		}
		/*
		 * Double d1 = 1.23; Double d2 = 1.78;
		 * 
		 * int dd1 = d1.intValue(); int dd2 = d2.intValue();
		 * 
		 * System.out.println("d1->" + d1 + " d2->" + d2);
		 */
	}

	public static int getPartitionPaddingMode() {
		return partition_padding_mode;
	}

}
