package src;

import java.util.ArrayList;

public class Partition {

	private String name;
	private int id;
	private ArrayList<PeriodicTask> workload;
	private SchedulingPolicy sched_pol;

	public Partition(String name, int id, SchedulingPolicy sched_pol) {
		this.name = name;
		this.id = id;
		this.workload = new ArrayList<PeriodicTask>();
		this.sched_pol = sched_pol;
	}

	public void addTask(PeriodicTask task) {
		workload.add(task);
	}

	public int getPartitionDBF(int t) {
		// if (sched_pol == SchedulingPolicy.EARLIEST_DEADLINE_FIRST) {
		int sum = 0;
		for (PeriodicTask pt : workload) {
			sum = pt.getTaskDBF(t);
		}
		return sum;
		// } else {
		// return 0;
		// }
	}

	public double getTaskUtilization() {
		double ut = 0.0;
		for (PeriodicTask pt : workload) {
			ut += (pt.getCapacity() * 1.0) / (pt.getPeriod() * 1.0);
		}
		return ut;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("***************\n");
		str.append("nome : " + name + "\n");
		str.append("id   : " + id + "\n");
		str.append("pol  : " + sched_pol + "\n");
		str.append("tasks:\n");
		for (PeriodicTask pt : workload) {
			str.append(pt.toString());
		}
		str.append("utilization : " + getTaskUtilization() + "\n");
		for (int i = 1; i <= 100; i++) {
			str.append("sbf " + i + " : " + getPartitionDBF(i) + "\n");
		}
		return str.toString();
	}
}
