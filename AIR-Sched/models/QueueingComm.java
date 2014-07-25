package models;

import java.util.List;

import utils.MathUtils;
import utils.PartitionUtils;

@SuppressWarnings("unused")
public class QueueingComm implements CommMode {

	private String source_part;
	private String source_task;
	private String dest_part;
	private String dest_task;
	private int bufferSize;
	private int net_id;
	private int delay;

	public QueueingComm(String source_part, String source_task,
			String dest_part, String dest_task, int bufferSize, int net_id,
			int delay) {
		super();
		this.source_part = source_part;
		this.source_task = source_task;
		this.dest_part = dest_part;
		this.dest_task = dest_task;
		this.bufferSize = bufferSize;
		this.net_id = net_id;
		this.delay = delay;
	}

	public boolean checkFeasibility(List<Partition> lop) {
		PeriodicTask pts = PartitionUtils
				.getTask(lop, source_part, source_task);
		int send_per = pts.getPeriod();
		// int send_cap = pts.getCapacity();
		PeriodicTask ptr = PartitionUtils
				.getTask(lop, source_part, source_task);
		int recv_per = ptr.getPeriod();
		// int recvs_cap = ptr.getCapacity();

		return MathUtils.upperBoundDivision(delay, send_per) <= bufferSize
				&& (send_per / recv_per) <= 1;
	}

}