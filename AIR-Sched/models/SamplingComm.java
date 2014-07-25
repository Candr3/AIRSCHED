package models;

import java.util.List;

import utils.PartitionUtils;

@SuppressWarnings("unused")
public class SamplingComm implements CommMode {

	private String source_part;
	private String source_task;
	private String dest_part;
	private String dest_task;
	private int validityInterval;
	private int net_id;
	private int delay;

	public SamplingComm(String source_part, String source_task,
			String dest_part, String dest_task, int validityInterval,
			int net_id, int delay) {
		super();
		this.source_part = source_part;
		this.source_task = source_task;
		this.dest_part = dest_part;
		this.dest_task = dest_task;
		this.validityInterval = validityInterval;
		this.net_id = net_id;
		this.delay = delay;
	}

	public boolean checkFeasibility(List<Partition> lop) {
		PeriodicTask pt = PartitionUtils.getTask(lop, source_part, source_task);
		int send_per = pt.getPeriod();
		int send_cap = pt.getCapacity();
		// int recv_per = PartitionUtils.getTask(lop, dest_part,
		// dest_task).getPeriod();

		return ((2 * send_per) - send_cap) + delay <= validityInterval;
	}

}