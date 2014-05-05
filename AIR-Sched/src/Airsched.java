package src;

import gui.gui;

import java.io.File;
import java.util.ArrayList;

import cheddarInterface.CheddarParser;

import cartsInterface.CartsInterface;
import cartsInterface.CartsModel;

import utils.XmlPartitionParser;

public class Airsched {

	public static final int NO_PARTITION_PADDING = 0;
	public static final int DUMMY_PARTITION_PADDING = 1;
	public static final int PARAMETRIC_PARTITION_PADDING = 2;

	public static final int UTILIZATION_THRESHOLD = 100;

	public static final String DEFAULT_INPUT_DIR = "partitions";
	public static final String DEFAULT_OUTPUT_DIR = "cheddarFiles/xml";

	private static int partition_padding_mode;
	private static int utilization_threshold;
	private static String input_dir;
	private static String output_dir;

	public static void main(String[] args) {

		partition_padding_mode = NO_PARTITION_PADDING;
		utilization_threshold = UTILIZATION_THRESHOLD;
		input_dir = DEFAULT_INPUT_DIR;
		output_dir = DEFAULT_OUTPUT_DIR;

		gui.generateGUI();

	}

	public static void analyse() {

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
		for (File f : (new File(getOutput_dir())).listFiles()) {
			f.delete();
		}
		for (int i = 0; i < ss.getModels().size(); i++) {
			String name = "input" + i;
			CheddarParser.createCheddarXml(ss.getPartitions(), ss.getModel(i),
					name);
		}
	}

	public static int getPartitionPaddingMode() {
		return partition_padding_mode;
	}

	public static void setPartitionPaddingMode(int i) {
		if (i >= 0 && i <= 2) {
			partition_padding_mode = i;
		}
	}

	public static int getUtilizationThreshold() {
		return utilization_threshold;
	}

	public static void setUtilizationThreshold(int value) {
		if (value >= 50 && value <= 100) {
			utilization_threshold = value;
		}
	}

	public static String getInput_dir() {
		return input_dir;
	}

	public static String getOutput_dir() {
		return output_dir;
	}

	public static void setInput_dir(String new_input_dir) {
		input_dir = new_input_dir;
	}

	public static void setOutput_dir(String new_output_dir) {
		output_dir = new_output_dir;
	}

}
