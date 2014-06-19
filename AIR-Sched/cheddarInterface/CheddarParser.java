package cheddarInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comparators.PartitionCriticalityFirstComparator;
import comparators.PartitionLargerPeriodFirstComparator;
import comparators.PartitionSmallerPeriodFirstComparator;

import models.CartsComponent;
import models.CartsModel;
import models.Partition;
import models.PeriodicTask;
import src.Airsched;
import utils.PartitionUtils;

public class CheddarParser {

	private static final String OUTPUT_DIR = "cheddarFiles/xml";

	// private static final String OUTPUT_FILE = "cheddarFiles/xml/input.xml";

	public static boolean createCheddarXml(List<Partition> lop, CartsModel cm,
			String filename) {

		// -> file
		File dir = new File(OUTPUT_DIR);
		// File file = new File(OUTPUT_FILE);
		File output_file = new File(OUTPUT_DIR + "/" + filename + ".xml");
		// System.out.println(dir.getCanonicalPath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!output_file.exists()) {
			try {
				output_file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		// dir.mkdir();

		try {

			int id = 0;
			BufferedWriter bwriter;
			bwriter = new BufferedWriter(new FileWriter(output_file));

			bwriter.write("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
			bwriter.write("<cheddar>\n");
			bwriter.write("  <core_units>\n");
			id++;
			bwriter.write("    <core_unit id=\" " + id + "\">\n");
			bwriter.write("      <object_type>CORE_OBJECT_TYPE</object_type>\n");
			bwriter.write("      <name>core1</name>\n");
			bwriter.write("      <scheduling>\n");
			bwriter.write("        <scheduling_parameters>\n");
			bwriter.write("        <scheduler_type>HIERARCHICAL_CYCLIC_PROTOCOL</scheduler_type>\n");
			bwriter.write("        <quantum>0</quantum>\n");
			bwriter.write("        <preemptive_type>PREEMPTIVE</preemptive_type>\n");
			bwriter.write("        <capacity>0</capacity>\n");
			bwriter.write("        <period>0</period>\n");
			bwriter.write("        <priority>0</priority>\n");
			bwriter.write("        <start_time>0</start_time>\n");
			bwriter.write("      </scheduling_parameters>\n");
			bwriter.write("    </scheduling>\n");
			bwriter.write("    <speed>1.00000</speed>\n");
			bwriter.write("    </core_unit>\n");
			bwriter.write("  </core_units>\n");
			bwriter.write("  <address_spaces>\n");

			// padding
			switch (Airsched.getPartitionPaddingMode()) {
			case (Airsched.DUMMY_PARTITION_PADDING):
				int sysIdle = cm.getSystemIdle();
				CartsComponent dummy = new CartsComponent("IDLE",
						"RATE_MONOTONIC_PROTOCOL", 0, sysIdle, sysIdle, 0.0);
				cm.addComponent(dummy);
				break;
			case (Airsched.PARAMETRIC_PARTITION_PADDING):
				int remaining = cm.getSystemIdle();
				//TODO
				break;
			default:
				break;
			}

			// sorts components
			switch (Airsched.getOrder()) {
			case (Airsched.NO_ORDER):
				break;
			case (Airsched.CRITICALITY_FIRST):
				cm.sort(new PartitionCriticalityFirstComparator());
				break;
			case (Airsched.LARGER_FIRST):
				cm.sort(new PartitionLargerPeriodFirstComparator());
				break;
			case (Airsched.SMALLER_FIRST):
				cm.sort(new PartitionSmallerPeriodFirstComparator());
				break;
			default:
				// System.out.println("deu merda!");
				break;
			}

			ArrayList<CartsComponent> comps = cm.getModel_components();
			for (int i = 0; i < comps.size(); i++) {
				id++;
				bwriter.write("    <address_space id=\" " + id + "\">\n");
				bwriter.write("      <object_type>ADDRESS_SPACE_OBJECT_TYPE</object_type>\n");
				bwriter.write("      <name>" + comps.get(i).getName()
						+ "</name>\n");
				bwriter.write("      <cpu_name>processor1</cpu_name>\n");
				bwriter.write("      <text_memory_size>0</text_memory_size>\n");
				bwriter.write("      <stack_memory_size>0</stack_memory_size>\n");
				bwriter.write("      <data_memory_size>0</data_memory_size>\n");
				bwriter.write("      <heap_memory_size>0</heap_memory_size>\n");
				bwriter.write("      <scheduling>\n");
				bwriter.write("        <scheduling_parameters>\n");
				bwriter.write("          <scheduler_type>RATE_MONOTONIC_PROTOCOL</scheduler_type>\n");
				bwriter.write("          <quantum>"
						+ comps.get(i).getExecution() + "</quantum>\n");
				bwriter.write("          <preemptive_type>PREEMPTIVE</preemptive_type>\n");
				bwriter.write("          <capacity>0</capacity>\n");
				bwriter.write("          <period>0</period>\n");
				bwriter.write("          <priority>0</priority>\n");
				bwriter.write("          <start_time>0</start_time>\n");
				bwriter.write("        </scheduling_parameters>\n");
				bwriter.write("      </scheduling>\n");
				bwriter.write("    </address_space>\n");
			}
			/*
			 * // IDLE PARTITION if (Airsched.getPartitionPaddingMode() ==
			 * Airsched.DUMMY_PARTITION_PADDING) { int sysIdle =
			 * cm.getSystemIdle(); if (sysIdle > 0) { id++;
			 * bwriter.write("    <address_space id=\" " + id + "\">\n");
			 * bwriter
			 * .write("      <object_type>ADDRESS_SPACE_OBJECT_TYPE</object_type>\n"
			 * ); bwriter.write("      <name>SYSTEM_IDLE</name>\n");
			 * bwriter.write("      <cpu_name>processor1</cpu_name>\n");
			 * bwriter.write("      <text_memory_size>0</text_memory_size>\n");
			 * bwriter
			 * .write("      <stack_memory_size>0</stack_memory_size>\n");
			 * bwriter.write("      <data_memory_size>0</data_memory_size>\n");
			 * bwriter.write("      <heap_memory_size>0</heap_memory_size>\n");
			 * bwriter.write("      <scheduling>\n");
			 * bwriter.write("        <scheduling_parameters>\n");
			 * bwriter.write(
			 * "          <scheduler_type>RATE_MONOTONIC_PROTOCOL</scheduler_type>\n"
			 * ); bwriter.write("          <quantum>" + sysIdle +
			 * "</quantum>\n"); bwriter.write(
			 * "          <preemptive_type>PREEMPTIVE</preemptive_type>\n");
			 * bwriter.write("          <capacity>0</capacity>\n");
			 * bwriter.write("          <period>0</period>\n");
			 * bwriter.write("          <priority>0</priority>\n");
			 * bwriter.write("          <start_time>0</start_time>\n");
			 * bwriter.write("        </scheduling_parameters>\n");
			 * bwriter.write("      </scheduling>\n");
			 * bwriter.write("    </address_space>\n"); } } // END OF DUMMY
			 * PARTITION
			 */
			bwriter.write("  </address_spaces>\n");
			bwriter.write("  <processors>\n");
			id++;
			bwriter.write("    <mono_core_processor id=\" " + id + "\">\n");
			bwriter.write("      <object_type>PROCESSOR_OBJECT_TYPE</object_type>\n");
			bwriter.write("      <name>processor1</name>\n");
			bwriter.write("      <network>a_network</network>\n");
			bwriter.write("      <processor_type>MONOCORE_TYPE</processor_type>\n");
			bwriter.write("      <migration_type>NO_MIGRATION_TYPE</migration_type>\n");
			bwriter.write("      <core ref=\" 1\"/>\n");
			bwriter.write("    </mono_core_processor>\n");
			bwriter.write("  </processors>\n");
			bwriter.write("  <tasks>\n");
			// for (Partition p : lop) {
			for (CartsComponent cc : comps) {
				Partition p = PartitionUtils.getPartition(lop, cc.getName());
				if (p != null) {
					for (PeriodicTask pt : p.getWorkload()) {
						id++;
						bwriter.write("    <periodic_task id=\" " + id
								+ "\">\n");
						bwriter.write("      <object_type>TASK_OBJECT_TYPE</object_type>\n");
						bwriter.write("      <name>" + p.getName() + "_"
								+ pt.getName() + "</name>\n");
						bwriter.write("      <task_type>PERIODIC_TYPE</task_type>\n");
						bwriter.write("      <cpu_name>processor1</cpu_name>\n");
						bwriter.write("      <address_space_name>"
								+ p.getName() + "</address_space_name>\n");
						bwriter.write("      <capacity>" + pt.getCapacity()
								+ "</capacity>\n");
						bwriter.write("      <deadline>" + pt.getPeriod()
								+ "</deadline>\n");
						bwriter.write("      <start_time>0</start_time>\n");
						bwriter.write("      <priority>1</priority>\n");
						bwriter.write("      <blocking_time>0</blocking_time>\n");
						bwriter.write("      <policy>SCHED_FIFO</policy>\n");
						bwriter.write("      <text_memory_size>0</text_memory_size>\n");
						bwriter.write("      <stack_memory_size>0</stack_memory_size>\n");
						bwriter.write("      <criticality>0</criticality>\n");
						bwriter.write("      <context_switch_overhead>0</context_switch_overhead>\n");
						bwriter.write("      <period>" + pt.getPeriod()
								+ "</period>\n");
						bwriter.write("      <jitter>0</jitter>\n");
						bwriter.write("    </periodic_task>\n");
					}
				}
			}
			bwriter.write("  </tasks>\n");
			bwriter.write("</cheddar>");

			bwriter.flush();
			bwriter.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}