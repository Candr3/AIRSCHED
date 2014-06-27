package models;

import java.util.ArrayList;
import java.util.List;

import utils.MathUtils;


public class Partition {

	private String name;
	private int id;
	private int criticality;
	private ArrayList<PeriodicTask> workload;
	private SchedulingPolicy sched_pol;

	public Partition(String name, int id, int criticality,
			SchedulingPolicy sched_pol) {
		this.name = name;
		this.id = id;
		this.criticality = criticality;
		this.workload = new ArrayList<PeriodicTask>();
		this.sched_pol = sched_pol;
	}

	public void addTask(PeriodicTask task) {
		workload.add(task);
	}

	public double getPartitionDBF(int t) {
		// if (sched_pol == SchedulingPolicy.EARLIEST_DEADLINE_FIRST) {
		double sum = 0.0;
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

	public int getHyperPeriod() {
		if (workload.size() <= 0)
			return 0;

		int hp = workload.get(0).getPeriod();
		for (int i = 1; i < workload.size(); i++) {
			hp = MathUtils.lcm(hp, workload.get(i).getPeriod());
		}

		return hp / 2;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return id;
	}

	public int getCriticality() {
		return criticality;
	}

	public List<PeriodicTask> getWorkload() {
		return workload;
	}

	public SchedulingPolicy getSchedulerType() {
		return sched_pol;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("***************\n");
		str.append("nome : " + name + "\n");
		str.append("id   : " + id + "\n");
		str.append("crit : " + criticality + "\n");
		str.append("pol  : " + sched_pol + "\n");
		str.append("tasks:\n");
		for (PeriodicTask pt : workload) {
			str.append(pt.toString());
		}
		str.append("utilization : " + getTaskUtilization() + "\n");
		str.append("hyperperiod : " + getHyperPeriod() + "\n");
		// for (int i = 1; i <= 100; i++) {
		// str.append("sbf " + i + " : " + getPartitionDBF(i) + "\n");
		// }
		return str.toString();
	}
}
