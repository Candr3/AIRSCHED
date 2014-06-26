package models;

import java.util.List;

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
		
	}
	
}
